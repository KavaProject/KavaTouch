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
import org.kavaproject.kavatouch.internal.CFunction;
import org.kavaproject.kavatouch.internal.Header;

@Header("UIGraphics")
public interface UIImageGraphics {
    @CFunction("UIGraphicsBeginImageContext")
    void beginImageContext(GraphicsSize size);

    @CFunction("UIGraphicsBeginImageContextWithOptions")
    void beginImageContext(GraphicsSize size, boolean opaque, float scale);

    @CFunction("UIGraphicsGetImageFromCurrentImageContext")
    UIImage getImageFromCurrentImageContext();
}
