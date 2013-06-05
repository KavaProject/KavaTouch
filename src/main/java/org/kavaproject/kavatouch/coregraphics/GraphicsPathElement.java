/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.coregraphics;

import org.kavaproject.kavatouch.internal.CTypedef;
import org.kavaproject.kavatouch.internal.Header;

import java.util.Arrays;

@Header("CGPath")
@CTypedef("CGPathElement")
public class GraphicsPathElement {
    public GraphicsPathElementType type;
    public GraphicsPoint[] points;

    GraphicsPathElement(GraphicsPathElementType type, GraphicsPoint... points) {
        this.type = type;
        this.points = points;
    }

    @Override
    public boolean equals(Object other) {
        return other != null && other instanceof GraphicsPathElement && type == ((GraphicsPathElement) other).type &&
                Arrays.equals(points, ((GraphicsPathElement) other).points);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String comma = "";
        sb.append("Element[").append(type);
        sb.append(", Points:{").append(type);
        for (GraphicsPoint point : points) {
            sb.append(comma).append(point);
            comma = ",";
        }
        sb.append("}]");
        return sb.toString();
    }

    GraphicsPoint getEndPoint() {
        switch (type) {
            case MOVE_TO_POINT:
            case ADD_LINE_TO_POINT:
            case CLOSE_SUBPATH:
                return points[0];
            case ADD_QUAD_CURVE_TO_POINT:
                return points[1];
            case ADD_CURVE_TO_POINT:
                return points[2];
            default:
                throw new IncompatibleClassChangeError();
        }
    }

    GraphicsPathElement applyAffineTransform(GraphicsAffineTransform transform) {
        return new GraphicsPathElement(type, transform.transformPoints(points));
    }

    GraphicsPathElement copy() {
        return new GraphicsPathElement(type, GraphicsPoint.copy(points)); //TODO points.clone() ?
    }
}
