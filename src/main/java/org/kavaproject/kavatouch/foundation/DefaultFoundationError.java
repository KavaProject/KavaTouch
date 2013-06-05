/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.foundation;

import org.kavaproject.kavatouch.corefoundation.CoreError;
import org.kavaproject.kavatouch.util.NotImplementedException;

import java.util.List;

public class DefaultFoundationError implements FoundationError {
    private final DefaultFoundationErrorFactory mFoundationErrorFactory;
    private final CoreError mCoreError;

    protected DefaultFoundationError(CoreError bridgedObject, DefaultFoundationErrorFactory foundationErrorFactory) {
        mFoundationErrorFactory = foundationErrorFactory;
        mCoreError = bridgedObject;
    }

    protected DefaultFoundationError(Coder decoder, DefaultFoundationErrorFactory foundationErrorFactory) {
        mFoundationErrorFactory = foundationErrorFactory;
        mCoreError = null;
        //TODO
    }

    @Override
    public CoreError toCoreType() {
        return mCoreError;
    }

    @Override
    public int code() {
        return mCoreError.getCode();
    }

    @Override
    public String domain() {
        return mCoreError.getDomain();
    }

    @Override
    public ErrorUserInfo userInfo() {
        return new ErrorUserInfo(mCoreError.copyUserInfo(), mFoundationErrorFactory);
    }

    @Override
    public String localizedDescription() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public List<String> localizedRecoveryOptions() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public String localizedRecoverySuggestion() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public String localizedFailureReason() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public ErrorRecoveryAttempting recoveryAttempter() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public String helpAnchor() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public FoundationErrorFactory getFactory() {
        return mFoundationErrorFactory;
    }

    @Override
    public void encode(Coder encoder) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public FoundationError copy() {
        throw new NotImplementedException(); //TODO
    }
}
