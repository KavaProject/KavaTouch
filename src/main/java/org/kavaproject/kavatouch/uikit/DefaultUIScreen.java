/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.uikit;

import org.kavaproject.kavatouch.DeviceHandle;
import org.kavaproject.kavatouch.coregraphics.GraphicsRect;
import org.kavaproject.kavatouch.coregraphics.GraphicsSize;
import org.kavaproject.kavatouch.foundation.NotificationCenter;
import org.kavaproject.kavatouch.runtime.SEL;
import org.kavaproject.kavatouch.uikit.staging.AnimationDisplayLink;
import org.kavaproject.kavatouch.util.NotImplementedException;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class DefaultUIScreen implements UIScreen, DeviceHandle.Observer {
    private final UIScreenFactory mUIScreenFactory;
    private final UIApplication mSharedApplication;
    private final NotificationCenter mDefaultNotificationCenter;
    private final UIScreenModeFactory mUIScreenModeFactory;
    private final DeviceHandle mDeviceHandle;
    private float mScale;
    private UIScreenMode mOnlyMode;
    private GraphicsRect mBounds = GraphicsRect.ZERO;

    protected DefaultUIScreen(UIScreenFactory uiScreenFactory, UIApplication sharedApplication,
                              NotificationCenter defaultNotificationCenter, UIScreenModeFactory uiScreenModeFactory,
                              DeviceHandle deviceHandle) {
        mUIScreenFactory = uiScreenFactory;
        mSharedApplication = sharedApplication;
        mDefaultNotificationCenter = defaultNotificationCenter;
        mUIScreenModeFactory = uiScreenModeFactory;
        mDeviceHandle = deviceHandle;
        mDeviceHandle.addObserver(this);
        onDeviceConfigurationChanged();
    }

    @Override
    public void onDeviceConfigurationChanged() {
        mScale = mDeviceHandle.getScale();
        GraphicsRect rect = mDeviceHandle.getScreenRect();
        mBounds = new GraphicsRect(0, 0, rect.getWidth() / mScale, rect.getHeight() / mScale);
        Map userInfo = getCurrentMode() != null ? Collections.singletonMap("_previousMode", getCurrentMode()) : null;
        mOnlyMode = mUIScreenModeFactory.create(mBounds.size, 1);
        mDefaultNotificationCenter.postNotificationName(NOTIFICATION_MODE_DID_CHANGE, this, userInfo);
        for (UIWindow window : mSharedApplication.getWindows()) {
            window.setNeedsDisplay();
        }
    }

    @Override
    public UIScreen getMirroredScreen() {
        return null;
    }

    @Override
    public GraphicsRect getApplicationFrame() {
//        float statusBarHeight = mSharedApplication.isStatusBarHidden() ? 0 : 20;
        float statusBarHeight = mDeviceHandle.getStatusBarHeight() / mScale;
        GraphicsSize size = getBounds().size;
        return new GraphicsRect(0, statusBarHeight, size.width, size.height - statusBarHeight);
    }

    @Override
    public GraphicsRect getBounds() {
        return mBounds;
    }

    @Override
    public float getScale() {
        return mScale;
    }

    @Override
    public UIScreenMode getPreferredMode() {
        return mOnlyMode;
    }

    @Override
    public List<UIScreenMode> getAvailableModes() {
        return mOnlyMode != null ? Collections.singletonList(mOnlyMode) : null;
    }

    @Override
    public AnimationDisplayLink createDisplayLink(Object target, SEL selector) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public float getBrightness() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void setBrightness(float value) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public boolean getWantsSoftwareDimming() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void setWantsSoftwareDimming(boolean value) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public UIScreenOverscanCompensation getOverscanCompensation() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void setOverscanCompensation(UIScreenOverscanCompensation value) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public UIScreenMode getCurrentMode() {
        return mOnlyMode;
    }

    @Override
    public UIScreenFactory getFactory() {
        return mUIScreenFactory;
    }
}
