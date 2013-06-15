/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.uikit;

import org.kavaproject.kavatouch.internal.Header;
import org.kavaproject.kavatouch.internal.OccProtocol;
import org.kavaproject.kavatouch.internal.OccProtocolInstanceMethod;

@Header("UINavigationBar")
@OccProtocol("UINavigationBarDelegate")
public interface UINavigationBarDelegate {
    @OccProtocolInstanceMethod("navigationBar:shouldPushItem:")
    boolean onNavigationBarShouldPushItem(UINavigationBar navigationBar, UINavigationItem item);

    @OccProtocolInstanceMethod("navigationBar:didPushItem:")
    void onNavigationBarDidPushItem(UINavigationBar navigationBar, UINavigationItem item);

    @OccProtocolInstanceMethod("navigationBar:shouldPopItem:")
    boolean onNavigationBarShouldPopItem(UINavigationBar navigationBar, UINavigationItem item);

    @OccProtocolInstanceMethod("navigationBar:didPopItem:")
    void onNavigationBarDidPopItem(UINavigationBar navigationBar, UINavigationItem item);
}
