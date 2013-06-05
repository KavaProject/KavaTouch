/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.corefoundation;

import org.kavaproject.kavatouch.internal.CData;
import org.kavaproject.kavatouch.internal.Header;

import java.util.List;

public class CoreURLErrorUserInfo extends CoreErrorUserInfo {
    @Header("CFURL")
    @CData("kCFURLKeysOfUnsetValuesKey")
    public List<CoreURLResourcePropertyKey> keysOfUnsetValues;

    public CoreURLErrorUserInfo(CoreURLErrorUserInfo template) {
        super(template);
        keysOfUnsetValues = template.keysOfUnsetValues;
    }

    public CoreURLErrorUserInfo() {
    }
}
