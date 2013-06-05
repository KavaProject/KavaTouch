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

@Header("CGContext")
@CEnumConstant("CGInterpolationQuality")
public enum GraphicsInterpolationQuality {
    @CEnumConstant(value = "kCGInterpolationDefault", constantValue = 0)
    DEFAULT,

    @CEnumConstant(value = "kCGInterpolationNone", constantValue = 1)
    NONE,

    @CEnumConstant(value = "kCGInterpolationLow", constantValue = 2)
    LOW,

    @CEnumConstant(value = "kCGInterpolationMedium", constantValue = 4)
    MEDIUM,

    @CEnumConstant(value = "kCGInterpolationHigh", constantValue = 3)
    HIGH;
}
