/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.uikit;

import org.apache.http.util.ByteArrayBuffer;
import org.kavaproject.kavatouch.coregraphics.*;
import org.kavaproject.kavatouch.foundation.URL;
import org.kavaproject.kavatouch.util.NotImplementedException;

import javax.inject.Inject;
import java.util.Map;
import java.util.Stack;

public class DefaultUIGraphics implements UIGraphics {
    private Stack<GraphicsContext> mContextStack = new Stack<GraphicsContext>();

    @Inject
    protected DefaultUIGraphics() {
    }

    @Override
    public void pushContext(GraphicsContext context) {
        mContextStack.push(context);
    }

    @Override
    public void endImageContext() {
        GraphicsContext context = getCurrentContext();
        if (!(context instanceof GraphicsBitmapContext)) {
            //empty
        }
        popContext();
    }

    @Override
    public void popContext() {
        mContextStack.pop();
    }

    @Override
    public GraphicsContext getCurrentContext() {
        return mContextStack.peek();
    }

    @Override
    public void rectClip(GraphicsRect rect) {
        GraphicsContext c = getCurrentContext();
        c.clipToRect(rect);
    }

    @Override
    public void rectFill(GraphicsRect rect) {
        rectFill(rect, GraphicsBlendMode.COPY);
    }

    @Override
    public void rectFill(GraphicsRect rect, GraphicsBlendMode blendMode) {
        GraphicsContext c = getCurrentContext();
        c.saveGState();
        c.setBlendMode(blendMode);
        c.fillRect(rect);
        c.restoreGState();
    }

    @Override
    public void rectFrame(GraphicsRect rect) {
        rectFrame(rect, GraphicsBlendMode.COPY);
    }

    @Override
    public void rectFrame(GraphicsRect rect, GraphicsBlendMode blendMode) {
        GraphicsContext c = getCurrentContext();
        c.saveGState();
        c.setBlendMode(blendMode);
        rectFrame(rect);
        c.restoreGState();
    }

    @Override
    public void beginPDFContext(ByteArrayBuffer data, GraphicsRect bounds, Map<String, Object> documentInfo) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public boolean beginPDFContext(String path, GraphicsRect bounds, Map<String, Object> documentInfo) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void endPDFContext() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void beginPDFPage() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void beginPDFPage(GraphicsRect bounds, Map<String, Object> pageInfo) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public GraphicsRect getPDFContextBounds() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void addPDFContextDestination(String name, GraphicsPoint point) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void setPDFContextDestination(String name, GraphicsRect rect) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void setPDFContextURL(URL url, GraphicsRect rect) {
        throw new NotImplementedException(); //TODO
    }
}
