/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.uikit;

import org.kavaproject.kavatouch.coreanimation.AnimationLayer;
import org.kavaproject.kavatouch.coreanimation.AnimationLayerDelegate;
import org.kavaproject.kavatouch.coreanimation.staging.AnimationAction;
import org.kavaproject.kavatouch.coregraphics.*;
import org.kavaproject.kavatouch.foundation.Coder;
import org.kavaproject.kavatouch.foundation.Coding;
import org.kavaproject.kavatouch.internal.*;
import org.kavaproject.kavatouch.runtime.Creatable;
import org.kavaproject.kavatouch.uikit.staging.*;

import java.util.EnumSet;
import java.util.List;
import java.util.Set;

@Header("UIView")
@OccClass("UIView")
public interface UIView extends UIResponder, AnimationLayerDelegate, Coding, Creatable, UIViewInternal {
    @OccInstanceMethod("setNeedsDisplay")
    void setNeedsDisplay();

    @OccInstanceProperty(value = "backgroundColor", argumentSemantic = ArgumentSemantic.COPY)
    UIColor getBackgroundColor();

    @OccInstanceProperty(value = "backgroundColor", argumentSemantic = ArgumentSemantic.COPY)
    void setBackgroundColor(UIColor value);

    @OccInstanceProperty("hidden")
    boolean isHidden();

    @OccInstanceProperty("hidden")
    void setHidden(boolean value);

    @OccInstanceProperty("alpha")
    float getAlpha();

    @OccInstanceProperty("alpha")
    void setAlpha(float value);

    @OccInstanceProperty("opaque")
    boolean isOpaque();

    @OccInstanceProperty("opaque")
    void setOpaque(boolean value);

    @OccInstanceProperty("clipsToBounds")
    boolean getClipsToBounds();

    @OccInstanceProperty("clipsToBounds")
    void setClipsToBounds(boolean value);

    @OccInstanceProperty("clearsContextBeforeDrawing")
    boolean getClearsContextBeforeDrawing();

    @OccInstanceProperty("clearsContextBeforeDrawing")
    void setClearsContextBeforeDrawing(boolean value);

    @OccInstanceProperty("userInteractionEnabled")
    boolean isUserInteractionEnabled();

    @OccInstanceProperty("userInteractionEnabled")
    void setUserInteractionEnabled(boolean value);

    @OccInstanceProperty("multipleTouchEnabled")
    boolean isMultipleTouchEnabled();

    @OccInstanceProperty("multipleTouchEnabled")
    void setMultipleTouchEnabled(boolean value);

    @OccInstanceProperty("exclusiveTouch")
    boolean isExclusiveTouch();

    @OccInstanceProperty("exclusiveTouch")
    void setExclusiveTouch(boolean value);

    @OccInstanceProperty("frame")
    GraphicsRect getFrame();

    @OccInstanceProperty("frame")
    void setFrame(GraphicsRect value);

    @OccInstanceProperty("center")
    GraphicsPoint getCenter();

    @OccInstanceProperty("center")
    void setCenter(GraphicsPoint value);

    @OccInstanceProperty("transform")
    GraphicsAffineTransform getTransform();

    @OccInstanceProperty("transform")
    void setTransform(GraphicsAffineTransform value);

    @OccInstanceProperty(value = "subviews", argumentSemantic = ArgumentSemantic.COPY)
    List<UIView> getSubviews();

    @OccInstanceProperty("window")
    UIWindow getWindow();

    @OccInstanceMethod("addSubview:")
    void addSubview(UIView view);

    @OccInstanceMethod("bringSubviewToFront:")
    void bringSubviewToFront(UIView view);

    @OccInstanceProperty(value = "layer", argumentSemantic = ArgumentSemantic.RETAIN)
    AnimationLayer getLayer();

    @OccInstanceMethod("sendSubviewToBack:")
    void sendSubviewToBack(UIView view);

    @OccInstanceMethod("removeFromSuperview")
    void removeFromSuperview();

    @OccInstanceMethod("insertSubview:atIndex:")
    void insertSubview(UIView view, int index);

    @OccInstanceMethod("insertSubview:aboveSubview:")
    void insertSubviewAbove(UIView newView, UIView subview);

    @OccInstanceMethod("insertSubview:belowSubview:")
    void insertSubviewBelow(UIView newView, UIView subview);

    @OccInstanceMethod("exchangeSubviewAtIndex:withSubviewAtIndex:")
    void exchangeSubview(int index1, int index2);

    @OccInstanceMethod("isDescendantOfView:")
    boolean isDescendantOfView(UIView view);

    @OccInstanceProperty("superview")
    UIView getSuperview();

    @OccInstanceProperty("autoresizingMask")
    EnumSet<UIViewAutoresizing> getAutoresizingMask();

    @OccInstanceProperty("autoresizingMask")
    void setAutoresizingMask(EnumSet<UIViewAutoresizing> value);

    @OccInstanceProperty("autoresizesSubviews")
    boolean getAutoresizesSubviews();

    @OccInstanceProperty("autoresizesSubviews")
    void setAutoresizesSubviews(boolean value);

    @OccInstanceProperty("contentMode")
    UIViewContentMode getContentMode();

    @OccInstanceProperty("contentMode")
    void setContentMode(UIViewContentMode value);

    @OccInstanceMethod("sizeThatFits:")
    GraphicsSize sizeThatFits(GraphicsSize size);

    @OccInstanceMethod("sizeToFit")
    void sizeToFit();

    @OccInstanceProperty("contentStretch")
    @Deprecated
    GraphicsRect getContentStretch();

    @OccInstanceProperty("contentStretch")
    @Deprecated
    void setContentStretch(GraphicsRect value);

    @OccInstanceMethod("layoutSubviews")
    void layoutSubviews();

    @OccInstanceMethod("setNeedsLayout")
    void setNeedsLayout();

    @OccInstanceMethod("layoutIfNeeded")
    void layoutIfNeeded();

    @OccInstanceMethod("translatesAutoresizingMaskIntoConstraints")
    boolean translatesAutoresizingMaskIntoConstraints();

    @OccInstanceMethod("setTranslatesAutoresizingMaskIntoConstraints:")
    void setTranslatesAutoresizingMaskIntoConstraints(boolean value);

    @OccInstanceMethod("constraints")
    List<LayoutConstraint> constraints();

    @OccInstanceMethod("addConstraint:")
    void addConstraint(LayoutConstraint constraint);

    @OccInstanceMethod("addConstraints:")
    void addConstraints(List<LayoutConstraint> constraints);

    @OccInstanceMethod("removeConstraint:")
    void removeConstraint(LayoutConstraint constraint);

    @OccInstanceMethod("removeConstraints:")
    void removeConstraints(List<LayoutConstraint> constraints);

    @OccInstanceMethod("systemLayoutSizeFittingSize:")
    GraphicsSize systemLayoutSizeFittingSize(GraphicsSize targetSize);

    @OccInstanceMethod("intrinsicContentSize")
    GraphicsSize intrinsicContentSize();

    @OccInstanceMethod("invalidateIntrinsicContentSize")
    void invalidateIntrinsicContentSize();

    @OccInstanceMethod("contentCompressionResistancePriorityForAxis:")
    UILayoutPriority contentCompressionResistancePriority(UILayoutConstraintAxis axis);

    @OccInstanceMethod("setContentCompressionResistancePriority:forAxis:")
    void setContentCompressionResistancePriority(UILayoutPriority priority, UILayoutConstraintAxis axis);

    @OccInstanceMethod("contentHuggingPriorityForAxis:")
    UILayoutPriority contentHuggingPriority(UILayoutConstraintAxis axis);

    @OccInstanceMethod("setContentHuggingPriority:forAxis:")
    void setContentHuggingPriority(UILayoutPriority priority, UILayoutConstraintAxis axis);

    @OccInstanceMethod("alignmentRectForFrame:")
    GraphicsRect alignmentRectForFrame(GraphicsRect frame);

    @OccInstanceMethod("frameForAlignmentRect:")
    GraphicsRect frameForAlignmentRect(GraphicsRect alignmentRect);

    @OccInstanceMethod("alignmentRectInsets")
    UIEdgeInsets alignmentRectInsets();

    @OccInstanceMethod("viewForBaselineLayout")
    UIView viewForBaselineLayout();

    @OccInstanceMethod("needsUpdateConstraints")
    boolean needsUpdateConstraints();

    @OccInstanceMethod("setNeedsUpdateConstraints")
    void setNeedsUpdateConstraints();

    @OccInstanceMethod("updateConstraints")
    void updateConstraints();

    @OccInstanceMethod("updateConstraintsIfNeeded")
    void updateConstraintsIfNeeded();

    @OccInstanceMethod("constraintsAffectingLayoutForAxis:")
    List<LayoutConstraint> getConstraintsAffectingLayout(UILayoutConstraintAxis axis);

    @OccInstanceMethod("hasAmbiguousLayout")
    boolean hasAmbiguousLayout();

    @OccInstanceMethod("exerciseAmbiguityInLayout")
    void exerciseAmbiguityInLayout();

    @OccInstanceMethod("setNeedsDisplayInRect:")
    void setNeedsDisplayInRect(GraphicsRect rect);

    @OccInstanceMethod("viewPrintFormatter")
    UIViewPrintFormatter viewPrintFormatter();

    @OccInstanceMethod("drawRect:forViewPrintFormatter:")
    void draw(GraphicsRect area, UIViewPrintFormatter formatter);

    @OccInstanceProperty(value = "gestureRecognizers", argumentSemantic = ArgumentSemantic.COPY)
    List<UIGestureRecognizer> getGestureRecognizers();

    @OccInstanceProperty(value = "gestureRecognizers", argumentSemantic = ArgumentSemantic.COPY)
    void setGestureRecognizers(List<UIGestureRecognizer> value);

    @OccInstanceMethod("addGestureRecognizer:")
    void addGestureRecognizer(UIGestureRecognizer recognizer);

    @OccInstanceMethod("removeGestureRecognizer:")
    void removeGestureRecognizer(UIGestureRecognizer recognizer);

    @OccInstanceMethod("gestureRecognizerShouldBegin:")
    boolean getGestureRecognizerShouldBegin(UIGestureRecognizer recognizer);

    @OccInstanceProperty("restorationIdentifier")
    String restorationIdentifier();

    @OccInstanceProperty("restorationIdentifier")
    void setRestorationIdentifier(String value);

    @OccInstanceMethod("encodeRestorableStateWithCoder:")
    void encodeRestorableState(Coder coder);

    @OccInstanceProperty("contentScaleFactor")
    float getContentScaleFactor();

    @OccInstanceProperty("contentScaleFactor")
    void setContentScaleFactor(float scale);

    @OccInstanceMethod("decodeRestorableStateWithCoder:")
    void decodeRestorableState(Coder coder);

    @OccInstanceProperty("tag")
    int tag();

    @OccInstanceProperty("tag")
    void setTag(int value);

    @OccInstanceMethod("viewWithTag:")
    UIView viewWithTag(int tag);

    @OccInstanceMethod("convertPoint:toView:")
    GraphicsPoint convertPointToView(GraphicsPoint point, UIView view);

    @OccInstanceMethod("convertPoint:fromView:")
    GraphicsPoint convertPointFromView(GraphicsPoint point, UIView view);

    @OccInstanceMethod("convertRect:toView:")
    GraphicsRect convertRectToView(GraphicsRect rect, UIView view);

    @OccInstanceMethod("convertRect:fromView:")
    GraphicsRect convertRectFromView(GraphicsRect rect, UIView view);

    @OccInstanceMethod("hitTest:withEvent:")
    UIView hitTest(GraphicsPoint point, UIEvent event);

    @OccInstanceMethod("pointInside:withEvent:")
    boolean pointInside(GraphicsPoint point, UIEvent event);

    @OccInstanceMethod("endEditing:")
    boolean endEditing(boolean force);

    @OccInstanceMethod("didAddSubview:")
    void onDidAddSubview(UIView subview);

    @OccInstanceMethod("willRemoveSubview:")
    void onWillRemoveSubview(UIView subview);

    @OccInstanceMethod("willMoveToSuperview:")
    void onWillMoveToSuperview(UIView newSuperview);

    @OccInstanceMethod("didMoveToSuperview")
    void onDidMoveToSuperview();

    @OccInstanceMethod("willMoveToWindow:")
    void onWillMoveToWindow(UIWindow newWindow);

    @OccInstanceMethod("didMoveToWindow")
    void onDidMoveToWindow();

    @Override
    void displayLayer(AnimationLayer layer);

    @Override
    void drawLayer(AnimationLayer layer, GraphicsContext ctx);

    @Override
    AnimationAction actionForLayer(AnimationLayer layer, String key);

    @OccInstanceProperty("bounds")
    GraphicsRect getBounds();

    @OccInstanceProperty("bounds")
    void setBounds(GraphicsRect value);

    @OccInstanceMethod("drawRect:")
    void draw(GraphicsRect rect);

    @Override
    UIResponder nextResponder();

    @Override
    void onTouchesBegan(Set<UITouch> touches, UIEvent event);

    @Override
    void onTouchesMoved(Set<UITouch> touches, UIEvent event);

    @Override
    void onTouchesEnded(Set<UITouch> touches, UIEvent event);

    @Override
    void onTouchesCancelled(Set<UITouch> touches, UIEvent event);

    @Override
    UIViewFactory getFactory();
}
