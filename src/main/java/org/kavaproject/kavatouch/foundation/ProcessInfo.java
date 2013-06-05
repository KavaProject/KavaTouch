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

import java.util.List;
import java.util.Map;

@Header("NSProcessInfo")
@OccClass("NSProcessInfo")
public interface ProcessInfo extends Creatable {
    @OccInstanceMethod("arguments")
    List<String> arguments();

    @OccInstanceMethod("environment")
    Map<String, Object> environment();

    @OccInstanceMethod("processIdentifier")
    int processIdentifier();

    @OccInstanceMethod("globallyUniqueString")
    String globallyUniqueString();

    @OccInstanceMethod("processName")
    String processName();

    @OccInstanceMethod("setProcessName:")
    void setProcessName(String name);

    @OccInstanceMethod("hostName")
    String hostName();

    @OccInstanceMethod("operatingSystem")
    int operatingSystem();

    @OccInstanceMethod("operatingSystemName")
    String operatingSystemName();

    @OccInstanceMethod("operatingSystemVersionString")
    String operatingSystemVersionString();

    @OccInstanceMethod("physicalMemory")
    long physicalMemory();

    @OccInstanceMethod("processorCount")
    int processorCount();

    @OccInstanceMethod("activeProcessorCount")
    int activeProcessorCount();

    @OccInstanceMethod("systemUptime")
    double systemUptime();

    @Override
    ProcessInfoFactory getFactory();
}
