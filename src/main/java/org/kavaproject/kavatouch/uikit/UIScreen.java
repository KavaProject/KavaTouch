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
import org.kavaproject.kavatouch.internal.*;
import org.kavaproject.kavatouch.runtime.Creatable;
import org.kavaproject.kavatouch.runtime.SEL;
import org.kavaproject.kavatouch.uikit.staging.AnimationDisplayLink;

import java.util.List;

@Header("UIScreen")
@OccClass("UIScreen")
public interface UIScreen extends Creatable {
    @OccConstant("UIScreenModeDidChangeNotification")
    String NOTIFICATION_MODE_DID_CHANGE = "UIScreenModeDidChangeNotification";
    @OccConstant("UIScreenDidConnectNotification")
    String NOTIFICATION_DID_CONNECT = "UIScreenDidConnectNotification";
    @OccConstant("UIScreenDidDisconnectNotification")
    String NOTIFICATION_DID_DISCONNECT = "UIScreenDidDisconnectNotification";
    @OccConstant("UIScreenBrightnessDidChangeNotification")
    String NOTIFICATION_BRIGHTNESS_DID_CHANGE = "UIScreenBrightnessDidChangeNotification";

    @OccInstanceProperty("mirroredScreen")
    UIScreen getMirroredScreen();

    @OccInstanceProperty("applicationFrame")
    GraphicsRect getApplicationFrame();

    @OccInstanceProperty("bounds")
    GraphicsRect getBounds();

    @OccInstanceProperty("scale")
    float getScale();

    @OccInstanceProperty("preferredMode")
    UIScreenMode getPreferredMode();

    @OccInstanceProperty(value = "availableModes", argumentSemantic = ArgumentSemantic.COPY)
    List<UIScreenMode> getAvailableModes();

    @OccInstanceMethod("displayLinkWithTarget:selector:")
    AnimationDisplayLink createDisplayLink(Object target, SEL selector);

    @OccInstanceProperty("brightness")
    float getBrightness();

    @OccInstanceProperty("brightness")
    void setBrightness(float value);

    @OccInstanceProperty("wantsSoftwareDimming")
    boolean getWantsSoftwareDimming();

    @OccInstanceProperty("wantsSoftwareDimming")
    void setWantsSoftwareDimming(boolean value);

    @OccInstanceProperty("overscanCompensation")
    UIScreenOverscanCompensation getOverscanCompensation();

    @OccInstanceProperty("overscanCompensation")
    void setOverscanCompensation(UIScreenOverscanCompensation value);

    @OccInstanceProperty("currentMode")
    UIScreenMode getCurrentMode();

    @Override
    UIScreenFactory getFactory();
}
