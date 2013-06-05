/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.runtime;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kavaproject.kavatouch.Injector;
import org.kavaproject.kavatouch.util.DeviceHandleMock;

public class FactoryServiceTest {
    private Pet mPet;
    private Cat mCat;
    private PetFactory mPetFactory;
    private CatFactory mCatFactory;
    private FactoryRegistry mFactoryRegistry;

    @Before
    public void setUp() throws Exception {
        Injector.setInstance(new Injector(new DeviceHandleMock()));
        Injector injector = Injector.getInstance();
        PetAndCatInjector petAndCatInjector = new PetAndCatInjector(injector);
        mPetFactory = petAndCatInjector.injectPetFactory();
        mCatFactory = petAndCatInjector.injectCatFactory();
        mPet = new DefaultPet(mPetFactory);
        mCat = new DefaultCat(mCatFactory);
        mFactoryRegistry = injector.injectFactoryRegistry();
    }

    @Test
    public void testGet() throws Exception {
        Assert.assertEquals(DefaultPetFactory.class, mPetFactory.getClass());
        Assert.assertEquals(DefaultCatFactory.class, mCatFactory.getClass());
    }

    @Test
    public void testGetFactory() throws Exception {
        Assert.assertEquals(mPetFactory, mFactoryRegistry.getFactory(mPet));
        Assert.assertEquals(mCatFactory, mFactoryRegistry.getFactory(mCat));
        Assert.assertEquals(FactoryRegistry.ROOT, mFactoryRegistry.getFactory(mPetFactory));
        Assert.assertEquals(FactoryRegistry.ROOT, mFactoryRegistry.getFactory(mCatFactory));
        Assert.assertEquals(FactoryRegistry.ROOT, mFactoryRegistry.getFactory(FactoryRegistry.ROOT));
    }

    @Test
    public void testGetSuper() throws Exception {
        Assert.assertNull(mFactoryRegistry.getSuperfactory(FactoryRegistry.ROOT));
        Assert.assertEquals(FactoryRegistry.ROOT, mFactoryRegistry.getSuperfactory(mPet));
        Assert.assertEquals(mPetFactory, mFactoryRegistry.getSuperfactory(mCat));
        Assert.assertEquals(FactoryRegistry.ROOT, mFactoryRegistry.getSuperfactory(mPetFactory));
        Assert.assertEquals(mPetFactory, mFactoryRegistry.getSuperfactory(mCatFactory));
    }
}
