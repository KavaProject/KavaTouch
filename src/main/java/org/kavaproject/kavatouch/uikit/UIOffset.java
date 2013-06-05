/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.uikit;

import org.kavaproject.kavatouch.internal.CFunction;
import org.kavaproject.kavatouch.internal.CTypedef;
import org.kavaproject.kavatouch.internal.Header;
import org.kavaproject.kavatouch.util.NotImplementedException;

@Header("UIGeometry")
@CTypedef(value = "UIOffset", tokenGroup = "UIKitDataTypes")
public class UIOffset {
    @CFunction("UIOffsetZero")
    public static final UIOffset ZERO = new UIOffset(0, 0);
    public final float horizontal;
    public final float vertical;

    @CFunction("UIOffsetMake")
    public UIOffset(float horizontal, float vertical) {
        this.horizontal = horizontal;
        this.vertical = vertical;
    }

    @CFunction("UIOffsetFromString")
    public UIOffset(String string) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("UIOffsetEqualToOffset")
    @Override
    public boolean equals(Object o) {
        return o != null && o instanceof UIOffset && ((UIOffset) o).horizontal == horizontal && ((UIOffset) o)
                .vertical == vertical;
    }

    @CFunction("NSStringFromUIOffset")
    @Override
    public String toString() {
        throw new NotImplementedException(); //TODO
    }
}
