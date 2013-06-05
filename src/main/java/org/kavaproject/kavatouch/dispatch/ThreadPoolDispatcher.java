/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.dispatch;

import java.util.HashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

class ThreadPoolDispatcher implements DispatchSink {
    final DispatchQueue highPriorityOvercommitQueue = new DispatchQueue(this, false,
            "com.apple.root.high-overcommit-priority");
    final DispatchQueue highPriorityQueue = new DispatchQueue(this, false, "com.apple.root.high-priority");
    final DispatchQueue defaultPriorityOvercommitQueue = new DispatchQueue(this, false,
            "com.apple.root.default-overcommit-priority");
    final DispatchQueue defaultPriorityQueue = new DispatchQueue(this, false, "com.apple.root.default-priority");
    final DispatchQueue lowPriorityOvercommitQueue = new DispatchQueue(this, false,
            "com.apple.root.low-overcommit-priority");
    final DispatchQueue lowPriorityQueue = new DispatchQueue(this, false, "com.apple.root.low-priority");
    final DispatchQueue bgPriorityOvercommitQueue = new DispatchQueue(this, false,
            "com.apple.root.background-overcommit-priority");
    final DispatchQueue bgPriorityQueue = new DispatchQueue(this, false, "com.apple.root.background-priority");
    private final ConcurrentLinkedQueue<Thread> mWaitingThreads = new ConcurrentLinkedQueue<Thread>();
    private final HashMap<Thread, DispatchQueueItem> mPendingItems = new HashMap<Thread, DispatchQueueItem>();
    private final HashMap<DispatchQueueItem, Integer> mThreadPriority = new HashMap<DispatchQueueItem, Integer>();
    private final Thread[] mThreads;

    public ThreadPoolDispatcher(int threadCount) {
        mThreadPriority.put(highPriorityOvercommitQueue, android.os.Process.THREAD_PRIORITY_DEFAULT);
        mThreadPriority.put(highPriorityQueue, android.os.Process.THREAD_PRIORITY_DEFAULT);
        mThreadPriority.put(defaultPriorityOvercommitQueue, android.os.Process.THREAD_PRIORITY_DEFAULT);
        mThreadPriority.put(defaultPriorityQueue, android.os.Process.THREAD_PRIORITY_DEFAULT);
        mThreadPriority.put(lowPriorityOvercommitQueue, android.os.Process.THREAD_PRIORITY_DEFAULT);
        mThreadPriority.put(lowPriorityQueue, android.os.Process.THREAD_PRIORITY_DEFAULT);
        mThreadPriority.put(bgPriorityOvercommitQueue, android.os.Process.THREAD_PRIORITY_BACKGROUND);
        mThreadPriority.put(bgPriorityQueue, android.os.Process.THREAD_PRIORITY_BACKGROUND);
        mThreads = new Thread[threadCount];
        for (int i = 0; i < threadCount; i++) {
            mThreads[i] = createWorkerThread(i);
        }
    }

    private Thread createWorkerThread(final int index) {
        return new Thread(new Runnable() {
            @Override
            public void run() {
                Thread thread = Thread.currentThread();
                while (true) {
                    DispatchQueueItem item = mPendingItems.get(thread);
                    if (item != null && execute(item)) {
                        continue;
                    }
                    if (execute(highPriorityOvercommitQueue) || execute(highPriorityQueue) || execute
                            (defaultPriorityOvercommitQueue) || execute(defaultPriorityQueue) || execute
                            (lowPriorityOvercommitQueue) || execute(lowPriorityQueue) || execute
                            (bgPriorityOvercommitQueue) || execute(bgPriorityQueue)) {
                        continue;
                    }
                    if (index >= 0) {
                        synchronized (thread) {
                            mWaitingThreads.add(thread);
                            try {
                                thread.wait();
                            } catch (InterruptedException e) {
                            }
                        }
                    }
                }
            }
        }, "gcd_thread_" + index);
    }

    private boolean execute(DispatchQueueItem item) {
        if (!item.canExecute()) {
            return false;
        }
        android.os.Process.setThreadPriority(mThreadPriority.get(item));
        boolean res = item.executeNextBlock();
        android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_DEFAULT);
        return res;
    }

    @Override
    public void push(DispatchQueueItem item) {
        Thread thread = mWaitingThreads.poll();
        if (thread != null) {
            mPendingItems.put(thread, item);
            synchronized (thread) {
                thread.notify();
            }
            return;
        }
        for (int i = 0; i < mThreads.length; i++) {
            thread = mThreads[i];
            switch (thread.getState()) {
                case NEW:
                    mPendingItems.put(thread, item);
                    thread.start();
                    return;
                case WAITING:
                case BLOCKED:
                case TIMED_WAITING:
                    thread = createWorkerThread(-1);
                    mPendingItems.put(thread, item);
                    thread.start();
                    return;
                case TERMINATED:
                    //TODO restart
                    break;
            }
        }
        if (item == highPriorityOvercommitQueue || item == defaultPriorityOvercommitQueue || item ==
                lowPriorityOvercommitQueue || item == bgPriorityOvercommitQueue) {
            thread = createWorkerThread(-1);
            mPendingItems.put(thread, item);
            thread.start();
            return;
        }
    }
}
