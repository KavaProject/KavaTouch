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
@OpaqueType("CFRunLoopTimerRef")
public class CoreRunLoopTimer implements CoreType {
    Handler handler;
    boolean firing; //Set while performing the callout
    double nextFireDate;
    Set<CoreRunLoopMode> modes = new HashSet<CoreRunLoopMode>();
    CoreRunLoop rl;
    private boolean valid = true;
    private double interval;
    private int order;

    @CFunction("CFRunLoopTimerCreateWithHandler")
    public CoreRunLoopTimer(double fireDate, double interval, int order, Handler block) {
        this.nextFireDate = fireDate;
        this.interval = Math.max(0, interval);
        this.order = order;
        this.handler = block;
    }

    @CFunction("CFRunLoopTimerDoesRepeat")
    public boolean doesRepeat() {
        return interval > 0;
    }

    @CFunction("CFRunLoopTimerGetInterval")
    public double getInterval() {
        return interval;
    }

    @CFunction("CFRunLoopTimerGetNextFireDate")
    public double getNextFireDate() {
        return nextFireDate;
    }

    @CFunction("CFRunLoopTimerSetNextFireDate")
    public void setNextFireDate(double fireDate) {
        if (!isValid()) {
            return;
        }
        nextFireDate = fireDate;
        for (CoreRunLoopMode mode : modes) {
            mode.timers.remove(this);
            mode.timers.add(this);
            mode.armNextTimer();
        }
    }

    @CFunction("CFRunLoopTimerIsValid")
    public boolean isValid() {
        return valid;
    }

    @CFunction("CFRunLoopTimerGetOrder")
    public int getOrder() {
        return order;
    }

    @CFunction("CFRunLoopTimerInvalidate")
    public void invalidate() {
        valid = false;
        CoreRunLoop rl = this.rl;
        if (rl == null) {
            return;
        }
        for (String modeName : rl.copyAllModes()) {
            rl.removeTimer(this, modeName);
        }
        rl.removeTimer(this, CoreRunLoop.MODE_DEFAULT);
    }

    public interface Handler {
        void execute(CoreRunLoopTimer timer);
    }
}
