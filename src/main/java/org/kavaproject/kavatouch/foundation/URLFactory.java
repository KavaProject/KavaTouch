/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.foundation;

import org.kavaproject.kavatouch.corefoundation.CoreURL;
import org.kavaproject.kavatouch.foundation.staging.URLBookmarkFileCreationOptions;
import org.kavaproject.kavatouch.foundation.staging.URLBookmarkResolutionOptions;
import org.kavaproject.kavatouch.internal.OccClass;
import org.kavaproject.kavatouch.internal.OccClassMethod;
import org.kavaproject.kavatouch.internal.OccInstanceMethod;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;

@OccClass("NSURL")
public interface URLFactory extends SecureCodingFactory, CodingFactory, CoreBridgeFactory<CoreURL> {
    @Override
    boolean supportsSecureCoding();

    @Override
    URL create(CoreURL coreURL);

    @OccInstanceMethod("initWithScheme:host:path:")
    URL create(String scheme, String host, String path);

    @OccClassMethod("URLWithString:")
    URL createURL(String urlString);

    @OccInstanceMethod("initWithString:relativeToURL:")
    URL create(String urlString, URL baseURL);

    @OccInstanceMethod("initFileURLWithPath:isDirectory:")
    URL createWithPath(String path, boolean isDir);

    @OccInstanceMethod("initFileURLWithPath:")
    URL createWithPath(String path);

    @OccClassMethod("fileURLWithPathComponents:")
    URL createFileURL(List<String> components);

    @OccInstanceMethod("initByResolvingBookmarkData:options:relativeToURL:bookmarkDataIsStale:error:")
    URL create(ByteBuffer bookmarkData, URLBookmarkResolutionOptions options, URL baseURL, boolean isStale) throws RuntimeException;

    @OccClassMethod("bookmarkDataWithContentsOfURL:error:")
    ByteBuffer createBookmarkData(URL bookmarkFileURL) throws RuntimeException;

    @OccClassMethod("writeBookmarkData:toURL:options:error:")
    boolean writeBookmarkData(ByteBuffer bookmarkData, URL bookmarkFileURL, URLBookmarkFileCreationOptions options)
            throws RuntimeException;

    @OccInstanceMethod("resourceValuesForKeys:fromBookmarkData:")
    Map<String, Object> getResourceValues(List<String> keys, ByteBuffer bookmarkData);
}
