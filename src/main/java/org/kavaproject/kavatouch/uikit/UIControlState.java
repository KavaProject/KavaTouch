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

@Header("UIControl")
@CTypedef("UIControlState")
public enum UIControlState {
    @CEnumConstant(value = "UIControlStateNormal", constantValue = 0)
    NORMAL(0),
    @CEnumConstant(value = "UIControlStateHighlighted", constantValue = 1 << 0)
    HIGHLIGHTED(1 << 0),
    @CEnumConstant(value = "UIControlStateDisabled", constantValue = 1 << 1)
    DISABLED(1 << 1),
    @CEnumConstant(value = "UIControlStateSelected", constantValue = 1 << 2)
    SELECTED(1 << 2),
    @CEnumConstant(value = "UIControlStateApplication", constantValue = 0x00FF0000)
    APPLICATION(0x00FF0000),
    @CEnumConstant(value = "UIControlStateReserved", constantValue = 0xFF000000)
    RESERVED(0xFF000000);

    UIControlState(int value) {
    }
}
