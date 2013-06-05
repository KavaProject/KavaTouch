/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.coregraphics;

import org.kavaproject.kavatouch.internal.CFunction;
import org.kavaproject.kavatouch.internal.CTypedef;
import org.kavaproject.kavatouch.internal.Header;
import org.kavaproject.kavatouch.util.NotImplementedException;

import java.nio.ByteBuffer;
import java.util.Arrays;

@Header("CGColorSpace")
@CTypedef("CGColorSpace")
public class GraphicsColorSpace {
    private GraphicsColorSpaceModel mModel;
    private GraphicsColorSpace mIndexedBaseSpace;
    private byte[] mIndexedColorTable;
    private float[] mLabWhitePoint;
    private float[] mLabBlackPoint;
    private float[] mLabRange;
    private GraphicsColorSpace mPatternBaseSpace;
    private float[] mCalibratedWhitePoint;
    private float[] mCalibratedBlackPoint;
    private float mCalibratedGrayGamma;
    private float[] mCalibratedRGBGamma;
    private String mName;
    private boolean mCalibrated;

    @CFunction("CGColorSpaceCreateCalibratedGray")
    public static GraphicsColorSpace createCalibratedGray(float[] whitePoint, float[] blackPoint, float gamma) {
        GraphicsColorSpace result = new GraphicsColorSpace();
        result.mModel = GraphicsColorSpaceModel.MONOCHROME; //TODO Check
        result.mCalibratedWhitePoint = whitePoint.clone();
        result.mCalibratedBlackPoint = blackPoint.clone();
        result.mCalibratedGrayGamma = gamma;
        result.mCalibrated = true;
        return result;
    }

    @CFunction("CGColorSpaceCreateCalibratedRGB")
    public static GraphicsColorSpace createCalibratedRGB(float[] whitePoint, float[] blackPoint, float[] gamma,
                                                         float[] matrix) {
        GraphicsColorSpace result = new GraphicsColorSpace();
        result.mModel = GraphicsColorSpaceModel.RGB; //TODO Check
        result.mCalibratedWhitePoint = whitePoint.clone();
        result.mCalibratedBlackPoint = blackPoint.clone();
        result.mCalibratedRGBGamma = gamma.clone();
        result.mCalibrated = true;
        return result;
    }

    @CFunction("CGColorSpaceCreateICCBased")
    public static GraphicsColorSpace createICCBased(int nComponents, float[] range, GraphicsDataProvider profile,
                                                    GraphicsColorSpace alternate) {
//        CGColorSpace result = new CGColorSpace();
//        result.model = ;
//        result.iccNComponents = nComponents;
//        result.iccRange = range;
//        result.iccProfile = profile;
//        result.iccAlternate = alternate;
//        return result;
        throw new NotImplementedException();
    }

    @CFunction("CGColorSpaceCreateWithICCProfile")
    public static GraphicsColorSpace createWithICCProfile(ByteBuffer data) {
        throw new NotImplementedException();
    }

    @CFunction("CGColorSpaceCreateLab")
    public static GraphicsColorSpace createLab(float[] whitePoint, float[] blackPoint, float[] range) {
        GraphicsColorSpace result = new GraphicsColorSpace();
        result.mModel = GraphicsColorSpaceModel.LAB;
        result.mLabWhitePoint = whitePoint.clone();
        result.mLabBlackPoint = blackPoint.clone();
        result.mLabRange = range.clone();
        return result;
    }

    @CFunction("CGColorSpaceCreateDeviceCMYK")
    public static GraphicsColorSpace createDeviceCMYK() {
        GraphicsColorSpace result = new GraphicsColorSpace();
        result.mModel = GraphicsColorSpaceModel.CMYK;
        return result;
    }

    @CFunction("CGColorSpaceCreateDeviceGray")
    public static GraphicsColorSpace createDeviceGray() {
        GraphicsColorSpace result = new GraphicsColorSpace();
        result.mModel = GraphicsColorSpaceModel.MONOCHROME;
        return result;
    }

    @CFunction("CGColorSpaceCreateDeviceRGB")
    public static GraphicsColorSpace createDeviceRGB() {
        GraphicsColorSpace result = new GraphicsColorSpace();
        result.mModel = GraphicsColorSpaceModel.RGB;
        return result;
    }

    @CFunction("CGColorSpaceCreateIndexed")
    public static GraphicsColorSpace createIndexed(GraphicsColorSpace baseSpace, int lastIndex, byte[] colorTable) {
        GraphicsColorSpace result = new GraphicsColorSpace();
        result.mModel = GraphicsColorSpaceModel.INDEXED;
        result.mIndexedBaseSpace = baseSpace;
//        result.indexedColorTableCount = baseSpace.getComponents() * (lastIndex + 1);
        result.mIndexedColorTable = colorTable;
        return result;
    }

    @CFunction("CGColorSpaceCreatePattern")
    public static GraphicsColorSpace createPattern(GraphicsColorSpace baseSpace) {
        GraphicsColorSpace result = new GraphicsColorSpace();
        result.mModel = GraphicsColorSpaceModel.PATTERN;
        result.mPatternBaseSpace = baseSpace;
        return result;
    }

    @CFunction("CGColorSpaceCreateWithName")
    public static GraphicsColorSpace createWithName(String name) {
        GraphicsColorSpace result = new GraphicsColorSpace();
        result.mModel = GraphicsColorSpaceModel.UNKNOWN;
        result.mName = name;
        return result;
    }

    @CFunction("CGColorSpaceGetNumberOfComponents")
    public int getNumberOfComponents() {
        switch (mModel) {
            case MONOCHROME:
                return 1;
            case RGB:
                return 3;
            case CMYK:
                return 4;
            case INDEXED:
                return 1;
//            case kCGColorSpaceModelDeviceN:
//                return ((O2ColorSpace_DeviceN *)self)->_numberOfComponents;
            default:
                return 0;
        }
    }

    @CFunction("CGColorSpaceGetBaseColorSpace")
    public GraphicsColorSpace getBaseColorSpace() {
        switch (getModel()) {
            case PATTERN:
            case INDEXED:
                return mIndexedBaseSpace;
            default:
                return null;
        }
    }

    @CFunction("CGColorSpaceGetModel")
    public GraphicsColorSpaceModel getModel() {
        return mModel;
    }

    @CFunction("CGColorSpaceGetColorTable")
    public byte[] getColorTable() {
        return mIndexedColorTable.clone();
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof GraphicsColorSpace)) {
            return false;
        }
        GraphicsColorSpace colorspace2 = (GraphicsColorSpace) other;
        if (colorspace2 == null || mModel != colorspace2.mModel) {
            return false;
        }
        switch (mModel) {
            case INDEXED:
                return mIndexedBaseSpace.equals(colorspace2.getBaseColorSpace()) && Arrays.equals(mIndexedColorTable,
                        colorspace2.getColorTable());
            case PATTERN:
                return mPatternBaseSpace.equals(colorspace2.mPatternBaseSpace);
            case LAB:
                return Arrays.equals(mLabBlackPoint, colorspace2.mLabBlackPoint) && Arrays.equals(mLabRange,
                        colorspace2.mLabRange) && Arrays.equals(mLabWhitePoint, colorspace2.mLabWhitePoint);
        }
        if (mCalibrated) {
            //TODO Check
            return colorspace2.mCalibrated && mCalibratedGrayGamma == colorspace2.mCalibratedGrayGamma && Arrays
                    .equals(mCalibratedBlackPoint, colorspace2.mCalibratedBlackPoint) && Arrays.equals(mCalibratedRGBGamma, colorspace2.mCalibratedRGBGamma) && Arrays.equals(mCalibratedWhitePoint, colorspace2.mCalibratedWhitePoint);
        }
        return true;
    }
}
