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

@Header("NSNotificationQueue")
@CTypedef("NSNotificationCoalescing")
public enum NotificationCoalescing {
    @CEnumConstant(value = "NSNotificationNoCoalescing", constantValue = 0)
    NO_COALESCING(0),

    @CEnumConstant(value = "NSNotificationCoalescingOnName", constantValue = 1)
    COALESCING_ON_NAME(1),

    @CEnumConstant(value = "NSNotificationCoalescingOnSender", constantValue = 2)
    COALESCING_ON_SENDER(2);
    public final int value;

    NotificationCoalescing(int value) {
        this.value = value;
    }
}
