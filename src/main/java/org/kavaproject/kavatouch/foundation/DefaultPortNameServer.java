/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.foundation;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class DefaultPortNameServer implements PortNameServer {
    private final PortNameServerFactory mPortNameServerFactory;
    private ConcurrentMap<String, Port> mPorts = new ConcurrentHashMap<String, Port>();

    protected DefaultPortNameServer(PortNameServerFactory portNameServerFactory) {
        mPortNameServerFactory = portNameServerFactory;
    }

    @Override
    public Port portForName(String portName) {
        return portForName(portName, null);
    }

    @Override
    public Port portForName(String portName, String hostName) {
        if (hostName != null && hostName != "") {
            return null; //TODO
        }
        return mPorts.get(portName);
    }

    @Override
    public boolean registerPortName(Port port, String portName) {
        return mPorts.putIfAbsent(portName, port) == null;
    }

    @Override
    public boolean removePortForName(String portName) {
        return mPorts.remove(portName) != null;
    }

    @Override
    public PortNameServerFactory getFactory() {
        return mPortNameServerFactory;
    }
}
