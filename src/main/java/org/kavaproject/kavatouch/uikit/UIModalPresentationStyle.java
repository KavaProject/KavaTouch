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

@Header("UIViewController")
@CTypedef("UIModalPresentationStyle")
public enum UIModalPresentationStyle {
    @CEnumConstant(value = "UIModalPresentationFullScreen", constantValue = 0)
    FULL_SCREEN(0),

    @CEnumConstant(value = "UIModalPresentationPageSheet", constantValue = 1)
    PAGE_SHEET(1),

    @CEnumConstant(value = "UIModalPresentationFormSheet", constantValue = 2)
    FORM_SHEET(2),

    @CEnumConstant(value = "UIModalPresentationCurrentContext", constantValue = 3)
    CURRENT_CONTEXT(3);

    UIModalPresentationStyle(int value) {
    }
}
