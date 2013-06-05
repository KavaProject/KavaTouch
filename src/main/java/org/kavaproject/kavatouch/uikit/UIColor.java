/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.uikit;

import org.kavaproject.kavatouch.coregraphics.GraphicsColor;
import org.kavaproject.kavatouch.foundation.Coding;
import org.kavaproject.kavatouch.foundation.CoreBridge;
import org.kavaproject.kavatouch.internal.Header;
import org.kavaproject.kavatouch.internal.OccClass;
import org.kavaproject.kavatouch.internal.OccInstanceMethod;
import org.kavaproject.kavatouch.internal.OccInstanceProperty;
import org.kavaproject.kavatouch.runtime.Creatable;
import org.kavaproject.kavatouch.uikit.staging.ImageColor;

@Header("UIColor")
@OccClass("UIColor")
public interface UIColor extends Coding, Creatable, CoreBridge<GraphicsColor> {
    @OccInstanceMethod("colorWithAlphaComponent:")
    UIColor colorWithAlpha(float alpha);

    @OccInstanceProperty("CGColor")
    @Override
    GraphicsColor toCoreType();

    @OccInstanceProperty("CIColor")
    ImageColor ciColor();

    @OccInstanceMethod("getHue:saturation:brightness:alpha:")
    boolean getHSBA(float[] hsba);

    @OccInstanceMethod("getRed:green:blue:alpha:")
    boolean getRGBA(float[] rgba);

    @OccInstanceMethod("getWhite:alpha:")
    boolean getWhiteAlpha(float[] whiteAlpha);

    @OccInstanceMethod("set")
    void setColor();

    @OccInstanceMethod("setFill")
    void setFill();

    @OccInstanceMethod("setStroke")
    void setStroke();

    @Override
    UIColorFactory getFactory();
}
