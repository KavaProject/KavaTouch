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
@OccClass("NSOperation")
public interface Operation extends Creatable {
    @OccInstanceMethod("start")
    void start();

    @OccInstanceMethod("main")
    void main();

    @OccInstanceMethod("completionBlock")
    CompletionBlock completionBlock();

    @OccInstanceMethod("setCompletionBlock:")
    void setCompletionBlock(CompletionBlock block);

    @OccInstanceMethod("cancel")
    void cancel();

    @OccInstanceMethod("isCancelled")
    boolean isCancelled();

    @OccInstanceMethod("isExecuting")
    boolean isExecuting();

    @OccInstanceMethod("isFinished")
    boolean isFinished();

    @OccInstanceMethod("isConcurrent")
    boolean isConcurrent();

    @OccInstanceMethod("isReady")
    boolean isReady();

    @OccInstanceMethod("addDependency:")
    void addDependency(Operation operation);

    @OccInstanceMethod("removeDependency:")
    void removeDependency(Operation operation);

    @OccInstanceMethod("dependencies")
    List dependencies();

    @OccInstanceMethod("queuePriority")
    OperationQueuePriority queuePriority();

    @OccInstanceMethod("setQueuePriority:")
    void setQueuePriority(OperationQueuePriority priority);

    @OccInstanceMethod("threadPriority")
    double threadPriority();

    @OccInstanceMethod("setThreadPriority:")
    void setThreadPriority(double priority);

    @OccInstanceMethod("waitUntilFinished")
    void waitUntilFinished();

    @Override
    OperationFactory getFactory();

    public interface CompletionBlock {
        void invoke();
    }
}
