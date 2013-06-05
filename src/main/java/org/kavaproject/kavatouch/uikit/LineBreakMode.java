/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.uikit;

import org.kavaproject.kavatouch.internal.CEnumConstant;
import org.kavaproject.kavatouch.internal.CTypedef;
import org.kavaproject.kavatouch.internal.Header;

@Header("NSParagraphStyle")
@CTypedef("NSLineBreakMode")
public enum LineBreakMode {
    @CEnumConstant(value = "NSLineBreakByWordWrapping", constantValue = 0)
    WORD_WRAP(0),

    @CEnumConstant(value = "NSLineBreakByCharWrapping", constantValue = 1)
    CHARACTER_WRAP(1),

    @CEnumConstant(value = "NSLineBreakByClipping", constantValue = 2)
    CLIP(2),

    @CEnumConstant(value = "NSLineBreakByTruncatingHead", constantValue = 3)
    HEAD_TRUNCATION(3),

    @CEnumConstant(value = "NSLineBreakByTruncatingTail", constantValue = 4)
    TAIL_TRUNCATION(4),

    @CEnumConstant(value = "NSLineBreakByTruncatingMiddle", constantValue = 5)
    MIDDLE_TRUNCATION(5);
    public final int value;

    LineBreakMode(int value) {
        this.value = value;
    }
}
