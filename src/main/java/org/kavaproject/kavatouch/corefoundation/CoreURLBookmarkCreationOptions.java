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
@CTypedef("CFURLBookmarkCreationOptions")
public enum CoreURLBookmarkCreationOptions {
    @CEnumConstant(value = "kCFURLBookmarkCreationPreferFileIDResolutionMask", constantValue = 1l << 8)
    PREFER_FILE_ID_RESOLUTION_MASK(1l << 8),
    @CEnumConstant(value = "kCFURLBookmarkCreationMinimalBookmarkMask", constantValue = 1l << 9)
    CREATION_MINIMAL_BOOKMARK_MASK(1l << 9),
    @CEnumConstant(value = "kCFURLBookmarkCreationSuitableForBookmarkFile", constantValue = 1l << 10)
    SUITABLE_FOR_BOOKMARK_FILE(1l << 10),
    @CEnumConstant(value = "kCFURLBookmarkCreationWithSecurityScope", constantValue = 1l << 11)
    WITH_SECURITY_SCOPE(1l << 11),
    @CEnumConstant(value = "kCFURLBookmarkCreationSecurityScopeAllowOnlyReadAccess", constantValue = 1l << 12)
    SECURITY_SCOPE_ALLOW_ONLY_READ_ACCESS(1l << 12);
    private final long value;

    CoreURLBookmarkCreationOptions(long value) {
        this.value = value;
    }
}
