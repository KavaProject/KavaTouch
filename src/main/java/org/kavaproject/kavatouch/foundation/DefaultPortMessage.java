/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.foundation;

import java.util.List;

public class DefaultPortMessage implements PortMessage {
    private final PortMessageFactory mPortMessageFactory;
    private Port mSendPort;
    private Port mReceivePort;
    private List mComponents;
    private int mMessageID;

    protected DefaultPortMessage(Port sendPort, Port receivePort, List components, int messageID,
                                 PortMessageFactory portMessageFactory) {
        mSendPort = sendPort;
        mReceivePort = receivePort;
        mComponents = components;
        mMessageID = messageID;
        mPortMessageFactory = portMessageFactory;
    }

    @Override
    public boolean sendBefore(FoundationDate date) {
        return mSendPort.send(date, mMessageID, mComponents, mReceivePort, 0);
    }

    @Override
    public List components() {
        return mComponents;
    }

    @Override
    public Port receivePort() {
        return mReceivePort;
    }

    @Override
    public Port sendPort() {
        return mSendPort;
    }

    @Override
    public void setMsgid(int msgid) {
        mMessageID = msgid;
    }

    @Override
    public int msgid() {
        return mMessageID;
    }

    @Override
    public PortMessageFactory getFactory() {
        return mPortMessageFactory;
    }
}
