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
import org.kavaproject.kavatouch.util.NotImplementedException;

public class DefaultUIFont implements UIFont {
    private final UIFontFactory mUIFontFactory;
    private String mFamilyName;
    private String mStyle;
    private float mSize;

    protected DefaultUIFont(String fontName, float fontSize, UIFontFactory uiFontFactory) {
        this.mUIFontFactory = uiFontFactory;
        String[] r = fontName.split("(?=\\p{Lu})");
        String familyName = "";
        for (int i = 0; i < r.length; i++) {
            familyName += r[i];
        }
        mFamilyName = familyName;
        mStyle = r[r.length - 1];
        mSize = fontSize;
    }

    @Override
    public UIFont withSize(float fontSize) {
        return mUIFontFactory.createWithName("Courier New", fontSize);
    }

    @Override
    public String getFontName() {
        return getFamilyName() + mStyle;
    }

    @Override
    public String getFamilyName() {
        return mFamilyName;
    }

    @Override
    public float getPointSize() {
        return mSize;
    }

    @Override
    public float getAscender() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public float getDescender() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public float getCapHeight() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public float getXHeight() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public float getLeading() {
        return getLineHeight();
    }

    @Override
    public float getLineHeight() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public UIFontFactory getFactory() {
        return mUIFontFactory;
    }

    @Override
    public Copying copy() {
        return null;
    }
}
