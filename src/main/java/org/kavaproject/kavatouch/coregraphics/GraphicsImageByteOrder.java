/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.coregraphics;

import org.kavaproject.kavatouch.internal.CEnumConstant;
import org.kavaproject.kavatouch.internal.Header;

@Header("CGImage")
public enum GraphicsImageByteOrder {
    @CEnumConstant(value = "kCGBitmapByteOrderDefault", constantValue = 0 << 12, definingEnum = GraphicsBitmapInfo
            .class)
    DEFAULT,

    @CEnumConstant(value = "kCGBitmapByteOrder16Little", constantValue = 1 << 12, definingEnum = GraphicsBitmapInfo
            .class)
    BYTE_ORDER_16_LITTLE,

    @CEnumConstant(value = "kCGBitmapByteOrder32Little", constantValue = 2 << 12, definingEnum = GraphicsBitmapInfo
            .class)
    BYTE_ORDER_32_LITTLE,

    @CEnumConstant(value = "kCGBitmapByteOrder16Big", constantValue = 3 << 12, definingEnum = GraphicsBitmapInfo.class)
    BYTE_ORDER_16_BIG,

    @CEnumConstant(value = "kCGBitmapByteOrder32Big", constantValue = 4 << 12, definingEnum = GraphicsBitmapInfo.class)
    BYTE_ORDER_32_BIG
}
