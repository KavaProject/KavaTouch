/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.foundation;

import org.kavaproject.kavatouch.runtime.Runnable3;
import org.kavaproject.kavatouch.util.OutArg;

import java.util.Map;

interface AttributedRangeCallback extends Runnable3<Map, Range, OutArg<Boolean>> {
    @Override
    void run(Map attributes, Range range, OutArg<Boolean> stop);
}
