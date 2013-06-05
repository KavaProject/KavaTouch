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

@Header("base")
@CTypedef("dispatch_object_t")
public abstract class DispatchObject {
    private final AtomicInteger mRetainCount = new AtomicInteger(1);
    private Object mContext;
    private DispatchFunction mFinalizer;

    @CFunction("dispatch_release")
    public void release() {
        int i = mRetainCount.decrementAndGet();
        if (i == 0) {
            if (mFinalizer != null) {
                mFinalizer.execute(mContext);
            }
        }
    }

    @CFunction("dispatch_retain")
    public void retain() {
        mRetainCount.incrementAndGet();
    }

    @CFunction("dispatch_get_context")
    public Object getContext() {
        return mContext;
    }

    @CFunction("dispatch_set_context")
    public void setContext(Object context) {
        this.mContext = context;
    }

    @CFunction("dispatch_set_finalizer_f")
    public void setFinalizer(DispatchFunction finalizer) {
        mFinalizer = finalizer;
    }
}
