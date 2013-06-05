/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.runtime;

import org.kavaproject.kavatouch.corefoundation.CoreRunLoop;
import org.kavaproject.kavatouch.corefoundation.CoreRunLoopActivity;
import org.kavaproject.kavatouch.corefoundation.CoreRunLoopObserver;
import org.kavaproject.kavatouch.dispatch.DispatchQueue;
import org.kavaproject.kavatouch.foundation.*;
import org.kavaproject.kavatouch.util.OutArg;

import javax.inject.Inject;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class DefaultMethodPerformer implements MethodPerformer {
    private final InvocationFactory mInvocationFactory;
    private final TimerFactory mTimerFactory;
    private final FoundationDateFactory mFoundationDateFactory;
    private final RunLoopFactory mRunLoopFactory;
    private final MethodDispatcher mMethodDispatcher;
    private final MethodSignatureService mMethodSignatureService;
    private ConcurrentMap<Timer, Invocation> mInvocations = new ConcurrentHashMap<Timer, Invocation>();
    private Object mTimerMonitor = new Object();

    @Inject
    protected DefaultMethodPerformer(InvocationFactory invocationFactory, TimerFactory timerFactory,
                                     FoundationDateFactory foundationDateFactory, RunLoopFactory runLoopFactory,
                                     MethodDispatcher methodDispatcher, MethodSignatureService methodSignatureService) {
        mInvocationFactory = invocationFactory;
        mTimerFactory = timerFactory;
        mFoundationDateFactory = foundationDateFactory;
        mRunLoopFactory = runLoopFactory;
        mMethodDispatcher = methodDispatcher;
        mMethodSignatureService = methodSignatureService;
    }

    @Override
    public void performAfterDelay(Object receiver, SEL selector, Object argument, double seconds) {
        performAfterDelay(receiver, selector, argument, seconds, Collections.singletonList(RunLoop.MODE_DEFAULT));
    }

    @Override
    public void performAfterDelay(Object receiver, SEL selector, Object argument, double seconds, List<String> modes) {
        MethodSignature methodSignature = mMethodSignatureService.getMethodSignature(receiver, selector);
        Invocation invocation = mInvocationFactory.createInvocation(methodSignature);
        invocation.setSelector(selector);
        invocation.setArgument(argument, 2);
        invocation.setTarget(receiver);
        final Timer timer = mTimerFactory.create(0, invocation, false);
        timer.setFireDate(mFoundationDateFactory.createWithTimeIntervalSinceNow(seconds));
        CoreRunLoopObserver observer = new CoreRunLoopObserver(CoreRunLoopActivity.ALL_ACTIVITIES, true, 0,
                new CoreRunLoopObserver.Handler() {
            @Override
            public void execute(CoreRunLoopObserver observer, CoreRunLoopActivity activity) {
                if (!timer.isValid()) {
                    mInvocations.remove(timer);
                    observer.invalidate();
                }
            }
        });

        for (String mode : modes) {
            RunLoop nsRunLoop = mRunLoopFactory.currentRunLoop();
            nsRunLoop.toCoreType().addObserver(observer, mode);
            mInvocations.put(timer, invocation);
            nsRunLoop.addTimer(timer, mode);
        }
    }

    @Override
    public void performOnMainThread(Object receiver, SEL selector, Object argument, boolean wait) {
        performOnThread(receiver, selector, DispatchQueue.mainThread, argument, wait);
    }

    @Override
    public void performOnThread(Object receiver, SEL selector, Thread thread, Object argument, boolean wait) {
        performOnThread(receiver, selector, thread, argument, wait, Collections.singletonList(RunLoop.MODES_COMMON));
    }

    @Override
    public void performOnThread(Object receiver, SEL selector, Thread thread, Object argument, boolean wait,
                                List<String> modes) {
        Invocation invocation = mInvocationFactory.createInvocation(mMethodSignatureService.getMethodSignature
                (receiver, selector));
        invocation.setSelector(selector);
        invocation.setArgument(argument, 2);
        invocation.setTarget(receiver);
        final Timer timer = mTimerFactory.create(0, invocation, false);
        timer.setFireDate(mFoundationDateFactory.create());
        CoreRunLoop rl = CoreRunLoop.INTERNAL.getRL(thread);
        CoreRunLoopObserver observer = new CoreRunLoopObserver(CoreRunLoopActivity.ALL_ACTIVITIES, true, 0,
                new CoreRunLoopObserver.Handler() {
            @Override
            public void execute(CoreRunLoopObserver observer, CoreRunLoopActivity activity) {
                if (!timer.isValid()) {
                    synchronized (mTimerMonitor) {
                        mTimerMonitor.notify();
                    }
                    observer.invalidate();
                }
            }
        });
        for (String mode : modes) {
            rl.addObserver(observer, mode);
            rl.addTimer(timer.toCoreType(), mode);
        }
        rl.wakeUp();
        if (wait) {
            synchronized (mTimerMonitor) {
                while (timer.isValid()) {
                    try {
                        mTimerMonitor.wait();
                    } catch (InterruptedException e) {
                    }
                }
            }
        }
    }

    @Override
    public void performOnMainThread(Object receiver, SEL selector, Object argument, boolean wait, List<String> modes) {
        performOnThread(receiver, selector, DispatchQueue.mainThread, argument, wait, modes);
    }

    @Override
    public Object perform(Object receiver, SEL sel) {
        return mMethodDispatcher.send(mInvocationFactory, receiver, sel);
    }

    @Override
    public Object perform(Object receiver, SEL sel, Object arg) {
        return mMethodDispatcher.send(mInvocationFactory, receiver, sel, arg);
    }

    @Override
    public Object perform(Object receiver, SEL sel, Object arg1, Object arg2) {
        return mMethodDispatcher.send(mInvocationFactory, receiver, sel, arg1, arg2);
    }

    @Override
    public void cancelPreviousPerformRequests(Object target) {
        Iterator<Map.Entry<Timer, Invocation>> it = mInvocations.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Timer, Invocation> entry = it.next();
            if (!entry.getValue().target().equals(target)) {
                continue;
            }
            entry.getKey().invalidate();
            it.remove();
        }
    }

    @Override
    public void cancelPreviousPerformRequests(Object target, SEL selector, Object argument) {
        Iterator<Map.Entry<Timer, Invocation>> it = mInvocations.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Timer, Invocation> entry = it.next();
            Invocation invocation = entry.getValue();
            if (!invocation.target().equals(target)) {
                continue;
            }
            if (invocation.selector() != selector) {
                continue;
            }
            OutArg<Object> arg = new OutArg<Object>();
            invocation.getArgument(arg, 3);
            if (argument != null && !argument.equals(arg.value)) {
                continue;
            }
            if (argument == null && arg.value != null) {
                continue;
            }
            entry.getKey().invalidate();
            it.remove();
        }
    }

    @Override
    public boolean resolveFactoryMethod(Factory factory, String selector) {
        return false;
    }
}
