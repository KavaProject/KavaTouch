/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.uikit;

import org.kavaproject.kavatouch.coregraphics.*;

import javax.inject.Inject;

import static org.kavaproject.kavatouch.coregraphics.GraphicsColorSpace.createDeviceRGB;

public class DefaultUIImageGraphics implements UIImageGraphics {
    private final UIGraphics mUIGraphics;
    private final UIScreen mMainScreen;
    private final UIImageFactory mUIImageFactory;

    @Inject
    protected DefaultUIImageGraphics(UIGraphics uiGraphics, UIScreen mainScreen, UIImageFactory uiImageFactory) {
        mUIGraphics = uiGraphics;
        mMainScreen = mainScreen;
        mUIImageFactory = uiImageFactory;
    }

    @Override
    public void beginImageContext(GraphicsSize size) {
        beginImageContext(size, false, 1);
    }

    @Override
    public void beginImageContext(GraphicsSize size, boolean opaque, float scale) {
        if (scale == 0) {
            scale = mMainScreen.getScale();
        }
        float width = size.width * scale;
        float height = size.height * scale;
        if (width > 0 && height > 0) {
            GraphicsColorSpace colorSpace = createDeviceRGB();
            GraphicsBitmapInfo bitmapInfo = new GraphicsBitmapInfo();
            bitmapInfo.alphaInfo = opaque ? GraphicsImageAlphaInfo.NONE_SKIP_FIRST : GraphicsImageAlphaInfo
                    .PREMULTIPLIED_FIRST;
            GraphicsBitmapContext ctx = new GraphicsBitmapContext(null, (int) width, (int) height, 8,
                    (int) (4 * width), colorSpace, bitmapInfo);
            ctx.internal.scale = scale;
            ctx.concatCTM(new GraphicsAffineTransform(1, 0, 0, -1, 0, height));
            ctx.scaleCTM(scale, scale);
            mUIGraphics.pushContext(ctx);
        }
    }

    @Override
    public UIImage getImageFromCurrentImageContext() {
        GraphicsContext context = mUIGraphics.getCurrentContext();
        if (!(context instanceof GraphicsBitmapContext)) {
            return null;
        }
        GraphicsBitmapContext bitmapContext = (GraphicsBitmapContext) context;
        GraphicsImage cgImage = bitmapContext.toImage();
        UIImage image = mUIImageFactory.create(cgImage, bitmapContext.internal.scale, UIImageOrientation.UP);
        return image;
    }
}
