/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.uikit;

import org.kavaproject.kavatouch.coregraphics.GraphicsRect;
import org.kavaproject.kavatouch.foundation.NotificationUserInfo;
import org.kavaproject.kavatouch.internal.Header;
import org.kavaproject.kavatouch.internal.OccConstant;

@Header("UIApplication")
public class UIApplicationUserInfo extends NotificationUserInfo {
    @OccConstant("UIApplicationStatusBarOrientationUserInfoKey")
    public UIInterfaceOrientation statusBarOrientation;
    @OccConstant("UIApplicationStatusBarFrameUserInfoKey")
    public GraphicsRect statusBarFrame;

    public UIApplicationUserInfo(UIApplicationUserInfo template) {
        super(template);
        statusBarOrientation = template.statusBarOrientation;
        statusBarFrame = template.statusBarFrame;
    }

    public UIApplicationUserInfo() {
    }

}
