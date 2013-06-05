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
@CTypedef("NSPostingStyle")
public enum PostingStyle {
    @CEnumConstant(value = "NSPostWhenIdle", constantValue = 1)
    POST_WHEN_IDLE(1),

    @CEnumConstant(value = "NSPostASAP", constantValue = 2)
    POST_ASAP(2),

    @CEnumConstant(value = "NSPostNow", constantValue = 3)
    NOW(3);
    public final int value;

    PostingStyle(int value) {
        this.value = value;
    }
}
