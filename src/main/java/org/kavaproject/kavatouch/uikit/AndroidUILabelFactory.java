/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.uikit;

import org.kavaproject.kavatouch.DeviceHandle;
import org.kavaproject.kavatouch.coreanimation.AnimationLayerFactory;
import org.kavaproject.kavatouch.coregraphics.GraphicsRect;
import org.kavaproject.kavatouch.foundation.Coder;
import org.kavaproject.kavatouch.runtime.MethodResolver;
import org.kavaproject.kavatouch.uikit.staging.UILabelAppearanceProxy;
import org.kavaproject.kavatouch.util.NotImplementedException;

import javax.inject.Inject;

public class AndroidUILabelFactory extends DefaultUIViewFactory implements UILabelFactory {
    private final UIFontFactory mUIFontFactory;
    private final DeviceHandle mDeviceHandle;

    @Inject
    protected AndroidUILabelFactory(UIFontFactory uiFontFactory, AnimationLayerFactory animationLayerFactory,
                                    UIGraphics uiGraphics, UIColorFactory uiColorFactory, UIScreen mainScreen,
                                    MethodResolver methodResolver, DeviceHandle deviceHandle) {
        super(animationLayerFactory, uiGraphics, uiColorFactory, mainScreen, methodResolver);
        mUIFontFactory = uiFontFactory;
        mDeviceHandle = deviceHandle;
    }

    @Override
    public UILabel create(GraphicsRect frame) {
        return new AndroidUILabel(frame, this, mUIFontFactory, getUIGraphics(), getUIColorFactory(), getMainScreen(),
                getMethodResolver(), mDeviceHandle);
    }

    @Override
    public UILabel create() {
        return create(GraphicsRect.NULL);
    }

    @Override
    public UILabelAppearanceProxy appearance() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public UILabelAppearanceProxy appearanceWhenContainedIn(UIAppearanceContainerFactory... containers) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public UILabel create(Coder decoder) {
        return new AndroidUILabel(decoder, this, mUIFontFactory, getUIGraphics(), getUIColorFactory(), getMainScreen(), getMethodResolver(), mDeviceHandle);
    }
}
