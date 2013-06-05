/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.foundation;

import org.kavaproject.kavatouch.corefoundation.CoreRunLoop;
import org.kavaproject.kavatouch.runtime.SEL;
import org.kavaproject.kavatouch.util.NotImplementedException;

import java.util.List;

public class DefaultRunLoop implements RunLoop {
    private final FoundationDateFactory mFoundationDateFactory;
    private final RunLoopFactory mRunLoopFactory;
    private final CoreRunLoop mCoreRunLoop;

    protected DefaultRunLoop(CoreRunLoop coreRunLoop, RunLoopFactory runLoopFactory,
                             FoundationDateFactory foundationDateFactory) {
        mFoundationDateFactory = foundationDateFactory;
        mRunLoopFactory = runLoopFactory;
        mCoreRunLoop = coreRunLoop;
    }

    @Override
    public String currentMode() {
        return mCoreRunLoop.copyCurrentMode();
    }

    @Override
    public FoundationDate limitDateForMode(String mode) {
        CoreRunLoop.runInMode(mode, 0, true);
        double nextTimerFireDate = toCoreType().getNextTimerFireDate(mode);
        return mFoundationDateFactory.createWithTimeIntervalSinceReferenceDate(nextTimerFireDate);
    }

    @Override
    public CoreRunLoop toCoreType() {
        return mCoreRunLoop;
    }

    @Override
    public void addTimer(Timer timer, String mode) {
        double fireDate = timer.toCoreType().getNextFireDate();
        if (fireDate == Double.MAX_VALUE) {
            timer.setFireDate(mFoundationDateFactory.createWithTimeIntervalSinceNow(timer.timeInterval()));
        }
        mCoreRunLoop.addTimer(timer.toCoreType(), mode);
    }

    @Override
    public void addPort(Port port, String mode) {
        port.scheduleInRunLoop(this, mode);
    }

    @Override
    public void removePort(Port port, String mode) {
        port.removeFromRunLoop(this, mode);
    }

    @Override
    public final void run() {
        CoreRunLoop.run();
    }

    @Override
    public boolean runUntil(String mode, FoundationDate limitDate) {
        CoreRunLoop.runInMode(mode, limitDate.timeIntervalSinceNow(), true);
        return true;
    }

    @Override
    public void runUntil(FoundationDate limitDate) {
        CoreRunLoop.runInMode(MODE_DEFAULT, limitDate.timeIntervalSinceNow(), false);
    }

    @Override
    public void acceptInputForMode(String mode, FoundationDate limitDate) {
        CoreRunLoop.runInMode(mode, limitDate.timeIntervalSinceNow(), true);
    }

    @Override
    public void perform(SEL selector, Object target, Object argument, int order, List<String> modes) {
        throw new NotImplementedException();
    }

    @Override
    public void cancelPerform(SEL selector, Object target, Object argument) {
        throw new NotImplementedException();
    }

    @Override
    public void cancelPerform(Object target) {
        throw new NotImplementedException();
    }

    @Override
    public RunLoopFactory getFactory() {
        return mRunLoopFactory;
    }
}
