/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.util;

import android.graphics.*;
import org.kavaproject.kavatouch.coregraphics.*;
import org.kavaproject.kavatouch.mobilecoreservices.UTType;
import org.kavaproject.kavatouch.uikit.UIColor;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class AndroidConversions {
    private static final long INTERVAL_BETWEEN_1970_AND_2001;

    static {
        INTERVAL_BETWEEN_1970_AND_2001 = new GregorianCalendar(2001, 1, 1, 0, 0, 0).getTimeInMillis();
    }

    public static final double toSecondsAbsoluteTime(Date jDate) {
        return toSecondsAbsoluteTime(jDate.getTime());
    }

    public static final double toSecondsAbsoluteTime(long millisSince1970) {
        long millisSince2001 = millisSince1970 - INTERVAL_BETWEEN_1970_AND_2001;
        return (double) millisSince2001 / 1000;
    }

    public static double toSecondsTimeSpan(long millis) {
        return (double) millis / 1000;
    }

    public static long toMillisSince1970(double secondsSince2001) {
        return (long) (1000 * secondsSince2001) + INTERVAL_BETWEEN_1970_AND_2001;
    }

    public static long toMillisTimeInterval(double timeInterval) {
        return (long) (1000 * timeInterval);
    }

    public static Matrix toMatrix(GraphicsAffineTransform transform) {
        Matrix matrix = new Matrix();
        matrix.setValues(new float[]{transform.a, transform.c, transform.tx, transform.b, transform.d, transform.ty,
                0, 0, 1F});
//        Matrix z = new Matrix();
//        z.postTranslate(transform.tx, transform.ty);
        return matrix;
    }

    public static final RectF toRectF(GraphicsRect cgRect) {
        float top = cgRect.getMinY();
        float bottom = cgRect.getMaxY();
        float left = cgRect.getMinX();
        float right = cgRect.getMaxX();
        return new RectF(left, top, right, bottom);
    }

    public static <K, V> Map<K, V> createMap(K[] keys, V[] values) {
        assert keys.length == values.length;
        HashMap<K, V> map = new HashMap<K, V>();
        for (int i = 0; i < keys.length; i++) {
            map.put(keys[i], values[i]);
        }
        return map;
    }

    public static UTType toUTType(String mimeType) {
        return null;
    }

    public static Rect toRect(GraphicsRect cgRect) {
        int top = (int) cgRect.getMinY();
        int bottom = (int) cgRect.getMaxY();
        int left = (int) cgRect.getMinX();
        int right = (int) cgRect.getMaxX();
        return new Rect(left, top, right, bottom);
    }

    public static int toColor(UIColor uiColor) {
        return toColor(uiColor.toCoreType());
    }

    public static int toColor(GraphicsColor cgColor) {
        float[] rgbaComponents = cgColor.toRGBAColor().getComponents();
        return toColor(rgbaComponents);
    }

    public static int toColor(float[] rgbaComponents) {
        float r = FloatUtil.clip(rgbaComponents[0], 0, 1);
        float g = FloatUtil.clip(rgbaComponents[1], 0, 1);
        float b = FloatUtil.clip(rgbaComponents[2], 0, 1);
        float a = FloatUtil.clip(rgbaComponents[3], 0, 1);
        return Color.argb((int) (255 * a), (int) (255 * r), (int) (255 * g), (int) (255 * b));
    }

    public static GraphicsImageAlphaInfo toAlphaInfo(Bitmap.Config config) {
        switch (config) {
            case ARGB_8888:
                return GraphicsImageAlphaInfo.FIRST;
            case ARGB_4444:
                return GraphicsImageAlphaInfo.FIRST;
            case RGB_565:
                return GraphicsImageAlphaInfo.NONE;
            case ALPHA_8:
                return GraphicsImageAlphaInfo.ONLY;
            default:
                throw new IllegalArgumentException();
        }
    }

    public static Path createAndroidPath(GraphicsPath cgPath) {
        Path androidPath = new Path();
        for (GraphicsPathElement element : cgPath.elements) {
            GraphicsPoint[] points = element.points;
            switch (element.type) {
                case MOVE_TO_POINT:
                    androidPath.moveTo(points[0].x, points[0].y);
                    break;
                case CLOSE_SUBPATH:
                    androidPath.close();
                    break;
                case ADD_CURVE_TO_POINT:
                    androidPath.cubicTo(points[0].x, points[0].y, points[1].x, points[1].y, points[2].x, points[2].y);
                    break;
                case ADD_QUAD_CURVE_TO_POINT:
                    androidPath.quadTo(points[0].x, points[0].y, points[1].x, points[2].y);
                    break;
                case ADD_LINE_TO_POINT:
                    androidPath.lineTo(points[0].x, points[0].y);
                    break;
            }
        }
        return androidPath;
    }
}
