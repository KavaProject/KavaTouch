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

import org.kavaproject.kavatouch.util.inject.Inject;
import java.util.HashMap;
import java.util.Map;

public class DefaultRunLoopFactory implements RunLoopFactory {
    private final FoundationDateFactory mFoundationDateFactory;
    private Map<CoreRunLoop, RunLoop> mRunLoops = new HashMap<CoreRunLoop, RunLoop>();

    @Inject
    protected DefaultRunLoopFactory(FoundationDateFactory foundationDateFactory) {
        mFoundationDateFactory = foundationDateFactory;
    }

    @Override
    public RunLoop currentRunLoop() {
        return create(CoreRunLoop.getCurrent());
    }

    @Override
    public RunLoop mainRunLoop() {
        return create(CoreRunLoop.getMain());
    }

    @Override
    public RunLoop create(CoreRunLoop coreRunLoop) {
        RunLoop runLoop = mRunLoops.get(coreRunLoop);
        if (runLoop == null) {
            runLoop = new DefaultRunLoop(coreRunLoop, this, mFoundationDateFactory);
            //Activated during scrolling. E.g. NSConnect is not scheduled in this mode by default.
            coreRunLoop.addCommonMode(RunLoop.MODE_EVENT_TRACKING);
            mRunLoops.put(coreRunLoop, runLoop);
        }
        return runLoop;
    }
}
