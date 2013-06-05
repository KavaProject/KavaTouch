/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.coreanimation;

import org.kavaproject.kavatouch.internal.Header;
import org.kavaproject.kavatouch.internal.OccConstant;

@Header("CALayer")
public enum AnimationContentsGravity {
    @OccConstant("kCAGravityCenter")
    CENTER,

    @OccConstant("kCAGravityTop")
    TOP,

    @OccConstant("kCAGravityBottom")
    BOTTOM,

    @OccConstant("kCAGravityLeft")
    LEFT,

    @OccConstant("kCAGravityRight")
    RIGHT,

    @OccConstant("kCAGravityTopLeft")
    TOP_LEFT,

    @OccConstant("kCAGravityTopRight")
    TOP_RIGHT,

    @OccConstant("kCAGravityBottomLeft")
    BOTTOM_LEFT,

    @OccConstant("kCAGravityBottomRight")
    BOTTOM_RIGHT,

    @OccConstant("kCAGravityResize")
    RESIZE,

    @OccConstant("kCAGravityResizeAspect")
    RESIZE_ASPECT,

    @OccConstant("kCAGravityResizeAspectFill")
    RESIZE_ASPECT_FILL
}
