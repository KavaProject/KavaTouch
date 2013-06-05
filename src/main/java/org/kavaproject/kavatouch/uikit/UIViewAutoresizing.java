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
@CTypedef("UIViewAutoresizing")
public enum UIViewAutoresizing {
    @CEnumConstant("UIViewAutoresizingNone")
    NONE,

    @CEnumConstant("UIViewAutoresizingFlexibleLeftMargin")
    FLEXIBLE_LEFT_MARGIN,

    @CEnumConstant("UIViewAutoresizingFlexibleWidth")
    FLEXIBLE_WIDTH,

    @CEnumConstant("UIViewAutoresizingFlexibleRightMargin")
    FLEXIBLE_RIGHT_MARGIN,

    @CEnumConstant("UIViewAutoresizingFlexibleTopMargin")
    FLEXIBLE_TOP_MARGIN,

    @CEnumConstant("UIViewAutoresizingFlexibleHeight")
    FLEXIBLE_HEIGHT,

    @CEnumConstant("UIViewAutoresizingFlexibleBottomMargin")
    FLEXIBLE_BOTTOM_MARGIN,
}
