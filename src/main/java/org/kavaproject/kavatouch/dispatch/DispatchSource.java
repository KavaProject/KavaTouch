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
import org.kavaproject.kavatouch.internal.CTypedef;
import org.kavaproject.kavatouch.internal.Header;

import java.util.concurrent.atomic.AtomicInteger;

@Header("dispatch")
@CTypedef("dispatch_source_t")
public abstract class DispatchSource extends DispatchObject {
    private AtomicInteger mSuspensionCount = new AtomicInteger(1);
    private Runnable mEventHandler;
    private DispatchBlock mCancelHandler;
    private DispatchQueue mQueue;
    private boolean mCancelled;

    public DispatchSource(DispatchQueue queue) {
        mQueue = queue;
    }

    @CFunction("dispatch_resume")
    public void resume() {
        int s = mSuspensionCount.decrementAndGet();
        if (s == 0) {
            onActivate();
        }
    }

    protected abstract void onActivate();

    @CFunction("dispatch_source_get_data")
    public abstract int getData();

    protected boolean isActive() {
        return mSuspensionCount.get() <= 0;
    }

    @CFunction("dispatch_suspend")
    public void suspend() {
        int s = mSuspensionCount.incrementAndGet();
        if (s == 1) {
            onDeactivate();
        }
    }

    protected abstract void onDeactivate();

    public DispatchQueue getQueue() {
        return mQueue;
    }

    @CFunction("dispatch_source_cancel")
    public void cancel() {
        mCancelled = true;
        if (mCancelHandler != null) {
            mQueue.dispatchAsync(mCancelHandler);
        }
    }

    public boolean isCancelled() {
        return mCancelled;
    }

    public Runnable getEventHandler() {
        return mEventHandler;
    }

    @CFunction("dispatch_source_set_event_handler")
    public void setEventHandler(Runnable handler) {
        mEventHandler = handler;
    }

    @CFunction("dispatch_source_set_cancel_handler")
    public void setCancelHandler(DispatchBlock cancelHandler) {
        mCancelHandler = cancelHandler;
    }
}
