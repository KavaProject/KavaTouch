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
import org.kavaproject.kavatouch.corefoundation.CoreMessagePortSendRequestResult;
import org.kavaproject.kavatouch.corefoundation.CoreRunLoopSource;
import org.kavaproject.kavatouch.util.NotImplementedException;

import java.util.List;

public class DefaultMessagePort extends PortBase implements MessagePort {
    private final MessagePortFactory mMessagePortFactory;
    private CoreMessagePort mCoreMessagePort;

    protected DefaultMessagePort(CoreMessagePort coreMessagePort, MessagePortFactory messagePortFactory) {
        this(messagePortFactory);
        mCoreMessagePort = coreMessagePort;
    }

    DefaultMessagePort(MessagePortFactory messagePortFactory) {
        this.mMessagePortFactory = messagePortFactory;
    }

    void setCoreMessagePort(CoreMessagePort coreMessagePort) {
        mCoreMessagePort = coreMessagePort;
    }

    @Override
    public void invalidate() {
        mCoreMessagePort.invalidate();
    }

    @Override
    public boolean isValid() {
        return mCoreMessagePort.isValid();
    }

    @Override
    public boolean send(FoundationDate limitDate, List components, Port receivePort, int reservedHeaderSpace) {
        return send(limitDate, 0, components, receivePort, reservedHeaderSpace);
    }

    @Override
    public boolean send(FoundationDate limitDate, int msgID, List components, Port receivePort,
                        int reservedHeaderSpace) {
        String name = mCoreMessagePort.getName();
        CoreMessagePort remote = CoreMessagePort.createRemote(name);
        return remote.sendRequest(msgID, null, limitDate.timeIntervalSinceNow(), 0, null,
                null) == CoreMessagePortSendRequestResult.SUCCESS;
    }

    @Override
    public void removeFromRunLoop(RunLoop runLoop, String mode) {
        if (!isValid()) {
            return;
        }
        runLoop.toCoreType().removeSource(mCoreMessagePort.source, mode);
    }

    @Override
    public void scheduleInRunLoop(RunLoop runLoop, String mode) {
        if (!isValid()) {
            return;
        }
        CoreRunLoopSource source = mCoreMessagePort.createRunLoopSource(0);
        runLoop.toCoreType().addSource(source, mode);
    }

    @Override
    public MessagePortFactory getFactory() {
        return mMessagePortFactory;
    }

    @Override
    public MessagePort copy() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public CoreMessagePort toCoreType() {
        return mCoreMessagePort;
    }
}
