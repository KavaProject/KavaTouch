/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.uikit;

import org.kavaproject.kavatouch.coregraphics.GraphicsPoint;
import org.kavaproject.kavatouch.uikit.staging.UIGestureRecognizer;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class DefaultUITouch implements UITouch {
    private final UITouchFactory mUITouchFactory;
    private UITouchPhase mPhase = UITouchPhase.CANCELLED;
    private GraphicsPoint mLocation = GraphicsPoint.ZERO;
    private GraphicsPoint mPreviousLocation;
    private int mTapCount;
    private double mTimestamp;
    private UIView mView;
    private UIWindow mWindow;
    private List<UIGestureRecognizer> mGestureRecognizers = new ArrayList<UIGestureRecognizer>();
    private EnumSet<UITouchFlag> mTouchFlags = EnumSet.noneOf(UITouchFlag.class);

    protected DefaultUITouch(UITouchFactory uiTouchFactory) {
        mUITouchFactory = uiTouchFactory;
    }

    @Override
    public GraphicsPoint locationInView(UIView view) {
        return locationInView(mLocation, view);
    }

    private GraphicsPoint locationInView(GraphicsPoint point, UIView view) {
        if (view != null) {
            point = view.convertPointFromView(point, mWindow);
        }
        return point;
    }

    @Override
    public GraphicsPoint previousLocationInView(UIView view) {
        return locationInView(mPreviousLocation, view);
    }

    @Override
    public UIView getView() {
        return mView;
    }

    @Override
    public void setView(UIView view) {
        mView = view;
    }

    @Override
    public UIWindow getWindow() {
        return mWindow;
    }

    @Override
    public void setWindow(UIWindow window) {
        mWindow = window;
    }

    @Override
    public int getTapCount() {
        return mTapCount;
    }

    @Override
    public void setTapCount(int tapCount) {
        mTapCount = tapCount;
    }

    @Override
    public void setLocationInWindow(GraphicsPoint location) {
        mPreviousLocation = mLocation;
        mLocation = location;
    }

    @Override
    public double getTimestamp() {
        return mTimestamp;
    }

    @Override
    public void setTimestamp(double timestamp) {
        mTimestamp = timestamp;
    }

    @Override
    public void addGestureRecognizers(Iterable<UIGestureRecognizer> recognizers) {
        for (UIGestureRecognizer recognizer : recognizers) {
            mGestureRecognizers.add(recognizer);
        }
    }

    @Override
    public void removeGestureRecognizers(Iterable<UIGestureRecognizer> recognizers) {
        for (UIGestureRecognizer recognizer : recognizers) {
            mGestureRecognizers.remove(recognizer);
        }
    }

    @Override
    public void clearGestureRecognizers() {
        mGestureRecognizers.clear();
    }

    @Override
    public UITouchPhase getPhase() {
        return mPhase;
    }

    @Override
    public void setPhase(UITouchPhase phase) {
        mPhase = phase;
    }

    @Override
    public EnumSet<UITouchFlag> getTouchFlags() {
        return mTouchFlags;
    }

    @Override
    public List<UIGestureRecognizer> getGestureRecognizers() {
        return new ArrayList<UIGestureRecognizer>(mGestureRecognizers);
    }

    @Override
    public UITouchFactory getFactory() {
        return mUITouchFactory;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String phase = "";
        switch (DefaultUITouch.this.mPhase) {
            case BEGAN:
                phase = "Began";
                break;
            case MOVED:
                phase = "Moved";
                break;
            case STATIONARY:
                phase = "Stationary";
                break;
            case ENDED:
                phase = "Ended";
                break;
            case CANCELLED:
                phase = "Cancelled";
                break;
        }
        return sb.append("<").append(getClass().getName()).append(": ").append("timestamp = ").append(DefaultUITouch
                .this.mTimestamp).append("; tapCount = ").append(DefaultUITouch.this.mTapCount).append("; phase = ")
                .append(DefaultUITouch.this.mPhase).append("; view = ").append(DefaultUITouch.this.mView).append("; "
                        + "window = ").append(DefaultUITouch.this.mWindow).append(">").toString();
    }
}
