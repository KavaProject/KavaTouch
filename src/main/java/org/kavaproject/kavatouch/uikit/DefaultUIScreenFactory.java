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
import org.kavaproject.kavatouch.foundation.NotificationCenter;

import org.kavaproject.kavatouch.util.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class DefaultUIScreenFactory implements UIScreenFactory {
    private final UIApplication mSharedApplication;
    private final NotificationCenter mDefaultNotificationCenter;
    private final UIScreenModeFactory mUIScreenModeFactory;
    private final DeviceHandle mDeviceHandle;
    private List<UIScreen> mScreens = new ArrayList<UIScreen>();

    @Inject
    protected DefaultUIScreenFactory(UIApplication sharedApplication, NotificationCenter defaultNotificationCenter,
                                     UIScreenModeFactory uiScreenModeFactory, DeviceHandle deviceHandle) {
        mSharedApplication = sharedApplication;
        mDefaultNotificationCenter = defaultNotificationCenter;
        mUIScreenModeFactory = uiScreenModeFactory;
        mDeviceHandle = deviceHandle;
    }

    @Override
    public List<UIScreen> screens() {
        return new ArrayList<UIScreen>(mScreens);
    }

    @Override
    public UIScreen create() {
        UIScreen screen = new DefaultUIScreen(this, mSharedApplication, mDefaultNotificationCenter,
                mUIScreenModeFactory, mDeviceHandle);
        mScreens.add(screen);
        return screen;
    }
}
