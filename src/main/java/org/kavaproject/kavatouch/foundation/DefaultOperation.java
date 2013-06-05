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

public class DefaultOperation implements Operation {
    private final OperationFactory mOperationFactory;

    protected DefaultOperation(OperationFactory operationFactory) {
        mOperationFactory = operationFactory;
    }

    @Override
    public void start() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void main() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public CompletionBlock completionBlock() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void setCompletionBlock(CompletionBlock block) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void cancel() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public boolean isCancelled() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public boolean isExecuting() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public boolean isFinished() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public boolean isConcurrent() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public boolean isReady() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void addDependency(Operation operation) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void removeDependency(Operation operation) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public List dependencies() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public OperationQueuePriority queuePriority() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void setQueuePriority(OperationQueuePriority priority) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public double threadPriority() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void setThreadPriority(double priority) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void waitUntilFinished() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public OperationFactory getFactory() {
        return mOperationFactory;
    }
}
