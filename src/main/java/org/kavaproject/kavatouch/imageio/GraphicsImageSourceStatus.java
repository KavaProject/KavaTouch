/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.imageio;

import org.kavaproject.kavatouch.internal.CEnumConstant;
import org.kavaproject.kavatouch.internal.CTypedef;
import org.kavaproject.kavatouch.internal.Header;

@Header("CGImageSource")
@CTypedef("CGImageSourceStatus")
public enum GraphicsImageSourceStatus {
    @CEnumConstant("kCGImageStatusUnexpectedEOF")
    UNEXPECTED_EOF(-5),

    @CEnumConstant("kCGImageStatusInvalidData")
    INVALID_DATA(-4),

    @CEnumConstant("kCGImageStatusUnknownType")
    UNKNOWN_TYPE(-3),

    @CEnumConstant("kCGImageStatusReadingHeader")
    READING_HEADER(-2),

    @CEnumConstant("kCGImageStatusIncomplete")
    INCOMPLETE(-1),

    @CEnumConstant("kCGImageStatusComplete")
    COMPLETE(0);
    public final int value;

    GraphicsImageSourceStatus(int value) {
        this.value = value;
    }
}
