/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.runtime;

import org.kavaproject.kavatouch.foundation.*;
import org.kavaproject.kavatouch.util.OutArg;

import javax.inject.Inject;

public class DefaultMethodDispatcher implements MethodDispatcher {
    private final MethodResolver mMethodResolver;
    private final FactoryRegistry mFactoryRegistry;
    private final FoundationExceptionFactory mFoundationExceptionFactory;
    private final MethodSignatureFactory mMethodSignatureFactory;

    @Inject
    public DefaultMethodDispatcher(MethodResolver methodResolver, FactoryRegistry factoryRegistry,
                                   FoundationExceptionFactory foundationExceptionFactory,
                                   MethodSignatureFactory methodSignatureFactory) {
        mMethodResolver = methodResolver;
        mFactoryRegistry = factoryRegistry;
        mFoundationExceptionFactory = foundationExceptionFactory;
        mMethodSignatureFactory = methodSignatureFactory;
    }

    @Override
    public Object send(InvocationFactory invocationFactory, Object receiver, SEL sel, Object... args) {
        IMP imp;
        while (true) {
            imp = mMethodResolver.findMethod(receiver, sel);
            if (imp != null) {
                break;
            }
            Factory cls = mFactoryRegistry.getFactory(receiver);
            if (!mMethodResolver.resolveInstanceMethod(cls, sel)) {
                Object forwardingTarget = getForwardingTarget(receiver, sel);
                if (forwardingTarget == null) {
                    break;
                } else {
                    return send(invocationFactory, forwardingTarget, sel, args);
                }
            }
        }
        if (imp == null) {
            MethodSignature signature = mMethodSignatureFactory.create(imp.encode());
            Invocation invocation = invocationFactory.createInvocation(signature);
            forwardInvocation(receiver, invocation);
            OutArg result = new OutArg();
            invocation.getReturnValue(result);
            return result.value;
        } else {
            return imp.invoke(receiver, sel, args);
        }
    }

    @Override
    public Object getForwardingTarget(Object receiver, SEL selector) {
        return null;
    }

    @Override
    public void forwardInvocation(Object receiver, Invocation invocation) {
        doesNotRecognize(receiver, invocation.selector());
    }

    @Override
    public void doesNotRecognize(Object receiver, SEL selector) {
        throw mFoundationExceptionFactory.getAdaptee(FoundationException.EXCEPTION_INVALID_ARGUMENT, "Does not recognize selector.");
    }
}
