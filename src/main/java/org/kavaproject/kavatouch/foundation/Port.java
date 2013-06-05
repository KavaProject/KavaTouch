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

import java.util.List;

@Header("NSPort")
@OccClass("NSPort")
public interface Port extends Coding, Copying, Creatable {
    @OccConstant("NSPortDidBecomeInvalidNotification")
    String NOTIFICATION_DID_BECOME_INVALID = "NSPortDidBecomeInvalidNotification";

    @OccInstanceMethod("invalidate")
    void invalidate();

    @OccInstanceMethod("isValid")
    boolean isValid();

    @OccInstanceMethod("setDelegate:")
    void setDelegate(PortDelegate delegate);

    @OccInstanceMethod("delegate")
    PortDelegate delegate();

    @OccInstanceMethod("sendBeforeDate:components:from:reserved:")
    boolean send(FoundationDate limitDate, List components, Port receivePort, int reservedHeaderSpace);

    @OccInstanceMethod("sendBeforeDate:msgid:components:from:reserved:")
    boolean send(FoundationDate limitDate, int msgID, List components, Port receivePort, int reservedHeaderSpace);

    @OccInstanceMethod("reservedSpaceLength")
    int reservedSpaceLength();

    @OccInstanceMethod("removeFromRunLoop:forMode:")
    void removeFromRunLoop(RunLoop runLoop, String mode);

    @OccInstanceMethod("scheduleInRunLoop:forMode:")
    void scheduleInRunLoop(RunLoop runLoop, String mode);

    @Override
    PortFactory getFactory();
}
