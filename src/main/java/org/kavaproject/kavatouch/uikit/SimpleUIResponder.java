/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.uikit;

import org.kavaproject.kavatouch.runtime.MethodResolver;
import org.kavaproject.kavatouch.runtime.SEL;
import org.kavaproject.kavatouch.uikit.staging.UndoManager;
import org.kavaproject.kavatouch.util.NotImplementedException;

import java.util.Set;

public class SimpleUIResponder implements UIResponder {
    private final MethodResolver mMethodResolver;
    private final UIResponderFactory mUIResponderFactory;

    protected SimpleUIResponder(UIResponderFactory uiResponderFactory, MethodResolver methodResolver) {
        mUIResponderFactory = uiResponderFactory;
        mMethodResolver = methodResolver;
    }

    @Override
    public UIWindow responderWindow() {
        if (this instanceof UIView) {
            return ((UIView) this).getWindow();
        } else if (nextResponder() != null) {
            return nextResponder().responderWindow();
        } else {
            return null;
        }
    }

    @Override
    public UIResponder nextResponder() {
        return null;
    }

    protected MethodResolver getMethodResolver() {
        return mMethodResolver;
    }

    @Override
    public boolean isFirstResponder() {
        return (responderWindow().getFirstResponder() == this);
    }

    @Override
    public boolean canBecomeFirstResponder() {
        return false;
    }

    @Override
    public boolean becomeFirstResponder() {
        if (isFirstResponder()) {
            return true;
        } else {
            UIWindow window = responderWindow();
            if (window != null && canBecomeFirstResponder()) {
                boolean didResign;
                UIResponder firstResponder = window.getFirstResponder();
                if (firstResponder != null && firstResponder.canResignFirstResponder()) {
                    didResign = firstResponder.resignFirstResponder();
                } else {
                    didResign = true;
                }
                if (didResign) {
                    window.makeKeyWindow();
                    window.setFirstResponder(this);
                    return true;
                }
            }
            return false;
        }
    }

    @Override
    public boolean canResignFirstResponder() {
        return true;
    }

    @Override
    public boolean resignFirstResponder() {
        if (isFirstResponder()) {
            responderWindow().setFirstResponder(null);
        }
        return true;
    }

    @Override
    public UIView getInputView() {
        return null;
    }

    @Override
    public UIView getInputAccessoryView() {
        return null;
    }

    @Override
    public void reloadInputViews() {

    }

    @Override
    public void onTouchesBegan(Set<UITouch> touches, UIEvent event) {
    }

    @Override
    public void onTouchesMoved(Set<UITouch> touches, UIEvent event) {
    }

    @Override
    public void onTouchesEnded(Set<UITouch> touches, UIEvent event) {
    }

    @Override
    public void onTouchesCancelled(Set<UITouch> touches, UIEvent event) {
    }

    @Override
    public void onMotionBegan(UIEventSubtype motion, UIEvent event) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void onMotionEnded(UIEventSubtype motion, UIEvent event) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void onMotionCancelled(UIEventSubtype motion, UIEvent event) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void onRemoteControlReceived(UIEvent event) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public UndoManager getUndoManager() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public boolean canPerform(SEL action, Object sender) {
        if (mMethodResolver.productsRespondToSelector(getFactory(), action)) {
            return true;
        } else {
            return nextResponder().canPerform(action, sender);
        }
    }


    @Override
    public UIResponderFactory getFactory() {
        return mUIResponderFactory;
    }


}
