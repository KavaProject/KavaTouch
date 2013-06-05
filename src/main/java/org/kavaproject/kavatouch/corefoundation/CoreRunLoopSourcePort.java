/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.corefoundation;

import org.kavaproject.kavatouch.internal.MachMessage;
import org.kavaproject.kavatouch.internal.MachPort;

public abstract class CoreRunLoopSourcePort extends CoreRunLoopSourceCustom {
    public CoreRunLoopSourcePort() {
        super(0);
    }

    public abstract MachMessage onMachPerform(MachMessage message, int size);

    public abstract MachPort onGetPort();

    @Override
    public void onCancel(CoreRunLoop rl, String mode) {
    }

    @Override
    public void onPerform() {
    }

    @Override
    public void onSchedule(CoreRunLoop rl, String mode) {
    }
}
