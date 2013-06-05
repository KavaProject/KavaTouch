/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.coregraphics;

import android.graphics.PorterDuff;
import org.kavaproject.kavatouch.internal.CEnumConstant;
import org.kavaproject.kavatouch.internal.CTypedef;
import org.kavaproject.kavatouch.internal.Header;

@Header("CGContext")
@CTypedef("CGBlendMode")
public enum GraphicsBlendMode {
    @CEnumConstant("kCGBlendModeNormal")
    NORMAL(PorterDuff.Mode.DST),

    @CEnumConstant("kCGBlendModeMultiply")
    MULTIPLY(PorterDuff.Mode.MULTIPLY),

    @CEnumConstant("kCGBlendModeScreen")
    SCREEN(PorterDuff.Mode.SCREEN),

    @CEnumConstant("kCGBlendModeOverlay")
    OVERLAY(PorterDuff.Mode.OVERLAY),  //Android 2.3.7

    @CEnumConstant("kCGBlendModeDarken")
    DARKEN(PorterDuff.Mode.DARKEN),

    @CEnumConstant("kCGBlendModeLighten")
    LIGHTEN(PorterDuff.Mode.LIGHTEN),

    @CEnumConstant("kCGBlendModeColorDodge")
    COLOR_DODGE(null),

    @CEnumConstant("kCGBlendModeColorBurn")
    COLOR_BURN(null),

    @CEnumConstant("kCGBlendModeSoftLight")
    SOFT_LIGHT(null),

    @CEnumConstant("kCGBlendModeHardLight")
    HARD_LIGHT(null),

    @CEnumConstant("kCGBlendModeDifference")
    DIFFERENCE(null),

    @CEnumConstant("kCGBlendModeExclusion")
    EXCLUSION(null),

    @CEnumConstant("kCGBlendModeHue")
    HUE(null),

    @CEnumConstant("kCGBlendModeSaturation")
    SATURATION(null),

    @CEnumConstant("kCGBlendModeColor")
    COLOR(null),

    @CEnumConstant("kCGBlendModeLuminosity")
    LUMINOSITY(null),

    @CEnumConstant("kCGBlendModeClear")
    CLEAR((PorterDuff.Mode.CLEAR)),

    @CEnumConstant("kCGBlendModeCopy")
    COPY(PorterDuff.Mode.SRC),

    @CEnumConstant("kCGBlendModeSourceIn")
    SOURCE_IN(PorterDuff.Mode.SRC_IN),

    @CEnumConstant("kCGBlendModeSourceOut")
    SOURCE_OUT(PorterDuff.Mode.SRC_OUT),

    @CEnumConstant("kCGBlendModeSourceAtop")
    SOURCE_ATOP(PorterDuff.Mode.SRC_ATOP),

    @CEnumConstant("kCGBlendModeDestinationOver")
    DESTINATION_OVER(PorterDuff.Mode.DST_OVER),

    @CEnumConstant("kCGBlendModeDestinationIn")
    DESTINATION_IN(PorterDuff.Mode.DST_IN),

    @CEnumConstant("kCGBlendModeDestinationOut")
    DESTINATION_OUT(PorterDuff.Mode.DST_OUT),

    @CEnumConstant("kCGBlendModeDestinationAtop")
    DESTINATION_ATOP(PorterDuff.Mode.DST_ATOP),

    @CEnumConstant("kCGBlendModeXOR")
    XOR(PorterDuff.Mode.XOR),

    @CEnumConstant("kCGBlendModePlusDarker")
    PLUS_DARKER(null),

    @CEnumConstant("kCGBlendModePlusLighter")
    PLUS_LIGHTER(PorterDuff.Mode.ADD);  //Android 2.3.7
    public final PorterDuff.Mode androidMode;

    GraphicsBlendMode(PorterDuff.Mode mode) {
        androidMode = mode;
    }
}
