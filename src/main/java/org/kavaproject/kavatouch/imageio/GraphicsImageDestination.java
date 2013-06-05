/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.imageio;

import org.apache.http.util.ByteArrayBuffer;
import org.kavaproject.kavatouch.corefoundation.CoreURL;
import org.kavaproject.kavatouch.coregraphics.GraphicsImage;
import org.kavaproject.kavatouch.coregraphics.staging.GraphicsDataConsumer;
import org.kavaproject.kavatouch.internal.CFunction;
import org.kavaproject.kavatouch.internal.Header;
import org.kavaproject.kavatouch.internal.OpaqueType;
import org.kavaproject.kavatouch.mobilecoreservices.UTType;
import org.kavaproject.kavatouch.util.NotImplementedException;

import java.util.Arrays;
import java.util.List;

@Header("CGImageDestination")
@OpaqueType("CGImageDestinationRef")
public class GraphicsImageDestination {
    @CFunction(value = "CGImageDestinationCreateWithDataConsumer")
    public static GraphicsImageDestination createWithDataConsumer(GraphicsDataConsumer consumer, UTType type,
                                                                  int count, Object options) {
        throw new NotImplementedException();
    }

    @CFunction(value = "CGImageDestinationCreateWithData")
    public static GraphicsImageDestination createWithData(ByteArrayBuffer data, UTType type, int count,
                                                          Object options) {
        throw new NotImplementedException();
    }

    @CFunction(value = "CGImageDestinationCreateWithURL")
    public static GraphicsImageDestination createWithURL(CoreURL url, UTType type, int count, Object options) {
        throw new NotImplementedException();
    }

    @CFunction(value = "CGImageDestinationCopyTypeIdentifiers")
    public static List<UTType> copyTypeIdentifiers() {
        return Arrays.asList(UTType.JPEG, UTType.PNG);
    }

    @CFunction(value = "CGImageDestinationAddImage")
    public void addImage(GraphicsImage image, GraphicsImageDestinationProperties properties) {
        throw new NotImplementedException();
    }

    @CFunction(value = "CGImageDestinationAddImageFromSource")
    public void addImage(GraphicsImageSource source, int index, GraphicsImageDestinationProperties properties) {
        throw new NotImplementedException();
    }

    @CFunction(value = "CGImageDestinationSetProperties")
    public void setProperties(GraphicsImageDestinationProperties properties) {
        throw new NotImplementedException();
    }

    @CFunction(value = "CGImageDestinationFinalize")
    public boolean finalizeX() {
        throw new NotImplementedException();
    }
}
