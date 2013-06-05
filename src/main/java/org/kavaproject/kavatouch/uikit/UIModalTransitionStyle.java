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

@Header("UIViewController")
@CTypedef("UIModalTransitionStyle")
public enum UIModalTransitionStyle {
    @CEnumConstant(value = "UIModalTransitionStyleCoverVertical", constantValue = 0)
    COVER_VERTICAL(0),

    @CEnumConstant(value = "UIModalTransitionStyleFlipHorizontal", constantValue = 1)
    FLIP_HORIZONTAL(1),

    @CEnumConstant(value = "UIModalTransitionStyleCrossDissolve", constantValue = 2)
    CROSS_DISSOLVE(2),

    @CEnumConstant(value = "UIModalTransitionStylePartialCurl", constantValue = 3)
    PARTIAL_CURL(3);

    UIModalTransitionStyle(int value) {
    }
}
