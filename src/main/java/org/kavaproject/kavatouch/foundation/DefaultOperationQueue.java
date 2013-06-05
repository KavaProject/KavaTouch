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

import java.util.List;

public class DefaultOperationQueue implements OperationQueue {
    private final OperationQueueFactory mOperationQueueFactory;

    protected DefaultOperationQueue(OperationQueueFactory operationQueueFactory) {
        mOperationQueueFactory = operationQueueFactory;
    }

    @Override
    public void addOperation(Operation operation) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void addOperations(List<Operation> operations, boolean wait) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void addOperation(Runnable block) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public List operations() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public int operationCount() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void cancelAllOperations() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void waitUntilAllOperationsAreFinished() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public int maxConcurrentOperationCount() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void setMaxConcurrentOperationCount(int count) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public boolean isSuspended() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void setSuspended(boolean suspend) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void setName(String newName) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public String name() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public OperationQueueFactory getFactory() {
        return mOperationQueueFactory;
    }
}
