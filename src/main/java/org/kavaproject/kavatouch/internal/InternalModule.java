/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.internal;

import com.google.inject.Provides;
import org.kavaproject.kavatouch.DeviceHandle;
import org.kavaproject.kavatouch.uikit.UIApplication;
import org.kavaproject.kavatouch.uikit.UIEventFactory;
import org.kavaproject.kavatouch.uikit.UIScreen;
import org.kavaproject.kavatouch.uikit.UITouchFactory;

import javax.inject.Singleton;

@Module
public final class InternalModule {
    @Provides
    @Singleton
    public SystemEventSource provideSystemEventSource(MotionEventHandler motionEventHandler,
                                                      DeviceHandle deviceHandle) {
        return new DefaultSystemEventSource(motionEventHandler, deviceHandle);
    }

    @Provides
    @Singleton
    public ScreenRedrawObserver provideScreenRedrawObserver(UIApplication sharedUIApplication,
                                                            DeviceHandle deviceHandle) {
        return new DefaultScreenRedrawObserver(sharedUIApplication, deviceHandle);
    }

    @Provides
    @Singleton
    public MotionEventHandler provideMotionEventHandler(UITouchFactory uiTouchFactory, UIEventFactory uiEventFactory,
                                                        UIApplication sharedApplication, UIScreen mainScreen, DeviceHandle deviceHandle) {
        return new DefaultMotionEventHandler(uiTouchFactory, uiEventFactory, sharedApplication, mainScreen, deviceHandle);
    }
}
