/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.foundation;

import org.kavaproject.kavatouch.internal.CEnumConstant;
import org.kavaproject.kavatouch.internal.CTypedef;

@CTypedef("NSAttributedStringEnumerationOptions ")
public enum AttributedStringEnumerationOptions {
    @CEnumConstant(value = "NSAttributedStringEnumerationReverse", constantValue = 1L << 1)
    REVERSE(1L << 1),

    @CEnumConstant(value = "NSAttributedStringEnumerationLongestEffectiveRangeNotRequired", constantValue = 1L << 20)
    LONGEST_EFFECTIVE_RANGE_NOT_REQUIRED(1L << 20);
    public final long value;

    AttributedStringEnumerationOptions(long value) {
        this.value = value;
    }

}
