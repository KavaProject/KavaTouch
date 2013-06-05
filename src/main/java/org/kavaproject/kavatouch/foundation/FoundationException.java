/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.foundation;

import org.kavaproject.kavatouch.internal.OccClass;
import org.kavaproject.kavatouch.internal.OccConstant;
import org.kavaproject.kavatouch.internal.OccInstanceMethod;
import org.kavaproject.kavatouch.runtime.Creatable;

@OccClass("NSException")
public interface FoundationException extends Coding, Copying, Creatable {
    @OccConstant("NSGenericException")
    String EXCEPTION_GENERIC = "NSGenericException";
    @OccConstant("NSRangeException")
    String EXCEPTION_RANGE = "NSRangeException";
    @OccConstant("NSInvalidArgumentException")
    String EXCEPTION_INVALID_ARGUMENT = "NSInvalidArgumentException";
    @OccConstant("NSInternalInconsistencyException")
    String EXCEPTION_INTERNAL_INCONSISTENCY = "NSInternalInconsistencyException";
    @OccConstant("NSMallocException")
    String EXCEPTION_MALLOC = "NSMallocException";
    @OccConstant("NSObjectInaccessibleException")
    String EXCEPTION_OBJECT_INACCESSIBLE = "NSObjectInaccessibleException";
    @OccConstant("NSObjectNotAvailableException")
    String EXCEPTION_OBJECT_NOT_AVAILABLE = "NSObjectNotAvailableException";
    @OccConstant("NSDestinationInvalidException")
    String EXCEPTION_DESTINATION_INVALID = "NSDestinationInvalidException";
    @OccConstant("NSPortTimeoutException")
    String EXCEPTION_PORT_TIMEOUT = "NSPortTimeoutException";
    @OccConstant("NSInvalidSendPortException")
    String EXCEPTION_INVALID_SEND_PORT = "NSInvalidSendPortException";
    @OccConstant("NSInvalidReceivePortException")
    String EXCEPTION_INVALID_RECEIVE_PORT = "NSInvalidReceivePortException";
    @OccConstant("NSPortSendException")
    String EXCEPTION_PORT_SEND = "NSPortSendException";
    @OccConstant("NSPortReceiveException")
    String EXCEPTION_PORT_RECEIVE = "NSPortReceiveException";
    @OccConstant("NSOldStyleException")
    String EXCEPTION_OLD_STYLE = "NSOldStyleException";

    @OccInstanceMethod("raise")
    RuntimeException getAdaptee();

    @OccInstanceMethod("name")
    String name();

    @OccInstanceMethod("reason")
    String reason();

    @OccInstanceMethod("userInfo")
    ExceptionUserInfo userInfo();

    @Override
    FoundationExceptionFactory getFactory();
}
