/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.uikit;

import org.kavaproject.kavatouch.coregraphics.GraphicsColor;
import org.kavaproject.kavatouch.coregraphics.GraphicsContext;
import org.kavaproject.kavatouch.foundation.Coder;
import org.kavaproject.kavatouch.uikit.staging.ImageColor;
import org.kavaproject.kavatouch.util.NotImplementedException;

public class DefaultUIColor implements UIColor {
    private final UIColorFactory mUIColorFactory;
    private final UIGraphics mUIGraphics;
    private GraphicsColor mGraphicsColor;

    protected DefaultUIColor(GraphicsColor graphicsColor, UIColorFactory uiColorFactory, UIGraphics uiGraphics) {
        mUIColorFactory = uiColorFactory;
        mUIGraphics = uiGraphics;
        mGraphicsColor = graphicsColor;
    }

    protected DefaultUIColor(Coder decoder, UIColorFactory uiColorFactory, UIGraphics uiGraphics) {
        mUIColorFactory = uiColorFactory;
        mUIGraphics = uiGraphics;
        //TODO
    }

    @Override
    public UIColor colorWithAlpha(float alpha) {
        GraphicsColor color = new GraphicsColor(mGraphicsColor, alpha);
        return mUIColorFactory.create(color);
    }

    @Override
    public GraphicsColor toCoreType() {
        return mGraphicsColor;
    }

    @Override
    public ImageColor ciColor() {
        throw new NotImplementedException();
    }

    @Override
    public boolean getHSBA(float[] hsba) {
        float[] rgba = new float[4];
        if (!getRGBA(rgba)) {
            return false;
        }
        RGBToHSB(rgba[0], rgba[1], rgba[2], hsba);
        hsba[3] = rgba[3];
        return true;
    }

    @Override
    public boolean getRGBA(float[] rgba) {
        GraphicsColor rgbColor = mGraphicsColor.toRGBAColor();
        if (rgbColor == null) {
            return false;
        }
        System.arraycopy(rgbColor.getComponents(), 0, rgba, 0, 4);
        return true;
    }

    @Override
    public boolean getWhiteAlpha(float[] whiteAlpha) {
        float[] rgba = new float[4];
        if (!getRGBA(rgba)) {
            return false;
        }
        RGBToWhite(rgba[0], rgba[1], rgba[2], whiteAlpha);
        whiteAlpha[1] = rgba[3];
        return true;
    }

    private static final void RGBToWhite(float red, float green, float blue, float[] white) {
        white[0] = (red + green + blue) / 3;
    }

    @Override
    public void setColor() {
        GraphicsContext context = mUIGraphics.getCurrentContext();
        context.setFillColor(mGraphicsColor);
        context.setStrokeColor(mGraphicsColor);
    }

    @Override
    public void setFill() {
        GraphicsContext context = mUIGraphics.getCurrentContext();
        context.setFillColor(mGraphicsColor);
    }

    @Override
    public void setStroke() {
        GraphicsContext context = mUIGraphics.getCurrentContext();
        context.setStrokeColor(mGraphicsColor);
    }

    @Override
    public UIColorFactory getFactory() {
        return mUIColorFactory;
    }

    private static final void RGBToHSB(float red, float green, float blue, float[] hsb) {
        float M = Math.max(red, Math.max(green, blue));
        float m = Math.min(red, Math.min(green, blue));
        float H = 0;
        float S = 0;
        float V = M;
        if (V > 0) {
            float C = M - m;
            S = C / V;
            if (C == 0) {
                H = 0;
            } else if (red == M) {
                H = (green - blue) / C;
                H %= 6;
            } else if (green == M) {
                H = 2 + (blue - red) / C;
            } else if (blue == M) {
                H = 4 + (red - green) / C;
            }

            H *= 60.f;
            if (H < 0) {
                H += 360.f;
            }
        }
        hsb[0] = H / 360;
        hsb[1] = S;
        hsb[2] = V;
    }

    @Override
    public void encode(Coder encoder) {
        throw new NotImplementedException(); //TODO
    }
}
