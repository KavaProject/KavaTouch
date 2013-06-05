/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.foundation;

import org.kavaproject.kavatouch.internal.Header;
import org.kavaproject.kavatouch.internal.OccClass;
import org.kavaproject.kavatouch.internal.OccInstanceMethod;
import org.kavaproject.kavatouch.runtime.Creatable;

@Header("NSPortNameServer")
@OccClass(value = "NSPortNameServer", osxOnly = true)
public interface PortNameServer extends Creatable {
    @OccInstanceMethod("portForName:")
    Port portForName(String portName);

    @OccInstanceMethod("portForName:host:")
    Port portForName(String portName, String hostName);

    @OccInstanceMethod("registerPort:name:")
    boolean registerPortName(Port port, String portName);

    @OccInstanceMethod("removePortForName:")
    boolean removePortForName(String portName);

    @Override
    PortNameServerFactory getFactory();
}
