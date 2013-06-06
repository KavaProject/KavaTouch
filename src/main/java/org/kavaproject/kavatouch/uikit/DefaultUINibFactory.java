/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.uikit;

import org.kavaproject.kavatouch.foundation.Bundle;
import org.kavaproject.kavatouch.internal.OccClass;
import org.kavaproject.kavatouch.util.NotImplementedException;

import org.kavaproject.kavatouch.util.inject.Inject;
import java.nio.ByteBuffer;

@OccClass("UINib")
public class DefaultUINibFactory implements UINibFactory {
    @Inject
    protected DefaultUINibFactory() {
    }

    @Override
    public UINib create(String name, Bundle bundle) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public UINib create(ByteBuffer data, Bundle bundle) {
        throw new NotImplementedException(); //TODO
    }
}
