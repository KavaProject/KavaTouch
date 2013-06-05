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
import org.kavaproject.kavatouch.util.NotImplementedException;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;

public class DefaultURL implements URL {
    private final URLFactory mURLFactory;
    private CoreURL mCoreURL;

    protected DefaultURL(CoreURL coreURL, URLFactory urlFactory) {
        mCoreURL = coreURL;
        mURLFactory = urlFactory;
    }

    @Override
    public boolean equals(Object other) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public boolean checkResourceIsReachable() throws RuntimeException {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public boolean isFileReferenceURL() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public boolean isFileURL() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public String absoluteString() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public URL absoluteURL() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public URL baseURL() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public String fragment() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public String host() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public String lastPathComponent() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public String parameterString() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public String password() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public String path() {
        return mCoreURL.copyPath();
    }

    @Override
    public List<String> pathComponents() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public String pathExtension() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public Integer port() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public String query() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public String relativePath() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public String relativeString() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public String resourceSpecifier() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public String scheme() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public URL standardizedURL() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public String user() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public URL filePathURL() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public URL fileReferenceURL() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public URL appendPathComponent(String pathComponent) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public URL appendPathComponent(String pathComponent, boolean isDirectory) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public URL appendPathExtension(String pathExtension) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public URL deleteLastPathComponent() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public URL deletePathExtension() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public URL resolveSymlinksInPath() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public URL standardizePath() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public ByteBuffer createBookmarkData(URLBookmarkCreationOptions options, List<String> keys,
                                         URL baseURL) throws RuntimeException {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public boolean tryGetResource(Object value, String key) throws RuntimeException {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public Map<String, Object> getResourceValues(List<String> keys) throws RuntimeException {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public boolean setResource(Object value, String key) throws RuntimeException {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public boolean setResourceValues(Map<String, Object> keyedValues) throws RuntimeException {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public URLFactory getFactory() {
        return mURLFactory;
    }

    @Override
    public URL copy() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void encode(Coder encoder) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public CoreURL toCoreType() {
        return mCoreURL;
    }
}
