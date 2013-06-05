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

public class DispatchSemaphore extends DispatchObject {
    final Semaphore semaphore;

    @CFunction("dispatch_semaphore_create")
    public DispatchSemaphore(int value) {
        semaphore = new Semaphore(value);
    }

    @CFunction("dispatch_semaphore_signal")
    public int signal() {
        boolean h = semaphore.hasQueuedThreads();
        semaphore.release();
        return h ? 1 : 0; //TODO Has to be implemented in a synced way
    }

    @CFunction("dispatch_semaphore_wait")
    public int waitForSemaphore(DispatchTime timeout) {
        return waitForSemaphore(semaphore, timeout) ? 0 : -1;
    }

    public static boolean waitForSemaphore(Semaphore semaphore, DispatchTime timeout) {
        try {
            return semaphore.tryAcquire(timeout.remainingTime(), timeout.unit());
        } catch (InterruptedException e) {
            throw new Error();
        }
    }
}
