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

@Header("CFURLAccess")
@CTypedef("CFURLError")
public class CoreURLException extends RuntimeException {
    private int mCode;

    private CoreURLException(int code) {
        mCode = code;
    }

    @CEnumConstant(value = "kCFURLUnknownError", constantValue = -10)
    public static class UnknownException extends CoreURLException {
        public UnknownException() {
            super(-10);
        }
    }

    @CEnumConstant(value = "kCFURLUnknownSchemeError", constantValue = -11)
    public static class UnknownSchemeException extends CoreURLException {
        public UnknownSchemeException() {
            super(-11);
        }
    }

    @CEnumConstant(value = "kCFURLResourceNotFoundError", constantValue = -12)
    public static class ResourceNotFoundException extends CoreURLException {
        public ResourceNotFoundException() {
            super(-12);
        }
    }

    @CEnumConstant(value = "kCFURLResourceAccessViolationError", constantValue = -13)
    public static class ResourceAccessViolationException extends CoreURLException {
        public ResourceAccessViolationException() {
            super(-13);
        }
    }

    @CEnumConstant(value = "kCFURLRemoteHostUnavailableError", constantValue = -14)
    public static class RemoteHostUnavailableException extends CoreURLException {
        public RemoteHostUnavailableException() {
            super(-14);
        }
    }

    @CEnumConstant(value = "kCFURLImproperArgumentsError", constantValue = -15)
    public static class ImproperArgumentsException extends CoreURLException {
        public ImproperArgumentsException() {
            super(-15);
        }
    }

    @CEnumConstant(value = "kCFURLUnknownPropertyKeyError", constantValue = -16)
    public static class UnknownPropertyKeyException extends CoreURLException {
        public UnknownPropertyKeyException() {
            super(-16);
        }
    }

    @CEnumConstant(value = "kCFURLPropertyKeyUnavailableError", constantValue = -17)
    public static class PropertyKeyUnavailableException extends CoreURLException {
        public PropertyKeyUnavailableException() {
            super(-17);
        }
    }

    @CEnumConstant(value = "kCFURLTimeoutError", constantValue = -18)
    public static class TimeoutException extends CoreURLException {
        public TimeoutException() {
            super(-18);
        }
    }
}
