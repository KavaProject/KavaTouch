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
import org.kavaproject.kavatouch.internal.Header;

@Header("NSObjCRuntime")
@CTypedef("NSComparisonResult")
public enum ComparisonResult {
    @CEnumConstant(value = "NSOrderedAscending", constantValue = -1)
    ASCENDING(-1),

    @CEnumConstant(value = "NSOrderedSame", constantValue = 0)
    SAME(0),

    @CEnumConstant(value = "NSOrderedDescending", constantValue = 1)
    DESCENDING(1);
    public final int value;

    ComparisonResult(int value) {
        this.value = value;
    }
}
