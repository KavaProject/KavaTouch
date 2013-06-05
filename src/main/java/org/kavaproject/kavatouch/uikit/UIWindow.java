/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.uikit;

import org.kavaproject.kavatouch.coregraphics.GraphicsPoint;
import org.kavaproject.kavatouch.coregraphics.GraphicsRect;
import org.kavaproject.kavatouch.internal.*;
import org.kavaproject.kavatouch.runtime.Creatable;

@Header("UIWindow")
@OccClass("UIWindow")
public interface UIWindow extends UIResponder, UIView, Creatable, UIWindowInternal {
    @OccConstant("UIWindowDidBecomeKeyNotification")
    String NOTIFICATION_DID_BECOME_KEY = "UIWindowDidBecomeKeyNotification";
    @OccConstant("UIWindowDidResignKeyNotification")
    String NOTIFICATION_DID_RESIGN_KEY = "UIWindowDidResignKeyNotification";
    @OccConstant("UIWindowDidBecomeHiddenNotification")
    String NOTIFICATION_DID_BECOME_HIDDEN = "UIWindowDidBecomeHiddenNotification";
    @OccConstant("UIWindowDidBecomeVisibleNotification")
    String NOTIFICATION_DID_BECOME_VISIBLE = "UIWindowDidBecomeVisibleNotification";
    @OccConstant("UIKeyboardWillShowNotification")
    String NOTIFICATION_KEYBOARD_WILL_SHOW = "UIKeyboardWillShowNotification";
    @OccConstant("UIKeyboardDidShowNotification")
    String NOTIFICATION_KEYBOARD_DID_SHOW = "UIKeyboardDidShowNotification";
    @OccConstant("UIKeyboardWillHideNotification")
    String NOTIFICATION_KEYBOARD_WILL_HIDE = "UIKeyboardWillHideNotification";
    @OccConstant("UIKeyboardDidHideNotification")
    String NOTIFICATION_KEYBOARD_DID_HIDE = "UIKeyboardDidHideNotification";
    @OccConstant("UIKeyboardWillChangeFrameNotification")
    String NOTIFICATION_KEYBOARD_WILL_CHANGE_FRAME = "UIKeyboardWillChangeFrameNotification";
    @OccConstant("UIKeyboardDidChangeFrameNotification")
    String NOTIFICATION_KEYBOARD_DID_CHANGE_FRAME = "UIKeyboardDidChangeFrameNotification";
    @OccConstant("UIWindowLevelNormal")
    float LEVEL_NORMAL = 0;
    @OccConstant("UIWindowLevelAlert")
    float LEVEL_ALERT = 1;
    @OccConstant("UIWindowLevelStatusBar")
    float LEVEL_STATUS_BAR = 2;

    @OccInstanceProperty("windowLevel")
    float getWindowLevel();

    @OccInstanceProperty("windowLevel")
    void setWindowLevel(float value);

    @OccInstanceProperty(value = "screen", argumentSemantic = ArgumentSemantic.RETAIN)
    UIScreen getScreen();

    @OccInstanceProperty(value = "screen", argumentSemantic = ArgumentSemantic.RETAIN)
    void setScreen(UIScreen value);

    @OccInstanceProperty(value = "rootViewController", argumentSemantic = ArgumentSemantic.RETAIN)
    UIViewController getRootViewController();

    @OccInstanceProperty(value = "rootViewController", argumentSemantic = ArgumentSemantic.RETAIN)
    void setRootViewController(UIViewController value);

    @OccInstanceProperty("keyWindow")
    boolean isKeyWindow();

    @OccInstanceMethod("makeKeyAndVisible")
    void makeKeyAndVisible();

    @OccInstanceMethod("becomeKeyWindow")
    void becomeKeyWindow();

    @OccInstanceMethod("makeKeyWindow")
    void makeKeyWindow();

    @OccInstanceMethod("resignKeyWindow")
    void resignKeyWindow();

    @OccInstanceMethod("convertPoint:toWindow:")
    GraphicsPoint convertPointToWindow(GraphicsPoint point, UIWindow window);

    @OccInstanceMethod("convertPoint:fromWindow:")
    GraphicsPoint convertPointFromWindow(GraphicsPoint point, UIWindow window);

    @OccInstanceMethod("convertRect:toWindow:")
    GraphicsRect convertRectToWindow(GraphicsRect rect, UIWindow window);

    @OccInstanceMethod("convertRect:fromWindow:")
    GraphicsRect convertRectFromWindow(GraphicsRect rect, UIWindow window);

    @OccInstanceMethod("sendEvent:")
    void sendEvent(UIEvent event);

    @Override
    UIResponder getFirstResponder();

    @Override
    void setFirstResponder(UIResponder firstResponder);

    @Override
    UIWindowFactory getFactory();
}
