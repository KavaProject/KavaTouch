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
import org.kavaproject.kavatouch.coreanimation.AnimationLayerFactory;
import org.kavaproject.kavatouch.coregraphics.GraphicsRect;
import org.kavaproject.kavatouch.foundation.Coder;
import org.kavaproject.kavatouch.foundation.NotificationCenter;
import org.kavaproject.kavatouch.runtime.MethodResolver;

import javax.inject.Inject;

public class DefaultUIWindowFactory extends DefaultUIViewFactory implements UIWindowFactory {
    private final UIApplication mSharedApplication;
    private final NotificationCenter mDefaultNotificationCenter;
    private final UIApplicationWindows mUIApplicationWindows;
    private final DeviceHandle mDeviceHandle;

    @Inject
    protected DefaultUIWindowFactory(UIApplication sharedAppliaction, NotificationCenter defaultNotificationCenter,
                                     AnimationLayerFactory animationLayerFactory, UIGraphics uiGraphics,
                                     UIColorFactory uiColorFactory, UIScreen mainScreen,
                                     MethodResolver methodResolver, UIApplicationWindows uiApplicationWindows,
                                     DeviceHandle deviceHandle) {
        super(animationLayerFactory, uiGraphics, uiColorFactory, mainScreen, methodResolver);
        mSharedApplication = sharedAppliaction;
        mDefaultNotificationCenter = defaultNotificationCenter;
        mUIApplicationWindows = uiApplicationWindows;
        mDeviceHandle = deviceHandle;
    }

    @Override
    public UIWindow create() {
        return create(GraphicsRect.NULL);
    }

    @Override
    public UIView create(Coder decoder) {
        return new DefaultUIWindow(decoder, this, getMainScreen(), mSharedApplication, mDefaultNotificationCenter,
                getUIGraphics(), getUIColorFactory(), getMethodResolver(), mUIApplicationWindows,
                mDeviceHandle.getAnimationEngine());
    }

    @Override
    public UIWindow create(GraphicsRect frame) {
        return new DefaultUIWindow(frame, this, getMainScreen(), mSharedApplication, mDefaultNotificationCenter, getUIGraphics(), getUIColorFactory(), getMethodResolver(), mUIApplicationWindows, mDeviceHandle.getAnimationEngine());
    }
}
