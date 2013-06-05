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
@CTypedef(values = {"CFURLBookmarkFileCreationOptions", "CFURLBookmarkResolutionOptions"})
public enum CoreURLBookmarkResolutionAndFileCreationOptions {
    @CEnumConstant(value = "kCFBookmarkResolutionWithoutUIMask", constantValue = 1l << 8)
    WITHOUT_UI_MASK(1l << 8),
    @CEnumConstant(value = "kCFBookmarkResolutionWithoutMountingMask", constantValue = 1l << 9)
    WITHOUT_MOUNTING_MASK(1l << 9),
    @CEnumConstant(value = "kCFURLBookmarkResolutionWithSecurityScope", constantValue = 1l << 10)
    WITH_SECURITY_SCOPE(1l << 10);
    public final long value;

    CoreURLBookmarkResolutionAndFileCreationOptions(long value) {
        this.value = value;
    }
}
