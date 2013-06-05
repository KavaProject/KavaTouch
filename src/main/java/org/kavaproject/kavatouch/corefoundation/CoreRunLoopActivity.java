/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.corefoundation;

import org.kavaproject.kavatouch.internal.CEnumConstant;
import org.kavaproject.kavatouch.internal.CTypedef;
import org.kavaproject.kavatouch.internal.Header;

import java.util.EnumSet;

@Header("CFRunLoop")
@CTypedef("CFRunLoopActivity")
public enum CoreRunLoopActivity {
    @CEnumConstant(value = "kCFRunLoopEntry", constantValue = 1 << 0)
    ENTRY(1 << 0),

    @CEnumConstant(value = "kCFRunLoopBeforeTimers", constantValue = 1 << 1)
    BEFORE_TIMERS(1 << 1),

    @CEnumConstant(value = "kCFRunLoopBeforeSources", constantValue = 1 << 2)
    BEFORE_SOURCES(1 << 2),

    @CEnumConstant(value = "kCFRunLoopBeforeWaiting", constantValue = 1 << 5)
    BEFORE_WAITING(1 << 5),

    @CEnumConstant(value = "kCFRunLoopAfterWaiting", constantValue = 1 << 6)
    AFTER_WAITING(1 << 6),

    @CEnumConstant(value = "kCFRunLoopExit", constantValue = 1 << 7)
    EXIT(1 << 7);
    @CEnumConstant(value = "kCFRunLoopAllActivities", constantValue = 0x0FFFFFFF)
    public static final EnumSet<CoreRunLoopActivity> ALL_ACTIVITIES = EnumSet.allOf(CoreRunLoopActivity.class);
    public final int value;

    CoreRunLoopActivity(int value) {
        this.value = value;
    }
}
