/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.foundation;

import org.kavaproject.kavatouch.corefoundation.CoreMessagePort;
import org.kavaproject.kavatouch.util.NotImplementedException;

import javax.inject.Inject;
import java.nio.ByteBuffer;

public class DefaultMessagePortFactory extends PortBaseFactory implements MessagePortFactory {
    private final PortMessageFactory mPortMessageFactory;
    private int mPortCounter;

    @Inject
    protected DefaultMessagePortFactory(PortMessageFactory portMessageFactory, MachPortFactory machPortFactory) {
        mPortMessageFactory = portMessageFactory;
    }

    @Override
    public Port createPort() {
        return create();
    }

    @Override
    public Port create() {
        final DefaultMessagePort port = new DefaultMessagePort(this);
        port.setCoreMessagePort(CoreMessagePort.createLocal("NSMessagePort-" + mPortCounter++,
                new CoreMessagePort.ReceiveHandler() {
            @Override
            public ByteBuffer execute(CoreMessagePort local, int msgid, ByteBuffer data) {
                PortMessage message = mPortMessageFactory.create(port, create(local), null);
                message.setMsgid(msgid);
                port.delegate().handlePortMessage(message);
                return null;
            }
        }));
        return port;
    }

    @Override
    public MessagePort create(CoreMessagePort coreMessagePort) {
        return new DefaultMessagePort(coreMessagePort, this); //TODO
    }

    @Override
    public MessagePort create(Coder decoder) {
        throw new NotImplementedException(); //TODO
    }
}
