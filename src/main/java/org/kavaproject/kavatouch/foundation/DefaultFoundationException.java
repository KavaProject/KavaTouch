/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.foundation;

import org.kavaproject.kavatouch.util.NotImplementedException;

public class DefaultFoundationException implements FoundationException {
    private final FoundationExceptionFactory mFoundationExceptionFactory;
    private String mName;
    private String mReason;
    private ExceptionUserInfo mUserInfo;

    protected DefaultFoundationException(String name, String reason, ExceptionUserInfo userInfo,
                                         FoundationExceptionFactory foundationExceptionFactory) {
        mName = name;
        mReason = reason;
        mUserInfo = userInfo;
        mFoundationExceptionFactory = foundationExceptionFactory;
    }

    protected DefaultFoundationException(Coder decoder, FoundationExceptionFactory foundationExceptionFactory) {
        //TODO
        mFoundationExceptionFactory = foundationExceptionFactory;
    }

    @Override
    public RuntimeException getAdaptee() {
        if (mName == EXCEPTION_INVALID_ARGUMENT) {
            return new IllegalArgumentException(mReason);
        } else {
            return new RuntimeException();
        }
    }

    @Override
    public String name() {
        return mName;
    }

    @Override
    public String reason() {
        return mReason;
    }

    @Override
    public ExceptionUserInfo userInfo() {
        return mUserInfo;
    }

    @Override
    public FoundationExceptionFactory getFactory() {
        return mFoundationExceptionFactory;
    }

    @Override
    public void encode(Coder encoder) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public FoundationException copy() {
        throw new NotImplementedException(); //TODO
    }
}
