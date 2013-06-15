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
import org.kavaproject.kavatouch.foundation.CodingFactory;
import org.kavaproject.kavatouch.foundation.FoundationDate;
import org.kavaproject.kavatouch.internal.OccClass;
import org.kavaproject.kavatouch.internal.OccClassMethod;
import org.kavaproject.kavatouch.internal.OccInstanceMethod;
import org.kavaproject.kavatouch.runtime.*;
import org.kavaproject.kavatouch.uikit.staging.UIViewAnimationCurve;
import org.kavaproject.kavatouch.uikit.staging.UIViewAnimationOptions;
import org.kavaproject.kavatouch.uikit.staging.UIViewAnimationTransition;

@OccClass("UIView")
public interface UIViewFactory extends UIResponderFactory, UIAppearanceContainerFactory, UIAppearanceFactory,
        CodingFactory, Factory {
    SEL SEL_DRAW_RECT = SEL.getInstance("drawRect:");
    IMP IMP_DRAW_RECT = new IMP1<UIView, GraphicsRect>() {
        @Override
        public void invoke(UIView receiver, SEL sel, GraphicsRect cgRect) {
            receiver.draw(cgRect);
        }
    };

    UIView create();

    @OccInstanceMethod("initWithFrame:")
    UIView create(GraphicsRect frame);

    @Override
    UIView create(Coder coder);

    @OccClassMethod("layerClass")
    AnimationLayerFactory layerFactory();

    @OccClassMethod("requiresConstraintBasedLayout")
    boolean requiresConstraintBasedLayout();

    @OccClassMethod("animateWithDuration:delay:options:animations:completion:")
    void animate(double duration, double delay, UIViewAnimationOptions options, Runnable animations,
                 Runnable1<Boolean> completion);

    @OccClassMethod("animateWithDuration:animations:completion:")
    void animate(double duration, Runnable animations, Runnable1<Boolean> completion);

    @OccClassMethod("animateWithDuration:animations:")
    void animate(double duration, Runnable animations);

    @OccClassMethod("transitionWithView:duration:options:animations:completion:")
    void transition(UIView view, double duration, UIViewAnimationOptions options, Runnable animations,
                    Runnable1<Boolean> completion);

    @OccClassMethod("transitionFromView:toView:duration:options:completion:")
    void transition(UIView fromView, UIView toView, double duration, UIViewAnimationOptions options,
                    Runnable1<Boolean> completion);

    @OccClassMethod("beginAnimations:context:")
    void beginAnimations(String animationID, Object context);

    @OccClassMethod("commitAnimations")
    void commitAnimations();

    @OccClassMethod("setAnimationStartDate:")
    void setAnimationStartDate(FoundationDate startTime);

    @OccClassMethod("setAnimationsEnabled:")
    void setAnimationsEnabled(boolean enabled);

    @OccClassMethod("setAnimationDelegate:")
    void setAnimationDelegate(Object delegate);

    @OccClassMethod("setAnimationWillStartSelector:")
    void setAnimationWillStartSelector(SEL selector);

    @OccClassMethod("setAnimationDidStopSelector:")
    void setAnimationDidStopSelector(SEL selector);

    @OccClassMethod("setAnimationDuration:")
    void setAnimationDuration(double duration);

    @OccClassMethod("setAnimationDelay:")
    void setAnimationDelay(double delay);

    @OccClassMethod("setAnimationCurve:")
    void setAnimationCurve(UIViewAnimationCurve curve);

    @OccClassMethod("setAnimationRepeatCount:")
    void setAnimationRepeatCount(float repeatCount);

    @OccClassMethod("setAnimationRepeatAutoreverses:")
    void setAnimationRepeatAutoreverses(boolean repeatAutoreverses);

    @OccClassMethod("setAnimationBeginsFromCurrentState:")
    void setAnimationBeginsFromCurrentState(boolean beginsFromCurrentState);

    @OccClassMethod("setAnimationTransition:forView:cache:")
    void setAnimationTransitionForViewCache(UIViewAnimationTransition transition, UIView view, boolean cache);

    @OccClassMethod("areAnimationsEnabled")
    boolean areAnimationsEnabled();

    @Override
    UIView appearance();

    @Override
    UIView appearance(UIAppearanceContainerFactory... containers);
}
