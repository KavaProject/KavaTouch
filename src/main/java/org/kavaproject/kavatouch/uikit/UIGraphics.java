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
import org.kavaproject.kavatouch.coregraphics.GraphicsBlendMode;
import org.kavaproject.kavatouch.coregraphics.GraphicsContext;
import org.kavaproject.kavatouch.coregraphics.GraphicsPoint;
import org.kavaproject.kavatouch.coregraphics.GraphicsRect;
import org.kavaproject.kavatouch.foundation.URL;
import org.kavaproject.kavatouch.internal.CFunction;
import org.kavaproject.kavatouch.internal.Header;

import java.util.Map;

@Header("UIGraphics")
public interface UIGraphics {
    @CFunction("UIGraphicsPushContext")
    void pushContext(GraphicsContext context);

    @CFunction("UIGraphicsEndImageContext")
    void endImageContext();

    @CFunction("UIGraphicsPopContext")
    void popContext();

    @CFunction("UIGraphicsGetCurrentContext")
    GraphicsContext getCurrentContext();

    @CFunction("UIRectClip")
    void rectClip(GraphicsRect rect);

    @CFunction("UIRectFill")
    void rectFill(GraphicsRect rect);

    @CFunction("UIRectFillUsingBlendMode")
    void rectFill(GraphicsRect rect, GraphicsBlendMode blendMode);

    @CFunction("UIRectFrame")
    void rectFrame(GraphicsRect rect);

    @CFunction("UIRectFrameUsingBlendMode")
    void rectFrame(GraphicsRect rect, GraphicsBlendMode blendMode);

    @CFunction("UIGraphicsBeginPDFContextToData")
    void beginPDFContext(ByteArrayBuffer data, GraphicsRect bounds, Map<String, Object> documentInfo);

    @CFunction("UIGraphicsBeginPDFContextToFile")
    boolean beginPDFContext(String path, GraphicsRect bounds, Map<String, Object> documentInfo);

    @CFunction("UIGraphicsEndPDFContext")
    void endPDFContext();

    @CFunction("UIGraphicsBeginPDFPage")
    void beginPDFPage();

    @CFunction("UIGraphicsBeginPDFPageWithInfo")
    void beginPDFPage(GraphicsRect bounds, Map<String, Object> pageInfo);

    @CFunction("UIGraphicsGetPDFContextBounds")
    GraphicsRect getPDFContextBounds();

    @CFunction("UIGraphicsAddPDFContextDestinationAtPoint")
    void addPDFContextDestination(String name, GraphicsPoint point);

    @CFunction("UIGraphicsSetPDFContextDestinationForRect")
    void setPDFContextDestination(String name, GraphicsRect rect);

    @CFunction("UIGraphicsSetPDFContextURLForRect")
    void setPDFContextURL(URL url, GraphicsRect rect);
}
