/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.corefoundation;

import org.kavaproject.kavatouch.internal.CEnumConstant;
import org.kavaproject.kavatouch.internal.CTypedef;
import org.kavaproject.kavatouch.internal.Header;

@Header("CFRunLoop")
@CTypedef("CFRunLoopExitCode")
public enum CoreRunLoopExitReason {
    @CEnumConstant(value = "kCFRunLoopRunFinished", constantValue = 1)
    FINISHED(1),

    @CEnumConstant(value = "kCFRunLoopRunStopped", constantValue = 2)
    STOPPED(2),

    @CEnumConstant(value = "kCFRunLoopRunTimedOut", constantValue = 3)
    TIMED_OUT(3),

    @CEnumConstant(value = "kCFRunLoopRunHandledSource", constantValue = 4)
    HANDLED_SOURCE(4);
    public final int value;

    CoreRunLoopExitReason(int value) {
        this.value = value;
    }
}
