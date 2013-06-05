/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.uikit;

import org.kavaproject.kavatouch.coreanimation.AnimationEngine;
import org.kavaproject.kavatouch.coregraphics.GraphicsPoint;
import org.kavaproject.kavatouch.coregraphics.GraphicsRect;
import org.kavaproject.kavatouch.coregraphics.MutableGraphicsPoint;
import org.kavaproject.kavatouch.foundation.Coder;
import org.kavaproject.kavatouch.foundation.NotificationCenter;
import org.kavaproject.kavatouch.runtime.MethodResolver;

import java.util.Set;

public class DefaultUIWindow extends DefaultUIView implements UIWindow {
    private final UIApplication mSharedApplication;
    private final NotificationCenter mDefaultNotificationCenter;
    private final UIApplicationWindows mUIApplicationWindows;
    private UIResponder mFirstResponder;
    private UIScreen mScreen;
    private UIViewController mRootViewController = null;

    protected DefaultUIWindow(GraphicsRect frame, UIWindowFactory uiWindowFactory, UIScreen mainScreen,
                              UIApplication sharedApplication, NotificationCenter defaultNotificationCenter,
                              UIGraphics uiGraphics, UIColorFactory uiColorFactory, MethodResolver methodResolver,
                              UIApplicationWindows uiApplicationWindows, AnimationEngine animationEngine) {
        super(frame, uiWindowFactory, uiGraphics, uiColorFactory, mainScreen, methodResolver);
        mSharedApplication = sharedApplication;
        mDefaultNotificationCenter = defaultNotificationCenter;
        mUIApplicationWindows = uiApplicationWindows;
        setHidden(true);
        setScreen(mainScreen);
        setOpaque(false);
        mUIApplicationWindows.addWindow(this);
        animationEngine.addModelTree(getLayer());
    }

    @Override
    public void setHidden(boolean value) {
        boolean wasHidden = isHidden();
        super.setHidden(value);
        if (wasHidden == value || mScreen == null) {
            return;
        }
        if (value) {
            mDefaultNotificationCenter.postNotificationName(NOTIFICATION_DID_BECOME_HIDDEN, this);
        } else {
            mDefaultNotificationCenter.postNotificationName(NOTIFICATION_DID_BECOME_VISIBLE, this);
        }
    }

    @Override
    public UIWindow getWindow() {
        return this;
    }

    @Override
    public void addSubview(UIView view) {
        super.addSubview(view);
        //TODO Cache transform
    }

    @Override
    public void removeFromSuperview() {
    }

    @Override
    public UIView getSuperview() {
        return null;
    }

    @Override
    public UIResponder nextResponder() {
        return mSharedApplication;
    }

    @Override
    public UIWindowFactory getFactory() {
        return (UIWindowFactory) super.getFactory();
    }

    protected DefaultUIWindow(Coder decoder, UIWindowFactory uiWindowFactory, UIScreen mainScreen,
                              UIApplication sharedApplication, NotificationCenter defaultNotificationCenter,
                              UIGraphics uiGraphics, UIColorFactory uiColorFactory, MethodResolver methodResolver,
                              UIApplicationWindows uiApplicationWindows, AnimationEngine animationEngine) {
        super(decoder, uiWindowFactory, uiGraphics, uiColorFactory, mainScreen, methodResolver);
        mSharedApplication = sharedApplication;
        mDefaultNotificationCenter = defaultNotificationCenter;
        mUIApplicationWindows = uiApplicationWindows;
        animationEngine.addModelTree(getLayer());
        //TODO
    }

    @Override
    public float getWindowLevel() {
        return getLayer().getZPosition();
    }

    @Override
    public void setWindowLevel(float value) {
        getLayer().setZPosition(value);
    }

    @Override
    public UIScreen getScreen() {
        return mScreen;
    }

    @Override
    public void setScreen(UIScreen value) {
        if (mScreen == value) {
            return;
        }
        mDefaultNotificationCenter.removeObserver(this, UIScreen.NOTIFICATION_MODE_DID_CHANGE, mScreen);
        boolean wasHidden = isHidden();
        setHidden(true);
        getLayer().removeFromSuperlayer();
        mScreen = value;
        if (!wasHidden) {
            setHidden(false);
        }
        mDefaultNotificationCenter.addObserver(this, UIWindowFactory.SEL_SCREEN_MODE_CHANGED_NOTIFICATION,
                UIScreen.NOTIFICATION_MODE_DID_CHANGE, mScreen);
    }

    @Override
    public UIViewController getRootViewController() {
        return mRootViewController;
    }

    @Override
    public void setRootViewController(UIViewController value) {
        if (mRootViewController != value) {
            for (UIView subview : getSubviews()) {
                subview.removeFromSuperview();
            }
            mRootViewController = value;
            mRootViewController.getView().setFrame(getBounds());
            addSubview(mRootViewController.getView());
        }
        mRootViewController = value;
    }

    @Override
    public boolean isKeyWindow() {
        return mUIApplicationWindows.getKeyWindow() == this;
    }

    @Override
    public void makeKeyAndVisible() {
        setHidden(false);
        makeKeyWindow();
    }

    @Override
    public void becomeKeyWindow() {
        if (mFirstResponder instanceof UIWindow) {
            ((UIWindow) mFirstResponder).becomeKeyWindow();
        }
        mDefaultNotificationCenter.postNotificationName(NOTIFICATION_DID_BECOME_KEY, this);
    }

    @Override
    public void makeKeyWindow() {
        if (isKeyWindow()) {
            return;
        }
        UIWindow oldKeyWindow = mUIApplicationWindows.getKeyWindow();
        if (oldKeyWindow != null) {
            oldKeyWindow.resignKeyWindow();
        }
        mUIApplicationWindows.setKeyWindow(this);
        becomeKeyWindow();
    }

    @Override
    public void resignKeyWindow() {
        if (mFirstResponder instanceof UIWindow) {
            ((UIWindow) mFirstResponder).resignKeyWindow();
        }
        mDefaultNotificationCenter.postNotificationName(NOTIFICATION_DID_RESIGN_KEY, this);
    }

    @Override
    public GraphicsPoint convertPointToWindow(GraphicsPoint point, UIWindow window) {
        if (window == this) {
            return point;
        }
        MutableGraphicsPoint convertedPoint = new MutableGraphicsPoint(point);
        convertedPoint.x += getFrame().origin.x;
        convertedPoint.y += getFrame().origin.y;
        if (window != null) {
            convertedPoint.x -= getWindow().getFrame().origin.x;
            convertedPoint.y -= getWindow().getFrame().origin.y;
        }
        return convertedPoint.toImmutablePoint();
    }

    @Override
    public GraphicsPoint convertPointFromWindow(GraphicsPoint point, UIWindow window) {
        if (window == this) {
            return point;
        }
        MutableGraphicsPoint convertedPoint = new MutableGraphicsPoint(point);
        if (window != null) {
            convertedPoint.x += window.getFrame().origin.x;
            convertedPoint.y += window.getFrame().origin.y;
        }
        convertedPoint.x -= getFrame().origin.x;
        convertedPoint.y -= getFrame().origin.y;
        return convertedPoint.toImmutablePoint();
    }

    @Override
    public GraphicsRect convertRectToWindow(GraphicsRect rect, UIWindow window) {
        GraphicsPoint top = convertPointToView(new GraphicsPoint(rect.getMinX(), rect.getMinY()), window);
        GraphicsPoint bottom = convertPointToView(new GraphicsPoint(rect.getMaxX(), rect.getMaxY()), window);
        return new GraphicsRect(top.x, top.y, bottom.x - top.x, bottom.y - top.y);
    }

    @Override
    public GraphicsRect convertRectFromWindow(GraphicsRect rect, UIWindow window) {
        GraphicsPoint top = convertPointFromView(new GraphicsPoint(rect.getMinX(), rect.getMinY()), window);
        GraphicsPoint bottom = convertPointFromView(new GraphicsPoint(rect.getMaxX(), rect.getMaxY()), window);
        return new GraphicsRect(top.x, top.y, bottom.x - top.x, bottom.y - top.y);
    }

    @Override
    public void sendEvent(UIEvent event) {
        if (event.type() == UIEventType.TOUCHES) {
            Set<UITouch> touches = event.touchesForWindow(this);
//            Set<UIGestureRecognizer> gestureRecognizers = new HashSet<UIGestureRecognizer>();
//            for (UITouch touch : touches) {
//                gestureRecognizers.addAll(touch.getGestureRecognizers());
//            }
            for (UITouch touch : touches) {
                UIView view = touch.getView();
                switch (touch.getPhase()) {
                    case BEGAN:
                        view.onTouchesBegan(touches, event);
                        return;
                    case MOVED:
                        view.onTouchesMoved(touches, event);
                        return;
                    case STATIONARY:
                        continue;
                    case ENDED:
                        view.onTouchesEnded(touches, event);
                        return;
                    case CANCELLED:
                        view.onTouchesCancelled(touches, event);
                        return;
                }
            }
        }
    }

    @Override
    public UIResponder getFirstResponder() {
        return mFirstResponder;
    }

    @Override
    public void setFirstResponder(UIResponder firstResponder) {
        this.mFirstResponder = firstResponder;
    }
}
