/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.foundation;

import org.kavaproject.kavatouch.foundation.staging.KeyedUnarchiverDelegate;
import org.kavaproject.kavatouch.internal.Header;
import org.kavaproject.kavatouch.internal.OccClass;
import org.kavaproject.kavatouch.internal.OccInstanceMethod;
import org.kavaproject.kavatouch.runtime.Creatable;
import org.kavaproject.kavatouch.runtime.Factory;

@Header("NSKeyedArchiver")
@OccClass("NSKeyedUnarchiver")
public interface KeyedUnarchiver extends Coder, Creatable {
    @OccInstanceMethod("classForClassName:")
    String getFactory(String name);

    @Override
    boolean containsKey(String key);

    @Override
    boolean decodeBool(String key);

    @Override
    byte[] decodeBytes(String key);

    @Override
    double decodeDouble(String key);

    @Override
    float decodeFloat(String key);

    @Override
    int decodeInt(String key);

    @Override
    long decodeLong(String key);

    @Override
    Object decodeObject(String key);

    @OccInstanceMethod("finishDecoding")
    void finishDecoding();

    @OccInstanceMethod("delegate")
    KeyedUnarchiverDelegate delegate();

    @OccInstanceMethod("setDelegate:")
    void setDelegate(KeyedUnarchiverDelegate delegate);

    @OccInstanceMethod("setClass:forClassName:")
    void setFactory(Factory factory, String name);

    @Override
    KeyedUnarchiverFactory getFactory();
}
