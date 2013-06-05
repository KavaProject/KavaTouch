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
import org.kavaproject.kavatouch.coregraphics.GraphicsColor;
import org.kavaproject.kavatouch.coregraphics.GraphicsColorSpace;
import org.kavaproject.kavatouch.foundation.Coder;
import org.kavaproject.kavatouch.uikit.staging.ImageColor;
import org.kavaproject.kavatouch.util.NotImplementedException;

import javax.inject.Inject;

import static org.kavaproject.kavatouch.coregraphics.GraphicsColorSpace.createDeviceRGB;

public class DefaultUIColorFactory implements UIColorFactory {
    private final UIImageFactory mUIImageFactory;
    private final UIGraphics mUIGraphics;
    private final DeviceHandle mDeviceHandle;

    @Inject
    protected DefaultUIColorFactory(UIImageFactory uiImageFactory, UIGraphics uiGraphics, DeviceHandle deviceHandle) {
        mUIImageFactory = uiImageFactory;
        mUIGraphics = uiGraphics;
        mDeviceHandle = deviceHandle;
    }

    @Override
    public UIColor create(Coder decoder) {
        return new DefaultUIColor(decoder, this, mUIGraphics);
    }

    @Override
    public UIColor createWithHSBA(float hue, float saturation, float brightness, float alpha) {
        float[] rgb = new float[3];
        HSBToRGB(hue, saturation, brightness, rgb);
        return createWithRGBA(rgb[0], rgb[1], rgb[2], alpha);
    }

    @Override
    public UIColor createWithRGBA(float red, float green, float blue, float alpha) {
        float[] components = {red, green, blue, alpha};
        GraphicsColorSpace colorSpace = createDeviceRGB();
        return create(new GraphicsColor(colorSpace, components));
    }

    @Override
    public UIColor create(GraphicsColor graphicsColor) {
        return new DefaultUIColor(graphicsColor, this, mUIGraphics);
    }

    @Override
    public UIColor create(ImageColor ciColor) {
        throw new NotImplementedException();
    }

    @Override
    public UIColor black() {
        return createWithWhiteAlpha(0, 1);
    }

    @Override
    public UIColor createWithWhiteAlpha(float white, float alpha) {
        float components[] = {white, alpha};
        GraphicsColorSpace colorSpace = GraphicsColorSpace.createDeviceGray();
        return create(new GraphicsColor(colorSpace, components));
    }

    @Override
    public UIColor darkGray() {
        return createWithWhiteAlpha(1F / 3, 1);
    }

    @Override
    public UIColor lightGray() {
        return createWithWhiteAlpha(2F / 3, 1);
    }

    @Override
    public UIColor white() {
        return createWithWhiteAlpha(1, 1);
    }

    @Override
    public UIColor gray() {
        return createWithWhiteAlpha(1F / 2, 1);
    }

    @Override
    public UIColor red() {
        return createWithRGBA(1, 0, 0, 1);
    }

    @Override
    public UIColor green() {
        return createWithRGBA(0, 1, 0, 1);
    }

    @Override
    public UIColor blue() {
        return createWithRGBA(0, 0, 1, 1);
    }

    @Override
    public UIColor cyan() {
        return createWithRGBA(0, 1, 1, 1);
    }

    @Override
    public UIColor yellow() {
        return createWithRGBA(1, 1, 0, 1);
    }

    @Override
    public UIColor magenta() {
        return createWithRGBA(1, 0, 1, 1);
    }

    @Override
    public UIColor orange() {
        return createWithRGBA(1, 0.5F, 0, 1);
    }

    @Override
    public UIColor purple() {
        return createWithRGBA(0.5F, 0, 0.5F, 1);
    }

    @Override
    public UIColor brown() {
        return createWithRGBA(0.6F, 0.4F, 0.2F, 1);
    }

    @Override
    public UIColor lightText() {
        return createWithWhiteAlpha(1, 0.6f);
    }

    @Override
    public UIColor darkText() {
        return createWithWhiteAlpha(0, 1);
    }

    @Override
    public UIColor groupTableViewBackgroundColor() {
        //see http://iphonedevwiki.net/index.php/UIColor
//        return sPinStripeColor; //iPhone
        return clear(); //iPad
    }

    @Override
    public UIColor clear() {
        return createWithWhiteAlpha(0, 0);
    }

    @Override
    public UIColor viewFlipsideBackgroundColor() {
        return createWithPatternImage(mUIImageFactory.create("UIStockImageWidgetBacksideBackground.png"));
    }

    @Override
    public UIColor createWithPatternImage(UIImage image) {
//        CGSize size = image.size();
//        CGPatternCallbacks callbacks = new CGPatternCallbacks(0, drawPattern, releasePatternInfo);
//        CGPatternRef pattern= CGPattern.CGPatternCreate(
//                image,
//                CGRectMake(0, 0, size.width, size.height),
//                CGAffineTransformIdentity(),
//                size.width, size.height, CGPattern.kCGPatternTilingNoDistortion, true, callbacks);
//        CGColorSpace colorSpace = CGColorSpaceCreateDeviceRGB(); //PatternColorSpace
//        float[] components = {1,1,1,1};
//        mCGColor = CGColorCreateWithPattern(colorSpace, pattern, components);
//        return this;
        throw new NotImplementedException();
    }

    @Override
    public UIColor scrollViewTexturedBackgroundColor() {
        return createWithPatternImage(mUIImageFactory.create("UIStockImageScrollViewTexturedBackgroundColor" + ".png"));
    }

    @Override
    public UIColor underPageBackgroundColor() {
        return createWithPatternImage(mUIImageFactory.create("UIStockImageUnderPageBackground.png"));
    }

    private static final void HSBToRGB(float hue, float saturation, float brightness, float[] rgb) {
        float red = brightness, green = brightness, blue = brightness;
        float H = hue * 360; // convert to degrees
        float S = saturation;
        float V = brightness;
        float m = 0; // the brightness delta.
        if (V != 0) {
            // We have enough color information to do a conversion
            float C = V * S;
            H %= 360;
            H /= 60; // convert to hexagonal segments
            int hueFace = (int) H; // cube face index
            float X = C * (1 - Math.abs((H % 2) - 1));
            switch (hueFace) {
                case 0:
                    red = C;
                    green = X;
                    blue = 0;
                    break;
                case 1:
                    red = X;
                    green = C;
                    blue = 0;
                    break;
                case 2:
                    red = 0;
                    green = C;
                    blue = X;
                    break;
                case 3:
                    red = 0;
                    green = X;
                    blue = C;
                    break;
                case 4:
                    red = X;
                    green = 0;
                    blue = C;
                    break;
                case 5:
                    red = C;
                    green = 0;
                    blue = X;
                    break;
            }
            m = V - C;
        }
        // Finally add in the brightness delta
        rgb[0] = red + m;
        rgb[1] = green + m;
        rgb[2] = blue + m;
    }

    @Override
    public UIColor tableSeparatorDarkColor() {
        return createWithRGBA(0.67f, 0.67f, 0.67f, 1);
    }

    @Override
    public UIColor tableSeparatorLightColor() {
        return createWithRGBA(0.88f, 0.88f, 0.88f, 1);
    }

    @Override
    public UIColor tableBackgroundColor() {
        return createWithRGBA(1.00f, 1.00f, 1.00f, 1);
    }

    @Override
    public UIColor tableSelectionColor() {
        return createWithRGBA(0.16f, 0.43f, 0.83f, 1);
    }

    @Override
    public UIColor sectionListBorderColor() {
        return createWithRGBA(0.52f, 0.56f, 0.58f, 0.6f);
    }

    @Override
    public UIColor sectionHeaderBackgroundColor() {
        return createWithRGBA(0.90f, 0.93f, 0.99f, 0.8f);
    }

    @Override
    public UIColor sectionHeaderOpaqueBackgroundColor() {
        return createWithRGBA(0.92f, 0.94f, 0.99f, 1);
    }

    @Override
    public UIColor sectionHeaderBorderColor() {
        return createWithRGBA(0.85f, 0.87f, 0.91f, 1);
    }

    @Override
    public UIColor tableCellBlueTextColor() {
        return createWithRGBA(0.22f, 0.33f, 0.53f, 1);
    }

    @Override
    public UIColor tableCellGrayTextColor() {
        return createWithRGBA(0.50f, 0.50f, 0.50f, 1);
    }

    @Override
    public UIColor textFieldAtomBlueColor() {
        return createWithRGBA(0.16f, 0.34f, 1.00f, 1);
    }

    @Override
    public UIColor textFieldAtomPurpleColor() {
        return createWithRGBA(0.41f, 0.00f, 0.74f, 1);
    }

    @Override
    public UIColor infoTextOverPinStripeTextColor() {
        return createWithRGBA(0.30f, 0.34f, 0.42f, 1);
    }

    @Override
    public UIColor tableCellValue1BlueColor() {
        return createWithRGBA(0.22f, 0.33f, 0.53f, 1);
    }

    @Override
    public UIColor tableCellValue2BlueColor() {
        return createWithRGBA(0.32f, 0.40f, 0.57f, 1);
    }

    @Override
    public UIColor tableGroupedSeparatorLightColor() {
        return createWithRGBA(0, 0, 0, 0.18f);
    }

    @Override
    public UIColor tableCellPlainBackgroundColor() {
        return createWithRGBA(1, 1, 1, 1);
    }

    @Override
    public UIColor tableCellGroupedBackgroundColor() {
        return mDeviceHandle.getUseIPadTheme() ? createWithRGBA(0.97f, 0.97f, 0.97f, 1) : createWithRGBA(1, 1, 1, 1);
    }

    @Override
    public UIColor tableShadowColor() {
        return createWithRGBA(1.00f, 1.00f, 0.91f, 1);
    }

    @Override
    public UIColor tableGroupedTopShadowColor() {
        return createWithRGBA(0, 0, 0, 0.8f);
    }

    @Override
    public UIColor selectionTintColor() {
        return createWithRGBA(0, 0.33f, 0.65f, 0.2f);
    }

    @Override
    public UIColor textCaretColor() {
        return createWithRGBA(0.26f, 0.42f, 0.95f, 1); // 0.41, 0.31, 0.27 (MobileNotes));
    }

    @Override
    public UIColor selectionCaretColor() {
        return createWithRGBA(0.26f, 0.42f, 0.95f, 1);
    }

    @Override
    public UIColor selectionHighlightColor() {
        return createWithRGBA(0.44f, 0.66f, 0.99f, 0.18f);
    }

    @Override
    public UIColor tableSelectionGradientStartColor() {
        return createWithRGBA(0.02f, 0.55f, 0.96f, 1);
    }

    @Override
    public UIColor tableSelectionGradientEndColor() {
        return createWithRGBA(0.04f, 0.37f, 0.91f, 1);
    }

    @Override
    public UIColor pinStripeColor() {
        return createWithPatternImage(mUIImageFactory.create("UIPinStripe.png"));
    }

    @Override
    public UIColor noContentLightGradientBackgroundColor() {
        return createWithPatternImage(mUIImageFactory.create("UIStockImageNoContentLightGradientBackgroundColor" +
                "" + ".png"));
    }

    @Override
    public UIColor noContentDarkGradientBackgroundColor() {
        return createWithPatternImage(mUIImageFactory.create("UIStockImageNoContentDarkGradientBackgroundColor" + "" +
                ".png"));
    }

    /**
     * [[UIColor lightTextColor] styleString] => "rgba(255,255,255,153)"
     * @return
     */
    @Override
    public String styleString() {
        throw new NotImplementedException(); //TODO
    }
}
