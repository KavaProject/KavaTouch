/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.runtime;

public class DefaultPet implements Pet {
    public static final String TEST_EAT = "Pet/eat/";
    public static final String TEST_NAP = "Pet/nap/";
    protected final PetFactory petFactory;

    public DefaultPet(PetFactory petFactory) {
        this.petFactory = petFactory;
    }

    @Override
    public String eat(String arg) {
        return TEST_EAT + arg;
    }

    @Override
    public String nap(String arg) {
        return TEST_NAP + arg;
    }

    @Override
    public Factory getFactory() {
        return petFactory;
    }
}
