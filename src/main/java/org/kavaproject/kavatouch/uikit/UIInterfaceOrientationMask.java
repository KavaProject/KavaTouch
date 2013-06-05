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

import java.util.EnumSet;

@Header("UIApplication")
@CTypedef("UIInterfaceOrientationMask")
public enum UIInterfaceOrientationMask {
    @CEnumConstant("UIInterfaceOrientationMaskPortrait")
    PORTRAIT(EnumSet.of(UIInterfaceOrientation.PORTRAIT)),

    @CEnumConstant("UIInterfaceOrientationMaskLandscapeLeft")
    LANDSCAPE_LEFT(EnumSet.of(UIInterfaceOrientation.LANDSCAPE_LEFT)),

    @CEnumConstant("UIInterfaceOrientationMaskLandscapeRight")
    LANDSCAPE_RIGHT(EnumSet.of(UIInterfaceOrientation.LANDSCAPE_RIGHT)),

    @CEnumConstant("UIInterfaceOrientationMaskPortraitUpsideDown")
    PORTRAIT_UPSIDE_DOWN(EnumSet.of(UIInterfaceOrientation.PORTRAIT_UPSIDE_DOWN)),

    @CEnumConstant("UIInterfaceOrientationMaskLandscape")
    LANDSCAPE(EnumSet.of(UIInterfaceOrientation.LANDSCAPE_LEFT, UIInterfaceOrientation.LANDSCAPE_RIGHT)),

    @CEnumConstant("UIInterfaceOrientationMaskAll")
    ALL(EnumSet.allOf(UIInterfaceOrientation.class)),

    @CEnumConstant("UIInterfaceOrientationMaskAllButUpsideDown")
    ALL_BUT_UPSIDE_DOWN(EnumSet.of(UIInterfaceOrientation.LANDSCAPE_LEFT, UIInterfaceOrientation.LANDSCAPE_RIGHT,
            UIInterfaceOrientation.PORTRAIT));
    public final EnumSet<UIInterfaceOrientation> orientations;

    UIInterfaceOrientationMask(EnumSet<UIInterfaceOrientation> orientations) {
        this.orientations = orientations;
    }
}
