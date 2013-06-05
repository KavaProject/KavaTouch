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
import org.kavaproject.kavatouch.foundation.Coder;
import org.kavaproject.kavatouch.imageio.GraphicsImageProperties;
import org.kavaproject.kavatouch.imageio.GraphicsImageSource;
import org.kavaproject.kavatouch.runtime.SEL;
import org.kavaproject.kavatouch.uikit.staging.ProcessingImage;
import org.kavaproject.kavatouch.util.NotImplementedException;

import java.nio.ByteBuffer;
import java.util.List;

public class DefaultUIImage implements UIImage {
    private final UIImageFactory mUIImageFactory;
    private final UIGraphics mUIGraphics;
    private float mScale;
    private GraphicsImageSource mImageSource;
    private GraphicsImage mGraphicsImage;
    private GraphicsSize mSize;
    private UIImageOrientation mImageOrientation = UIImageOrientation.UP;
    private UIImageResizingMode mResizingMode = UIImageResizingMode.TILE;

    protected DefaultUIImage(Coder decoder, UIImageFactory uiImageFactory, UIGraphics uiGraphics) {
        mUIImageFactory = uiImageFactory;
        mUIGraphics = uiGraphics;
        //TODO
    }

    protected DefaultUIImage(GraphicsImageSource imageSource, float scale, UIImageFactory uiImageFactory,
                             UIGraphics uiGraphics) {
        this(uiImageFactory, uiGraphics);
        mImageSource = imageSource;
        mScale = scale;
    }

    protected DefaultUIImage(UIImageFactory uiImageFactory, UIGraphics uiGraphics) {
        mUIImageFactory = uiImageFactory;
        mUIGraphics = uiGraphics;
    }

    protected DefaultUIImage(GraphicsImage graphicsImage, float scale, UIImageOrientation imageOrientation,
                             UIImageFactory uiImageFactory, UIGraphics uiGraphics) {
        this(uiImageFactory, uiGraphics);
        mGraphicsImage = graphicsImage;
        mScale = scale;
        mImageOrientation = imageOrientation;
    }

    @Override
    public UIImage createImage(UIEdgeInsets alignmentInsets) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public UIImage resizableImage(UIEdgeInsets capInsets) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public UIImage resizableImage(UIEdgeInsets capInsets, UIImageResizingMode resizingMode) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public UIImage stretchableImage(int leftCapWidth, int toCapWidth) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public UIImageOrientation getImageOrientation() {
        return mImageOrientation;
    }

    @Override
    public float getScale() {
        return mScale;
    }

    @Override
    public UIImageResizingMode getResizingMode() {
        return mResizingMode;
    }

    @Override
    public ProcessingImage toProcessingImage() {
        throw new NotImplementedException(); //TODO
//        return mCIImage;
    }

    @Override
    public List<UIImage> getImages() {
        return null; //TODO
    }

    @Override
    public double getDuration() {
        throw new NotImplementedException(); //TODO
//        return mDuration;
    }

    @Override
    public UIEdgeInsets getCapInsets() {
        throw new NotImplementedException(); //TODO
//        return mCapInsets;
    }

    @Override
    public UIEdgeInsets getAlignmentRectInsets() {
        throw new NotImplementedException(); //TODO
//        return mAlignmentRectInsets;
    }

    @Override
    public int getLeftCapWidth() {
        return 0; //TODO
    }

    @Override
    public int getTopCapHeight() {
        return 0; //TODO
    }

    @Override
    public void draw(GraphicsPoint point) {
        draw(new GraphicsRect(point, getSize()));
    }

    @Override
    public void draw(GraphicsRect rect) {
        if (rect.size.height > 0 && rect.size.width > 0) {
            drawInRectFromRect(rect, GraphicsRect.NULL);
        }
    }

    @Override
    public GraphicsImage toGraphicsImage() {
        if (mGraphicsImage == null && mImageSource != null) {
            mGraphicsImage = mImageSource.createImage(0, null);
            mImageSource = null;
        }
        return mGraphicsImage;
    }

    @Override
    public GraphicsSize getSize() {
        if (mSize != null) {
            return mSize;
        }
        if (mGraphicsImage != null) {
            mSize = new GraphicsSize(mGraphicsImage.getWidth() / mScale, mGraphicsImage.getHeight() / mScale);
            return mSize;
        }
        GraphicsImageProperties properties = mImageSource.copyProperties(0, null);
        mSize = new GraphicsSize(properties.pixelWidth / mScale, properties.pixelHeight / mScale);
        return mSize;
    }

    @Override
    public void draw(GraphicsPoint point, GraphicsBlendMode blendMode, float alpha) {
        draw(new GraphicsRect(point, getSize()), blendMode, alpha);
    }

    @Override
    public void draw(GraphicsRect rect, GraphicsBlendMode blendMode, float alpha) {
        GraphicsContext ctx = mUIGraphics.getCurrentContext();
        ctx.saveGState();
        ctx.setBlendMode(blendMode);
        ctx.setAlpha(alpha);
        draw(rect);
        ctx.restoreGState();
    }

    @Override
    public void drawAsPattern(GraphicsRect rect) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public ByteBuffer jpegRepresentation(float compressionQuality) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public ByteBuffer pngRepresentation() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void writeToSavedPhotosAlbum(Object completionTarget, SEL completionSelector, Object contextInfo) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public UIImageFactory getFactory() {
        return mUIImageFactory;
    }

    private void drawInRectFromRect(GraphicsRect rect, GraphicsRect fromRect) {
        GraphicsImage image = toGraphicsImage();
        GraphicsContext ctx = mUIGraphics.getCurrentContext();
        ctx.saveGState();
        ctx.translateCTM(rect.origin.x, rect.origin.y + rect.size.height);
        ctx.scaleCTM(1, -1);
        rect = new GraphicsRect(GraphicsPoint.ZERO, rect.size);
        if (fromRect.isNull()) {
            ctx.drawImage(rect, image);
        } else {
            fromRect = new GraphicsRect(fromRect.origin.x * mScale, fromRect.origin.y * mScale,
                    fromRect.size.width * mScale, fromRect.size.height * mScale);
            GraphicsImage tempImage = image.createWithImageInRect(fromRect);
            ctx.drawImage(rect, tempImage);
        }
        ctx.restoreGState();
    }

    @Override
    public void encode(Coder encoder) {
        throw new NotImplementedException(); //TODO
    }
}
