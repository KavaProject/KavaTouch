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
@CTypedef(value = "CGSize")
public class GraphicsSize {
    @CData("CGSizeZero")
    public static final GraphicsSize ZERO = new GraphicsSize(0, 0);
    public final float width, height;

    public GraphicsSize(GraphicsSize template) {
        this.width = template.width;
        this.height = template.height;
    }

    @CFunction("CGSizeMake")
    public GraphicsSize(float width, float height) {
        this.width = width;
        this.height = height;
    }

    @CFunction("CGSizeMakeWithDictionaryRepresentation")
    public static GraphicsSize valueOf(Map<String, Float> dict) {
        Float width = dict.get("Width");
        Float height = dict.get("Height");
        if (width == null || height == null) {
            return null;
        }
        return new GraphicsSize(width, height);
    }

    @CFunction("CGSizeCreateDictionaryRepresentation")
    public Map<String, Float> toMap() {
        HashMap<String, Float> res = new HashMap<String, Float>();
        res.put("Width", width);
        res.put("Height", height);
        return res;
    }

    @CFunction("CGSizeEqualToSize")
    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof GraphicsSize)) {
            return false;
        }
        GraphicsSize other = (GraphicsSize) o;
        return FloatUtil.equals(width, other.width) && FloatUtil.equals(height, other.height);
    }

    @Override
    public int hashCode() {
        return (int) (width + height);
    }

    @Override
    public String toString() {
        return "CGSize[" + width + "," + height + "]";
    }
}
