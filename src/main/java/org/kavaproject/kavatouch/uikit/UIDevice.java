/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.uikit;

import org.kavaproject.kavatouch.internal.*;
import org.kavaproject.kavatouch.runtime.Creatable;
import org.kavaproject.kavatouch.uikit.staging.UIDeviceBatteryState;
import org.kavaproject.kavatouch.uikit.staging.UIUserInterfaceIdiom;

import java.util.UUID;

@Header("UIDevice")
@OccClass("UIDevice")
public interface UIDevice extends Creatable {
    @OccConstant("UIDeviceBatteryLevelDidChangeNotification")
    String NOTIFICATION_BATTERY_LEVEL_DID_CHANGE = "UIDeviceBatteryLevelDidChangeNotification";
    @OccConstant("UIDeviceBatteryStateDidChangeNotification")
    String NOTIFICATION_BATTERY_STATE_DID_CHANGE = "UIDeviceBatteryStateDidChangeNotification";
    @OccConstant("UIDeviceOrientationDidChangeNotification")
    String NOTIFICATION_ORIENTATION_DID_CHANGE = "UIDeviceOrientationDidChangeNotification";
    @OccConstant("UIDeviceProximityStateDidChangeNotification")
    String NOTIFICATION_PROXIMITY_STATE_DID_CHANGE = "UIDeviceProximityStateDidChangeNotification";

    @OccInstanceProperty("multitaskingSupported")
    boolean isMultitaskingSupported();

    @OccInstanceProperty("name")
    String getName();

    @OccInstanceProperty("systemName")
    String getSystemName();

    @OccInstanceProperty("systemVersion")
    String getSystemVersion();

    @OccInstanceProperty("model")
    String getModel();

    @OccInstanceProperty("localizedModel")
    String getLocalizedModel();

    @OccInstanceProperty(value = "userInterfaceIdiom", argumentSemantic = ArgumentSemantic.RETAIN)
    UIUserInterfaceIdiom getUserInterfaceIdiom();

    @OccInstanceProperty("identifierForVendor")
    UUID getIdentifierForVendor();

    @OccInstanceProperty("uniqueIdentifier")
    @Deprecated
    String getUniqueIdentifier();

    @OccInstanceProperty("orientation")
    UIDeviceOrientation getOrientation();

    @OccInstanceProperty("generatesDeviceOrientationNotifications")
    boolean isGeneratingDeviceOrientationNotifications();

    @OccInstanceMethod("beginGeneratingDeviceOrientationNotifications")
    void beginGeneratingDeviceOrientationNotifications();

    @OccInstanceMethod("endGeneratingDeviceOrientationNotifications")
    void endGeneratingDeviceOrientationNotifications();

    @OccInstanceProperty("batteryLevel")
    float getBatteryLevel();

    @OccInstanceProperty("batteryMonitoringEnabled")
    boolean isBatteryMonitoringEnabled();

    @OccInstanceProperty("batteryState")
    UIDeviceBatteryState getBatteryState();

    @OccInstanceProperty("proximityMonitoringEnabled")
    boolean isProximityMonitoringEnabled();

    @OccInstanceProperty("proximityMonitoringEnabled")
    void setProximityMonitoringEnabled(boolean value);

    @OccInstanceProperty("proximityState")
    boolean getProximityState();

    @OccInstanceMethod("playInputClick")
    void playInputClick();

    @Override
    UIDeviceFactory getFactory();
}
