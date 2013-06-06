/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.foundation;

import org.kavaproject.kavatouch.corefoundation.CoreRunLoopTimer;
import org.kavaproject.kavatouch.runtime.MethodSignatureService;
import org.kavaproject.kavatouch.runtime.SEL;

import org.kavaproject.kavatouch.util.inject.Inject;

public class DefaultTimerFactory implements TimerFactory {
    private final FoundationDateFactory mFoundationDateFactory;
    private final RunLoopFactory mRunLoopFactory;
    private final InvocationFactory mInvocationFactory;
    private final MethodSignatureService mMethodSelectionService;

    @Inject
    protected DefaultTimerFactory(FoundationDateFactory foundationDateFactory, RunLoopFactory runLoopFactory,
                                  InvocationFactory invocationFactory, MethodSignatureService methodSignatureService) {
        mFoundationDateFactory = foundationDateFactory;
        mRunLoopFactory = runLoopFactory;
        mInvocationFactory = invocationFactory;
        mMethodSelectionService = methodSignatureService;
    }

    @Override
    public Timer schedule(double seconds, Invocation invocation, boolean repeats) {
        Timer timer = create(mFoundationDateFactory.createWithTimeIntervalSinceNow(seconds), seconds, invocation,
                repeats, null);
        mRunLoopFactory.currentRunLoop().addTimer(timer, RunLoop.MODE_DEFAULT);
        return timer;
    }

    private Timer create(FoundationDate date, double seconds, Invocation invocation, boolean repeats, Object userInfo) {
        double interval = repeats && seconds <= 0 ? 0.0001 : seconds;
        double fireDate = (date == null) ? Double.MAX_VALUE : date.timeIntervalSinceReferenceDate();
        return new DefaultTimer(invocation, userInfo, repeats, interval, fireDate, this, mFoundationDateFactory);
    }

    @Override
    public Timer schedule(double seconds, Object target, SEL selector, Object userInfo, boolean repeats) {
        Timer timer = create(mFoundationDateFactory.createWithTimeIntervalSinceNow(seconds), seconds, target,
                selector, userInfo, repeats);
        mRunLoopFactory.currentRunLoop().addTimer(timer, RunLoop.MODE_DEFAULT);
        return timer;
    }

    @Override
    public Timer create(FoundationDate date, double seconds, Object target, SEL selector, Object userInfo,
                        boolean repeats) {
        Invocation invocation = mInvocationFactory.createInvocation(mMethodSelectionService.getMethodSignature
                (target, selector));
        invocation.setSelector(selector);
        invocation.setArgument(this, 2);
        invocation.setTarget(target);
        return create(date, seconds, invocation, repeats, userInfo);
    }

    @Override
    public Timer create(double seconds, Invocation invocation, boolean repeats) {
        return create(null, seconds, invocation, repeats, null);
    }

    @Override
    public Timer create(CoreRunLoopTimer coreRunLoopTimer) {
        return new DefaultTimer(coreRunLoopTimer, this, mFoundationDateFactory);
    }
}
