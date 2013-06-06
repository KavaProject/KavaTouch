/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.uikit;

import org.kavaproject.kavatouch.DeviceHandle;
import org.kavaproject.kavatouch.corefoundation.CoreURL;
import org.kavaproject.kavatouch.corefoundation.CoreURLPathStyle;
import org.kavaproject.kavatouch.coregraphics.GraphicsImage;
import org.kavaproject.kavatouch.foundation.BundleFactory;
import org.kavaproject.kavatouch.foundation.Coder;
import org.kavaproject.kavatouch.foundation.FoundationString;
import org.kavaproject.kavatouch.foundation.FoundationStringFactory;
import org.kavaproject.kavatouch.imageio.GraphicsImageSource;
import org.kavaproject.kavatouch.internal.ImageScaleModifier;
import org.kavaproject.kavatouch.uikit.staging.ProcessingImage;
import org.kavaproject.kavatouch.util.NotImplementedException;

import org.kavaproject.kavatouch.util.inject.Inject;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultUIImageFactory implements UIImageFactory {
    private final BundleFactory mBundleFactory;
    private final UIGraphics mUIGraphics;
    private final FoundationStringFactory mFoundationStringFactory;
    private final DeviceHandle mDeviceHandle;
    private Map<String, UIImage> mImageCache = new HashMap<String, UIImage>();

    @Inject
    protected DefaultUIImageFactory(BundleFactory bundleFactory, UIGraphics uiGraphics,
                                    FoundationStringFactory foundationStringFactory, DeviceHandle deviceHandle) {
        mBundleFactory = bundleFactory;
        mUIGraphics = uiGraphics;
        mFoundationStringFactory = foundationStringFactory;
        mDeviceHandle = deviceHandle;
    }

    @Override
    public UIImage create(String name) {
        String resourcePath = mBundleFactory.mainBundle().resourcePath();
        FoundationString extension = mFoundationStringFactory.create(name).pathExtension();
        FoundationString nameWithoutExtension = mFoundationStringFactory.create(name);
        if (extension.toString().equals("")) {
            extension = mFoundationStringFactory.create("png");
        } else {
            nameWithoutExtension = mFoundationStringFactory.create(name).deletePathExtension();
        }
        String filePath = mFoundationStringFactory.create(resourcePath).appendPathComponent(nameWithoutExtension)
                .appendPathExtension(extension).toString();
        UIImage image = createWithPath(filePath);
        if (image == null) {
            FoundationString dashedName = nameWithoutExtension.replace("_", "-");
            filePath = mFoundationStringFactory.create(resourcePath).appendPathComponent(dashedName)
                    .appendPathExtension(extension).toString();
            image = createWithPath(filePath);
        }
        return image;
    }

    @Override
    public UIImage createWithPath(String filePath) {
        UIImage image = mImageCache.get(filePath);
        if (image == null) {
            GraphicsImageSource imageSource = null;
            float scale = 0;
            outerloop:
            for (ImageScaleModifier scaleModifier : mDeviceHandle.getImageScaleModifiers()) {
                for (String deviceModifier : mDeviceHandle.getDeviceModifiers()) {
                    imageSource = GraphicsImageSource.createWithURL(pathWithModifiers(filePath, scaleModifier.name,
                            deviceModifier), null);
                    if (imageSource != null) {
                        scale = scaleModifier.scale;
                        break outerloop;
                    }
                }
            }
            if (imageSource != null) {
                image = new DefaultUIImage(imageSource, scale, this, mUIGraphics);
                mImageCache.put(filePath, image);
            }
        }
        return image;
    }

    @Override
    public UIImage createAnimatedImage(String name, double durationSeconds) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public UIImage createAnimatedImage(List<UIImage> images, double secondsDuration) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public UIImage createAnimatedResizableImage(String name, UIEdgeInsets capInsets, double durationSeconds) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public UIImage createAnimatedResizableImage(String name, UIEdgeInsets capInsets,
                                                UIImageResizingMode resizingMode, double durationSeconds) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public UIImage create(ByteBuffer data) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public UIImage create(ByteBuffer data, float scale) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public UIImage create(GraphicsImage graphicsImage) {
        return create(graphicsImage, 1, UIImageOrientation.UP);
        //TODO Check if we should use UIScreen>>scale instead of 1
    }

    @Override
    public UIImage create(GraphicsImage bridgedObject, float scale, UIImageOrientation orientation) {
        return new DefaultUIImage(bridgedObject, scale, orientation, this, mUIGraphics);
    }

    @Override
    public UIImage create(ProcessingImage processingImage) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public UIImage create(ProcessingImage processingImage, float scale, UIImageOrientation orientation) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public UIImage create(Coder decoder) {
        return new DefaultUIImage(decoder, this, mUIGraphics);
    }

    private CoreURL pathWithModifiers(String filePath, String scaleModifier, String deviceModifier) {
        String extension = mFoundationStringFactory.create(filePath).pathExtension().toString();
        String pathWithoutExtension = mFoundationStringFactory.create(filePath).deletePathExtension().toString();
        return new CoreURL(pathWithoutExtension + scaleModifier + deviceModifier + "." + extension, CoreURLPathStyle.HFS, false);
    }
}
