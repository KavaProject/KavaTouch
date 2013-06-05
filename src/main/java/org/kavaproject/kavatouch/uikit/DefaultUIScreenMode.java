/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.uikit;

import org.kavaproject.kavatouch.coregraphics.GraphicsSize;

public class DefaultUIScreenMode implements UIScreenMode {
    private final UIScreenModeFactory mUIScreenModeFactory;
    private GraphicsSize mSize;
    private float mPixelAspectRatio;

    protected DefaultUIScreenMode(GraphicsSize size, float pixelAspectRatio, UIScreenModeFactory uiScreenModeFactory) {
        mSize = size;
        mPixelAspectRatio = pixelAspectRatio;
        mUIScreenModeFactory = uiScreenModeFactory;
    }

    @Override
    public GraphicsSize getSize() {
        return mSize;
    }

    @Override
    public float getPixelAspectRatio() {
        return mPixelAspectRatio;
    }

    @Override
    public UIScreenModeFactory getFactory() {
        return mUIScreenModeFactory;
    }
}
