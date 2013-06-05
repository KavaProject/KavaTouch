/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.coregraphics;

import org.kavaproject.kavatouch.internal.CData;
import org.kavaproject.kavatouch.internal.CFunction;
import org.kavaproject.kavatouch.internal.CTypedef;
import org.kavaproject.kavatouch.internal.Header;
import org.kavaproject.kavatouch.util.FloatUtil;

import java.util.HashMap;
import java.util.Map;

@Header("CGGeometry")
@CTypedef(value = "CGPoint")
public class GraphicsPoint {
    @CData("CGPointZero")
    public static final GraphicsPoint ZERO = new GraphicsPoint(0, 0);
    public final float x, y;

    public GraphicsPoint(GraphicsPoint template) {
        x = template.x;
        y = template.y;
    }

    @CFunction("CGPointMake")
    public GraphicsPoint(float x, float y) {
        this.x = x;
        this.y = y;
    }

    @CFunction("CGPointMakeWithDictionaryRepresentation")
    public static GraphicsPoint makeWithDictionaryRepresentation(Map<String, Float> dict) {
        Float x = dict.get("X");
        Float y = dict.get("Y");
        if (x == null || y == null) {
            return null;
        }
        return new GraphicsPoint(x, y);
    }

    static GraphicsPoint[] copy(GraphicsPoint[] points) {
        GraphicsPoint[] result = new GraphicsPoint[points.length];
        for (int i = 0; i < points.length; i++) {
            result[i] = new GraphicsPoint(points[i].x, points[i].y);
        }
        return result;
    }

    @CFunction("CGPointCreateDictionaryRepresentation")
    public Map<String, Float> toDictionaryRepresentation() {
        HashMap<String, Float> res = new HashMap<String, Float>();
        res.put("X", x);
        res.put("Y", y);
        return res;
    }

    @CFunction("CGPointEqualToPoint")
    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof GraphicsPoint)) {
            return false;
        }
        GraphicsPoint other = (GraphicsPoint) o;
        return FloatUtil.equals(x, other.x) && FloatUtil.equals(y, other.y);
    }

    @Override
    public int hashCode() {
        return (int) (x + y);
    }

    @Override
    public String toString() {
        return "CGPoint[" + x + "," + y + "]";
    }
}
