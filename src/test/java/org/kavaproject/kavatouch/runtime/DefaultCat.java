/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.runtime;

public class DefaultCat extends DefaultPet implements Cat {
    public static final String TEST_MEAW = "Cat/meaw/";
    public static final String TEST_EAT = "Cat/eat/";
    protected final CatFactory catFactory;

    public DefaultCat(CatFactory catFactory) {
        super(catFactory);
        this.catFactory = catFactory;
    }

    @Override
    public String meaw(String arg) {
        return TEST_MEAW + arg;
    }

    @Override
    public String eat(String arg) {
        return TEST_EAT + arg;
    }

    @Override
    public Factory getFactory() {
        return catFactory;
    }
}
