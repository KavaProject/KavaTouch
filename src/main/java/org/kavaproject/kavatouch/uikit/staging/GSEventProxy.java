/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.uikit.staging;

import org.kavaproject.kavatouch.coregraphics.GraphicsPoint;
import org.kavaproject.kavatouch.coregraphics.GraphicsSize;
import org.kavaproject.kavatouch.internal.OccClass;
import org.kavaproject.kavatouch.uikit.UIEventSubtype;
import org.kavaproject.kavatouch.uikit.UIEventType;
import org.kavaproject.kavatouch.uikit.UITouchPhase;

@OccClass("GSEventProxy")
public class GSEventProxy {
    UITouchPhase flags;
    UIEventType type;
    UIEventSubtype subtype;
    GraphicsSize size;
    GraphicsPoint point1, point2, point3;
}
