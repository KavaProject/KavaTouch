/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.dispatch.staging;

import org.kavaproject.kavatouch.internal.CTypedef;

@CTypedef("dispatch_io_interval_flags_t")
public enum DispatchIOIntervalFlags {
    DISPATCH_IO_STRICT_INTERVAL(0x1);

    private final int value;

    DispatchIOIntervalFlags(int value) {
        this.value = value;
    }
}
