/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.foundation;

import javax.inject.Inject;
import java.util.List;

public class DefaultFoundationExceptionFactory implements FoundationExceptionFactory {
    @Inject
    protected DefaultFoundationExceptionFactory() {
    }

    @Override
    public RuntimeException getAdaptee(String name, String format, Object... args) {
        return create(name, format, null).getAdaptee(); //TODO
    }

    @Override
    public FoundationException create(String name, String reason, ExceptionUserInfo userInfo) {
        return new DefaultFoundationException(name, reason, userInfo, this);
    }

    @Override
    public RuntimeException getAdaptee(String name, String format, List argsList) {
        return create(name, format, null).getAdaptee(); //TODO
    }

    @Override
    public FoundationException create(Coder decoder) {
        return new DefaultFoundationException(decoder, this);
    }
}
