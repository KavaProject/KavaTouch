/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.dispatch;

import org.kavaproject.kavatouch.internal.CData;
import org.kavaproject.kavatouch.internal.CFunction;
import org.kavaproject.kavatouch.internal.CTypedef;
import org.kavaproject.kavatouch.internal.Header;

import java.util.concurrent.TimeUnit;

@Header("time")
@CTypedef("dispatch_time_t")
public interface DispatchTime {
    public static final int NSEC_PER_SEC = 1000000000;
    public static final int USEC_PER_SEC = 1000000;
    public static final int NSEC_PER_USEC = 1000;
    @CData("DISPATCH_TIME_FOREVER")
    DispatchTime FOREVER = new DispatchTime() {
        @Override
        public DispatchTime plus(long delta, TimeUnit unit) {
            return this;
        }

        @Override
        public long remainingTime() {
            return Long.MAX_VALUE;
        }

        @Override
        public TimeUnit unit() {
            return TimeUnit.DAYS;
        }
    };
    @CData("DISPATCH_TIME_NOW")
    DispatchTime NOW = new DispatchTime() {
        @Override
        public DispatchTime plus(long delta, TimeUnit unit) {
            return new NanoTime(System.nanoTime() + TimeUnit.NANOSECONDS.convert(delta, unit));
        }

        @Override
        public long remainingTime() {
            return 0;
        }

        @Override
        public TimeUnit unit() {
            return TimeUnit.NANOSECONDS;
        }
    };

    @CFunction("dispatch_time")
    DispatchTime plus(long delta, TimeUnit unit);

    long remainingTime();

    TimeUnit unit();
}
