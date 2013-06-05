/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.coreanimation;

import org.kavaproject.kavatouch.coreanimation.staging.AnimationAction;
import org.kavaproject.kavatouch.foundation.CodingFactory;
import org.kavaproject.kavatouch.internal.Header;
import org.kavaproject.kavatouch.internal.OccClass;
import org.kavaproject.kavatouch.internal.OccClassMethod;
import org.kavaproject.kavatouch.internal.OccInstanceMethod;
import org.kavaproject.kavatouch.runtime.IMP1;
import org.kavaproject.kavatouch.runtime.SEL;

@Header("CALayer")
@OccClass("CALayer")
public interface AnimationLayerFactory extends CodingFactory {
    SEL SEL_SET_CONTENTS_SCALE = SEL.getInstance("setContentsScale:");
    IMP1 IMP_SET_CONTENTS_SCALE = new IMP1<AnimationLayer, Float>() {
        @Override
        public void invoke(AnimationLayer receiver, SEL sel, Float aFloat) {
            receiver.setContentsScale(aFloat);
        }
    };

    @OccInstanceMethod("init")
    AnimationLayer create();

    @OccClassMethod("needsDisplayForKey:")
    boolean needsDisplay(String key);

    @OccClassMethod("defaultActionForKey:")
    AnimationAction defaultAction(String key);

    @OccClassMethod("defaultValueForKey:")
    Object defaultValue(String key);
}
