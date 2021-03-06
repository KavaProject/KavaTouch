/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.coreanimation;

import org.kavaproject.kavatouch.internal.Module;
import org.kavaproject.kavatouch.util.inject.Provides;
import org.kavaproject.kavatouch.uikit.UIColorFactory;

import org.kavaproject.kavatouch.util.inject.Singleton;

@Module
public class CoreAnimationModule {
    @Provides
    @Singleton
    public AnimationLayerFactory provideAnimationLayerFactory(UIColorFactory uiColorFactory) {
        return new GraphicsAnimationLayerFactory(uiColorFactory);
    }
}
