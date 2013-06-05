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
import org.kavaproject.kavatouch.foundation.Coder;
import org.kavaproject.kavatouch.foundation.CodingFactory;
import org.kavaproject.kavatouch.foundation.CoreBridgeFactory;
import org.kavaproject.kavatouch.internal.OccClass;
import org.kavaproject.kavatouch.internal.OccClassMethod;
import org.kavaproject.kavatouch.internal.OccInstanceMethod;
import org.kavaproject.kavatouch.runtime.Factory;
import org.kavaproject.kavatouch.uikit.staging.ImageColor;

@OccClass("UIColor")
public interface UIColorFactory extends CodingFactory, Factory, CoreBridgeFactory<GraphicsColor>,
        UIColorFactoryInternal {
    @Override
    UIColor create(Coder decoder);

    @OccInstanceMethod("initWithHue:saturation:brightness:alpha:")
    UIColor createWithHSBA(float hue, float saturation, float brightness, float alpha);

    @OccInstanceMethod("initWithRed:green:blue:alpha:")
    UIColor createWithRGBA(float red, float green, float blue, float alpha);

    @OccInstanceMethod("initWithCGColor:")
    @Override
    UIColor create(GraphicsColor graphicsColor);

    @OccInstanceMethod("initWithCIColor:")
    UIColor create(ImageColor ciColor);

    @OccClassMethod("blackColor")
    UIColor black();

    @OccInstanceMethod("initWithWhite:alpha:")
    UIColor createWithWhiteAlpha(float white, float alpha);

    @OccClassMethod("darkGrayColor")
    UIColor darkGray();

    @OccClassMethod("lightGrayColor")
    UIColor lightGray();

    @OccClassMethod("whiteColor")
    UIColor white();

    @OccClassMethod("grayColor")
    UIColor gray();

    @OccClassMethod("redColor")
    UIColor red();

    @OccClassMethod("greenColor")
    UIColor green();

    @OccClassMethod("blueColor")
    UIColor blue();

    @OccClassMethod("cyanColor")
    UIColor cyan();

    @OccClassMethod("yellowColor")
    UIColor yellow();

    @OccClassMethod("magentaColor")
    UIColor magenta();

    @OccClassMethod("orangeColor")
    UIColor orange();

    @OccClassMethod("purpleColor")
    UIColor purple();

    @OccClassMethod("brownColor")
    UIColor brown();

    @OccClassMethod("lightTextColor")
    UIColor lightText();

    @OccClassMethod("darkTextColor")
    UIColor darkText();

    @OccClassMethod("groupTableViewBackgroundColor")
    UIColor groupTableViewBackgroundColor();

    @OccClassMethod("clearColor")
    UIColor clear();

    @OccClassMethod("viewFlipsideBackgroundColor")
    UIColor viewFlipsideBackgroundColor();

    @OccInstanceMethod("initWithPatternImage:")
    UIColor createWithPatternImage(UIImage image);

    @OccClassMethod("scrollViewTexturedBackgroundColor")
    UIColor scrollViewTexturedBackgroundColor();

    @OccClassMethod("underPageBackgroundColor")
    UIColor underPageBackgroundColor();
}
