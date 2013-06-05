/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.coregraphics;

import org.kavaproject.kavatouch.internal.CFunction;
import org.kavaproject.kavatouch.util.FloatUtil;

import java.util.ArrayList;
import java.util.List;

public class MutableGraphicsPath extends GraphicsPath {
    @CFunction("CGPathCreateMutable")
    public MutableGraphicsPath() {
        this(new ArrayList<GraphicsPathElement>());
    }

    public MutableGraphicsPath(ArrayList<GraphicsPathElement> elements) {
        super(elements);
    }

    @CFunction("CGPathCreateMutableCopy")
    public MutableGraphicsPath(GraphicsPath path) {
        this(new ArrayList<GraphicsPathElement>(path.elements), path.boundingBox, path.pathBoundingBox,
                path.polygonBoundingBox);
    }

    public MutableGraphicsPath(List<GraphicsPathElement> elements, GraphicsRect boundingBox,
                               GraphicsRect pathBoundingBox, GraphicsRect polygonBoundingBox) {
        super(elements, boundingBox, pathBoundingBox, polygonBoundingBox);
    }

    @CFunction("CGPathCreateMutableCopyByTransformingPath")
    public MutableGraphicsPath(GraphicsPath path, GraphicsAffineTransform transform) {
        super(path, transform);
    }

    private static int numberOfPointsForElement(GraphicsPathElement element) {
        switch (element.type) {
            case MOVE_TO_POINT:
            case ADD_LINE_TO_POINT:
            case CLOSE_SUBPATH:
                return 1;
            case ADD_QUAD_CURVE_TO_POINT:
                return 2;
            case ADD_CURVE_TO_POINT:
                return 3;
            default:
                throw new IncompatibleClassChangeError();
        }
    }

    @CFunction("CGPathAddArcToPoint")
    public void addArcToPoint(GraphicsAffineTransform transform, float x1, float y1, float x2, float y2, float radius) {
        final float PI = (float) Math.PI;
        final float DEG_90 = PI / 2;
        GraphicsPoint start = getCurrentPoint();
        if (transform != null) {
            start = transform.invert().transformPoint(start);
        }
        if ((FloatUtil.equals(start.x, x1) && FloatUtil.equals(start.y, y1)) || (FloatUtil.equals(x1,
                x2) && FloatUtil.equals(y1, y2))) {
            addLineToPoint(transform, x1, y1);
            return;
        }
        float norm1x = start.x - x1;
        float norm1y = start.y - y1;
        float l1 = android.util.FloatMath.sqrt(norm1x * norm1x + norm1y * norm1y);
        norm1x /= l1;
        norm1y /= l1;
        norm1x = norm1x > 0 ? Math.min(norm1x, 1) : Math.max(norm1x, -1);
        float norm2x = x2 - x1;
        float norm2y = y2 - y1;
        float l2 = android.util.FloatMath.sqrt(norm2x * norm2x + norm2y * norm2y);
        norm2x /= l2;
        norm2y /= l2;
        norm2x = norm2x > 0 ? Math.min(norm2x, 1) : Math.max(norm2x, -1);
        float u = norm1x * norm2x + norm1y * norm2y;
        if (u < -0.999) {
            addLineToPoint(transform, x1, y1);
            return;
        } else if (u > 0.999) {
            addLineToPoint(transform, x1 == 0 ? 0 : Float.NEGATIVE_INFINITY * x1,
                    y1 == 0 ? 0 : Float.NEGATIVE_INFINITY * y1);
            return;
        }
        float l = radius / android.util.FloatMath.sin((float) Math.acos(u));
        float centerX = x1 + (norm1x + norm2x) * l;
        float centerY = y1 + (norm1y + norm2y) * l;

        float startAngle = (float) Math.acos(norm1x);
        if (norm1y < 0) {
            startAngle = -startAngle;
        }
        float endAngle = (float) Math.acos(norm2x);
        if (norm2y < 0) {
            endAngle = -endAngle;
        }
        float xy = norm1x * norm2y - norm2x * norm1y;
        boolean clockwise = xy >= 0;
        if (clockwise) {
            endAngle = endAngle + DEG_90;
            startAngle = startAngle - DEG_90;
        } else {
            endAngle = endAngle - DEG_90;
            startAngle = startAngle + DEG_90;
        }
        addArc(transform, centerX, centerY, radius, startAngle, endAngle, clockwise);
    }

    @CFunction("CGPathAddLineToPoint")
    public void addLineToPoint(GraphicsAffineTransform transform, float x, float y) {
        GraphicsPoint p = new GraphicsPoint(x, y);
        if (transform != null) {
            p = transform.transformPoint(p);
        }
        addElement(GraphicsPathElementType.ADD_LINE_TO_POINT, p);
    }

    private void addElement(GraphicsPathElementType type, GraphicsPoint... point) {
        elements.add(new GraphicsPathElement(type, point));
        boundingBox = null;
        pathBoundingBox = null;
        polygonBoundingBox = null;
    }

    @CFunction("CGPathAddArc")
    public void addArc(GraphicsAffineTransform transform, float x, float y, float radius, float startAngle,
                       float endAngle, boolean clockwise) {
        float delta;
        if (clockwise) {
            delta = -Math.abs(FloatUtil.radians(360) - endAngle + startAngle) % FloatUtil.radians(360);
        } else {
            delta = Math.abs(endAngle - startAngle) % FloatUtil.radians(360);
        }
        addRelativeArc(transform, x, y, radius, startAngle, delta);
    }

    @CFunction("CGPathAddRelativeArc")
    public void addRelativeArc(GraphicsAffineTransform transform, float x, float y, float radius, float startAngle,
                               float delta) {
        GraphicsPoint[][] points;
        boolean clockwise = false;
        if (delta < 0) {
            points = BezierMath.createCurvesForRelativeArc(x, y, radius, startAngle + delta, -delta);
            clockwise = true;
        } else {
            points = BezierMath.createCurvesForRelativeArc(x, y, radius, startAngle, delta);
        }
        if (points.length > 0) {
            int i = clockwise ? points.length - 1 : 0;
            if (isEmpty()) {
                moveToPoint(transform, points[i][clockwise ? 3 : 0].x, points[i][clockwise ? 3 : 0].y);
            } else {
                addLineToPoint(transform, points[i][clockwise ? 3 : 0].x, points[i][clockwise ? 3 : 0].y);
            }
        }
        for (int j = 0; j < points.length; j++) {
            int i = clockwise ? points.length - j - 1 : j;
            if (clockwise) {
                addCurveToPoint(transform, points[i][2].x, points[i][2].y, points[i][1].x, points[i][1].y,
                        points[i][0].x, points[i][0].y);
            } else {
                addCurveToPoint(transform, points[i][1].x, points[i][1].y, points[i][2].x, points[i][2].y,
                        points[i][3].x, points[i][3].y);
            }
        }
    }

    @CFunction("CGPathMoveToPoint")
    public void moveToPoint(GraphicsAffineTransform transform, float x, float y) {
        GraphicsPoint p = new GraphicsPoint(x, y);
        if (transform != null) {
            p = transform.transformPoint(p);
        }
        addElement(GraphicsPathElementType.MOVE_TO_POINT, p);
    }

    @CFunction("CGPathAddCurveToPoint")
    public void addCurveToPoint(GraphicsAffineTransform transform, float cp1x, float cp1y, float cp2x, float cp2y,
                                float x, float y) {
        GraphicsPoint[] points = {new GraphicsPoint(cp1x, cp1y), new GraphicsPoint(cp2x, cp2y), new GraphicsPoint(x,
                y)};
        if (transform != null) {
            points = transform.transformPoints(points);
        }
        addElement(GraphicsPathElementType.ADD_CURVE_TO_POINT, points);
    }

    @CFunction("CGPathAddPath")
    public void addPath(GraphicsAffineTransform transform, GraphicsPath path) {
        super.addPath(transform, path);
    }

    @CFunction("CGPathAddQuadCurveToPoint")
    public void addQuadCurveToPoint(GraphicsAffineTransform transform, float cpx, float cpy, float x, float y) {
        GraphicsPoint[] points = {new GraphicsPoint(cpx, cpy), new GraphicsPoint(x, y)};
        if (transform != null) {
            points = transform.transformPoints(points);
        }
        addElement(GraphicsPathElementType.ADD_QUAD_CURVE_TO_POINT, points);
    }

    @CFunction("CGPathAddRects")
    public void addRects(GraphicsAffineTransform transform, GraphicsRect[] rects) {
        for (int i = 0; i < rects.length; i++) {
            addRect(transform, rects[i]);
        }
    }

    @CFunction("CGPathAddRect")
    public void addRect(GraphicsAffineTransform transform, GraphicsRect rect) {
        float left = rect.getMinX();
        float bottom = rect.getMinY();
        float right = rect.getMaxX();
        float top = rect.getMaxY();
        addLines(transform, new GraphicsPoint[]{new GraphicsPoint(left, bottom), new GraphicsPoint(right, bottom),
                new GraphicsPoint(right, top), new GraphicsPoint(left, top)}, 4);
        // add left edge and close
        closeSubpath();
    }

    @CFunction("CGPathCloseSubpath")
    public void closeSubpath() {
        GraphicsPoint p = null;
        for (int i = elements.size() - 1; i >= 0; i--) {
            GraphicsPathElement element = elements.get(i);
            GraphicsPathElementType type = element.type;
            if (type == GraphicsPathElementType.MOVE_TO_POINT || type == GraphicsPathElementType.CLOSE_SUBPATH) {
                p = element.getEndPoint();
                break;
            }
        }
        if (p != null) {
            addElement(GraphicsPathElementType.CLOSE_SUBPATH, p);
        }
    }

    @CFunction("CGPathAddLines")
    public void addLines(GraphicsAffineTransform transform, GraphicsPoint[] points, int count) {
        if (count == 0) {
            return;
        }
        moveToPoint(transform, points[0].x, points[0].y);
        for (int i = 1; i < count; i++) {
            addLineToPoint(transform, points[i].x, points[i].y);
        }
    }

    @CFunction("CGPathAddEllipseInRect")
    public void addEllipseInRect(GraphicsAffineTransform transform, GraphicsRect rect) {
        GraphicsAffineTransform scale = GraphicsAffineTransform.makeScale(rect.size.width, rect.size.height);
        GraphicsAffineTransform translation = GraphicsAffineTransform.makeTranslation(rect.origin.x, rect.origin.y);
        GraphicsAffineTransform t = GraphicsAffineTransform.multiply(scale, translation);
        if (transform != null) {
            t = GraphicsAffineTransform.multiply(t, transform);
        }
        addRelativeArc(t, 0.5F, 0.5F, 0.5F, FloatUtil.radians(270), FloatUtil.radians(360));
        closeSubpath();
    }
}
