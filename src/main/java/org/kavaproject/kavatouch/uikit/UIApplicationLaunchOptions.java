/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.uikit;

import org.kavaproject.kavatouch.corefoundation.CorePropertyList;
import org.kavaproject.kavatouch.foundation.URL;
import org.kavaproject.kavatouch.internal.OccConstant;
import org.kavaproject.kavatouch.uikit.staging.UILocalNotification;

import java.util.List;
import java.util.Map;

public class UIApplicationLaunchOptions {
    @OccConstant("UIApplicationLaunchOptionsURLKey")
    public URL url;
    @OccConstant("UIApplicationLaunchOptionsSourceApplicationKey")
    public String sourceApplication;
    @OccConstant("UIApplicationLaunchOptionsRemoteNotificationKey")
    public Map remoteNotification;
    @OccConstant("UIApplicationLaunchOptionsAnnotationKey")
    public CorePropertyList annotationKey;
    @OccConstant("UIApplicationLaunchOptionsLocalNotificationKey")
    public UILocalNotification localNotification;
    @OccConstant("UIApplicationLaunchOptionsLocationKey")
    public Boolean location;
    @OccConstant("UIApplicationLaunchOptionsNewsstandDownloadsKey")
    public List<String> newsstandDownloads;
}
