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
import org.kavaproject.kavatouch.internal.*;
import org.kavaproject.kavatouch.runtime.Creatable;
import org.kavaproject.kavatouch.uikit.staging.UIGestureRecognizer;

import java.util.List;

@Header("UITouch")
@OccClass("UITouch")
public interface UITouch extends UITouchInternal, Creatable {
    @OccInstanceMethod("locationInView:")
    GraphicsPoint locationInView(UIView view);

    @OccInstanceMethod("previousLocationInView:")
    GraphicsPoint previousLocationInView(UIView view);

    @OccInstanceProperty(value = "view", argumentSemantic = ArgumentSemantic.RETAIN)
    UIView getView();

    @OccInstanceProperty(value = "window", argumentSemantic = ArgumentSemantic.RETAIN)
    UIWindow getWindow();

    @OccInstanceProperty("tapCount")
    int getTapCount();

    @OccInstanceProperty("timestamp")
    double getTimestamp();

    @OccInstanceProperty("phase")
    UITouchPhase getPhase();

    @OccInstanceProperty(value = "gestureRecognizers", argumentSemantic = ArgumentSemantic.COPY)
    List<UIGestureRecognizer> getGestureRecognizers();

    @Override
    UITouchFactory getFactory();
}
