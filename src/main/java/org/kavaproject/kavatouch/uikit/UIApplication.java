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
import org.kavaproject.kavatouch.foundation.URL;
import org.kavaproject.kavatouch.internal.*;
import org.kavaproject.kavatouch.runtime.Creatable;
import org.kavaproject.kavatouch.runtime.SEL;
import org.kavaproject.kavatouch.uikit.staging.UIBackgroundTaskIdentifier;
import org.kavaproject.kavatouch.uikit.staging.UILocalNotification;

import java.util.EnumSet;
import java.util.List;

@Header("UIApplication")
@OccClass("UIApplication")
public interface UIApplication extends UIResponder, UIActionSheetDelegate, Creatable {
    @OccConstant("UITrackingRunLoopMode")
    String RUN_LOOP_MODE_UI_TRACKING = "UITrackingRunLoopMode";
    @OccConstant("UIBackgroundTaskInvalid")
    int BACKGROUND_TASK_INVALID = -1;
    @OccConstant("UIMinimumKeepAliveTimeout")
    double MINIMUM_KEEP_ALIVE_TIMEOUT = 10;
    @OccConstant("UIApplicationInvalidInterfaceOrientationException")
    String EXCEPTION_INVALID_INTERFACE_ORIENTATION = "UIApplicationInvalidInterfaceOrientationException";
    @OccConstant("UIApplicationDidBecomeActiveNotification")
    String NOTIFICATION_DID_BECOME_ACTIVE = "UIApplicationDidBecomeActiveNotification";
    @OccConstant("UIApplicationDidChangeStatusBarFrameNotification")
    String NOTIFICATION_DID_CHANGE_STATUS_BAR_FRAME = "UIApplicationDidChangeStatusBarFrameNotification";
    @OccConstant("UIApplicationDidChangeStatusBarOrientationNotification")
    String NOTIFICATION_DID_CHANGE_STATUS_BAR_ORIENTATION = "UIApplicationDidChangeStatusBarOrientationNotification";
    @OccConstant("UIApplicationDidEnterBackgroundNotification")
    String NOTIFICATION_DID_ENTER_BACKGROUND = "UIApplicationDidEnterBackgroundNotification";
    @OccConstant("UIApplicationDidFinishLaunchingNotification")
    String NOTIFICATION_DID_FINISH_LAUNCHING = "UIApplicationDidFinishLaunchingNotification";
    @OccConstant("UIApplicationDidReceiveMemoryWarningNotification")
    String NOTIFICATION_DID_RECEIVE_MEMORY_WARNING = "UIApplicationDidReceiveMemoryWarningNotification";
    @OccConstant("UIApplicationProtectedDataDidBecomeAvailable")
    String NOTIFICATION_PROTECTED_DATA_DID_BECOME_AVAILABLE = "UIApplicationProtectedDataDidBecomeAvailable";
    @OccConstant("UIApplicationProtectedDataWillBecomeUnavailable")
    String NOTIFICATION_PROTECTED_DATA_WILL_BECOME_UNAVAILABLE = "UIApplicationProtectedDataWillBecomeUnavailable";
    @OccConstant("UIApplicationSignificantTimeChangeNotification")
    String NOTIFICATION_SIGNIFICANT_TIME_CHANGE = "UIApplicationSignificantTimeChangeNotification";
    @OccConstant("UIApplicationWillChangeStatusBarOrientationNotification")
    String NOTIFICATION_WILL_CHANGE_STATUS_BAR_ORIENTATION = "UIApplicationWillChangeStatusBarOrientationNotification";
    @OccConstant("UIApplicationWillChangeStatusBarFrameNotification")
    String NOTIFICATION_WILL_CHANGE_STATUS_BAR_FRAME = "UIApplicationWillChangeStatusBarFrameNotification";
    @OccConstant("UIApplicationWillEnterForegroundNotification")
    String NOTIFICATION_WILL_ENTER_FOREGROUND = "UIApplicationWillEnterForegroundNotification";
    @OccConstant("UIApplicationWillResignActiveNotification")
    String NOTIFICATION_WILL_RESIGN_ACTIVE = "UIApplicationWillResignActiveNotification";
    @OccConstant("UIApplicationWillTerminateNotification")
    String NOTIFICATION_WILL_TERMINATE = "UIApplicationWillTerminateNotification";

    @OccInstanceProperty(value = "delegate", argumentSemantic = ArgumentSemantic.ASSIGN)
    UIApplicationDelegate getDelegate();

    @OccInstanceProperty(value = "delegate", argumentSemantic = ArgumentSemantic.ASSIGN)
    void setDelegate(UIApplicationDelegate value);

    @OccInstanceProperty("keyWindow")
    UIWindow getKeyWindow();

    @OccInstanceMethod("supportedInterfaceOrientationsForWindow:")
    EnumSet<UIInterfaceOrientation> supportedInterfaceOrientationsForWindow(UIWindow window);

    @OccInstanceMethod("sendEvent:")
    void sendEvent(UIEvent event);

    @OccInstanceProperty("windows")
    List<UIWindow> getWindows();

    @OccInstanceMethod(value = "sendAction:to:from:forEvent:")
    boolean sendAction(SEL action, Object target, Object sender, UIEvent event);

    @OccInstanceMethod("beginIgnoringInteractionEvents")
    void beginIgnoringInteractionEvents();

    @OccInstanceMethod("endIgnoringInteractionEvents")
    void endIgnoringInteractionEvents();

    @OccInstanceMethod("isIgnoringInteractionEvents")
    boolean isIgnoringInteractionEvents();

    @OccInstanceProperty("applicationSupportsShakeToEdit")
    boolean getApplicationSupportsShakeToEdit();

    @OccInstanceProperty("applicationSupportsShakeToEdit")
    void setApplicationSupportsShakeToEdit(boolean value);

    @OccInstanceProperty("isProximitySensingEnabled")
    @Deprecated
    boolean isProximitySensingEnabled();

    @OccInstanceProperty("isProximitySensingEnabled")
    @Deprecated
    void setProximitySensingEnabled(boolean value);

    @OccInstanceMethod(value = "openURL:")
    boolean openURL(URL url);

    @OccInstanceMethod(value = "canOpenURL:")
    boolean canOpenURL(URL url);

    @OccInstanceMethod(value = "registerForRemoteNotificationTypes:")
    void registerForRemoteNotificationTypes(EnumSet<UIRemoteNotificationType> types);

    @OccInstanceMethod("unregisterForRemoteNotifications")
    void unregisterForRemoteNotifications();

    @OccInstanceMethod("enabledRemoteNotificationTypes")
    EnumSet<UIRemoteNotificationType> enabledRemoteNotificationTypes();

    @OccInstanceProperty("idleTimerDisabled")
    boolean isIdleTimerDisabled();

    @OccInstanceProperty("idleTimerDisabled")
    void setIdleTimerDisabled(boolean value);

    @OccInstanceProperty("applicationState")
    UIApplicationState getApplicationState();

    @OccInstanceProperty("backgroundTimeRemaining")
    double getBackgroundTimeRemaining();

    @OccInstanceMethod("beginBackgroundTaskWithExpirationHandler:")
    UIBackgroundTaskIdentifier beginBackgroundTaskWithExpirationHandler(Runnable handler);

    @OccInstanceMethod("endBackgroundTask:")
    void endBackgroundTask(UIBackgroundTaskIdentifier taskIdentifier);

    @OccInstanceMethod("setKeepAliveTimeout:handler:")
    boolean setKeepAliveTimeoutHandler(double timeout, Runnable handler);

    @OccInstanceMethod("clearKeepAliveTimeout")
    void clearKeepAliveTimeout();

    @OccInstanceMethod("extendStateRestoration")
    void extendStateRestoration();

    @OccInstanceMethod("completeStateRestoration")
    void completeStateRestoration();

    @OccInstanceMethod("presentLocalNotificationNow:")
    void presentLocalNotificationNow(UILocalNotification notification);

    @OccInstanceProperty(value = "scheduledLocalNotifications", argumentSemantic = ArgumentSemantic.COPY)
    List<UILocalNotification> getScheduledLocalNotifications();

    @OccInstanceProperty(value = "scheduledLocalNotifications", argumentSemantic = ArgumentSemantic.COPY)
    void setScheduledLocalNotifications(List<UILocalNotification> value);

    @OccInstanceMethod("cancelAllLocalNotifications")
    void cancelAllLocalNotifications();

    @OccInstanceMethod("cancelLocalNotification:")
    void cancelLocalNotification(UILocalNotification notification);

    @OccInstanceMethod("scheduleLocalNotification:")
    void scheduleLocalNotification(UILocalNotification notification);

    @OccInstanceProperty("protectedDataAvailable")
    boolean getProtectedDataAvailable();

    @OccInstanceMethod("beginReceivingRemoteControlEvents")
    void beginReceivingRemoteControlEvents();

    @OccInstanceMethod("endReceivingRemoteControlEvents")
    void endReceivingRemoteControlEvents();

    @OccInstanceProperty("statusBarOrientation")
    UIInterfaceOrientation getStatusBarOrientation();

    @OccInstanceProperty("statusBarOrientation")
    void setStatusBarOrientation(UIInterfaceOrientation value);

    @OccInstanceMethod("setStatusBarOrientation:animated:")
    void setStatusBarOrientation(UIInterfaceOrientation orientation, boolean animated);

    @OccInstanceProperty("statusBarOrientationAnimationDuration")
    float getStatusBarOrientationAnimationDuration();

    @OccInstanceProperty("statusBarHidden")
    boolean isStatusBarHidden();

    @OccInstanceProperty("statusBarHidden")
    void setStatusBarHidden(boolean value);

    @OccInstanceMethod("setStatusBarHidden:withAnimation:")
    void setStatusBarHidden(boolean hidden, UIStatusBarAnimation animation);

    @OccInstanceProperty("statusBarStyle")
    UIStatusBarStyle getStatusBarStyle();

    @OccInstanceProperty("statusBarStyle")
    void setStatusBarStyle(UIStatusBarStyle value);

    @OccInstanceMethod("setStatusBarStyle:animated:")
    void setStatusBarStyleAnimated(UIStatusBarStyle style, boolean animated);

    @OccInstanceProperty("statusBarFrame")
    GraphicsRect getStatusBarFrame();

    @OccInstanceProperty("networkActivityIndicatorVisible")
    boolean isNetworkActivityIndicatorVisible();

    @OccInstanceProperty("networkActivityIndicatorVisible")
    void setNetworkActivityIndicatorVisible(boolean value);

    @OccInstanceProperty("applicationIconBadgeNumber")
    int getApplicationIconBadgeNumber();

    @OccInstanceProperty("applicationIconBadgeNumber")
    void setApplicationIconBadgeNumber(int value);

    @OccInstanceProperty("userInterfaceLayoutDirection")
    UIUserInterfaceLayoutDirection getUserInterfaceLayoutDirection();

    @OccInstanceMethod("setStatusBarHidden:animated:")
    @Deprecated
    void setStatusBarHidden(boolean hidden, boolean animated);

    @OccInstanceMethod("setNewsstandIconImage:")
    void setNewsstandIconImage(UIImage image);

    @Override
    UIApplicationFactory getFactory();
}
