/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.uikit;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class DefaultUIApplicationWindows implements UIApplicationWindows {
    private UIWindow mKeyWindow;
    private List<UIWindow> mWindows = new ArrayList<UIWindow>();

    @Inject
    protected DefaultUIApplicationWindows() {

    }

    @Override
    public void addWindow(UIWindow window) {
        mWindows.add(window);
    }

    @Override
    public UIWindow getKeyWindow() {
        return mKeyWindow;
    }

    @Override
    public void setKeyWindow(UIWindow window) {
        mKeyWindow = window;
    }

    @Override
    public List<UIWindow> getWindows() {
        return mWindows;
    }
}
