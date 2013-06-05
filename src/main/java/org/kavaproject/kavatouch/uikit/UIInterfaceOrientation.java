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
import org.kavaproject.kavatouch.internal.CMacro;
import org.kavaproject.kavatouch.internal.CTypedef;
import org.kavaproject.kavatouch.internal.Header;

@Header("UIApplication")
@CTypedef("UIInterfaceOrientation")
public enum UIInterfaceOrientation {
    @CEnumConstant(value = "UIInterfaceOrientationPortrait")
    PORTRAIT(UIDeviceOrientation.PORTRAIT),

    @CEnumConstant(value = "UIInterfaceOrientationPortraitUpsideDown")
    PORTRAIT_UPSIDE_DOWN(UIDeviceOrientation.PORTRAIT_UPSIDE_DOWN),

    @CEnumConstant(value = "UIInterfaceOrientationLandscapeLeft")
    LANDSCAPE_LEFT(UIDeviceOrientation.LANDSCAPE_RIGHT),

    @CEnumConstant(value = "UIInterfaceOrientationLandscapeRight")
    LANDSCAPE_RIGHT(UIDeviceOrientation.LANDSCAPE_LEFT);


    UIInterfaceOrientation(UIDeviceOrientation value) {
    }

    @CMacro(value = "UIInterfaceOrientationIsPortrait", tokenGroup = "UIKit")
    public boolean isPortrait() {
        return this == PORTRAIT || this == PORTRAIT_UPSIDE_DOWN;
    }

    @CMacro(value = "UIInterfaceOrientationIsLandscape", tokenGroup = "UIKit")
    public boolean isLandscape() {
        return this == LANDSCAPE_LEFT || this == LANDSCAPE_RIGHT;
    }
}
