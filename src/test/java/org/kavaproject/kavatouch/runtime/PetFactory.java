/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.runtime;


public interface PetFactory extends Factory {
    SEL SEL_EAT = SEL.getInstance("eat:");
    SEL SEL_NAP = SEL.getInstance("nap:");

    Pet create();

    String eat(String arg);

    String nap(String arg);
}
