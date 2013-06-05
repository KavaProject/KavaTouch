/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.foundation;

import org.kavaproject.kavatouch.corefoundation.CorePropertyListFormat;
import org.kavaproject.kavatouch.internal.CEnumConstant;
import org.kavaproject.kavatouch.internal.CTypedef;
import org.kavaproject.kavatouch.internal.Header;

@Header("NSPropertyList")
@CTypedef(value = "NSPropertyListFormat")
public enum PropertyListFormat {
    @CEnumConstant("NSPropertyListOpenStepFormat")
    OPEN_STEP(CorePropertyListFormat.OPEN_STEP),

    @CEnumConstant("NSPropertyListXMLFormat_v1_0")
    XML(CorePropertyListFormat.XML_V1_0),

    @CEnumConstant("NSPropertyListBinaryFormat_v1_0")
    BINARY(CorePropertyListFormat.BINARY_V1_0);
    public final CorePropertyListFormat value;

    PropertyListFormat(CorePropertyListFormat value) {
        this.value = value;
    }
}
