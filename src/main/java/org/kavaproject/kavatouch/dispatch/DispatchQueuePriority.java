/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.dispatch;

import org.kavaproject.kavatouch.internal.CEnumConstant;
import org.kavaproject.kavatouch.internal.CTypedef;
import org.kavaproject.kavatouch.internal.Header;

@Header("dispatch")
@CTypedef("dispatch_queue_priority_t")
public enum DispatchQueuePriority {
    @CEnumConstant(value = "DISPATCH_QUEUE_PRIORITY_HIGH", constantValue = 2)
    HIGH(2),

    @CEnumConstant(value = "DISPATCH_QUEUE_PRIORITY_DEFAULT", constantValue = 0)
    DEFAULT(0),

    @CEnumConstant(value = "DISPATCH_QUEUE_PRIORITY_LOW", constantValue = -2)
    LOW(-2),

    @CEnumConstant(value = "DISPATCH_QUEUE_PRIORITY_BACKGROUND", constantValue = Integer.MIN_VALUE)
    BACKGROUND(Integer.MIN_VALUE);
    public final int value;

    DispatchQueuePriority(int value) {
        this.value = value;
    }
}
