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
import org.kavaproject.kavatouch.foundation.Coder;
import org.kavaproject.kavatouch.uikit.UIColorFactory;
import org.kavaproject.kavatouch.util.NotImplementedException;

import javax.inject.Inject;

public class GraphicsAnimationLayerFactory implements AnimationLayerFactory {
    private final UIColorFactory mUIColorFactory;
    private final DefaultLayoutManagerFactory mDefaultLayoutManagerFactory = new DefaultLayoutManagerFactory();

    @Inject
    protected GraphicsAnimationLayerFactory(UIColorFactory uiColorFactory) {
        this.mUIColorFactory = uiColorFactory;
    }

    @Override
    public AnimationLayer create() {
        return new GraphicsAnimationLayer(this, mDefaultLayoutManagerFactory, mUIColorFactory);
    }

    @Override
    public boolean needsDisplay(String key) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public AnimationAction defaultAction(String key) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public Object defaultValue(String key) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public AnimationLayer create(Coder decoder) {
        return new GraphicsAnimationLayer(decoder, this, mDefaultLayoutManagerFactory, mUIColorFactory);
    }
}
