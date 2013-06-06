/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.uikit;

import org.kavaproject.kavatouch.coreanimation.AnimationLayerFactory;
import org.kavaproject.kavatouch.coregraphics.GraphicsRect;
import org.kavaproject.kavatouch.foundation.Coder;
import org.kavaproject.kavatouch.foundation.FoundationDate;
import org.kavaproject.kavatouch.runtime.MethodResolver;
import org.kavaproject.kavatouch.runtime.Runnable1;
import org.kavaproject.kavatouch.runtime.SEL;
import org.kavaproject.kavatouch.uikit.staging.UIViewAnimationCurve;
import org.kavaproject.kavatouch.uikit.staging.UIViewAnimationOptions;
import org.kavaproject.kavatouch.uikit.staging.UIViewAnimationTransition;
import org.kavaproject.kavatouch.uikit.staging.UIViewAppearanceProxy;
import org.kavaproject.kavatouch.util.NotImplementedException;

import org.kavaproject.kavatouch.util.inject.Inject;

public class DefaultUIViewFactory extends SimpleUIResponderFactory implements UIViewFactory {
    private final AnimationLayerFactory mAnimationLayerFactory;
    private final UIGraphics mUIGraphics;
    private final UIColorFactory mUIColorFactory;
    private final UIScreen mMainScreen;

    @Inject
    protected DefaultUIViewFactory(AnimationLayerFactory animationLayerFactory, UIGraphics uiGraphics,
                                   UIColorFactory uiColorFactory, UIScreen mainScreen, MethodResolver methodResolver) {
        super(methodResolver);
        mAnimationLayerFactory = animationLayerFactory;
        mUIGraphics = uiGraphics;
        mUIColorFactory = uiColorFactory;
        mMainScreen = mainScreen;
    }

    @Override
    public UIView create(GraphicsRect frame) {
        return new DefaultUIView(frame, this, mUIGraphics, mUIColorFactory, mMainScreen, getMethodResolver());
    }

    @Override
    public AnimationLayerFactory layerFactory() {
        return mAnimationLayerFactory;
    }

    @Override
    public boolean requiresConstraintBasedLayout() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void animate(double duration, double delay, UIViewAnimationOptions options, Runnable animations,
                        Runnable1<Boolean> completion) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void animate(double duration, Runnable animations, Runnable1<Boolean> completion) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void animate(double duration, Runnable animations) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void transition(UIView view, double duration, UIViewAnimationOptions options, Runnable animations,
                           Runnable1<Boolean> completion) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void transition(UIView fromView, UIView toView, double duration, UIViewAnimationOptions options,
                           Runnable1<Boolean> completion) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void beginAnimations(String animationID, Object context) {
        //TODO
    }

    @Override
    public void commitAnimations() {
        //TODO
    }

    @Override
    public void setAnimationStartDate(FoundationDate startTime) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void setAnimationsEnabled(boolean enabled) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void setAnimationDelegate(Object delegate) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void setAnimationWillStartSelector(SEL selector) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void setAnimationDidStopSelector(SEL selector) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void setAnimationDuration(double duration) {
        //TODO
    }

    @Override
    public void setAnimationDelay(double delay) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void setAnimationCurve(UIViewAnimationCurve curve) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void setAnimationRepeatCount(float repeatCount) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void setAnimationRepeatAutoreverses(boolean repeatAutoreverses) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void setAnimationBeginsFromCurrentState(boolean beginsFromCurrentState) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void setAnimationTransitionForViewCache(UIViewAnimationTransition transition, UIView view, boolean cache) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public boolean areAnimationsEnabled() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public UIView create() {
        return new DefaultUIView(GraphicsRect.NULL, this, mUIGraphics, mUIColorFactory, mMainScreen,
                getMethodResolver());
    }

    @Override
    public UIViewAppearanceProxy appearance() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public UIViewAppearanceProxy appearanceWhenContainedIn(UIAppearanceContainerFactory... containers) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public UIView create(Coder decoder) {
        return new DefaultUIView(decoder, this, mUIGraphics, mUIColorFactory, mMainScreen, getMethodResolver());
    }

    protected UIScreen getMainScreen() {
        return mMainScreen;
    }

    protected UIGraphics getUIGraphics() {
        return mUIGraphics;
    }

    protected UIColorFactory getUIColorFactory() {
        return mUIColorFactory;
    }
}
