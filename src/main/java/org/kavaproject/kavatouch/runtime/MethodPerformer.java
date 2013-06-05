/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.runtime;

import org.kavaproject.kavatouch.internal.OccClassMethod;
import org.kavaproject.kavatouch.internal.OccInstanceMethod;
import org.kavaproject.kavatouch.internal.OccProtocolInstanceMethod;

import java.util.List;

public interface MethodPerformer {
    @OccInstanceMethod(value = "performSelector:withObject:afterDelay:")
    void performAfterDelay(Object receiver, SEL selector, Object argument, double seconds);

    @OccInstanceMethod(value = "performSelector:withObject:afterDelay:inModes:")
    void performAfterDelay(Object receiver, SEL selector, Object argument, double seconds, List<String> modes);

    @OccInstanceMethod(value = "performSelectorOnMainThread:withObject:waitUntilDone:")
    void performOnMainThread(Object receiver, SEL selector, Object argument, boolean wait);

    @OccInstanceMethod(value = "performSelector:OnThread:WithObject:WaitUntilDone")
    void performOnThread(Object receiver, SEL selector, Thread thread, Object argument, boolean wait);

    @OccInstanceMethod(value = "performSelector:onThread:withObject:waitUntilDone:modes:")
    void performOnThread(Object receiver, SEL selector, Thread thread, Object argument, boolean wait,
                         List<String> modes);

    @OccInstanceMethod(value = "performSelectorOnMainThread:withObject:waitUntilDone:modes:")
    void performOnMainThread(Object receiver, SEL selector, Object argument, boolean wait, List<String> modes);

    @OccProtocolInstanceMethod("performSelector:")
    Object perform(Object receiver, SEL sel);

    @OccProtocolInstanceMethod("performSelector:withObject:")
    Object perform(Object receiver, SEL sel, Object arg);

    @OccProtocolInstanceMethod("performSelector:withObject:withObject:")
    Object perform(Object receiver, SEL sel, Object arg1, Object arg2);

    @OccClassMethod(value = "cancelPreviousPerformRequestsWithTarget:")
    void cancelPreviousPerformRequests(Object target);

    @OccClassMethod(value = "cancelPreviousPerformRequestsWithTarget:selector:object:")
    void cancelPreviousPerformRequests(Object target, SEL selector, Object argument);

    @OccClassMethod(value = "resolveClassMethod:")
    boolean resolveFactoryMethod(Factory factory, String selector);
}
