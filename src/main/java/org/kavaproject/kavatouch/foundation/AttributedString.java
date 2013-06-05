/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.foundation;

import org.kavaproject.kavatouch.coregraphics.GraphicsPoint;
import org.kavaproject.kavatouch.coregraphics.GraphicsRect;
import org.kavaproject.kavatouch.coregraphics.GraphicsSize;
import org.kavaproject.kavatouch.foundation.staging.StringDrawingContext;
import org.kavaproject.kavatouch.foundation.staging.StringDrawingOptions;
import org.kavaproject.kavatouch.internal.Header;
import org.kavaproject.kavatouch.internal.OccClass;
import org.kavaproject.kavatouch.internal.OccInstanceMethod;
import org.kavaproject.kavatouch.runtime.Creatable;
import org.kavaproject.kavatouch.util.OutArg;

import java.util.Map;

@Header("NSAttributedString")
@OccClass("NSAttributedString")
public interface AttributedString extends Coding, Copying, MutableCopying, Creatable {
    @OccInstanceMethod("string")
    String value();

    @OccInstanceMethod("length")
    int length();

    @OccInstanceMethod("attributesAtIndex:effectiveRange:")
    Map getAttributes(int index, OutArg<Range> effectiveRange);

    @OccInstanceMethod("attributesAtIndex:longestEffectiveRange:inRange:")
    Map getAttributes(int index, OutArg<Range> longestEffectiveRange, Range rangeLimit);

    @OccInstanceMethod("attribute:atIndex:effectiveRange:")
    Object getAttribute(String attributeName, int index, OutArg<Range> effectiveRange);

    @OccInstanceMethod("attribute:atIndex:longestEffectiveRange:inRange:")
    Object getAttribute(String attributeName, int index, OutArg<Range> longestEffectiveRange, Range rangeLimit);

    @OccInstanceMethod("isEqualToAttributedString:")
    @Override
    boolean equals(Object other);

    @OccInstanceMethod("attributedSubstringFromRange:")
    AttributedString substring(Range range);

    @OccInstanceMethod("enumerateAttribute:inRange:options:usingBlock:")
    void enumerateAttribute(String attributeName, Range enumerationRange, AttributedStringEnumerationOptions options,
                            AttributedStringCallback block);

    @OccInstanceMethod("enumerateAttributesInRange:options:usingBlock:")
    void enumerateAttributes(Range enumerationRange, AttributedStringEnumerationOptions options,
                             AttributedRangeCallback block);

    @Header("NSStringDrawing")
    @OccInstanceMethod("drawAtPoint:")
    void drawString(GraphicsPoint point);

    @Header("NSStringDrawing")
    @OccInstanceMethod("drawInRect:")
    void drawString(GraphicsRect rect);

    @Header("NSStringDrawing")
    @OccInstanceMethod("drawWithRect:options:context:")
    void drawString(GraphicsRect rect, StringDrawingOptions options, StringDrawingContext context);

    @Header("NSStringDrawing")
    @OccInstanceMethod("size")
    GraphicsSize size();

    @Header("NSStringDrawing")
    @OccInstanceMethod("boundingRectWithSize:options:context:")
    GraphicsRect getBoundingRect(GraphicsSize size, StringDrawingOptions options, StringDrawingContext context);

    @Override
    void encode(Coder encoder);

    @Override
    AttributedString copy();

    @Override
    AttributedString mutableCopy();

    @Override
    AttributedStringFactory getFactory();
}
