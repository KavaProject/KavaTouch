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
@CTypedef("CGImageAlphaInfo")
public enum GraphicsImageAlphaInfo {
    @CEnumConstant("kCGImageAlphaNone")
    NONE,

    @CEnumConstant("kCGImageAlphaPremultipliedLast")
    PREMULTIPLIED_LAST,

    @CEnumConstant("kCGImageAlphaPremultipliedFirst")
    PREMULTIPLIED_FIRST,

    @CEnumConstant("kCGImageAlphaLast")
    LAST,

    @CEnumConstant("kCGImageAlphaFirst")
    FIRST,

    @CEnumConstant("kCGImageAlphaNoneSkipLast")
    NONE_SKIP_LAST,

    @CEnumConstant("kCGImageAlphaNoneSkipFirst")
    NONE_SKIP_FIRST,

    @CEnumConstant("kCGImageAlphaOnly")
    ONLY
}
