/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.dispatch.staging;

public enum DispatchSourceProcFlags {
    DISPATCH_PROC_EXIT(0x80000000),

    DISPATCH_PROC_FORK(0x40000000),

    DISPATCH_PROC_EXEC(0x20000000),

    DISPATCH_PROC_SIGNAL(0x08000000);
    private final int value;

    DispatchSourceProcFlags(int value) {
        this.value = value;
    }
}
