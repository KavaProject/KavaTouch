/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.uikit;

import org.kavaproject.kavatouch.foundation.Coding;
import org.kavaproject.kavatouch.internal.*;
import org.kavaproject.kavatouch.runtime.Creatable;
import org.kavaproject.kavatouch.uikit.staging.UIBarButtonItem;

import java.util.List;

@Header("UINavigationBar")
@OccClass("UINavigationItem")
public interface UINavigationItem extends Coding, Creatable {
    @OccInstanceProperty(value = "title", argumentSemantic = ArgumentSemantic.COPY)
    String getTitle();

    @OccInstanceProperty(value = "title", argumentSemantic = ArgumentSemantic.COPY)
    void setTitle(String value);

    @OccInstanceProperty(value = "prompt", argumentSemantic = ArgumentSemantic.COPY)
    String getPrompt();

    @OccInstanceProperty(value = "prompt", argumentSemantic = ArgumentSemantic.COPY)
    void setPrompt(String value);

    @OccInstanceProperty(value = "backBarButtonItem", argumentSemantic = ArgumentSemantic.RETAIN)
    UIBarButtonItem getBackBarButtonItem();

    @OccInstanceProperty(value = "backBarButtonItem", argumentSemantic = ArgumentSemantic.RETAIN)
    void setBackBarButtonItem(UIBarButtonItem value);

    @OccInstanceProperty(value = "hidesBackButton", argumentSemantic = ArgumentSemantic.ASSIGN)
    boolean getHidesBackButton();

    @OccInstanceProperty(value = "hidesBackButton", argumentSemantic = ArgumentSemantic.ASSIGN)
    void setHidesBackButton(boolean value);

    @OccInstanceMethod("setHidesBackButton:animated:")
    void setHidesBackButton(boolean hidesBackButton, boolean animated);

    @OccInstanceProperty("leftItemsSupplementBackButton")
    boolean getLeftItemsSupplementBackButton();

    @OccInstanceProperty("leftItemsSupplementBackButton")
    void setLeftItemsSupplementBackButton(boolean value);

    @OccInstanceProperty(value = "titleView", argumentSemantic = ArgumentSemantic.RETAIN)
    UIView getTitleView();

    @OccInstanceProperty(value = "titleView", argumentSemantic = ArgumentSemantic.RETAIN)
    void setTitleView(UIView value);

    @OccInstanceProperty(value = "leftBarButtonItems", argumentSemantic = ArgumentSemantic.COPY)
    List<UIBarButtonItem> getLeftBarButtonItems();

    @OccInstanceProperty(value = "leftBarButtonItems", argumentSemantic = ArgumentSemantic.COPY)
    void setLeftBarButtonItems(List<UIBarButtonItem> value);

    @OccInstanceProperty(value = "leftBarButtonItem", argumentSemantic = ArgumentSemantic.RETAIN)
    UIBarButtonItem getLeftBarButtonItem();

    @OccInstanceProperty(value = "leftBarButtonItem", argumentSemantic = ArgumentSemantic.RETAIN)
    void setLeftBarButtonItem(UIBarButtonItem value);

    @OccInstanceProperty(value = "rightBarButtonItems", argumentSemantic = ArgumentSemantic.COPY)
    List<UIBarButtonItem> getRightBarButtonItems();

    @OccInstanceProperty(value = "rightBarButtonItems", argumentSemantic = ArgumentSemantic.COPY)
    void setRightBarButtonItems(List<UIBarButtonItem> value);

    @OccInstanceProperty(value = "rightBarButtonItem", argumentSemantic = ArgumentSemantic.RETAIN)
    UIBarButtonItem getRightBarButtonItem();

    @OccInstanceProperty(value = "rightBarButtonItem", argumentSemantic = ArgumentSemantic.RETAIN)
    void setRightBarButtonItem(UIBarButtonItem value);

    @OccInstanceMethod("setLeftBarButtonItems:animated:")
    void setLeftBarButtonItems(List<UIBarButtonItem> items, boolean animated);

    @OccInstanceMethod("setLeftBarButtonItem:animated:")
    void setLeftBarButtonItem(UIBarButtonItem item, boolean animated);

    @OccInstanceMethod("setRightBarButtonItems:animated:")
    void setRightBarButtonItems(List<UIBarButtonItem> items, boolean animated);

    @OccInstanceMethod("setRightBarButtonItem:animated:")
    void setRightBarButtonItem(UIBarButtonItem item, boolean animated);

    @Override
    UINavigationItemFactory getFactory();
}
