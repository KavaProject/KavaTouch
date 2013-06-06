/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.foundation;

import org.kavaproject.kavatouch.corefoundation.CoreError;

import org.kavaproject.kavatouch.util.inject.Inject;

public class DefaultFoundationErrorFactory implements FoundationErrorFactory {
    @Inject
    protected DefaultFoundationErrorFactory() {
    }

    @Override
    public FoundationError create(Coder decoder) {
        return new DefaultFoundationError(decoder, this);
    }

    @Override
    public FoundationError create(String domain, int code, ErrorUserInfo userInfo) {
        return new DefaultFoundationError(CoreError.getInstance(domain, code, userInfo), this);
    }

    @Override
    public FoundationError create(Exception adaptee) {
        return create(CoreError.valueOf(adaptee));
    }

    @Override
    public FoundationError create(CoreError coreObject) {
        return new DefaultFoundationError(coreObject, this);
    }
}
