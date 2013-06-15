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

@OccClass("UIToolbar")
public interface UIToolbar extends UIView {
    @OccInstanceProperty("barStyle")
    UIBarStyle getBarStyle();

    @OccInstanceProperty("barStyle")
    void setBarStyle(UIBarStyle style);

    @OccInstanceProperty(value = "translucent", argumentSemantic = ArgumentSemantic.ASSIGN)
    boolean isTranslucent();

    @OccInstanceProperty(value = "translucent", argumentSemantic = ArgumentSemantic.ASSIGN)
    void setTranslucent(boolean value);

    @OccInstanceProperty(value = "items", argumentSemantic = ArgumentSemantic.COPY)
    List<UIBarButtonItem> getItems();

    @OccInstanceProperty(value = "items", argumentSemantic = ArgumentSemantic.COPY)
    void setItems(List<UIBarButtonItem> value);

    @OccInstanceMethod("setItems:animated:")
    void setItems(List<UIBarButtonItem> items, boolean animated);

    @OccInstanceMethod("backgroundImageForToolbarPosition:barMetrics:")
    UIImage getBackgroundImage(UIToolbarPosition position, UIBarMetrics metrics);

    @OccInstanceMethod("setBackgroundImage:forToolbarPosition:barMetrics:")
    void setBackgroundImage(UIImage image, UIToolbarPosition position, UIBarMetrics metrics);

    @OccInstanceMethod("shadowImageForToolbarPosition:")
    UIImage getShadowImage(UIToolbarPosition position);

    @OccInstanceMethod("setShadowImage:forToolbarPosition:")
    void setShadowImage(UIImage image, UIToolbarPosition position);

    @OccInstanceProperty(value = "tintColor", argumentSemantic = ArgumentSemantic.RETAIN)
    UIColor getTintColor();

    @OccInstanceProperty(value = "tintColor", argumentSemantic = ArgumentSemantic.RETAIN)
    void setTintColor(UIColor value);

    @Override
    UIToolbarFactory getFactory();
}
