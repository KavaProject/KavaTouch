/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.foundation;

import org.kavaproject.kavatouch.corefoundation.CoreRunLoopTimer;

public class DefaultTimer implements Timer {
    private final FoundationDateFactory mFoundationDateFactory;
    private final TimerFactory mTimerFactory;
    private CoreRunLoopTimer mCoreRunLoopTimer;
    private Object mUserInfo;
    private boolean mRepeats;
    private Invocation mInvocation;

    protected DefaultTimer(CoreRunLoopTimer runLoopTimer, TimerFactory timerFactory,
                           FoundationDateFactory foundationDateFactory) {
        mCoreRunLoopTimer = runLoopTimer;
        mFoundationDateFactory = foundationDateFactory;
        mTimerFactory = timerFactory;
    }

    protected DefaultTimer(Invocation invocation, Object userInfo, boolean repeats, double interval, double fireDate,
                           TimerFactory timerFactory, FoundationDateFactory foundationDateFactory) {
        mFoundationDateFactory = foundationDateFactory;
        mTimerFactory = timerFactory;
        mCoreRunLoopTimer = new CoreRunLoopTimer(fireDate, interval, 0, new CoreRunLoopTimer.Handler() {
            @Override
            public void execute(CoreRunLoopTimer cfTimer) {
                fire();
            }
        });
        mInvocation = invocation;
        mUserInfo = userInfo;
        mRepeats = repeats;
    }

    @Override
    public void fire() {
        mInvocation.invoke();
        if (!mRepeats) {
            invalidate();
        }
    }

    @Override
    public void invalidate() {
        mCoreRunLoopTimer.invalidate();
    }

    @Override
    public boolean isValid() {
        return mCoreRunLoopTimer.isValid();
    }

    @Override
    public FoundationDate fireDate() {
        return mFoundationDateFactory.createWithTimeIntervalSinceReferenceDate(mCoreRunLoopTimer.getNextFireDate());
    }

    @Override
    public void setFireDate(FoundationDate date) {
        mCoreRunLoopTimer.setNextFireDate(date.timeIntervalSinceReferenceDate());
    }

    @Override
    public double timeInterval() {
        return mCoreRunLoopTimer.getInterval();
    }

    @Override
    public Object userInfo() {
        return mUserInfo;
    }

    @Override
    public TimerFactory getFactory() {
        return mTimerFactory;
    }

    @Override
    public CoreRunLoopTimer toCoreType() {
        return mCoreRunLoopTimer;
    }
}
