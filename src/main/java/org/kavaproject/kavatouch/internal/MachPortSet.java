/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class MachPortSet {
    private final MachPort.Observer mObserver = new MachPort.Observer() {
        //Concurrent access
        @Override
        public void update(MachPort sourcePort, MachMessage message) {
            mPortQueue.add(sourcePort);
        }
    };
    private BlockingQueue<MachPort> mPortQueue = new LinkedBlockingQueue<MachPort>();
    private ArrayList<MachPort> mPorts = new ArrayList<MachPort>();

    public MachPortSet(MachPort port) {
        add(port);
    }

    public void add(MachPort port) {
        if (port == null) {
            return;
        }
        port.addObserver(mObserver);
        mPorts.add(port);
        mPortQueue.add(port);
    }

    public MachPortSet() {
    }

    public MachResult receive(MachMessage message) {
        return receive(message, Long.MAX_VALUE, TimeUnit.DAYS);
    }

    public MachResult receive(MachMessage message, long timeout, TimeUnit unit) {
        return waitForPort(message, timeout, unit);
    }

    private MachResult waitForPort(MachMessage message, long timeout, TimeUnit unit) {
        MachPort port;
        while (true) {
            try {
                port = mPortQueue.poll(timeout, unit);
            } catch (InterruptedException e) {
                return MachResult.INTERRUPTED;
            }
            if (port == null) {
                return MachResult.SEND_TIMEOUT;
            }
            if (mPorts.contains(port) && port.hasMessages()) {
                break;
            }
        }
        return port.receive(message, timeout, unit);
    }

    public void remove(MachPort port) {
        if (port == null) {
            return;
        }
        port.removeObserver(mObserver);
        mPorts.remove(port);
        mPortQueue.removeAll(Collections.singletonList(port));
    }

    public void clear() {
        for (MachPort port : mPorts) {
            port.removeObserver(mObserver);
            mPortQueue.removeAll(Collections.singletonList(port));
        }
        mPorts.clear();
    }
}
