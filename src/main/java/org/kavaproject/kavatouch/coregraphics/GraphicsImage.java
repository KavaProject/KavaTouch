/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.coregraphics;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import org.kavaproject.kavatouch.corefoundation.CoreType;
import org.kavaproject.kavatouch.imageio.GraphicsImageSource;
import org.kavaproject.kavatouch.internal.CFunction;
import org.kavaproject.kavatouch.internal.Header;
import org.kavaproject.kavatouch.internal.OpaqueType;
import org.kavaproject.kavatouch.mobilecoreservices.UTType;
import org.kavaproject.kavatouch.util.AndroidConversions;
import org.kavaproject.kavatouch.util.NotImplementedException;

@Header("CGImage")
@OpaqueType("CGImageRef")
public class GraphicsImage implements Cloneable, CoreType {
    GraphicsRect imageInRect;
    Bitmap bitmap; //TODO LRU cache
    private GraphicsImageSource mSource;
    private UTType mType;
    private int mWidth = -1;
    private int mHeight = -1;
    private GraphicsImageAlphaInfo mAlphaInfo;
    private BitmapFactory.Options mOptions = new BitmapFactory.Options();

    GraphicsImage(Bitmap bitmap) {
        this.bitmap = Bitmap.createBitmap(bitmap);
        mWidth = bitmap.getWidth();
        mHeight = bitmap.getHeight();
        mAlphaInfo = AndroidConversions.toAlphaInfo(bitmap.getConfig());
    }

    GraphicsImage(GraphicsImage image) {
        bitmap = image.bitmap;
        mSource = image.mSource;
        mType = image.mType;
        mWidth = image.mWidth;
        mHeight = image.mHeight;
        imageInRect = image.imageInRect;
        mAlphaInfo = image.mAlphaInfo;
    }

    GraphicsImage(GraphicsDataProvider dataProvider, float[] decode, boolean shouldInterpolate,
                  GraphicsColorRenderingIntent intent, UTType type) {
        this(GraphicsImageSource.createWithDataProvider(dataProvider, null));
        this.mType = type;
    }

    GraphicsImage(GraphicsImageSource source) {
        this.mSource = source;
    }

    @CFunction("CGImageCreate")
    public static GraphicsImage create(int width, int height, int bitsPerComponent, int bitsPerPixel,
                                       int bytesPerRow, GraphicsColorSpace colorspace, GraphicsBitmapInfo bitmapInfo,
                                       GraphicsDataProvider provider, float[] decode, boolean shouldInterpolate,
                                       GraphicsColorRenderingIntent intent) {
        throw new NotImplementedException();
    }

    @CFunction("CGImageCreateWithJPEGDataProvider")
    public static GraphicsImage createWithJPEGDataProvider(GraphicsDataProvider source, float[] decode,
                                                           boolean shouldInterpolate,
                                                           GraphicsColorRenderingIntent intent) {
        return new GraphicsImage(source, decode, shouldInterpolate, intent, UTType.JPEG);
    }

    @CFunction("CGImageCreateWithPNGDataProvider")
    public static GraphicsImage createWithPNGDataProvider(GraphicsDataProvider source, float[] decode,
                                                          boolean shouldInterpolate,
                                                          GraphicsColorRenderingIntent intent) {
        return new GraphicsImage(source, decode, shouldInterpolate, intent, UTType.PNG);
    }

    @CFunction("CGImageMaskCreate")
    public static GraphicsImage createMask(int width, int height, int bitsPerComponent, int bitsPerPixel,
                                           int bytesPerRow, GraphicsDataProvider provider, float[] decode,
                                           boolean shouldInterpolate) {
        throw new NotImplementedException();
    }

    public static Bitmap getBitmap(GraphicsImage image) {
        if (image instanceof GraphicsImage) {
            return ((GraphicsImage) image).bitmap();
        }
        throw new UnsupportedOperationException();
    }

    Bitmap bitmap() {
        if (imageInRect == null) {
            if (bitmap != null) {
                return bitmap;
            } else {
                bitmap = mSource.internal.decode(mOptions);
            }
        } else {
            Rect rect = AndroidConversions.toRect(imageInRect.integral());
            if (bitmap != null) {
                return Bitmap.createBitmap(bitmap, rect.left, rect.top, rect.width(), rect.height());
            } else {
                bitmap = mSource.internal.decodeRegion(rect, mOptions);
            }
        }
        return bitmap;
    }

    @CFunction("CGImageCreateWithImageInRect")
    public GraphicsImage createWithImageInRect(GraphicsRect rect) {
        GraphicsImage res = new GraphicsImage(this);
        if (res.imageInRect != null) {
            throw new NotImplementedException();
        } else {
            res.imageInRect = rect;
        }
        return res;
    }

    @CFunction("CGImageCreateWithMask")
    public GraphicsImage createWithMask(GraphicsImage mask) {
        throw new NotImplementedException();
    }

    @CFunction("CGImageCreateWithMaskingColors")
    public GraphicsImage createWithMaskingColors(float[] components) {
        throw new NotImplementedException();
    }

    @CFunction("CGImageCreateCopyWithColorSpace")
    public GraphicsImage clone(GraphicsColorSpace colorspace) {
        throw new NotImplementedException();
    }

    @CFunction("CGImageGetAlphaInfo")
    public GraphicsImageAlphaInfo getAlphaInfo() {
        if (mAlphaInfo == null) {
            decodeInfo();
        }
        return mAlphaInfo;
    }

    private void decodeInfo() {
        BitmapFactory.Options bfOptions = new BitmapFactory.Options();
        bfOptions.inSampleSize = Integer.MAX_VALUE; //TODO Check what is fastest. We don't need the bitmap pixels.
        Bitmap bitmap = mSource.internal.decode(bfOptions);
        Bitmap.Config config = bitmap.getConfig();
        mAlphaInfo = AndroidConversions.toAlphaInfo(config);
    }

    @CFunction("CGImageGetBitmapInfo")
    public GraphicsBitmapInfo getBitmapInfo() {
        throw new NotImplementedException();
    }

    @CFunction("CGImageGetBitsPerComponent")
    public int getBitsPerComponent() {
        throw new NotImplementedException();
    }

    @CFunction("CGImageGetBitsPerPixel")
    public int getBitsPerPixel() {
        throw new NotImplementedException();
    }

    @CFunction("CGImageGetBytesPerRow")
    public int getBytesPerRow() {
        throw new NotImplementedException();
    }

    @CFunction("CGImageGetColorSpace")
    public GraphicsColorSpace getColorSpace() {
        throw new NotImplementedException();
    }

    @CFunction("CGImageGetDataProvider")
    public GraphicsDataProvider getDataProvider() {
        throw new NotImplementedException();
    }

    @CFunction("CGImageGetDecode")
    public float[] getDecode() {
        throw new NotImplementedException();
    }

    @CFunction("CGImageGetHeight")
    public int getHeight() {
        if (mHeight == -1) {
            decodeBounds();
        }
        return mHeight;
    }

    private void decodeBounds() {
        BitmapFactory.Options bfOptions = new BitmapFactory.Options();
        bfOptions.inJustDecodeBounds = true;
        mSource.internal.decode(bfOptions);
        mWidth = bfOptions.outWidth;
        mHeight = bfOptions.outHeight;
        if (bfOptions.outMimeType != null) {
            mType = AndroidConversions.toUTType(bfOptions.outMimeType);
        }
    }

    @CFunction("CGImageGetShouldInterpolate")
    public boolean getShouldInterpolate() {
        throw new NotImplementedException();
    }

    @CFunction("CGImageGetRenderingIntent")
    public GraphicsColorRenderingIntent getRenderingIntent() {
        throw new NotImplementedException();
    }

    @CFunction("CGImageGetWidth")
    public int getWidth() {
        if (mWidth == -1) {
            decodeBounds();
        }
        return mWidth;
    }

    @CFunction("CGImageIsMask")
    public boolean isMask() {
        throw new NotImplementedException();
    }

    @CFunction("CGImageCopy")
    @Override
    public GraphicsImage clone() {
        return new GraphicsImage(this);
    }

    @Override
    public String toString() {
        return "CGImageRef[" + "]";
    }

    public static class Internal {
        public static GraphicsImage create(GraphicsImageSource imageSource) {
            return new GraphicsImage(imageSource);
        }

        public static GraphicsImage create(Bitmap bitmap) {
            return new GraphicsImage(bitmap);
        }
    }
}
