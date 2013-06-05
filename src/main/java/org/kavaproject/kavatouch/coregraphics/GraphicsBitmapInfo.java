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
import org.kavaproject.kavatouch.internal.CTypedef;
import org.kavaproject.kavatouch.internal.Header;

@Header("CGImage")
@CTypedef("CGBitmapInfo")
public class GraphicsBitmapInfo {
    @CEnumConstant(value = "kCGBitmapAlphaInfoMask", constantValue = 0x1F)
    public GraphicsImageAlphaInfo alphaInfo;
    @CEnumConstant(value = "kCGBitmapFloatComponents", constantValue = 1 << 8)
    public boolean floatComponents;
    @CEnumConstant(value = "kCGBitmapByteOrderMask", constantValue = 0x7000)
    public GraphicsImageByteOrder byteOrder;
}
