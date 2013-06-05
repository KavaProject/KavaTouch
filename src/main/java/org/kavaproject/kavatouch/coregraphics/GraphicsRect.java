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
import org.kavaproject.kavatouch.util.NotImplementedException;

import java.util.HashMap;
import java.util.Map;

@Header("CGGeometry")
@CTypedef(value = "CGRect")
public class GraphicsRect {
    @CData("CGRectNull")
    public final static GraphicsRect NULL = new GraphicsRect(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY, 0, 0);
    @CData("CGRectInfinite")
    public final static GraphicsRect INFINITE = new GraphicsRect(new GraphicsPoint(Float.NEGATIVE_INFINITY,
            Float.NEGATIVE_INFINITY), new GraphicsSize(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY));
    @CData("CGRectZero")
    public final static GraphicsRect ZERO = new GraphicsRect(0, 0, 0, 0);
    public final GraphicsPoint origin;
    public final GraphicsSize size;

    public GraphicsRect(GraphicsPoint origin, GraphicsSize size) {
        this.origin = origin;
        this.size = size;
    }

    public GraphicsRect(GraphicsRect template) {
        this(template.origin.x, template.origin.y, template.size.width, template.size.height);
    }

    @CFunction("CGRectMake")
    public GraphicsRect(float x, float y, float width, float height) {
        origin = new GraphicsPoint(x, y);
        size = new GraphicsSize(width, height);
    }

    @CFunction("CGRectMakeWithDictionaryRepresentation")
    public static GraphicsRect makeWithDictionaryRepresentation(Map<String, Float> dict) {
        Float x = dict.get("X");
        Float y = dict.get("Y");
        Float width = dict.get("Width");
        Float height = dict.get("Height");
        if (x == null || y == null || width == null || y == null) {
            return null;
        }
        return new GraphicsRect(x, y, width, height);
    }

    @CFunction("CGRectCreateDictionaryRepresentation")
    public Map<String, Float> toDictionaryRepresentation() {
        HashMap<String, Float> res = new HashMap<String, Float>();
        res.put("X", origin.x);
        res.put("Y", origin.y);
        res.put("Width", size.width);
        res.put("Height", size.height);
        return res;
    }

    @CFunction("CGRectDivide")
    public void divide(GraphicsRect slice, GraphicsRect remainder, float amount, GraphicsRectEdge edge) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CGRectInset")
    public GraphicsRect inset(float dx, float dy) {
        float originX = getMinX();
        float originY = getMinY();
        float width = getWidth();
        float height = getHeight();
        return new GraphicsRect(originX + dx, originY + dy, width - 2 * dx, height - 2 * dy); //TODO Consider
        // returning null rectangle
    }

    @CFunction("CGRectGetWidth")
    public float getWidth() {
        return Math.abs(size.width);
    }

    @CFunction("CGRectGetHeight")
    public float getHeight() {
        return Math.abs(size.height);
    }

    @CFunction("CGRectGetMinY")
    public float getMinY() {
        if (origin == null) {
            return Float.POSITIVE_INFINITY;
        }
        return size.height >= 0 ? origin.y : origin.y + size.height;
    }

    @CFunction("CGRectGetMinX")
    public float getMinX() {
        return size.width >= 0 ? origin.x : origin.x + size.width;
    }

    @CFunction("CGRectIntegral")
    public GraphicsRect integral() {
        if (!isEmpty()) {
            float maxX = (float) Math.ceil(getMaxX());
            float maxY = (float) Math.ceil(getMaxY());
            float x = (float) Math.floor(origin.x);
            float y = (float) Math.floor(origin.y);
            float width = maxX - getMinX();
            float height = maxY - getMinY();
            return new GraphicsRect(x, y, width, height);
        }
        return this;
    }

    @CFunction("CGRectIsEmpty")
    public boolean isEmpty() {
        return isNull() || FloatUtil.equals(getWidth(), 0) || FloatUtil.equals(getHeight(), 0);
    }

    @CFunction("CGRectIsNull")
    public boolean isNull() {
        return equals(NULL);
    }

    @CFunction("CGRectEqualToRect")
    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof GraphicsRect)) {
            return false;
        }
        GraphicsRect other = (GraphicsRect) o;
        return origin.equals(other.origin) && size.equals(other.size);
    }

    @Override
    public int hashCode() {
        return origin.hashCode() * size.hashCode();
    }

    @Override
    public String toString() {
        return "CGRect[" + origin + "," + size + "]";
    }

    @CFunction("CGRectGetMaxY")
    public float getMaxY() {
        return size.height >= 0 ? origin.y + size.height : origin.y;
    }

    @CFunction("CGRectGetMaxX")
    public float getMaxX() {
        return size.width >= 0 ? origin.x + size.width : origin.x;
    }

    @CFunction("CGRectIntersection")
    public GraphicsRect intersection(GraphicsRect rect) {
        if (!intersects(rect)) {
            return NULL;
        }
        float x = Math.max(getMinX(), rect.getMinX());
        float y = Math.max(getMinY(), rect.getMinY());
        float width = Math.min(getMaxX(), rect.getMaxX()) - x;
        float height = Math.min(getMaxY(), rect.getMaxY()) - y;
        return new GraphicsRect(x, y, width, height);
    }

    @CFunction("CGRectIntersectsRect")
    public boolean intersects(GraphicsRect rect) {
        return getMinX() < rect.getMaxX() && getMaxX() > rect.getMinX() &&
                getMinY() < rect.getMaxY() && getMaxY() > rect.getMinY();
    }

    @CFunction("CGRectOffset")
    public GraphicsRect offset(float dx, float dy) {
        return new GraphicsRect(origin.x + dx, origin.y + dy, size.width, size.height);
    }

    @CFunction("CGRectStandardize")
    public GraphicsRect standardize() {
        float sWidth = getWidth();
        float sHeight = getHeight();
        float sX = getMinX();
        float sY = getMinY();
        return new GraphicsRect(sX, sY, sWidth, sHeight);
    }

    @CFunction("CGRectUnion")
    public GraphicsRect unionWith(GraphicsRect rect) {
        if (isNull()) {
            return rect;
        }
        if (rect.isNull()) {
            return this;
        }
        float minX = Math.min(getMinX(), rect.getMinX());
        float minY = Math.min(getMinY(), rect.getMinY());
        float maxX = Math.max(getMaxX(), rect.getMaxX());
        float maxY = Math.max(getMaxY(), rect.getMaxY());
        return new GraphicsRect(minX, minY, maxX - minX, maxY - minY);
    }

    @CFunction("CGRectContainsPoint")
    public boolean contains(GraphicsPoint point) {
        return contains(point.x, point.y);
    }

    boolean contains(float x, float y) {
        return (x >= getMinX() && x < getMaxX() &&
                y >= getMinY() && y < getMaxY());
    }

    @CFunction("CGRectContainsRect")
    public boolean contains(GraphicsRect rect) {
        GraphicsSize size = rect.size;
        GraphicsPoint origin = rect.origin;
        return this.origin.x <= origin.x && this.origin.y <= origin.y && this.origin.x + this.size.width >= origin.x
                + size.width &&
                this.origin.y + this.size.height >= origin.y + size.height;
    }

    @CFunction("CGRectGetMidX")
    public float getMidX() {
        return origin.x + size.width / 2;
    }

    @CFunction("CGRectGetMidY")
    public float getMidY() {
        return origin.y + size.height / 2;
    }

    @CFunction("CGRectIsInfinite")
    public boolean isInfinite() {
        return equals(INFINITE);
    }

    GraphicsRect unionWithCoordinates(float x, float y) {
        float left = Math.min(origin.x, x);
        float bottom = Math.min(origin.y, y);
        float right = Math.max(origin.x + size.width, x);
        float top = Math.max(origin.y + size.height, y);
        float resX = left;
        float resY = bottom;
        float resWidth = right - left;
        float resHeight = top - bottom;
        return new GraphicsRect(resX, resY, resWidth, resHeight);
    }
}
