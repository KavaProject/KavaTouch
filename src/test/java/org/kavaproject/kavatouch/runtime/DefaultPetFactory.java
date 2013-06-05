/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.runtime;

public class DefaultPetFactory implements PetFactory {
    public static final String TEST_EAT = "PetFactory/eat/";
    public static final String TEST_NAP = "PetFactory/nap/";
    public static final IMP META_IMP_EAT = new IMP1R<String, PetFactory, String>() {
        @Override
        public String invoke(PetFactory receiver, SEL sel, String s) {
            return receiver.eat(s);
        }
    };
    public static final IMP META_IMP_NAP = new IMP1R<String, PetFactory, String>() {
        @Override
        public String invoke(PetFactory receiver, SEL sel, String s) {
            return receiver.nap(s);
        }
    };
    public static final IMP IMP_EAT = new IMP1R<String, Pet, String>() {
        @Override
        public String invoke(Pet receiver, SEL sel, String s) {
            return receiver.eat(s);
        }
    };
    public static final IMP IMP_NAP = new IMP1R<String, Pet, String>() {
        @Override
        public String invoke(Pet receiver, SEL sel, String s) {
            return receiver.nap(s);
        }
    };

    protected DefaultPetFactory(FactoryRegistry factoryRegistry, MethodResolver methodResolver) {
        if (DefaultPetFactory.class == getClass()) {
            factoryRegistry.register(PetFactory.class, this);
            factoryRegistry.register(DefaultPetFactory.class, this);
            methodResolver.register(DefaultPetFactory.class, SEL_EAT, META_IMP_EAT);
            methodResolver.register(DefaultPet.class, SEL_EAT, IMP_EAT);
            methodResolver.register(DefaultPetFactory.class, SEL_NAP, META_IMP_NAP);
            methodResolver.register(DefaultPet.class, SEL_NAP, IMP_NAP);
        }
    }

    @Override
    public Pet create() {
        return new DefaultPet(this);
    }

    @Override
    public String eat(String arg) {
        return TEST_EAT + arg;
    }

    @Override
    public String nap(String arg) {
        return TEST_NAP + arg;
    }
}
