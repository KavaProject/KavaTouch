/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.coreanimation;

import org.kavaproject.kavatouch.coreanimation.staging.Animation;
import org.kavaproject.kavatouch.coreanimation.staging.AnimationAction;
import org.kavaproject.kavatouch.coreanimation.staging.AnimationEdgeAntialiasingMask;
import org.kavaproject.kavatouch.coreanimation.staging.ImageFilter;
import org.kavaproject.kavatouch.coregraphics.*;
import org.kavaproject.kavatouch.foundation.Coder;
import org.kavaproject.kavatouch.uikit.Session;
import org.kavaproject.kavatouch.uikit.UIColorFactory;
import org.kavaproject.kavatouch.util.FloatUtil;
import org.kavaproject.kavatouch.util.NotImplementedException;
import org.kavaproject.kavatouch.util.ReverseIterable;

import java.util.*;

public class GraphicsAnimationLayer implements AnimationLayer {
    private final DefaultLayoutManager mLayoutManager;
    private final UIColorFactory mUIColorFactory;
    private final AnimationLayerFactory mAnimationLayerFactory;
    private boolean mNeedsDisplayOnBoundsChange = false;
    private boolean mNeedsDisplay = false;
    private boolean mNeedsLayout = true;
    private AnimationLayerDelegate mDelegate;
    private GraphicsRect mBounds = GraphicsRect.ZERO;
    private GraphicsPoint mAnchorPoint = new GraphicsPoint(0.5f, 0.5f);
    private AnimationTransform3D mTransform = AnimationTransform3D.CATransform3DIdentity;
    private AnimationTransform3D mSublayerTransform = AnimationTransform3D.CATransform3DIdentity;
    private GraphicsImage mContents; //TODO Cache
    private GraphicsRect mContentsRect = new GraphicsRect(0, 0, 1, 1);
    private AnimationContentsGravity mContentsGravity = AnimationContentsGravity.RESIZE;
    private float mContentsScale = 1;
    private boolean mMasksToBounds = false;
    private float mCornerRadius = 0;
    private float mBorderWidth = 0;
    private GraphicsColor mBorderColor;
    private GraphicsColor mBackgroundColor = null;
    private boolean mOpaque = false;
    private GraphicsRect mContentsCenter = new GraphicsRect(0, 0, 1, 1);
    private boolean mHidden = false;
    private float mOpacity = 1;
    private float mZPosition = 0;
    private float mAnchorPointZ = 0;
    private AnimationLayer mMask = null;
    private boolean mDoubleSided = true;
    private String mName = null;
    private GraphicsPoint mPosition = GraphicsPoint.ZERO;
    private List<AnimationLayer> mSublayers = null;
    private AnimationLayer mSuperlayer;
    private ChangeLogger mChangeLogger = ChangeLogger.NOOP;

    protected GraphicsAnimationLayer(AnimationLayerFactory animationLayerFactory,
                                     DefaultLayoutManagerFactory defaultLayoutManagerFactory,
                                     UIColorFactory uiColorFactory) {
        mLayoutManager = defaultLayoutManagerFactory.create();
        mAnimationLayerFactory = animationLayerFactory;
        mUIColorFactory = uiColorFactory;
        mBorderColor = mUIColorFactory.black().toCoreType();
    }

    protected GraphicsAnimationLayer(Coder decoder, AnimationLayerFactory animationLayerFactory,
                                     DefaultLayoutManagerFactory defaultLayoutManagerFactory,
                                     UIColorFactory uiColorFactory) {
        mLayoutManager = defaultLayoutManagerFactory.create();
        mAnimationLayerFactory = animationLayerFactory;
        mUIColorFactory = uiColorFactory;
        //TODO
    }

    @Override
    public AnimationLayer presentationLayer() {
        throw new NotImplementedException();
    }

    @Override
    public AnimationLayer modelLayer() {
        throw new NotImplementedException();
    }

    @Override
    public AnimationLayerDelegate getDelegate() {
        return mDelegate;
    }

    @Override
    public void setDelegate(AnimationLayerDelegate value) {
        mDelegate = value;
    }

    @Override
    public GraphicsImage getContents() {
        return mContents;
    }

    @Override
    public void setContents(GraphicsImage value) {
        mContents = value;
        mChangeLogger.logLayerChange(this);
    }

    @Override
    public GraphicsRect getContentsRect() {
        return mContentsRect;
    }

    @Override
    public void setContentsRect(GraphicsRect value) {
        mContentsRect = value;
        mChangeLogger.logLayerChange(this);
    }

    @Override
    public GraphicsRect getContentsCenter() {
        return mContentsCenter;
    }

    @Override
    public void setContentsCenter(GraphicsRect value) {
        mContentsCenter = value;
        mChangeLogger.logLayerChange(this);
    }

    @Override
    public AnimationContentsGravity getContentsGravity() {
        return mContentsGravity;
    }

    @Override
    public void setContentsGravity(AnimationContentsGravity value) {
        mContentsGravity = value;
        mChangeLogger.logLayerChange(this);
    }

    @Override
    public float getOpacity() {
        return mOpacity;
    }

    @Override
    public void setOpacity(float value) {
        mOpacity = FloatUtil.clip(value, 0, 1);
        mChangeLogger.logLayerChange(this);
    }

    @Override
    public boolean isHidden() {
        return mHidden;
    }

    @Override
    public void setHidden(boolean value) {
        mHidden = value;
        mChangeLogger.logLayerChange(this);
    }

    @Override
    public boolean getMasksToBounds() {
        return mMasksToBounds;
    }

    @Override
    public void setMasksToBounds(boolean value) {
        this.mMasksToBounds = value;
    }

    @Override
    public AnimationLayer getMask() {
        return mMask;
    }

    @Override
    public void setMask(AnimationLayer value) {
        mMask = value;
        mChangeLogger.logLayerChange(this);
    }

    @Override
    public boolean isDoubleSided() {
        return mDoubleSided;
    }

    @Override
    public void setDoubleSided(boolean value) {
        mDoubleSided = value;
    }

    @Override
    public float getCornerRadius() {
        return mCornerRadius;
    }

    @Override
    public void setCornerRadius(float value) {
        this.mCornerRadius = value;
        mChangeLogger.logLayerChange(this);
    }

    @Override
    public float getBorderWidth() {
        return mBorderWidth;
    }

    @Override
    public void setBorderWidth(float value) {
        this.mBorderWidth = value;
        mChangeLogger.logLayerChange(this);
    }

    @Override
    public GraphicsColor getBorderColor() {
        return mBorderColor;
    }

    @Override
    public void setBorderColor(GraphicsColor value) {
        this.mBorderColor = value;
        mChangeLogger.logLayerChange(this);
    }

    @Override
    public GraphicsColor getBackgroundColor() {
        return mBackgroundColor;
    }

    @Override
    public void setBackgroundColor(GraphicsColor value) {
        this.mBackgroundColor = value;
        mChangeLogger.logLayerChange(this);
    }

    @Override
    public float getShadowOpacity() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void setShadowOpacity(float value) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public float getShadowRadius() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void setShadowRadius(float value) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public GraphicsSize getShadowOffset() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void setShadowOffset(GraphicsSize value) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public GraphicsColor getShadowColor() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void setShadowColor(GraphicsColor value) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public GraphicsPath getShadowPath() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void setShadowPath(GraphicsPath value) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public Map<String, Object> getStyle() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void setStyle(Map<String, Object> value) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public List<ImageFilter> getFilters() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void setFilters(List<ImageFilter> value) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public ImageFilter getCompositingFilter() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void setCompositingFilter(ImageFilter value) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public List<ImageFilter> getBackgroundFilters() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void setBackgroundFilters(List<ImageFilter> value) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public String getMinificationFilter() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void setMinificationFilter(String value) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public float getMinificationFilterBias() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void setMinificationFilterBias(float value) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public String getMagnificationFilter() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void setMagnificationFilter(String value) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public boolean isOpaque() {
        return mOpaque;
    }

    @Override
    public void setOpaque(boolean value) {
        mOpaque = value;
    }

    @Override
    public EnumSet<AnimationEdgeAntialiasingMask> getEdgeAntialiasingMask() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void setEdgeAntialiasingMask(EnumSet<AnimationEdgeAntialiasingMask> value) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public boolean getContentsAreFlipped() {
        return true;
    }

    @Override
    public boolean isGeometryFlipped() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void setGeometryFlipped(boolean value) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public boolean getDrawsAsynchronously() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void setDrawsAsynchronously(boolean value) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public boolean getShouldRasterize() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void setShouldRasterize(boolean value) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public float getRasterizationScale() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void setRasterizationScale(float value) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void render(GraphicsContext context) {
        long start;
        if (Session.DEBUG_MEASSURE_RENDERING_TIME) {
            start = System.nanoTime();
        }
        context.saveGState();
        GraphicsPoint position = getPosition();
        context.concatCTM(GraphicsAffineTransform.makeTranslation(position.x, position.y));
        context.concatCTM(affineTransform());
        GraphicsRect bounds = getBounds();
        GraphicsPoint anchorPoint = getAnchorPoint();
        float dx = anchorPoint.x * bounds.size.width;
        float dy = anchorPoint.y * bounds.size.height;
        context.concatCTM(GraphicsAffineTransform.makeTranslation(-dx, -dy));
        context.setAlpha(getOpacity());
        if (getMasksToBounds()) {
            GraphicsPath boundingPath = GraphicsPath.createWithRect(bounds, null); //TODO cornerRadius
            context.beginPath();
            context.addPath(boundingPath);
            context.clip();
        }
        GraphicsPath borderPath = null;
        if (getBackgroundColor() != null || getBorderWidth() > 0) {
            GraphicsRect borderRect = bounds.inset(getBorderWidth() / 2, getBorderWidth() / 2);
            borderPath = GraphicsPath.createWithRect(borderRect, null); //TODO cornerRadius
            context.beginPath();
            context.addPath(borderPath);
        }
        if (getBackgroundColor() != null) {
            context.setFillColor(getBackgroundColor());
            context.fillPath();
        }
        if (getContents() != null) {
            context.drawImage(bounds, getContents()); //TODO contentsRect, contentsCenter, contentsGravity,
            // contentsScale
        }
        if (getSublayers() != null) {
            for (AnimationLayer sublayer : getSublayers()) {
                sublayer.render(context);
            }
        }
        if (getBorderWidth() > 0) {
            context.beginPath();
            context.addPath(borderPath);
            context.setStrokeColor(getBorderColor());
            context.setLineWidth(getBorderWidth());
            context.strokePath();
        }
        context.restoreGState();
        if (Session.DEBUG_MEASSURE_RENDERING_TIME) {
            android.util.Log.d("KAVA", "Rendering time in ms for " + this + ":" + (float) (System.nanoTime() - start)
                    / 1000000);
        }
    }

    @Override
    public GraphicsRect getFrame() {
        //Actually, transformation is also taken into account. Wonder why this has been dropped from the official docs
        GraphicsAffineTransform t = affineTransform();
        MutableGraphicsRect bounds = new MutableGraphicsRect(mBounds);
        //Calculate size
        bounds.origin.x -= mAnchorPoint.x * bounds.size.width;
        bounds.origin.y -= mAnchorPoint.y * bounds.size.height;
        GraphicsRect transformedBounds = t.transformRect(bounds.toImmutableRect());
        //Calculate origin
        GraphicsPoint position = getPosition();
        GraphicsPoint origin = new GraphicsPoint(position.x - mAnchorPoint.x * transformedBounds.size.width,
                position.y - mAnchorPoint.y * transformedBounds.size.height);
        return new GraphicsRect(origin, transformedBounds.size);
    }

    @Override
    public void setFrame(GraphicsRect value) {
        GraphicsRect bounds = new GraphicsRect(mBounds.origin.x, mBounds.origin.y, value.size.width, value.size.height);
        setBounds(bounds);
        GraphicsPoint position = new GraphicsPoint(mAnchorPoint.x * mBounds.size.width + value.origin.x,
                mAnchorPoint.y * mBounds.size.height + value.origin.y);
        setPosition(position);
    }

    @Override
    public GraphicsAffineTransform affineTransform() {
        return getTransform().getAffine();
    }

    @Override
    public AnimationTransform3D getTransform() {
        return new AnimationTransform3D(mTransform);
    }

    @Override
    public void setTransform(AnimationTransform3D value) {
        mTransform = new AnimationTransform3D(value);
        if (mNeedsDisplayOnBoundsChange) {
            setNeedsDisplay(); //needsScreenUpdate will be set on redisplay
        }
        mChangeLogger.logLayerChange(this);
    }

    @Override
    public GraphicsPoint getPosition() {
        return mPosition;
    }

    @Override
    public void setPosition(GraphicsPoint value) {
        mPosition = value;
        mChangeLogger.logLayerChange(this);
    }

    @Override
    public float getZPosition() {
        return mZPosition;
    }

    @Override
    public void setZPosition(float value) {
        mZPosition = value;
    }

    @Override
    public float getAnchorPointZ() {
        return mAnchorPointZ;
    }

    @Override
    public void setAnchorPointZ(float value) {
        mAnchorPointZ = value;
    }

    @Override
    public GraphicsPoint getAnchorPoint() {
        return mAnchorPoint;
    }

    @Override
    public void setAnchorPoint(GraphicsPoint value) {
        if (value.equals(mAnchorPoint)) {
            return;
        }
        mAnchorPoint = value;
        mChangeLogger.logLayerChange(this);
    }

    @Override
    public AnimationTransform3D getSublayerTransform() {
        return mSublayerTransform;
    }

    @Override
    public void setSublayerTransform(AnimationTransform3D value) {
        mSublayerTransform = value;
    }

    @Override
    public void setAffineTransform(GraphicsAffineTransform transform) {
        setTransform(AnimationTransform3D.makeFromAffine(transform));
    }

    @Override
    public void addSublayer(AnimationLayer layer) {
        if (mSublayers == null) {
            mSublayers = new ArrayList<AnimationLayer>();
        }
        insertSublayer(layer, mSublayers.size());
    }

    @Override
    public void insertSublayer(AnimationLayer layer, int index) {
        if (!(layer instanceof AnimationLayerInternal)) {
            throw new NotImplementedException();
        }
        layer.setSuperlayer(this);
        if (mSublayers == null) {
            mSublayers = new ArrayList<AnimationLayer>();
        }
        mSublayers.add(index, layer);
        setNeedsLayout();
        mChangeLogger.logLayerHierarchyChange(layer, index, this);
        layer.setChangeLogger(mChangeLogger);
    }

    @Override
    public void insertSublayerBelow(AnimationLayer newLayer, AnimationLayer existingSublayer) {
        if (mSublayers == null) {
            mSublayers = new ArrayList<AnimationLayer>();
        }
        int index = mSublayers.indexOf(existingSublayer);
        if (index == -1) {
            throw new Error();
        }
        insertSublayer(newLayer, index);
    }

    @Override
    public void insertSublayerAbove(AnimationLayer newLayer, AnimationLayer existingSublayer) {
        int indexBelow = mSublayers.indexOf(existingSublayer);
        if (indexBelow == -1) {
            throw new Error();
        }
        insertSublayer(newLayer, indexBelow + 1);
    }

    @Override
    public void replaceSublayer(AnimationLayer oldLayer, AnimationLayer newLayer) {
        int index = mSublayers.indexOf(oldLayer);
        if (index == -1) {
            throw new Error();
        }
        oldLayer.removeFromSuperlayer();
        insertSublayer(newLayer, index);
    }

    @Override
    public void removeFromSuperlayer() {
        if (mSuperlayer == null) {
            return;
        }
        List<AnimationLayer> sublayers = mSuperlayer.getSublayers();
        sublayers.remove(this);
        mSuperlayer.setSublayers(sublayers);
        mSuperlayer = null;
        mChangeLogger.logLayerHierarchyChange(this, -1, null);
        setChangeLogger(ChangeLogger.NOOP);
    }

    @Override
    public List<AnimationLayer> getSublayers() {
        return mSublayers == null ? null : new ArrayList<AnimationLayer>(mSublayers);
    }

    @Override
    public void setSublayers(List<AnimationLayer> value) {
        for (AnimationLayer layer : value) {
            addSublayer(layer);
        }
    }

    @Override
    public void setNeedsDisplayInRect(GraphicsRect rect) {
        setNeedsDisplay();
    }

    @Override
    public boolean getNeedsDisplayOnBoundsChange() {
        return mNeedsDisplayOnBoundsChange;
    }

    @Override
    public void setNeedsDisplayOnBoundsChange(boolean value) {
        mNeedsDisplayOnBoundsChange = value;
    }

    @Override
    public void displayIfNeeded() {
        if (mNeedsDisplay) {
            display();
        }
    }

    @Override
    public void display() {
        if (mDelegate != null) {
            try {
                mDelegate.displayLayer(this);
                mNeedsDisplay = false;
                return;
            } catch (UnsupportedOperationException ex) {
                //Java/ObjC: if (mDelegate != null && mDelegate.respondsToSelector(CALayerDelegateSelectors
                // .SEL_DISPLAY_LAYER))
            }
        }
        GraphicsSize size = getBounds().size;
        int width = (int) (size.width * getContentsScale());
        int height = (int) (size.height * getContentsScale());
        GraphicsBitmapInfo bitmapInfo = new GraphicsBitmapInfo();
        bitmapInfo.alphaInfo = mOpaque ? GraphicsImageAlphaInfo.NONE : GraphicsImageAlphaInfo.PREMULTIPLIED_FIRST;
        GraphicsBitmapContext ctx = new GraphicsBitmapContext(null, width, height, 0, 0,
                GraphicsColorSpace.createDeviceRGB(), bitmapInfo);
//        if (getContentsAreFlipped()) {
//            ctx.scaleCTM(1.0F, -1.0F);
//            ctx.translateCTM(0, getBounds().size.height);
//        }
        ctx.scaleCTM(mContentsScale, mContentsScale);
        drawInContext(ctx);
        setContents(ctx.toImage());
        mNeedsDisplay = false;
    }

    @Override
    public float getContentsScale() {
        return mContentsScale;
    }

    @Override
    public void setContentsScale(float value) {
        mContentsScale = value;
    }

    @Override
    public GraphicsRect getBounds() {
        return mBounds;
    }

    @Override
    public void setBounds(GraphicsRect value) {
        if (mBounds.equals(value)) {
            return;
        }
        mBounds = value;
        if (mNeedsDisplayOnBoundsChange) {
            setNeedsDisplay();
        }
        setNeedsLayout();
    }

    @Override
    public void setNeedsLayout() {
        mNeedsLayout = true;
        mLayoutManager.invalidateLayout(this);
    }

    @Override
    public void setNeedsDisplay() {
        mNeedsDisplay = true;
    }

    @Override
    public void drawInContext(GraphicsContext context) {
        if (mDelegate != null) { // if (mDelegate != null && mDelegate.respondsToSelector(Selectors.drawLayerInContext))
            try {
                mDelegate.drawLayer(this, context);
            } catch (UnsupportedOperationException ex) {
                //empty
            }
        }
    }

    @Override
    public boolean needsDisplay() {
        return mNeedsDisplay;
    }

    @Override
    public void addAnimation(Animation animation, String key) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public Animation animationForKey(String key) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void removeAllAnimations() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void removeAnimation(String key) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public List<String> animationKeys() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void layoutIfNeeded() {
        if (!needsLayout()) {
            return;
        }
        AnimationLayer currLayer = this;
        while (currLayer.getSuperlayer() != null) {
            currLayer = getSuperlayer();
            if (!currLayer.needsLayout()) {
                break;
            }
        }
        currLayer.layoutSublayers();
    }

    @Override
    public boolean needsLayout() {
        return mNeedsLayout;
    }

    @Override
    public void layoutSublayers() {
        if (mDelegate != null && mDelegate instanceof AnimationLayerDelegate.SublayerLayouting) {
            // if (mDelegate != null && mDelegate.respondsToSelector(Selectors.layoutSublayersOfLayer))
            ((AnimationLayerDelegate.SublayerLayouting) mDelegate).layoutSublayersOfLayer(this);
            mNeedsLayout = false;
            return;
        }
        mLayoutManager.layoutSublayers(this);
        mNeedsLayout = false;
        mChangeLogger.logLayerChange(this);
    }

    @Override
    public AnimationLayer getSuperlayer() {
        return mSuperlayer;
    }

    @Override
    public GraphicsSize preferredFrameSize() {
        return mLayoutManager.preferredSize(this);
    }

    @Override
    public AnimationAction actionForKey(String key) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public Map<String, AnimationAction> getActions() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void setActions(Map<String, AnimationAction> value) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public GraphicsPoint convertPointFromLayer(GraphicsPoint point, AnimationLayer layer) {
        if (layer == null) {
            return point;
        }
        return TransformMath.createTransform(layer, this).transformPoint(point);
    }

    @Override
    public GraphicsPoint convertPointToLayer(GraphicsPoint point, AnimationLayer layer) {
        if (layer == null) {
            return point;
        }
        return TransformMath.createTransform(this, layer).transformPoint(point);
    }

    @Override
    public GraphicsRect convertRectFromLayer(GraphicsRect rect, AnimationLayer layer) {
        if (layer == null) {
            return rect;
        }
        return TransformMath.createTransform(layer, this).transformRect(rect);
    }

    @Override
    public GraphicsRect convertRectToLayer(GraphicsRect rect, AnimationLayer layer) {
        if (layer == null) {
            return rect;
        }
        return TransformMath.createTransform(this, layer).transformRect(rect);
    }

    @Override
    public double convertTimeFromLayer(double seconds, AnimationLayer layer) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public double convertTimeToLayer(double seconds, AnimationLayer layer) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public AnimationLayer hitTest(GraphicsPoint point) {
        point = convertPointFromLayer(point, mSuperlayer);
        if (!contains(point)) {
            return null;
        }
        for (AnimationLayer sublayer : new ReverseIterable<AnimationLayer>(mSublayers)) {
            AnimationLayer res = sublayer.hitTest(point);
            if (res != null) {
                return res;
            }
        }
        return this;
    }

    @Override
    public boolean contains(GraphicsPoint point) {
        return mBounds.contains(point);
    }

    @Override
    public GraphicsRect getVisibleRect() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void scrollPoint(GraphicsPoint point) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void scrollRectToVisible(GraphicsRect rect) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public boolean shouldArchiveValue(String key) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public String getName() {
        return mName;
    }

    @Override
    public void setName(String value) {
        mName = value;
    }

    @Override
    public void update(AnimationLayer layer) {
        GraphicsAnimationLayer l = (GraphicsAnimationLayer) layer;
        mDelegate = l.mDelegate;
        mBounds = l.mBounds;
        mAnchorPoint = l.mAnchorPoint;
        mTransform = l.mTransform;
        mSublayerTransform = l.mSublayerTransform;
        mContents = l.mContents;
        mContentsRect = l.mContentsRect;
        mContentsGravity = l.mContentsGravity;
        mContentsScale = l.mContentsScale;
        mMasksToBounds = l.mMasksToBounds;
        mCornerRadius = l.mCornerRadius;
        mBorderWidth = l.mBorderWidth;
        mBorderColor = l.mBorderColor;
        mBackgroundColor = l.mBackgroundColor;
        mOpaque = l.mOpaque;
        mContentsCenter = l.mContentsCenter;
        mHidden = l.mHidden;
        mOpacity = l.mOpacity;
        mZPosition = l.mZPosition;
        mAnchorPointZ = l.mAnchorPointZ;
        mMask = l.mMask;
        mDoubleSided = l.mDoubleSided;
        mName = l.mName;
        mPosition = l.mPosition;
    }

    @Override
    public AnimationLayerFactory getFactory() {
        return mAnimationLayerFactory;
    }

    @Override
    public void setSuperlayer(AnimationLayer layer) {
        mSuperlayer = layer;
    }

    @Override
    public void setChangeLogger(ChangeLogger logger) {
        if (mChangeLogger == logger) {
            return;
        }
        mChangeLogger = logger;
        mChangeLogger.logLayerChange(this);
        List<AnimationLayer> sublayers = mSublayers != null ? mSublayers : Collections.<AnimationLayer>emptyList();
        int i = 0;
        for (AnimationLayer sublayer : sublayers) {
            sublayer.setChangeLogger(logger);
            mChangeLogger.logLayerHierarchyChange(sublayer, i++, this);
        }
    }

    @Override
    public double getBeginTime() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void setBeginTime(double value) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public double getTimeOffset() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void setTimeOffset(double value) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public float getRepeatCount() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void setRepeatCount(float value) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public double getRepeatDuration() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void setRepeatDuration(double value) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public double getDuration() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void setDuration(double value) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public float getSpeed() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void setSpeed(float value) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public boolean getAutoreverses() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void setAutoreverses(boolean value) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public String getFillMode() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void setFillMode(String value) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void encode(Coder encoder) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public String toString() {
        return "CALayer[name=\"" + getName() + "\"]";
    }
}
