/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.coreanimation;

import org.kavaproject.kavatouch.coregraphics.GraphicsAffineTransform;
import org.kavaproject.kavatouch.coregraphics.GraphicsPoint;

import java.util.ArrayList;
import java.util.List;

final class TransformMath {
    //TODO Incorporate layer transforms
    public static final GraphicsAffineTransform createTransform(AnimationLayer srcLayer, AnimationLayer dstLayer) {
        List<AnimationLayer> rootPath = pathToRootLayer(dstLayer);
        GraphicsAffineTransform t = GraphicsAffineTransform.IDENTITY();
        AnimationLayer sharedLayer = srcLayer;
        //Climb up own superlayer branch
        while (sharedLayer != null) {
            if (rootPath.contains(sharedLayer)) {
                break;
            }
            GraphicsPoint origin = sharedLayer.getFrame().origin;
            t = t.translate(origin.x, origin.y);
            sharedLayer = sharedLayer.getSuperlayer();
        }
        if (sharedLayer == null) {
            throw new IllegalArgumentException(); //No common superlayer
        }
        //Climb down target sublayer branch
        for (int i = rootPath.indexOf(sharedLayer) - 1; i >= 0; i--) {
            AnimationLayer nextSublayer = rootPath.get(i);
            GraphicsPoint origin = nextSublayer.getFrame().origin;
            t = t.translate(-origin.x, -origin.y);
        }
        return t;
    }

    private static final List<AnimationLayer> pathToRootLayer(AnimationLayer layer) {
        ArrayList<AnimationLayer> path = new ArrayList<AnimationLayer>();
        path.add(layer);
        AnimationLayer nextSuperlayer = layer.getSuperlayer();
        while (nextSuperlayer != null) {
            path.add(nextSuperlayer);
            nextSuperlayer = nextSuperlayer.getSuperlayer();
        }
        return path;
    }
}
