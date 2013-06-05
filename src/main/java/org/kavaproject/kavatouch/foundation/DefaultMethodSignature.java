/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.foundation;

public class DefaultMethodSignature implements MethodSignature {
    private final MethodSignatureFactory mMethodSignatureFactory;
    private String[] mTypes;

    protected DefaultMethodSignature(String[] objcTypes, MethodSignatureFactory methodSignatureFactory) {
        mTypes = objcTypes;
        mMethodSignatureFactory = methodSignatureFactory;
    }

    @Override
    public String getArgumentType(int index) {
        return mTypes[index + 1];
    }

    @Override
    public int frameLength() {
        return numberOfArguments();
    }

    @Override
    public int numberOfArguments() {
        return mTypes.length - 1; //The last might be the return type
    }

    @Override
    public String methodReturnType() {
        return mTypes[0];
    }

    @Override
    public int methodReturnLength() {
        return 1;
    }

    @Override
    public boolean isOneway() {
        return false; //TODO
    }

    @Override
    public MethodSignatureFactory getFactory() {
        return mMethodSignatureFactory;
    }
}
