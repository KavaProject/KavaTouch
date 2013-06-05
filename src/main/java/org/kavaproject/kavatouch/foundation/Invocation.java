/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.foundation;

import org.kavaproject.kavatouch.internal.Header;
import org.kavaproject.kavatouch.internal.OccClass;
import org.kavaproject.kavatouch.internal.OccInstanceMethod;
import org.kavaproject.kavatouch.runtime.Creatable;
import org.kavaproject.kavatouch.runtime.SEL;
import org.kavaproject.kavatouch.util.OutArg;

@Header("NSInvocation")
@OccClass("NSInvocation")
public interface Invocation<TTarget, TReturn> extends Coding, Creatable {
    @OccInstanceMethod("setSelector:")
    void setSelector(SEL selector);

    @OccInstanceMethod("selector")
    SEL selector();

    @OccInstanceMethod("target")
    TTarget target();

    @OccInstanceMethod("setArgument:atIndex:")
    void setArgument(Object argument, int index);

    @OccInstanceMethod("getArgument:atIndex:")
    void getArgument(OutArg<Object> argument, int index);

    @OccInstanceMethod("argumentsRetained")
    boolean argumentsRetained();

    @OccInstanceMethod("retainArguments")
    void retainArguments();

    @OccInstanceMethod("getReturnValue:")
    void getReturnValue(OutArg<TReturn> buffer);

    @OccInstanceMethod("invokeWithTarget:")
    void invoke(TTarget target);

    @OccInstanceMethod("setTarget:")
    void setTarget(TTarget anObject);

    @OccInstanceMethod("invoke")
    void invoke();

    @OccInstanceMethod("setReturnValue:")
    void setReturnValue(TReturn buffer);

    @OccInstanceMethod("methodSignature")
    MethodSignature methodSignature();

    @Override
    InvocationFactory getFactory();
}
