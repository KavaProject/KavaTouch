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

@Header("NSPortMessage")
@OccClass(value = "NSPortMessage", osxOnly = true)
public interface PortMessage extends Creatable {
    @OccInstanceMethod("sendBeforeDate:")
    boolean sendBefore(FoundationDate date);

    @OccInstanceMethod("components")
    List components();

    @OccInstanceMethod("receivePort")
    Port receivePort();

    @OccInstanceMethod("sendPort")
    Port sendPort();

    @OccInstanceMethod("setMsgid:")
    void setMsgid(int msgid);

    @OccInstanceMethod("msgid")
    int msgid();

    @Override
    PortMessageFactory getFactory();
}
