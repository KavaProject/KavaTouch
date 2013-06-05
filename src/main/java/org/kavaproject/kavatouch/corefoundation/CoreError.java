/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.corefoundation;

import org.kavaproject.kavatouch.internal.CData;
import org.kavaproject.kavatouch.internal.CFunction;
import org.kavaproject.kavatouch.internal.CTypedef;
import org.kavaproject.kavatouch.util.NotImplementedException;

@CTypedef("CFError")
public class CoreError implements CoreType {
    @CData("kCFErrorDomainPOSIX")
    public static final String DOMAIN_POSIX = "kCFErrorDomainPOSIX";
    @CData("kCFErrorDomainOSStatus")
    public static final String DOMAIN_OS_STATUS = "kCFErrorDomainOSStatus";
    @CData("kCFErrorDomainMach")
    public static final String DOMAIN_MACH = "kCFErrorDomainMach";
    @CData("kCFErrorDomainCocoa")
    public static final String DOMAIN_COCOA = "kCFErrorDomainCocoa";
    private final Exception mAdaptee;

    private CoreError(String domain, int code, CoreErrorUserInfo userInfo) {
        mAdaptee = new RuntimeException(); //TODO
    }

    private CoreError(Exception adaptee) {
        mAdaptee = adaptee;
    }

    public static CoreError valueOf(Exception adaptee) {
        return new CoreError(adaptee);
    }

    @CFunction("CFErrorCreate")
    public static CoreError getInstance(String domain, int code, CoreErrorUserInfo userInfo) {
        return new CoreError(domain, code, userInfo);
    }

    public Exception toException() {
        return mAdaptee;
    }

    @CFunction("CFErrorGetDomain")
    public String getDomain() {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CFErrorGetCode")
    public int getCode() {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CFErrorCopyUserInfo")
    public CoreErrorUserInfo copyUserInfo() {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CFErrorCopyDescription")
    @Override
    public String toString() {
        return "CFError"; //TODO
    }

    @CFunction("CFErrorCopyFailureReason")
    public String copyFailureReason() {
        throw new NotImplementedException();
    }

    @CFunction("CFErrorCopyRecoverySuggestion")
    public String copyRecoverySuggestion() {
        throw new NotImplementedException();
    }
}
