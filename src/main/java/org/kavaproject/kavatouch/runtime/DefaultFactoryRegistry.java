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

public class DefaultFactoryRegistry implements FactoryRegistry {
    private final Map<Class<? extends Factory>, Factory> mFactories = new HashMap<Class<? extends Factory>, Factory>();

    @Inject
    protected DefaultFactoryRegistry() {
        register(RootFactory.class, ROOT);
    }

    @Override
    public void register(Class<? extends Factory> clazz, Factory factory) {
        assert mFactories.get(clazz) == null : clazz.toString() + " is already bound.";
        mFactories.put(clazz, factory);
    }

    @Override
    public boolean isProductMember(Object receiver, Factory factory) {
        return getFactory(receiver) == factory;
    }

    @Override
    public Factory getFactory(Object product) {
        return product instanceof Creatable ? ((Creatable) product).getFactory() : FactoryRegistry.ROOT;
    }

    @Override
    public boolean isKindOfCreatable(Object receiver, Factory factory) {
        return isSubfactory(getFactory(receiver), factory);
    }

    @Override
    public boolean isSubfactory(Factory factory, Factory occClass) {
        Factory candidate = factory;
        while (candidate != null) {
            if (candidate == occClass) {
                return true;
            }
            candidate = getSuperfactory(candidate);
        }
        return false;
    }

    @Override
    public Factory getSuperfactory(Object product) {
        if (product == FactoryRegistry.ROOT) {
            return null;
        }
        Class factoryClass = product instanceof Factory ? product.getClass() : getFactory(product).getClass();
        Factory factory = mFactories.get(factoryClass.getSuperclass());
        return factory != null ? factory : FactoryRegistry.ROOT;
    }
}
