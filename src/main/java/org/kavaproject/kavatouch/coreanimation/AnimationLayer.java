/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.coreanimation;

import org.kavaproject.kavatouch.coreanimation.staging.*;
import org.kavaproject.kavatouch.coregraphics.*;
import org.kavaproject.kavatouch.foundation.Coding;
import org.kavaproject.kavatouch.internal.*;
import org.kavaproject.kavatouch.runtime.Creatable;

import java.util.EnumSet;
import java.util.List;
import java.util.Map;

@Header("CALayer")
@OccClass("CALayer")
public interface AnimationLayer extends Coding, AnimationMediaTiming, AnimationLayerInternal, Creatable {
    @OccInstanceMethod("presentationLayer")
    AnimationLayer presentationLayer();

    @OccInstanceMethod("modelLayer")
    AnimationLayer modelLayer();

    @OccInstanceProperty(value = "delegate", argumentSemantic = ArgumentSemantic.ASSIGN)
    AnimationLayerDelegate getDelegate();

    @OccInstanceProperty(value = "delegate", argumentSemantic = ArgumentSemantic.ASSIGN)
    void setDelegate(AnimationLayerDelegate value);

    @OccInstanceProperty(value = "contents", argumentSemantic = ArgumentSemantic.RETAIN)
    GraphicsImage getContents();

    @OccInstanceProperty(value = "contents", argumentSemantic = ArgumentSemantic.RETAIN)
    void setContents(GraphicsImage value);

    @OccInstanceProperty("contentsRect")
    GraphicsRect getContentsRect();

    @OccInstanceProperty("contentsRect")
    void setContentsRect(GraphicsRect value);

    @OccInstanceProperty("contentsCenter")
    GraphicsRect getContentsCenter();

    @OccInstanceProperty("contentsCenter")
    void setContentsCenter(GraphicsRect value);

    @OccInstanceProperty(value = "contentsGravity", argumentSemantic = ArgumentSemantic.COPY)
    AnimationContentsGravity getContentsGravity();

    @OccInstanceProperty(value = "contentsGravity", argumentSemantic = ArgumentSemantic.COPY)
    void setContentsGravity(AnimationContentsGravity value);

    @OccInstanceProperty("opacity")
    float getOpacity();

    @OccInstanceProperty("opacity")
    void setOpacity(float value);

    @OccInstanceProperty("hidden")
    boolean isHidden();

    @OccInstanceProperty("hidden")
    void setHidden(boolean value);

    @OccInstanceProperty("masksToBounds")
    boolean getMasksToBounds();

    @OccInstanceProperty("masksToBounds")
    void setMasksToBounds(boolean value);

    @OccInstanceProperty(value = "mask", argumentSemantic = ArgumentSemantic.RETAIN)
    AnimationLayer getMask();

    @OccInstanceProperty(value = "mask", argumentSemantic = ArgumentSemantic.RETAIN)
    void setMask(AnimationLayer value);

    @OccInstanceProperty("doubleSided")
    boolean isDoubleSided();

    @OccInstanceProperty("doubleSided")
    void setDoubleSided(boolean value);

    @OccInstanceProperty("cornerRadius")
    float getCornerRadius();

    @OccInstanceProperty("cornerRadius")
    void setCornerRadius(float value);

    @OccInstanceProperty("borderWidth")
    float getBorderWidth();

    @OccInstanceProperty("borderWidth")
    void setBorderWidth(float value);

    @OccInstanceProperty("borderColor")
    GraphicsColor getBorderColor();

    @OccInstanceProperty("borderColor")
    void setBorderColor(GraphicsColor value);

    @OccInstanceProperty("backgroundColor")
    GraphicsColor getBackgroundColor();

    @OccInstanceProperty("backgroundColor")
    void setBackgroundColor(GraphicsColor value);

    @OccInstanceProperty("shadowOpacity")
    float getShadowOpacity();

    @OccInstanceProperty("shadowOpacity")
    void setShadowOpacity(float value);

    @OccInstanceProperty("shadowRadius")
    float getShadowRadius();

    @OccInstanceProperty("shadowRadius")
    void setShadowRadius(float value);

    @OccInstanceProperty("shadowOffset")
    GraphicsSize getShadowOffset();

    @OccInstanceProperty("shadowOffset")
    void setShadowOffset(GraphicsSize value);

    @OccInstanceProperty("shadowColor")
    GraphicsColor getShadowColor();

    @OccInstanceProperty("shadowColor")
    void setShadowColor(GraphicsColor value);

    @OccInstanceProperty("shadowPath")
    GraphicsPath getShadowPath();

    @OccInstanceProperty("shadowPath")
    void setShadowPath(GraphicsPath value);

    @OccInstanceProperty(value = "style", argumentSemantic = ArgumentSemantic.COPY)
    Map<String, Object> getStyle();

    @OccInstanceProperty(value = "style", argumentSemantic = ArgumentSemantic.COPY)
    void setStyle(Map<String, Object> value);

    @OccInstanceProperty(value = "filters", argumentSemantic = ArgumentSemantic.COPY)
    List<ImageFilter> getFilters();

    @OccInstanceProperty(value = "filters", argumentSemantic = ArgumentSemantic.COPY)
    void setFilters(List<ImageFilter> value);

    @OccInstanceProperty(value = "compositingFilter", argumentSemantic = ArgumentSemantic.RETAIN)
    ImageFilter getCompositingFilter();

    @OccInstanceProperty(value = "compositingFilter", argumentSemantic = ArgumentSemantic.RETAIN)
    void setCompositingFilter(ImageFilter value);

    @OccInstanceProperty(value = "backgroundFilters", argumentSemantic = ArgumentSemantic.COPY)
    List<ImageFilter> getBackgroundFilters();

    @OccInstanceProperty(value = "backgroundFilters", argumentSemantic = ArgumentSemantic.COPY)
    void setBackgroundFilters(List<ImageFilter> value);

    @OccInstanceProperty(value = "minificationFilter", argumentSemantic = ArgumentSemantic.COPY)
    String getMinificationFilter();

    @OccInstanceProperty(value = "minificationFilter", argumentSemantic = ArgumentSemantic.COPY)
    void setMinificationFilter(String value);

    @OccInstanceProperty("minificationFilterBias")
    float getMinificationFilterBias();

    @OccInstanceProperty("minificationFilterBias")
    void setMinificationFilterBias(float value);

    @OccInstanceProperty(value = "magnificationFilter", argumentSemantic = ArgumentSemantic.COPY)
    String getMagnificationFilter();

    @OccInstanceProperty(value = "magnificationFilter", argumentSemantic = ArgumentSemantic.COPY)
    void setMagnificationFilter(String value);

    @OccInstanceProperty("opaque")
    boolean isOpaque();

    @OccInstanceProperty("opaque")
    void setOpaque(boolean value);

    @OccInstanceProperty("edgeAntialiasingMask")
    EnumSet<AnimationEdgeAntialiasingMask> getEdgeAntialiasingMask();

    @OccInstanceProperty("edgeAntialiasingMask")
    void setEdgeAntialiasingMask(EnumSet<AnimationEdgeAntialiasingMask> value);

    @OccInstanceMethod("contentsAreFlipped")
    boolean getContentsAreFlipped();

    @OccInstanceProperty("geometryFlipped")
    boolean isGeometryFlipped();

    @OccInstanceProperty("geometryFlipped")
    void setGeometryFlipped(boolean value);

    @OccInstanceProperty("drawsAsynchronously")
    boolean getDrawsAsynchronously();

    @OccInstanceProperty("drawsAsynchronously")
    void setDrawsAsynchronously(boolean value);

    @OccInstanceProperty("shouldRasterize")
    boolean getShouldRasterize();

    @OccInstanceProperty("shouldRasterize")
    void setShouldRasterize(boolean value);

    @OccInstanceProperty("rasterizationScale")
    float getRasterizationScale();

    @OccInstanceProperty("rasterizationScale")
    void setRasterizationScale(float value);

    @OccInstanceMethod("renderInContext:")
    void render(GraphicsContext context);

    @OccInstanceProperty("frame")
    GraphicsRect getFrame();

    @OccInstanceProperty("frame")
    void setFrame(GraphicsRect value);

    @OccInstanceMethod("affineTransform")
    GraphicsAffineTransform affineTransform();

    @OccInstanceProperty("transform")
    AnimationTransform3D getTransform();

    @OccInstanceProperty("transform")
    void setTransform(AnimationTransform3D value);

    @OccInstanceProperty("position")
    GraphicsPoint getPosition();

    @OccInstanceProperty("position")
    void setPosition(GraphicsPoint value);

    @OccInstanceProperty("zPosition")
    float getZPosition();

    @OccInstanceProperty("zPosition")
    void setZPosition(float value);

    @OccInstanceProperty("anchorPointZ")
    float getAnchorPointZ();

    @OccInstanceProperty("anchorPointZ")
    void setAnchorPointZ(float value);

    @OccInstanceProperty("anchorPoint")
    GraphicsPoint getAnchorPoint();

    @OccInstanceProperty("anchorPoint")
    void setAnchorPoint(GraphicsPoint value);

    @OccInstanceProperty("sublayerTransform")
    AnimationTransform3D getSublayerTransform();

    @OccInstanceProperty("sublayerTransform")
    void setSublayerTransform(AnimationTransform3D value);

    @OccInstanceMethod("setAffineTransform:")
    void setAffineTransform(GraphicsAffineTransform transform);

    @OccInstanceMethod(value = "addSublayer:")
    void addSublayer(AnimationLayer layer);

    @OccInstanceMethod("insertSublayer:atIndex:")
    void insertSublayer(AnimationLayer layer, int index);

    @OccInstanceMethod("insertSublayer:below:")
    void insertSublayerBelow(AnimationLayer newLayer, AnimationLayer existingSublayer);

    @OccInstanceMethod("insertSublayer:above:")
    void insertSublayerAbove(AnimationLayer newLayer, AnimationLayer existingSublayer);

    @OccInstanceMethod("replaceSublayer:with:")
    void replaceSublayer(AnimationLayer oldLayer, AnimationLayer newLayer);

    @OccInstanceMethod("removeFromSuperlayer")
    void removeFromSuperlayer();

    @OccInstanceProperty(value = "sublayers", argumentSemantic = ArgumentSemantic.COPY)
    List<AnimationLayer> getSublayers();

    @OccInstanceProperty(value = "sublayers", argumentSemantic = ArgumentSemantic.COPY)
    void setSublayers(List<AnimationLayer> value);

    @OccInstanceMethod(value = "setNeedsDisplayInRect:")
    void setNeedsDisplayInRect(GraphicsRect rect);

    @OccInstanceProperty("needsDisplayOnBoundsChange")
    boolean getNeedsDisplayOnBoundsChange();

    @OccInstanceProperty("needsDisplayOnBoundsChange")
    void setNeedsDisplayOnBoundsChange(boolean value);

    @OccInstanceMethod("displayIfNeeded")
    void displayIfNeeded();

    @OccInstanceMethod("display")
    void display();

    @OccInstanceProperty("contentsScale")
    float getContentsScale();

    @OccInstanceProperty("contentsScale")
    void setContentsScale(float value);

    @OccInstanceProperty("bounds")
    GraphicsRect getBounds();

    @OccInstanceProperty("bounds")
    void setBounds(GraphicsRect value);

    @OccInstanceMethod("setNeedsLayout")
    void setNeedsLayout();

    @OccInstanceMethod("setNeedsDisplay")
    void setNeedsDisplay();

    @OccInstanceMethod("drawInContext:")
    void drawInContext(GraphicsContext context);

    @OccInstanceMethod("needsDisplay")
    boolean needsDisplay();

    @OccInstanceMethod("addAnimation:forKey:")
    void addAnimation(Animation animation, String key);

    @OccInstanceMethod("animationForKey:")
    Animation animationForKey(String key);

    @OccInstanceMethod("removeAllAnimations")
    void removeAllAnimations();

    @OccInstanceMethod("removeAnimationForKey:")
    void removeAnimation(String key);

    @OccInstanceMethod("animationKeys")
    List<String> animationKeys();

    @OccInstanceMethod("layoutIfNeeded")
    void layoutIfNeeded();

    @OccInstanceMethod("needsLayout")
    boolean needsLayout();

    @OccInstanceMethod("layoutSublayers")
    void layoutSublayers();

    @OccInstanceProperty("superlayer")
    AnimationLayer getSuperlayer();

    @OccInstanceMethod("preferredFrameSize")
    GraphicsSize preferredFrameSize();

    @OccInstanceMethod("actionForKey:")
    AnimationAction actionForKey(String key);

    @OccInstanceProperty(value = "actions", argumentSemantic = ArgumentSemantic.COPY)
    Map<String, AnimationAction> getActions();

    void setActions(Map<String, AnimationAction> value);

    @OccInstanceMethod("convertPoint:fromLayer:")
    GraphicsPoint convertPointFromLayer(GraphicsPoint point, AnimationLayer layer);

    @OccInstanceMethod("convertPoint:toLayer:")
    GraphicsPoint convertPointToLayer(GraphicsPoint point, AnimationLayer layer);

    @OccInstanceMethod("convertRect:fromLayer:")
    GraphicsRect convertRectFromLayer(GraphicsRect rect, AnimationLayer layer);

    @OccInstanceMethod("convertRect:toLayer:")
    GraphicsRect convertRectToLayer(GraphicsRect rect, AnimationLayer layer);

    @OccInstanceMethod("convertTime:fromLayer:")
    double convertTimeFromLayer(double seconds, AnimationLayer layer);

    @OccInstanceMethod("convertTime:toLayer:")
    double convertTimeToLayer(double seconds, AnimationLayer layer);

    @OccInstanceMethod(value = "hitTest:")
    AnimationLayer hitTest(GraphicsPoint point);

    @OccInstanceMethod(value = "containsPoint:")
    boolean contains(GraphicsPoint point);

    @OccInstanceProperty("visibleRect")
    GraphicsRect getVisibleRect();

    @OccInstanceMethod("scrollPoint:")
    void scrollPoint(GraphicsPoint point);

    @OccInstanceMethod("scrollRectToVisible:")
    void scrollRectToVisible(GraphicsRect rect);

    @OccInstanceMethod("shouldArchiveValueForKey:")
    boolean shouldArchiveValue(String key);

    @OccInstanceProperty(value = "name", argumentSemantic = ArgumentSemantic.COPY)
    String getName();

    @OccInstanceProperty(value = "name", argumentSemantic = ArgumentSemantic.COPY)
    void setName(String value);

    @OccInstanceMethod(value = "initWithLayer:")
    void update(AnimationLayer layer);

    @Override
    AnimationLayerFactory getFactory();
}
