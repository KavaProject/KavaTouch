/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.uikit;

import org.kavaproject.kavatouch.coregraphics.GraphicsPath;
import org.kavaproject.kavatouch.coregraphics.GraphicsPoint;
import org.kavaproject.kavatouch.coregraphics.GraphicsRect;
import org.kavaproject.kavatouch.coregraphics.GraphicsSize;
import org.kavaproject.kavatouch.foundation.CodingFactory;
import org.kavaproject.kavatouch.internal.OccClass;
import org.kavaproject.kavatouch.internal.OccClassMethod;
import org.kavaproject.kavatouch.runtime.Factory;

@OccClass("UIBezierPath")
public interface UIBezierPathFactory extends CodingFactory, Factory {
    @OccClassMethod("bezierPath")
    UIBezierPath create();

    /**
     * aPath.appendBezierPathWithRect(NSRect.makeRect(2.0, 16.0, 8.0, 5.0));
     * <pre>
     * (2,21)         (10,21)
     * ----------------
     * |              |
     * |              |
     * |              |
     * ----------------
     * (2,16)         (10,16)
     * </pre>
     */
    @OccClassMethod("bezierPathWithRect:")
    UIBezierPath rect(GraphicsRect rect);

    @OccClassMethod("bezierPathWithOvalInRect:")
    UIBezierPath oval(GraphicsRect rect);

    @OccClassMethod("bezierPathWithRoundedRect:cornerRadius:")
    UIBezierPath roundedRect(GraphicsRect rect, float cornerRadius);

    @OccClassMethod("bezierPathWithRoundedRect:byRoundingCorners:cornerRadii:")
    UIBezierPath roundedRect(GraphicsRect rect, UIRectCorner roundingCorners, GraphicsSize cornerRadii);

    @OccClassMethod("bezierPathWithArcCenter:radius:startAngle:endAngle:clockwise:")
    UIBezierPath arc(GraphicsPoint center, float radius, float startAngle, float endAngle, boolean clockwise);

    @OccClassMethod("bezierPathWithCGPath:")
    UIBezierPath create(GraphicsPath bridgedObject);
}
