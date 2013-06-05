/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.corefoundation;

public abstract class CoreRunLoopSourceCustom extends CoreRunLoopSource {
    public CoreRunLoopSourceCustom(int order) {
        super(order);
    }

    public abstract void onCancel(CoreRunLoop rl, String mode);

    public abstract void onPerform();

    public abstract void onSchedule(CoreRunLoop rl, String mode);
}
