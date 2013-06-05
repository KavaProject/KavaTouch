/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.corefoundation;

import org.kavaproject.kavatouch.internal.CTypedef;
import org.kavaproject.kavatouch.internal.Header;

import java.util.HashMap;
import java.util.Map;

@Header("CFString")
@CTypedef("CFStringEncoding")
public class CoreStringEncoding {
    private static Map<String, CoreStringEncoding> sInstances = new HashMap<String, CoreStringEncoding>();
    private final String mName;

    private CoreStringEncoding(String name) {
        mName = name;
    }

    public static CoreStringEncoding valueOf(String name) {
        CoreStringEncoding instance = sInstances.get(name);
        if (instance == null) {
            instance = new CoreStringEncoding(name);
            sInstances.put(name, instance);
        }
        return instance;
    }

    @Override
    public String toString() {
        return mName;
    }
}
