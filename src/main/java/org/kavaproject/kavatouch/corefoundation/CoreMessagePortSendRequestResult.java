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

@Header("CFMessagePort")
@CTypedef("CFMessagePortSendRequestErrorCode")
public enum CoreMessagePortSendRequestResult {
    @CEnumConstant(value = "kCFMessagePortSuccess", constantValue = 0)
    SUCCESS(0),

    @CEnumConstant(value = "kCFMessagePortSendTimeout", constantValue = -1)
    SEND_TIMEOUT(-1),

    @CEnumConstant(value = "kCFMessagePortReceiveTimeout", constantValue = -2)
    RECEIVE_TIMEOUT(-2),

    @CEnumConstant(value = "kCFMessagePortIsInvalid", constantValue = -3)
    PORT_IS_INVALID(-3),

    @CEnumConstant(value = "kCFMessagePortTransportError", constantValue = -4)
    TRANSPORT_ERROR(-4),

    @CEnumConstant(value = "kCFMessagePortBecameInvalidError", constantValue = -5)
    PORT_BECAME_INVALID_ERROR(-5);
    public final int value;

    CoreMessagePortSendRequestResult(int value) {

        this.value = value;
    }
}
