/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.coreanimation;

import org.kavaproject.kavatouch.coregraphics.GraphicsSize;
import org.kavaproject.kavatouch.internal.OccProtocolInstanceMethod;
import org.kavaproject.kavatouch.runtime.Creatable;
import org.kavaproject.kavatouch.runtime.Factory;

import java.util.List;

class DefaultLayoutManager implements AnimationLayoutManager, Creatable {
    private final DefaultLayoutManagerFactory mDefaultLayoutManagerFactory;

    public DefaultLayoutManager(DefaultLayoutManagerFactory defaultLayoutManagerFactory) {
        this.mDefaultLayoutManagerFactory = defaultLayoutManagerFactory;
    }

    @OccProtocolInstanceMethod(value = "invalidateLayoutOfLayer:")
    void invalidateLayout(AnimationLayer layer) {
        //TODO
    }

    @Override
    public void layoutSublayers(AnimationLayer layer) {
        List<AnimationLayer> sublayers = layer.getSublayers();
        if (sublayers == null) {
            return;
        }
        for (AnimationLayer sublayer : sublayers) {
            sublayer.layoutSublayers();
        }
    }

    @OccProtocolInstanceMethod(value = "preferredSizeOfLayer:")
    GraphicsSize preferredSize(AnimationLayer layer) {
        return layer.getBounds().size;
    }

    @Override
    public Factory getFactory() {
        return mDefaultLayoutManagerFactory;
    }
}
