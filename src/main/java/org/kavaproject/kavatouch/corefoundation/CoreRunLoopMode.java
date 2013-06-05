/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.corefoundation;

import org.kavaproject.kavatouch.dispatch.DispatchQueue;
import org.kavaproject.kavatouch.internal.MachMessage;
import org.kavaproject.kavatouch.internal.MachPort;
import org.kavaproject.kavatouch.util.AndroidConversions;

import java.util.*;
import java.util.concurrent.TimeUnit;

class CoreRunLoopMode {
    final String mode;
    final MachPort timerPort;
    private final Runnable mCallback;
    SortedSet<CoreRunLoopObserver> observers = new TreeSet<CoreRunLoopObserver>(new Comparator<CoreRunLoopObserver>() {
        @Override
        public int compare(CoreRunLoopObserver lhs, CoreRunLoopObserver rhs) {
            return ((Integer) lhs.order).compareTo(rhs.order);
        }
    });
    SortedSet<CoreRunLoopTimer> timers = new TreeSet<CoreRunLoopTimer>(new Comparator<CoreRunLoopTimer>() {
        @Override
        public int compare(CoreRunLoopTimer lhs, CoreRunLoopTimer rhs) {
            return ((Double) lhs.nextFireDate).compareTo(rhs.nextFireDate);
        }
    });
    SortedSet<CoreRunLoopSourceCustom> v0Sources = new TreeSet<CoreRunLoopSourceCustom>(new
                                                                                                Comparator<CoreRunLoopSourceCustom>() {
        @Override
        public int compare(CoreRunLoopSourceCustom lhs, CoreRunLoopSourceCustom rhs) {
            return ((Integer) lhs.getOrder()).compareTo(rhs.getOrder());
        }
    });
    Map<MachPort, CoreRunLoopSourcePort> v1Sources = new HashMap<MachPort, CoreRunLoopSourcePort>();


    CoreRunLoopMode(String mode) {
        this.mode = mode;
        timerPort = new MachPort("org.kavaproject.timerPort");
        timerPort.releaseReceiveRights();
        final MachMessage msg = new MachMessage();
        msg.destinationPort = timerPort;
        mCallback = new Runnable() {
            @Override
            public void run() {
                msg.send(0, TimeUnit.NANOSECONDS);
            }
        };
    }

    public void armNextTimer() {
        DispatchQueue.timerHandler.removeCallbacks(mCallback);
        CoreRunLoopTimer nextTimer = null;
        Iterator<CoreRunLoopTimer> it = timers.iterator();
        while (it.hasNext()) {
            nextTimer = it.next();
            if (!nextTimer.firing) {
                break;
            }
        }
        if (nextTimer == null) {
            return;
        }
        DispatchQueue.timerHandler.postDelayed(mCallback, AndroidConversions.toMillisSince1970(nextTimer.getNextFireDate()) - System.currentTimeMillis());
    }
}
