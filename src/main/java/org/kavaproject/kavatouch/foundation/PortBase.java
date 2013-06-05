/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.foundation;

public abstract class PortBase implements Port {
    private PortDelegate mDelegate;

    protected PortBase() {
    }

    protected PortBase(Coder decoder) {
        //TODO
    }

    @Override
    public void setDelegate(PortDelegate delegate) {
        mDelegate = delegate;
    }

    @Override
    public PortDelegate delegate() {
        return mDelegate;
    }

    @Override
    public int reservedSpaceLength() {
        return 0;
    }

    @Override
    public void encode(Coder encoder) {
    }

    @Override
    public Port copy() {
        return null;
    }
}
