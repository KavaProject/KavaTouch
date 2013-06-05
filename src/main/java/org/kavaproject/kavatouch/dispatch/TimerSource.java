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

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class TimerSource extends DispatchSource {
    private final Runnable mCallback = new Runnable() {
        @Override
        public void run() {
            TimerSource.this.fire();
        }
    };
    private DispatchTime mNextFireTime;
    private long mInterval; //in nanos
    private Semaphore mSemaphore = new Semaphore(1);
    private int mData;

    @CFunction("dispatch_source_create")
    public TimerSource(DispatchQueue queue) {
        super(queue);
    }

    private void fire() {
        if (isCancelled()) {
            return;
        }
        if (mSemaphore.tryAcquire()) {
            mData = 0;
            getQueue().dispatch(new DispatchBlock() {
                @Override
                public void execute(int index, Object context) {
                    getEventHandler().run();
                }
            }, mSemaphore);
        }
        mData++;
        int passedIntervals = calculatePassedIntervalls(mNextFireTime, mInterval);
        mNextFireTime = passedIntervals < 0 ? mNextFireTime : mNextFireTime.plus(mInterval * (passedIntervals + 1),
                TimeUnit.NANOSECONDS);
        updateNextFireTime();
    }

    private static int calculatePassedIntervalls(DispatchTime time, long interval) {
        long passedSpan = -time.remainingTime();
        if (passedSpan < 0) {
            return -1;
        } else {
            long convertedInteval = time.unit().convert(interval, TimeUnit.NANOSECONDS);
            return (int) (passedSpan / convertedInteval);
        }
    }

    private synchronized void updateNextFireTime() {
        if (mNextFireTime == DispatchTime.FOREVER) {
            return;
        }
        DispatchQueue.timerHandler.postDelayed(mCallback, TimeUnit.MILLISECONDS.convert(mNextFireTime.remainingTime()
                , mNextFireTime.unit()));
    }

    @Override
    protected void onActivate() {
        updateNextFireTime();
    }

    @Override
    public int getData() {
        return mData;
    }

    @Override
    protected void onDeactivate() {
        DispatchQueue.timerHandler.removeCallbacks(mCallback);
    }

    public void setTimer(DispatchTime start, long interval, long leeway) {
        if (isCancelled()) {
            return;
        }
        mInterval = interval;
        mNextFireTime = start == DispatchTime.NOW ? start.plus(0, TimeUnit.NANOSECONDS) : start;
        if (isActive()) {
            DispatchQueue.timerHandler.removeCallbacks(mCallback);
            updateNextFireTime();
        }
    }
}
