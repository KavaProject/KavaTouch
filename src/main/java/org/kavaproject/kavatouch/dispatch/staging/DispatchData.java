/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.dispatch.staging;

import org.kavaproject.kavatouch.dispatch.DispatchObject;
import org.kavaproject.kavatouch.internal.CTypedef;

@CTypedef("dispatch_data_t")
public class DispatchData extends DispatchObject {
    public static final DispatchData dispatch_data_empty = new DispatchData();

    public static final Runnable DISPATCH_DATA_DESTRUCTOR_DEFAULT = new Runnable() {
        @Override
        public void run() {
        }
    };

    public static final Runnable DISPATCH_DATA_DESTRUCTOR_FREE = new Runnable() {
        @Override
        public void run() {
        }
    };

    public static interface DispatchApplyBlock {
        void execute(int arg);
    }

    private static interface DispatchApplyFunction<T> {
        /**
         * void (*work)(void *, int)
         */
        void execute(T context, int arg);
    }
}
