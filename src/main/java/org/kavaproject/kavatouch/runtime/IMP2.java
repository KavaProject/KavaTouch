/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.runtime;

public abstract class IMP2<TRcv, T1, T2> extends IMP<Void, TRcv> {
    @Override
    Void invoke(TRcv receiver, SEL sel, Object... args) {
        invoke(receiver, sel, (T1) args[0], (T2) args[1]);
        return null;
    }

    public abstract void invoke(TRcv receiver, SEL sel, T1 arg1, T2 arg2);

    @Override
    public String[] encode() {
        return new String[5];
    }
}
