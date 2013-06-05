/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch;

import org.kavaproject.kavatouch.coreanimation.AnimationLayerFactory;
import org.kavaproject.kavatouch.coreanimation.CoreAnimationModule;
import org.kavaproject.kavatouch.foundation.*;
import org.kavaproject.kavatouch.internal.*;
import org.kavaproject.kavatouch.runtime.*;
import org.kavaproject.kavatouch.uikit.*;

/**
 * Important: Call CoreApplication.init() before using Injector. CoreFoundation classes aren't injected yet and have to
 * be set up manually.
 */
public class Injector {
    private static Injector sInstance;
    private final RuntimeModule mRuntimeModule;
    private final CoreAnimationModule mCoreAnimationModule;
    private final FoundationModule mFoundationModule;
    private final UIKitModule mUIKitModule;
    private final InternalModule mInternalModule;
    private final DeviceHandle mDeviceHandle;
    private MethodPerformer mMethodPerformer;
    private FactoryRegistry mFactoryRegistry;
    private MethodResolver mMethodResolver;
    private FactoryService mFactoryService;
    private MethodDispatcher mMethodDispatcher;
    private MethodSignatureService mMethodSignatureService;
    private AnimationLayerFactory mAnimationLayerFactory;
    private AttributedStringFactory mAttributedStringFactory;
    private BlockOperationFactory mBlockOperationFactory;
    private FoundationErrorFactory mFoundationErrorFactory;
    private InvocationOperationFactory mInvocationOperationFactory;
    private KeyedArchiverFactory mKeyedArchiverFactory;
    private KeyedUnarchiverFactory mKeyedUnarchiverFactory;
    private MessagePortNameServer mSharedMessagePortNameServer;
    private NotificationFactory mNotificationFactory;
    private NotificationQueueFactory mNotificationQueueFactory;
    private OperationQueueFactory mOperationQueueFactory;
    private PortNameServer mSystemDefaultPortNameServer;
    private ProcessInfo mProcessInfo;
    private PropertyListSerializationFactory mPropertyListSerializationFactory;
    private URLFactory mURLFactory;
    private MethodSignatureFactory mMethodSignatureFactory;
    private InvocationFactory mInvocationFactory;
    private TimerFactory mTimerFactory;
    private FoundationDateFactory mFoundationDateFactory;
    private RunLoopFactory mRunLoopFactory;
    private FoundationThreadFactory mFoundationThreadFactory;
    private FoundationExceptionFactory mFoundationExceptionFactory;
    private FoundationStringFactory mFoundationStringFactory;
    private BundleFactory mBundleFactory;
    private NotificationCenterFactory mNotificationCenterFactory;
    private MachPortFactory mMachPortFactory;
    private PortMessageFactory mPortMessageFactory;
    private UIBezierPathFactory mUIBezierPathFactory;
    private UIDevice mCurrentUIDevice;
    private UINibFactory mUINibFactory;
    private UIResponderFactory mUIResponderFactory;
    private UIScreenModeFactory mUIScreenModeFactory;
    private UIApplicationWindows mUIApplicationWindows;
    private Session mSession;
    private UIApplication mSharedApplication;
    private MotionEventHandler mMotionEventHandler;
    private UITouchFactory mUITouchFactory;
    private UIEventFactory mUIEventFactory;
    private UIViewControllerFactory mUIViewControllerFactory;
    private UIViewFactory mUIViewFactory;
    private UIImageViewFactory mUIImageViewFactory;
    private UIImageFactory mUIImageFactory;
    private UILabelFactory mUILabelFactory;
    private UIColorFactory mUIColorFactory;
    private UIFontFactory mUIFontFactory;
    private UIWindowFactory mUIWindowFactory;
    private UIScreenFactory mUIScreenFactory;
    private UIGraphics mUIGraphics;
    private UIImageGraphics mUIImageGraphics;
    private SystemEventSource mSystemEventSource;
    private ScreenRedrawObserver mScreenRedrawObserver;
    private MethodBackgroundPerformer mMethodBackgroundPerformer;
    private UIScreen mMainScreen;
    private NotificationCenter mDefaultNotificationCenter;
    private UIDeviceFactory mUIDeviceFactory;

    public Injector(DeviceHandle deviceHandle) {
        mDeviceHandle = deviceHandle;
        mRuntimeModule = new RuntimeModule();
        mCoreAnimationModule = new CoreAnimationModule();
        mFoundationModule = new FoundationModule();
        mUIKitModule = new UIKitModule();
        mInternalModule = new InternalModule();
    }

    public static Injector getInstance() {
        return sInstance;
    }

    public static void setInstance(Injector instance) {
        sInstance = instance;
    }

    public MethodPerformer injectMethodPerformer() {
        if (mMethodPerformer == null) {
            mMethodPerformer = mRuntimeModule.provideMethodPerformer(injectInvocationFactory(), injectTimerFactory(),
                    injectFoundationDateFactory(), injectRunLoopFactory(), injectMethodDispatcher(),
                    injectMethodSignatureService());
        }
        return mMethodPerformer;
    }

    public MethodDispatcher injectMethodDispatcher() {
        if (mMethodDispatcher == null) {
            mMethodDispatcher = mRuntimeModule.provideMethodDispatcher(injectMethodResolver(),
                    injectFactoryRegistry(), injectFoundationExceptionFactory(), injectMethodSignatureFactory());
        }
        return mMethodDispatcher;
    }

    public MethodSignatureService injectMethodSignatureService() {
        if (mMethodSignatureService == null) {
            mMethodSignatureService = mRuntimeModule.provideMethodSignatureService(injectMethodResolver(),
                    injectMethodSignatureFactory());
        }
        return mMethodSignatureService;
    }

    public FactoryService injectFactoryService() {
        if (mFactoryService == null) {
            mFactoryService = mRuntimeModule.provideFactoryService(injectFactoryRegistry());
        }
        return mFactoryService;
    }

    public FactoryRegistry injectFactoryRegistry() {
        if (mFactoryRegistry == null) {
            mFactoryRegistry = mRuntimeModule.provideFactoryRegistry();
        }
        return mFactoryRegistry;
    }

    public MethodSignatureFactory injectMethodSignatureFactory() {
        if (mMethodSignatureFactory == null) {
            mMethodSignatureFactory = mFoundationModule.provideMethodSignatureFactory();
        }
        return mMethodSignatureFactory;
    }

    public InvocationFactory injectInvocationFactory() {
        if (mInvocationFactory == null) {
            mInvocationFactory = mFoundationModule.provideInvocationFactory(injectMethodDispatcher());
        }
        return mInvocationFactory;
    }

    public TimerFactory injectTimerFactory() {
        if (mTimerFactory == null) {
            mTimerFactory = mFoundationModule.provideTimerFactory(injectFoundationDateFactory(),
                    injectRunLoopFactory(), injectInvocationFactory(), injectMethodSignatureService());
        }
        return mTimerFactory;
    }

    public FoundationDateFactory injectFoundationDateFactory() {
        if (mFoundationDateFactory == null) {
            mFoundationDateFactory = mFoundationModule.provideFoundationDateFactory();
        }
        return mFoundationDateFactory;
    }

    public RunLoopFactory injectRunLoopFactory() {
        if (mRunLoopFactory == null) {
            mRunLoopFactory = mFoundationModule.provideRunLoopFactory(injectFoundationDateFactory());
        }
        return mRunLoopFactory;
    }

    public FoundationExceptionFactory injectFoundationExceptionFactory() {
        if (mFoundationExceptionFactory == null) {
            mFoundationExceptionFactory = mFoundationModule.provideFoundationExceptionFactory();
        }
        return mFoundationExceptionFactory;
    }

    public UIViewFactory injectUIViewFactory() {
        if (mUIViewFactory == null) {
            mUIViewFactory = mUIKitModule.provideUIViewFactory(injectAnimationLayerFactory(), injectUIGraphics(),
                    injectUIColorFactory(), injectMainScreen(), injectMethodResolver());
        }
        return mUIViewFactory;
    }

    public UIImageViewFactory injectUIImageViewFactory() {
        if (mUIImageViewFactory == null) {
            mUIImageViewFactory = mUIKitModule.provideUIImageViewFactory(injectAnimationLayerFactory(),
                    injectUIGraphics(), injectUIImageGraphics(), injectUIColorFactory(), injectMainScreen(),
                    injectMethodResolver());
        }
        return mUIImageViewFactory;
    }

    public UIImageFactory injectUIImageFactory() {
        if (mUIImageFactory == null) {
            mUIImageFactory = mUIKitModule.provideUIImageFactory(injectBundleFactory(), injectUIGraphics(),
                    injectFoundationStringFactory(), injectDeviceHandle());
        }
        return mUIImageFactory;
    }

    public UILabelFactory injectUILabelFactory() {
        if (mUILabelFactory == null) {
            mUILabelFactory = mUIKitModule.provideUILabelFactory(injectUIFontFactory(),
                    injectAnimationLayerFactory(), injectUIGraphics(), injectUIColorFactory(), injectMainScreen(),
                    injectMethodResolver(), injectDeviceHandle());
        }
        return mUILabelFactory;
    }

    public UIColorFactory injectUIColorFactory() {
        if (mUIColorFactory == null) {
            mUIColorFactory = mUIKitModule.provideUIColorFactory(injectUIImageFactory(), injectUIGraphics(),
                    injectDeviceHandle());
        }
        return mUIColorFactory;
    }

    private DeviceHandle injectDeviceHandle() {
        return mDeviceHandle;
    }

    public UIFontFactory injectUIFontFactory() {
        if (mUIFontFactory == null) {
            mUIFontFactory = mUIKitModule.provideUIFontFactory();
        }
        return mUIFontFactory;
    }

    public UIWindowFactory injectUIWindowFactory() {
        if (mUIWindowFactory == null) {
            mUIWindowFactory = mUIKitModule.provideUIWindowFactory(injectSharedApplication(),
                    injectDefaultNotificationCenter(), injectAnimationLayerFactory(), injectUIGraphics(),
                    injectUIColorFactory(), injectMainScreen(), injectMethodResolver(), injectUIApplicationWindows(),
                    injectDeviceHandle());
        }
        return mUIWindowFactory;
    }

    public UIScreen injectMainScreen() {
        if (mMainScreen == null) {
            mMainScreen = mUIKitModule.provideMainScreen(injectUIScreenFactory());
        }
        return mMainScreen;
    }

    public UIScreenFactory injectUIScreenFactory() {
        if (mUIScreenFactory == null) {
            mUIScreenFactory = mUIKitModule.provideUIScreenFactory(injectSharedApplication(),
                    injectDefaultNotificationCenter(), injectUIScreenModeFactory(), injectDeviceHandle());
        }
        return mUIScreenFactory;
    }

    public NotificationCenter injectDefaultNotificationCenter() {
        if (mDefaultNotificationCenter == null) {
            mDefaultNotificationCenter = mFoundationModule.provideDefaultNotificationCenter
                    (injectNotificationCenterFactory());
        }
        return mDefaultNotificationCenter;
    }

    public FoundationThreadFactory injectFoundationThreadFactory() {
        if (mFoundationThreadFactory == null) {
            mFoundationThreadFactory = mFoundationModule.provideFoundationThreadFactory(injectMethodPerformer());
        }
        return mFoundationThreadFactory;
    }

    public FoundationStringFactory injectFoundationStringFactory() {
        if (mFoundationStringFactory == null) {
            mFoundationStringFactory = mFoundationModule.provideFoundationStringFactory();
        }
        return mFoundationStringFactory;
    }

    public AnimationLayerFactory injectAnimationLayerFactory() {
        if (mAnimationLayerFactory == null) {
            mAnimationLayerFactory = mCoreAnimationModule.provideAnimationLayerFactory(injectUIColorFactory());
        }
        return mAnimationLayerFactory;
    }

    public UIImageGraphics injectUIImageGraphics() {
        if (mUIImageGraphics == null) {
            mUIImageGraphics = mUIKitModule.provideUIImageGraphics(injectUIGraphics(), injectMainScreen(),
                    injectUIImageFactory());
        }
        return mUIImageGraphics;
    }

    public BundleFactory injectBundleFactory() {
        if (mBundleFactory == null) {
            mBundleFactory = mFoundationModule.provideBundleFactory(injectURLFactory());
        }
        return mBundleFactory;
    }

    public NotificationCenterFactory injectNotificationCenterFactory() {
        if (mNotificationCenterFactory == null) {
            mNotificationCenterFactory = mFoundationModule.provideNotificationCenterFactory();
        }
        return mNotificationCenterFactory;
    }

    public UIScreenModeFactory injectUIScreenModeFactory() {
        if (mUIScreenModeFactory == null) {
            mUIScreenModeFactory = mUIKitModule.provideUIScreenModeFactory();
        }
        return mUIScreenModeFactory;
    }

    public SystemEventSource injectSystemEventSource() {
        if (mSystemEventSource == null) {
            mSystemEventSource = mInternalModule.provideSystemEventSource(injectMotionEventHandler(),
                    injectDeviceHandle());
        }
        return mSystemEventSource;
    }

    public ScreenRedrawObserver injectScreenRedrawObserver() {
        if (mScreenRedrawObserver == null) {
            mScreenRedrawObserver = mInternalModule.provideScreenRedrawObserver(injectSharedApplication(),
                    injectDeviceHandle());
        }
        return mScreenRedrawObserver;
    }

    public URLFactory injectURLFactory() {
        if (mURLFactory == null) {
            mURLFactory = mFoundationModule.provideURLFactory();
        }
        return mURLFactory;
    }

    public UIApplicationWindows injectUIApplicationWindows() {
        if (mUIApplicationWindows == null) {
            mUIApplicationWindows = mUIKitModule.provideUIApplicationWindows();
        }
        return mUIApplicationWindows;
    }

    public Session injectSession() {
        if (mSession == null) {
            mSession = mUIKitModule.provideSession(injectDefaultNotificationCenter(), injectSystemEventSource(),
                    injectScreenRedrawObserver(), injectSharedApplication(), injectDeviceHandle());
        }
        return mSession;
    }

    @OccClassMethod("sharedApplication")
    public UIApplication injectSharedApplication() {
        if (mSharedApplication == null) {
            mSharedApplication = mUIKitModule.provideSharedApplication(injectCurrentUIDevice(),
                    injectMethodResolver(), injectMethodPerformer(), injectUIApplicationWindows());
        }
        return mSharedApplication;
    }

    public MotionEventHandler injectMotionEventHandler() {
        if (mMotionEventHandler == null) {
            mMotionEventHandler = mInternalModule.provideMotionEventHandler(injectUITouchFactory(),
                    injectUIEventFactory(), injectSharedApplication(), injectMainScreen(), injectDeviceHandle());
        }
        return mMotionEventHandler;
    }

    public UITouchFactory injectUITouchFactory() {
        if (mUITouchFactory == null) {
            mUITouchFactory = mUIKitModule.provideUITouchFactory();
        }
        return mUITouchFactory;
    }

    public UIEventFactory injectUIEventFactory() {
        if (mUIEventFactory == null) {
            mUIEventFactory = mUIKitModule.provideUIEventFactory();
        }
        return mUIEventFactory;
    }

    public UIViewControllerFactory injectUIViewControllerFactory() {
        if (mUIViewControllerFactory == null) {
            mUIViewControllerFactory = mUIKitModule.provideUIViewControllerFactory(injectUIViewFactory(),
                    injectMethodResolver());
        }
        return mUIViewControllerFactory;
    }

    public MachPortFactory injectMachPortFactory() {
        if (mMachPortFactory == null) {
            mMachPortFactory = mFoundationModule.provideMachPortFactory(injectPortMessageFactory());
        }
        return mMachPortFactory;
    }

    public PortMessageFactory injectPortMessageFactory() {
        if (mPortMessageFactory == null) {
            mPortMessageFactory = mFoundationModule.providePortMessageFactory();
        }
        return mPortMessageFactory;
    }

    public AttributedStringFactory injectAttributedStringFactory() {
        if (mAttributedStringFactory == null) {
            mAttributedStringFactory = mFoundationModule.provideAttributedStringFactory();
        }
        return mAttributedStringFactory;
    }

    public BlockOperationFactory injectBlockOperationFactory() {
        if (mBlockOperationFactory == null) {
            mBlockOperationFactory = mFoundationModule.provideBlockOperationFactory();
        }
        return mBlockOperationFactory;
    }

    public FoundationErrorFactory injectFoundationErrorFactory() {
        if (mFoundationErrorFactory == null) {
            mFoundationErrorFactory = mFoundationModule.provideFoundationErrorFactory();
        }
        return mFoundationErrorFactory;
    }

    public InvocationOperationFactory injectInvocationOperationFactory() {
        if (mInvocationOperationFactory == null) {
            mInvocationOperationFactory = mFoundationModule.provideInvocationOperationFactory();
        }
        return mInvocationOperationFactory;
    }

    public KeyedArchiverFactory injectKeyedArchiverFactory() {
        if (mKeyedArchiverFactory == null) {
            mKeyedArchiverFactory = mFoundationModule.provideKeyedArchiverFactory();
        }
        return mKeyedArchiverFactory;
    }

    public KeyedUnarchiverFactory injectKeyedUnarchiverFactory() {
        if (mKeyedUnarchiverFactory == null) {
            mKeyedUnarchiverFactory = mFoundationModule.provideKeyedUnarchiverFactory();
        }
        return mKeyedUnarchiverFactory;
    }

    public MessagePortNameServer injectSharedMessagePortNameServer() {
        if (mSharedMessagePortNameServer == null) {
            mSharedMessagePortNameServer = mFoundationModule.provideSharedMessagePortNameServer();
        }
        return mSharedMessagePortNameServer;
    }

    public NotificationFactory injectNotificationFactory() {
        if (mNotificationFactory == null) {
            mNotificationFactory = mFoundationModule.provideNotificationFactory();
        }
        return mNotificationFactory;
    }

    public NotificationQueueFactory injectNotificationQueueFactory() {
        if (mNotificationQueueFactory == null) {
            mNotificationQueueFactory = mFoundationModule.provideNotificationQueueFactory();
        }
        return mNotificationQueueFactory;
    }

    public OperationQueueFactory injectOperationQueueFactory() {
        if (mOperationQueueFactory == null) {
            mOperationQueueFactory = mFoundationModule.provideOperationQueueFactory();
        }
        return mOperationQueueFactory;
    }

    public PortNameServer injectSystemDefaultPortNameServer() {
        if (mSystemDefaultPortNameServer == null) {
            mSystemDefaultPortNameServer = mFoundationModule.provideSystemDefaultPortNameServer();
        }
        return mSystemDefaultPortNameServer;
    }

    public ProcessInfo injectProcessInfo() {
        if (mProcessInfo == null) {
            mProcessInfo = mFoundationModule.provideProcessInfo();
        }
        return mProcessInfo;
    }

    public PropertyListSerializationFactory injectPropertyListSerializationFactory() {
        if (mPropertyListSerializationFactory == null) {
            mPropertyListSerializationFactory = mFoundationModule.providePropertyListSerializationFactory();
        }
        return mPropertyListSerializationFactory;
    }

    public UIBezierPathFactory injectUIBezierPathFactory() {
        if (mUIBezierPathFactory == null) {
            mUIBezierPathFactory = mUIKitModule.provideUIBezierPathFactory(injectUIGraphics());
        }
        return mUIBezierPathFactory;
    }

    public UIGraphics injectUIGraphics() {
        if (mUIGraphics == null) {
            mUIGraphics = mUIKitModule.provideUIGraphics();
        }
        return mUIGraphics;
    }

    public UIDevice injectCurrentUIDevice() {
        if (mCurrentUIDevice == null) {
            mCurrentUIDevice = mUIKitModule.provideCurrentUIDevice(injectUIDeviceFactory());
        }
        return mCurrentUIDevice;
    }

    private UIDeviceFactory injectUIDeviceFactory() {
        if (mUIDeviceFactory == null) {
            mUIDeviceFactory = mUIKitModule.provideUIDeviceFactory();
        }
        return mUIDeviceFactory;
    }

    public UINibFactory injectUINibFactory() {
        if (mUINibFactory == null) {
            mUINibFactory = mUIKitModule.provideUINibFactory();
        }
        return mUINibFactory;
    }

    public UIResponderFactory injectUIResponderFactory() {
        if (mUIResponderFactory == null) {
            mUIResponderFactory = mUIKitModule.provideUIResponderFactory(injectMethodResolver());
        }
        return mUIResponderFactory;
    }

    public MethodResolver injectMethodResolver() {
        if (mMethodResolver == null) {
            mMethodResolver = mRuntimeModule.provideMethodResolver(injectFactoryRegistry());
        }
        return mMethodResolver;
    }

    public MethodBackgroundPerformer injectMethodBackgroundPerformer() {
        if (mMethodBackgroundPerformer == null) {
            mMethodBackgroundPerformer = mRuntimeModule.provideMethodBackgroundPerformer(injectFoundationThreadFactory());
        }
        return mMethodBackgroundPerformer;
    }
}
