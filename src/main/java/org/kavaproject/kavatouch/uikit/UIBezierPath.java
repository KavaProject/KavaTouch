/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.uikit;

import org.kavaproject.kavatouch.coregraphics.*;
import org.kavaproject.kavatouch.foundation.Coding;
import org.kavaproject.kavatouch.foundation.Copying;
import org.kavaproject.kavatouch.internal.Header;
import org.kavaproject.kavatouch.internal.OccClass;
import org.kavaproject.kavatouch.internal.OccInstanceMethod;
import org.kavaproject.kavatouch.internal.OccInstanceProperty;
import org.kavaproject.kavatouch.runtime.Creatable;
import org.kavaproject.kavatouch.util.OutArg;

@Header("UIBezierPath")
@OccClass("UIBezierPath")
public interface UIBezierPath extends Coding, Copying, Creatable {
    @OccInstanceMethod("bezierPathByReversingPath")
    UIBezierPath bezierPathByReversingPath();

    @OccInstanceMethod("moveToPoint:")
    void move(GraphicsPoint point);

    @OccInstanceMethod("addLineToPoint:")
    void addLine(GraphicsPoint point);

    @OccInstanceProperty("empty")
    boolean isEmpty();

    @OccInstanceMethod("addArcWithCenter:radius:startAngle:endAngle:clockwise:")
    void addArc(GraphicsPoint center, float radius, float startAngle, float endAngle, boolean clockwise);

    @OccInstanceMethod("addCurveToPoint:controlPoint1:controlPoint2:")
    void addCurve(GraphicsPoint point, GraphicsPoint controlPoint1, GraphicsPoint controlPoint2);

    @OccInstanceMethod("addQuadCurveToPoint:controlPoint:")
    void addQuadCurve(GraphicsPoint point, GraphicsPoint controlPoint);

    @OccInstanceMethod("closePath")
    void closePath();

    @OccInstanceMethod("removeAllPoints")
    void removeAllPoints();

    @OccInstanceMethod("appendPath:")
    void append(UIBezierPath aPath);

    @OccInstanceProperty("cgPath")
    GraphicsPath getGraphicsPath();

    @OccInstanceProperty("currentPoint")
    GraphicsPoint currentPoint();

    @OccInstanceProperty("lineWidth")
    float getLineWidth();

    @OccInstanceProperty("lineWidth")
    void setLineWidth(float value);

    @OccInstanceProperty("lineCapStyle")
    GraphicsLineCap getLineCapStyle();

    @OccInstanceProperty("lineCapStyle")
    void setLineCapStyle(GraphicsLineCap value);

    @OccInstanceProperty("lineJoinStyle")
    GraphicsLineJoin getLineJoinStyle();

    @OccInstanceProperty("lineJoinStyle")
    void setLineJoinStyle(GraphicsLineJoin value);

    @OccInstanceProperty("miterLimit")
    float getMiterLimit();

    @OccInstanceProperty("miterLimit")
    void setMiterLimit(float value);

    @OccInstanceProperty("flatness")
    float getFlatness();

    @OccInstanceProperty("flatness")
    void setFlatness(float value);

    @OccInstanceProperty("usesEvenOddFillRule")
    boolean getUsesEvenOddFillRule();

    @OccInstanceProperty("usesEvenOddFillRule")
    void setUsesEvenOddFillRule(boolean value);

    @OccInstanceMethod("setLineDash:count:phase:")
    void setLineDash(float[] pattern, int count, float phase);

    @OccInstanceMethod("getLineDash:count:phase:")
    void getLineDash(float[] pattern, OutArg<Integer> count, OutArg<Float> phase);

    @OccInstanceMethod("fill")
    void fill();

    @OccInstanceMethod("fillWithBlendMode:alpha:")
    void fill(GraphicsBlendMode blendMode, float alpha);

    @OccInstanceMethod("stroke")
    void stroke();

    @OccInstanceMethod("strokeWithBlendMode:alpha:")
    void stroke(GraphicsBlendMode blendMode, float alpha);

    @OccInstanceMethod("addClip")
    void addClip();

    @OccInstanceMethod("containsPoint:")
    boolean contains(GraphicsPoint point);

    @OccInstanceProperty("bounds")
    GraphicsRect getBounds();

    @OccInstanceMethod("applyTransform:")
    void apply(GraphicsAffineTransform transform);

    @Override
    UIBezierPathFactory getFactory();
}
