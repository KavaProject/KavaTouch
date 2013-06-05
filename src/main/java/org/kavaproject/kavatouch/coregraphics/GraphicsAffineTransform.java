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

@Header("CGAffineTransform")
@CTypedef("CGAffineTransform")
public class GraphicsAffineTransform {
    public float a, b, c, d, tx, ty;

    public GraphicsAffineTransform(GraphicsAffineTransform template) {
        a = template.a;
        b = template.b;
        c = template.c;
        d = template.d;
        tx = template.tx;
        ty = template.ty;
    }

    @CFunction(value = "CGAffineTransformMake")
    public GraphicsAffineTransform(float a, float b, float c, float d, float tx, float ty) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.tx = tx;
        this.ty = ty;
    }

    @CFunction(value = "CGAffineTransformMakeRotation")
    public static final GraphicsAffineTransform makeRotation(float angle) {
        return IDENTITY().rotate(angle);
    }

    @CData(value = "CGAffineTransformIdentity")
    public static final GraphicsAffineTransform IDENTITY() {
        return new GraphicsAffineTransform(1, 0, 0, 1, 0, 0);
    }

    @CFunction(value = "CGAffineTransformRotate")
    public GraphicsAffineTransform rotate(float angle) {
        float cos = android.util.FloatMath.cos(angle);
        float sin = android.util.FloatMath.sin(angle);
        GraphicsAffineTransform res = new GraphicsAffineTransform(this);
        res.multiplyElementsInPlace(cos, sin, -sin, cos, 0, 0);
        return res;
    }

    private void multiplyElementsInPlace(float a, float b, float c, float d, float tx, float ty) {
        this.a = this.a * a + this.b * c;
        this.b = this.a * b + this.b * d;
        this.c = this.c * a + this.d * c;
        this.d = this.c * b + this.d * d;
        this.tx = this.tx * a + this.ty * c + tx;
        this.ty = this.tx * b + this.ty * d + ty;
    }

    @CFunction(value = "CGAffineTransformMakeScale")
    public static final GraphicsAffineTransform makeScale(float sx, float sy) {
        return IDENTITY().scale(sx, sy);
    }

    @CFunction(value = "CGAffineTransformScale")
    public GraphicsAffineTransform scale(float sx, float sy) {
        GraphicsAffineTransform res = new GraphicsAffineTransform(this);
        res.multiplyElementsInPlace(sx, 0, 0, sy, 0, 0);
        return res;
    }

    @CFunction(value = "CGAffineTransformMakeTranslation")
    public static final GraphicsAffineTransform makeTranslation(float tx, float ty) {
        return IDENTITY().translate(tx, ty);
    }

    @CFunction(value = "CGAffineTransformTranslate")
    public GraphicsAffineTransform translate(float tx, float ty) {
        GraphicsAffineTransform res = new GraphicsAffineTransform(this);
        res.multiplyElementsInPlace(1, 0, 0, 1, tx, ty);
        return res;
    }

    @CFunction(value = "CGAffineTransformConcat")
    public static final GraphicsAffineTransform multiply(GraphicsAffineTransform t1, GraphicsAffineTransform t2) {
        GraphicsAffineTransform res = new GraphicsAffineTransform(t1);
        res.multiplyElementsInPlace(t2.a, t2.b, t2.c, t2.d, t2.tx, t2.ty);
        return res;
    }

    @CFunction(value = "CGAffineTransformInvert")
    public GraphicsAffineTransform invert() {
        float determinant = a * d - c * b;
        if (determinant == 0) {
            return new GraphicsAffineTransform(this);
        }
        return new GraphicsAffineTransform(d / determinant, -b / determinant, -c / determinant, a / determinant,
                (-d * tx + c * ty) / determinant, (b * tx - a * ty) / determinant);
    }

    @CFunction(value = "CGSizeApplyAffineTransform")
    public GraphicsSize transformSize(GraphicsSize size) {
        float width = size.width;
        float height = size.height;
        return new GraphicsSize(a * width + c * height + tx, b * width + d * height + ty);
    }

    @CFunction(value = "CGRectApplyAffineTransform")
    public GraphicsRect transformRect(GraphicsRect rect) {
        rect = rect.standardize();
        GraphicsPoint topLeft = new GraphicsPoint(rect.origin.x, rect.origin.y + rect.size.height);
        GraphicsPoint topRight = new GraphicsPoint(rect.origin.x + rect.size.width, rect.origin.y + rect.size.height);
        GraphicsPoint bottomRight = new GraphicsPoint(rect.origin.x + rect.size.width, rect.origin.y);
        topLeft = transformPoint(topLeft);
        topRight = transformPoint(topRight);
        bottomRight = transformPoint(bottomRight);
        GraphicsPoint bottomLeft = transformPoint(rect.origin);
        rect = new GraphicsRect(topLeft.x, topLeft.y, 0, 0);
        rect = rect.unionWithCoordinates(topRight.x, topRight.y);
        rect = rect.unionWithCoordinates(bottomRight.x, bottomRight.y);
        rect = rect.unionWithCoordinates(bottomLeft.x, bottomLeft.y);
        return rect;
    }

    @CFunction(value = "CGPointApplyAffineTransform")
    public GraphicsPoint transformPoint(GraphicsPoint point) {
        float x = point.x;
        float y = point.y;
        return new GraphicsPoint(a * x + c * y + tx, b * x + d * y + ty);
    }

    @CFunction(value = "CGAffineTransformIsIdentity")
    public boolean isIdentity() {
        return equals(IDENTITY());
    }

    @CFunction("CGAffineTransformEqualToTransform")
    public boolean equals(GraphicsAffineTransform t2) {
        return FloatUtil.equals(a, t2.a) && FloatUtil.equals(b, t2.b) && FloatUtil.equals(c,
                t2.c) && FloatUtil.equals(d, t2.d) && FloatUtil.equals(tx, t2.tx) && FloatUtil.equals(ty, t2.ty);
    }

    @Override
    public String toString() {
        boolean unrotated = a == 1 && b == 0 && c == 0 && d == 1;
        boolean untranslated = tx == 0 && ty == 0;
        if (unrotated && untranslated) {
            return "CGAffineTransform[identity]";
        } else if (unrotated) {
            return "CGAffineTransform[translation tx=" + tx + ", ty=" + ty + "]";
        } else if (untranslated) {
            return "CGAffineTransform[rotation a=" + a + ", b=" + b + ", c=" + c + ", d=" + d + "]";
        } else {
            return "CGAffineTransform[a=" + a + ", b=" + b + ", c=" + c + ", d=" + d + ", tx=" + tx + ", " +
                    "ty=" + ty + "]";
        }
    }

    GraphicsPoint[] transformPoints(GraphicsPoint[] points) {
        GraphicsPoint[] result = new GraphicsPoint[points.length];
        int i = 0;
        for (GraphicsPoint point : points) {
            result[i++] = transformPoint(point);
        }
        return result;
    }
}
