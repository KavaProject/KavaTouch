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
import org.kavaproject.kavatouch.internal.Header;
import org.kavaproject.kavatouch.internal.OpaqueType;
import org.kavaproject.kavatouch.util.NotImplementedException;

import java.util.ArrayList;
import java.util.List;

@Header("CGPath")
@OpaqueType("CGPathRef")
public class GraphicsPath {
    private final static float sFlatness = 0.01F; //0.6F
    public List<GraphicsPathElement> elements;
    public GraphicsRect boundingBox;
    public GraphicsRect pathBoundingBox;
    public GraphicsRect polygonBoundingBox;

    public GraphicsPath(List<GraphicsPathElement> elements, GraphicsRect boundingBox, GraphicsRect pathBoundingBox,
                        GraphicsRect polygonBoundingBox) {
        this(elements);
        this.boundingBox = boundingBox;
        this.pathBoundingBox = pathBoundingBox;
        this.polygonBoundingBox = polygonBoundingBox;
    }

    public GraphicsPath(List<GraphicsPathElement> elements) {
        this.elements = elements;
    }

    @CFunction("CGPathCreateCopy")
    public GraphicsPath(GraphicsPath other) {
        this(new ArrayList<GraphicsPathElement>(other.elements));
    }


    @CFunction("CGPathCreateCopyByTransformingPath")
    public GraphicsPath(GraphicsPath path, GraphicsAffineTransform transform) {
        addPath(transform, path);
    }

    protected void addPath(GraphicsAffineTransform m, GraphicsPath path2) {
        for (GraphicsPathElement element : path2.elements) {
            elements.add(m != null ? element.applyAffineTransform(m) : element.copy());
        }
    }

    @CFunction("CGPathCreateWithEllipseInRect")
    public static GraphicsPath ellipseFromRect(GraphicsRect rect, GraphicsAffineTransform transform) {
        MutableGraphicsPath path = new MutableGraphicsPath();
        path.addEllipseInRect(transform, rect);
        return new GraphicsPath(path.elements);
    }

    @CFunction("CGPathCreateWithRect")
    public static GraphicsPath createWithRect(GraphicsRect rect, GraphicsAffineTransform transform) {
        MutableGraphicsPath path = new MutableGraphicsPath();
        path.addRect(transform, rect);
        return new GraphicsPath(path.elements);
    }

    @CFunction("CGPathCreateCopyByDashingPath")
    public static GraphicsPath copyByDashingPath(GraphicsPath path, GraphicsAffineTransform transform, float phase,
                                                 float[] lengths, int count) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CGPathCreateCopyByStrokingPath")
    public static GraphicsPath copyByStrokingPath(GraphicsPath path, GraphicsAffineTransform transform,
                                                  float lineWidth, GraphicsLineCap lineCap,
                                                  GraphicsLineJoin lineJoin, float miterLimit) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CGPathApply")
    public void apply(Object info, ApplierFunction function) {
        for (GraphicsPathElement element : elements) {
            function.eval(info, element);
        }
    }

    @CFunction("CGPathEqualToPath")
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof GraphicsPath)) {
            return false;
        }
        GraphicsPath path2 = (GraphicsPath) other;
        if (elements.size() != path2.elements.size()) {
            return false;
        }
        for (int i = 0; i < elements.size(); i++) {
            if (!elements.get(i).equals(path2.elements.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PathRef{\n");
        sb.append(elements.isEmpty() ? "EMPTY" : elements.get(0));
        for (int i = 1; i < elements.size(); i++) {
            sb.append(",\n").append(elements.get(i));
        }
        sb.append("\n}");
        return sb.toString();
    }

    @CFunction("CGPathGetPathBoundingBox")
    public GraphicsRect getPathBoundingBox() {
        if (pathBoundingBox != null) {
            return pathBoundingBox;
        }
        GraphicsRect pathBounds = CGPathGetPolygonBoundingBox();
        GraphicsRect controlBounds = getBoundingBox();
        float flatness = Math.min(sFlatness, sFlatness * controlBounds.size.width * controlBounds.size.height);
        GraphicsPoint fromPoint = GraphicsPoint.ZERO, toPoint = GraphicsPoint.ZERO;
        for (GraphicsPathElement element : elements) {
            switch (element.type) {
                case MOVE_TO_POINT:
                    toPoint = element.points[0];
                    break;
                case ADD_LINE_TO_POINT:
                case CLOSE_SUBPATH:
                    toPoint = element.points[0];
                    pathBounds = BezierMath.rectUnionWithLine(pathBounds, fromPoint, toPoint);
                    break;
                case ADD_QUAD_CURVE_TO_POINT:
                    toPoint = element.points[1];
                    pathBounds = BezierMath.rectUnionWithQuadCurve(pathBounds, fromPoint, toPoint, element.points[0],
                            flatness);
                    break;
                case ADD_CURVE_TO_POINT:
                    toPoint = element.points[2];
                    pathBounds = BezierMath.rectUnionWithCurve(pathBounds, fromPoint, toPoint, element.points[0],
                            element.points[1], flatness);
                    break;
            }
            fromPoint = toPoint;
        }
        pathBoundingBox = pathBounds;
        return pathBounds;
    }

    @CFunction("CGPathGetPolygonBoundingBox")
    private GraphicsRect CGPathGetPolygonBoundingBox() {
        if (polygonBoundingBox != null) {
            return polygonBoundingBox;
        }
        GraphicsPoint currentPoint = getCurrentPoint();
        GraphicsRect bounds = new GraphicsRect(currentPoint.x, currentPoint.y, 0, 0);
        GraphicsPoint startPoint = GraphicsPoint.ZERO;
        for (GraphicsPathElement element : elements) {
            switch (element.type) {
                case MOVE_TO_POINT:
                    startPoint = element.points[0];
                    break;
                default:
                    if (startPoint != GraphicsPoint.ZERO) {
                        bounds = bounds.unionWithCoordinates(startPoint.x, startPoint.y);
                        startPoint = GraphicsPoint.ZERO;
                    }
                    GraphicsPoint endPoint = element.getEndPoint();
                    bounds = bounds.unionWithCoordinates(endPoint.x, endPoint.y);
            }
        }
        polygonBoundingBox = bounds;
        return new GraphicsRect(bounds);
    }

    @CFunction("CGPathIsRect")
    public boolean isRect(MutableGraphicsRect rect) {
        List<GraphicsPathElement> elements = this.elements;
        if (elements.size() != 5) {
            return false;
        }
        if ((elements.get(0).type != GraphicsPathElementType.MOVE_TO_POINT) || (elements.get(1).type !=
                GraphicsPathElementType.ADD_LINE_TO_POINT) || (elements.get(2).type != GraphicsPathElementType
                .ADD_LINE_TO_POINT) || (elements.get(3).type != GraphicsPathElementType.ADD_LINE_TO_POINT) ||
                (elements.get(4).type != GraphicsPathElementType.CLOSE_SUBPATH)) {
            return false;
        }
        if ((elements.get(0).points[0].y != elements.get(1).points[0].y) || (elements.get(1).points[0].x != elements
                .get(2).points[0].x) || (elements.get(2).points[0].y != elements.get(3).points[0].y) || (elements.get
                (3).points[0].x != elements.get(0).points[0].x)) {
            return false;
        }

        float left = elements.get(0).points[0].x;
        float bottom = elements.get(0).points[0].y;
        float right = elements.get(2).points[0].x;
        float top = elements.get(2).points[0].y;
        rect.origin.x = left;
        rect.origin.y = bottom;
        rect.size.width = right - left;
        rect.size.height = top - bottom;
        return true;
    }

    @CFunction("CGPathContainsPoint")
    public boolean containsPoint(GraphicsAffineTransform m, GraphicsPoint point, boolean eoFill) {
        if (m != null) {
            point = m.transformPoint(point);
        }
        // Some quick test first
        GraphicsRect controlBounds = getBoundingBox();
        if (!controlBounds.contains(point)) {
            return false;
        }
        float flatness = Math.min(sFlatness, sFlatness * controlBounds.size.width * controlBounds.size.height);
        int cn = 0; // the crossing number counter
        GraphicsPoint fromPoint = GraphicsPoint.ZERO, toPoint = GraphicsPoint.ZERO;
        for (GraphicsPathElement element : elements) {
            switch (element.type) {
                case MOVE_TO_POINT:
                    toPoint = element.points[0];
                    break;
                case ADD_LINE_TO_POINT:
                case CLOSE_SUBPATH:
                    toPoint = element.points[0];
                    cn += BezierMath.countLineCrossings(point, fromPoint, toPoint);
                    break;
                case ADD_QUAD_CURVE_TO_POINT:
                    toPoint = element.points[1];
                    cn += BezierMath.countQuadCurveCrossings(point, fromPoint, toPoint, element.points[0], flatness);
                    break;
                case ADD_CURVE_TO_POINT:
                    toPoint = element.points[2];
                    cn += BezierMath.countCurveCrossings(point, fromPoint, toPoint, element.points[0],
                            element.points[1], flatness);
                    break;
            }
            fromPoint = toPoint;
        }
        if (eoFill) {
            return (cn & 1) != 0; // 0 if even (out), and 1 if odd (in)
        } else {
            return cn != 0;
        }
    }

    @CFunction("CGPathGetBoundingBox")
    public GraphicsRect getBoundingBox() {
        if (boundingBox != null) {
            return boundingBox;
        }
        GraphicsPoint currentPoint = getCurrentPoint();
        GraphicsRect bounds = new GraphicsRect(currentPoint.x, currentPoint.y, 0, 0);
        GraphicsPoint fromPoint = GraphicsPoint.ZERO, toPoint = GraphicsPoint.ZERO;
        for (GraphicsPathElement element : elements) {
            switch (element.type) {
                case MOVE_TO_POINT:
                    toPoint = element.points[0];
                    break;
                case ADD_LINE_TO_POINT:
                case CLOSE_SUBPATH:
                    toPoint = element.points[0];
                    bounds = BezierMath.rectUnionWithPoints(bounds, fromPoint, toPoint);
                    break;
                case ADD_QUAD_CURVE_TO_POINT:
                    toPoint = element.points[1];
                    bounds = BezierMath.rectUnionWithPoints(bounds, fromPoint, toPoint, element.points[0]);
                    break;
                case ADD_CURVE_TO_POINT:
                    toPoint = element.points[2];
                    bounds = BezierMath.rectUnionWithPoints(bounds, fromPoint, toPoint, element.points[0],
                            element.points[1]);
                    break;
            }
            fromPoint = toPoint;
        }
        boundingBox = bounds;
        return new GraphicsRect(bounds);
    }

    @CFunction("CGPathGetCurrentPoint")
    public GraphicsPoint getCurrentPoint() {
        if (isEmpty()) {
            return GraphicsPoint.ZERO;
        }
        return elements.get(elements.size() - 1).getEndPoint();
    }

    @CFunction("CGPathIsEmpty")
    public boolean isEmpty() {
        return elements.isEmpty();
    }

    public static interface ApplierFunction {
        void eval(Object info, final GraphicsPathElement element);
    }
}
