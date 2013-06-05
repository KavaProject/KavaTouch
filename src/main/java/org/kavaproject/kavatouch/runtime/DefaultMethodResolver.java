/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.runtime;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

public class DefaultMethodResolver implements MethodResolver {
    private final Map<Class, Map<SEL, IMP>> mMethodImplementations = new HashMap<Class, Map<SEL, IMP>>();
    private final FactoryRegistry mFactoryRegistry;

    @Inject
    protected DefaultMethodResolver(FactoryRegistry factoryRegistry) {
        mFactoryRegistry = factoryRegistry;
    }

    @Override
    public <T> void register(Class<T> clazz, SEL selector, IMP<?, T> imp) {
        Map<SEL, IMP> impsBySelector = mMethodImplementations.get(clazz);
        if (impsBySelector == null) {
            impsBySelector = new HashMap<SEL, IMP>();
            mMethodImplementations.put(clazz, impsBySelector);
        }
        impsBySelector.put(selector, imp);
    }

    @Override
    public String[] getTypeEncoding(Object receiver, SEL selector) {
        IMP imp = findMethodWithClass(receiver.getClass(), selector);
        return imp == null ? null : imp.encode();
    }

    @Override
    public boolean resolveInstanceMethod(Factory factory, SEL selector) {
        return false;
    }

    @Override
    public boolean respondsToSelector(Object receiver, SEL sel) {
        return productsRespondToSelector(mFactoryRegistry.getFactory(receiver), sel);
    }

    @Override
    public boolean productsRespondToSelector(Factory receiver, SEL selector) {
        return findMethod(receiver, selector) != null;
    }

    @Override
    public IMP findMethod(Object receiver, SEL selector) {
        return findMethodWithClass(receiver.getClass(), selector);
    }

    private IMP findMethodWithClass(Class candidate, SEL sel) {
        IMP imp;
        do {
            Map<SEL, IMP> map = mMethodImplementations.get(candidate);
            imp = map == null ? null : map.get(sel);
            if (imp != null) {
                break;
            }
            candidate = candidate.getSuperclass();
        } while (candidate != null); //TODO Could stop earlier
        return imp;
    }
}
