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
import org.kavaproject.kavatouch.internal.OccClass;
import org.kavaproject.kavatouch.internal.OccClassMethod;
import org.kavaproject.kavatouch.internal.OccInstanceMethod;
import org.kavaproject.kavatouch.runtime.Factory;
import org.kavaproject.kavatouch.runtime.SEL;

@OccClass("NSTimer")
public interface TimerFactory extends Factory, CoreBridgeFactory<CoreRunLoopTimer> {
    @OccClassMethod("scheduledTimerWithTimeInterval:invocation:repeats:")
    Timer schedule(double seconds, Invocation invocation, boolean repeats);

    @OccClassMethod("scheduledTimerWithTimeInterval:target:selector:userInfo:repeats:")
    Timer schedule(double seconds, Object target, SEL selector, Object userInfo, boolean repeats);

    @OccInstanceMethod("initWithFireDate:interval:target:selector:userInfo:repeats:")
    Timer create(FoundationDate date, double seconds, Object target, SEL selector, Object userInfo, boolean repeats);

    @OccClassMethod("timerWithTimeInterval:invocation:repeats:")
    Timer create(double seconds, Invocation invocation, boolean repeats);
}
