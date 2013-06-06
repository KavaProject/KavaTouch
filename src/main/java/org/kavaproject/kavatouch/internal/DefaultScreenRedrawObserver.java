/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.internal;

import org.kavaproject.kavatouch.DeviceHandle;
import org.kavaproject.kavatouch.coreanimation.AnimationEngine;
import org.kavaproject.kavatouch.coreanimation.AnimationLayer;
import org.kavaproject.kavatouch.corefoundation.CoreRunLoop;
import org.kavaproject.kavatouch.corefoundation.CoreRunLoopActivity;
import org.kavaproject.kavatouch.corefoundation.CoreRunLoopObserver;
import org.kavaproject.kavatouch.uikit.UIApplication;

import org.kavaproject.kavatouch.util.inject.Inject;
import java.util.List;

public class DefaultScreenRedrawObserver implements ScreenRedrawObserver {
    private final UIApplication mSharedApplication;
    private final AnimationEngine mAnimationEngine;
    private CoreRunLoopObserver mRLObserver;

    @Inject
    protected DefaultScreenRedrawObserver(UIApplication sharedApplication, DeviceHandle deviceHandle) {
        mSharedApplication = sharedApplication;
        mAnimationEngine = deviceHandle.getAnimationEngine();
        mRLObserver = new CoreRunLoopObserver(CoreRunLoopActivity.ALL_ACTIVITIES, true, 0,
                new CoreRunLoopObserver.Handler() {
            @Override
            public void execute(CoreRunLoopObserver observer, CoreRunLoopActivity activity) {
                if (!mAnimationEngine.isWaiting()) {
                    if (activity == CoreRunLoopActivity.BEFORE_WAITING) {
                        //This combination doesn't really happen, so no need for optimization here
                    } else {
                        return;
                    }
                }
                //Update the model layer
                AnimationLayer modelLayer = mSharedApplication.getWindows().get(0).getLayer();
                modelLayer.layoutIfNeeded();
                //Redisplay layers that need it and mark superlayers for screen update
                recurseDisplayLayer(modelLayer);
                //Copy changes to render tree
                mAnimationEngine.commitModelChanges();
            }
        });
    }

    /**
     * Redisplays all changed layers and marks superlayers for rendering.
     */
    private static void recurseDisplayLayer(AnimationLayer layer) {
        layer.displayIfNeeded(); //TODO mark dirty rects
        List<AnimationLayer> sublayers = layer.getSublayers();
        if (sublayers == null) {
            return;
        }
        for (AnimationLayer sublayer : sublayers) {
            recurseDisplayLayer(sublayer);
        }
    }

    @Override
    public void addToCurrentRunLoop() {
        CoreRunLoop cfLoop = CoreRunLoop.getCurrent();
        cfLoop.addObserver(mRLObserver, CoreRunLoop.MODE_DEFAULT);
    }
}
