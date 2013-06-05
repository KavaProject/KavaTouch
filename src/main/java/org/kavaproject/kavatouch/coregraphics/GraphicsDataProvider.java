/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.coregraphics;

import org.kavaproject.kavatouch.corefoundation.CoreURL;
import org.kavaproject.kavatouch.internal.CFunction;
import org.kavaproject.kavatouch.internal.CTypedef;
import org.kavaproject.kavatouch.internal.Header;
import org.kavaproject.kavatouch.internal.OpaqueType;
import org.kavaproject.kavatouch.util.NotImplementedException;

import java.io.File;
import java.nio.ByteBuffer;

@Header("CGDataProvider")
@OpaqueType(value = "CGDataProviderRef")
public class GraphicsDataProvider {
    public final Internal internal = new Internal();

    private GraphicsDataProvider() {
    }

    @CFunction("CGDataProviderCreateWithCFData")
    public GraphicsDataProvider(ByteBuffer data) {
        throw new NotImplementedException();
    }

    @CFunction("CGDataProviderCreateWithData")
    public GraphicsDataProvider(byte[] info, byte[] data, int size, CGDataProviderReleaseDataCallback releaseData) {
        throw new NotImplementedException();
    }

    @CFunction("CGDataProviderCreateDirect")
    public static GraphicsDataProvider createDirect(byte[] info, long size, CGDataProviderDirectCallbacks callbacks) {
        throw new NotImplementedException();
    }

    @CFunction("CGDataProviderCreateSequential")
    public static GraphicsDataProvider createSequential(byte[] info, CGDataProviderSequentialCallbacks callbacks) {
        throw new NotImplementedException();
    }

    @CFunction("CGDataProviderCreateWithURL")
    public static GraphicsDataProvider create(CoreURL url) {
        String path = url.copyPath();
        return create(path);
    }

    @CFunction("CGDataProviderCreateWithFilename")
    public static GraphicsDataProvider create(String filename) {
        if (!new File(filename).exists()) {
            return null;
        }
        GraphicsDataProvider provider = new GraphicsDataProvider();
        provider.internal.path = filename;
        return provider;
    }

    @CFunction("CGDataProviderCopyData")
    public ByteBuffer copyData() {
        throw new NotImplementedException();
    }

    @CFunction("CGDataProviderGetBytesCallback")
    public static interface CGDataProviderGetBytesCallback {
    }

    @CFunction("CGDataProviderReleaseInfoCallback")
    public static interface CGDataProviderReleaseInfoCallback {
    }

    @CFunction("CGDataProviderRewindCallback")
    public static interface CGDataProviderRewindCallback {
    }

    @CFunction("CGDataProviderSkipBytesCallback")
    public static interface CGDataProviderSkipBytesCallback {
    }

    @CFunction("CGDataProviderSkipForwardCallback")
    public static interface CGDataProviderSkipForwardCallback {
    }

    @CFunction("CGDataProviderGetBytePointerCallback")
    public static interface CGDataProviderGetBytePointerCallback {
    }

    @CFunction("CGDataProviderGetBytesAtOffsetCallback")
    public static interface CGDataProviderGetBytesAtOffsetCallback {
    }

    @CFunction("CGDataProviderReleaseBytePointerCallback")
    public static interface CGDataProviderReleaseBytePointerCallback {
    }

    @CFunction("CGDataProviderReleaseDataCallback")
    public static interface CGDataProviderReleaseDataCallback {
    }

    @CFunction("CGDataProviderGetBytesAtPositionCallback")
    public static interface CGDataProviderGetBytesAtPositionCallback {
    }

    public static class Internal {
        public String path;
    }

    @CTypedef("CGDataProviderCallbacks")
    public static class CGDataProviderCallbacks {
        public CGDataProviderGetBytesCallback getBytes;
        public CGDataProviderSkipBytesCallback skipBytes;
        public CGDataProviderRewindCallback rewind;
        public CGDataProviderReleaseInfoCallback releaseProvider;
    }

    @CTypedef("CGDataProviderDirectAccessCallbacks")
    public static class CGDataProviderDirectAccessCallbacks {
        public CGDataProviderGetBytePointerCallback getBytePointer;
        public CGDataProviderReleaseBytePointerCallback releaseBytePointer;
        public CGDataProviderGetBytesAtOffsetCallback getBytes;
        public CGDataProviderReleaseInfoCallback releaseProvider;
    }

    @CTypedef("CGDataProviderDirectCallbacks")
    public static class CGDataProviderDirectCallbacks {
        public CGDataProviderGetBytePointerCallback getBytePointer;
        public CGDataProviderReleaseBytePointerCallback releaseBytePointer;
        public CGDataProviderGetBytesAtPositionCallback getBytesAtPosition;
        public CGDataProviderReleaseInfoCallback releaseInfo;
        int version;
    }

    @CTypedef("CGDataProviderSequentialCallbacks")
    public static class CGDataProviderSequentialCallbacks {
        public CGDataProviderGetBytesCallback getBytes;
        public CGDataProviderSkipForwardCallback skipForward;
        public CGDataProviderRewindCallback rewind;
        public CGDataProviderReleaseInfoCallback releaseInfo;
        int version;
    }
}
