/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.corefoundation;

import org.kavaproject.kavatouch.internal.*;
import org.kavaproject.kavatouch.util.AndroidConversions;
import org.kavaproject.kavatouch.util.NotImplementedException;
import org.kavaproject.kavatouch.util.OutArg;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

@Header("CFMessagePort")
@OpaqueType("CFMessagePortRef")
public class CoreMessagePort implements CoreType {
    private static final ByteBuffer EMPTY_RETURN = ByteBuffer.allocate(0);
    private static Map<String, CoreMessagePort> sLocalPorts = new HashMap<String, CoreMessagePort>();
    private static Map<String, CoreMessagePort> sRemotePorts = new HashMap<String, CoreMessagePort>();
    private static ConcurrentMap<String, MachPort> sMachPorts = new ConcurrentHashMap<String, MachPort>();
    private static ConcurrentMap<CoreMachPort, Map<Integer, ByteBuffer>> sReplies = new
            ConcurrentHashMap<CoreMachPort, Map<Integer, ByteBuffer>>();
    public String name;
    public ReceiveHandler messageReceivedCallBack;
    public InvalidationHandler invalidationCallBack;
    public CoreMachPort machPort;
    public CoreRunLoopSource source;
    public CoreMachPort replyPort;
    public Map<Integer, ByteBuffer> replies = new HashMap<Integer, ByteBuffer>();
    public int replyIdCounter;

    @CFunction("CFMessagePortCreateLocal")
    public static CoreMessagePort createLocal(String name, ReceiveHandler handler) {
        synchronized (sLocalPorts) {
            CoreMessagePort port = sLocalPorts.get(name);
            if (port != null) {
                return port;
            } else {
                port = new CoreMessagePort();
                port.messageReceivedCallBack = handler;
                if (name != null) {
                    port.setName(name);
                }
                return port;
            }
        }
    }

    @CFunction("CFMessagePortSetName")
    public synchronized boolean setName(String newName) {
        if (sLocalPorts.containsKey(newName)) {
            return false;
        }
        CoreMachPort oldPort = null;
        if (name != null) {
            sLocalPorts.remove(name);
            oldPort = machPort;
        }
        MachPort portNum = createMachPort(newName);
        machPort = CoreMachPort.create(portNum, new CoreMachPort.ReceiveHandler() {
            @Override
            public void execute(CoreMachPort port, MachMessage message) {
                MachMessageData data = (MachMessageData) message.data;
                messageReceivedCallBack.execute(CoreMessagePort.this, data.msgId, data.bytes);
            }
        });
        name = newName;
        sLocalPorts.put(newName, this);
        if (oldPort != null) {
            oldPort.invalidate();
        }
        return true;
    }

    private static MachPort createMachPort(String newName) {
        MachPort machPort = new MachPort("org.kavaproject.corefoundation.CFMessagePort");
        sMachPorts.put(newName, machPort);
        return machPort;
    }

    @CFunction("CFMessagePortCreateRemote")
    public static CoreMessagePort createRemote(String name) {
        synchronized (sRemotePorts) {
            CoreMessagePort messagePort = sRemotePorts.get(name);
            if (messagePort != null) {
                return messagePort;
            } else {
                messagePort = new CoreMessagePort();
                messagePort.name = name;
                messagePort.messageReceivedCallBack = new ReceiveHandler() {
                    @Override
                    public ByteBuffer execute(CoreMessagePort local, int msgid, ByteBuffer data) {
                        Map<Integer, ByteBuffer> replyData = sReplies.get(local);
                        if (replyData != null) {
                            replyData.put(msgid, data == null ? EMPTY_RETURN : data);
                        }
                        return null;
                    }
                };
                MachPort portNum = getMachPort(name);
                final CoreMessagePort finalMessagePort = messagePort;
                messagePort.machPort = CoreMachPort.create(portNum, new CoreMachPort.ReceiveHandler() {
                    @Override
                    public void execute(CoreMachPort port, MachMessage message) {
                        MachMessageData data = (MachMessageData) message.data;
                        finalMessagePort.messageReceivedCallBack.execute(finalMessagePort, data.msgId, data.bytes);
                    }
                });
                sRemotePorts.put(name, messagePort);
                return messagePort;
            }
        }
    }

    private static MachPort getMachPort(String name) {
        return sMachPorts.get(name);
    }

    @CFunction("CFMessagePortCreateRunLoopSource")
    public synchronized CoreRunLoopSource createRunLoopSource(int order) {
        if (!isValid() || isRemote()) {
            return null;
        }
        if (source == null || !source.isValid()) {
            source = machPort.createRunLoopSource(0);
        }
        return source;
    }

    @CFunction("CFMessagePortIsRemote")
    public boolean isRemote() {
        return messageReceivedCallBack == null;
    }

    @CFunction("CFMessagePortIsValid")
    public boolean isValid() {
        return name != null && machPort.isValid();
    }

    @CFunction("CFMessagePortInvalidate")
    public void invalidate() {
        if (!isValid()) {
            return;
        }
        if (invalidationCallBack != null) {
            invalidationCallBack.execute(this);
        }
        if (isRemote()) {
            sRemotePorts.remove(name);
            if (replyPort != null) {
                replyPort.invalidate();
            }
        } else {
            sLocalPorts.remove(name);
            machPort.invalidate();
        }
        if (source != null) {
            source.invalidate();
        }
    }

    @CFunction("CFMessagePortSendRequest")
    public synchronized CoreMessagePortSendRequestResult sendRequest(int msgid, ByteBuffer data, double sendTimeout,
                                                                     double rcvTimeout, String replyMode,
                                                                     OutArg<ByteBuffer> returnData) {
        int replyId = -1;
        CoreRunLoop rl = CoreRunLoop.getCurrent();
        CoreRunLoopSource source = null;
        if (!isValid()) {
            return CoreMessagePortSendRequestResult.PORT_IS_INVALID;
        }
        if (this.replyPort == null) {
            this.replyPort = CoreMachPort.create(new CoreMachPort.ReceiveHandler() {
                @Override
                public void execute(CoreMachPort port, MachMessage message) {
                    MachMessageData data = (MachMessageData) message.data;
                    if (!replies.containsKey(data.replyId)) {
                        return;
                    }
                    replies.put(data.replyId, data.bytes == null ? EMPTY_RETURN : data.bytes);
                }
            });
        }
        if (replyMode != null) {
            replyId = this.replyIdCounter++;
            source = this.replyPort.createRunLoopSource(0);
            rl.addSource(source, replyMode);
        }
        MachMessage message = new MachMessage();
        message.destinationPort = this.machPort.getPort();
        message.replyPort = replyMode != null ? this.replyPort.getPort() : null;
        message.data = new MachMessageData(replyId, msgid, data);
        MachResult result = message.send(AndroidConversions.toMillisTimeInterval(sendTimeout), TimeUnit.MILLISECONDS);
        if (result != MachResult.SUCCESS) {
            rl.removeSource(source, replyMode);
            return result == MachResult.SEND_TIMEOUT ? CoreMessagePortSendRequestResult.SEND_TIMEOUT :
                    CoreMessagePortSendRequestResult.TRANSPORT_ERROR;
        }
        if (replyMode == null) {
            return CoreMessagePortSendRequestResult.SUCCESS;
        }
        double now = CoreAbsoluteTime.getCurrent();
        double deadline = now + rcvTimeout;
        double remainingTime = rcvTimeout;
        ByteBuffer reply = null;
        while (remainingTime > 0) {
            CoreRunLoop.runInMode(replyMode, remainingTime, true);
            reply = this.replies.get(replyId);
            if (reply != null) {
                break;
            }
            remainingTime = deadline - CoreAbsoluteTime.getCurrent();
        }
        rl.removeSource(source, replyMode);
        this.replies.remove(replyId);
        if (returnData != null) {
            returnData.value = reply == EMPTY_RETURN ? null : reply;
        }
        if (reply == null) {
            return isValid() ? CoreMessagePortSendRequestResult.RECEIVE_TIMEOUT : CoreMessagePortSendRequestResult
                    .PORT_BECAME_INVALID_ERROR;
        } else {
            return CoreMessagePortSendRequestResult.SUCCESS;
        }
    }

    @CFunction("CFMessagePortSetDispatchQueue")
    public void setDispatchQueue(Queue queue) {
        throw new NotImplementedException();
    }

    @CFunction("CFMessagePortGetInvalidationCallBack")
    public InvalidationHandler getInvalidationCallBack() {
        return invalidationCallBack;
    }

    @CFunction("CFMessagePortSetInvalidationCallBack")
    public synchronized void setInvalidationCallBack(InvalidationHandler callout) {
        invalidationCallBack = callout;
        if (callout != null && !isValid()) {
            callout.execute(this);
        }
    }

    @CFunction("CFMessagePortGetName")
    public String getName() {
        return name;
    }

    public interface ReceiveHandler {
        ByteBuffer execute(CoreMessagePort local, int msgid, ByteBuffer data);
    }

    public interface InvalidationHandler {
        void execute(CoreMessagePort port);
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
