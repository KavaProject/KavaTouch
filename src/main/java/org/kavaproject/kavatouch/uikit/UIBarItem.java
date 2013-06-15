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
import org.kavaproject.kavatouch.runtime.Creatable;

import java.util.Map;

@OccClass("UIBarItem")
public interface UIBarItem extends Creatable {
    @OccInstanceProperty("enabled")
    boolean isEnabled();

    @OccInstanceProperty("enabled")
    void setEnabled(boolean value);

    @OccInstanceProperty(value = "image", argumentSemantic = ArgumentSemantic.RETAIN)
    UIImage getImage();

    @OccInstanceProperty(value = "image", argumentSemantic = ArgumentSemantic.RETAIN)
    void setImage(UIImage value);

    @OccInstanceProperty(value = "landscapeImagePhone", argumentSemantic = ArgumentSemantic.RETAIN)
    UIImage getLandscapeImagePhone();

    @OccInstanceProperty(value = "landscapeImagePhone", argumentSemantic = ArgumentSemantic.RETAIN)
    void setLandscapeImagePhone(UIImage value);

    @OccInstanceProperty("imageInsets")
    UIEdgeInsets getImageInsets();

    @OccInstanceProperty("imageInsets")
    void setImageInsets(UIEdgeInsets value);

    @OccInstanceProperty("landscapeImagePhoneInsets")
    UIEdgeInsets getLandscapeImagePhoneInsets();

    @OccInstanceProperty("landscapeImagePhoneInsets")
    void setLandscapeImagePhoneInsets(UIEdgeInsets value);

    @OccInstanceProperty(value = "title", argumentSemantic = ArgumentSemantic.COPY)
    String getTitle();

    @OccInstanceProperty(value = "title", argumentSemantic = ArgumentSemantic.COPY)
    void setTitle(String value);

    @OccInstanceProperty("tag")
    int getTag();

    @OccInstanceProperty("tag")
    void setTag(int value);

    @OccInstanceMethod("setTitleTextAttributes:forState:")
    void setTitleTextAttributes(Map<String, Object> attributes, UIControlState state);

    @OccInstanceMethod("titleTextAttributesForState:")
    Map<String, Object> getTitleTextAttributes(UIControlState state);

    @Override
    UIBarItemFactory getFactory();
}
