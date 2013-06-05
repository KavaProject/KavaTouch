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

@Header("UIBezierPath")
@CTypedef("UIRectCorner")
public enum UIRectCorner {
    @CEnumConstant(value = "UIRectCornerTopLeft", constantValue = 1 << 0)
    TOP_LEFT(1 << 0),
    @CEnumConstant(value = "UIRectCornerTopRight", constantValue = 1 << 1)
    TOP_RIGHT(1 << 1),
    @CEnumConstant(value = "UIRectCornerBottomLeft", constantValue = 1 << 2)
    BOTTOM_LEFT(1 << 2),
    @CEnumConstant(value = "UIRectCornerBottomRight", constantValue = 1 << 3)
    BOTTOM_RIGHT(1 << 3),
    @CEnumConstant(value = "UIRectCornerAllCorners", constantValue = ~0)
    ALL_CORNERS(~0);

    UIRectCorner(int value) {

    }
}
