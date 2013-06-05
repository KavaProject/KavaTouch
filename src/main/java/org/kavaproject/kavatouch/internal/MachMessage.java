/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.internal;

import java.util.concurrent.TimeUnit;

public class MachMessage {
    public MachPort replyPort;
    public MachPort destinationPort;
    public Object data;

    public void copyTo(MachMessage message) {
        message.data = data;
        message.destinationPort = destinationPort;
        message.replyPort = replyPort;
    }

    public MachResult send() {
        return send(Long.MAX_VALUE, TimeUnit.DAYS);
    }

    public MachResult send(long timeout, TimeUnit unit) {
        return destinationPort.send(this, timeout, unit);
    }

    public MachResult reply() {
        return reply(Long.MAX_VALUE, TimeUnit.DAYS);
    }

    public MachResult reply(long timeout, TimeUnit unit) {
        return replyPort.send(this, timeout, unit);
    }
}
