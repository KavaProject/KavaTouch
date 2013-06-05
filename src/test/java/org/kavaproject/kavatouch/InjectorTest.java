/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kavaproject.kavatouch.Injector;
import org.kavaproject.kavatouch.util.DeviceHandleMock;

public class InjectorTest {
    private Injector mInjector;

    @Before
    public void setUp() throws Exception {
        Injector.setInstance(new Injector(new DeviceHandleMock()));
        mInjector = Injector.getInstance();
    }

    @Test
    public void testInternalModule() throws Exception {
        Assert.assertNotNull(mInjector.injectSystemEventSource());
        Assert.assertNotNull(mInjector.injectMotionEventHandler());
//        Assert.assertNotNull(mInjector.injectScreenRedrawObserver());
    }

    @Test
    public void testRuntimeModule() throws Exception {
        Assert.assertNotNull(mInjector.injectMethodPerformer());
        Assert.assertNotNull(mInjector.injectFactoryService());
        Assert.assertNotNull(mInjector.injectMethodResolver());
        Assert.assertNotNull(mInjector.injectFactoryRegistry());
        Assert.assertNotNull(mInjector.injectMethodDispatcher());
        Assert.assertNotNull(mInjector.injectMethodResolver());
        Assert.assertNotNull(mInjector.injectMethodBackgroundPerformer());
    }

    @Test
    public void testFoundationModule() throws Exception {
        Assert.assertNotNull(mInjector.injectAttributedStringFactory());
        Assert.assertNotNull(mInjector.injectBlockOperationFactory());
        Assert.assertNotNull(mInjector.injectBundleFactory());
        Assert.assertNotNull(mInjector.injectFoundationDateFactory());
        Assert.assertNotNull(mInjector.injectFoundationErrorFactory());
        Assert.assertNotNull(mInjector.injectFoundationExceptionFactory());
        Assert.assertNotNull(mInjector.injectFoundationStringFactory());
        Assert.assertNotNull(mInjector.injectFoundationThreadFactory());
        Assert.assertNotNull(mInjector.injectInvocationFactory());
        Assert.assertNotNull(mInjector.injectInvocationOperationFactory());
        Assert.assertNotNull(mInjector.injectKeyedArchiverFactory());
        Assert.assertNotNull(mInjector.injectKeyedUnarchiverFactory());
        Assert.assertNotNull(mInjector.injectMachPortFactory());
        Assert.assertNotNull(mInjector.injectPortMessageFactory());
        Assert.assertNotNull(mInjector.injectSharedMessagePortNameServer());
        Assert.assertNotNull(mInjector.injectMethodSignatureFactory());
        Assert.assertNotNull(mInjector.injectNotificationCenterFactory());
        Assert.assertNotNull(mInjector.injectNotificationFactory());
        Assert.assertNotNull(mInjector.injectNotificationQueueFactory());
        Assert.assertNotNull(mInjector.injectOperationQueueFactory());
        Assert.assertNotNull(mInjector.injectPortMessageFactory());
        Assert.assertNotNull(mInjector.injectSystemDefaultPortNameServer());
        Assert.assertNotNull(mInjector.injectProcessInfo());
        Assert.assertNotNull(mInjector.injectPropertyListSerializationFactory());
        Assert.assertNotNull(mInjector.injectRunLoopFactory());
        Assert.assertNotNull(mInjector.injectTimerFactory());
        Assert.assertNotNull(mInjector.injectURLFactory());
    }

    @Test
    public void testCoreAnimationModule() throws Exception {
        Assert.assertNotNull(mInjector.injectAnimationLayerFactory());
    }

    @Test
    public void testUIKitModule() throws Exception {
        Assert.assertNotNull(mInjector.injectSharedApplication());
//        Assert.assertNotNull(mInjector.injectSession());
        Assert.assertNotNull(mInjector.injectUIBezierPathFactory());
        Assert.assertNotNull(mInjector.injectUIColorFactory());
        Assert.assertNotNull(mInjector.injectCurrentUIDevice());
        Assert.assertNotNull(mInjector.injectUIEventFactory());
        Assert.assertNotNull(mInjector.injectUIFontFactory());
        Assert.assertNotNull(mInjector.injectUIGraphics());
        Assert.assertNotNull(mInjector.injectUIImageFactory());
        Assert.assertNotNull(mInjector.injectUIImageGraphics());
        Assert.assertNotNull(mInjector.injectUIImageViewFactory());
        Assert.assertNotNull(mInjector.injectUILabelFactory());
        Assert.assertNotNull(mInjector.injectUINibFactory());
        Assert.assertNotNull(mInjector.injectUIResponderFactory());
        Assert.assertNotNull(mInjector.injectUIScreenFactory());
        Assert.assertNotNull(mInjector.injectUIScreenModeFactory());
        Assert.assertNotNull(mInjector.injectUITouchFactory());
        Assert.assertNotNull(mInjector.injectUIViewControllerFactory());
        Assert.assertNotNull(mInjector.injectUIViewFactory());
//        Assert.assertNotNull(mInjector.injectUIWindowFactory());
        Assert.assertNotNull(mInjector.injectUIApplicationWindows());
    }
}
