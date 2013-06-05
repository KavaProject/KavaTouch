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

import java.util.List;

@Header("NSOperation")
@OccClass("NSOperationQueue")
public interface OperationQueue extends Creatable {
    @OccInstanceMethod("addOperation:")
    void addOperation(Operation operation);

    @OccInstanceMethod("addOperations:waitUntilFinished:")
    void addOperations(List<Operation> operations, boolean wait);

    @OccInstanceMethod("addOperationWithBlock:")
    void addOperation(Runnable block);

    @OccInstanceMethod("operations")
    List operations();

    @OccInstanceMethod("operationCount")
    int operationCount();

    @OccInstanceMethod("cancelAllOperations")
    void cancelAllOperations();

    @OccInstanceMethod("waitUntilAllOperationsAreFinished")
    void waitUntilAllOperationsAreFinished();

    @OccInstanceMethod("maxConcurrentOperationCount")
    int maxConcurrentOperationCount();

    @OccInstanceMethod("setMaxConcurrentOperationCount:")
    void setMaxConcurrentOperationCount(int count);

    @OccInstanceMethod("isSuspended")
    boolean isSuspended();

    @OccInstanceMethod("setSuspended:")
    void setSuspended(boolean suspend);

    @OccInstanceMethod("setName:")
    void setName(String newName);

    @OccInstanceMethod("name")
    String name();

    @Override
    OperationQueueFactory getFactory();
}
