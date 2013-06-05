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

@Header("CFURL")
@CTypedef("CFURLComponentType")
public enum CoreURLComponentType {
    @CEnumConstant(value = "kCFURLComponentScheme", constantValue = 1)
    SCHEME(1),
    @CEnumConstant(value = "kCFURLComponentNetLocation", constantValue = 2)
    NET_LOCATION(2),
    @CEnumConstant(value = "kCFURLComponentPath", constantValue = 3)
    PATH(3),
    @CEnumConstant(value = "kCFURLComponentResourceSpecifier", constantValue = 4)
    RESOURCE_SPECIFIER(4),
    @CEnumConstant(value = "kCFURLComponentUser", constantValue = 5)
    USER(5),
    @CEnumConstant(value = "kCFURLComponentPassword", constantValue = 6)
    PASSWORD(6),
    @CEnumConstant(value = "kCFURLComponentUserInfo", constantValue = 7)
    USER_INFO(7),
    @CEnumConstant(value = "kCFURLComponentHost", constantValue = 8)
    HOST(8),
    @CEnumConstant(value = "kCFURLComponentPort", constantValue = 9)
    PORT(9),
    @CEnumConstant(value = "kCFURLComponentParameterString", constantValue = 10)
    PARAMETER_STRING(10),
    @CEnumConstant(value = "kCFURLComponentQuery", constantValue = 11)
    QUERY(11),
    @CEnumConstant(value = "kCFURLComponentFragment", constantValue = 12)
    FRAGMENT(12);
    public final int value;

    CoreURLComponentType(int value) {
        this.value = value;
    }
}
