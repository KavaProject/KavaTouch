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

@Header("CFPropertyList")
@CTypedef("CFPropertyListFormat")
public enum CorePropertyListFormat {
    @CEnumConstant(value = "kCFPropertyListOpenStepFormat", constantValue = 1)
    OPEN_STEP(1),

    @CEnumConstant(value = "kCFPropertyListXMLFormat_v1_0", constantValue = 100)
    XML_V1_0(100),

    @CEnumConstant(value = "kCFPropertyListBinaryFormat_v1_0", constantValue = 200)
    BINARY_V1_0(200);

    CorePropertyListFormat(int value) {
    }
}
