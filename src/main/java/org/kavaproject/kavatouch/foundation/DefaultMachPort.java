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
import org.kavaproject.kavatouch.corefoundation.CoreRunLoop;
import org.kavaproject.kavatouch.corefoundation.CoreRunLoopSource;
import org.kavaproject.kavatouch.internal.MachMessage;
import org.kavaproject.kavatouch.internal.MachResult;
import org.kavaproject.kavatouch.runtime.Factory;
import org.kavaproject.kavatouch.util.AndroidConversions;
import org.kavaproject.kavatouch.util.NotImplementedException;

import java.nio.ByteBuffer;
import java.util.EnumSet;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class DefaultMachPort extends PortBase implements MachPort {
    private final MachPortFactory mMachPortFactory;
    private final PortMessageFactory mPortMessageFactory;
    private EnumSet<MachPortRights> mOptions;
    private CoreMachPort mCoreMachPort;

    protected DefaultMachPort(org.kavaproject.kavatouch.internal.MachPort machPort, EnumSet<MachPortRights> options,
                              MachPortFactory machPortFactory, PortMessageFactory portMessageFactory) {
        this(machPort, machPortFactory, portMessageFactory);
        mOptions = options;
    }

    protected DefaultMachPort(org.kavaproject.kavatouch.internal.MachPort machPort, MachPortFactory machPortFactory,
                              PortMessageFactory portMessageFactory) {
        mMachPortFactory = machPortFactory;
        mPortMessageFactory = portMessageFactory;
        mCoreMachPort = CoreMachPort.create(machPort, new CoreMachPort.ReceiveHandler() {
            @Override
            public void execute(CoreMachPort port, MachMessage message) {
                delegate().handleMachMessage(message);
            }
        });
    }

    protected DefaultMachPort(CoreMachPort coreMachPort, MachPortFactory machPortFactory,
                              PortMessageFactory portMessageFactory) {
        mMachPortFactory = machPortFactory;
        mPortMessageFactory = portMessageFactory;
        mCoreMachPort = coreMachPort;
    }

    protected DefaultMachPort(Coder decoder, MachPortFactory machPortFactory, PortMessageFactory portMessageFactory) {
        super(decoder);
        mMachPortFactory = machPortFactory;
        mPortMessageFactory = portMessageFactory;
        //TODO
    }

    @Override
    public void setDelegate(MachPortDelegate anObject) {
        super.setDelegate(anObject);
    }

    @Override
    public org.kavaproject.kavatouch.internal.MachPort machPort() {
        return mCoreMachPort.getPort();
    }

    @Override
    public void invalidate() {
        mCoreMachPort.invalidate();
    }

    @Override
    public boolean isValid() {
        return mCoreMachPort.isValid();
    }

    @Override
    public boolean send(FoundationDate limitDate, List components, Port receivePort, int reservedHeaderSpace) {
        return send(limitDate, 0, components, receivePort, reservedHeaderSpace);
    }

    @Override
    public boolean send(FoundationDate limitDate, int msgID, List components, Port receivePort,
                        int reservedHeaderSpace) {
        assert receivePort instanceof DefaultMachPort;
        MachMessage message = new MachMessage();
        message.destinationPort = machPort();
        org.kavaproject.kavatouch.internal.MachPort replyPort = ((MachPort) receivePort).machPort();
        message.replyPort = replyPort;
        message.data = new MachMessageData(-1, msgID, null);
        MachResult result = message.send(AndroidConversions.toMillisTimeInterval(limitDate.timeIntervalSinceNow()),
                TimeUnit.MILLISECONDS);
        if (result != MachResult.SUCCESS) {
            return false;
        }
        result = replyPort.receive(null, AndroidConversions.toMillisTimeInterval(limitDate.timeIntervalSinceNow()),
                TimeUnit.MILLISECONDS);
        return result == MachResult.SUCCESS;
    }

    @Override
    public void removeFromRunLoop(RunLoop runLoop, String mode) {
        CoreRunLoop rl = runLoop.toCoreType();
        rl.removeSource(mCoreMachPort.source, mode);
    }

    @Override
    public void scheduleInRunLoop(RunLoop runLoop, String mode) {
        CoreRunLoopSource source = mCoreMachPort.createRunLoopSource(0);
        CoreRunLoop rl = runLoop.toCoreType();
        rl.addSource(source, mode);
    }

    @Override
    public MachPortFactory getFactory() {
        return mMachPortFactory;
    }

    @Override
    public void setDelegate(PortDelegate delegate) {
        super.setDelegate(new NSPortDelegateWrapper(delegate, mMachPortFactory, mPortMessageFactory));
    }

    @Override
    public MachPortDelegate delegate() {
        return (MachPortDelegate) super.delegate();
    }

    @Override
    public MachPort copy() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public CoreMachPort toCoreType() {
        return mCoreMachPort;
    }

    private static class NSPortDelegateWrapper implements MachPortDelegate {
        protected final MachPortFactory machPortFactory;
        protected final PortMessageFactory portMessageFactory;
        private final PortDelegate mPortDelegate;

        public NSPortDelegateWrapper(PortDelegate portDelegate, MachPortFactory machPortFactory,
                                     PortMessageFactory portMessageFactory) {
            mPortDelegate = portDelegate;
            this.machPortFactory = machPortFactory;
            this.portMessageFactory = portMessageFactory;
        }

        @Override
        public void handleMachMessage(MachMessage machMessage) {
            MachMessageData data = (MachMessageData) machMessage.data;
            MachPort sendPort = machPortFactory.create(machMessage.destinationPort);
            MachPort receivePort = machPortFactory.create(machMessage.replyPort);
            List components = null;
            PortMessage portMessage = portMessageFactory.create(sendPort, receivePort, components);
            portMessage.setMsgid(data.msgId);
            mPortDelegate.handlePortMessage(portMessage);
        }

        @Override
        public void handlePortMessage(PortMessage portMessage) {
            mPortDelegate.handlePortMessage(portMessage);
        }

        @Override
        public Factory getFactory() {
            return new CoderBaseFactory() {
                @Override
                protected NSPortDelegateWrapper clone() throws CloneNotSupportedException {
                    return NSPortDelegateWrapper.this;
                }
            };
        }
    }

    private static class MachMessageData {
        public int msgId;
        public ByteBuffer bytes;
        public Integer replyId;

        public MachMessageData(int replyId, int msgId, ByteBuffer bytes) {
            this.bytes = bytes;
            this.msgId = msgId;
            this.replyId = replyId;
        }
    }
}
