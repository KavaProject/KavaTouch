/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.internal;

import org.kavaproject.kavatouch.uikit.Session;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

public class MachPort {
    private final CopyOnWriteArrayList<Observer> mObservers = new CopyOnWriteArrayList<Observer>();
    private final ArrayBlockingQueue<MachMessage> mQueue;
    private final String mName;
    private boolean mValid = true;
    private Thread mReceiverThread;

    public MachPort(String name) {
        this(name, Session.DEFAULT_MACH_QUEUE_CAPACITY);
    }

    public MachPort(String name, int queueCapacity) {
        mName = name;
        mQueue = new ArrayBlockingQueue<MachMessage>(queueCapacity);
        acquireReceiveRights();
    }

    public synchronized void acquireReceiveRights() {
        if (hasReceiveRights()) {
            return;
        }
        if (mReceiverThread != null) {
            throw new IllegalStateException("Different thread has receive rights while trying to acquire receive " +
                    "rights.");
        }
        mReceiverThread = Thread.currentThread();
    }

    public synchronized boolean hasReceiveRights() {
        return mReceiverThread == Thread.currentThread();
    }

    @Override
    public String toString() {
        return "MachPort[" + mName + "]";
    }

    public synchronized void releaseReceiveRights() {
        if (mReceiverThread != Thread.currentThread()) {
            throw new IllegalStateException("Thread has no receive rights while trying to release receive rights.");
        }
        mReceiverThread = null;
    }

    public MachResult receive(MachMessage message) {
        return receive(message, Long.MAX_VALUE, TimeUnit.DAYS);
    }

    public MachResult receive(MachMessage message, long timeout, TimeUnit unit) {
        acquireReceiveRights();
        return waitForMessage(message, timeout, unit);
    }

    private MachResult waitForMessage(MachMessage message, long timeout, TimeUnit unit) {
        try {
            MachMessage incomingMessage = mQueue.poll(timeout, unit);
            if (incomingMessage != null) {
                if (message != null) {
                    incomingMessage.copyTo(message);
                }
                return MachResult.SUCCESS;
            } else {
                return MachResult.RECEIVE_TIMEOUT;
            }
        } catch (InterruptedException e) {
            return MachResult.INTERRUPTED;
        }
    }

    public MachResult send(MachMessage message, long timeout, TimeUnit unit) {
        return onMessageReceived(message, timeout, unit);
    }

    private MachResult onMessageReceived(MachMessage message, long timeout, TimeUnit unit) {
        try {
            if (mQueue.offer(message, timeout, unit)) {
                for (Observer observer : mObservers) {
                    observer.update(this, message);
                }
                return MachResult.SUCCESS;
            } else {
                return MachResult.SEND_TIMEOUT;
            }
        } catch (InterruptedException e) {
            return MachResult.INTERRUPTED;
        }
    }

    public boolean addObserver(Observer observer) {
        return mObservers.add(observer);
    }

    public boolean removeObserver(Observer observer) {
        //Concurrent access 1
        return mObservers.remove(observer);
    }

    public boolean hasMessages() {
        return !mQueue.isEmpty();
    }

    public boolean isValid() {
        return mValid;
    }

    public void invalidate() {
        mValid = false;
    }

    static interface Observer {
        void update(MachPort sourcePort, MachMessage message);
    }
}
