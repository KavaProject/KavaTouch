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
import org.kavaproject.kavatouch.internal.Header;

@Header("NSOperation")
public enum OperationQueuePriority {
    @CEnumConstant(value = "NSOperationQueuePriorityVeryLow", constantValue = -8)
    VERY_LOW(-8),

    @CEnumConstant(value = "NSOperationQueuePriorityLow", constantValue = -4)
    LOW(-4),

    @CEnumConstant(value = "NSOperationQueuePriorityNormal", constantValue = 0)
    NORMAL(0),

    @CEnumConstant(value = "NSOperationQueuePriorityHigh", constantValue = 4)
    HIGH(4),

    @CEnumConstant(value = "NSOperationQueuePriorityVeryHigh", constantValue = 8)
    VERY_HIGH(8);
    public final int value;

    OperationQueuePriority(int value) {
        this.value = value;
    }

}
