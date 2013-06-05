/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.corefoundation;

import org.kavaproject.kavatouch.internal.Header;
import org.kavaproject.kavatouch.internal.OpaqueType;
import org.kavaproject.kavatouch.util.NotImplementedException;
import org.kavaproject.kavatouch.util.OutArg;

import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.Map;

@Header("CFDictionary")
@OpaqueType("CFDictionaryRef")
public class CoreDictionary<K, V> implements CorePropertyList, CoreType {
    private final Map<K, V> mAdaptee;

    private CoreDictionary(Map adaptee) {
        this.mAdaptee = adaptee;
    }

    public static <K, V> CoreDictionary<K, V> valueOf(Map<K, V> map) {
        return new CoreDictionary(map);
    }

    public Map<K, V> toMap() {
        return mAdaptee;
    }

    @Override
    public CorePropertyList deepCopy(CorePropertyListMutabilityOptions mutabilityOption) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public ByteBuffer createData(CorePropertyListFormat format, int options) throws RuntimeException {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public long write(OutputStream stream, CorePropertyListFormat format, int options) throws RuntimeException {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public ByteBuffer createXMLData(CorePropertyList propertyList) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public long writeToStream(OutputStream stream, CorePropertyListFormat format, OutArg<String> errorString) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public boolean isValid(CorePropertyListFormat format) {
        throw new NotImplementedException(); //TODO
    }
}
