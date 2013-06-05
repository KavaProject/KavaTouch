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
import org.kavaproject.kavatouch.foundation.Coder;
import org.kavaproject.kavatouch.util.NotImplementedException;
import org.kavaproject.kavatouch.util.OutArg;

public class DefaultUIBezierPath implements UIBezierPath {
    private final UIBezierPathFactory mUIBezierPathFactory;
    private final UIGraphics mUIGraphics;
    private MutableGraphicsPath mGraphicsPath;
    private float mLineWidth = 1;
    private GraphicsLineCap mLineCapStyle = GraphicsLineCap.BUTT;
    private GraphicsLineJoin mLineJoinStyle = GraphicsLineJoin.MITER;
    private float mFlatness = 0.6F;
    private float[] mLineDashPattern;
    private float mLineDashPhase;
    private float mMiterLimit = 10;
    private boolean mUsesEvenOddFillRule = false;

    protected DefaultUIBezierPath(MutableGraphicsPath bridgedObject, UIBezierPathFactory uiBezierPathFactory,
                                  UIGraphics uiGraphics) {
        mUIBezierPathFactory = uiBezierPathFactory;
        mUIGraphics = uiGraphics;
        mGraphicsPath = bridgedObject;
    }

    @Override
    public UIBezierPath bezierPathByReversingPath() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void move(GraphicsPoint point) {
        mGraphicsPath.moveToPoint(null, point.x, point.y);
    }

    @Override
    public void addLine(GraphicsPoint point) {
        if (isEmpty()) {
            return;
        }
        mGraphicsPath.addLineToPoint(null, point.x, point.y);
    }

    @Override
    public boolean isEmpty() {
        return mGraphicsPath.isEmpty();
    }

    @Override
    public void addArc(GraphicsPoint center, float radius, float startAngle, float endAngle, boolean clockwise) {
        if (isEmpty()) {
            return;
        }
        mGraphicsPath.addArc(null, center.x, center.y, radius, startAngle, endAngle, clockwise);
    }

    @Override
    public void addCurve(GraphicsPoint point, GraphicsPoint controlPoint1, GraphicsPoint controlPoint2) {
        if (isEmpty()) {
            return;
        }
        mGraphicsPath.addCurveToPoint(null, controlPoint1.x, controlPoint1.y, controlPoint2.x, controlPoint2.y,
                point.x, point.y);
    }

    @Override
    public void addQuadCurve(GraphicsPoint point, GraphicsPoint controlPoint) {
        if (isEmpty()) {
            return;
        }
        mGraphicsPath.addQuadCurveToPoint(null, controlPoint.x, controlPoint.y, point.x, point.y);
    }

    @Override
    public void closePath() {
        if (isEmpty()) {
            return;
        }
        mGraphicsPath.closeSubpath();
    }

    @Override
    public void removeAllPoints() {
        mGraphicsPath = new MutableGraphicsPath();
    }

    @Override
    public void append(UIBezierPath aPath) {
        mGraphicsPath.addPath(null, aPath.getGraphicsPath());
    }

    @Override
    public GraphicsPath getGraphicsPath() {
        return new GraphicsPath(mGraphicsPath);
    }

    @Override
    public GraphicsPoint currentPoint() {
        return mGraphicsPath.getCurrentPoint();
    }

    @Override
    public float getLineWidth() {
        return mLineWidth;
    }

    @Override
    public void setLineWidth(float value) {
        mLineWidth = value;
    }

    @Override
    public GraphicsLineCap getLineCapStyle() {
        return mLineCapStyle;
    }

    @Override
    public void setLineCapStyle(GraphicsLineCap value) {
        mLineCapStyle = value;
    }

    @Override
    public GraphicsLineJoin getLineJoinStyle() {
        return mLineJoinStyle;
    }

    @Override
    public void setLineJoinStyle(GraphicsLineJoin value) {
        mLineJoinStyle = value;
    }

    @Override
    public float getMiterLimit() {
        return mMiterLimit;
    }

    @Override
    public void setMiterLimit(float value) {
        mMiterLimit = value;
    }

    @Override
    public float getFlatness() {
        return mFlatness;
    }

    @Override
    public void setFlatness(float value) {
        mFlatness = value;
    }

    @Override
    public boolean getUsesEvenOddFillRule() {
        return mUsesEvenOddFillRule;
    }

    @Override
    public void setUsesEvenOddFillRule(boolean value) {
        mUsesEvenOddFillRule = value;
    }

    @Override
    public void setLineDash(float[] pattern, int count, float phase) {
        mLineDashPattern = new float[count];
        System.arraycopy(pattern, 0, mLineDashPattern, 0, count);
        mLineDashPhase = phase;
    }

    @Override
    public void getLineDash(float[] pattern, OutArg<Integer> count, OutArg<Float> phase) {
        if (pattern != null) {
            System.arraycopy(mLineDashPattern, 0, pattern, 0, mLineDashPattern.length);
        }
        if (count != null) {
            count.value = mLineDashPattern.length;
        }
        if (phase != null) {
            phase.value = mLineDashPhase;
        }
    }

    @Override
    public void fill() {
        GraphicsContext context = mUIGraphics.getCurrentContext();
        context.saveGState();
        setupContext(context);
        context.beginPath();
        context.addPath(mGraphicsPath);
        if (mUsesEvenOddFillRule) {
            context.eoFillPath();
        } else {
            context.fillPath();
        }
        context.restoreGState();
    }

    private void setupContext(GraphicsContext context) {
        context.setFlatness(mFlatness);
        context.setLineCap(mLineCapStyle);
        context.setLineDash(mLineDashPhase, mLineDashPattern, mLineDashPattern != null ? mLineDashPattern.length : 0);
        context.setLineJoin(mLineJoinStyle);
        context.setLineWidth(mLineWidth);
        context.setMiterLimit(mMiterLimit);
    }

    @Override
    public void fill(GraphicsBlendMode blendMode, float alpha) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void stroke() {
        GraphicsContext context = mUIGraphics.getCurrentContext();
        context.saveGState();
        setupContext(context);
        context.beginPath();
        context.addPath(mGraphicsPath);
        context.strokePath();
        context.restoreGState();
    }

    @Override
    public void stroke(GraphicsBlendMode blendMode, float alpha) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void addClip() {
        GraphicsContext context = mUIGraphics.getCurrentContext();
        if (context.isPathEmpty()) context.beginPath();
        context.addPath(mGraphicsPath);
        if (mUsesEvenOddFillRule) {
            context.eoClip();
        } else {
            context.clip();
        }
    }

    @Override
    public boolean contains(GraphicsPoint point) {
        return mGraphicsPath.containsPoint(null, point, mUsesEvenOddFillRule);
    }

    @Override
    public GraphicsRect getBounds() {
        return mGraphicsPath.getBoundingBox();
    }

    @Override
    public void apply(GraphicsAffineTransform transform) {
        mGraphicsPath = new MutableGraphicsPath(mGraphicsPath, transform);
    }

    @Override
    public UIBezierPathFactory getFactory() {
        return mUIBezierPathFactory;
    }

    @Override
    public void encode(Coder encoder) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public UIBezierPath copy() {
        throw new NotImplementedException(); //TODO
    }
}
