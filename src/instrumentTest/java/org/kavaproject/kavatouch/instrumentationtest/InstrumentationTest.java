/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.instrumentationtest;

import android.test.ActivityInstrumentationTestCase2;

/**
 * Only works from gradle.
 */
public class InstrumentationTest extends ActivityInstrumentationTestCase2<InstrumentationActivity> {
    private boolean mTest;

    public InstrumentationTest() {
        super(InstrumentationActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        mTest = true;
    }

    public void testFoo() {
        assertTrue(mTest);
    }
}
