/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.runtime;

import org.kavaproject.kavatouch.foundation.Invocation;
import org.kavaproject.kavatouch.foundation.InvocationFactory;
import org.kavaproject.kavatouch.internal.OccInstanceMethod;

public interface MethodDispatcher {
    Object send(InvocationFactory invocationFactory, Object receiver, SEL sel, Object... args);

    @OccInstanceMethod(value = "forwardingTargetForSelector:")
    Object getForwardingTarget(Object receiver, SEL selector);

    @OccInstanceMethod(value = "forwardInvocation:")
    void forwardInvocation(Object receiver, Invocation invocation);

    @OccInstanceMethod(value = "doesNotRecognizeSelector:")
    void doesNotRecognize(Object receiver, SEL selector);
}
