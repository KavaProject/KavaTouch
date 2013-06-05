/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.foundation;

import org.kavaproject.kavatouch.coregraphics.GraphicsAffineTransform;
import org.kavaproject.kavatouch.coregraphics.GraphicsPoint;
import org.kavaproject.kavatouch.coregraphics.GraphicsRect;
import org.kavaproject.kavatouch.coregraphics.GraphicsSize;
import org.kavaproject.kavatouch.runtime.Factory;
import org.kavaproject.kavatouch.uikit.UIEdgeInsets;
import org.kavaproject.kavatouch.uikit.UIOffset;
import org.kavaproject.kavatouch.util.NotImplementedException;
import org.kavaproject.kavatouch.util.OutArg;

import java.nio.ByteBuffer;
import java.util.HashSet;
import java.util.Set;

public abstract class CoderBase implements Coder {
    private static final int SYSTEM_VERSION = 0;
    private final CoderFactory mCoderFactory;

    protected CoderBase(CoderFactory coderFactory) {
        this.mCoderFactory = coderFactory;
    }

    @Override
    public boolean allowsKeyedCoding() {
        return false;
    }

    @Override
    public boolean containsKey(String key) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void encode(String itemType, Object[] address) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void encode(boolean value, String key) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void encodeByCopy(Object object) {
        encode(object);
    }

    @Override
    public void encode(Object object) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void encodeByRef(Object object) {
        encode(object);
    }

    @Override
    public void encode(byte[] bytes) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void encode(byte[] bytes, int number, String key) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void encodeConditionalObject(Object object) {
        encode(object);
    }

    @Override
    public void encodeConditionalObject(Object value, String key) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void encode(ByteBuffer data) {
    }

    @Override
    public void encode(double value, String key) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void encode(float value, String key) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void encode(int value, String key) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void encode(long value, String key) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void encode(Object value, String key) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void encodeRoot(Object rootObject) {
        encode(rootObject);
    }

    @Override
    public void decode(String itemType, OutArg address) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public boolean decodeBool(String key) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public byte[] decodeBytes(String key) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public byte[] decodeBytes() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public ByteBuffer decodeDataObject() {
        return null;
    }

    @Override
    public double decodeDouble(String key) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public float decodeFloat(String key) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public int decodeInt(String key) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public long decodeLong(String key) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public Object decodeObject() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void decodeValueOfObjCTypeAt(String valueType, OutArg<Object> data) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void decodeValuesOfObjCTypes(String[] valueTypes, OutArg<Object[]> result) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public Object decodeObjectOfFactory(Factory factory, String key) {
        if (requiresSecureCoding()) {
            throw new UnsupportedOperationException();
        }
        return decodeObject(key);
    }

    @Override
    public boolean requiresSecureCoding() {
        return false;
    }

    @Override
    public Object decodeObject(String key) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public Object decodePropertyList(String key) {
        Set<Factory> plistTypes = new HashSet<Factory>(); //TODO
        return decodeObjectOfFactories(plistTypes, key);
    }

    @Override
    public Object decodeObjectOfFactories(Set<Factory> factories, String key) {
        if (requiresSecureCoding()) {
            throw new UnsupportedOperationException();
        }
        return decodeObject(key);
    }

    @Override
    public Set<SecureCodingFactory> allowedClasses() {
        return new HashSet<SecureCodingFactory>();
    }

    @Override
    public int systemVersion() {
        return SYSTEM_VERSION;
    }

    @Override
    public int versionForClassName(String className) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void encode(GraphicsPoint point, String key) {

    }

    @Override
    public void encode(GraphicsRect rect, String key) {

    }

    @Override
    public void encode(GraphicsSize size, String key) {

    }

    @Override
    public void encode(GraphicsAffineTransform transform, String key) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void encode(UIEdgeInsets insets, String key) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void encode(UIOffset offset, String key) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public GraphicsPoint decodeGraphicsPoint(String key) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public GraphicsRect decodeGraphicsRect(String key) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public GraphicsSize decodeGraphicsSize(String key) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public GraphicsAffineTransform decodeCGAffineTransform(String key) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public UIEdgeInsets decodeUIEdgeInsets(String key) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public UIOffset decodeUIOffset(String key) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public CoderFactory getFactory() {
        return mCoderFactory;
    }
}
