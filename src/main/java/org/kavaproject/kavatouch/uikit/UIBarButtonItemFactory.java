/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.uikit;

import org.kavaproject.kavatouch.internal.OccClass;
import org.kavaproject.kavatouch.internal.OccInstanceMethod;
import org.kavaproject.kavatouch.runtime.SEL;

@OccClass("UIBarButtonItem")
public interface UIBarButtonItemFactory extends UIBarItemFactory {
    @OccInstanceMethod("initWithBarButtonSystemItem:target:action:")
    UIBarButtonItem create(UIBarButtonSystemItem systemItem, Object target, SEL action);

    @OccInstanceMethod("initWithCustomView:")
    UIBarButtonItem create(UIView customView);

    @OccInstanceMethod("initWithImage:style:target:action:")
    UIBarButtonItem create(UIImage image, UIBarButtonItemStyle style, Object target, SEL action);

    @OccInstanceMethod("initWithTitle:style:target:action:")
    UIBarButtonItem create(String title, UIBarButtonItemStyle style, Object target, SEL action);

    @OccInstanceMethod("initWithImage:landscapeImagePhone:style:target:action:")
    UIBarButtonItemFactory create(UIImage image, UIImage landscapeImagePhone, UIBarButtonItemStyle style,
                                  Object target, SEL action);

    @Override
    UIBarButtonItem appearance();

    @Override
    UIBarButtonItem appearance(UIAppearanceContainerFactory... containers);
}
