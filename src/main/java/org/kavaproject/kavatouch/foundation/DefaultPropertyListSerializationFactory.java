/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.foundation;

import org.kavaproject.kavatouch.corefoundation.CorePropertyList;
import org.kavaproject.kavatouch.foundation.staging.PropertyListMutabilityOptions;
import org.kavaproject.kavatouch.foundation.staging.PropertyListReadOptions;
import org.kavaproject.kavatouch.foundation.staging.PropertyListWriteOptions;
import org.kavaproject.kavatouch.util.NotImplementedException;
import org.kavaproject.kavatouch.util.OutArg;

import org.kavaproject.kavatouch.util.inject.Inject;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;

public class DefaultPropertyListSerializationFactory implements PropertyListSerializationFactory {
    @Inject
    protected DefaultPropertyListSerializationFactory() {
    }

    @Override
    public ByteBuffer asData(CorePropertyList propertyList, PropertyListFormat format,
                             PropertyListWriteOptions options) throws RuntimeException {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public int write(CorePropertyList propertyList, OutputStream stream, PropertyListFormat format,
                     PropertyListWriteOptions options) throws RuntimeException {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public CorePropertyList toPropertyList(ByteBuffer data, PropertyListReadOptions options,
                                           PropertyListFormat format) throws RuntimeException {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public CorePropertyList toPropertyList(InputStream stream, PropertyListReadOptions options,
                                           PropertyListFormat format) throws RuntimeException {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public boolean isValid(CorePropertyList propertyList, PropertyListFormat format) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public ByteBuffer asData(CorePropertyList propertyList, PropertyListFormat format,
                             OutArg<String> errorDescription) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public CorePropertyList toPropertyList(ByteBuffer data, PropertyListMutabilityOptions options, PropertyListFormat format, OutArg<String> errorDescription) {
        throw new NotImplementedException(); //TODO
    }
}
