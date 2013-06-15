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

@Header("UIBarButtonItem")
@CTypedef("UIBarButtonSystemItem")
public enum UIBarButtonSystemItem {
    @CEnumConstant("UIBarButtonSystemItemDone")
    DONE,
    @CEnumConstant("UIBarButtonSystemItemCancel")
    CANCEL,
    @CEnumConstant("UIBarButtonSystemItemEdit")
    EDIT,
    @CEnumConstant("UIBarButtonSystemItemSave")
    SAVE,
    @CEnumConstant("UIBarButtonSystemItemAdd")
    ADD,
    @CEnumConstant("UIBarButtonSystemItemFlexibleSpace")
    FLEXIBLE_SPACE,
    @CEnumConstant("UIBarButtonSystemItemFixedSpace")
    FIXED_SPACE,
    @CEnumConstant("UIBarButtonSystemItemCompose")
    COMPOSE,
    @CEnumConstant("UIBarButtonSystemItemReply")
    REPLY,
    @CEnumConstant("UIBarButtonSystemItemAction")
    ACTION,
    @CEnumConstant("UIBarButtonSystemItemOrganize")
    ORGANIZE,
    @CEnumConstant("UIBarButtonSystemItemBookmarks")
    BOOKMARKS,
    @CEnumConstant("UIBarButtonSystemItemSearch")
    SEARCH,
    @CEnumConstant("UIBarButtonSystemItemRefresh")
    REFRESH,
    @CEnumConstant("UIBarButtonSystemItemStop")
    STOP,
    @CEnumConstant("UIBarButtonSystemItemCamera")
    CAMERA,
    @CEnumConstant("UIBarButtonSystemItemTrash")
    TRASH,
    @CEnumConstant("UIBarButtonSystemItemPlay")
    PLAY,
    @CEnumConstant("UIBarButtonSystemItemPause")
    PAUSE,
    @CEnumConstant("UIBarButtonSystemItemRewind")
    REWIND,
    @CEnumConstant("UIBarButtonSystemItemFastForward")
    FAST_FORWARD,
    @CEnumConstant("UIBarButtonSystemItemUndo")
    UNDO,
    @CEnumConstant("UIBarButtonSystemItemRedo")
    REDO,
    @CEnumConstant("UIBarButtonSystemItemPageCurl")
    PAGE_CURL,
}
