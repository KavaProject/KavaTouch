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

@Header("UIDevice")
@CTypedef("UIDeviceOrientation")
public enum UIDeviceOrientation {
    @CEnumConstant("UIDeviceOrientationUnknown")
    UNKNOWN,

    @CEnumConstant("UIDeviceOrientationPortrait")
    PORTRAIT,

    @CEnumConstant("UIDeviceOrientationPortraitUpsideDown")
    PORTRAIT_UPSIDE_DOWN,

    @CEnumConstant("UIDeviceOrientationLandscapeLeft")
    LANDSCAPE_LEFT,

    @CEnumConstant("UIDeviceOrientationLandscapeRight")
    LANDSCAPE_RIGHT,

    @CEnumConstant("UIDeviceOrientationFaceUp")
    FACE_UP,

    @CEnumConstant("UIDeviceOrientationFaceDown")
    FACE_DOWN;

    @Header("UIApplication")
    @CMacro(value = "UIDeviceOrientationIsValidInterfaceOrientation", tokenGroup = "UIKit")
    public boolean isValidInterfaceOrientation() {
        return this == PORTRAIT || this == PORTRAIT_UPSIDE_DOWN || this == LANDSCAPE_LEFT || this == LANDSCAPE_RIGHT;
    }

    @Header("UIApplication")
    @CMacro(value = "UIDeviceOrientationIsPortrait", tokenGroup = "UIKit")
    public boolean isPortrait() {
        return this == PORTRAIT || this == PORTRAIT_UPSIDE_DOWN;
    }

    @Header("UIApplication")
    @CMacro(value = "UIDeviceOrientationIsLandscape", tokenGroup = "UIKit")
    public boolean isLandscape() {
        return this == LANDSCAPE_LEFT || this == LANDSCAPE_RIGHT;
    }
}
