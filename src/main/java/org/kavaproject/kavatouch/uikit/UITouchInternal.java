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
import org.kavaproject.kavatouch.uikit.staging.UIGestureRecognizer;

import java.util.EnumSet;

public interface UITouchInternal {
    void setTapCount(int tapCount);

    void setLocationInWindow(GraphicsPoint location);

    void setView(UIView view);

    void setWindow(UIWindow window);

    void setPhase(UITouchPhase phase);

    EnumSet<UITouchFlag> getTouchFlags();

    void setTimestamp(double timestamp);

    void addGestureRecognizers(Iterable<UIGestureRecognizer> recognizers);

    void removeGestureRecognizers(Iterable<UIGestureRecognizer> recognizers);

    void clearGestureRecognizers();
}
