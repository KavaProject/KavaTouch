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
import org.kavaproject.kavatouch.util.NotImplementedException;
import org.kavaproject.kavatouch.util.OutArg;

import java.util.Map;

public class DefaultAttributedString implements AttributedString {
    private final AttributedStringFactory mAttributedStringFactory;

    protected DefaultAttributedString(AttributedStringFactory attributedStringFactory) {
        super();
        this.mAttributedStringFactory = attributedStringFactory;
    }

    protected DefaultAttributedString(Coder decoder, AttributedStringFactory attributedStringFactory) {
        super();
        this.mAttributedStringFactory = attributedStringFactory;
        //TODO
    }

    @Override
    public String value() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public int length() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public Map getAttributes(int index, OutArg<Range> effectiveRange) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public Map getAttributes(int index, OutArg<Range> longestEffectiveRange, Range rangeLimit) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public Object getAttribute(String attributeName, int index, OutArg<Range> effectiveRange) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public Object getAttribute(String attributeName, int index, OutArg<Range> longestEffectiveRange, Range rangeLimit) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public AttributedString substring(Range range) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void enumerateAttribute(String attributeName, Range enumerationRange, AttributedStringEnumerationOptions
            options, AttributedStringCallback block) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void enumerateAttributes(Range enumerationRange, AttributedStringEnumerationOptions options,
                                    AttributedRangeCallback block) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void drawString(GraphicsPoint point) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void drawString(GraphicsRect rect) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void drawString(GraphicsRect rect, StringDrawingOptions options, StringDrawingContext context) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public GraphicsSize size() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public GraphicsRect getBoundingRect(GraphicsSize size, StringDrawingOptions options, StringDrawingContext context) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void encode(Coder encoder) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public AttributedString copy() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public AttributedString mutableCopy() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public AttributedStringFactory getFactory() {
        return mAttributedStringFactory;
    }

    @Override
    public boolean equals(Object other) {
        throw new NotImplementedException(); //TODO
    }
}
