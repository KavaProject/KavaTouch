/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.foundation;

import org.kavaproject.kavatouch.corefoundation.CoreRunLoop;
import org.kavaproject.kavatouch.internal.Header;
import org.kavaproject.kavatouch.internal.OccClass;
import org.kavaproject.kavatouch.internal.OccConstant;
import org.kavaproject.kavatouch.internal.OccInstanceMethod;
import org.kavaproject.kavatouch.runtime.Creatable;
import org.kavaproject.kavatouch.runtime.SEL;

import java.util.List;

@Header("NSRunLoop")
@OccClass("NSRunLoop")
public interface RunLoop extends Creatable, CoreBridge<CoreRunLoop> {
    @OccConstant("NSDefaultRunLoopMode")
    String MODE_DEFAULT = CoreRunLoop.MODE_DEFAULT;
    @OccConstant("NSRunLoopCommonModes")
    String MODES_COMMON = CoreRunLoop.MODES_COMMON;
    @OccConstant("NSConnectionReplyMode")
    String MODE_CONNECTION_REPLY = "NSConnectionReplyMode";
    @OccConstant("NSModalPanelRunLoopMode")
    String MODE_MODAL_PANEL = "NSModalPanelRunLoopMode";
    @OccConstant("NSEventTrackingRunLoopMode")
    String MODE_EVENT_TRACKING = "NSEventTrackingRunLoopMode";

    @OccInstanceMethod("currentMode")
    String currentMode();

    @OccInstanceMethod("limitDateForMode:")
    FoundationDate limitDateForMode(String mode);

    @OccInstanceMethod("getCFRunLoop")
    @Override
    CoreRunLoop toCoreType();

    @OccInstanceMethod("addTimer:ForMode:")
    void addTimer(Timer timer, String mode);

    @OccInstanceMethod("addPort:forMode:")
    void addPort(Port port, String mode);

    @OccInstanceMethod("removePort:forMode:")
    void removePort(Port port, String mode);

    @OccInstanceMethod("run")
    void run();

    @OccInstanceMethod("runMode:beforeDate:")
    boolean runUntil(String mode, FoundationDate limitDate);

    @OccInstanceMethod("runUntilDate:")
    void runUntil(FoundationDate limitDate);

    @OccInstanceMethod("acceptInputForMode:beforeDate:")
    void acceptInputForMode(String mode, FoundationDate limitDate);

    @OccInstanceMethod("performSelector:target:argument:order:modes:")
    void perform(SEL selector, Object target, Object argument, int order, List<String> modes);

    @OccInstanceMethod("cancelPerformSelector:target:argument:")
    void cancelPerform(SEL selector, Object target, Object argument);

    @OccInstanceMethod("cancelPerformSelectorsWithTarget:")
    void cancelPerform(Object target);

    @Override
    RunLoopFactory getFactory();
}
