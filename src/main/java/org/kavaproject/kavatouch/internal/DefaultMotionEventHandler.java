/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.internal;

import android.util.Log;
import android.view.MotionEvent;
import org.kavaproject.kavatouch.DeviceHandle;
import org.kavaproject.kavatouch.coregraphics.GraphicsPoint;
import org.kavaproject.kavatouch.uikit.*;
import org.kavaproject.kavatouch.util.ReverseIterable;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class DefaultMotionEventHandler implements MotionEventHandler {
    private static final int MAX_POINT_CNT = 1;
    private final UITouchFactory mUITouchFactory;
    private final UIEventFactory mUIEventFactory;
    private final UIApplication mSharedApplication;
    private final UIScreen mMainScreen;
    private final DeviceHandle mDeviceHandle;
    private UIEvent mSharedEvent;
    private UITouch[] mCurrentTouches = new UITouch[10];
    private double[] mDownTimestamps = new double[10];
    private float mScale;
    private GraphicsPoint mScreenOffset;

    @Inject
    protected DefaultMotionEventHandler(UITouchFactory uiTouchFactory, UIEventFactory uiEventFactory,
                                        UIApplication sharedApplication, UIScreen mainScreen,
                                        DeviceHandle deviceHandle) {
        mUITouchFactory = uiTouchFactory;
        mUIEventFactory = uiEventFactory;
        mSharedApplication = sharedApplication;
        mMainScreen = mainScreen;
        mDeviceHandle = deviceHandle;
        mSharedEvent = this.mUIEventFactory.create();
        Arrays.fill(mDownTimestamps, Double.MIN_VALUE);
        for (int i = 0; i < MAX_POINT_CNT; i++) {
            mCurrentTouches[i] = this.mUITouchFactory.create();
        }
    }

    //Change MAX_POINT_CNT and setTouch to setTouches for multi touch
    @Override
    public void dispatch(List<MotionEvent> motionEvents) {
        mScale = mMainScreen.getScale();
        mScreenOffset = mDeviceHandle.getScreenOffset();
        MotionEvent moveEvent = null;
        for (MotionEvent motionEvent : motionEvents) {
            int action = (motionEvent.getAction() & MotionEvent.ACTION_MASK);
            if (action == MotionEvent.ACTION_MOVE || action == MotionEvent.ACTION_POINTER_DOWN) {
                moveEvent = motionEvent;
            } else {
                if (moveEvent != null) {
                    dispatch(moveEvent);
                    moveEvent = null;
                }
                dispatch(motionEvent);
            }
        }
        if (moveEvent != null) {
            dispatch(moveEvent);
        }
    }

    private void dispatch(MotionEvent motionEvent) {
        double timestamp = ((double) motionEvent.getEventTime()) / 1000; //Should be ok, uptimeMillis
        int pointerIndex = ((motionEvent.getAction() & MotionEvent.ACTION_POINTER_INDEX_MASK) >> MotionEvent
                .ACTION_POINTER_INDEX_SHIFT);
        int pointerId = motionEvent.getPointerId(pointerIndex);
        int action = (motionEvent.getAction() & MotionEvent.ACTION_MASK);
        int pointCnt = motionEvent.getPointerCount();
        if (pointCnt > MAX_POINT_CNT) {
            return;
        }
        UITouchPhase phase;
        switch (action) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                phase = UITouchPhase.BEGAN;
                if (Session.DEBUG) {
                    Log.d("KAVA", "Down");
                }
                break;
            case MotionEvent.ACTION_MOVE:
                phase = UITouchPhase.MOVED;
                if (Session.DEBUG) {
                    Log.d("KAVA", "Move");
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                phase = UITouchPhase.ENDED;
                if (Session.DEBUG) {
                    Log.d("KAVA", "Up");
                }
                break;
            case MotionEvent.ACTION_CANCEL:
                phase = UITouchPhase.CANCELLED;
                if (Session.DEBUG) {
                    Log.d("KAVA", "Cancel");
                }
                break;
            default:
                throw new IndexOutOfBoundsException();
        }
        UITouch currentTouch = null;
        GraphicsPoint currentLocation = null;
        boolean touchWasActive = false;
        if (pointerIndex <= MAX_POINT_CNT - 1) {
            for (int i = 0; i < pointCnt; i++) {
                int id = motionEvent.getPointerId(i);
                GraphicsPoint location = new GraphicsPoint((motionEvent.getX(i) + mScreenOffset.x) / mScale,
                        (motionEvent.getY(i) + mScreenOffset.y) / mScale);
                UITouch touch = mCurrentTouches[id];
                //TODO Implement multi-touch and remove null check
                if (touch == null) {
                    continue;
                }
                if (id == pointerId) {
                    if (phase == UITouchPhase.BEGAN) {
                        double downTimestamp = mDownTimestamps[id];
                        boolean isSuccessiveTap = timestamp - downTimestamp < 0.5;
                        touch.setTapCount(isSuccessiveTap ? touch.getTapCount() + 1 : 1);
                        mDownTimestamps[id] = timestamp;
                    }
                    touchWasActive = (touch.getPhase() == UITouchPhase.BEGAN || touch.getPhase() == UITouchPhase
                            .MOVED || touch.getPhase() == UITouchPhase.STATIONARY);
                    touch.setPhase(phase);
                    touch.setTimestamp(timestamp);
                    currentTouch = touch;
                    currentLocation = location;
                } else {
                    touch.setPhase(UITouchPhase.STATIONARY);
                    touch.setTimestamp(timestamp);
                }
                mSharedEvent.update(Collections.singleton(touch));
            }
        }
        //TODO Implement multi-touch and remove null check
        if (currentTouch == null) {
            return;
        }
        if (touchWasActive && (phase == UITouchPhase.ENDED || phase == UITouchPhase.MOVED)) {
            currentTouch.setLocationInWindow(currentTouch.getWindow().convertPointFromWindow(currentLocation, null));
            mSharedApplication.sendEvent(mSharedEvent);
        } else if (!mSharedApplication.isIgnoringInteractionEvents()) {
            UIWindow window = hitTest(mSharedApplication, mMainScreen, currentLocation);
            GraphicsPoint locationInWindow = window.convertPointFromWindow(currentLocation, null);
            UIView view = window.hitTest(locationInWindow, mSharedEvent);
            currentTouch.setWindow(window);
            currentTouch.setView(view);
            currentTouch.setLocationInWindow(locationInWindow);
            currentTouch.clearGestureRecognizers();
            UIView nextView = view;
            while (nextView != null) {
                currentTouch.addGestureRecognizers(nextView.getGestureRecognizers());
                nextView = nextView.getSuperview();
            }
            mSharedApplication.sendEvent(mSharedEvent);
        }
        return;
    }

    public static UIWindow hitTest(UIApplication application, UIScreen screen, GraphicsPoint clickPoint) {
        for (UIWindow window : ReverseIterable.wrap(application.getWindows())) {
            if (window.getScreen() != screen) {
                continue;
            }
            if (window.getFrame().contains(clickPoint)) {
                return window;
            }
        }
        return null;
    }
}
