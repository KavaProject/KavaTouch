/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.uikit;

import org.kavaproject.kavatouch.foundation.Copying;
import org.kavaproject.kavatouch.internal.*;
import org.kavaproject.kavatouch.runtime.Creatable;

@Header("UIFont")
@OccClass("UIFont")
public interface UIFont extends Copying, Creatable {
    @OccInstanceMethod("fontWithSize:")
    UIFont withSize(float fontSize);

    @OccInstanceProperty("fontName")
    String getFontName();

    @OccInstanceProperty(value = "familyName", argumentSemantic = ArgumentSemantic.RETAIN)
    String getFamilyName();

    @OccInstanceProperty("pointSize")
    float getPointSize();

    @OccInstanceProperty("ascender")
    float getAscender();

    @OccInstanceProperty("descender")
    float getDescender();

    @OccInstanceProperty("capHeight")
    float getCapHeight();

    @OccInstanceProperty("xHeight")
    float getXHeight();

    @OccInstanceProperty("leading")
    @Deprecated
    float getLeading();

    @OccInstanceProperty("lineHeight")
    float getLineHeight();

    @Override
    UIFontFactory getFactory();
}
