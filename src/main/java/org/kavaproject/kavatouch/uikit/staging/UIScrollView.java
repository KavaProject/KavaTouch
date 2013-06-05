/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.uikit.staging;

import org.kavaproject.kavatouch.coregraphics.GraphicsRect;
import org.kavaproject.kavatouch.foundation.Coder;
import org.kavaproject.kavatouch.foundation.Coding;
import org.kavaproject.kavatouch.util.NotImplementedException;
import org.kavaproject.kavatouch.runtime.MethodResolver;
import org.kavaproject.kavatouch.uikit.*;

public class UIScrollView extends DefaultUIView implements Coding {
    public UIScrollView(GraphicsRect frame, UIScrollViewFactory uiScrollViewFactory, UIGraphics uiGraphics,
                        UIColorFactory uiColorFactory, UIScreen mainScreen, MethodResolver methodResolver) {
        super(frame, uiScrollViewFactory, uiGraphics, uiColorFactory, mainScreen, methodResolver);
    }

    @Override
    public void encode(Coder encoder) {
        throw new NotImplementedException(); //TODO
    }
}
