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

import javax.inject.Inject;

public class DefaultUIBezierPathFactory implements UIBezierPathFactory {
    private final UIGraphics mUIGraphics;

    @Inject
    protected DefaultUIBezierPathFactory(UIGraphics uiGraphics) {
        mUIGraphics = uiGraphics;
    }

    @Override
    public UIBezierPath create() {
        return new DefaultUIBezierPath(new MutableGraphicsPath(), this, mUIGraphics);
    }

    @Override
    public UIBezierPath rect(GraphicsRect rect) {
        MutableGraphicsPath cgPath = new MutableGraphicsPath();
        cgPath.addRect(null, rect);
        return new DefaultUIBezierPath(cgPath, this, mUIGraphics);
    }

    @Override
    public UIBezierPath oval(GraphicsRect rect) {
        MutableGraphicsPath cgPath = new MutableGraphicsPath();
        cgPath.addEllipseInRect(null, rect);
        return new DefaultUIBezierPath(cgPath, this, mUIGraphics);
    }

    @Override
    public UIBezierPath roundedRect(GraphicsRect rect, float cornerRadius) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public UIBezierPath roundedRect(GraphicsRect rect, UIRectCorner roundingCorners, GraphicsSize cornerRadii) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public UIBezierPath arc(GraphicsPoint center, float radius, float startAngle, float endAngle, boolean clockwise) {
        MutableGraphicsPath cgPath = new MutableGraphicsPath();
        cgPath.addArc(null, center.x, center.y, radius, startAngle, endAngle, clockwise);
        return new DefaultUIBezierPath(cgPath, this, mUIGraphics);
    }

    @Override
    public UIBezierPath create(GraphicsPath bridgedObject) {
        return new DefaultUIBezierPath(new MutableGraphicsPath(bridgedObject), this, mUIGraphics);
    }

    @Override
    public UIBezierPath create(Coder decoder) {
        throw new NotImplementedException(); //TODO
    }
}
