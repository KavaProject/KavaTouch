/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.uikit;

import org.kavaproject.kavatouch.internal.Header;
import org.kavaproject.kavatouch.internal.OccClass;
import org.kavaproject.kavatouch.internal.OccInstanceMethod;
import org.kavaproject.kavatouch.internal.OccInstanceProperty;
import org.kavaproject.kavatouch.runtime.Creatable;
import org.kavaproject.kavatouch.uikit.staging.UIGestureRecognizer;

import java.util.Set;

@Header("UIEvent")
@OccClass("UIEvent") //UITouchesEvent
public interface UIEvent extends UIEventInternal, Creatable {
    @OccInstanceMethod("touchesForView")
    Set<UITouch> touchesForView(UIView view);

    @OccInstanceMethod("allTouches")
    Set<UITouch> allTouches();

    @OccInstanceMethod("touchesForWindow")
    Set<UITouch> touchesForWindow(UIWindow window);

    @OccInstanceProperty("timestamp")
    double timestamp();

    @OccInstanceProperty("type")
    UIEventType type();

    @OccInstanceProperty("subtype")
    UIEventSubtype subtype();

    @OccInstanceMethod("touchesForGestureRecognizer:")
    Set<UITouch> touchesForGestureRecognizer(UIGestureRecognizer recognizer);

    @Override
    UIEventFactory getFactory();
}
