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

public class MethodPerformerTest {
    private static final String TEST = "test";
    private PetFactory mPetFactory;
    private CatFactory mCatFactory;
    private Pet mPet;
    private Cat mCat;
    private MethodPerformer mMethodPerformer;

    @Before
    public void setUp() throws Exception {
        Injector.setInstance(new Injector(new DeviceHandleMock()));
        Injector injector = Injector.getInstance();
        PetAndCatInjector petAndCatInjector = new PetAndCatInjector(injector);
        mPetFactory = petAndCatInjector.injectPetFactory();
        mCatFactory = petAndCatInjector.injectCatFactory();
        mPet = mPetFactory.create();
        mCat = mCatFactory.create();
        mMethodPerformer = injector.injectMethodPerformer();
    }

    @Test
    public void testCallInstance() throws Exception {
        Assert.assertEquals(DefaultPet.TEST_EAT + TEST, mPet.eat(TEST));
        Assert.assertEquals(DefaultPet.TEST_NAP + TEST, mPet.nap(TEST));
        Assert.assertEquals(DefaultCat.TEST_EAT + TEST, mCat.eat(TEST));
        Assert.assertEquals(DefaultCat.TEST_NAP + TEST, mCat.nap(TEST));
        Assert.assertEquals(DefaultCat.TEST_MEAW + TEST, mCat.meaw(TEST));
    }

    @Test
    public void testCallFactory() throws Exception {
        Assert.assertEquals(DefaultPetFactory.TEST_EAT + TEST, mPetFactory.eat(TEST));
        Assert.assertEquals(DefaultPetFactory.TEST_NAP + TEST, mPetFactory.nap(TEST));
        Assert.assertEquals(DefaultCatFactory.TEST_EAT + TEST, mCatFactory.eat(TEST));
        Assert.assertEquals(DefaultCatFactory.TEST_NAP + TEST, mCatFactory.nap(TEST));
        Assert.assertEquals(DefaultCatFactory.TEST_MEAW + TEST, mCatFactory.meaw(TEST));
    }

    @Test
    public void testPerformInstance() throws Exception {
        Assert.assertEquals(DefaultPet.TEST_EAT + TEST, mMethodPerformer.perform(mPet, PetFactory.SEL_EAT, TEST));
        Assert.assertEquals(DefaultPet.TEST_NAP + TEST, mMethodPerformer.perform(mPet, PetFactory.SEL_NAP, TEST));
        Assert.assertEquals(DefaultCat.TEST_EAT + TEST, mMethodPerformer.perform(mCat, PetFactory.SEL_EAT, TEST));
        Assert.assertEquals(DefaultCat.TEST_NAP + TEST, mMethodPerformer.perform(mCat, PetFactory.SEL_NAP, TEST));
        Assert.assertEquals(DefaultCat.TEST_MEAW + TEST, mMethodPerformer.perform(mCat, CatFactory.SEL_MEAW, TEST));
    }

    @Test
    public void testPerformFactory() throws Exception {
        Assert.assertEquals(DefaultPetFactory.TEST_EAT + TEST, mMethodPerformer.perform(mPetFactory,
                PetFactory.SEL_EAT, TEST));
        Assert.assertEquals(DefaultPetFactory.TEST_NAP + TEST, mMethodPerformer.perform(mPetFactory,
                PetFactory.SEL_NAP, TEST));
        Assert.assertEquals(DefaultCatFactory.TEST_EAT + TEST, mMethodPerformer.perform(mCatFactory,
                PetFactory.SEL_EAT, TEST));
        Assert.assertEquals(DefaultCatFactory.TEST_NAP + TEST, mMethodPerformer.perform(mCatFactory,
                PetFactory.SEL_NAP, TEST));
        Assert.assertEquals(DefaultCatFactory.TEST_MEAW + TEST, mMethodPerformer.perform(mCatFactory, CatFactory.SEL_MEAW, TEST));
    }
}
