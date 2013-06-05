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

import java.util.EnumSet;

@Header("CFRunLoop")
@OpaqueType("CFRunLoopObserverRef")
public class CoreRunLoopObserver implements CoreType {
    EnumSet<CoreRunLoopActivity> activities;
    boolean repeats;
    int order;
    Handler handler;
    boolean valid = true;
    CoreRunLoop rl;

    @CFunction("CFRunLoopObserverCreateWithHandler")
    public CoreRunLoopObserver(EnumSet<CoreRunLoopActivity> activities, Boolean repeats, int order, Handler block) {
        this.activities = activities;
        this.repeats = repeats;
        this.order = order;
        this.handler = block;
    }

    @CFunction("CFRunLoopObserverDoesRepeat")
    public boolean doesRepeat() {
        return repeats;
    }

    @CFunction("CFRunLoopObserverGetActivities")
    public EnumSet<CoreRunLoopActivity> getActivities() {
        return activities;
    }

    @CFunction("CFRunLoopObserverGetOrder")
    public int getOrder() {
        return order;
    }

    @CFunction("CFRunLoopObserverInvalidate")
    public void invalidate() {
        for (String mode : rl.copyAllModes()) {
            rl.removeObserver(this, mode);
        }
        rl.removeObserver(this, CoreRunLoop.MODE_DEFAULT);
        valid = false;
    }

    @CFunction("CFRunLoopObserverIsValid")
    public boolean isValid() {
        return valid;
    }

    public interface Handler {
        void execute(CoreRunLoopObserver observer, CoreRunLoopActivity activity);
    }
}
