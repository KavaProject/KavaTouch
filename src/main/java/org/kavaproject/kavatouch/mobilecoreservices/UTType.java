/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.mobilecoreservices;

import org.kavaproject.kavatouch.corefoundation.CoreURL;
import org.kavaproject.kavatouch.internal.CData;
import org.kavaproject.kavatouch.internal.CFunction;
import org.kavaproject.kavatouch.internal.Header;
import org.kavaproject.kavatouch.util.NotImplementedException;

import java.util.List;
import java.util.Map;

public class UTType {
    @Header("UTCoreTypes")
    @CData("kUTTypePNG")
    public static final UTType PNG = new UTType("kUTTypePNG");
    @Header("UTCoreTypes")
    @CData("kUTTypeJPEG")
    public static final UTType JPEG = new UTType("kUTTypeJPEG");
    private final String s;

    UTType(String s) {
        this.s = s;
    }

    @CFunction("UTTypeCreatePreferredIdentifierForTag")
    public UTType createPreferredIdentifier(String tagClass, String tag, String conformingToUTI) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("UTTypeCreateAllIdentifiersForTag")
    public List<UTType> createAllIdentifiers(String tagClass, String tag, String conformingToUTI) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("UTTypeCopyPreferredTagWithClass")
    public String copyPreferredTagWithClass(String tagClass) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("UTTypeEqual")
    @Override
    public boolean equals(Object other) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("UTTypeCopyDescription")
    @Override
    public String toString() {
        return s;
    }

    @CFunction("UTTypeConformsTo")
    public boolean conformsTo(UTType otherUTI) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("UTTypeCopyDeclaration")
    public Map<String, Object> copyDeclaration() {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("UTTypeCopyDeclaringBundleURL")
    public CoreURL copyDeclaringBundleURL() {
        throw new NotImplementedException(); //TODO
    }
}
