/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.uikit;

import org.kavaproject.kavatouch.internal.CEnumConstant;

public enum UIEventSubtype {
    @CEnumConstant(value = "UIEventSubtypeNone", constantValue = 0)
    NONE(0),
    @CEnumConstant(value = "UIEventSubtypeMotionShake", constantValue = 1)
    MOTION_SHAKE(1),
    @CEnumConstant(value = "UIEventSubtypeRemoteControlPlay", constantValue = 100)
    REMOTE_CONTROL_PLAY(100),
    @CEnumConstant(value = "UIEventSubtypeRemoteControlPause", constantValue = 101)
    REMOTE_CONTROL_PAUSE(101),
    @CEnumConstant(value = "UIEventSubtypeRemoteControlStop", constantValue = 102)
    REMOTE_CONTROL_STOP(102),
    @CEnumConstant(value = "UIEventSubtypeRemoteControlTogglePlayPause", constantValue = 103)
    REMOTE_CONTROL_TOGGLE_PLAY_PAUSE(103),
    @CEnumConstant(value = "UIEventSubtypeRemoteControlNextTrack", constantValue = 104)
    REMOTE_CONTROL_NEXT_TRACK(104),
    @CEnumConstant(value = "UIEventSubtypeRemoteControlPreviousTrack", constantValue = 105)
    REMOTE_CONTROL_PREVIOUS_TRACK(105),
    @CEnumConstant(value = "UIEventSubtypeRemoteControlBeginSeekingBackward", constantValue = 106)
    REMOTE_CONTROL_BEGIN_SEEKING_BACKWARD(106),
    @CEnumConstant(value = "UIEventSubtypeRemoteControlEndSeekingBackward", constantValue = 107)
    REMOTE_CONTROL_END_SEEKING_BACKWARD(107),
    @CEnumConstant(value = "UIEventSubtypeRemoteControlBeginSeekingForward", constantValue = 108)
    REMOTE_CONTROL_BEGIN_SEEKING_FORWARD(108),
    @CEnumConstant(value = "UIEventSubtypeRemoteControlEndSeekingForward", constantValue = 109)
    REMOTE_CONTROL_END_SEEKING_FORWARD(109);
    public final int value;

    UIEventSubtype(int value) {
        this.value = value;
    }
}
