/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.dispatch;

import org.kavaproject.kavatouch.internal.CTypedef;
import org.kavaproject.kavatouch.internal.Header;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

@Header("queue")
@CTypedef("dispatch_block_t")
public abstract class DispatchBlock implements DispatchQueueItem {
    static final ThreadLocal<DispatchQueue> currentQueue = new ThreadLocal<DispatchQueue>();
    Semaphore executionFinishedSemaphore;
    boolean isBarrier;
    int waitingOnBarrier;
    int iterations = 1;
    AtomicInteger index = new AtomicInteger();
    DispatchQueue queue;

    @Override
    public boolean executeNextBlock() {
        int i = index.getAndIncrement();
        if (canExecute(i)) {
            currentQueue.set(queue);
            execute(i, queue.getContext());
            if (isBarrier) {
                queue.onBarrierPassed();
            }
            if (executionFinishedSemaphore != null) {
                executionFinishedSemaphore.release();
            }
            return true;
        } else {
            return false;
        }
    }

    public abstract void execute(int index, Object context);

    @Override
    public boolean canExecute() {
        return canExecute(index.get());
    }

    @Override
    public boolean isEmpty() {
        return index.get() >= iterations;
    }

    private boolean canExecute(int i) {
        return i < iterations && waitingOnBarrier < queue.getFinishedBarrier();
    }
}
