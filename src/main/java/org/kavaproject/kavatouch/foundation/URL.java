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
import org.kavaproject.kavatouch.foundation.staging.URLBookmarkCreationOptions;
import org.kavaproject.kavatouch.internal.OccInstanceMethod;
import org.kavaproject.kavatouch.runtime.Creatable;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;

public interface URL extends Copying, Coding, Creatable, CoreBridge<CoreURL> {
    @OccInstanceMethod("isEqual:")
    @Override
    boolean equals(Object other);

    @OccInstanceMethod("checkResourceIsReachableAndReturnError:")
    boolean checkResourceIsReachable() throws RuntimeException;

    @OccInstanceMethod("isFileReferenceURL")
    boolean isFileReferenceURL();

    @OccInstanceMethod("isFileURL")
    boolean isFileURL();

    @OccInstanceMethod("absoluteString")
    String absoluteString();

    @OccInstanceMethod("absoluteURL")
    URL absoluteURL();

    @OccInstanceMethod("baseURL")
    URL baseURL();

    @OccInstanceMethod("fragment")
    String fragment();

    @OccInstanceMethod("host")
    String host();

    @OccInstanceMethod("lastPathComponent")
    String lastPathComponent();

    @OccInstanceMethod("parameterString")
    String parameterString();

    @OccInstanceMethod("password")
    String password();

    @OccInstanceMethod("path")
    String path();

    @OccInstanceMethod("pathComponents")
    List<String> pathComponents();

    @OccInstanceMethod("pathExtension")
    String pathExtension();

    @OccInstanceMethod("port")
    Integer port();

    @OccInstanceMethod("query")
    String query();

    @OccInstanceMethod("relativePath")
    String relativePath();

    @OccInstanceMethod("relativeString")
    String relativeString();

    @OccInstanceMethod("resourceSpecifier")
    String resourceSpecifier();

    @OccInstanceMethod("scheme")
    String scheme();

    @OccInstanceMethod("standardizedURL")
    URL standardizedURL();

    @OccInstanceMethod("user")
    String user();

    @OccInstanceMethod("filePathURL")
    URL filePathURL();

    @OccInstanceMethod("fileReferenceURL")
    URL fileReferenceURL();

    @OccInstanceMethod("URLByAppendingPathComponent:")
    URL appendPathComponent(String pathComponent);

    @OccInstanceMethod("URLByAppendingPathComponent:isDirectory:")
    URL appendPathComponent(String pathComponent, boolean isDirectory);

    @OccInstanceMethod("URLByAppendingPathExtension:")
    URL appendPathExtension(String pathExtension);

    @OccInstanceMethod("URLByDeletingLastPathComponent")
    URL deleteLastPathComponent();

    @OccInstanceMethod("URLByDeletingPathExtension")
    URL deletePathExtension();

    @OccInstanceMethod("URLByResolvingSymlinksInPath")
    URL resolveSymlinksInPath();

    @OccInstanceMethod("URLByStandardizingPath")
    URL standardizePath();

    @OccInstanceMethod("bookmarkDataWithOptions:includingResourceValuesForKeys:relativeToURL:error:")
    ByteBuffer createBookmarkData(URLBookmarkCreationOptions options, List<String> keys,
                                  URL baseURL) throws RuntimeException;

    @OccInstanceMethod("getResourceValue:forKey:error:")
    boolean tryGetResource(Object value, String key) throws RuntimeException;

    @OccInstanceMethod("resourceValuesForKeys:error:")
    Map<String, Object> getResourceValues(List<String> keys) throws RuntimeException;

    @OccInstanceMethod("setResourceValue:forKey:error:")
    boolean setResource(Object value, String key) throws RuntimeException;

    @OccInstanceMethod("setResourceValues:error:")
    boolean setResourceValues(Map<String, Object> keyedValues) throws RuntimeException;

    @Override
    URLFactory getFactory();
}
