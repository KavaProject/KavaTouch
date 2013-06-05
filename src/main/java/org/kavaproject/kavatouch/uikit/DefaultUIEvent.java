/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.uikit;

import org.kavaproject.kavatouch.uikit.staging.UIGestureRecognizer;

import java.util.HashSet;
import java.util.Set;

public class DefaultUIEvent implements UIEvent {
    private final UIEventFactory mUIEventFactory;
    private Set<UITouch> mTouches;
    private double mTimestamp = Double.MIN_VALUE;

    protected DefaultUIEvent(UIEventFactory uiEventFactory) {
        mUIEventFactory = uiEventFactory;
    }

    @Override
    public Set<UITouch> touchesForView(UIView view) {
        HashSet<UITouch> touches = new HashSet<UITouch>(1);
        for (UITouch touch : allTouches()) {
            if (touch.getView() == view) {
                touches.add(touch);
            }
        }
        return touches;
    }

    @Override
    public Set<UITouch> allTouches() {
        return mTouches;
    }

    @Override
    public Set<UITouch> touchesForWindow(UIWindow window) {
        HashSet<UITouch> touches = new HashSet<UITouch>(1);
        for (UITouch touch : allTouches()) {
            if (touch.getWindow() == window) {
                touches.add(touch);
            }
        }
        return touches;
    }

    @Override
    public double timestamp() {
        return mTimestamp;
    }

    @Override
    public UIEventType type() {
        return UIEventType.TOUCHES;
    }

    @Override
    public UIEventSubtype subtype() {
        return UIEventSubtype.NONE;
    }

    @Override
    public Set<UITouch> touchesForGestureRecognizer(UIGestureRecognizer recognizer) {
        HashSet<UITouch> touches = new HashSet<UITouch>(1);
        for (UITouch touch : allTouches()) {
            if (touch.getGestureRecognizers().contains(recognizer)) {
                touches.add(touch);
            }
        }
        return touches;
    }

    @Override
    public UIEventFactory getFactory() {
        return mUIEventFactory;
    }

    @Override
    public void update(Set<UITouch> touches) {
        mTouches = touches;
        for (UITouch touch : touches) {
            mTimestamp = Math.max(mTimestamp, touch.getTimestamp());
        }
    }
}
