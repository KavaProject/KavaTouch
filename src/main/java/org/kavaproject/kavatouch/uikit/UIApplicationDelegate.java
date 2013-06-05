/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.uikit;

import org.kavaproject.kavatouch.corefoundation.CorePropertyList;
import org.kavaproject.kavatouch.coregraphics.GraphicsRect;
import org.kavaproject.kavatouch.foundation.Coder;
import org.kavaproject.kavatouch.foundation.URL;
import org.kavaproject.kavatouch.internal.Header;
import org.kavaproject.kavatouch.internal.OccProtocol;
import org.kavaproject.kavatouch.internal.OccProtocolInstanceMethod;
import org.kavaproject.kavatouch.internal.OccProtocolInstanceProperty;
import org.kavaproject.kavatouch.runtime.SEL;
import org.kavaproject.kavatouch.uikit.staging.UILocalNotification;

import java.nio.ByteBuffer;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;

@Header("UIApplication")
@OccProtocol("UIApplicationDelegate")
public interface UIApplicationDelegate {
    SEL SEL_APPLICATION_DID_FINISH_LAUNCHING_WITH_OPTIONS = SEL.getInstance
            ("application:didFinishLaunchingWithOptions:");
    SEL SEL_GET_WINDOW = SEL.getInstance("getWindow");
    SEL SEL_SET_WINDOW = SEL.getInstance("setWindow:");

    @OccProtocolInstanceMethod("application:willFinishLaunchingWithOptions:")
    boolean onWillFinishLaunching(UIApplication application, UIApplicationLaunchOptions launchOptions);

    @OccProtocolInstanceMethod("application:didFinishLaunchingWithOptions:")
    boolean onDidFinishLaunching(UIApplication application, UIApplicationLaunchOptions launchOptions);

    @OccProtocolInstanceMethod("applicationDidBecomeActive:")
    void onDidBecomeActive(UIApplication application);

    @OccProtocolInstanceMethod("applicationWillResignActive:")
    void onWillResignActive(UIApplication application);

    @OccProtocolInstanceMethod("applicationDidEnterBackground:")
    void onDidEnterBackground(UIApplication application);

    @OccProtocolInstanceMethod("applicationWillEnterForeground:")
    void onWillEnterForeground(UIApplication application);

    @OccProtocolInstanceMethod("applicationWillTerminate:")
    void onWillTerminate(UIApplication application);

    public interface Storyboarding {
        @OccProtocolInstanceProperty("window")
        UIWindow getWindow();

        @OccProtocolInstanceProperty("window")
        void setWindow(UIWindow value);
    }

    public interface DeprecatedMethods {
        @OccProtocolInstanceMethod("applicationDidFinishLaunching:")
        void onDidFinishLaunching(UIApplication application);

        @OccProtocolInstanceMethod("application:handleOpenURL:")
        @Deprecated
        boolean handleOpenURL(UIApplication application, URL url);
    }

    public interface LocalNotificationHandling {
        @OccProtocolInstanceMethod("application:didReceiveLocalNotification:")
        void onDidReceiveLocalNotification(UIApplication application, UILocalNotification notification);
    }

    public interface StateRestorationManagement {
        @OccProtocolInstanceMethod("application:shouldSaveApplicationState:")
        boolean onShouldSaveApplicationState(UIApplication application, Coder coder);

        @OccProtocolInstanceMethod("application:shouldRestoreApplicationState:")
        boolean onShouldRestoreApplicationState(UIApplication application, Coder coder);

        @OccProtocolInstanceMethod("application:viewControllerWithRestorationIdentifierPath:coder:")
        UIViewController getViewController(UIApplication application, List<String> identifierComponents, Coder coder);

        @OccProtocolInstanceMethod("application:willEncodeRestorableStateWithCoder:")
        void onWillEncodeRestorableState(UIApplication application, Coder coder);

        @OccProtocolInstanceMethod("application:didDecodeRestorableStateWithCoder:")
        void onDidDecodeRestorableState(UIApplication application, Coder coder);
    }

    public interface DefaultInterfaceOrientationManagement {
        @OccProtocolInstanceMethod("application:supportedInterfaceOrientationsForWindow:")
        EnumSet<UIInterfaceOrientation> getSupportedInterfaceOrientations(UIApplication application, UIWindow window);
    }

    public interface StatusBarChangeManagement {
        @OccProtocolInstanceMethod("application:willChangeStatusBarOrientation:duration:")
        void onWillChangeStatusBarOrientation(UIApplication application,
                                              UIInterfaceOrientation newStatusBarOrientation, double duration);

        @OccProtocolInstanceMethod("application:didChangeStatusBarOrientation:")
        void onDidChangeStatusBarOrientation(UIApplication application, UIInterfaceOrientation oldStatusBarOrientation);

        @OccProtocolInstanceMethod("application:willChangeStatusBarFrame:")
        void onWillChangeStatusBarFrame(UIApplication application, GraphicsRect newStatusBarFrame);

        @OccProtocolInstanceMethod("application:didChangeStatusBarFrame:")
        void onDidChangeStatusBarFrame(UIApplication application, GraphicsRect oldStatusBarFrame);
    }

    public interface SystemNotificationResponding {
        @OccProtocolInstanceMethod("applicationDidReceiveMemoryWarning:")
        void onDidReceiveMemoryWarning(UIApplication application);

        @OccProtocolInstanceMethod("applicationSignificantTimeChange:")
        void onSignificantTimeChange(UIApplication application);
    }

    public interface RemoteNotificationHandling {
        @OccProtocolInstanceMethod("application:didReceiveRemoteNotification:")
        void onDidReceiveRemoteNotification(UIApplication application, Map<String, Object> userInfo);

        @OccProtocolInstanceMethod("application:didRegisterForRemoteNotificationsWithDeviceToken:")
        void onDidRegisterForRemoteNotifications(UIApplication application, ByteBuffer deviceToken);

        @OccProtocolInstanceMethod("application:didFailToRegisterForRemoteNotificationsWithError:")
        void onDidFailToRegisterForRemoteNotifications(UIApplication application) throws RuntimeException;
    }

    public interface ContentProtectionChangeResponding {
        @OccProtocolInstanceMethod("applicationProtectedDataWillBecomeUnavailable:")
        void onProtectedDataWillBecomeUnavailable(UIApplication application);

        @OccProtocolInstanceMethod("applicationProtectedDataDidBecomeAvailable:")
        void onProtectedDataDidBecomeAvailable(UIApplication application);
    }

    public interface URLResourceOpening {
        @OccProtocolInstanceMethod("application:openURL:sourceApplication:annotation:")
        boolean openURL(UIApplication application, URL url, String sourceApplication, CorePropertyList annotation);
    }
}
