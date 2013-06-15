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

@Header("UIToolbar")
@CTypedef("UIToolbarPosition")
public enum UIToolbarPosition {
    @CEnumConstant(value = "UIToolbarPositionAny", constantValue = 0)
    ANY(0),
    @CEnumConstant(value = "UIToolbarPositionBottom", constantValue = 1)
    BOTTOM(1),
    @CEnumConstant(value = "UIToolbarPositionTop", constantValue = 2)
    TOP(2);

    UIToolbarPosition(int value) {

    }
}
