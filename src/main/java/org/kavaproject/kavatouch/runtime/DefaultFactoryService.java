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
import org.kavaproject.kavatouch.util.NotImplementedException;

import javax.inject.Inject;
import java.util.List;

public class DefaultFactoryService implements FactoryService {
    private final FactoryRegistry mFactoryRegistry;

    @Inject
    protected DefaultFactoryService(FactoryRegistry factoryRegistry) {
        mFactoryRegistry = factoryRegistry;
    }

    @Override
    public Proxy autoContentAccessingProxy(Object receiver) {
        throw new NotImplementedException();
    }

    @Override
    public Object awakeAfterUsingCoder(Object receiver, Coder decoder) {
        return receiver;
    }

    @Override
    public Factory factoryForCoder(Object receiver) {
        return mFactoryRegistry.getFactory(receiver);
    }

    @Override
    public Factory factoryForKeyedArchiver(Object receiver) {
        return null; //TODO Check
    }

    @Override
    public Object replacementObject(Object receiver, Coder coder) {
        return receiver;
    }

    @Override
    public Object replacementObject(Object receiver, KeyedArchiver archiver) {
        return null; //TODO Check
    }

    @Override
    public boolean isProxy(Object receiver) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public boolean conformsToProtocol(Factory factory, Class protocol) {
        return false;
    }

    @Override
    public List<String> factoryFallbacksForKeyedArchiver(Factory factory) {
        return null;
    }

    @Override
    public Factory factoryForKeyedUnarchiver(Factory factory) {
        throw new NotImplementedException();
    }

    @Override
    public void setVersion(Factory factory, int version) {
        throw new NotImplementedException();
    }

    @Override
    public int version(Factory factory) {
        throw new NotImplementedException();
    }
}
