/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.uikit;

import org.kavaproject.kavatouch.internal.*;
import org.kavaproject.kavatouch.runtime.Creatable;

import java.util.List;

@Header("UIImageView")
@OccClass("UIImageView")
public interface UIImageView extends UIResponder, UIView, Creatable {
    @OccInstanceProperty(value = "image", argumentSemantic = ArgumentSemantic.RETAIN)
    UIImage getImage();

    @OccInstanceProperty(value = "image", argumentSemantic = ArgumentSemantic.RETAIN)
    void setImage(UIImage value);

    @OccInstanceProperty(value = "highlightedImage", argumentSemantic = ArgumentSemantic.RETAIN)
    UIImage getHighlightedImage();

    @OccInstanceProperty(value = "highlightedImage", argumentSemantic = ArgumentSemantic.RETAIN)
    void setHighlightedImage(UIImage value);

    @OccInstanceProperty(value = "animationImages", argumentSemantic = ArgumentSemantic.COPY)
    List<UIImage> getAnimationImages();

    @OccInstanceProperty(value = "animationImages", argumentSemantic = ArgumentSemantic.COPY)
    void setAnimationImages(List<UIImage> value);

    @OccInstanceProperty(value = "highlightedAnimationImages", argumentSemantic = ArgumentSemantic.COPY)
    List<UIImage> getHighlightedAnimationImages();

    @OccInstanceProperty(value = "highlightedAnimationImages", argumentSemantic = ArgumentSemantic.COPY)
    void setHighlightedAnimationImages(List<UIImage> value);

    @OccInstanceProperty(value = "animationDuration")
    double getAnimationDuration();

    @OccInstanceProperty(value = "animationDuration")
    void setAnimationDuration(double value);

    @OccInstanceProperty(value = "animationRepeatCount")
    int getAnimationRepeatCount();

    @OccInstanceProperty(value = "animationRepeatCount")
    void setAnimationRepeatCount(int value);

    @OccInstanceMethod("stopAnimating")
    void stopAnimating();

    @OccInstanceProperty(value = "highlighted")
    boolean isHighlighted();

    @OccInstanceProperty(value = "highlighted")
    void setHighlighted(boolean value);

    @OccInstanceMethod("isAnimating")
    boolean isAnimating();

    @OccInstanceMethod("startAnimating")
    void startAnimating();

    @Override
    UIImageViewFactory getFactory();
}
