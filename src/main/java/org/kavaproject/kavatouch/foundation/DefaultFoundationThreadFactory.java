/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.foundation;

import org.kavaproject.kavatouch.dispatch.DispatchQueue;
import org.kavaproject.kavatouch.runtime.MethodPerformer;
import org.kavaproject.kavatouch.runtime.SEL;
import org.kavaproject.kavatouch.util.AndroidConversions;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

public class DefaultFoundationThreadFactory implements FoundationThreadFactory {
    private final MethodPerformer mMethodPerformer;
    private boolean mMultiThreaded;
    private Map<Thread, FoundationThread> mThreads = new WeakHashMap<Thread, FoundationThread>();

    @Inject
    protected DefaultFoundationThreadFactory(MethodPerformer methodPerformer) {
        mMethodPerformer = methodPerformer;
    }

    @Override
    public void detachNewThread(SEL selector, Object target, Object argument) {
        create(target, selector, argument).start();
    }

    @Override
    public FoundationThread create(Object target, SEL selector, Object argument) {
        return new DefaultFoundationThread(target, selector, argument, this, mMethodPerformer);
    }

    @Override
    public void sleepUntil(FoundationDate date) {
        sleepFor(date.timeIntervalSinceNow());
    }

    @Override
    public void sleepFor(double seconds) {
        try {
            Thread.sleep(AndroidConversions.toMillisTimeInterval(seconds));
        } catch (InterruptedException e) {
            throw new Error("interrupt() was called for thread while it was sleeping.");
        }
    }

    @Override
    public void exit() {
        Thread.currentThread().stop();
    }

    @Override
    public boolean isMainThread() {
        return currentThread().equals(mainThread());
    }

    @Override
    public FoundationThread mainThread() {
        return create(DispatchQueue.mainThread);
    }

    @Override
    public FoundationThread currentThread() {
        Thread jThread = Thread.currentThread();
        return create(jThread);
    }

    private FoundationThread create(Thread jThread) {
        FoundationThread foundationThread = mThreads.get(jThread);
        if (foundationThread == null) {
            foundationThread = new DefaultFoundationThread(jThread, this, mMethodPerformer);
            mThreads.put(jThread, foundationThread);
        }
        return foundationThread;
    }

    @Override
    public boolean isMultiThreaded() {
        return mMultiThreaded;
    }

    void setMultiThreaded(boolean multiThreaded) {
        mMultiThreaded = multiThreaded;
    }

    @Override
    public List callStackReturnAddresses() {
        return null;
    }

    @Override
    public List callStackSymbols() {
        return Arrays.asList(Thread.currentThread().getStackTrace()); //TODO Format as Strings
    }

    @Override
    public double threadPriority() {
        return currentThread().threadPriority();
    }

    @Override
    public FoundationThread create() {
        return new DefaultFoundationThread(this, mMethodPerformer);
    }

    @Override
    public boolean setThreadPriority(double priority) {
        try {
            currentThread().setThreadPriority(priority);
        } catch (IllegalArgumentException ex) {
            return false;
        }
        return true;
    }
}
