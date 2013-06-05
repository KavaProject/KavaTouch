/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.uikit;

import org.kavaproject.kavatouch.foundation.Bundle;
import org.kavaproject.kavatouch.foundation.Coder;
import org.kavaproject.kavatouch.runtime.MethodResolver;
import org.kavaproject.kavatouch.util.NotImplementedException;

import javax.inject.Inject;

public class DefaultUIViewControllerFactory extends SimpleUIResponderFactory implements UIViewControllerFactory {
    private final UIViewFactory mUIViewFactory;

    @Inject
    protected DefaultUIViewControllerFactory(UIViewFactory uiViewFactory, MethodResolver methodResolver) {
        super(methodResolver);
        mUIViewFactory = uiViewFactory;
    }

    @Override
    public UIViewController create(String nibName, Bundle nibBundle) {
        return new DefaultUIViewController(nibName, nibBundle, this, mUIViewFactory, getMethodResolver());
    }

    @Override
    public void attemptRotationToDeviceOrientation() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public UIViewController create(Coder decoder) {
        throw new NotImplementedException(); //TODO
    }
}
