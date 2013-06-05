/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.uikit;

import org.kavaproject.kavatouch.internal.Header;
import org.kavaproject.kavatouch.internal.OccClass;
import org.kavaproject.kavatouch.internal.OccClassMethod;
import org.kavaproject.kavatouch.runtime.Factory;

import java.util.List;

@OccClass("UIFont")
public interface UIFontFactory extends Factory {
    @OccClassMethod("systemFontOfSize:")
    UIFont systemFontOfSize(float fontSize);

    @OccClassMethod("fontWithName:size:")
    UIFont createWithName(String fontName, float fontSize);

    @OccClassMethod("boldSystemFontOfSize:")
    UIFont boldSystemFontOfSize(float fontSize);

    @OccClassMethod("italicSystemFontOfSize:")
    UIFont italicSystemFontOfSize(float fontSize);

    @OccClassMethod("familyNames")
    List<String> familyNames();

    @OccClassMethod("fontNamesForFamilyName:")
    List<String> fontNamesForFamilyName(String familyName);

    @Header("UIInterface")
    @OccClassMethod("labelFontSize")
    float labelFontSize();

    @Header("UIInterface")
    @OccClassMethod("buttonFontSize")
    float buttonFontSize();

    @Header("UIInterface")
    @OccClassMethod("smallSystemFontSize")
    float smallSystemFontSize();

    @Header("UIInterface")
    @OccClassMethod("systemFontSize")
    float systemFontSize();
}
