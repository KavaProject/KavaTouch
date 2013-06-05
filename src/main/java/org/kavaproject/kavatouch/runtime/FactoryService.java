/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.runtime;

import org.kavaproject.kavatouch.foundation.Coder;
import org.kavaproject.kavatouch.foundation.KeyedArchiver;
import org.kavaproject.kavatouch.foundation.staging.Proxy;
import org.kavaproject.kavatouch.internal.OccClassMethod;
import org.kavaproject.kavatouch.internal.OccInstanceMethod;
import org.kavaproject.kavatouch.internal.OccProtocolInstanceMethod;

import java.util.List;

public interface FactoryService {
    @OccInstanceMethod(value = "autoContentAccessingProxy")
    Proxy autoContentAccessingProxy(Object receiver);

    @OccInstanceMethod(value = "awakeAfterUsingCoder:")
    Object awakeAfterUsingCoder(Object receiver, Coder decoder);

    @OccInstanceMethod(value = "classForCoder")
    Factory factoryForCoder(Object receiver);

    @OccInstanceMethod(value = "classForKeyedArchiver")
    Factory factoryForKeyedArchiver(Object receiver);

    @OccInstanceMethod(value = "replacementObjectForCoder:")
    Object replacementObject(Object receiver, Coder coder);

    @OccInstanceMethod(value = "replacementObjectForKeyedArchiver")
    Object replacementObject(Object receiver, KeyedArchiver archiver);

    @OccProtocolInstanceMethod("isProxy")
    boolean isProxy(Object receiver);

    @OccClassMethod(value = "conformsToProtocol:")
    boolean conformsToProtocol(Factory factory, Class protocol);

    @OccClassMethod("classFallbacksForKeyedArchiver")
    List<String> factoryFallbacksForKeyedArchiver(Factory factory);

    @OccClassMethod("classForKeyedUnarchiver")
    Factory factoryForKeyedUnarchiver(Factory factory);

    @OccClassMethod("setVersion:")
    void setVersion(Factory factory, int version);

    @OccClassMethod("version")
    int version(Factory factory);
}
