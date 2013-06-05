/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.coregraphics;

import org.kavaproject.kavatouch.corefoundation.CoreType;
import org.kavaproject.kavatouch.coregraphics.staging.GraphicsPattern;
import org.kavaproject.kavatouch.internal.CFunction;
import org.kavaproject.kavatouch.internal.Header;
import org.kavaproject.kavatouch.internal.OpaqueType;
import org.kavaproject.kavatouch.util.NotImplementedException;

@Header("CGColor")
@OpaqueType("CGColorRef")
public class GraphicsColor implements CoreType {
    private float[] mComponents;
    private GraphicsColorSpace mColorspace;
    private int mNumberOfComponents;
    private GraphicsPattern mPattern;

    @CFunction("CGColorCreateCopyWithAlpha")
    public GraphicsColor(GraphicsColor color, float alpha) {
        this(color);
        mComponents[color.getNumberOfComponents() - 1] = alpha;
    }

    @CFunction("CGColorGetNumberOfComponents")
    public int getNumberOfComponents() {
        return mNumberOfComponents;
    }

    @CFunction("CGColorCreateCopy")
    public GraphicsColor(GraphicsColor color) {
        this(color.getColorSpace(), color.getComponents());
        mPattern = color.getPattern();
    }

    @CFunction("CGColorGetComponents")
    public float[] getComponents() {
        return mComponents.clone();
    }

    @CFunction("CGColorGetColorSpace")
    public GraphicsColorSpace getColorSpace() {
        return mColorspace;
    }

    @CFunction("CGColorGetPattern")
    public GraphicsPattern getPattern() {
        return mPattern;
    }

    @CFunction("CGColorCreate")
    public GraphicsColor(GraphicsColorSpace colorspace, float[] components) {
        mColorspace = colorspace;
        mNumberOfComponents = colorspace.getNumberOfComponents() + 1;
//        float[] components = new float[color.numberOfComponents];
//        for (int i = 0; i < color.numberOfComponents - 1; i++) {
//            components[i] = color.components[i];
//        }
        mComponents = components.clone();
    }

    @CFunction("CGColorCreateWithPattern")
    public GraphicsColor(GraphicsColorSpace colorspace, GraphicsPattern pattern, float[] components) {
        this(colorspace, components);
        mPattern = pattern;
    }

    @CFunction("CGColorEqualToColor")
    @Override
    public boolean equals(Object other) {
        if (other == null || !(other instanceof GraphicsColor)) {
            return false;
        }
        GraphicsColor color2 = (GraphicsColor) other;
        if (!mColorspace.equals(color2.mColorspace)) {
            return false;
        }
        for (int i = 0; i < mNumberOfComponents; i++) {
            if (mComponents[i] != color2.mComponents[i]) {
                return false;
            }
        }
        return true;
    }

    @CFunction("CGColorGetAlpha")
    public float getAlpha() {
        return mComponents[mNumberOfComponents - 1];
    }

    public GraphicsColor toRGBAColor() {
        GraphicsColorSpace colorSpace = getColorSpace();
        float[] rgbComponents = new float[4];
        boolean success = convertComponentsToRGBA(colorSpace, mComponents, rgbComponents);
        if (!success) {
            return null;
        }
        GraphicsColorSpace rgbColorSpace = GraphicsColorSpace.createDeviceRGB();
        return new GraphicsColor(rgbColorSpace, rgbComponents);
    }

    private static boolean convertComponentsToRGBA(GraphicsColorSpace inputSpace, float[] components,
                                                   float[] rgbComponents) {
        GraphicsColorSpaceModel model = inputSpace.getModel();
        switch (model) {
            case MONOCHROME:
                rgbComponents[0] = components[0];
                rgbComponents[1] = components[0];
                rgbComponents[2] = components[0];
                rgbComponents[3] = components[1];
                return true;
            case RGB:
                rgbComponents[0] = components[0];
                rgbComponents[1] = components[1];
                rgbComponents[2] = components[2];
                rgbComponents[3] = components[3];
                return true;
            case CMYK:
                float K = components[3];
                float C = (components[0] * (1 - K) + K);
                float M = (components[1] * (1 - K) + K);
                float Y = (components[2] * (1 - K) + K);
                rgbComponents[0] = (1 - C);
                rgbComponents[1] = (1 - M);
                rgbComponents[2] = (1 - Y);
                rgbComponents[3] = components[4];
                return true;
            case LAB:
                return false;
            case DEVICE_N:
                throw new NotImplementedException();
            case INDEXED:
                return false;
            case PATTERN:
            default:
                return false;
        }
    }
}
