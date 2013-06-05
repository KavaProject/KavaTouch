/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.coregraphics;

import android.graphics.Paint;

class GraphicsState {
    public Paint paint;
    public GraphicsAffineTransform ctm;
    public GraphicsColor rgbFillColor;
    public GraphicsColor rgbStrokeColor;
    public GraphicsColorSpace fillColorspace;
    public GraphicsColorSpace strokeColorspace;
    public float alpha = 1;

    public static GraphicsState copy(GraphicsState state) {
        GraphicsState result = new GraphicsState();
        result.paint = new Paint(state.paint);
        result.ctm = new GraphicsAffineTransform(state.ctm);
        result.rgbFillColor = new GraphicsColor(state.rgbFillColor);
        result.rgbStrokeColor = new GraphicsColor(state.rgbStrokeColor);
        result.fillColorspace = state.fillColorspace;
        result.strokeColorspace = state.strokeColorspace;
        result.alpha = state.alpha;
        return result;
    }
}
