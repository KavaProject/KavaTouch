/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.imageio;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Rect;
import org.kavaproject.kavatouch.corefoundation.CoreURL;
import org.kavaproject.kavatouch.coregraphics.GraphicsDataProvider;
import org.kavaproject.kavatouch.coregraphics.GraphicsImage;
import org.kavaproject.kavatouch.internal.CFunction;
import org.kavaproject.kavatouch.internal.Header;
import org.kavaproject.kavatouch.internal.OpaqueType;
import org.kavaproject.kavatouch.mobilecoreservices.UTType;
import org.kavaproject.kavatouch.util.NotImplementedException;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;

@Header("CGImageSource")
@OpaqueType(value = "CGImageSourceRef")
public class GraphicsImageSource {
    public final Internal internal = new Internal();
    private final GraphicsImageOptions mOptions;
    private final GraphicsImage mImage;
    private GraphicsDataProvider mDataProvider;

    private GraphicsImageSource(ByteBuffer data, GraphicsImageOptions options) {
        this(new GraphicsDataProvider(data), options);
    }

    private GraphicsImageSource(GraphicsDataProvider dataProvider, GraphicsImageOptions options) {
        this(options);
        mDataProvider = dataProvider;
    }

    private GraphicsImageSource(GraphicsImageOptions options) {
        if (options != null) {
            mOptions = new GraphicsImageOptions(options);
        } else {
            mOptions = new GraphicsImageOptions();
        }
        mImage = GraphicsImage.Internal.create(this);
    }

    GraphicsImageSource() {
        throw new UnsupportedOperationException();
    }

    @CFunction(value = "CGImageSourceCreateWithDataProvider")
    public static GraphicsImageSource createWithDataProvider(GraphicsDataProvider provider,
                                                             GraphicsImageOptions options) {
        return new GraphicsImageSource(provider, options);
    }

    @CFunction(value = "CGImageSourceCreateWithData")
    public static GraphicsImageSource createWithData(ByteBuffer data, GraphicsImageOptions options) {
        return new GraphicsImageSource(data, options);
    }

    @CFunction(value = "CGImageSourceCreateWithURL")
    public static GraphicsImageSource createWithURL(CoreURL url, GraphicsImageOptions options) {
        GraphicsDataProvider dataProvider = GraphicsDataProvider.create(url);
        return dataProvider != null ? new GraphicsImageSource(dataProvider, options) : null;
    }

    @CFunction(value = "CGImageSourceCopyTypeIdentifiers")
    public static List<UTType> copyTypeIdentifiers() {
        return Arrays.asList(UTType.JPEG, UTType.PNG);
    }

    @CFunction(value = "CGImageSourceCreateImageAtIndex")
    public GraphicsImage createImage(int index, GraphicsImageOptions options) {
        return mImage;
    }

    @CFunction(value = "CGImageSourceCreateThumbnailAtIndex")
    public GraphicsImage createThumbnail(int index, GraphicsImageThumbnailOptions options) {
        throw new NotImplementedException();
    }

    @CFunction(value = "CGImageSourceCreateIncremental")
    public GraphicsImageSource createIncremental(GraphicsImageOptions options) {
        return new GraphicsImageSource(options);
    }

    @CFunction(value = "CGImageSourceUpdateData")
    public void updateData(ByteBuffer data, boolean finalX) {
        throw new NotImplementedException();
    }

    @CFunction(value = "CGImageSourceUpdateDataProvider")
    public void updateDataProvider(GraphicsDataProvider provider, boolean finalX) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction(value = "CGImageSourceGetType")
    public UTType getType() {
        throw new NotImplementedException();
    }

    @CFunction(value = "CGImageSourceGetCount")
    public int getCount() {
        return 1;
    }

    @CFunction(value = "CGImageSourceCopyProperties")
    public GraphicsImageProperties copyProperties(GraphicsImageOptions options) {
        throw new NotImplementedException();
    }

    @CFunction(value = "CGImageSourceCopyPropertiesAtIndex")
    public GraphicsImageProperties copyProperties(int index, GraphicsImageOptions options) {
        GraphicsImageProperties properties = new GraphicsImageProperties();
        properties.pixelWidth = mImage.getWidth();
        properties.pixelHeight = mImage.getHeight();
        return properties;
    }

    @CFunction(value = "CGImageSourceGetStatus")
    public GraphicsImageSourceStatus getStatus() {
        throw new NotImplementedException();
    }

    @CFunction(value = "CGImageSourceGetStatusAtIndex")
    public GraphicsImageSourceStatus getStatus(int index) {
        throw new NotImplementedException();
    }

    public class Internal {
        public Bitmap decode(BitmapFactory.Options options) {
            if (mDataProvider.internal.path != null) {
                return BitmapFactory.decodeFile(mDataProvider.internal.path, options);
            } else {
                ByteBuffer data = mDataProvider.copyData();
                byte[] bytes = data.array();
                return BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);
            }
        }

        public Bitmap decodeRegion(Rect rect, BitmapFactory.Options options) {
            try {
                if (mDataProvider.internal.path != null) {
                    return BitmapRegionDecoder.newInstance(mDataProvider.internal.path, true).decodeRegion(rect,
                            options);
                } else {
                    ByteBuffer data = mDataProvider.copyData();
                    byte[] bytes = data.array();
                    return BitmapRegionDecoder.newInstance(bytes, 0, bytes.length, true).decodeRegion(rect, options);
                }
            } catch (IOException e) {
                return null;
            }
        }
    }
}
