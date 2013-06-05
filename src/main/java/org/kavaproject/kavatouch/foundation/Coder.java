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
import org.kavaproject.kavatouch.internal.Header;
import org.kavaproject.kavatouch.internal.OccClass;
import org.kavaproject.kavatouch.internal.OccInstanceMethod;
import org.kavaproject.kavatouch.runtime.Creatable;
import org.kavaproject.kavatouch.runtime.Factory;
import org.kavaproject.kavatouch.uikit.UIEdgeInsets;
import org.kavaproject.kavatouch.uikit.UIOffset;
import org.kavaproject.kavatouch.util.OutArg;

import java.nio.ByteBuffer;
import java.util.Set;

@OccClass("NSCoder")
public interface Coder extends Creatable {
    @OccInstanceMethod("allowsKeyedCoding")
    boolean allowsKeyedCoding();

    @OccInstanceMethod("containsValueForKey:")
    boolean containsKey(String key);

    @OccInstanceMethod("encodeArrayOfObjCType:count:at:")
    void encode(String itemType, Object[] address);

    @OccInstanceMethod("encodeBoolForKey:")
    void encode(boolean value, String key);

    @OccInstanceMethod("encodeBycopyObject:")
    void encodeByCopy(Object object);

    @OccInstanceMethod("encodeObject:")
    void encode(Object object);

    @OccInstanceMethod("encodeByrefObject:")
    void encodeByRef(Object object);

    @OccInstanceMethod("encodeBytes:length:")
    void encode(byte[] bytes);

    @OccInstanceMethod("encodeBytes:length:forKey:")
    void encode(byte[] bytes, int number, String key);

    @OccInstanceMethod("encodeConditionalObject:")
    void encodeConditionalObject(Object object);

    @OccInstanceMethod("encodeConditionalObject:forKey:")
    void encodeConditionalObject(Object value, String key);

    @OccInstanceMethod("encodeDataObject:")
    void encode(ByteBuffer data);

    @OccInstanceMethod("encodeDouble:forKey:")
    void encode(double value, String key);

    @OccInstanceMethod("encodeFloat:forKey:")
    void encode(float value, String key);

    @OccInstanceMethod("encodeInt:forKey:")
    void encode(int value, String key);

    @OccInstanceMethod("encodeInt64:forKey:")
    void encode(long value, String key);

    @OccInstanceMethod("encodeObject:forKey:")
    void encode(Object value, String key);

    @OccInstanceMethod("encodeRootObject:")
    void encodeRoot(Object rootObject);

    @OccInstanceMethod("decodeArrayOfObjCType:count:at:")
    void decode(String itemType, OutArg address);

    @OccInstanceMethod("decodeBoolForKey:")
    boolean decodeBool(String key);

    @OccInstanceMethod("decodeBytesForKey:returnedLength:")
    byte[] decodeBytes(String key);

    @OccInstanceMethod("decodeBytesWithReturnedLength:")
    byte[] decodeBytes();

    @OccInstanceMethod("decodeDataObject")
    ByteBuffer decodeDataObject();

    @OccInstanceMethod("decodeDoubleForKey:")
    double decodeDouble(String key);

    @OccInstanceMethod("decodeFloatForKey:")
    float decodeFloat(String key);

    @OccInstanceMethod("decodeIntForKey:")
    int decodeInt(String key);

    @OccInstanceMethod("decodeInt64ForKey:")
    long decodeLong(String key);

    @OccInstanceMethod("decodeObject")
    Object decodeObject();

    @OccInstanceMethod("decodeValueOfObjCType:at:")
    void decodeValueOfObjCTypeAt(String valueType, OutArg<Object> data);

    @OccInstanceMethod("decodeValuesOfObjCTypes:")
    void decodeValuesOfObjCTypes(String[] valueTypes, OutArg<Object[]> result);

    @OccInstanceMethod("decodeObjectOfClass:forKey:")
    Object decodeObjectOfFactory(Factory factory, String key);

    @OccInstanceMethod("requiresSecureCoding")
    boolean requiresSecureCoding();

    @OccInstanceMethod("decodeObjectForKey:")
    Object decodeObject(String key);

    @OccInstanceMethod("decodePropertyListForKey:")
    Object decodePropertyList(String key);

    @OccInstanceMethod("decodeObjectOfClassesForKey")
    Object decodeObjectOfFactories(Set<Factory> factories, String key);

    @OccInstanceMethod("allowedClasses")
    Set<SecureCodingFactory> allowedClasses();

    @OccInstanceMethod("systemVersion")
    int systemVersion();

    @OccInstanceMethod("versionForClassName:")
    int versionForClassName(String className);

    @Header("UIGeometry")
    @OccInstanceMethod("encodeCGPoint:forKey:")
    void encode(GraphicsPoint point, String key);

    @Header("UIGeometry")
    @OccInstanceMethod("encodeCGRect:forKey:")
    void encode(GraphicsRect rect, String key);

    @Header("UIGeometry")
    @OccInstanceMethod("encodeCGSize:forKey:")
    void encode(GraphicsSize size, String key);

    @Header("UIGeometry")
    @OccInstanceMethod("encodeCGAffineTransform:forKey:")
    void encode(GraphicsAffineTransform transform, String key);

    @Header("UIGeometry")
    @OccInstanceMethod("encodeUIEdgeInsets:forKey:")
    void encode(UIEdgeInsets insets, String key);

    @Header("UIGeometry")
    @OccInstanceMethod("encodeUIOffset:forKey:")
    void encode(UIOffset offset, String key);

    @Header("UIGeometry")
    @OccInstanceMethod("decodeCGPointForKey:")
    GraphicsPoint decodeGraphicsPoint(String key);

    @Header("UIGeometry")
    @OccInstanceMethod("decodeCGRectForKey:")
    GraphicsRect decodeGraphicsRect(String key);

    @Header("UIGeometry")
    @OccInstanceMethod("decodeCGSizeForKey:")
    GraphicsSize decodeGraphicsSize(String key);

    @Header("UIGeometry")
    @OccInstanceMethod("decodeCGAffineTransformForKey:")
    GraphicsAffineTransform decodeCGAffineTransform(String key);

    @Header("UIGeometry")
    @OccInstanceMethod("decodeUIEdgeInsetsForKey:")
    UIEdgeInsets decodeUIEdgeInsets(String key);

    @Header("UIGeometry")
    @OccInstanceMethod("decodeUIOffsetForKey:")
    UIOffset decodeUIOffset(String key);

    @Override
    CoderFactory getFactory();
}
