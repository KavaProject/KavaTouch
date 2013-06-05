/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.foundation;

import org.kavaproject.kavatouch.corefoundation.CoreMachPort;

import javax.inject.Inject;
import java.util.EnumSet;

public class DefaultMachPortFactory extends PortBaseFactory implements MachPortFactory {
    private final PortMessageFactory mPortMessageFactory;

    @Inject
    protected DefaultMachPortFactory(PortMessageFactory portMessageFactory) {
        mPortMessageFactory = portMessageFactory;
    }

    @Override
    public Port createPort() {
        return create(new org.kavaproject.kavatouch.internal.MachPort("org.kavaproject.kavatouch.foundation.NSPort"));
    }

    @Override
    public MachPort create(org.kavaproject.kavatouch.internal.MachPort machPort) {
        return new DefaultMachPort(machPort, this, mPortMessageFactory);
    }

    @Override
    public MachPort create(org.kavaproject.kavatouch.internal.MachPort machPort, EnumSet<MachPortRights> options) {
        return new DefaultMachPort(machPort, options, this, mPortMessageFactory);
    }

    @Override
    public MachPort create(Coder decoder) {
        return new DefaultMachPort(decoder, this, mPortMessageFactory);
    }

    @Override
    public MachPort create(CoreMachPort coreObject) {
        return new DefaultMachPort(coreObject, this, mPortMessageFactory);
    }
}
