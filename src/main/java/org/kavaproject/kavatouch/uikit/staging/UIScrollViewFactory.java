/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.uikit.staging;

import org.kavaproject.kavatouch.coreanimation.AnimationLayerFactory;
import org.kavaproject.kavatouch.internal.OccClass;
import org.kavaproject.kavatouch.runtime.MethodResolver;
import org.kavaproject.kavatouch.uikit.*;

@OccClass("UIScrollView")
public class UIScrollViewFactory extends DefaultUIViewFactory {

    protected UIScrollViewFactory(AnimationLayerFactory animationLayerFactory,
                                  UIGraphics uiGraphics,
                                  UIColorFactory uiColorFactory,
                                  UIScreen mainScreen,
                                  MethodResolver methodResolver) {
        super(animationLayerFactory, uiGraphics, uiColorFactory, mainScreen, methodResolver);
    }

}
