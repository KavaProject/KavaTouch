/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.uikit;

import org.kavaproject.kavatouch.uikit.staging.UIDeviceBatteryState;
import org.kavaproject.kavatouch.uikit.staging.UIUserInterfaceIdiom;
import org.kavaproject.kavatouch.util.NotImplementedException;

import java.util.UUID;

public class DefaultUIDevice implements UIDevice {
    private final UIDeviceFactory mUIDeviceFactory;
    private boolean mProximityMonitoringEnabled = false;

    protected DefaultUIDevice(UIDeviceFactory uiDeviceFactory) {
        mUIDeviceFactory = uiDeviceFactory;
    }

    @Override
    public boolean isMultitaskingSupported() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public String getName() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public String getSystemName() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public String getSystemVersion() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public String getModel() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public String getLocalizedModel() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public UIUserInterfaceIdiom getUserInterfaceIdiom() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public UUID getIdentifierForVendor() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public String getUniqueIdentifier() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public UIDeviceOrientation getOrientation() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public boolean isGeneratingDeviceOrientationNotifications() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void beginGeneratingDeviceOrientationNotifications() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void endGeneratingDeviceOrientationNotifications() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public float getBatteryLevel() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public boolean isBatteryMonitoringEnabled() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public UIDeviceBatteryState getBatteryState() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public boolean isProximityMonitoringEnabled() {
        return mProximityMonitoringEnabled;
    }

    @Override
    public void setProximityMonitoringEnabled(boolean value) {
        mProximityMonitoringEnabled = value;
    }

    @Override
    public boolean getProximityState() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void playInputClick() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public UIDeviceFactory getFactory() {
        return mUIDeviceFactory;
    }
}
