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
import org.kavaproject.kavatouch.corefoundation.CoreURLPathStyle;
import org.kavaproject.kavatouch.foundation.staging.URLBookmarkFileCreationOptions;
import org.kavaproject.kavatouch.foundation.staging.URLBookmarkResolutionOptions;
import org.kavaproject.kavatouch.util.NotImplementedException;

import org.kavaproject.kavatouch.util.inject.Inject;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;

public class DefaultURLFactory implements URLFactory {
    @Inject
    protected DefaultURLFactory() {
    }

    @Override
    public boolean supportsSecureCoding() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public URL create(CoreURL bridgedObject) {
        return new DefaultURL(bridgedObject, this);
    }

    @Override
    public URL create(String scheme, String host, String path) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public URL createURL(String urlString) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public URL create(String urlString, URL baseURL) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public URL createWithPath(String path, boolean isDir) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public URL createWithPath(String path) {
        return new DefaultURL(new CoreURL(path, CoreURLPathStyle.HFS, false), this);
    }

    @Override
    public URL createFileURL(List<String> components) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public URL create(ByteBuffer bookmarkData, URLBookmarkResolutionOptions options, URL baseURL,
                      boolean isStale) throws RuntimeException {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public ByteBuffer createBookmarkData(URL bookmarkFileURL) throws RuntimeException {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public boolean writeBookmarkData(ByteBuffer bookmarkData, URL bookmarkFileURL,
                                     URLBookmarkFileCreationOptions options) throws RuntimeException {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public Map<String, Object> getResourceValues(List<String> keys, ByteBuffer bookmarkData) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public URL create(Coder decoder) {
        throw new NotImplementedException(); //TODO
    }
}
