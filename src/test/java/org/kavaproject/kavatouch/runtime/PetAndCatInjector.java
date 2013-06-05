/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.runtime;

import org.kavaproject.kavatouch.Injector;

public final class PetAndCatInjector {
    private final Injector di;
    private final PetAndCatModule mPetAndCatModule;
    private PetFactory mPetFactory;
    private CatFactory mCatFactory;

    public PetAndCatInjector(Injector injector) {
        di = injector;
        mPetAndCatModule = new PetAndCatModule();
    }

    public PetFactory injectPetFactory() {
        if (mPetFactory == null) {
            mPetFactory = mPetAndCatModule.providePetFactory(di.injectFactoryRegistry(), di.injectMethodResolver());
        }
        return mPetFactory;
    }

    public CatFactory injectCatFactory() {
        if (mCatFactory == null) {
            mCatFactory = mPetAndCatModule.provideCatFactory(di.injectFactoryRegistry(), di.injectMethodResolver());
        }
        return mCatFactory;
    }
}
