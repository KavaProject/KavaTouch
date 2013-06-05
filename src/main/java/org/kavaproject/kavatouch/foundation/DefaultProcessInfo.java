/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.foundation;

import android.os.SystemClock;
import org.kavaproject.kavatouch.util.AndroidConversions;
import org.kavaproject.kavatouch.util.NotImplementedException;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class DefaultProcessInfo implements ProcessInfo {
    private final ProcessInfoFactory mProcessInfoFactory;

    protected DefaultProcessInfo(ProcessInfoFactory processInfoFactory) {
        mProcessInfoFactory = processInfoFactory;
    }

    @Override
    public List<String> arguments() {
        return Collections.EMPTY_LIST;
    }

    @Override
    public Map<String, Object> environment() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public int processIdentifier() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public String globallyUniqueString() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public String processName() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void setProcessName(String name) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public String hostName() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public int operatingSystem() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public String operatingSystemName() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public String operatingSystemVersionString() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public long physicalMemory() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public int processorCount() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public int activeProcessorCount() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public double systemUptime() {
        return AndroidConversions.toSecondsTimeSpan(SystemClock.uptimeMillis());
    }

    @Override
    public ProcessInfoFactory getFactory() {
        return mProcessInfoFactory;
    }
}
