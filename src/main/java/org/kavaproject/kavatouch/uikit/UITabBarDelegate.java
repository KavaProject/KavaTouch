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

import java.util.List;

@Header("UITabBar")
@OccProtocol("UITabBarDelegate")
public interface UITabBarDelegate {
    @OccProtocolInstanceMethod("tabBar:willBeginCustomizingItems:")
    void onTabBarWillBeginCustomizingItems(UITabBar tabBar, List<UITabBarItem> items);

    @OccProtocolInstanceMethod("tabBar:didBeginCustomizingItems:")
    void onTabBarDidBeginCustomizingItems(UITabBar tabBar, List<UITabBarItem> items);

    @OccProtocolInstanceMethod("tabBar:willEndCustomizingItems:changed:")
    void onTabBarWillEndCustomizingItems(UITabBar tabBar, List<UITabBarItem> items, boolean changed);

    @OccProtocolInstanceMethod("tabBar:didEndCustomizingItems:changed:")
    void onTabBarDidEndCustomizingItems(UITabBar tabBar, List<UITabBarItem> items, boolean changed);

    @OccProtocolInstanceMethod(value = "tabBar:didSelectItem:", required = true)
    void onTabBarDidSelectItem(UITabBar tabBar, UITabBarItem item);
}
