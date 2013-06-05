/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.runtime;

/**
 * TODO
 */
public interface CatFactory extends PetFactory {
    SEL SEL_MEAW = SEL.getInstance("meaw:");

    @Override
    Cat create();

    @Override
    String eat(String arg);

    String meaw(String arg);
}