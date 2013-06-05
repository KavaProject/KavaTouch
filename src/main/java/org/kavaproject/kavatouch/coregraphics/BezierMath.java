/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.coregraphics;

import org.kavaproject.kavatouch.util.FloatUtil;

public class BezierMath {
    final static float u = 2.0F / 3.0F * (android.util.FloatMath.sqrt(2.0F) - 1.0F);
    final static GraphicsPoint[][] ELLIPSE = {{new GraphicsPoint(1.0F, 0.5F + u), new GraphicsPoint(0.5F + u, 1.0F),
            new GraphicsPoint(0.5F, 1.0F)}, //topRight
            {new GraphicsPoint(0.5F - u, 1.0F), new GraphicsPoint(0.0F, 0.5F + u), new GraphicsPoint(0.0F, 0.5F)},
            //topLeft
            {new GraphicsPoint(0.0F, 0.5F - u), new GraphicsPoint(0.5F - u, 0.0F), new GraphicsPoint(0.5F, 0.0F)},
            //bottomLeft
            {new GraphicsPoint(0.5F + u, 0.0F), new GraphicsPoint(1.0F, 0.5F - u), new GraphicsPoint(1.0F,
                    0.5F)}  //bottomRight
    };

    public static final GraphicsPoint[][] createCurvesForEllipse(GraphicsRect rect) {
        //rect.orig The upper-left corner of the ellipse bounds
        //rect.size The width and height of the ellipse bounds
        GraphicsAffineTransform scale = GraphicsAffineTransform.makeScale(rect.size.width, rect.size.height);
        GraphicsAffineTransform translation = GraphicsAffineTransform.makeTranslation(rect.origin.x, rect.origin.y);
        GraphicsAffineTransform t = GraphicsAffineTransform.multiply(scale, translation);
        GraphicsPoint[][] result = new GraphicsPoint[4][3];
        for (int i = 0; i < 4; i++) {
            result[i] = t.transformPoints(ELLIPSE[i]);
        }
        return result;
    }

    public static final GraphicsPoint[][] createCurvesForRelativeArc(float x, float y, float radius,
                                                                     float startAngle, float delta) {
        if (delta < 0) {
            throw new IllegalArgumentException("delta must be positive");
        }
        startAngle = startAngle % FloatUtil.radians(360); //Subtract full circles
        if (startAngle < 0) {
            startAngle += FloatUtil.radians(360); //Make start angle positive
        }
        float step = FloatUtil.radians(90); //90 degrees
        int segmentCount = (int) Math.ceil(delta / step); //partition the circle segment in sub segments
        GraphicsPoint[][] points = new GraphicsPoint[segmentCount][];
        for (int pointsIndex = 0; pointsIndex < segmentCount; pointsIndex++) {
            //90 degree during the first iterations, in the last iteration the remainder
            float sweepAngle = (delta > step) ? step : delta;
            float[] XY = new float[8];
            float B = android.util.FloatMath.sin(sweepAngle / 2);
            float C = android.util.FloatMath.cos(sweepAngle / 2);
            float A = 1 - C;
            float X = A * 4 / 3;
            float Y = B - X * (1 - A) / B;
            float s = android.util.FloatMath.sin(startAngle + sweepAngle / 2);
            float c = android.util.FloatMath.cos(startAngle + sweepAngle / 2);
            XY[0] = C;
            XY[1] = -B;
            XY[2] = C + X;
            XY[3] = -Y;
            XY[4] = C + X;
            XY[5] = Y;
            XY[6] = C;
            XY[7] = B;
            points[pointsIndex] = new GraphicsPoint[4];
            for (int i = 0; i < 4; i++) {
                points[pointsIndex][i] = new GraphicsPoint(x + (XY[i * 2] * c - XY[i * 2 + 1] * s) * radius,
                        y + (XY[i * 2] * s + XY[i * 2 + 1] * c) * radius);
            }
            startAngle += step;
            delta -= step;
        }
        return points;
    }

    public static final int countCurveCrossings(GraphicsPoint point, GraphicsPoint fromPoint, GraphicsPoint toPoint,
                                                GraphicsPoint a, GraphicsPoint b, float flatness) {
        // Count the crossings on the Y axis from the point to a bezier curve
        Curve curve = new Curve(fromPoint.x, fromPoint.y, toPoint.x, toPoint.y, a.x, a.y, b.x, b.y);
        return countCurveCrossings(point, curve, flatness);
    }

    public static final int countQuadCurveCrossings(GraphicsPoint point, GraphicsPoint fromPoint,
                                                    GraphicsPoint toPoint, GraphicsPoint a, float flatness) {
        //TODO Test
        // Count the crossings on the Y axis from the point to a quad curve
        Curve curve = new Curve(fromPoint.x, fromPoint.y, toPoint.x, toPoint.y, a.x, a.y);
        return countQuadCurveCrossings(point, curve, flatness);
    }

    public static final int countLineCrossings(GraphicsPoint point, GraphicsPoint fromPoint, GraphicsPoint toPoint) {
        Curve curve = new Curve(fromPoint.x, fromPoint.y, toPoint.x, toPoint.y);
        return countLineCrossings(point, curve);
    }

    private static final int countLineCrossings(GraphicsPoint point, Curve l) {
        if (((l.fromY <= point.y) && (l.toY > point.y)) || ((l.fromY > point.y) && (l.toY <= point.y))) {
            // an upward crossing or a downward crossing
            // compute the actual edge-ray intersect x-coordinate
            float vt = (point.y - l.fromY) / (l.toY - l.fromY);
            if (point.x < l.fromX + vt * (l.toX - l.fromX)) {
                // point.x() < intersect
                return l.fromY < l.toY ? 1 : -1;   // a valid crossing of y=point.y() right of point.x
            }
        }
        return 0;
    }

    public static final GraphicsRect rectUnionWithPoints(GraphicsRect bounds, GraphicsPoint... points) {
        for (GraphicsPoint point : points) {
            bounds = rectUnionWithPoint(bounds, point.x, point.y);
        }
        return bounds;
    }

    private static final GraphicsRect rectUnionWithPoint(GraphicsRect bounds, float x, float y) {
        bounds = bounds.standardize();
        return bounds.unionWithCoordinates(x, y);
    }

    public static final GraphicsRect rectUnionWithLine(GraphicsRect bounds, GraphicsPoint fromPoint,
                                                       GraphicsPoint toPoint) {
        Curve curve = new Curve(fromPoint.x, fromPoint.y, toPoint.x, toPoint.y);
        return rectUnionWithLine(bounds, curve);
    }

    private static final GraphicsRect rectUnionWithLine(GraphicsRect bounds, Curve c) {
        bounds = bounds.unionWithCoordinates(c.fromX, c.fromY);
        bounds = bounds.unionWithCoordinates(c.toX, c.toY);
        return bounds;
    }

    public static final GraphicsRect rectUnionWithQuadCurve(GraphicsRect bounds, GraphicsPoint fromPoint,
                                                            GraphicsPoint toPoint, GraphicsPoint a, float flatness) {
        Curve curve = new Curve(fromPoint.x, fromPoint.y, toPoint.x, toPoint.y, a.x, a.y);
        return rectUnionWithQuadCurve(bounds, curve, flatness);
    }

    public static final GraphicsRect rectUnionWithCurve(GraphicsRect bounds, GraphicsPoint fromPoint,
                                                        GraphicsPoint toPoint, GraphicsPoint a, GraphicsPoint b,
                                                        float flatness) {
        Curve curve = new Curve(fromPoint.x, fromPoint.y, toPoint.x, toPoint.y, a.x, a.y, b.x, b.y);
        return rectUnionWithCurve(bounds, curve, flatness);
    }

    private static final GraphicsRect rectUnionWithQuadCurve(GraphicsRect bounds, Curve c, float flatness) {
        if (bounds.contains(c.fromX, c.fromY) && bounds.contains(c.toX, c.toY) && bounds.contains(c.aX, c.aY)) {
            return bounds;
        }
        if (quadCurveIsFlat(flatness, c)) {
            bounds = rectUnionWithLine(bounds, c);
        } else {
            Curve sub1 = new Curve();
            Curve sub2 = new Curve();
            splitQuadCurve(c, sub1, sub2);
            bounds = rectUnionWithQuadCurve(bounds, sub1, flatness);
            bounds = rectUnionWithQuadCurve(bounds, sub2, flatness);
        }
        return bounds;
    }

    private static final GraphicsRect rectUnionWithCurve(GraphicsRect bounds, Curve c, float flatness) {
        if (bounds.contains(c.fromX, c.fromY) && bounds.contains(c.toX, c.toY) && bounds.contains(c.aX,
                c.aY) && bounds.contains(c.bX, c.bY)) {
            return bounds;
        }
        if (curveIsFlat(flatness, c)) {
            bounds = rectUnionWithLine(bounds, c);
        } else {
            Curve sub1 = new Curve();
            Curve sub2 = new Curve();
            splitCurve(c, sub1, sub2);
            bounds = rectUnionWithCurve(bounds, sub1, flatness);
            bounds = rectUnionWithCurve(bounds, sub2, flatness);
        }
        return bounds;
    }

    private static int countCurveCrossings(GraphicsPoint point, Curve c, float flatness) {
        //Optimization
        float minY = Math.min(Math.min(c.fromY, c.toY), Math.min(c.aY, c.bY));
        float maxY = Math.max(Math.max(c.fromY, c.toY), Math.max(c.aY, c.bY));
        if (minY > point.y || maxY < point.y) {
            return 0;
        }
        //Check if point left of all curve points and in between fromPoint.y and toPoint.y not yet implemented
        if (curveIsFlat(flatness, c)) {
            // Flat enough curve : handle it like a segment
            return countLineCrossings(point, c);
        } else {
            // Subdivide the bezier path and test both subpaths - adapted from the flatten path code
            Curve sub1 = new Curve();
            Curve sub2 = new Curve();
            splitCurve(c, sub1, sub2);
            return countCurveCrossings(point, sub1, flatness) + countCurveCrossings(point, sub2, flatness);
        }
    }

    private static int countQuadCurveCrossings(GraphicsPoint point, Curve c, float flatness) {
        float minY = Math.min(Math.min(c.fromY, c.toY), c.aY);
        float maxY = Math.max(Math.max(c.fromY, c.toY), c.aY);
        if (minY > point.y || maxY < point.y) {
            return 0;
        }
        if (quadCurveIsFlat(flatness, c)) {
            return countLineCrossings(point, c);
        } else {
            Curve sub1 = new Curve();
            Curve sub2 = new Curve();
            splitQuadCurve(c, sub1, sub2);
            return countQuadCurveCrossings(point, sub1, flatness) + countQuadCurveCrossings(point, sub2, flatness);
        }
    }

    private static final boolean curveIsFlat(float desiredFlatness, Curve c) {
        // Roughly compute the furthest distance of the curved path from the line connecting start to end
        double dx1 = 3.0 * c.aX - 2.0 * c.fromX - c.toX;
        dx1 *= dx1;
        double dy1 = 3.0 * c.aY - 2.0 * c.fromY - c.toY;
        dy1 *= dy1;

        double dx2 = 3.0 * c.bX - 2.0 * c.toX - c.fromX;
        dx2 *= dx2;
        double dy2 = 3.0 * c.bY - 2.0 * c.toY - c.fromY;
        dy2 *= dy2;

        return Math.max(dx1, dx2) + Math.max(dy1, dy2) <= desiredFlatness;
    }

    //TODO Test
    private static final boolean quadCurveIsFlat(float desiredFlatness, Curve c) {
        // Roughly compute the furthest distance of the curved path from the line connecting start to end
        double dx = 3.0 * c.aX - 2.0 * c.fromX - c.toX;
        dx *= dx;
        double dy = 3.0 * c.aY - 2.0 * c.fromY - c.toY;
        dy *= dy;

        return dx + dy <= desiredFlatness;
    }

    private static void splitCurve(Curve c, Curve sub1, Curve sub2) {
        sub1.aX = (c.fromX + c.aX) / 2;
        sub1.aY = (c.fromY + c.aY) / 2;
        float TX = (c.aX + c.bX) / 2;
        float TY = (c.aY + c.bY) / 2;
        sub1.bX = (sub1.aX + TX) / 2;
        sub1.bY = (sub1.aY + TY) / 2;
        sub2.bX = (c.bX + c.toX) / 2;
        sub2.bY = (c.bY + c.toY) / 2;
        sub2.aX = (TX + sub2.bX) / 2;
        sub2.aY = (TY + sub2.bY) / 2;
        sub2.fromX = (sub1.bX + sub2.aX) / 2;
        sub2.fromY = (sub1.bY + sub2.aY) / 2;

        sub1.fromX = c.fromX;
        sub1.fromY = c.fromY;
        sub1.toX = sub2.fromX;
        sub1.toY = sub2.fromY;
        sub2.toX = c.toX;
        sub2.toY = c.toY;
    }

    private static void splitQuadCurve(Curve c, Curve sub1, Curve sub2) {
        sub1.aX = (c.fromX + c.aX) / 2;
        sub1.aY = (c.fromY + c.aY) / 2;
        sub2.aX = (c.aX + c.toX) / 2;
        sub2.aY = (c.aY + c.toY) / 2;
        sub2.fromX = (sub1.aX + sub2.aX) / 2;
        sub2.fromY = (sub1.aY + sub2.aY) / 2;

        sub1.fromX = c.fromX;
        sub1.fromY = c.fromY;
        sub1.toX = sub2.fromX;
        sub1.toY = sub2.fromY;
        sub2.toX = c.toX;
        sub2.toY = c.toY;
    }

    private static final class Curve {
        public float fromX;
        public float fromY;
        public float toX;
        public float toY;
        public float aX;
        public float aY;
        public float bX;
        public float bY;

        public Curve(float fromX, float fromY, float toX, float toY, float aX, float aY, float bX, float bY) {
            this(fromX, fromY, toX, toY, aX, aY);
            this.bX = bX;
            this.bY = bY;
        }

        public Curve(float fromX, float fromY, float toX, float toY, float aX, float aY) {
            this(fromX, fromY, toX, toY);
            this.aX = aX;
            this.aY = aY;
        }

        public Curve(float fromX, float fromY, float toX, float toY) {
            this.fromX = fromX;
            this.fromY = fromY;
            this.toX = toX;
            this.toY = toY;
        }

        public Curve() {
        }
    }
}
