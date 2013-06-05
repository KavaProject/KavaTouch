/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.uikit;

import org.kavaproject.kavatouch.internal.CEnumConstant;
import org.kavaproject.kavatouch.internal.CTypedef;
import org.kavaproject.kavatouch.internal.Header;

@Header("UIView")
@CTypedef("UIViewContentMode")
public enum UIViewContentMode {
    @CEnumConstant("UIViewContentModeScaleToFill")
    SCALE_TO_FILL,

    @CEnumConstant("UIViewContentModeScaleAspectFit")
    SCALE_ASPECT_FIT,

    @CEnumConstant("UIViewContentModeScaleAspectFill")
    SCALE_ASPECT_FILL,

    @CEnumConstant("UIViewContentModeRedraw")
    REDRAW,

    @CEnumConstant("UIViewContentModeCenter")
    CENTER,

    @CEnumConstant("UIViewContentModeTop")
    TOP,

    @CEnumConstant("UIViewContentModeBottom")
    BOTTOM,

    @CEnumConstant("UIViewContentModeLeft")
    LEFT,

    @CEnumConstant("UIViewContentModeRight")
    RIGHT,

    @CEnumConstant("UIViewContentModeTopLeft")
    TOP_LEFT,

    @CEnumConstant("UIViewContentModeTopRight")
    TOP_RIGHT,

    @CEnumConstant("UIViewContentModeBottomLeft")
    BOTTOM_LEFT,

    @CEnumConstant("UIViewContentModeBottomRight")
    BOTTOM_RIGHT,
}
