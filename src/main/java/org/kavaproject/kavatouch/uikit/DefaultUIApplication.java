/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.uikit;

import org.kavaproject.kavatouch.coregraphics.GraphicsRect;
import org.kavaproject.kavatouch.foundation.FoundationDate;
import org.kavaproject.kavatouch.foundation.URL;
import org.kavaproject.kavatouch.runtime.MethodPerformer;
import org.kavaproject.kavatouch.runtime.MethodResolver;
import org.kavaproject.kavatouch.runtime.SEL;
import org.kavaproject.kavatouch.uikit.staging.UIActionSheet;
import org.kavaproject.kavatouch.uikit.staging.UIBackgroundTaskIdentifier;
import org.kavaproject.kavatouch.uikit.staging.UILocalNotification;
import org.kavaproject.kavatouch.util.NotImplementedException;

import java.util.*;

public class DefaultUIApplication extends SimpleUIResponder implements UIApplication {
    private final UIDevice mCurrentUIDevice;
    private final MethodPerformer mMethodPerformer;
    private final UIApplicationWindows mUIApplicationWindows;
    private UIApplicationDelegate mDelegate;
    private boolean mStatusBarHidden = true;
    private GraphicsRect sStatusBarFrame = GraphicsRect.ZERO;
    private FoundationDate mBackgroundTasksExpirationDate;
    private boolean mNetworkActivityIndicatorVisible = false;
    private int mInteractionEventIgnoreCounter;
    private UIInterfaceOrientation mStatusBarOrientation;
    private UIStatusBarStyle mStatusBarStyle = UIStatusBarStyle.DEFAULT;
    private List<UILocalNotification> mScheduledLocalNotifications = new ArrayList<UILocalNotification>();
    private boolean mApplicationSupportsShakeToEdit = true;
    private boolean mRemoteControlEventsEnabled = true;
    private int mApplicationIconBadgeNumber = 0;
    private UIApplicationState mApplicationState = UIApplicationState.ACTIVE;

    public DefaultUIApplication(UIApplicationFactory uiApplicationFactory, UIDevice currentUIDevice,
                                MethodResolver methodResolver, MethodPerformer methodPerformer,
                                UIApplicationWindows uiApplicationWindows) {
        super(uiApplicationFactory, methodResolver);
        mCurrentUIDevice = currentUIDevice;
        mMethodPerformer = methodPerformer;
        mUIApplicationWindows = uiApplicationWindows;
    }

    @Override
    public UIApplicationDelegate getDelegate() {
        return mDelegate;
    }

    @Override
    public void setDelegate(UIApplicationDelegate value) {
        mDelegate = value;
    }

    @Override
    public UIWindow getKeyWindow() {
        return mUIApplicationWindows.getKeyWindow();
    }

    @Override
    public EnumSet<UIInterfaceOrientation> supportedInterfaceOrientationsForWindow(UIWindow window) {
        return EnumSet.allOf(UIInterfaceOrientation.class);
    }

    @Override
    public void sendEvent(UIEvent event) {
        for (UIWindow window : getWindows()) {
            if (event.touchesForWindow(window).isEmpty()) {
                continue;
            }
            window.sendEvent(event);
        }
    }

    @Override
    public List<UIWindow> getWindows() {
        ArrayList<UIWindow> windows = new ArrayList<UIWindow>(mUIApplicationWindows.getWindows());
        Collections.sort(windows, new Comparator<UIWindow>() {
            @Override
            public int compare(UIWindow lhs, UIWindow rhs) {
                float d = rhs.getWindowLevel() - lhs.getWindowLevel();
                return d > 0 ? 1 : (d < 0 ? -1 : 0);
            }
        });
        return windows;
    }

    @Override
    public boolean sendAction(SEL action, Object target, Object sender, UIEvent event) {
        if (target == null) {
            Object responder = sender;
            while (responder != null) {
                if (getMethodResolver().respondsToSelector(responder, action)) {
                    target = responder;
                    break;
                } else if (responder instanceof UIResponder) {
                    responder = ((UIResponder) responder).nextResponder();
                } else {
                    responder = null;
                }
            }
        }
        if (target != null) {
            mMethodPerformer.perform(target, action, sender, event);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void beginIgnoringInteractionEvents() {
        mInteractionEventIgnoreCounter++;
    }

    @Override
    public void endIgnoringInteractionEvents() {
        mInteractionEventIgnoreCounter--;
    }

    @Override
    public boolean isIgnoringInteractionEvents() {
        return mInteractionEventIgnoreCounter > 0;
    }

    @Override
    public boolean getApplicationSupportsShakeToEdit() {
        return mApplicationSupportsShakeToEdit;
    }

    @Override
    public void setApplicationSupportsShakeToEdit(boolean value) {
        mApplicationSupportsShakeToEdit = value;
    }

    @Override
    public boolean isProximitySensingEnabled() {
        return mCurrentUIDevice.isProximityMonitoringEnabled();
    }

    @Override
    public void setProximitySensingEnabled(boolean value) {
        mCurrentUIDevice.setProximityMonitoringEnabled(value);
    }

    @Override
    public boolean openURL(URL url) {
        throw new NotImplementedException();
    }

    @Override
    public boolean canOpenURL(URL url) {
        throw new NotImplementedException();
    }

    @Override
    public void registerForRemoteNotificationTypes(EnumSet<UIRemoteNotificationType> types) {
        throw new NotImplementedException();
    }

    @Override
    public void unregisterForRemoteNotifications() {
        throw new NotImplementedException();
    }

    @Override
    public EnumSet<UIRemoteNotificationType> enabledRemoteNotificationTypes() {
        throw new NotImplementedException();
    }

    @Override
    public boolean isIdleTimerDisabled() {
        return false;
    }

    @Override
    public void setIdleTimerDisabled(boolean value) {
        throw new NotImplementedException();
    }

    @Override
    public UIApplicationState getApplicationState() {
        return mApplicationState;
    }

    @Override
    public double getBackgroundTimeRemaining() {
        return mBackgroundTasksExpirationDate.timeIntervalSinceNow();
    }

    @Override
    public UIBackgroundTaskIdentifier beginBackgroundTaskWithExpirationHandler(Runnable handler) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void endBackgroundTask(UIBackgroundTaskIdentifier taskIdentifier) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public boolean setKeepAliveTimeoutHandler(double timeout, Runnable handler) {
        throw new NotImplementedException();
    }

    @Override
    public void clearKeepAliveTimeout() {
        throw new NotImplementedException();
    }

    @Override
    public void extendStateRestoration() {
        throw new NotImplementedException();
    }

    @Override
    public void completeStateRestoration() {
        throw new NotImplementedException();
    }

    @Override
    public void presentLocalNotificationNow(UILocalNotification notification) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public List<UILocalNotification> getScheduledLocalNotifications() {
        return new ArrayList<UILocalNotification>(mScheduledLocalNotifications);
    }

    @Override
    public void setScheduledLocalNotifications(List<UILocalNotification> value) {
        cancelAllLocalNotifications();
        for (UILocalNotification notification : value) {
            scheduleLocalNotification(notification);
        }
    }

    @Override
    public void cancelAllLocalNotifications() {
        for (UILocalNotification notification : mScheduledLocalNotifications) {
            cancelLocalNotification(notification);
        }
    }

    @Override
    public void cancelLocalNotification(UILocalNotification notification) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void scheduleLocalNotification(UILocalNotification notification) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public boolean getProtectedDataAvailable() {
        return true;
    }

    @Override
    public void beginReceivingRemoteControlEvents() {
        mRemoteControlEventsEnabled = true;
    }

    @Override
    public void endReceivingRemoteControlEvents() {
        mRemoteControlEventsEnabled = false;
    }

    @Override
    public UIInterfaceOrientation getStatusBarOrientation() {
        return mStatusBarOrientation != null ? mStatusBarOrientation : UIInterfaceOrientation.PORTRAIT;
    }

    @Override
    public void setStatusBarOrientation(UIInterfaceOrientation value) {
        setStatusBarOrientation(value, false);
    }

    @Override
    public void setStatusBarOrientation(UIInterfaceOrientation orientation, boolean animated) {
        mStatusBarOrientation = orientation;
    }

    @Override
    public float getStatusBarOrientationAnimationDuration() {
        return 0.3F;
    }

    @Override
    public boolean isStatusBarHidden() {
        return mStatusBarHidden;
    }

    @Override
    public void setStatusBarHidden(boolean value) {
        setStatusBarHidden(value, UIStatusBarAnimation.NONE);
    }

    @Override
    public void setStatusBarHidden(boolean hidden, UIStatusBarAnimation animation) {
        mStatusBarHidden = hidden;
    }

    @Override
    public UIStatusBarStyle getStatusBarStyle() {
        return mStatusBarStyle;
    }

    @Override
    public void setStatusBarStyle(UIStatusBarStyle value) {
        setStatusBarStyleAnimated(value, false);
    }

    @Override
    public void setStatusBarStyleAnimated(UIStatusBarStyle style, boolean animated) {
        mStatusBarStyle = style;
    }

    @Override
    public GraphicsRect getStatusBarFrame() {
        return mStatusBarHidden ? GraphicsRect.ZERO : sStatusBarFrame;
    }

    @Override
    public boolean isNetworkActivityIndicatorVisible() {

        return mNetworkActivityIndicatorVisible;
    }

    @Override
    public void setNetworkActivityIndicatorVisible(boolean value) {
        if (mNetworkActivityIndicatorVisible == value) {
            return;
        }
        mNetworkActivityIndicatorVisible = value;
    }

    @Override
    public int getApplicationIconBadgeNumber() {
        return mApplicationIconBadgeNumber;
    }

    @Override
    public void setApplicationIconBadgeNumber(int value) {
        mApplicationIconBadgeNumber = value;
    }

    @Override
    public UIUserInterfaceLayoutDirection getUserInterfaceLayoutDirection() {
        return UIUserInterfaceLayoutDirection.LEFT_TO_RIGHT;
    }

    @Override
    public void setStatusBarHidden(boolean hidden, boolean animated) {
        setStatusBarHidden(hidden, animated ? UIStatusBarAnimation.FADE : UIStatusBarAnimation.NONE);
    }

    @Override
    public void setNewsstandIconImage(UIImage image) {
        throw new NotImplementedException();
    }

    @Override
    public void onWillPresentActionSheet(UIActionSheet actionSheet) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void onDidPresentActionSheet(UIActionSheet actionSheet) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void onWillDismiss(UIActionSheet actionSheet, int buttonIndex) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void onDidDismiss(UIActionSheet actionSheet, int buttonIndex) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public UIApplicationFactory getFactory() {
        return (UIApplicationFactory) super.getFactory();
    }
}
