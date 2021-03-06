/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.coregraphics;

import org.kavaproject.kavatouch.internal.CEnumConstant;
import org.kavaproject.kavatouch.internal.CTypedef;
import org.kavaproject.kavatouch.internal.Header;

@Header("CGPath")
@CTypedef("CGPathElementType")
public enum GraphicsPathElementType {
    @CEnumConstant("kCGPathElementMoveToPoint")
    MOVE_TO_POINT,

    @CEnumConstant("kCGPathElementAddLineToPoint")
    ADD_LINE_TO_POINT,

    @CEnumConstant("kCGPathElementAddQuadCurveToPoint")
    ADD_CURVE_TO_POINT,

    @CEnumConstant("kCGPathElementAddCurveToPoint")
    ADD_QUAD_CURVE_TO_POINT,

    @CEnumConstant("kCGPathElementCloseSubpath")
    CLOSE_SUBPATH
}
