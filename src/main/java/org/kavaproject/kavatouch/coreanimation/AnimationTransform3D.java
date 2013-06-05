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
import org.kavaproject.kavatouch.internal.CFunction;
import org.kavaproject.kavatouch.internal.CTypedef;
import org.kavaproject.kavatouch.internal.Header;
import org.kavaproject.kavatouch.util.NotImplementedException;

@Header("CATransform3D")
@CTypedef(value = "CATransform3D", tokenGroup = "CoreAnimation_functions")
public class AnimationTransform3D {

    public static final AnimationTransform3D CATransform3DIdentity = new AnimationTransform3D(1, 0, 0, 0, 0, 1, 0, 0,
            0, 0, 1, 0, 0, 0, 0, 1);
    public final float m11, m12, m13, m14;
    public final float m21, m22, m23, m24;
    public final float m31, m32, m33, m34;
    public final float m41, m42, m43, m44;

    private AnimationTransform3D(float m11, float m12, float m13, float m14, float m21, float m22, float m23,
                                 float m24, float m31, float m32, float m33, float m34, float m41, float m42,
                                 float m43, float m44) {
        this.m11 = m11;
        this.m12 = m12;
        this.m13 = m13;
        this.m14 = m14;

        this.m21 = m21;
        this.m22 = m22;
        this.m23 = m23;
        this.m24 = m24;

        this.m31 = m31;
        this.m32 = m32;
        this.m33 = m33;
        this.m34 = m34;

        this.m41 = m41;
        this.m42 = m42;
        this.m43 = m43;
        this.m44 = m44;
    }

    public AnimationTransform3D(AnimationTransform3D template) {
        m11 = template.m11;
        m12 = template.m12;
        m13 = template.m13;
        m14 = template.m14;

        m21 = template.m21;
        m22 = template.m22;
        m23 = template.m23;
        m24 = template.m24;

        m31 = template.m31;
        m32 = template.m32;
        m33 = template.m33;
        m34 = template.m34;

        m41 = template.m41;
        m42 = template.m42;
        m43 = template.m43;
        m44 = template.m44;
    }

    @CFunction("CATransform3DMakeTranslation")
    public static AnimationTransform3D makeTranslation(float tx, float ty, float tz) {
        return new AnimationTransform3D(1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, tx, ty, tz, 1);
    }

    @CFunction("CATransform3DMakeScale")
    public static AnimationTransform3D makeScale(float sx, float sy, float sz) {
        return new AnimationTransform3D(sx, 0, 0, 0, 0, sy, 0, 0, 0, 0, sz, 0, 0, 0, 0, 1);
    }

    @CFunction("CATransform3DMakeRotation")
    public static AnimationTransform3D makeRotation(float angle, float x, float y, float z) {
        if (x == 0 && y == 0 && z == 0) {
            return CATransform3DIdentity;
        }
        float norm = (float) Math.sqrt(x * x + y * y + z * z);
        float l = x / norm;
        float m = y / norm;
        float n = z / norm;
        float sin = (float) Math.sin(angle);
        float cos = (float) Math.cos(angle);
        float icos = 1 - cos;
        return new AnimationTransform3D(l * l * icos + cos, m * l * icos - n * sin, n * l * icos + m * sin, 0,
                l * m * icos + n * sin, m * m * icos + cos, n * m * icos - l * sin, 0, l * n * icos - m * sin,
                m * n * icos + l * sin, n * n * icos + cos, 0, 0, 0, 0, 1);
    }

    @CFunction("CATransform3DMakeAffineTransform")
    public static AnimationTransform3D makeFromAffine(GraphicsAffineTransform transform) {
        return new AnimationTransform3D(transform.a, transform.b, 0, 0, transform.c, transform.d, 0, 0, 0, 0, 1, 0,
                transform.tx, transform.ty, 0, 1);
    }

    @CFunction("CATransform3DIsIdentity")
    public boolean isIdentity() {
        return CATransform3DIdentity.equals(this);
    }

    @CFunction("CATransform3DEqualToTransform")
    @Override
    public boolean equals(Object other) {
        if (other == null || !(other instanceof AnimationTransform3D)) {
            return false;
        }
        AnimationTransform3D template = (AnimationTransform3D) other;
        return m11 == template.m11 && m12 == template.m12 && m13 == template.m13 && m14 == template.m14

                && m21 == template.m21 && m22 == template.m22 && m23 == template.m23 && m24 == template.m24

                && m31 == template.m31 && m32 == template.m32 && m33 == template.m33 && m34 == template.m34

                && m41 == template.m41 && m42 == template.m42 && m43 == template.m43 && m44 == template.m44;
    }

    @CFunction("CATransform3DTranslate")
    public AnimationTransform3D translate(float tx, float ty, float tz) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CATransform3DScale")
    public AnimationTransform3D scale(float sx, float sy, float sz) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CATransform3DRotate")
    public AnimationTransform3D rotate(float angle, float x, float y, float z) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CATransform3DConcat")
    public AnimationTransform3D concat(AnimationTransform3D other) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CATransform3DInvert")
    public AnimationTransform3D invert() {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CATransform3DIsAffine")
    public boolean isAffine() {
        return m13 == 0 && m14 == 0 && m23 == 0 && m24 == 0 && m31 == 0 && m32 == 0 && m33 == 1 && m34 == 0 && m43 ==
                0 && m44 == 1;
    }

    @CFunction("CATransform3DGetAffineTransform")
    public GraphicsAffineTransform getAffine() {
        return new GraphicsAffineTransform(m11, m12, m21, m22, m41, m42);
    }
}
