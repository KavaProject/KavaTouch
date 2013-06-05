/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.foundation;

import org.kavaproject.kavatouch.runtime.MethodDispatcher;

import javax.inject.Inject;

public class DefaultInvocationFactory implements InvocationFactory {
    private final MethodDispatcher mMethodDispatcher;

    @Inject
    protected DefaultInvocationFactory(MethodDispatcher methodDispatcher) {
        mMethodDispatcher = methodDispatcher;
    }

    @Override
    public Invocation create(Coder decoder) {
        return new DefaultInvocation(decoder, this, mMethodDispatcher);
    }

    @Override
    public <TTarget, TReturn> Invocation<TTarget, TReturn> createInvocation(MethodSignature signature) {
        return new DefaultInvocation<TTarget, TReturn>(signature, new Object[signature.numberOfArguments() - 2],
                this, mMethodDispatcher);
    }
}
