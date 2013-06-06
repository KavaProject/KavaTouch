/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.uikit;

import org.kavaproject.kavatouch.internal.Module;
import org.kavaproject.kavatouch.util.inject.Provides;
import org.kavaproject.kavatouch.DeviceHandle;
import org.kavaproject.kavatouch.coreanimation.AnimationLayerFactory;
import org.kavaproject.kavatouch.foundation.BundleFactory;
import org.kavaproject.kavatouch.foundation.FoundationStringFactory;
import org.kavaproject.kavatouch.foundation.NotificationCenter;
import org.kavaproject.kavatouch.internal.ScreenRedrawObserver;
import org.kavaproject.kavatouch.internal.SystemEventSource;
import org.kavaproject.kavatouch.runtime.MethodPerformer;
import org.kavaproject.kavatouch.runtime.MethodResolver;

import org.kavaproject.kavatouch.util.inject.Singleton;

@Module
public class UIKitModule {
    @Provides
    @Singleton
    public UIApplication provideSharedApplication(UIDevice currentUIDevice, MethodResolver methodResolver,
                                                  MethodPerformer methodPerformer,
                                                  UIApplicationWindows uiApplicationWindows) {
        return new DefaultUIApplicationFactory(currentUIDevice, methodResolver, methodPerformer,
                uiApplicationWindows).create();
    }

    @Provides
    @Singleton
    public Session provideSession(NotificationCenter defaultNotificationCenter, SystemEventSource systemEventSource,
                                  ScreenRedrawObserver screenRedrawObserver, UIApplication sharedUIApplication,
                                  DeviceHandle deviceHandle) {
        return new DefaultSession(defaultNotificationCenter, systemEventSource, screenRedrawObserver,
                sharedUIApplication, deviceHandle);
    }

    @Provides
    @Singleton
    public UIBezierPathFactory provideUIBezierPathFactory(UIGraphics uiGraphics) {
        return new DefaultUIBezierPathFactory(uiGraphics);
    }

    @Provides
    @Singleton
    public UIColorFactory provideUIColorFactory(UIImageFactory uiImageFactory, UIGraphics uiGraphics,
                                                DeviceHandle deviceHandle) {
        return new DefaultUIColorFactory(uiImageFactory, uiGraphics, deviceHandle);
    }

    @Provides
    @Singleton
    public UIDevice provideCurrentUIDevice(UIDeviceFactory uiDeviceFactory) {
        return uiDeviceFactory.create();
    }

    @Provides
    @Singleton
    public UIEventFactory provideUIEventFactory() {
        return new DefaultUIEventFactory();
    }

    @Provides
    @Singleton
    public UIFontFactory provideUIFontFactory() {
        return new DefaultUIFontFactory();
    }

    @Provides
    @Singleton
    public UIGraphics provideUIGraphics() {
        return new DefaultUIGraphics();
    }

    @Provides
    @Singleton
    public UIImageFactory provideUIImageFactory(BundleFactory bundleFactory, UIGraphics uiGraphics,
                                                FoundationStringFactory foundationStringFactory,
                                                DeviceHandle deviceHandle) {
        return new DefaultUIImageFactory(bundleFactory, uiGraphics, foundationStringFactory, deviceHandle);
    }

    @Provides
    @Singleton
    public UIImageGraphics provideUIImageGraphics(UIGraphics uiGraphics, UIScreen mainScreen,
                                                  UIImageFactory uiImageFactory) {
        return new DefaultUIImageGraphics(uiGraphics, mainScreen, uiImageFactory);
    }

    @Provides
    @Singleton
    public UIImageViewFactory provideUIImageViewFactory(AnimationLayerFactory animationLayerFactory,
                                                        UIGraphics uiGraphics, UIImageGraphics uiImageGraphics,
                                                        UIColorFactory uiColorFactory, UIScreen mainScreen,
                                                        MethodResolver methodResolver) {
        return new DefaultUIImageViewFactory(animationLayerFactory, uiGraphics, uiImageGraphics, uiColorFactory,
                mainScreen, methodResolver);
    }

    @Provides
    @Singleton
    public UILabelFactory provideUILabelFactory(UIFontFactory uiFontFactory,
                                                AnimationLayerFactory animationLayerFactory, UIGraphics uiGraphics,
                                                UIColorFactory uiColorFactory, UIScreen mainScreen,
                                                MethodResolver methodResolver, DeviceHandle deviceHandle) {
        return new AndroidUILabelFactory(uiFontFactory, animationLayerFactory, uiGraphics, uiColorFactory,
                mainScreen, methodResolver, deviceHandle);
    }

    @Provides
    @Singleton
    public UINibFactory provideUINibFactory() {
        return new DefaultUINibFactory();
    }

    @Provides
    @Singleton
    public UIResponderFactory provideUIResponderFactory(MethodResolver methodResolver) {
        return new SimpleUIResponderFactory(methodResolver);
    }

    @Provides
    @Singleton
    public UIScreenFactory provideUIScreenFactory(UIApplication sharedUIApplication,
                                                  NotificationCenter notificationCenter,
                                                  UIScreenModeFactory uiScreenModeFactory, DeviceHandle deviceHandle) {
        return new DefaultUIScreenFactory(sharedUIApplication, notificationCenter, uiScreenModeFactory, deviceHandle);
    }

    @Provides
    @Singleton
    public UIScreenModeFactory provideUIScreenModeFactory() {
        return new DefaultUIScreenModeFactory();
    }

    @Provides
    @Singleton
    public UITouchFactory provideUITouchFactory() {
        return new DefaultUITouchFactory();
    }

    @Provides
    @Singleton
    public UIViewControllerFactory provideUIViewControllerFactory(UIViewFactory uiViewFactory,
                                                                  MethodResolver methodResolver) {
        return new DefaultUIViewControllerFactory(uiViewFactory, methodResolver);
    }

    @Provides
    @Singleton
    public UIViewFactory provideUIViewFactory(AnimationLayerFactory animationLayerFactory, UIGraphics uiGraphics,
                                              UIColorFactory uiColorFactory, UIScreen mainScreen,
                                              MethodResolver methodResolver) {
        return new DefaultUIViewFactory(animationLayerFactory, uiGraphics, uiColorFactory, mainScreen, methodResolver);
    }

    @Provides
    @Singleton
    public UIWindowFactory provideUIWindowFactory(UIApplication sharedUIApplication, NotificationCenter defaultNotificationCenter, AnimationLayerFactory animationLayerFactory, UIGraphics uiGraphics, UIColorFactory uiColorFactory, UIScreen mainScreen, MethodResolver methodResolver, UIApplicationWindows uiApplicationWindows, DeviceHandle deviceHandle) {
        return new DefaultUIWindowFactory(sharedUIApplication, defaultNotificationCenter, animationLayerFactory, uiGraphics, uiColorFactory, mainScreen, methodResolver, uiApplicationWindows, deviceHandle);
    }

    @Provides
    @Singleton
    public UIApplicationWindows provideUIApplicationWindows() {
        return new DefaultUIApplicationWindows();
    }

    @Provides
    @Singleton
    public UIScreen provideMainScreen(UIScreenFactory uiScreenFactory) {
        return uiScreenFactory.create();
    }

    @Provides
    @Singleton
    public UIDeviceFactory provideUIDeviceFactory() {
        return new DefaultUIDeviceFactory();
    }
}
