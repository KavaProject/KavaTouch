/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.uikit;

import org.kavaproject.kavatouch.util.NotImplementedException;

import org.kavaproject.kavatouch.util.inject.Inject;
import java.util.Arrays;
import java.util.List;

public class DefaultUIFontFactory implements UIFontFactory {
    @Inject
    protected DefaultUIFontFactory() {
    }

    @Override
    public UIFont systemFontOfSize(float fontSize) {
        return createWithName("Courier New", fontSize);
    }

    @Override
    public UIFont createWithName(String fontName, float fontSize) {
        return new DefaultUIFont(fontName, fontSize, this);
    }

    @Override
    public UIFont boldSystemFontOfSize(float fontSize) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public UIFont italicSystemFontOfSize(float fontSize) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public List<String> familyNames() {
        return Arrays.asList("normal", "serif", "monospace");
    }

    @Override
    public List<String> fontNamesForFamilyName(String familyName) {
        return Arrays.asList(familyName + "Bold", familyName + "BoldItalic", familyName + "Italic",
                familyName + "Normal");
    }

    @Override
    public float labelFontSize() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public float buttonFontSize() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public float smallSystemFontSize() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public float systemFontSize() {
        throw new NotImplementedException(); //TODO
    }
}
