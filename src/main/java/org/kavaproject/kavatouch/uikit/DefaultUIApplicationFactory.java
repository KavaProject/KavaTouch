/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.uikit;

import org.kavaproject.kavatouch.runtime.MethodPerformer;
import org.kavaproject.kavatouch.runtime.MethodResolver;

import javax.inject.Inject;

public class DefaultUIApplicationFactory extends SimpleUIResponderFactory implements UIApplicationFactory {
    private final UIDevice mCurrentUIDevice;
    private final MethodPerformer mMethodPerformer;
    private final UIApplicationWindows mUIApplicationWindows;

    @Inject
    protected DefaultUIApplicationFactory(UIDevice currentUIDevice, MethodResolver methodResolver,
                                          MethodPerformer methodPerformer, UIApplicationWindows uiApplicationWindows) {
        super(methodResolver);
        mCurrentUIDevice = currentUIDevice;
        mMethodPerformer = methodPerformer;
        mUIApplicationWindows = uiApplicationWindows;
    }

    @Override
    public UIApplication create() {
        return new DefaultUIApplication(this, mCurrentUIDevice, getMethodResolver(), mMethodPerformer, mUIApplicationWindows);
    }
}
