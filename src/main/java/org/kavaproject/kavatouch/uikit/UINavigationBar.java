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
import org.kavaproject.kavatouch.internal.ArgumentSemantic;
import org.kavaproject.kavatouch.internal.OccClass;
import org.kavaproject.kavatouch.internal.OccInstanceMethod;
import org.kavaproject.kavatouch.internal.OccInstanceProperty;

import java.util.List;
import java.util.Map;

@OccClass("UINavigationBar")
public interface UINavigationBar extends UIView, Coding {
    @OccInstanceProperty(value = "barStyle", argumentSemantic = ArgumentSemantic.ASSIGN)
    UIBarStyle getBarStyle();

    @OccInstanceProperty(value = "barStyle", argumentSemantic = ArgumentSemantic.ASSIGN)
    void setBarStyle(UIBarStyle value);

    @OccInstanceProperty(value = "shadowImage", argumentSemantic = ArgumentSemantic.RETAIN)
    UIImage getShadowImage();

    @OccInstanceProperty(value = "shadowImage", argumentSemantic = ArgumentSemantic.RETAIN)
    void setShadowImage(UIImage value);

    @OccInstanceProperty(value = "translucent", argumentSemantic = ArgumentSemantic.ASSIGN)
    boolean isTranslucent();

    @OccInstanceProperty(value = "translucent", argumentSemantic = ArgumentSemantic.ASSIGN)
    void setTranslucent(boolean value);

    @OccInstanceProperty(value = "delegate", argumentSemantic = ArgumentSemantic.ASSIGN)
    UINavigationBarDelegate getDelegate();

    @OccInstanceProperty(value = "delegate", argumentSemantic = ArgumentSemantic.ASSIGN)
    void setDelegate(UINavigationBarDelegate value);

    @OccInstanceMethod("pushNavigationItem:animated:")
    void pushNavigationItem(UINavigationItem item, boolean animated);

    @OccInstanceMethod("popNavigationItemAnimated:")
    UINavigationItem popNavigationItem(boolean animated);

    @OccInstanceMethod("setItems:animated:")
    void setItems(List<UINavigationItem> items, boolean animated);

    @OccInstanceProperty(value = "items", argumentSemantic = ArgumentSemantic.COPY)
    List<UINavigationItem> getItems();

    @OccInstanceProperty(value = "items", argumentSemantic = ArgumentSemantic.COPY)
    void setItems(List<UINavigationItem> value);

    @OccInstanceProperty(value = "topItem", argumentSemantic = ArgumentSemantic.RETAIN)
    UINavigationItem getTopItem();

    @OccInstanceProperty(value = "backItem", argumentSemantic = ArgumentSemantic.RETAIN)
    UINavigationItem getBackItem();

    @OccInstanceProperty(value = "tintColor", argumentSemantic = ArgumentSemantic.RETAIN)
    UIColor getTintColor();

    @OccInstanceProperty(value = "tintColor", argumentSemantic = ArgumentSemantic.RETAIN)
    void setTintColor(UIColor value);

    @OccInstanceMethod("backgroundImageForBarMetrics:")
    UIImage getBackgroundImage(UIBarMetrics metrics);

    @OccInstanceMethod("setBackgroundImage:forBarMetrics:")
    void setBackgroundImage(UIImage backgroundImage, UIBarMetrics barMetrics);

    @OccInstanceMethod("titleVerticalPositionAdjustmentForBarMetrics:")
    float getTitleVerticalPositionAdjustment(UIBarMetrics barMetrics);

    @OccInstanceMethod("setTitleVerticalPositionAdjustment:forBarMetrics:")
    void setTitleVerticalPositionAdjustment(float adjustment, UIBarMetrics barMetrics);

    @OccInstanceProperty(value = "titleTextAttributes", argumentSemantic = ArgumentSemantic.COPY)
    Map<String, Object> getTitleTextAttributes();

    @OccInstanceProperty(value = "titleTextAttributes", argumentSemantic = ArgumentSemantic.COPY)
    void setTitleTextAttributes(Map<String, Object> value);

    @Override
    UINavigationBarFactory getFactory();
}
