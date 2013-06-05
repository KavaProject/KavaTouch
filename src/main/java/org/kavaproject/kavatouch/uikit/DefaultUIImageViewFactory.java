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
import org.kavaproject.kavatouch.coregraphics.GraphicsPoint;
import org.kavaproject.kavatouch.coregraphics.GraphicsRect;
import org.kavaproject.kavatouch.runtime.MethodResolver;
import org.kavaproject.kavatouch.uikit.staging.UIImageViewAppearanceProxy;
import org.kavaproject.kavatouch.util.NotImplementedException;

import javax.inject.Inject;

public class DefaultUIImageViewFactory extends DefaultUIViewFactory implements UIImageViewFactory {
    private final UIImageGraphics mUIImageGraphics;

    @Inject
    protected DefaultUIImageViewFactory(AnimationLayerFactory animationLayerFactory, UIGraphics uiGraphics,
                                        UIImageGraphics uiImageGraphics, UIColorFactory uiColorFactory,
                                        UIScreen mainScreen, MethodResolver methodResolver) {
        super(animationLayerFactory, uiGraphics, uiColorFactory, mainScreen, methodResolver);
        mUIImageGraphics = uiImageGraphics;
    }

    @Override
    public UIImageView create(UIImage image) {
        GraphicsRect frame = new GraphicsRect(GraphicsPoint.ZERO, image.getSize());
        UIImageView imageView = create(frame);
        imageView.setImage(image);
        return imageView;
    }

    @Override
    public UIImageView create(UIImage image, UIImage highlightedImage) {
        GraphicsRect frame = new GraphicsRect(GraphicsPoint.ZERO, image.getSize());
        UIImageView imageView = create(frame);
        imageView.setImage(image);
        imageView.setHighlightedImage(highlightedImage);
        return imageView;
    }

    @Override
    public UIImageView create(GraphicsRect frame) {
        return new DefaultUIImageView(frame, this, getUIGraphics(), mUIImageGraphics, getUIColorFactory(),
                getMainScreen(), getMethodResolver());
    }

    @Override
    public UIImageView create() {
        return create(GraphicsRect.NULL);
    }

    @Override
    public UIImageViewAppearanceProxy appearance() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public UIImageViewAppearanceProxy appearanceWhenContainedIn(UIAppearanceContainerFactory... containers) {
        throw new NotImplementedException(); //TODO
    }


}
