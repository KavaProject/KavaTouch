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
import org.kavaproject.kavatouch.runtime.SEL;

import java.util.Set;

@OccClass("UIBarButtonItem")
public interface UIBarButtonItem extends UIBarItem {
    @OccInstanceProperty(value = "target", argumentSemantic = ArgumentSemantic.ASSIGN)
    Object getTarget();

    @OccInstanceProperty(value = "target", argumentSemantic = ArgumentSemantic.ASSIGN)
    void setTarget(Object value);

    @OccInstanceProperty("action")
    SEL getAction();

    @OccInstanceProperty("action")
    void setAction(SEL value);

    @OccInstanceProperty("style")
    UIBarButtonItemStyle getStyle();

    @OccInstanceProperty("style")
    void setStyle(UIBarButtonItemStyle value);

    @OccInstanceProperty(value = "possibleTitles", argumentSemantic = ArgumentSemantic.COPY)
    Set<String> getPossibleTitles();

    @OccInstanceProperty(value = "possibleTitles", argumentSemantic = ArgumentSemantic.COPY)
    void setPossibleTitles(Set<String> value);

    @OccInstanceProperty("width")
    float getWidth();

    @OccInstanceProperty("width")
    void setWidth(float value);

    @OccInstanceProperty(value = "customView", argumentSemantic = ArgumentSemantic.RETAIN)
    UIView getCustomView();

    @OccInstanceProperty(value = "customView", argumentSemantic = ArgumentSemantic.RETAIN)
    void setCustomView(UIView value);

    @OccInstanceProperty(value = "tintColor", argumentSemantic = ArgumentSemantic.RETAIN)
    UIColor getTintColor();

    @OccInstanceProperty(value = "tintColor", argumentSemantic = ArgumentSemantic.RETAIN)
    void setTintColor(UIColor value);

    @OccInstanceMethod("backButtonBackgroundImageForState:barMetrics:")
    UIImage getBackButtonBackgroundImage(UIControlState state, UIBarMetrics metrics);

    @OccInstanceMethod("setBackButtonBackgroundImage:forState:barMetrics:")
    void setBackButtonBackgroundImage(UIImage image, UIControlState state, UIBarMetrics metrics);

    @OccInstanceMethod("backButtonTitlePositionAdjustmentForBarMetrics:")
    UIOffset getBackButtonTitlePositionAdjustment(UIBarMetrics metrics);

    @OccInstanceMethod("setBackButtonTitlePositionAdjustment:forBarMetrics:")
    void setBackButtonTitlePositionAdjustment(UIOffset adjustment, UIBarMetrics metrics);

    @OccInstanceMethod("backButtonBackgroundVerticalPositionAdjustmentForBarMetrics:")
    float getBackButtonBackgroundVerticalPositionAdjustment(UIBarMetrics metrics);

    @OccInstanceMethod("setBackButtonBackgroundVerticalPositionAdjustment:forBarMetrics:")
    void setBackButtonBackgroundVerticalPositionAdjustment(float adjustment, UIBarMetrics metrics);

    @OccInstanceMethod("backgroundVerticalPositionAdjustmentForBarMetrics:")
    float getBackgroundVerticalPositionAdjustment(UIBarMetrics metrics);

    @OccInstanceMethod("setBackgroundVerticalPositionAdjustment:forBarMetrics:")
    void setBackgroundVerticalPositionAdjustment(float adjustment, UIBarMetrics metrics);

    @OccInstanceMethod("backgroundImageForState:barMetrics:")
    UIImage getBackgroundImage(UIControlState state, UIBarMetrics metrics);

    @OccInstanceMethod("setBackgroundImage:forState:barMetrics:")
    void setBackgroundImage(UIImage backgroundImage, UIControlState state, UIBarMetrics metrics);

    @OccInstanceMethod("backgroundImageForState:style:barMetrics:")
    UIImage getBackgroundImage(UIControlState state, UIBarButtonItemStyle style, UIBarMetrics metrics);

    @OccInstanceMethod("setBackgroundImage:forState:style:barMetrics:")
    void setBackgroundImage(UIImage image, UIControlState state, UIBarButtonItemStyle style, UIBarMetrics metrics);

    @OccInstanceMethod("titlePositionAdjustmentForBarMetrics:")
    UIOffset getTitlePositionAdjustment(UIBarMetrics metrics);

    @OccInstanceMethod("setTitlePositionAdjustment:forBarMetrics:")
    void setTitlePositionAdjustment(UIOffset adjustment, UIBarMetrics metrics);

    @Override
    UIBarButtonItemFactory getFactory();
}
