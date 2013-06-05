/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.util;

public class FloatUtil {
    public static final float F_PI = (float) Math.PI;

    public static boolean equals(float d1, float d2) {
        if (d1 == 0 && d2 == 0) {
            return true;
        }
        if (d1 == 0 || d2 == 0) {
            return false;
        }
        if (d1 == Float.POSITIVE_INFINITY && d2 == Float.POSITIVE_INFINITY) {
            return true;
        }
        if (d1 == Float.NEGATIVE_INFINITY && d2 == Float.NEGATIVE_INFINITY) {
            return true;
        }
        float d = d1 / d2;
        return (Math.abs(d - 1.0) < 0.001);
    }

    public static float radians(float degrees) {
        return F_PI * degrees / 180;
    }

    public static float clip(float value, int floor, int ceiling) {
        return Math.max(floor, Math.min(ceiling, value));
    }
}
