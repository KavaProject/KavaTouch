/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.uikit;

import android.util.FloatMath;
import org.kavaproject.kavatouch.coreanimation.AnimationContentsGravity;
import org.kavaproject.kavatouch.coreanimation.AnimationLayer;
import org.kavaproject.kavatouch.coreanimation.staging.AnimationAction;
import org.kavaproject.kavatouch.coregraphics.*;
import org.kavaproject.kavatouch.foundation.Coder;
import org.kavaproject.kavatouch.runtime.MethodResolver;
import org.kavaproject.kavatouch.uikit.staging.*;
import org.kavaproject.kavatouch.util.NotImplementedException;
import org.kavaproject.kavatouch.util.ReverseIterable;

import java.lang.reflect.Method;
import java.util.*;

public class DefaultUIView extends SimpleUIResponder implements UIView {
    private final UIScreen mMainScreen;
    private final UIColorFactory mUIColorFactory;
    private final UIGraphics mUIGraphics;
    private AnimationLayer mLayer;
    private UIViewController mViewController;
    private GraphicsAffineTransform mTransformToWindow;
    private ArrayList<UIView> mSubviews = new ArrayList<UIView>();
    private UIView mSuperview;
    private boolean mClearsContextBeforeDrawing = true;
    private boolean mUserInteractionEnabled = true;
    private UIViewContentMode mContentMode = UIViewContentMode.SCALE_TO_FILL;
    private EnumSet<UIViewAutoresizing> mAutoresizingMask = EnumSet.of(UIViewAutoresizing.NONE);
    private boolean mAutoresizesSubviews = true;
    private boolean mImplementsDrawRect = false;
    private List<UIGestureRecognizer> mGestureRecognizers = new ArrayList<UIGestureRecognizer>();
    private int mTag = 0;
    private boolean mMultipleTouchEnabled = false;
    private Set<UITouch> mCurrentTouches = new HashSet<UITouch>();

    protected DefaultUIView(GraphicsRect frame, UIViewFactory uiViewFactory, UIGraphics uiGraphics,
                            UIColorFactory uiColorFactory, UIScreen mainScreen, MethodResolver methodResolver) {
        super(uiViewFactory, methodResolver);
        // An alternative to inflection would be using the emulated ObjC runtime, but then the framework user has to
        // know about it and keep it in sync:
//            IMP uiViewImp = UIViewFactory.instance.instanceMethodForSelector(UIViewFactory.SEL_DRAW_RECT);
//            IMP ownImp = methodForSelector(UIViewFactory.SEL_DRAW_RECT);
//            mImplementsDrawRect = uiViewImp != ownImp;
        mUIGraphics = uiGraphics;
        mUIColorFactory = uiColorFactory;
        mMainScreen = mainScreen;
        try {
            Method method = getClass().getMethod("draw", GraphicsRect.class);
            mImplementsDrawRect = method.getDeclaringClass() != DefaultUIView.class;
        } catch (NoSuchMethodException e) {
            throw new Error();
        }
        mLayer = getFactory().layerFactory().create();
        mLayer.setName(getClass().getName());
        mLayer.setDelegate(this);
        setOpaque(true);
        if (mImplementsDrawRect) {
            mLayer.setContentsScale(mMainScreen.getScale());
        }
        setNeedsDisplay();
        if (!frame.isNull()) {
            setFrame(frame);
        }
    }

    @Override
    public void setNeedsDisplay() {
        mLayer.setNeedsDisplay();
    }

    @Override
    public UIColor getBackgroundColor() {
        return mLayer.getBackgroundColor() != null ? mUIColorFactory.create(mLayer.getBackgroundColor()) : null;
    }

    @Override
    public void setBackgroundColor(UIColor value) {
        mLayer.setBackgroundColor(value != null ? new GraphicsColor(value.toCoreType()) : null);
    }

    @Override
    public boolean isHidden() {
        return mLayer.isHidden();
    }

    @Override
    public void setHidden(boolean value) {
        //TODO find new first responder if necessary
        if (value != mLayer.isHidden()) {
            mLayer.setHidden(value);
        }
    }

    @Override
    public float getAlpha() {
        return mLayer.getOpacity();
    }

    @Override
    public void setAlpha(float value) {
        mLayer.setOpacity(value);
    }

    @Override
    public boolean isOpaque() {
        return mLayer.isOpaque();
    }

    @Override
    public void setOpaque(boolean value) {
        mLayer.setOpaque(value);
    }

    @Override
    public boolean getClipsToBounds() {
        return mLayer.getMasksToBounds();
    }

    @Override
    public void setClipsToBounds(boolean value) {
        mLayer.setMasksToBounds(value);
    }

    @Override
    public boolean getClearsContextBeforeDrawing() {
        return mClearsContextBeforeDrawing;
    }

    @Override
    public void setClearsContextBeforeDrawing(boolean value) {
        mClearsContextBeforeDrawing = value;
    }

    @Override
    public boolean isUserInteractionEnabled() {
        return mUserInteractionEnabled;
    }

    @Override
    public void setUserInteractionEnabled(boolean value) {
        mUserInteractionEnabled = value;
    }

    @Override
    public boolean isMultipleTouchEnabled() {
        return mMultipleTouchEnabled;
    }

    @Override
    public void setMultipleTouchEnabled(boolean value) {
        mMultipleTouchEnabled = value;
    }

    @Override
    public boolean isExclusiveTouch() {
        throw new NotImplementedException();
    }

    @Override
    public void setExclusiveTouch(boolean value) {
        throw new NotImplementedException();
    }

    @Override
    public GraphicsRect getFrame() {
        return mLayer.getFrame();
    }

    @Override
    public void setFrame(GraphicsRect value) {
        if (value.equals(mLayer.getFrame())) {
            return;
        }
        GraphicsRect oldBounds = mLayer.getBounds();
        mLayer.setFrame(value);
        boundsDidChangeFromTo(oldBounds, mLayer.getBounds());
    }

    @Override
    public GraphicsPoint getCenter() {
        return mLayer.getPosition();
    }

    @Override
    public void setCenter(GraphicsPoint value) {
        mLayer.setPosition(value);
    }

    @Override
    public GraphicsAffineTransform getTransform() {
        return mLayer.affineTransform();
    }

    @Override
    public void setTransform(GraphicsAffineTransform value) {
        mLayer.setAffineTransform(value);
    }

    @Override
    public List<UIView> getSubviews() {
        return new ArrayList<UIView>(mSubviews); //Chameleon is wrong
    }

    @Override
    public UIWindow getWindow() {
        return mSuperview != null ? mSuperview.getWindow() : null;
    }

    @Override
    public void addSubview(UIView view) {
        insertSubview(view, mSubviews.size());
    }

    @Override
    public void bringSubviewToFront(UIView view) {
        if (mSubviews.size() <= 1) {
            return;
        }
        mSubviews.remove(view);
        mSubviews.add(view);
        List<AnimationLayer> sublayers = mLayer.getSublayers();
        sublayers.remove(view.getLayer());
        sublayers.add(view.getLayer());
        mLayer.setSublayers(sublayers);
    }

    @Override
    public AnimationLayer getLayer() {
        return mLayer;
    }

    @Override
    public void sendSubviewToBack(UIView view) {
        if (mSubviews.size() <= 1) {
            return;
        }
        mSubviews.remove(view);
        mSubviews.add(0, view);
        List<AnimationLayer> sublayers = mLayer.getSublayers();
        sublayers.remove(view.getLayer());
        sublayers.add(0, view.getLayer());
        mLayer.setSublayers(sublayers);
    }

    @Override
    public void removeFromSuperview() {
        if (mSuperview == null) {
            return;
        }
        boolean hadWindow = getWindow() != null;
        if (hadWindow) {
            onWillMoveToWindow(null);
        }
        mSuperview.onWillRemoveSubview(this);
        onWillMoveToSuperview(null);

        for (UITouch touch : mCurrentTouches) {
            for (UIGestureRecognizer recognizer : touch.getGestureRecognizers()) {
                if (recognizer.getView() == this) {
                    touch.removeGestureRecognizers(Collections.singleton(recognizer));
                }
            }
            touch.setView(null);
        }
        mCurrentTouches.clear();

        mLayer.removeFromSuperlayer();
        onDidMoveToSuperview();
        if (hadWindow) {
            onDidMoveToWindow();
        }
    }

    @Override
    public void insertSubview(UIView view, int index) {
        boolean windowChanged = getWindow() != view.getWindow();
        view.removeFromSuperview();
        if (windowChanged) {
            view.onWillMoveToWindow(getWindow());
        }
        view.onWillMoveToSuperview(this);
//        view.viewController.viewWillDisappear(false);
        view.setSuperview(this);
        if (mSubviews.size() == 0) {
            mLayer.addSublayer(view.getLayer());
        } else if (index == 0) {
            mLayer.insertSublayerBelow(view.getLayer(), mSubviews.get(index).getLayer());
        } else {
            mLayer.insertSublayerAbove(view.getLayer(), mSubviews.get(index - 1).getLayer());
        }
        mSubviews.add(index, view);
        onDidAddSubview(view);
//        view.viewController.viewDidDisappear(false);
        view.onDidMoveToSuperview();
        if (windowChanged) {
            view.onDidMoveToWindow();
        }
//        NSNotificationCenter.defaultCenter().postNotificationNameObject(UIViewDidMoveToSuperviewNotification, this);
    }

    @Override
    public void insertSubviewAbove(UIView newView, UIView subview) {
        int indexBelow = mSubviews.indexOf(subview);
        insertSubview(newView, indexBelow + 1);
    }

    @Override
    public void insertSubviewBelow(UIView newView, UIView subview) {
        int index = mSubviews.indexOf(subview);
        insertSubview(newView, index);
    }

    @Override
    public void exchangeSubview(int index1, int index2) {
        UIView subview1 = mSubviews.get(index1);
        UIView subview2 = mSubviews.get(index2);
        mSubviews.set(index1, subview2);
        mSubviews.set(index2, subview1);
        List<AnimationLayer> sublayers = mLayer.getSublayers();
        index1 = sublayers.indexOf(subview1.getLayer());
        index2 = sublayers.indexOf(subview2.getLayer());
        sublayers.set(index1, subview2.getLayer());
        sublayers.set(index2, subview1.getLayer());
        mLayer.setSublayers(sublayers);
    }

    @Override
    public boolean isDescendantOfView(UIView view) {
        if (view == null) {
            return false;
        }
        UIView candidateView = this;
        while (candidateView != null) {
            if (candidateView == view) {
                return true;
            } else {
                candidateView = candidateView.getSuperview();
            }
        }
        return false;
    }

    @Override
    public UIView getSuperview() {
        return mSuperview;
    }

    @Override
    public EnumSet<UIViewAutoresizing> getAutoresizingMask() {
        return mAutoresizingMask;
    }

    @Override
    public void setAutoresizingMask(EnumSet<UIViewAutoresizing> value) {
        mAutoresizingMask = value;
    }

    @Override
    public boolean getAutoresizesSubviews() {
        return mAutoresizesSubviews;
    }

    @Override
    public void setAutoresizesSubviews(boolean value) {
        mAutoresizesSubviews = value;
    }

    @Override
    public UIViewContentMode getContentMode() {
        return mContentMode;
    }

    @Override
    public void setContentMode(UIViewContentMode value) {
        if (value == mContentMode) {
            return;
        }
        mContentMode = value;
        if (value == UIViewContentMode.REDRAW) {
            mLayer.setNeedsDisplayOnBoundsChange(true);
            return;
        }
        mLayer.setNeedsDisplayOnBoundsChange(false);
        switch (value) {
            case SCALE_TO_FILL:
                mLayer.setContentsGravity(AnimationContentsGravity.RESIZE);
                break;
            case SCALE_ASPECT_FIT:
                mLayer.setContentsGravity(AnimationContentsGravity.RESIZE_ASPECT);
                break;
            case SCALE_ASPECT_FILL:
                mLayer.setContentsGravity(AnimationContentsGravity.RESIZE_ASPECT_FILL);
                break;
            case CENTER:
                mLayer.setContentsGravity(AnimationContentsGravity.CENTER);
                break;
            case TOP:
                mLayer.setContentsGravity(AnimationContentsGravity.TOP);
                break;
            case BOTTOM:
                mLayer.setContentsGravity(AnimationContentsGravity.BOTTOM);
                break;
            case LEFT:
                mLayer.setContentsGravity(AnimationContentsGravity.LEFT);
                mLayer.setNeedsDisplayOnBoundsChange(false);
                break;
            case RIGHT:
                mLayer.setContentsGravity(AnimationContentsGravity.RIGHT);
                break;
            case TOP_LEFT:
                mLayer.setContentsGravity(AnimationContentsGravity.TOP_LEFT);
                break;
            case TOP_RIGHT:
                mLayer.setContentsGravity(AnimationContentsGravity.TOP_RIGHT);
                break;
            case BOTTOM_LEFT:
                mLayer.setContentsGravity(AnimationContentsGravity.BOTTOM_LEFT);
                break;
            case BOTTOM_RIGHT:
                mLayer.setContentsGravity(AnimationContentsGravity.BOTTOM_RIGHT);
                break;
        }
    }

    @Override
    public GraphicsSize sizeThatFits(GraphicsSize size) {
        return size;
    }

    @Override
    public void sizeToFit() {
        GraphicsRect frame = getFrame();
        frame = new GraphicsRect(frame.origin, sizeThatFits(frame.size));
        setFrame(frame);
    }

    @Override
    public GraphicsRect getContentStretch() {
        return mLayer.getContentsCenter();
    }

    @Override
    public void setContentStretch(GraphicsRect value) {
        mLayer.setContentsCenter(value);
    }

    @Override
    public void layoutSubviews() {

    }

    @Override
    public void setNeedsLayout() {
        mLayer.setNeedsLayout();
    }

    @Override
    public void layoutIfNeeded() {
        getLayer().layoutIfNeeded();
    }

    @Override
    public boolean translatesAutoresizingMaskIntoConstraints() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void setTranslatesAutoresizingMaskIntoConstraints(boolean value) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public List<LayoutConstraint> constraints() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void addConstraint(LayoutConstraint constraint) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void addConstraints(List<LayoutConstraint> constraints) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void removeConstraint(LayoutConstraint constraint) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void removeConstraints(List<LayoutConstraint> constraints) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public GraphicsSize systemLayoutSizeFittingSize(GraphicsSize targetSize) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public GraphicsSize intrinsicContentSize() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void invalidateIntrinsicContentSize() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public UILayoutPriority contentCompressionResistancePriority(UILayoutConstraintAxis axis) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void setContentCompressionResistancePriority(UILayoutPriority priority, UILayoutConstraintAxis axis) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public UILayoutPriority contentHuggingPriority(UILayoutConstraintAxis axis) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void setContentHuggingPriority(UILayoutPriority priority, UILayoutConstraintAxis axis) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public GraphicsRect alignmentRectForFrame(GraphicsRect frame) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public GraphicsRect frameForAlignmentRect(GraphicsRect alignmentRect) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public UIEdgeInsets alignmentRectInsets() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public UIView viewForBaselineLayout() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public boolean needsUpdateConstraints() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void setNeedsUpdateConstraints() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void updateConstraints() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void updateConstraintsIfNeeded() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public List<LayoutConstraint> getConstraintsAffectingLayout(UILayoutConstraintAxis axis) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public boolean hasAmbiguousLayout() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void exerciseAmbiguityInLayout() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void setNeedsDisplayInRect(GraphicsRect rect) {
        mLayer.setNeedsDisplayInRect(rect);
    }

    @Override
    public UIViewPrintFormatter viewPrintFormatter() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void draw(GraphicsRect area, UIViewPrintFormatter formatter) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public List<UIGestureRecognizer> getGestureRecognizers() {
        return new ArrayList<UIGestureRecognizer>(mGestureRecognizers);
    }

    @Override
    public void setGestureRecognizers(List<UIGestureRecognizer> value) {
        for (UIGestureRecognizer recognizer : mGestureRecognizers) {
            recognizer.setView(null);
        }
        mGestureRecognizers.clear();
        for (UIGestureRecognizer recognizer : value) {
            addGestureRecognizer(recognizer);
        }
    }

    @Override
    public void addGestureRecognizer(UIGestureRecognizer recognizer) {
        if (mGestureRecognizers.contains(recognizer)) {
            return;
        }
        recognizer.getView().removeGestureRecognizer(recognizer);
        mGestureRecognizers.add(recognizer);
        recognizer.setView(this);
    }

    @Override
    public void removeGestureRecognizer(UIGestureRecognizer recognizer) {
        if (!mGestureRecognizers.contains(recognizer)) {
            return;
        }
        recognizer.setView(null);
        mGestureRecognizers.remove(recognizer);
    }

    @Override
    public boolean getGestureRecognizerShouldBegin(UIGestureRecognizer recognizer) {
        throw new NotImplementedException();
    }

    @Override
    public String restorationIdentifier() {
        throw new NotImplementedException();
    }

    @Override
    public void setRestorationIdentifier(String value) {
        throw new NotImplementedException();
    }

    @Override
    public void encodeRestorableState(Coder coder) {
        throw new NotImplementedException();
    }

    @Override
    public float getContentScaleFactor() {
        return mLayer.getContentsScale();
    }

    @Override
    public void setContentScaleFactor(float scale) {
        if (scale <= 0 && mImplementsDrawRect) {
            scale = mMainScreen.getScale();
        }
        if (scale > 0 && scale != getContentScaleFactor()) {
            mLayer.setContentsScale(scale);
            setNeedsDisplay();
        }
    }

    @Override
    public void decodeRestorableState(Coder coder) {
        throw new NotImplementedException();
    }

    @Override
    public int tag() {
        return mTag;
    }

    @Override
    public void setTag(int value) {
        mTag = value;
    }

    @Override
    public UIView viewWithTag(int tag) {
        UIView foundView = null;
        if (this.mTag == tag) {
            foundView = this;
        } else {
            for (UIView view : ReverseIterable.wrap(mSubviews)) {
                foundView = view.viewWithTag(tag);
                if (foundView != null) {
                    break;
                }
            }
        }
        return foundView;
    }

    @Override
    public GraphicsPoint convertPointToView(GraphicsPoint point, UIView view) {
        // http://stackoverflow.com/questions/6024338/how-does-uiview-convert-points-between-views
        // Benchmarking suggests that the original implementation is caching view to window transforms. The inverse
        // takes double the time. All other transforms take 20 times as long.
        // Note that this doesn't happen when working in the layer hierarchy. Layers are always slow. It also won't
        // happen before the view is visible.
        // I deduct that the view to window transform is cached. But what if the layers in between change?
        // Answer: Conversions involving the window won't detect layer changes until the next redraw cycle. Conversions
        // from/to view coordinates will.
        // Before the view is first made visible, it won't find a cached transformation and default to the layer
        // conversion.
        // This behavior has been confirmed for iOS 5 on the emulator.
        if (view == null || view == getWindow()) {
            if (mTransformToWindow != null) {
                return mTransformToWindow.transformPoint(point);
            } else {
                return mLayer.convertPointToLayer(point, getWindow().getLayer());
            }
        } else {
            return mLayer.convertPointToLayer(point, view.getLayer());
        }
    }

    @Override
    public GraphicsPoint convertPointFromView(GraphicsPoint point, UIView view) {
        if (view == null || view == getWindow()) {
            if (mTransformToWindow != null) {
                GraphicsAffineTransform transformFromWindow = mTransformToWindow.invert();
                return transformFromWindow.transformPoint(point);
            } else {
                return getWindow().getLayer().convertPointToLayer(point, mLayer);
            }
        } else {
            return view.getLayer().convertPointToLayer(point, mLayer);
        }
    }

    @Override
    public GraphicsRect convertRectToView(GraphicsRect rect, UIView view) {
        GraphicsPoint top = convertPointToView(new GraphicsPoint(rect.getMinX(), rect.getMinY()), view);
        GraphicsPoint bottom = convertPointToView(new GraphicsPoint(rect.getMaxX(), rect.getMaxY()), view);
        return new GraphicsRect(top.x, top.y, bottom.x - top.x, bottom.y - top.y);
    }

    @Override
    public GraphicsRect convertRectFromView(GraphicsRect rect, UIView view) {
        GraphicsPoint top = convertPointFromView(new GraphicsPoint(rect.getMinX(), rect.getMinY()), view);
        GraphicsPoint bottom = convertPointFromView(new GraphicsPoint(rect.getMaxX(), rect.getMaxY()), view);
        return new GraphicsRect(top.x, top.y, bottom.x - top.x, bottom.y - top.y);
    }

    @Override
    public UIView hitTest(GraphicsPoint point, UIEvent event) {
        if (isHidden() || !isUserInteractionEnabled() || getAlpha() < 0.01 || !pointInside(point, event)) {
            return null;
        } else {
            for (UIView subview : ReverseIterable.wrap(mSubviews)) {
                UIView hitView = subview.hitTest(subview.convertPointFromView(point, this), event);
                if (hitView != null) {
                    return hitView;
                }
            }
            return this;
        }
    }

    @Override
    public boolean pointInside(GraphicsPoint point, UIEvent event) {
        return getBounds().contains(point);
    }

    @Override
    public boolean endEditing(boolean force) {
        throw new NotImplementedException();
    }

    @Override
    public void onDidAddSubview(UIView subview) {

    }

    @Override
    public void onWillRemoveSubview(UIView subview) {

    }

    @Override
    public void onWillMoveToSuperview(UIView newSuperview) {

    }

    @Override
    public void onDidMoveToSuperview() {

    }

    @Override
    public void onWillMoveToWindow(UIWindow newWindow) {

    }

    @Override
    public void onDidMoveToWindow() {

    }

    @Override
    public void displayLayer(AnimationLayer layer) {
        //A little trick discovered by the Chameleon guys, see their notes for this method and respondsToSelector:
        if (mImplementsDrawRect) {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public void drawLayer(AnimationLayer layer, GraphicsContext ctx) {
        //Chameleon: Only called when implementsDrawRect == true
//        CGRect bounds = ctx.getClipBoundingBox(); //TODO Test!
        GraphicsRect bounds = getBounds();
        mUIGraphics.pushContext(ctx);
        ctx.saveGState();
        if (mClearsContextBeforeDrawing) {
            ctx.clearRect(bounds);
        }
        if (mLayer.getBackgroundColor() != null) {
            ctx.setFillColor(mLayer.getBackgroundColor());
            ctx.fillRect(bounds);
        }
        mUIColorFactory.black().setColor();
        draw(bounds);
        ctx.restoreGState();
        mUIGraphics.popContext();
    }

    @Override
    public AnimationAction actionForLayer(AnimationLayer layer, String key) {
        throw new NotImplementedException();  //TODO
    }

    @Override
    public GraphicsRect getBounds() {
        return mLayer.getBounds();
    }

    @Override
    public void setBounds(GraphicsRect value) {
        if (value.equals(mLayer.getBounds())) {
            return;
        }
        GraphicsRect oldBounds = mLayer.getBounds();
        mLayer.setBounds(value);
        boundsDidChangeFromTo(oldBounds, value);
    }

    @Override
    public void draw(GraphicsRect rect) {
    }

    @Override
    public void setSuperview(UIView view) {
        mSuperview = view;
    }

    /**
     * top + bottom + height      => y = floor(y + (y / HEIGHT * delta)); height = floor(height + (height / HEIGHT *
     * delta))
     * top + height               => t = y + height; y = floor(y + (y / t * delta); height = floor(height + (height / t
     * * delta);
     * bottom + height            => height = floor(height + (height / (HEIGHT - y) * delta))
     * top + bottom               => y = floor(y + (delta / 2))
     * height                     => height = floor(height + delta)
     * top                        => y = floor(y + delta)
     * bottom                     => y = floor(y)
     * @param oldSize
     * @param newSize
     */
    @Override
    public void superviewSizeDidChangeFromTo(GraphicsSize oldSize, GraphicsSize newSize) {
        //See Chameleon
        if (mAutoresizingMask.contains(UIViewAutoresizing.NONE)) {
            return;
        }
        MutableGraphicsRect frame = new MutableGraphicsRect(getFrame());
        GraphicsSize delta = new GraphicsSize(newSize.width - oldSize.width, newSize.height - oldSize.height);
        if (mAutoresizingMask.contains(UIViewAutoresizing.FLEXIBLE_TOP_MARGIN) && mAutoresizingMask.contains
                (UIViewAutoresizing.FLEXIBLE_HEIGHT) && mAutoresizingMask.contains(UIViewAutoresizing
                .FLEXIBLE_BOTTOM_MARGIN)) {
            frame.origin.y = FloatMath.floor(frame.origin.y + (frame.origin.y / oldSize.height * delta.height));
            frame.size.height = FloatMath.floor(frame.size.height + (frame.size.height / oldSize.height * delta
                    .height));
        } else if (mAutoresizingMask.contains(UIViewAutoresizing.FLEXIBLE_TOP_MARGIN) && mAutoresizingMask.contains
                (UIViewAutoresizing.FLEXIBLE_HEIGHT)) {
            float t = frame.origin.y + frame.size.height;
            frame.origin.y = FloatMath.floor(frame.origin.y + (frame.origin.y / t * delta.height));
            frame.size.height = FloatMath.floor(frame.size.height + (frame.size.height / t * delta.height));
        } else if (mAutoresizingMask.contains(UIViewAutoresizing.FLEXIBLE_BOTTOM_MARGIN) && mAutoresizingMask
                .contains(UIViewAutoresizing.FLEXIBLE_HEIGHT)) {
            frame.size.height = FloatMath.floor(frame.size.height + (frame.size.height / (oldSize.height - frame
                    .origin.y) * delta.height));
        } else if (mAutoresizingMask.contains(UIViewAutoresizing.FLEXIBLE_BOTTOM_MARGIN) && mAutoresizingMask
                .contains(UIViewAutoresizing.FLEXIBLE_TOP_MARGIN)) {
            frame.origin.y = FloatMath.floor(frame.origin.y + (delta.height / 2.f));
        } else if (mAutoresizingMask.contains(UIViewAutoresizing.FLEXIBLE_HEIGHT)) {
            frame.size.height = FloatMath.floor(frame.size.height + delta.height);
        } else if (mAutoresizingMask.contains(UIViewAutoresizing.FLEXIBLE_TOP_MARGIN)) {
            frame.origin.y = FloatMath.floor(frame.origin.y + delta.height);
        } else if (mAutoresizingMask.contains(UIViewAutoresizing.FLEXIBLE_BOTTOM_MARGIN)) {
            frame.origin.y = FloatMath.floor(frame.origin.y);
        }
        if (mAutoresizingMask.contains(UIViewAutoresizing.FLEXIBLE_LEFT_MARGIN) && mAutoresizingMask.contains
                (UIViewAutoresizing.FLEXIBLE_WIDTH) && mAutoresizingMask.contains(UIViewAutoresizing
                .FLEXIBLE_RIGHT_MARGIN)) {
            frame.origin.x = FloatMath.floor(frame.origin.x + (frame.origin.x / oldSize.width * delta.width));
            frame.size.width = FloatMath.floor(frame.size.width + (frame.size.width / oldSize.width * delta.width));
        } else if (mAutoresizingMask.contains(UIViewAutoresizing.FLEXIBLE_LEFT_MARGIN) && mAutoresizingMask.contains
                (UIViewAutoresizing.FLEXIBLE_WIDTH)) {
            float t = frame.origin.x + frame.size.width;
            frame.origin.x = FloatMath.floor(frame.origin.x + (frame.origin.x / t * delta.width));
            frame.size.width = FloatMath.floor(frame.size.width + (frame.size.width / t * delta.width));
        } else if (mAutoresizingMask.contains(UIViewAutoresizing.FLEXIBLE_RIGHT_MARGIN) && mAutoresizingMask.contains
                (UIViewAutoresizing.FLEXIBLE_WIDTH)) {
            frame.size.width = FloatMath.floor(frame.size.width + (frame.size.width / (oldSize.width - frame.origin
                    .x) * delta.width));
        } else if (mAutoresizingMask.contains(UIViewAutoresizing.FLEXIBLE_RIGHT_MARGIN) && mAutoresizingMask.contains
                (UIViewAutoresizing.FLEXIBLE_LEFT_MARGIN)) {
            frame.origin.x = FloatMath.floor(frame.origin.x + (delta.width / 2.f));
        } else if (mAutoresizingMask.contains(UIViewAutoresizing.FLEXIBLE_WIDTH)) {
            frame.size.width = FloatMath.floor(frame.size.width + delta.width);
        } else if (mAutoresizingMask.contains(UIViewAutoresizing.FLEXIBLE_LEFT_MARGIN)) {
            frame.origin.x = FloatMath.floor(frame.origin.x + delta.width);
        } else if (mAutoresizingMask.contains(UIViewAutoresizing.FLEXIBLE_RIGHT_MARGIN)) {
            frame.origin.x = FloatMath.floor(frame.origin.x);
        }
        setFrame(frame.toImmutableRect());
    }

    private void boundsDidChangeFromTo(GraphicsRect oldBounds, GraphicsRect newBounds) {
        if (!oldBounds.equals(newBounds)) {
            // Chameleon: setNeedsLayout doesn't seem like it should be necessary, however there was a rendering bug
            // in a table in Flamingo that went away when this was placed here. There must be some strange ordering
            // issue with how that layout manager stuff works. I never quite narrowed it down. This was an easy fix,
            // if perhaps not ideal.
            setNeedsLayout();
            if (!oldBounds.size.equals(newBounds.size)) {
                if (mAutoresizesSubviews) {
                    for (UIView subview : mSubviews) {
                        subview.superviewSizeDidChangeFromTo(oldBounds.size, newBounds.size);
                    }
                }
            }
        }
    }

    protected DefaultUIView(Coder decoder, UIViewFactory uiViewFactory, UIGraphics uiGraphics,
                            UIColorFactory uiColorFactory, UIScreen mainScreen, MethodResolver methodResolver) {
        super(uiViewFactory, methodResolver);
        mUIGraphics = uiGraphics;
        mUIColorFactory = uiColorFactory;
        mMainScreen = mainScreen;
        //TODO
    }

    @Override
    public UIResponder nextResponder() {
        return mViewController != null ? mViewController : getSuperview();
    }

    @Override
    public void onTouchesBegan(Set<UITouch> touches, UIEvent event) {
        for (UITouch touch : event.touchesForView(this)) {
            mCurrentTouches.add(touch);
        }
        if (mSuperview != null) {
            mSuperview.onTouchesBegan(touches, event);
        }
    }

    @Override
    public void onTouchesMoved(Set<UITouch> touches, UIEvent event) {
        if (mSuperview != null) {
            mSuperview.onTouchesMoved(touches, event);
        }
    }

    @Override
    public void onTouchesEnded(Set<UITouch> touches, UIEvent event) {
        mCurrentTouches.clear();
        if (mSuperview != null) {
            mSuperview.onTouchesEnded(touches, event);
        }
    }

    @Override
    public void onTouchesCancelled(Set<UITouch> touches, UIEvent event) {
        mCurrentTouches.clear();
        if (mSuperview != null) {
            mSuperview.onTouchesCancelled(touches, event);
        }
    }

    @Override
    public UIViewFactory getFactory() {
        return (UIViewFactory) super.getFactory();
    }

    @Override
    public void encode(Coder encoder) {
        throw new NotImplementedException(); //TODO
    }

    protected UIScreen getMainScreen() {
        return mMainScreen;
    }

    protected UIGraphics getUIGraphics() {
        return mUIGraphics;
    }

//    @Override
//    public boolean respondsToSelector(String aSelector) {
//        if (aSelector == CALayerDelegateSelectors.SEL_DISPLAY_LAYER) {
//            return !mImplementsDrawRect; //false in subclasses that overwrite drawRect
//        } else {
//            return super.respondsToSelector(aSelector);
//        }
//    }

    protected UIColorFactory getUIColorFactory() {
        return mUIColorFactory;
    }
}
