/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.foundation;

import org.kavaproject.kavatouch.corefoundation.CoreRunLoopTimer;
import org.kavaproject.kavatouch.internal.Header;
import org.kavaproject.kavatouch.internal.OccClass;
import org.kavaproject.kavatouch.internal.OccInstanceMethod;
import org.kavaproject.kavatouch.runtime.Creatable;

@Header("NSTimer")
@OccClass("NSTimer")
public interface Timer extends Creatable, CoreBridge<CoreRunLoopTimer> {
    @OccInstanceMethod("fire")
    void fire();

    @OccInstanceMethod("invalidate")
    void invalidate();

    @OccInstanceMethod("isValid")
    boolean isValid();

    @OccInstanceMethod("fireDate")
    FoundationDate fireDate();

    @OccInstanceMethod("setFireDate:")
    void setFireDate(FoundationDate date);

    @OccInstanceMethod("timeInterval")
    double timeInterval();

    @OccInstanceMethod("userInfo")
    Object userInfo();

    @Override
    TimerFactory getFactory();
}
