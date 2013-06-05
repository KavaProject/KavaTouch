/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.dispatch;

import android.os.Handler;
import org.kavaproject.kavatouch.dispatch.staging.DispatchOnce;
import org.kavaproject.kavatouch.internal.CFunction;
import org.kavaproject.kavatouch.internal.CTypedef;
import org.kavaproject.kavatouch.internal.Header;
import org.kavaproject.kavatouch.util.NotImplementedException;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Header("queue")
@CTypedef("dispatch_queue_t")
public class DispatchQueue extends DispatchObject implements DispatchQueueItem, DispatchSink {
    public static Handler timerHandler;
    public static Thread mainThread;
    private static DispatchQueue sMainQueue;
    private static ThreadPoolDispatcher sDispatcher;
    private static Handler mainHandler;
    final Map contexts = new HashMap();
    final Map<Object, DispatchFunction> destructors = new HashMap<Object, DispatchFunction>();
    private final AtomicInteger mSuspendCount = new AtomicInteger();
    private final AtomicInteger mBarrierCounter = new AtomicInteger();
    private final ConcurrentLinkedQueue<DispatchQueueItem> mQueues = new ConcurrentLinkedQueue<DispatchQueueItem>();
    private final boolean mSerial;
    volatile String label;
    private volatile int mFinishedBarrier = 1;
    private volatile DispatchSink mTargetQueue;

    @CFunction("dispatch_queue_create")
    public DispatchQueue(String label, DispatchQueueType attr) {
        this(sDispatcher.defaultPriorityOvercommitQueue, attr != DispatchQueueType.CONCURRENT, label);
    }


    DispatchQueue(DispatchSink targetQueue, boolean serial, String label) {
        mTargetQueue = targetQueue;
        mSerial = serial;
        this.label = label;
    }

    public static void init(Handler timerHandler, Handler mainHandler, Thread mainThread) {
        DispatchQueue.timerHandler = timerHandler;
        DispatchQueue.mainHandler = mainHandler;
        DispatchQueue.mainThread = mainThread;
        sMainQueue = new DispatchQueue(null, true, "dispatch_queue_main");
        sDispatcher = new ThreadPoolDispatcher(Runtime.getRuntime().availableProcessors() * 4);
    }

    @CFunction("dispatch_get_global_queue")
    public static final DispatchQueue getGlobalQueue(DispatchQueuePriority priority) {
        switch (priority) {
            case LOW:
                return sDispatcher.lowPriorityQueue;
            case DEFAULT:
                return sDispatcher.defaultPriorityQueue;
            case HIGH:
                return sDispatcher.highPriorityQueue;
            case BACKGROUND:
                return sDispatcher.bgPriorityQueue;
            default:
                throw new IllegalArgumentException();
        }
    }

    @CFunction("dispatch_get_main_queue")
    public static final DispatchQueue getMainQueue() {
        return sMainQueue;
    }

    @CFunction("dispatch_main")
    public static final void main() {
        DispatchSink sink = new DispatchSink() {
            @Override
            public void push(final DispatchQueueItem item) {
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        while (item.executeNextBlock()) {
                            //empty
                        }
                    }
                });
            }
        };
        sMainQueue.setTargetQueue(sink);
        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                while (sMainQueue.executeNextBlock()) {
                    //empty
                }
            }
        });
    }

    public void setTargetQueue(DispatchSink queue) {
        mTargetQueue = queue;
    }

    @Override
    public boolean executeNextBlock() {
        if (mSuspendCount.get() > 0) {
            return false;
        }
        boolean res = false;
        Iterator<DispatchQueueItem> it = mQueues.iterator();
        while (it.hasNext()) {
            DispatchQueueItem item = it.next();
            if (item.isEmpty()) {
                it.remove();
                continue;
            }
            res = item.executeNextBlock();
            if (res || mSerial) {
                break;
            }
        }
        return res;
    }

    @Override
    public boolean canExecute() {
        return !isEmpty();
    }

    @Override
    public boolean isEmpty() {
        return mQueues.isEmpty();
    }

    @CFunction("dispatch_once")
    public static final void dispatchOnce(DispatchOnce predicate, Runnable block) {
        throw new NotImplementedException();
    }

    @CFunction("dispatch_get_specific")
    public static Object getSpecificForCurrentQueue(Object key) {
        DispatchQueue queue = getCurrentQueue();
        return queue.getSpecific(key);
    }

    @CFunction("dispatch_queue_get_specific")
    public Object getSpecific(Object key) {
        return contexts.get(key);
    }

    @CFunction("dispatch_get_current_queue")
    public static final DispatchQueue getCurrentQueue() {
        DispatchQueue dq = DispatchBlock.currentQueue.get();
        if (dq != null) {
            return dq;
        } else if (mainThread == Thread.currentThread()) {
            return sMainQueue;
        } else {
            return sDispatcher.defaultPriorityQueue;
        }
    }

    @CFunction("dispatch_queue_set_specific")
    public void setSpecific(Object key, Object context, DispatchFunction destructor) {
        contexts.put(key, context);
        destructors.put(key, destructor);
    }

    @CFunction("dispatch_barrier_async")
    public void dispatchBarrierAsync(DispatchBlock block) {
        throw new NotImplementedException();
    }

    @CFunction("dispatch_after")
    public void dispatchAfter(DispatchTime when, final DispatchBlock block) {
        Runnable callback = new Runnable() {
            @Override
            public void run() {
                dispatchAsync(block);
            }
        };
        timerHandler.postDelayed(callback, TimeUnit.MILLISECONDS.convert(when.remainingTime(), when.unit()));
    }

    @CFunction("dispatch_async")
    public void dispatchAsync(DispatchBlock block) {
        dispatch(block, null);
    }

    void dispatch(DispatchBlock block, Semaphore semaphore) {
        block.waitingOnBarrier = mBarrierCounter.get();
        block.queue = this;
        block.executionFinishedSemaphore = semaphore;
        push(block);
    }

    @CFunction("dispatch_sync")
    public void dispatchSync(DispatchBlock block) {
        Semaphore sema = new Semaphore(0);
        dispatch(block, sema);
        sema.acquireUninterruptibly();
    }

    @CFunction("dispatch_queue_get_label")
    public String getLabel() {
        return label;
    }

    @CFunction("dispatch_apply")
    public void dispatchApply(int iterations, DispatchBlock block) {
        block.iterations = iterations;
        dispatch(block, null);
    }

    @CFunction("dispatch_resume")
    public void resume() {
        int i = mSuspendCount.decrementAndGet();
        if (i == 0) {
            DispatchQueueItem item = mQueues.peek();
            if (item != null && item.canExecute()) {
                pushToTargetQueue();
            }
        }
    }

    @CFunction("dispatch_suspend")
    public void suspend() {
        int i = mSuspendCount.incrementAndGet();
    }

    @Override
    public void push(DispatchQueueItem item) {
        if (!mQueues.contains(item)) {
            mQueues.add(item);
            pushToTargetQueue();
        }
    }

    @CFunction("dispatch_barrier_sync")
    public void dispatchBarrierSync(DispatchBlock block) {
        Semaphore semaphore = new Semaphore(0);
        block.isBarrier = true;
        dispatch(block, semaphore);
        mBarrierCounter.incrementAndGet();
        semaphore.acquireUninterruptibly();
    }

    public int getFinishedBarrier() {
        return mFinishedBarrier;
    }

    void onBarrierPassed() {
        mFinishedBarrier++;
    }


    private void pushToTargetQueue() {
        DispatchSink targetQueue = mTargetQueue;
        if (targetQueue != null) {
            targetQueue.push(this);
        }
    }


}
