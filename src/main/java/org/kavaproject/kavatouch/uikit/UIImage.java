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
import org.kavaproject.kavatouch.foundation.Coding;
import org.kavaproject.kavatouch.internal.*;
import org.kavaproject.kavatouch.runtime.Creatable;
import org.kavaproject.kavatouch.runtime.SEL;
import org.kavaproject.kavatouch.uikit.staging.ProcessingImage;

import java.nio.ByteBuffer;
import java.util.List;

@Header("UIImage")
@OccClass("UIImage")
public interface UIImage extends Coding, Creatable {
    @OccInstanceMethod("imageWithAlignmentRectInsets:")
    UIImage createImage(UIEdgeInsets alignmentInsets);

    @OccInstanceMethod("resizableImageWithCapInsets:")
    UIImage resizableImage(UIEdgeInsets capInsets);

    @OccInstanceMethod("resizableImageWithCapInsets:resizingMode:")
    UIImage resizableImage(UIEdgeInsets capInsets, UIImageResizingMode resizingMode);

    @OccInstanceMethod("stretchableImageWithLeftCapWidth:topCapHeight:")
    @Deprecated
    UIImage stretchableImage(int leftCapWidth, int toCapWidth);

    @OccInstanceProperty(value = "imageOrientation")
    UIImageOrientation getImageOrientation();

    @OccInstanceProperty(value = "scale")
    float getScale();

    @OccInstanceProperty(value = "resizingMode")
    UIImageResizingMode getResizingMode();

    @OccInstanceProperty(value = "CIImage")
    ProcessingImage toProcessingImage();

    @OccInstanceProperty(value = "images")
    List<UIImage> getImages();

    @OccInstanceProperty(value = "duration")
    double getDuration();

    @OccInstanceProperty(value = "capInsets")
    UIEdgeInsets getCapInsets();

    @OccInstanceProperty(value = "alignmentRectInsets")
    UIEdgeInsets getAlignmentRectInsets();

    @OccInstanceProperty(value = "leftCapWidth")
    @Deprecated
    int getLeftCapWidth();

    @OccInstanceProperty(value = "topCapHeight")
    @Deprecated
    int getTopCapHeight();

    @OccInstanceMethod("drawAtPoint:")
    void draw(GraphicsPoint point);

    @OccInstanceMethod("drawInRect:")
    void draw(GraphicsRect rect);

    @OccInstanceProperty(value = "CGImage")
    GraphicsImage toGraphicsImage();

    @OccInstanceProperty(value = "size")
    GraphicsSize getSize();

    @OccInstanceMethod("drawAtPoint:blendMode:alpha:")
    void draw(GraphicsPoint point, GraphicsBlendMode blendMode, float alpha);

    @OccInstanceMethod("drawInRect:blendMode:alpha:")
    void draw(GraphicsRect rect, GraphicsBlendMode blendMode, float alpha);

    @OccInstanceMethod("drawAsPatternInRect:")
    void drawAsPattern(GraphicsRect rect);

    @CFunction(value = "UIImageJPEGRepresentation", tokenGroup = "UIKit")
    ByteBuffer jpegRepresentation(float compressionQuality);

    @CFunction(value = "UIImagePNGRepresentation", tokenGroup = "UIKit")
    ByteBuffer pngRepresentation();

    @Header("UIImagePickerController")
    @CFunction(value = "UIImagePNGRepresentation", tokenGroup = "UIKit")
    void writeToSavedPhotosAlbum(Object completionTarget, SEL completionSelector, Object contextInfo);

    @Override
    UIImageFactory getFactory();
}
