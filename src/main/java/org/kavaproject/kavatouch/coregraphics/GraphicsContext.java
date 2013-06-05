/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.coregraphics;


import android.graphics.*;
import android.util.Log;
import org.kavaproject.kavatouch.coregraphics.staging.*;
import org.kavaproject.kavatouch.internal.CFunction;
import org.kavaproject.kavatouch.internal.Header;
import org.kavaproject.kavatouch.internal.OpaqueType;
import org.kavaproject.kavatouch.uikit.Session;
import org.kavaproject.kavatouch.util.AndroidConversions;
import org.kavaproject.kavatouch.util.FloatUtil;
import org.kavaproject.kavatouch.util.NotImplementedException;

import java.util.Map;
import java.util.Stack;

@Header("CGContext")
@OpaqueType(value = "CGContextRef")
public class GraphicsContext {
    public final Internal internal = new Internal();
    private MutableGraphicsPath path;
    private MutableGraphicsPath clipPath = new MutableGraphicsPath();
    private MutableGraphicsPath eoClipPath = new MutableGraphicsPath();
    private Stack<GraphicsState> stateStack = new Stack<GraphicsState>();
    private GraphicsState state = new GraphicsState();

    protected GraphicsContext(Canvas canvas) {
        GraphicsColorSpace colorSpace = GraphicsColorSpace.createDeviceRGB();
        state.paint = new Paint();
        state.ctm = new GraphicsAffineTransform(1, 0, 0, 1, 0, 0);
        state.rgbFillColor = new GraphicsColor(colorSpace, new float[]{1, 1, 1, 1});
        state.rgbStrokeColor = new GraphicsColor(colorSpace, new float[]{0, 0, 0, 1});
        state.fillColorspace = GraphicsColorSpace.createDeviceRGB();
        state.strokeColorspace = GraphicsColorSpace.createDeviceRGB();
        internal.canvas = canvas;
    }

    @CFunction("CGContextFlush")
    public void flush() {
        throw new NotImplementedException();
    }

    @CFunction("CGContextSynchronize")
    public void synchronize() {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CGContextGetInterpolationQuality")
    public GraphicsInterpolationQuality getInterpolationQuality() {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CGContextSetInterpolationQuality")
    public void setInterpolationQuality(GraphicsInterpolationQuality quality) {
        throw new NotImplementedException();
    }

    @CFunction("CGContextSetFlatness")
    public void setFlatness(float flatness) {
    }

    @CFunction("CGContextSetLineCap")
    public void setLineCap(GraphicsLineCap cap) {
        state.paint.setStrokeCap(cap.androidCap);
    }

    @CFunction("CGContextSetLineDash")
    public void setLineDash(float phase, float[] legths, int count) {
        if (count < 2) {
            state.paint.setPathEffect(null);
        } else {
            state.paint.setPathEffect(new DashPathEffect(legths, phase));
        }
    }

    @CFunction("CGContextSetLineJoin")
    public void setLineJoin(GraphicsLineJoin join) {
        state.paint.setStrokeJoin(join.androidJoin);
    }

    @CFunction("CGContextSetMiterLimit")
    public void setMiterLimit(float limit) {
        state.paint.setStrokeMiter(limit);
    }

    @CFunction("CGContextSetPatternPhase")
    public void setPatternPhase(GraphicsSize phase) {
        throw new NotImplementedException();
    }

    @CFunction("CGContextSetFillPattern")
    public void setFillPattern(GraphicsPattern pattern, float[] components) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CGContextSetRenderingIntent")
    public void setRenderingIntent(GraphicsColorRenderingIntent intent) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CGContextSetShouldAntialias")
    public void setShouldAntialias(boolean shouldAntialias) {
        state.paint.setAntiAlias(shouldAntialias);
    }

    @CFunction("CGContextSetStrokePattern")
    public void setStrokePattern(GraphicsPattern pattern, float[] components) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CGContextSetBlendMode")
    public void setBlendMode(GraphicsBlendMode mode) {
        state.paint.setXfermode(new PorterDuffXfermode(mode.androidMode));
//        context.state.paint.setShader(new BitmapShader(blend, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
    }

    @CFunction("CGContextSetAllowsAntialiasing")
    public void setAllowsAntialiasing(boolean allowsAntialiasing) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CGContextSetAllowsFontSmoothing")
    public void setAllowsFontSmoothing(boolean allowsFontSmoothing) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CGContextSetShouldSmoothFonts")
    public void setShouldSmoothFonts(boolean shouldSmoothFonts) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CGContextSetAllowsFontSubpixelPositioning")
    public void setAllowsFontSubpixelPositioning(boolean allowsFontSubpixelPositioning) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CGContextSetShouldSubpixelPositionFonts")
    public void setShouldSubpixelPositionFonts(boolean shouldSubpixelPositionFonts) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CGContextSetAllowsFontSubpixelQuantization")
    public void setAllowsFontSubpixelQuantization(boolean allowsFontSubpixelQuantization) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CGContextSetShouldSubpixelQuantizeFonts")
    public void setShouldSubpixelQuantizeFonts(boolean shouldSubpixelQuantizeFonts) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CGContextAddArc")
    public void addArc(float x, float y, float radius, float startAngle, float endAngle, int clockwise) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CGContextAddArcToPoint")
    public void addArcToPoint(float x1, float y1, float x2, float y2, float radius) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CGContextAddCurveToPoint")
    public void addCurveToPoint(float cp1x, float cp1y, float cp2x, float cp2y, float x, float y) {
        path.addCurveToPoint(state.ctm, cp1x, cp1y, cp2x, cp2y, x, y);
    }

    @CFunction("CGContextAddLines")
    public void addLines(GraphicsPoint[] points, int count) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CGContextAddLineToPoint")
    public void addLineToPoint(float x, float y) {
        path.addLineToPoint(state.ctm, x, y);
    }

    @CFunction("CGContextAddPath")
    public void addPath(GraphicsPath path) {
        this.path.addPath(state.ctm, path);
    }

    @CFunction("CGContextCopyPath")
    public GraphicsPath copyPath() {
        return new GraphicsPath(path);
    }

    @CFunction("addQuadCurveToPoint")
    public void addQuadCurveToPoint(float cpx, float cpy, float x, float y) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CGContextAddRects")
    public void addRects(GraphicsRect[] rects, int count) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CGContextClosePath")
    public void closePath() {
        path.closeSubpath();
    }

    @CFunction("CGContextMoveToPoint")
    public void moveTo(float x, float y) {
        path.moveToPoint(state.ctm, x, y);
    }

    @CFunction("CGContextAddEllipseInRect")
    public void addEllipseInRect(GraphicsRect rect) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CGContextClearRect")
    public void clearRect(GraphicsRect rect) {
        Paint clearPaint = new Paint();
        clearPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        internal.canvas.drawRect(AndroidConversions.toRectF(rect), clearPaint);
    }

    @CFunction("CGContextDrawPath")
    public void drawPath(GraphicsPathDrawingMode mode) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CGContextEOFillPath")
    public void eoFillPath() {
        state.paint.setStyle(Paint.Style.FILL);
        setPaintColor(state.rgbStrokeColor.getComponents());
        Path androidPath = AndroidConversions.createAndroidPath(path);
        androidPath.setFillType(Path.FillType.EVEN_ODD);
        internal.canvas.drawPath(androidPath, state.paint);
    }

    private void setPaintColor(float[] rgbaComponents) {
        state.paint.setColor(AndroidConversions.toColor(rgbaComponents)); //Was setARGB
    }

    @CFunction("CGContextFillRect")
    public void fillRect(GraphicsRect rect) {
        fillRects(new GraphicsRect[]{rect}, 1);
    }

    @CFunction("CGContextFillRects")
    public void fillRects(GraphicsRect rects[], int count) {
        beginPath();
        for (GraphicsRect rect : rects) {
            addRect(rect);
        }
        fillPath();
        beginPath();
    }

    @CFunction("CGContextFillPath")
    public void fillPath() {
        state.paint.setStyle(Paint.Style.FILL);
        float colorAlpha = state.rgbFillColor.getAlpha();
        setPaintColor(state.rgbFillColor.getComponents());
        setPaintAlpha(state.alpha * colorAlpha);
        Path androidPath = AndroidConversions.createAndroidPath(path);
        androidPath.setFillType(Path.FillType.WINDING);
        internal.canvas.drawPath(androidPath, state.paint);
    }

    private void setPaintAlpha(float alpha) {
        state.paint.setAlpha((int) (FloatUtil.clip(alpha, 0, 1) * 255));
    }

    @CFunction("CGContextBeginPath")
    public void beginPath() {
        path = new MutableGraphicsPath();
    }

    @CFunction("CGContextAddRect")
    public void addRect(GraphicsRect rect) {
        path.addRect(state.ctm, rect);
    }

    @CFunction("CGContextFillEllipseInRect")
    public void fillEllipseInRect(GraphicsRect rect) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CGContextStrokeRectWithWidth")
    public void strokeRect(GraphicsRect rect, float width) {
        saveGState();
        setLineWidth(width);
        strokeRect(rect);
        restoreGState();
    }

    @CFunction("CGContextStrokeRect")
    public void strokeRect(GraphicsRect rect) {
        beginPath();
        addRect(rect);
        strokePath();
        beginPath();
    }

    @CFunction("CGContextStrokePath")
    public void strokePath() {
        state.paint.setStyle(Paint.Style.STROKE);
        float colorAlpha = state.rgbStrokeColor.getAlpha();
        setPaintColor(state.rgbStrokeColor.getComponents());
        setPaintAlpha(state.alpha * colorAlpha);
        Path androidPath = AndroidConversions.createAndroidPath(path);
        internal.canvas.drawPath(androidPath, state.paint);
    }

    @CFunction("CGContextSetLineWidth")
    public void setLineWidth(float width) {
        state.paint.setStrokeWidth(width);
    }

    @CFunction("CGContextRestoreGState")
    public void restoreGState() {
        internal.canvas.restore();
        state = stateStack.pop();
    }

    @CFunction("CGContextSaveGState")
    public void saveGState() {
        internal.canvas.save();
        stateStack.push(state);
        state = GraphicsState.copy(state);
    }

    @CFunction("CGContextReplacePathWithStrokedPath")
    public void replacePathWithStrokedPath() {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CGContextStrokeEllipseInRect")
    public void strokeEllipseInRect(GraphicsRect rect) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CGContextStrokeLineSegments")
    public void strokeLineSegments(GraphicsPoint[] points, int count) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CGContextIsPathEmpty")
    public boolean isPathEmpty() {
        return path.isEmpty();
    }

    @CFunction("CGContextGetPathCurrentPoint")
    public GraphicsPoint getPathCurrentPoint() {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CGContextGetPathBoundingBox")
    public GraphicsRect getPathBoundingBox() {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CGContextPathContainsPoint")
    public boolean pathContainsPoint(GraphicsPoint point, GraphicsPathDrawingMode mode) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CGContextEOClip")
    public void eoClip() {
        if (path == null) {
            return;
        }
        eoClipPath.addPath(GraphicsAffineTransform.IDENTITY(), path); //TODO JIT-clip the canvas
        Path androidPath = AndroidConversions.createAndroidPath(path);
        androidPath.setFillType(Path.FillType.EVEN_ODD);
        internal.canvas.clipPath(androidPath); //TODO We assume that android closes open subpaths,
        // that might not be true.
        beginPath();
    }

    @CFunction("CGContextClipToRects")
    public void clipToRects(GraphicsRect[] rects, int count) {
        for (GraphicsRect rect : rects) {
            clipToRect(rect);
        }
    }

    @CFunction("CGContextClipToRect")
    public void clipToRect(GraphicsRect rect) {
        beginPath();
        addRect(rect);
        clip();
    }

    @CFunction("CGContextClip")
    public void clip() {
        if (path == null) {
            return;
        }
        clipPath.addPath(GraphicsAffineTransform.IDENTITY(), path); //TODO JIT-clip the canvas
        Path androidPath = AndroidConversions.createAndroidPath(path);
        androidPath.setFillType(Path.FillType.WINDING);
        internal.canvas.clipPath(androidPath); //TODO We assume that android closes open subpaths,
        // that might not be true.
        beginPath();
    }

    @CFunction("CGContextGetClipBoundingBox")
    public GraphicsRect getClipBoundingBox() {
        MutableGraphicsPath clipPath = new MutableGraphicsPath(this.clipPath);
        clipPath.addPath(GraphicsAffineTransform.IDENTITY(), eoClipPath);
        GraphicsAffineTransform inverseCTM = state.ctm.invert();
        clipPath = new MutableGraphicsPath(clipPath, inverseCTM);
        return clipPath.getBoundingBox();
    }

    @CFunction("CGContextClipToMask")
    public void clipToMask(GraphicsRect rect, GraphicsImage mask) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CGContextSetAlpha")
    public void setAlpha(float alpha) {
        state.alpha = alpha;
    }

    @CFunction("CGContextSetCMYKFillColor")
    public void setCMYKFillColor(float cyan, float magenta, float yellow, float black, float alpha) {
        GraphicsColorSpace colorSpace = GraphicsColorSpace.createDeviceCMYK();
        setFillColorSpace(colorSpace);
        float[] components = {cyan, magenta, yellow, black, FloatUtil.clip(alpha, 0, 1)};
        setFillColor(new GraphicsColor(colorSpace, components));
    }

    @CFunction("CGContextSetFillColorWithColor")
    public void setFillColor(GraphicsColor color) {
        GraphicsColor rgbColor = color.toRGBAColor();
        state.rgbFillColor = rgbColor;
    }

    @CFunction("CGContextSetFillColorSpace")
    public void setFillColorSpace(GraphicsColorSpace colorspace) {
        state.fillColorspace = colorspace;
        //TODO Assign an appropriate initial value to the fill color
    }

    @CFunction("CGContextSetFillColor")
    public void setFillColor(float[] components) {
        setFillColor(new GraphicsColor(state.fillColorspace, components));
    }

    @CFunction("CGContextSetCMYKStrokeColor")
    public void setCMYKStrokeColor(float cyan, float magenta, float yellow, float black, float alpha) {
        GraphicsColorSpace colorSpace = GraphicsColorSpace.createDeviceCMYK();
        setStrokeColorSpace(colorSpace);
        float[] components = {cyan, magenta, yellow, black, FloatUtil.clip(alpha, 0, 1)};
        setStrokeColor(new GraphicsColor(colorSpace, components));
    }

    @CFunction("CGContextSetStrokeColorWithColor")
    public void setStrokeColor(GraphicsColor color) {
        GraphicsColor rgbColor = color.toRGBAColor();
        state.rgbStrokeColor = rgbColor;
    }

    @CFunction("CGContextSetStrokeColorSpace")
    public void setStrokeColorSpace(GraphicsColorSpace colorspace) {
        state.strokeColorspace = colorspace;
        //TODO Assign an appropriate initial value to the stroke color
    }

    @CFunction("CGContextSetGrayFillColor")
    public void setGrayFillColor(float gray, float alpha) {
        setFillColor(new GraphicsColor(GraphicsColorSpace.createDeviceGray(), new float[]{gray, alpha}));
    }

    @CFunction("CGContextSetGrayStrokeColor")
    public void setGrayStrokeColor(float gray, float alpha) {
        setStrokeColor(new GraphicsColor(GraphicsColorSpace.createDeviceGray(), new float[]{gray, alpha}));
    }

    @CFunction("CGContextSetRGBFillColor")
    public void setRGBFillColor(float red, float green, float blue, float alpha) {
        GraphicsColorSpace colorSpace = GraphicsColorSpace.createDeviceRGB();
        setFillColorSpace(colorSpace);
        float[] components = {red, green, blue, alpha};
        state.rgbFillColor = new GraphicsColor(colorSpace, components);
    }

    @CFunction("CGContextSetRGBStrokeColor")
    public void setRGBStrokeColor(float red, float green, float blue, float alpha) {
        GraphicsColorSpace colorSpace = GraphicsColorSpace.createDeviceRGB();
        setStrokeColorSpace(colorSpace);
        float[] components = {red, green, blue, alpha};
        state.rgbFillColor = new GraphicsColor(colorSpace, components);
    }

    @CFunction("CGContextSetShadow")
    public void setShadow(GraphicsSize offset, float blur) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CGContextSetShadowWithColor")
    public void setShadowWithColor(GraphicsSize offset, float blur, GraphicsColor color) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CGContextSetStrokeColor")
    public void setStrokeColor(float[] components) {
        setStrokeColor(new GraphicsColor(state.strokeColorspace, components));
    }

    @CFunction("CGContextConcatCTM")
    public void concatCTM(GraphicsAffineTransform transform) {
        state.ctm = GraphicsAffineTransform.multiply(transform, state.ctm);
    }

    @CFunction("CGContextGetCTM")
    public GraphicsAffineTransform getCTM() {
        return state.ctm;
    }

    @CFunction("CGContextRotateCTM")
    public void rotateCTM(float angle) {
        state.ctm = state.ctm.rotate(angle);
    }

    @CFunction("CGContextScaleCTM")
    public void scaleCTM(float sx, float sy) {
        state.ctm = state.ctm.scale(sx, sy);
    }

    @CFunction("CGContextTranslateCTM")
    public void translateCTM(float tx, float ty) {
        state.ctm = state.ctm.translate(tx, ty);
    }

    @CFunction("CGContextBeginTransparencyLayer")
    public void beginTransparencyLayer(Map auxiliaryInfo) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CGContextBeginTransparencyLayerWithRect")
    public void beginTransparencyLayer(GraphicsRect rect, Map auxiliaryInfo) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CGContextEndTransparencyLayer")
    public void endTransparencyLayer() {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CGContextDrawTiledImage")
    public void drawTiledImage(GraphicsRect rect, GraphicsImage image) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CGContextDrawImage")
    public void drawImage(GraphicsRect rect, GraphicsImage image) {
        long start;
        if (Session.DEBUG_MEASSURE_CONTEXT_TIME) {
            start = System.nanoTime();
        }
        rect = state.ctm.transformRect(rect);
        Bitmap bitmap = GraphicsImage.getBitmap(image);
        setPaintAlpha(state.alpha);
        internal.canvas.drawBitmap(bitmap, null, AndroidConversions.toRectF(rect), state.paint);
        if (Session.DEBUG_MEASSURE_CONTEXT_TIME) {
            Log.d("KAVA", "Drawing time in ms for " + image + ":" + (float) (System.nanoTime() - start) / 1000000);
        }
    }

    @CFunction("CGContextDrawPDFPage")
    public void drawPDFPage(GraphicsPDFPage page) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CGContextDrawLinearGradient")
    public void drawLinearGradient(GraphicsGradient gradient, GraphicsPoint startPoint, GraphicsPoint endPoint,
                                   GraphicsGradientDrawingOptions options) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CGContextDrawRadialGradient")
    public void drawRadialGradient(GraphicsGradient gradient, GraphicsPoint startCenter, float startRadius,
                                   GraphicsPoint endCenter, float endRadius, GraphicsGradientDrawingOptions options) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CGContextDrawShading")
    public void drawShading(GraphicsShading shading) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CGContextBeginPage")
    public void beginPage(GraphicsRect mediaBox) {
    }

    @CFunction("CGContextEndPage")
    public void endPage() {
    }

    @CFunction("CGContextShowGlyphs")
    public void showGlyphs(GraphicsGlyph[] g, int count) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CGContextShowGlyphsAtPoint")
    public void showGlyphsAtPoint(float x, float y, GraphicsGlyph[] glyphs, int count) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CGContextShowGlyphsWithAdvances")
    public void showGlyphsWithAdvances(GraphicsGlyph[] glyphs, GraphicsSize[] advances, int count) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CGContextShowGlyphsAtPositions")
    public void showGlyphsAtPositions(GraphicsGlyph[] glyphs, GraphicsPoint[] positions, int count) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CGContextGetTextMatrix")
    public GraphicsAffineTransform getTextMatrix() {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CGContextSetTextMatrix")
    public void setTextMatrix(GraphicsAffineTransform t) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CGContextGetTextPosition")
    public GraphicsPoint getTextPosition() {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CGContextSelectFont")
    public void selectFont(String name, float size, GraphicsTextEncoding textEncoding) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CGContextSetCharacterSpacing")
    public void setCharacterSpacing(float spacing) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CGContextSetFont")
    public void setFont(GraphicsFont font) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CGContextSetFontSize")
    public void setFontSize(float size) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CGContextSetTextDrawingMode")
    public void setTextDrawingMode(GraphicsTextDrawingMode mode) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CGContextSetTextPosition")
    public void setTextPosition(float x, float y) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CGContextShowText")
    public void showText(String string, int length) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CGContextShowTextAtPoint")
    public void showTextAtPoint(float x, float y, String string, int length) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CGContextGetUserSpaceToDeviceSpaceTransform")
    public GraphicsAffineTransform getUserSpaceToDeviceSpaceTransform() {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CGContextConvertPointToDeviceSpace")
    public GraphicsPoint convertPointToDeviceSpace(GraphicsPoint point) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CGContextConvertPointToUserSpace")
    public GraphicsPoint convertPointToUserSpace(GraphicsPoint point) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CGContextConvertSizeToDeviceSpace")
    public GraphicsSize convertSizeToDeviceSpace(GraphicsSize size) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CGContextConvertSizeToUserSpace")
    public GraphicsSize convertSizeToUserSpace(GraphicsSize size) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CGContextConvertRectToDeviceSpace")
    public GraphicsRect convertRectToDeviceSpace(GraphicsRect rect) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CGContextConvertRectToUserSpace")
    public GraphicsRect convertRectToUserSpace(GraphicsRect rect) {
        throw new NotImplementedException(); //TODO
    }

    @Header("CGLayer")
    @CFunction(value = "CGContextDrawLayerInRect")
    public void drawLayer(GraphicsRect rect, GraphicsLayer layer) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction(value = "CGContextDrawLayerAtPoint")
    public void drawLayer(GraphicsPoint point, GraphicsLayer layer) {
        throw new NotImplementedException(); //TODO
    }

    public static class Internal {
        public Canvas canvas;

        public static GraphicsContext create(Canvas canvas) {
            return new GraphicsContext(canvas);
        }
    }
}
