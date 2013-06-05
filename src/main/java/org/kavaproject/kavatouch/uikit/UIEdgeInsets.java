/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.uikit;

import org.kavaproject.kavatouch.coregraphics.GraphicsRect;
import org.kavaproject.kavatouch.internal.CFunction;
import org.kavaproject.kavatouch.internal.CTypedef;
import org.kavaproject.kavatouch.internal.Header;
import org.kavaproject.kavatouch.util.NotImplementedException;

@Header("UIGeometry")
@CTypedef(value = "UIEdgeInsets", tokenGroup = "UIKitDataTypes")
public class UIEdgeInsets {

    @CFunction(value = "UIEdgeInsetsZero", tokenGroup = "UIKit")
    public static final UIEdgeInsets ZERO = new UIEdgeInsets(0, 0, 0, 0);
    public final float top, left, bottom, right;

    public UIEdgeInsets(UIEdgeInsets template) {
        this(template.top, template.left, template.bottom, template.right);
    }

    @CFunction("UIEdgeInsetsMake")
    public UIEdgeInsets(float top, float left, float bottom, float right) {
        this.top = top;
        this.left = left;
        this.bottom = bottom;
        this.right = right;
    }


    @CFunction(value = "UIEdgeInsetsFromString", tokenGroup = "UIKit")
    public UIEdgeInsets(String string) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction(value = "UIEdgeInsetsEqualToEdgeInsets", tokenGroup = "UIKit")
    @Override
    public boolean equals(Object o) {
        return o != null && o instanceof UIEdgeInsets && ((UIEdgeInsets) o).top == top && ((UIEdgeInsets) o).left ==
                left && ((UIEdgeInsets) o).bottom == bottom && ((UIEdgeInsets) o).right == right;
    }

    @CFunction(value = "NSStringFromUIEdgeInsets", tokenGroup = "UIKit")
    @Override
    public String toString() {
        throw new NotImplementedException(); //TODO
    }

    @CFunction(value = "UIEdgeInsetsInsetRect", tokenGroup = "UIKit")
    public GraphicsRect insetRect(GraphicsRect rect) {
        throw new NotImplementedException(); //TODO
    }
}
