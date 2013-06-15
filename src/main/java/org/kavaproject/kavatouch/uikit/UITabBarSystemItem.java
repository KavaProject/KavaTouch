/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.uikit;

import org.kavaproject.kavatouch.internal.CEnumConstant;
import org.kavaproject.kavatouch.internal.CTypedef;
import org.kavaproject.kavatouch.internal.Header;

@Header("UITabBarItem")
@CTypedef("UITabBarSystemItem")
public enum  UITabBarSystemItem {
    @CEnumConstant("UITabBarSystemItemMore")
    MORE,
    @CEnumConstant("UITabBarSystemItemFavorites")
    FAVORITES,
    @CEnumConstant("UITabBarSystemItemFeatured")
    FEATURED,
    @CEnumConstant("UITabBarSystemItemTopRated")
    TOP_RATED,
    @CEnumConstant("UITabBarSystemItemRecents")
    RECENTS,
    @CEnumConstant("UITabBarSystemItemContacts")
    CONTACTS,
    @CEnumConstant("UITabBarSystemItemHistory")
    HISTORY,
    @CEnumConstant("UITabBarSystemItemBookmarks")
    BOOKMARKS,
    @CEnumConstant("UITabBarSystemItemSearch")
    SEARCH,
    @CEnumConstant("UITabBarSystemItemDownloads")
    DOWNLOADS,
    @CEnumConstant("UITabBarSystemItemMostRecent")
    MOST_RECENT,
    @CEnumConstant("UITabBarSystemItemMostViewed")
    MOST_VIEWED
}
