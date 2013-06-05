/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.foundation;

import org.kavaproject.kavatouch.corefoundation.CoreError;
import org.kavaproject.kavatouch.internal.OccClass;
import org.kavaproject.kavatouch.internal.OccConstant;
import org.kavaproject.kavatouch.internal.OccInstanceMethod;
import org.kavaproject.kavatouch.runtime.Creatable;

import java.util.List;

@OccClass("NSError")
public interface FoundationError extends Coding, Copying, Creatable, CoreBridge<CoreError> {
    @OccConstant("NSPOSIXErrorDomain")
    String DOMAIN_POSIX = CoreError.DOMAIN_POSIX;
    @OccConstant("NSOSStatusErrorDomain")
    String DOMAIN_OS_STATUS = CoreError.DOMAIN_OS_STATUS;
    @OccConstant("NSMachErrorDomain")
    String DOMAIN_NS_MACH = CoreError.DOMAIN_MACH;

    @OccInstanceMethod("code")
    int code();

    @OccInstanceMethod("domain")
    String domain();

    @OccInstanceMethod("userInfo")
    ErrorUserInfo userInfo();

    @OccInstanceMethod("localizedDescription")
    String localizedDescription();

    @OccInstanceMethod("localizedRecoveryOptions")
    List<String> localizedRecoveryOptions();

    @OccInstanceMethod("localizedRecoverySuggestion")
    String localizedRecoverySuggestion();

    @OccInstanceMethod("localizedFailureReason")
    String localizedFailureReason();

    @OccInstanceMethod("recoveryAttempter")
    ErrorRecoveryAttempting recoveryAttempter();

    @OccInstanceMethod("helpAnchor")
    String helpAnchor();

    @Override
    FoundationErrorFactory getFactory();
}
