/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.foundation;

import org.kavaproject.kavatouch.util.inject.Inject;

public class DefaultProcessInfoFactory implements ProcessInfoFactory {
    private final ProcessInfo mInstance = new DefaultProcessInfo(this);

    @Inject
    protected DefaultProcessInfoFactory() {
    }

    @Override
    public ProcessInfo processInfo() {
        return mInstance;
    }
}
