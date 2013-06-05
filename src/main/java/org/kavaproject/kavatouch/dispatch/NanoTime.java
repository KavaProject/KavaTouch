/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.dispatch;

import java.util.concurrent.TimeUnit;

public class NanoTime implements DispatchTime {
    private final long mNanos;

    public NanoTime(long nanos) {
        mNanos = nanos;
    }

    @Override
    public DispatchTime plus(long delta, TimeUnit unit) {
        long sum = TimeUnit.NANOSECONDS.convert(delta, unit);
        sum += mNanos;
        if (delta > 0 && sum < mNanos) {
            return DispatchTime.FOREVER;
        }
        if (delta < 0 && sum > mNanos) {
            return new NanoTime(Long.MIN_VALUE);
        }
        return new NanoTime(sum);
    }

    @Override
    public long remainingTime() {
        return mNanos - System.nanoTime();
    }

    @Override
    public TimeUnit unit() {
        return TimeUnit.NANOSECONDS;
    }
}
