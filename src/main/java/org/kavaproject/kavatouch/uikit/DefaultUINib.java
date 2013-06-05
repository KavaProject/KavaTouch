/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.uikit;

import org.kavaproject.kavatouch.foundation.UINibLoadingOptions;
import org.kavaproject.kavatouch.util.NotImplementedException;

import java.util.List;

public class DefaultUINib implements UINib {
    private final UINibFactory mUINibFactory;

    public DefaultUINib(UINibFactory uiNibFactory) {
        mUINibFactory = uiNibFactory;
    }

    @Override
    public List instantiateWithOwnerOptions(Object owner, UINibLoadingOptions options) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public UINibFactory getFactory() {
        return mUINibFactory;
    }
}
