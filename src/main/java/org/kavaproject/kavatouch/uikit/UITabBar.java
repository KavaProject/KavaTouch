/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.uikit;

import org.kavaproject.kavatouch.internal.ArgumentSemantic;
import org.kavaproject.kavatouch.internal.OccClass;
import org.kavaproject.kavatouch.internal.OccInstanceMethod;
import org.kavaproject.kavatouch.internal.OccInstanceProperty;

import java.util.List;

@OccClass("UITabBar")
public interface UITabBar extends UIView {
    @OccInstanceProperty(value = "delegate", argumentSemantic = ArgumentSemantic.ASSIGN)
    UITabBarDelegate getDelegate();

    @OccInstanceProperty(value = "delegate", argumentSemantic = ArgumentSemantic.ASSIGN)
    void setDelegate(UITabBarDelegate value);

    @OccInstanceProperty(value = "items", argumentSemantic = ArgumentSemantic.COPY)
    List<UITabBarItem> getItems();

    @OccInstanceProperty(value = "items", argumentSemantic = ArgumentSemantic.COPY)
    void setItems(List<UITabBarItem> value);

    @OccInstanceProperty(value = "selectedItem", argumentSemantic = ArgumentSemantic.ASSIGN)
    UITabBarItem getSelectedItem();

    @OccInstanceProperty(value = "selectedItem", argumentSemantic = ArgumentSemantic.ASSIGN)
    void setSelectedItem(UITabBarItem value);

    @OccInstanceMethod("setItems:animated:")
    void setItems(List<UITabBarItem> items, boolean animated);

    @OccInstanceMethod("beginCustomizingItems:")
    void beginCustomizingItems(List<UITabBarItem> items);

    @OccInstanceMethod("endCustomizingAnimated:")
    boolean endCustomizing(boolean animated);

    @OccInstanceMethod("isCustomizing")
    boolean isCustomizing();

    @OccInstanceProperty(value = "backgroundImage", argumentSemantic = ArgumentSemantic.RETAIN)
    UIImage getBackgroundImage();

    @OccInstanceProperty(value = "backgroundImage", argumentSemantic = ArgumentSemantic.RETAIN)
    void setBackgroundImage(UIImage value);

    @OccInstanceProperty(value = "selectedImageTintColor", argumentSemantic = ArgumentSemantic.RETAIN)
    UIColor getSelectedImageTintColor();

    @OccInstanceProperty(value = "selectedImageTintColor", argumentSemantic = ArgumentSemantic.RETAIN)
    void setSelectedImageTintColor(UIColor value);

    @OccInstanceProperty(value = "selectionIndicatorImage", argumentSemantic = ArgumentSemantic.RETAIN)
    UIImage getSelectionIndicatorImage();

    @OccInstanceProperty(value = "selectionIndicatorImage", argumentSemantic = ArgumentSemantic.RETAIN)
    void setSelectionIndicatorImage(UIImage value);

    @OccInstanceProperty(value = "shadowImage", argumentSemantic = ArgumentSemantic.RETAIN)
    UIImage getShadowImage();

    @OccInstanceProperty(value = "shadowImage", argumentSemantic = ArgumentSemantic.RETAIN)
    void setShadowImage(UIImage value);

    @OccInstanceProperty(value = "tintColor", argumentSemantic = ArgumentSemantic.RETAIN)
    UIColor getTintColor();

    @OccInstanceProperty(value = "tintColor", argumentSemantic = ArgumentSemantic.RETAIN)
    void setTintColor(UIColor value);

    @Override
    UITabBarFactory getFactory();
}
