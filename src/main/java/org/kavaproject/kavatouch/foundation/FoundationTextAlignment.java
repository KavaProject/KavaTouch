/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.foundation;

import android.view.Gravity;
import org.kavaproject.kavatouch.foundation.staging.TextAlignment;
import org.kavaproject.kavatouch.internal.CEnumConstant;
import org.kavaproject.kavatouch.internal.CFunction;
import org.kavaproject.kavatouch.internal.CTypedef;
import org.kavaproject.kavatouch.internal.Header;
import org.kavaproject.kavatouch.util.NotImplementedException;

@Header("NSText")
@CTypedef(value = "NSTextAlignment", tokenGroup = "NSString_UIKit_Additions")
public enum FoundationTextAlignment {
    @CEnumConstant(value = "NSTextAlignmentLeft", constantValue = 0)
    LEFT(0, Gravity.LEFT),

    @CEnumConstant(value = "NSTextAlignmentCenter", constantValue = 1)
    CENTER(1, Gravity.CENTER_HORIZONTAL),

    @CEnumConstant(value = "NSTextAlignmentRight", constantValue = 2)
    RIGHT(2, Gravity.RIGHT),

    @CEnumConstant(value = "NSTextAlignmentJustified", constantValue = 3)
    JUSTIFIED(3, Gravity.LEFT),

    @CEnumConstant(value = "NSTextAlignmentNatural", constantValue = 4)
    NATURAL(4, Gravity.LEFT);
    public final int value;
    public final int gravity;

    FoundationTextAlignment(int value, int gravity) {
        this.value = value;
        this.gravity = gravity;
    }

    @CFunction("NSTextAlignmentFromCTTextAlignment")
    public FoundationTextAlignment valueOf(TextAlignment textAlignment) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("NSTextAlignmentToCTTextAlignment")
    public TextAlignment toTextAlignment() {
        throw new NotImplementedException(); //TODO
    }
}
