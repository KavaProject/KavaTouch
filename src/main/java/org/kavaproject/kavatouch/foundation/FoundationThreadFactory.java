/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.foundation;

import org.kavaproject.kavatouch.internal.OccClass;
import org.kavaproject.kavatouch.internal.OccClassMethod;
import org.kavaproject.kavatouch.internal.OccInstanceMethod;
import org.kavaproject.kavatouch.runtime.Factory;
import org.kavaproject.kavatouch.runtime.SEL;

import java.util.List;

@OccClass("NSThread")
public interface FoundationThreadFactory extends Factory {
    @OccClassMethod("detachNewThreadSelector:toTarget:withObject:")
    void detachNewThread(SEL selector, Object target, Object argument);

    @OccInstanceMethod("initWithTarget:selector:object:")
    FoundationThread create(Object target, SEL selector, Object argument);

    @OccClassMethod("sleepUntilDate:")
    void sleepUntil(FoundationDate date);

    @OccClassMethod("sleepForTimeInterval:")
    void sleepFor(double seconds);

    @OccClassMethod("exit")
    void exit();

    @OccClassMethod("isMainThread")
    boolean isMainThread();

    @OccClassMethod("mainThread")
    FoundationThread mainThread();

    @OccClassMethod("currentThread")
    FoundationThread currentThread();

    @OccClassMethod("isMultiThreaded")
    boolean isMultiThreaded();

    @OccClassMethod("callStackReturnAddresses")
    List callStackReturnAddresses();

    @OccClassMethod("callStackSymbols")
    List callStackSymbols();

    @OccClassMethod("threadPriority")
    double threadPriority();

    FoundationThread create();

    @OccClassMethod("setThreadPriority:")
    boolean setThreadPriority(double priority);
}
