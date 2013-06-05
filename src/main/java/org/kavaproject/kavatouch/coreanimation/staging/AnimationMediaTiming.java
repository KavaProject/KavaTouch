/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.coreanimation.staging;

import org.kavaproject.kavatouch.internal.Header;
import org.kavaproject.kavatouch.internal.OccConstant;
import org.kavaproject.kavatouch.internal.OccProtocol;
import org.kavaproject.kavatouch.internal.OccProtocolInstanceProperty;

@Header("CAMediaTiming")
@OccProtocol("CAMediaTiming")
public interface AnimationMediaTiming {
    @OccConstant("kCAFillModeRemoved")
    public static final String FILL_MODE_REMOVED = "kCAFillModeRemoved";
    @OccConstant("kCAFillModeForwards")
    public static final String FILL_MODE_FORWARDS = "kCAFillModeForwards";
    @OccConstant("kCAFillModeBackwards")
    public static final String FILL_MODE_BACKWARDS = "kCAFillModeBackwards";
    @OccConstant("kCAFillModeBoth")
    public static final String FILL_MODE_BOTH = "kCAFillModeBoth";
    @OccConstant("kCAFillModeFrozen")
    public static final String FILL_MODE_FROZEN = "kCAFillModeFrozen";

    @OccProtocolInstanceProperty("beginTime")
    double getBeginTime();

    @OccProtocolInstanceProperty("beginTime")
    void setBeginTime(double value);

    @OccProtocolInstanceProperty("timeOffset")
    double getTimeOffset();

    @OccProtocolInstanceProperty("timeOffset")
    void setTimeOffset(double value);

    @OccProtocolInstanceProperty("repeatCount")
    float getRepeatCount();

    @OccProtocolInstanceProperty("repeatCount")
    void setRepeatCount(float value);

    @OccProtocolInstanceProperty("repeatDuration")
    double getRepeatDuration();

    @OccProtocolInstanceProperty("repeatDuration")
    void setRepeatDuration(double value);

    @OccProtocolInstanceProperty("duration")
    double getDuration();

    @OccProtocolInstanceProperty("duration")
    void setDuration(double value);

    @OccProtocolInstanceProperty("speed")
    float getSpeed();

    @OccProtocolInstanceProperty("speed")
    void setSpeed(float value);

    @OccProtocolInstanceProperty("autoreverses")
    boolean getAutoreverses();

    @OccProtocolInstanceProperty("autoreverses")
    void setAutoreverses(boolean value);

    @OccProtocolInstanceProperty("fillMode")
    String getFillMode();

    @OccProtocolInstanceProperty("fillMode")
    void setFillMode(String value);
}
