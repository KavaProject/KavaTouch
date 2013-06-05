/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.dispatch;

import org.kavaproject.kavatouch.internal.CFunction;

import java.util.concurrent.TimeUnit;

public class WallTime implements DispatchTime {
    private final long mMillis;

    @CFunction("dispatch_walltime")
    public WallTime(long when) {
        mMillis = when;
    }

    @CFunction("dispatch_walltime")
    public static WallTime currentTime(long delta, TimeUnit unit) {
        return new WallTime(System.currentTimeMillis() + TimeUnit.MILLISECONDS.convert(delta, unit));
    }

    @Override
    public DispatchTime plus(long delta, TimeUnit unit) {
        long sum = TimeUnit.MILLISECONDS.convert(delta, unit);
        sum += mMillis;
        if (delta > 0 && sum < mMillis) {
            return DispatchTime.FOREVER;
        }
        if (delta < 0 && sum > mMillis) {
            return new WallTime(Long.MIN_VALUE);
        }
        return new WallTime(sum);
    }

    @Override
    public long remainingTime() {
        return mMillis - System.currentTimeMillis();
    }

    @Override
    public TimeUnit unit() {
        return TimeUnit.NANOSECONDS;
    }
}
