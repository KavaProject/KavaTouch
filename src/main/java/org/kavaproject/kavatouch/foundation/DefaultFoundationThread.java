/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.foundation;

import org.kavaproject.kavatouch.runtime.MethodPerformer;
import org.kavaproject.kavatouch.runtime.SEL;

import java.util.HashMap;
import java.util.Map;

public class DefaultFoundationThread implements FoundationThread {
    private final DefaultFoundationThreadFactory mFoundationThreadFactory;
    private final MethodPerformer mMethodPerformer;
    private Thread mAdaptee;
    private Object mTarget;
    private SEL mSelector;
    private Object mArgument;
    private long mStackSize;
    private String mName;
    private Map mThreadDictionary = new HashMap();
    private double mThreadPriority = -1;
    private volatile boolean mCancelled;

    protected DefaultFoundationThread(Object target, SEL selector, Object argument,
                                      DefaultFoundationThreadFactory foundationThreadFactory,
                                      MethodPerformer methodPerformer) {
        mFoundationThreadFactory = foundationThreadFactory;
        mMethodPerformer = methodPerformer;
        mTarget = target;
        mSelector = selector;
        mArgument = argument;
    }

    protected DefaultFoundationThread(Thread adaptee, DefaultFoundationThreadFactory foundationThreadFactory,
                                      MethodPerformer methodPerformer) {
        this(foundationThreadFactory, methodPerformer);
        mAdaptee = adaptee;
    }

    protected DefaultFoundationThread(DefaultFoundationThreadFactory foundationThreadFactory,
                                      MethodPerformer methodPerformer) {
        this.mFoundationThreadFactory = foundationThreadFactory;
        this.mMethodPerformer = methodPerformer;
    }

    @Override
    public void start() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                main();
            }
        };
        if (mStackSize > 0) {
            if (mName == null) {
                mName = "threadNameUnknown";
            }
            mAdaptee = new Thread(Thread.currentThread().getThreadGroup(), runnable, mName, mStackSize);
        } else if (mName != null) {
            mAdaptee = new Thread(runnable, mName);
        } else {
            mAdaptee = new Thread(runnable);
        }
        if (mThreadPriority >= 0) {
            setJThreadPriority(mThreadPriority);
        }
        mAdaptee.start();
        mFoundationThreadFactory.setMultiThreaded(true);
    }

    private void setJThreadPriority(double priority) {
        int maxPriority = mAdaptee.getThreadGroup().getMaxPriority();
        priority *= maxPriority - Thread.MIN_PRIORITY;
        priority += Thread.MIN_PRIORITY;
        mAdaptee.setPriority((int) priority);
    }

    @Override
    public void main() {
        mMethodPerformer.perform(mTarget, mSelector, mArgument);
    }

    @Override
    public void cancel() {
        mCancelled = true;
    }

    @Override
    public boolean isExecuting() {
        return mAdaptee != null && mAdaptee.isAlive();
    }

    @Override
    public boolean isFinished() {
        return mAdaptee != null && !mAdaptee.isAlive();
    }

    @Override
    public boolean isCancelled() {
        return mCancelled;
    }

    @Override
    public boolean isMainThread() {
        return equals(mFoundationThreadFactory.mainThread());
    }

    @Override
    public Map threadDictionary() {
        return mThreadDictionary;
    }

    @Override
    public String name() {
        if (mAdaptee == null) {
            return mAdaptee.getName();
        } else {
            return mName;
        }
    }

    @Override
    public void setName(String name) {
        if (mAdaptee == null) {
            mName = name;
        } else {
            mAdaptee.setName(name);
        }
    }

    @Override
    public long stackSize() {
        return mStackSize;
    }

    @Override
    public void setStackSize(long size) {
        mStackSize = size;
    }

    @Override
    public double threadPriority() {
        if (mAdaptee == null) {
            return mThreadPriority;
        } else {
            int maxPriority = mAdaptee.getThreadGroup().getMaxPriority();
            double priority = (double) mAdaptee.getPriority();
            priority -= Thread.MIN_PRIORITY;
            priority /= maxPriority - Thread.MIN_PRIORITY;
            return priority;
        }
    }

    @Override
    public void setThreadPriority(double priority) {
        if (mAdaptee == null) {
            mThreadPriority = priority;
        } else {
            setJThreadPriority(priority);
        }
    }

    @Override
    public FoundationThreadFactory getFactory() {
        return mFoundationThreadFactory;
    }
}
