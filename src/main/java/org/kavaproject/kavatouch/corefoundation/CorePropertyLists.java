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
import org.kavaproject.kavatouch.util.NotImplementedException;
import org.kavaproject.kavatouch.util.OutArg;

import java.io.InputStream;
import java.nio.ByteBuffer;

@Header("CFPropertyList")
public class CorePropertyLists {
    @CFunction("CFPropertyListCreateWithData")
    public static final CorePropertyList create(ByteBuffer data, CorePropertyListMutabilityOptions options,
                                                OutArg<CorePropertyListFormat> format) throws RuntimeException {
        throw new NotImplementedException();
    }

    @CFunction("CFPropertyListCreateWithStream")
    public static final CorePropertyList create(InputStream stream, long streamLength,
                                                CorePropertyListMutabilityOptions options,
                                                OutArg<CorePropertyListFormat> format) throws RuntimeException {
        throw new NotImplementedException();
    }

    @Deprecated
    @CFunction("CFPropertyListCreateFromXMLData")
    public static final CorePropertyList createFromXMLData(ByteBuffer xmlData, CorePropertyListMutabilityOptions
            mutabilityOption, OutArg<String> errorString) {
        throw new NotImplementedException();
    }

    @Deprecated
    @CFunction("CFPropertyListCreateFromStream")
    public static final CorePropertyList create(InputStream stream, long streamLength, CorePropertyListMutabilityOptions mutabilityOption, CorePropertyListFormat format, OutArg<String> errorString) {
        throw new NotImplementedException();
    }
}
