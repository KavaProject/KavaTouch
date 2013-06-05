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
@CTypedef("CFURLPathStyle")
public enum CoreURLPathStyle {
    @CEnumConstant(value = "kCFURLPOSIXPathStyle", constantValue = 0)
    POSIX(0),
    @CEnumConstant(value = "kCFURLHFSPathStyle", constantValue = 1)
    HFS(1),
    @CEnumConstant(value = "kCFURLWindowsPathStyle", constantValue = 2)
    WINDOWS(2);
    public final int value;

    CoreURLPathStyle(int value) {
        this.value = value;
    }
}
