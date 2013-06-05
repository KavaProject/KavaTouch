/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.runtime;


public class DefaultCatFactory extends DefaultPetFactory implements CatFactory {
    public static final String TEST_MEAW = "CatFactory/meaw/";
    public static final String TEST_EAT = "CatFactory/eat/";
    public static final IMP META_IMP_MEAW = new IMP1R<String, CatFactory, String>() {
        @Override
        public String invoke(CatFactory receiver, SEL sel, String s) {
            return receiver.meaw(s);
        }
    };
    public static final IMP IMP_MEAW = new IMP1R<String, Cat, String>() {
        @Override
        public String invoke(Cat receiver, SEL sel, String s) {
            return receiver.meaw(s);
        }
    };

    protected DefaultCatFactory(FactoryRegistry factoryRegistry, MethodResolver methodResolver) {
        super(factoryRegistry, methodResolver);
        if (DefaultCatFactory.class == getClass()) {
            factoryRegistry.register(CatFactory.class, this);
            factoryRegistry.register(DefaultCatFactory.class, this);
            methodResolver.register(DefaultCatFactory.class, SEL_MEAW, META_IMP_MEAW);
            methodResolver.register(DefaultCat.class, SEL_MEAW, IMP_MEAW);
        }
    }

    @Override
    public Cat create() {
        return new DefaultCat(this);
    }

    @Override
    public String eat(String arg) {
        return TEST_EAT + arg;
    }

    @Override
    public String meaw(String arg) {
        return TEST_MEAW + arg;
    }
}
