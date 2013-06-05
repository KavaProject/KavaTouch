/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.runtime;

import org.kavaproject.kavatouch.internal.Module;
import com.google.inject.Provides;

@Module
public class PetAndCatModule {
    @Provides
    public PetFactory providePetFactory(FactoryRegistry factoryRegistry, MethodResolver methodRegistry) {
        return new DefaultPetFactory(factoryRegistry, methodRegistry);
    }

    @Provides
    public CatFactory provideCatFactory(FactoryRegistry factoryRegistry, MethodResolver methodRegistry) {
        return new DefaultCatFactory(factoryRegistry, methodRegistry);
    }
}
