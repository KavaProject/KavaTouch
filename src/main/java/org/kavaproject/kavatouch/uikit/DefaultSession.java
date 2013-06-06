/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.uikit;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import org.kavaproject.kavatouch.DeviceHandle;
import org.kavaproject.kavatouch.corefoundation.CoreBundle;
import org.kavaproject.kavatouch.corefoundation.CoreRunLoop;
import org.kavaproject.kavatouch.corefoundation.CoreURL;
import org.kavaproject.kavatouch.corefoundation.CoreURLPathStyle;
import org.kavaproject.kavatouch.dispatch.DispatchQueue;
import org.kavaproject.kavatouch.dispatch.DispatchQueueItem;
import org.kavaproject.kavatouch.dispatch.DispatchSink;
import org.kavaproject.kavatouch.foundation.NotificationCenter;
import org.kavaproject.kavatouch.internal.*;
import org.kavaproject.kavatouch.util.AssetUtil;

import org.kavaproject.kavatouch.util.inject.Inject;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class DefaultSession implements Session {
    private final NotificationCenter mDefaultNotificationCenter;
    private final SystemEventSource mSystemEventSource;
    private final ScreenRedrawObserver mScreenRedrawObserver;
    private final UIApplication mSharedApplication;
    private final DeviceHandle mDeviceHandle;
    private final Map<String, CoreBundle> loadedBundles = new HashMap<String, CoreBundle>();
    private final Map<String, String> bundleIDs = new HashMap<String, String>();
    private MachPort mMainPort;
    private String mMainBundleID;

    @Inject
    protected DefaultSession(NotificationCenter defaultNotificationCenter, SystemEventSource systemEventSource,
                             ScreenRedrawObserver screenRedrawObserver, UIApplication sharedApplication,
                             DeviceHandle deviceHandle) {
        mDefaultNotificationCenter = defaultNotificationCenter;
        mSystemEventSource = systemEventSource;
        mScreenRedrawObserver = screenRedrawObserver;
        mSharedApplication = sharedApplication;
        mDeviceHandle = deviceHandle;
        mMainPort = new MachPort("org.kavaproject/MainPort");
        mMainPort.releaseReceiveRights();
    }

    @Override
    public void run() {
        initGCD();
        initBundle();
        notifyDelegate();
        initRL();
        while (true) {
            CoreRunLoop.runInMode(CoreRunLoop.MODE_DEFAULT, Double.MAX_VALUE, false);
        }
    }

    private void notifyDelegate() {
//        UIImage defaultImage = UIImage.imageNamed("Default.png");
//        UIImageView defaultImageView = UIImageViewFactory.create(defaultImage);
//        defaultImageView.setContentMode(UIViewContentMode.CENTER);
//        UIScreen screen = UIScreenFactory.INSTANCE.mainScreen();
//        UIWindow defaultWindow = UIWindowFactory.INSTANCE.create(screen.getBounds());
//        defaultWindow.setUserInteractionEnabled(false);
//        defaultWindow.setScreen(screen);
//        defaultWindow.setOpaque(true);
//        defaultWindow.addSubview(defaultImageView);
//        defaultWindow.makeKeyAndVisible();
        UIApplicationDelegate delegate = mSharedApplication.getDelegate();
        if (delegate instanceof UIApplicationDelegate.DeprecatedMethods) {
            ((UIApplicationDelegate.DeprecatedMethods) delegate).onDidFinishLaunching(mSharedApplication);
        } else {
            delegate.onDidFinishLaunching(mSharedApplication, new UIApplicationLaunchOptions());
        }
        mDefaultNotificationCenter.postNotificationName(UIApplication.NOTIFICATION_DID_FINISH_LAUNCHING,
                mSharedApplication);
    }

    private void initBundle() {
        //copy main bundle to accessible location
        Context context = mDeviceHandle.getContext();
        String appBundleName;
        PackageManager p = context.getPackageManager();
        ApplicationInfo info = context.getApplicationInfo();
        String label = p.getApplicationLabel(info).toString();
        appBundleName = label + ".app";
        File assetFile = new File("");// new File(appBundleName);
        File storageFile = new File(context.getFilesDir(), appBundleName);
        CoreURL appBundleUrl = new CoreURL(storageFile.getPath(), CoreURLPathStyle.HFS, true);
        mMainBundleID = CoreBundle.create(appBundleUrl).getIdentifier();
        SharedPreferences preferences = context.getSharedPreferences("org.kavaproject.ApplicationLoop", 0);
        int lastRunVersion = preferences.getInt("LAST_RUN_VERSION", -1);
        int version;
        try {
            version = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            throw new Error(e);
        }
        if (lastRunVersion == version) {
            return;
        }
        if (!AssetUtil.copyAssetsToInternalStorage(assetFile, storageFile, context.getAssets())) {
            throw new Error();
        }
        if (!DEBUG) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("LAST_RUN_VERSION", version);
            editor.commit();
        }
    }

    private void initRL() {
        //start listening for touch events
        mSystemEventSource.addToCurrentRunLoop();
        //start redrawing on ui changes
        mScreenRedrawObserver.addToCurrentRunLoop();
    }

    private void initGCD() {
        //connect GCD main queue to mach port mechanism
        final MachMessage message = new MachMessage();
        message.destinationPort = mMainPort;
        DispatchQueue.getMainQueue().setTargetQueue(new DispatchSink() {
            @Override
            public void push(DispatchQueueItem item) {
                message.send(0, TimeUnit.NANOSECONDS);
            }
        });
    }

    @Override
    public void main(UIApplicationDelegate applicationDelegate) {
        mSharedApplication.setDelegate(applicationDelegate);
        Thread mainThread = new Thread(this, "org.kavaproject.uikit.UIApplication/main");
        Handler handler = new Handler();
        DispatchQueue.init(handler, null, mainThread);
        mainThread.start();
        SessionProvider.session = this;
    }

    @Override
    public MachPort getMainPort() {
        return mMainPort;
    }

    @Override
    public String getBundleID(String path) {
        return bundleIDs.get(path);
    }

    @Override
    public void putBundle(String id, String path, CoreBundle bundle) {
        loadedBundles.put(id, bundle);
        bundleIDs.put(path, id);
    }

    @Override
    public CoreBundle getBundle(String bundleID) {
        return loadedBundles.get(bundleID);
    }

    @Override
    public String getMainBundleID() {
        return mMainBundleID;
    }
}
