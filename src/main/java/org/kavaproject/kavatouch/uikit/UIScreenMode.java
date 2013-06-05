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
import org.kavaproject.kavatouch.internal.OccClass;
import org.kavaproject.kavatouch.internal.OccInstanceProperty;
import org.kavaproject.kavatouch.runtime.Creatable;

@OccClass("UIScreenMode")
public interface UIScreenMode extends Creatable {
    @OccInstanceProperty("size")
    GraphicsSize getSize();

    @OccInstanceProperty("pixelAspectRatio")
    float getPixelAspectRatio();

    @Override
    UIScreenModeFactory getFactory();
}
