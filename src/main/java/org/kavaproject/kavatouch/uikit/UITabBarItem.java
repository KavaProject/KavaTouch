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

@OccClass("UITabBarItem")
public interface UITabBarItem extends UIBarItem {
    @OccInstanceProperty(value = "badgeValue", argumentSemantic = ArgumentSemantic.COPY)
    String getBadgeValue();

    @OccInstanceProperty(value = "badgeValue", argumentSemantic = ArgumentSemantic.COPY)
    void setBadgeValue(String value);

    @OccInstanceMethod("finishedSelectedImage")
    UIImage getFinishedSelectedImage();

    @OccInstanceMethod("finishedUnselectedImage")
    UIImage getFinishedUnselectedImage();

    @OccInstanceMethod("setFinishedSelectedImage:withFinishedUnselectedImage:")
    void setFinishedImage(UIImage selectedImage, UIImage unselectedImage);

    @OccInstanceMethod("titlePositionAdjustment")
    UIOffset getTitlePositionAdjustment();

    @OccInstanceMethod("setTitlePositionAdjustment:")
    void setTitlePositionAdjustment(UIOffset adjustment);

    @Override
    UITabBarItemFactory getFactory();
}
