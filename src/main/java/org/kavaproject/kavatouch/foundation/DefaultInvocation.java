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
import org.kavaproject.kavatouch.runtime.SEL;
import org.kavaproject.kavatouch.util.NotImplementedException;
import org.kavaproject.kavatouch.util.OutArg;

public class DefaultInvocation<TTarget, TReturn> implements Invocation<TTarget, TReturn> {
    private final MethodDispatcher mMethodDispatcher;
    private final InvocationFactory mInvocationFactory;
    private MethodSignature mSignature;
    private Object[] mArguments;
    private boolean mArgumentsRetained;
    private TReturn mReturnValue;
    private Object mReceiver;
    private SEL mSelector;

    protected DefaultInvocation(MethodSignature signature, Object[] arguments, InvocationFactory invocationFactory,
                                MethodDispatcher methodDispatcher) {
        mMethodDispatcher = methodDispatcher;
        mInvocationFactory = invocationFactory;
        mSignature = signature;
        mArguments = arguments;
    }

    protected DefaultInvocation(Coder decoder, InvocationFactory invocationFactory, MethodDispatcher methodDispatcher) {
        mMethodDispatcher = methodDispatcher;
        mInvocationFactory = invocationFactory;
        //TODO
    }

    @Override
    public void setSelector(SEL selector) {
        mSelector = selector;
    }

    @Override
    public SEL selector() {
        return mSelector;
    }

    @Override
    public TTarget target() {
        return (TTarget) mReceiver;
    }

    @Override
    public void setArgument(Object argument, int index) {
        if (index == 0) {
            mReceiver = argument;
        } else if (index == 1) {
            mSelector = (SEL) argument;
        } else {
            mArguments[index - 2] = argument;
        }
    }

    @Override
    public void getArgument(OutArg<Object> argument, int index) {
        if (index == 0) {
            argument.value = mReceiver;
        } else if (index == 1) {
            argument.value = mSelector;
        } else {
            argument.value = mArguments[index - 2];
        }
    }

    @Override
    public boolean argumentsRetained() {
        return mArgumentsRetained;
    }

    @Override
    public void retainArguments() {
        mArgumentsRetained = true;
    }

    @Override
    public void getReturnValue(OutArg<TReturn> buffer) {
        buffer.value = mReturnValue;
    }

    @Override
    public void invoke(TTarget target) {
        setTarget(target);
        invoke();
    }

    @Override
    public void setTarget(TTarget anObject) {
        mReceiver = anObject;
    }

    @Override
    public void invoke() {
        setReturnValue((TReturn) mMethodDispatcher.send(getFactory(), mReceiver, mSelector, mArguments));
    }

    @Override
    public void setReturnValue(TReturn buffer) {
        mReturnValue = buffer;
    }

    @Override
    public MethodSignature methodSignature() {
        return mSignature;
    }

    @Override
    public InvocationFactory getFactory() {
        return mInvocationFactory;
    }

    @Override
    public void encode(Coder encoder) {
        throw new NotImplementedException();  //TODO
    }
}
