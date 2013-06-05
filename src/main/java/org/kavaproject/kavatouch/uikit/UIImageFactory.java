/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.uikit;

import org.kavaproject.kavatouch.coregraphics.GraphicsImage;
import org.kavaproject.kavatouch.foundation.Coder;
import org.kavaproject.kavatouch.foundation.CodingFactory;
import org.kavaproject.kavatouch.internal.OccClass;
import org.kavaproject.kavatouch.internal.OccClassMethod;
import org.kavaproject.kavatouch.internal.OccInstanceMethod;
import org.kavaproject.kavatouch.uikit.staging.ProcessingImage;

import java.nio.ByteBuffer;
import java.util.List;

@OccClass("UIImage")
public interface UIImageFactory extends CodingFactory {
    @OccClassMethod("imageNamed:")
    UIImage create(String name);

    @OccInstanceMethod("initWithContentsOfFile:")
    UIImage createWithPath(String filePath);

    @OccClassMethod("animatedImageNamed:duration:")
    UIImage createAnimatedImage(String name, double durationSeconds);

    @OccClassMethod("animatedImageWithImages:duration:")
    UIImage createAnimatedImage(List<UIImage> images, double secondsDuration);

    @OccClassMethod("animatedResizableImageNamed:capInsets:duration:")
    UIImage createAnimatedResizableImage(String name, UIEdgeInsets capInsets, double durationSeconds);

    @OccClassMethod("animatedResizableImageNamed:capInsets:resizingMode:duration:")
    UIImage createAnimatedResizableImage(String name, UIEdgeInsets capInsets, UIImageResizingMode resizingMode,
                                         double durationSeconds);

    @OccInstanceMethod("initWithData:")
    UIImage create(ByteBuffer data);

    @OccInstanceMethod("initWithData:scale:")
    UIImage create(ByteBuffer data, float scale);

    @OccInstanceMethod("initWithCGImage:")
    UIImage create(GraphicsImage graphicsImage);

    @OccInstanceMethod("initWithCGImage:scale:orientation:")
    UIImage create(GraphicsImage bridgedObject, float scale, UIImageOrientation orientation);

    @OccInstanceMethod("initWithCIImage:")
    UIImage create(ProcessingImage processingImage);

    @OccInstanceMethod("initWithCIImage:scale:orientation:")
    UIImage create(ProcessingImage processingImage, float scale, UIImageOrientation orientation);

    @Override
    UIImage create(Coder decoder);
}
