/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.corefoundation;

import org.kavaproject.kavatouch.internal.CFunction;
import org.kavaproject.kavatouch.internal.Header;
import org.kavaproject.kavatouch.internal.OpaqueType;

import java.util.HashSet;
import java.util.Set;

@Header("CFRunLoop")
@OpaqueType("CFRunLoopSourceRef")
public class CoreRunLoopSource implements CoreType {
    boolean signalled;
    Set<CoreRunLoop> runloops = new HashSet<CoreRunLoop>();
    private int mOrder;
    private boolean mValid = true;

    @CFunction("CFRunLoopSourceCreate")
    protected CoreRunLoopSource(int order) {
        mOrder = order;
    }

    @CFunction("CFRunLoopSourceGetOrder")
    public int getOrder() {
        return mOrder;
    }

    @CFunction("CFRunLoopSourceInvalidate")
    public synchronized void invalidate() {
        for (CoreRunLoop rl : runloops) {
            for (String mode : rl.copyAllModes()) {
                rl.removeSource(this, mode);
            }
            rl.removeSource(this, CoreRunLoop.MODE_DEFAULT);
        }
        mValid = false;
    }

    @CFunction("CFRunLoopSourceIsValid")
    public boolean isValid() {
        return mValid;
    }

    @CFunction("CFRunLoopSourceSignal")
    public void signal() {
        signalled = true;
    }
}
