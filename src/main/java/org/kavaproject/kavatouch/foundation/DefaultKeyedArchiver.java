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
import org.kavaproject.kavatouch.runtime.Factory;
import org.kavaproject.kavatouch.util.NotImplementedException;

public class DefaultKeyedArchiver extends CoderBase implements KeyedArchiver {
    protected DefaultKeyedArchiver(KeyedArchiverFactory keyedArchiverFactory) {
        super(keyedArchiverFactory);
    }

    @Override
    public String getFactory(Factory factory) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void finishEncoding() {

    }

    @Override
    public PropertyListFormat outputFormat() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void setOutputFormat(PropertyListFormat format) {

    }

    @Override
    public KeyedArchiverDelegate delegate() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void setDelegate(KeyedArchiverDelegate delegate) {

    }

    @Override
    public void setFactory(String codedName, Factory factory) {

    }

    @Override
    public void encode(boolean value, String key) {

    }

    @Override
    public void encode(byte[] bytesp, int number, String key) {

    }

    @Override
    public void encodeConditionalObject(Object value, String key) {

    }

    @Override
    public void encode(double value, String key) {

    }

    @Override
    public void encode(float value, String key) {

    }

    @Override
    public void encode(int value, String key) {

    }

    @Override
    public void encode(long value, String key) {

    }

    @Override
    public void encode(Object value, String key) {

    }

    @Override
    public KeyedArchiverFactory getFactory() {
        return (KeyedArchiverFactory) super.getFactory();
    }
}
