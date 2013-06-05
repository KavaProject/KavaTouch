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
import android.graphics.Canvas;
import org.kavaproject.kavatouch.internal.CFunction;
import org.kavaproject.kavatouch.internal.Header;
import org.kavaproject.kavatouch.internal.OpaqueType;
import org.kavaproject.kavatouch.util.NotImplementedException;

@Header("CGBitmapContext")
@OpaqueType(value = "CGBitmapContextRef")
public class GraphicsBitmapContext extends GraphicsContext {
    public final Internal internal = new Internal();
    private Bitmap mBitmap;

    @CFunction(value = "CGBitmapContextCreate")
    public GraphicsBitmapContext(byte[] data, int width, int height, int bitsPerComponent, int bytesPerRow,
                                 GraphicsColorSpace colorspace, GraphicsBitmapInfo bitmapInfo) {
        this(createBitmap(width, height));
    }

    private static Bitmap createBitmap(int width, int height) {
        Bitmap.Config config = Bitmap.Config.ARGB_8888;
        // bitmap.setHasAlpha(!bitmapInfo.contains(CGBitmapInfo.kCGImageAlphaNone)); //Android 2.3.7
        return Bitmap.createBitmap(width, height, config);
    }

    private GraphicsBitmapContext(Bitmap bitmap) {
        super(createCanvas(bitmap));
        mBitmap = bitmap;
    }

    private static Canvas createCanvas(Bitmap bitmap) {
        Canvas canvas = new Canvas(bitmap);
        //Quartz' default coordinate system is flipped compared to Androids.
        canvas.translate(0, bitmap.getHeight());
        canvas.scale(1, -1);
        return canvas;
    }

    @CFunction(value = "CGBitmapContextCreateWithData")
    public GraphicsBitmapContext(byte[] data, int width, int height, int bitsPerComponent, int bytesPerRow,
                                 GraphicsColorSpace space, GraphicsBitmapInfo bitmapInfo,
                                 GraphicsBitmapContextReleaseDataCallback releaseCallback, Object releaseInfo) {
        super(null);
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CGBitmapContextCreateImage")
    public GraphicsImage toImage() {
        return GraphicsImage.Internal.create(mBitmap);
    }

    @CFunction("CGBitmapContextGetBitmapInfo")
    public GraphicsBitmapInfo getBitmapInfo() {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CGBitmapContextGetAlphaInfo")
    public GraphicsImageAlphaInfo getAlphaInfo() {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CGBitmapContextGetBitsPerComponent")
    public int getBitsPerComponent() {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CGBitmapContextGetBitsPerPixel")
    public int getBitsPerPixel() {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CGBitmapContextGetBytesPerRow")
    public int getBytesPerRow() {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CGBitmapContextGetColorSpace")
    public GraphicsColorSpace getColorSpace() {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CGBitmapContextGetData")
    public byte[] getData() {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CGBitmapContextGetHeight")
    public int getHeight() {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CGBitmapContextGetWidth")
    public int getWidth() {
        throw new NotImplementedException(); //TODO
    }

    public static class Internal {
        public float scale;
    }
}
