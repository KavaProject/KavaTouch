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

@Header("CGColorSpace")
@CTypedef("CGColorSpaceModel")
public enum GraphicsColorSpaceModel {
    @CEnumConstant(value = "kCGColorSpaceModelUnknown", constantValue = -1)
    UNKNOWN(-1),
    @CEnumConstant(value = "kCGColorSpaceModelMonochrome", constantValue = 0)
    MONOCHROME(0),
    @CEnumConstant(value = "kCGColorSpaceModelRGB", constantValue = 1)
    RGB(1),
    @CEnumConstant(value = "kCGColorSpaceModelCMYK", constantValue = 2)
    CMYK(2),
    @CEnumConstant(value = "kCGColorSpaceModelLab", constantValue = 3)
    LAB(3),
    @CEnumConstant(value = "kCGColorSpaceModelDeviceN", constantValue = 4)
    DEVICE_N(4),
    @CEnumConstant(value = "kCGColorSpaceModelIndexed", constantValue = 5)
    INDEXED(5),
    @CEnumConstant(value = "kCGColorSpaceModelPattern", constantValue = 6)
    PATTERN(6);
    public final int value;

    GraphicsColorSpaceModel(int value) {
        this.value = value;
    }
}
