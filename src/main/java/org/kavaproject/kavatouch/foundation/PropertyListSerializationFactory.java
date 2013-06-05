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
import org.kavaproject.kavatouch.internal.Header;
import org.kavaproject.kavatouch.internal.OccClass;
import org.kavaproject.kavatouch.internal.OccClassMethod;
import org.kavaproject.kavatouch.runtime.Factory;
import org.kavaproject.kavatouch.util.OutArg;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;

@Header("NSPropertyList")
@OccClass("NSPropertyListSerialization")
public interface PropertyListSerializationFactory extends Factory {
    @OccClassMethod("dataWithPropertyList:format:options:error:")
    ByteBuffer asData(CorePropertyList propertyList, PropertyListFormat format, PropertyListWriteOptions options)
            throws RuntimeException;

    @OccClassMethod("writePropertyList:toStream:format:options:error:")
    int write(CorePropertyList propertyList, OutputStream stream, PropertyListFormat format,
              PropertyListWriteOptions options) throws RuntimeException;

    @OccClassMethod("propertyListWithData:options:format:error:")
    CorePropertyList toPropertyList(ByteBuffer data, PropertyListReadOptions options,
                                    PropertyListFormat format) throws RuntimeException;

    @OccClassMethod("propertyListWithStream:options:format:error:")
    CorePropertyList toPropertyList(InputStream stream, PropertyListReadOptions options,
                                    PropertyListFormat format) throws RuntimeException;

    @OccClassMethod("propertyList:isValidForFormat:")
    boolean isValid(CorePropertyList propertyList, PropertyListFormat format);

    @OccClassMethod("dataFromPropertyList:format:errorDescription:")
    @Deprecated
    ByteBuffer asData(CorePropertyList propertyList, PropertyListFormat format, OutArg<String> errorDescription);

    @OccClassMethod("propertyListFromData:mutabilityOption:format:errorDescription:")
    @Deprecated
    CorePropertyList toPropertyList(ByteBuffer data, PropertyListMutabilityOptions options, PropertyListFormat format, OutArg<String> errorDescription);
}
