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
import org.kavaproject.kavatouch.internal.OccConstant;
import org.kavaproject.kavatouch.internal.OccInstanceMethod;
import org.kavaproject.kavatouch.runtime.Creatable;

import java.util.Map;

@Header("NSThread")
@OccClass("NSThread")
public interface FoundationThread extends Creatable {
    @OccConstant("NSDidBecomeSingleThreadedNotification")
    String NOTIFICATION_DID_BECOME_SINGLE_THREADED = "NSDidBecomeSingleThreadedNotification";
    @OccConstant("NSThreadWillExitNotification")
    String NOTIFICATION_THREAD_WILL_EXIT = "NSThreadWillExitNotification";
    @OccConstant("NSWillBecomeMultiThreadedNotification")
    String NOTIFICATION_WILL_BECOME_MULTI_THREADED = "NSWillBecomeMultiThreadedNotification";

    @OccInstanceMethod("start")
    void start();

    @OccInstanceMethod("main")
    void main();

    @OccInstanceMethod("cancel")
    void cancel();

    @OccInstanceMethod("isExecuting")
    boolean isExecuting();

    @OccInstanceMethod("isFinished")
    boolean isFinished();

    @OccInstanceMethod("isCancelled")
    boolean isCancelled();

    @OccInstanceMethod("isMainThread")
    boolean isMainThread();

    @OccInstanceMethod("threadDictionary")
    Map threadDictionary();

    @OccInstanceMethod("name")
    String name();

    @OccInstanceMethod("setName")
    void setName(String name);

    @OccInstanceMethod("stackSize")
    long stackSize();

    @OccInstanceMethod("setStackSize")
    void setStackSize(long size);

    @OccInstanceMethod("threadPriority")
    double threadPriority();

    @OccInstanceMethod("setThreadPriority:")
    void setThreadPriority(double priority);

    @Override
    FoundationThreadFactory getFactory();
}
