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

import java.util.HashMap;
import java.util.Map;

@Header("CFMachPort")
@OpaqueType("CFMachPortRef")
public class CoreMachPort implements CoreType {
    private static Map<MachPort, CoreMachPort> sMachPorts = new HashMap<MachPort, CoreMachPort>();
    public MachPort portNum;
    public CoreRunLoopSourcePort source;
    public ReceiveHandler receiveHandler;
    public InvalidationHandler invalidationHandler;

    @CFunction("CFMachPortCreate")
    public static CoreMachPort create(ReceiveHandler callout) {
        return create(new MachPort("org.kavaproject.corefoundation.CFMachPort"), callout);
    }

    @CFunction("CFMachPortCreateWithPort")
    public static CoreMachPort create(MachPort portNum, ReceiveHandler callout) {
        synchronized (sMachPorts) {
            CoreMachPort port = getMachPort(portNum);
            if (port != null) {
                return port;
            } else {
                port = new CoreMachPort();
                port.receiveHandler = callout;
                port.portNum = portNum;
                putMachPort(portNum, port);
                return port;
            }
        }
    }

    private static CoreMachPort getMachPort(MachPort name) {
        return sMachPorts.get(name);
    }

    private static CoreMachPort putMachPort(MachPort name, CoreMachPort port) {
        return sMachPorts.put(name, port);
    }

    private static CoreMachPort removeNativePort(MachPort name) {
        return sMachPorts.remove(name);
    }

    private static boolean containsNativePort(MachPort name) {
        return sMachPorts.containsKey(name);
    }

    @CFunction("CFMachPortInvalidate")
    public void invalidate() {
        synchronized (this) {
            if (!isValid()) {
                return;
            }
            if (invalidationHandler != null) {
                invalidationHandler.execute(this);
            }
            portNum.invalidate();
            if (source != null) {
                source.invalidate();
            }
        }
    }

    @CFunction("CFMachPortIsValid")
    public boolean isValid() {
        return portNum.isValid();
    }

    @CFunction("CFMachPortCreateRunLoopSource")
    public synchronized CoreRunLoopSource createRunLoopSource(int order) {
        if (!isValid()) {
            return null;
        }
        if (source == null || !source.isValid()) {
            source = new CoreRunLoopSourcePort() {
                @Override
                public MachMessage onMachPerform(MachMessage message, int size) {
                    receiveHandler.execute(CoreMachPort.this, message);
                    return null;
                }

                @Override
                public MachPort onGetPort() {
                    return portNum;
                }
            };
        }
        return source;
    }

    @CFunction("CFMachPortGetInvalidationCallBack")
    public InvalidationHandler getInvalidationCallBack() {
        return invalidationHandler;
    }

    @CFunction("CFMachPortSetInvalidationCallBack")
    public void setInvalidationCallBack(InvalidationHandler callout) {
        synchronized (this) {
            invalidationHandler = callout;
            if (callout != null && !isValid()) {
                callout.execute(this);
            }
        }
    }

    @CFunction("CFMachPortGetPort")
    public MachPort getPort() {
        return portNum;
    }

    public interface ReceiveHandler {
        void execute(CoreMachPort port, MachMessage message);
    }

    public interface InvalidationHandler {
        void execute(CoreMachPort port);
    }
}
