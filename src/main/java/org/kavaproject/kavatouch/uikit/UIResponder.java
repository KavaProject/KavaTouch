/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.uikit;

import org.kavaproject.kavatouch.internal.*;
import org.kavaproject.kavatouch.runtime.Creatable;
import org.kavaproject.kavatouch.runtime.SEL;
import org.kavaproject.kavatouch.uikit.staging.UndoManager;

import java.util.Set;

@Header("UIResponder")
@OccClass("UIResponder")
public interface UIResponder extends Creatable, UIResponderInternal {
    @OccInstanceMethod("nextResponder")
    UIResponder nextResponder();

    @OccInstanceMethod("isFirstResponder")
    boolean isFirstResponder();

    @OccInstanceMethod("canBecomeFirstResponder")
    boolean canBecomeFirstResponder();

    @OccInstanceMethod("becomeFirstResponder")
    boolean becomeFirstResponder();

    @OccInstanceMethod("canResignFirstResponder")
    boolean canResignFirstResponder();

    @OccInstanceMethod("resignFirstResponder")
    boolean resignFirstResponder();

    @OccInstanceProperty(value = "inputView", argumentSemantic = ArgumentSemantic.RETAIN)
    UIView getInputView();

    @OccInstanceProperty(value = "inputAccessoryView", argumentSemantic = ArgumentSemantic.RETAIN)
    UIView getInputAccessoryView();

    @OccInstanceMethod("reloadInputViews")
    void reloadInputViews();

    @OccInstanceMethod("touchesBegan:withEvent:")
    void onTouchesBegan(Set<UITouch> touches, UIEvent event);

    @OccInstanceMethod("touchesMoved:withEvent:")
    void onTouchesMoved(Set<UITouch> touches, UIEvent event);

    @OccInstanceMethod("touchesEnded:withEvent:")
    void onTouchesEnded(Set<UITouch> touches, UIEvent event);

    @OccInstanceMethod("touchesCancelled:withEvent:")
    void onTouchesCancelled(Set<UITouch> touches, UIEvent event);

    @OccInstanceMethod("motionBegan:withEvent:")
    void onMotionBegan(UIEventSubtype motion, UIEvent event);

    @OccInstanceMethod("motionEnded:withEvent:")
    void onMotionEnded(UIEventSubtype motion, UIEvent event);

    @OccInstanceMethod("motionCancelled:withEvent:")
    void onMotionCancelled(UIEventSubtype motion, UIEvent event);

    @OccInstanceMethod("remoteControlReceivedWithEvent:")
    void onRemoteControlReceived(UIEvent event);

    @OccInstanceProperty("undoManager")
    UndoManager getUndoManager();

    @OccInstanceMethod("canPerformAction:withSender:")
    boolean canPerform(SEL action, Object sender);

    @Override
    UIResponderFactory getFactory();
}
