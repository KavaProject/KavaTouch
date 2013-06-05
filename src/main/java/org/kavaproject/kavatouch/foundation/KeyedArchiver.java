/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.foundation;

import org.kavaproject.kavatouch.foundation.staging.KeyedArchiverDelegate;
import org.kavaproject.kavatouch.internal.Header;
import org.kavaproject.kavatouch.internal.OccClass;
import org.kavaproject.kavatouch.internal.OccInstanceMethod;
import org.kavaproject.kavatouch.runtime.Creatable;
import org.kavaproject.kavatouch.runtime.Factory;

@Header("NSKeyedArchiver")
@OccClass("NSKeyedArchiver")
public interface KeyedArchiver extends Coder, Creatable {
    @OccInstanceMethod("classNameForClass:")
    String getFactory(Factory factory);

    @OccInstanceMethod("finishEncoding")
    void finishEncoding();

    @OccInstanceMethod("outputFormat")
    PropertyListFormat outputFormat();

    @OccInstanceMethod("setOutputFormat:")
    void setOutputFormat(PropertyListFormat format);

    @OccInstanceMethod("delegate")
    KeyedArchiverDelegate delegate();

    @OccInstanceMethod("setDelegate:")
    void setDelegate(KeyedArchiverDelegate delegate);

    @OccInstanceMethod("setClassName:forClass:")
    void setFactory(String codedName, Factory factory);

    @OccInstanceMethod("encodeBool:forKey:")
    @Override
    public void encode(boolean value, String key);

    @OccInstanceMethod("encodeBytes:length:forKey:")
    @Override
    public void encode(byte[] bytesp, int number, String key);

    @OccInstanceMethod("encodeConditionalObject:forKey:")
    @Override
    public void encodeConditionalObject(Object value, String key);

    @OccInstanceMethod("encodeDouble:forKey:")
    @Override
    public void encode(double value, String key);

    @OccInstanceMethod("encodeFloat:forKey:")
    @Override
    public void encode(float value, String key);

    @OccInstanceMethod("encodeInt:forKey:")
    @Override
    public void encode(int value, String key);

    @OccInstanceMethod("encodeInt64:forKey:")
    @Override
    public void encode(long value, String key);

    @OccInstanceMethod("encodeObject:forKey:")
    @Override
    public void encode(Object value, String key);

    @Override
    KeyedArchiverFactory getFactory();
}
