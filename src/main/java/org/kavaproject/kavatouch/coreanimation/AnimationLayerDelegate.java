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
import org.kavaproject.kavatouch.coregraphics.GraphicsContext;
import org.kavaproject.kavatouch.internal.Header;
import org.kavaproject.kavatouch.internal.OccProtocol;
import org.kavaproject.kavatouch.internal.OccProtocolInstanceMethod;
import org.kavaproject.kavatouch.runtime.Creatable;

@Header("CALayer")
@OccProtocol("CALayerDelegate")
public interface AnimationLayerDelegate extends Creatable {
    public static final String SEL_DISPLAY_LAYER = "displayLayer:";
    public static final String SEL_LAYOUT_SUBLAYERS_OF_LAYER = "layoutSublayersOfLayer:";
    public static final String SEL_DRAW_LAYER_IN_CONTEXT = "drawLayer:InContext:";

    @OccProtocolInstanceMethod("displayLayer:")
    void displayLayer(AnimationLayer layer);

    @OccProtocolInstanceMethod("drawLayer:inContext:")
    void drawLayer(AnimationLayer layer, GraphicsContext ctx);

    @OccProtocolInstanceMethod("actionForLayer:forKey:")
    AnimationAction actionForLayer(AnimationLayer layer, String key);

    public interface SublayerLayouting {
        @OccProtocolInstanceMethod("layoutSublayersOfLayer:")
        void layoutSublayersOfLayer(AnimationLayer layer);
    }
}
