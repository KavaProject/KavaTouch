/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.corefoundation;

import org.kavaproject.kavatouch.internal.CFunction;
import org.kavaproject.kavatouch.internal.Header;
import org.kavaproject.kavatouch.internal.OpaqueType;
import org.kavaproject.kavatouch.util.OutArg;

import java.io.OutputStream;
import java.nio.ByteBuffer;

@Header("CFPropertyList")
@OpaqueType("CFPropertyListRef")
public interface CorePropertyList {
    @CFunction("CFPropertyListCreateDeepCopy")
    CorePropertyList deepCopy(CorePropertyListMutabilityOptions mutabilityOption);

    @CFunction("CFPropertyListCreateData")
    ByteBuffer createData(CorePropertyListFormat format, int options) throws RuntimeException;

    @CFunction("CFPropertyListWrite")
    public long write(OutputStream stream, CorePropertyListFormat format, int options);

    @CFunction("CFPropertyListCreateXMLData")
    ByteBuffer createXMLData(CorePropertyList propertyList);

    @CFunction("CFPropertyListWriteToStream")
    long writeToStream(OutputStream stream, CorePropertyListFormat format, OutArg<String> errorString);

    @CFunction("CFPropertyListIsValid")
    boolean isValid(CorePropertyListFormat format);
}
