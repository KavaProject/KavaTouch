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
import org.kavaproject.kavatouch.coregraphics.GraphicsContext;
import org.kavaproject.kavatouch.coregraphics.GraphicsPoint;
import org.kavaproject.kavatouch.coregraphics.GraphicsRect;
import org.kavaproject.kavatouch.coregraphics.GraphicsSize;
import org.kavaproject.kavatouch.foundation.Coder;
import org.kavaproject.kavatouch.runtime.MethodResolver;

import java.util.ArrayList;
import java.util.List;

public class DefaultUIImageView extends DefaultUIView implements UIImageView {
    private final UIImageGraphics mUIImageGraphics;
    private Double mAnimationDuration = null;
    private List<UIImage> mAnimationImages = null;
    private int mAnimationRepeatCount = 0;
    private boolean mHighlighted;
    private List<UIImage> mHighlightedAnimationImages = null;
    private UIImage mHighlightedImage;
    private UIImage mImage;

    protected DefaultUIImageView(GraphicsRect frame, UIImageViewFactory uiImageViewFactory, UIGraphics uiGraphics,
                                 UIImageGraphics uiImageGraphics, UIColorFactory uiColorFactory, UIScreen mainScreen,
                                 MethodResolver methodResolver) {
        super(frame, uiImageViewFactory, uiGraphics, uiColorFactory, mainScreen, methodResolver);
        mUIImageGraphics = uiImageGraphics;
    }

    protected DefaultUIImageView(Coder decoder, UIImageViewFactory uiImageViewFactory, UIGraphics uiGraphics,
                                 UIImageGraphics uiImageGraphics, UIColorFactory uiColorFactory, UIScreen mainScreen,
                                 MethodResolver methodResolver) {
        super(decoder, uiImageViewFactory, uiGraphics, uiColorFactory, mainScreen, methodResolver);
        mUIImageGraphics = uiImageGraphics;
        //TODO
    }

    @Override
    public UIImage getImage() {
        return mImage;
    }

    @Override
    public void setImage(UIImage value) {
        if (value == mImage) {
        }
        mImage = value;
        if (!mHighlighted || mHighlightedImage == null) {
            setNeedsDisplay();
        }
    }

    @Override
    public UIImage getHighlightedImage() {
        return mHighlightedImage; //TODO
    }

    @Override
    public void setHighlightedImage(UIImage value) {
        if (value == mHighlightedImage) {
            return;
        }
        mHighlightedImage = value; //TODO
        if (mHighlighted) {
            setNeedsDisplay();
        }
    }

    @Override
    public List<UIImage> getAnimationImages() {
        return new ArrayList<UIImage>(mAnimationImages); //TODO
    }

    @Override
    public void setAnimationImages(List<UIImage> value) {
        mAnimationImages = new ArrayList<UIImage>(value); //TODO
    }

    @Override
    public List<UIImage> getHighlightedAnimationImages() {
        return new ArrayList<UIImage>(mHighlightedAnimationImages); //TODO
    }

    @Override
    public void setHighlightedAnimationImages(List<UIImage> value) {
        mHighlightedAnimationImages = new ArrayList<UIImage>(value); //TODO
    }

    @Override
    public double getAnimationDuration() {
        return mAnimationDuration; //TODO
    }

    @Override
    public void setAnimationDuration(double value) {
        mAnimationDuration = value; //TODO
    }

    @Override
    public int getAnimationRepeatCount() {
        return mAnimationRepeatCount; //TODO
    }

    @Override
    public void setAnimationRepeatCount(int value) {
        mAnimationRepeatCount = value; //TODO
    }

    @Override
    public void stopAnimating() {
        //TODO
    }

    @Override
    public boolean isHighlighted() {
        return mHighlighted;
    }

    @Override
    public void setHighlighted(boolean value) {
        if (value == mHighlighted) {
            return;
        }
        mHighlighted = value;
        setNeedsDisplay();
        if (isAnimating()) {
            startAnimating();
        }
    }

    @Override
    public boolean isAnimating() {
        return false; //TODO
    }

    @Override
    public void startAnimating() {
        //TODO
    }

    @Override
    public boolean isUserInteractionEnabled() {
        return super.isUserInteractionEnabled();
    }

    @Override
    public void setUserInteractionEnabled(boolean value) {
        super.setUserInteractionEnabled(value);
    }

    @Override
    public GraphicsSize sizeThatFits(GraphicsSize size) {
        return mImage != null ? mImage.getSize() : GraphicsSize.ZERO;
    }

    @Override
    public void displayLayer(AnimationLayer layer) {
        UIImage displayImage = (mHighlighted && mHighlightedImage != null) ? mHighlightedImage : mImage;
        layer.setContentsScale(displayImage.getScale());
        GraphicsSize size = displayImage.getSize();
        mUIImageGraphics.beginImageContext(size, true, displayImage.getScale());
        GraphicsContext context = getUIGraphics().getCurrentContext();
        context.drawImage(new GraphicsRect(GraphicsPoint.ZERO, size), displayImage.toGraphicsImage());
        layer.setContents(mUIImageGraphics.getImageFromCurrentImageContext().toGraphicsImage());
        getUIGraphics().endImageContext();
    }

    @Override
    public UIImageViewFactory getFactory() {
        return (UIImageViewFactory) super.getFactory();
    }
}
