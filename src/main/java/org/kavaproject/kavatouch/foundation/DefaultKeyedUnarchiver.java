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
import org.kavaproject.kavatouch.runtime.Factory;
import org.kavaproject.kavatouch.util.NotImplementedException;

public class DefaultKeyedUnarchiver extends CoderBase implements KeyedUnarchiver {
    protected DefaultKeyedUnarchiver(KeyedUnarchiverFactory keyedUnarchiverFactory) {
        super(keyedUnarchiverFactory);
    }

    @Override
    public String getFactory(String name) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void finishDecoding() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public KeyedUnarchiverDelegate delegate() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void setDelegate(KeyedUnarchiverDelegate delegate) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void setFactory(Factory factory, String name) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public boolean containsKey(String key) {
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
    public Object decodeObject(String key) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public KeyedUnarchiverFactory getFactory() {
        return (KeyedUnarchiverFactory) super.getFactory();
    }
}
