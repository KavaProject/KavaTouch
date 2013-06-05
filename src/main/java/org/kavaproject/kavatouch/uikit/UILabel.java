/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.uikit;

import org.kavaproject.kavatouch.coregraphics.GraphicsRect;
import org.kavaproject.kavatouch.coregraphics.GraphicsSize;
import org.kavaproject.kavatouch.foundation.AttributedString;
import org.kavaproject.kavatouch.foundation.Coding;
import org.kavaproject.kavatouch.foundation.FoundationTextAlignment;
import org.kavaproject.kavatouch.internal.*;
import org.kavaproject.kavatouch.runtime.Creatable;

@Header("UILabel")
@OccClass("UILabel")
public interface UILabel extends UIResponder, UIView, Coding, Creatable {
    @OccInstanceProperty(value = "text", argumentSemantic = ArgumentSemantic.COPY)
    String getText();

    @OccInstanceProperty(value = "text", argumentSemantic = ArgumentSemantic.COPY)
    void setText(String value);

    @OccInstanceProperty("attributedText")
    AttributedString getAttributedText();

    @OccInstanceProperty("attributedText")
    void setAttributedText(AttributedString value);

    @OccInstanceProperty(value = "font", argumentSemantic = ArgumentSemantic.RETAIN)
    UIFont getFont();

    @OccInstanceProperty(value = "font", argumentSemantic = ArgumentSemantic.RETAIN)
    void setFont(UIFont value);

    @OccInstanceProperty(value = "textColor", argumentSemantic = ArgumentSemantic.RETAIN)
    UIColor getTextColor();

    @OccInstanceProperty(value = "textColor", argumentSemantic = ArgumentSemantic.RETAIN)
    void setTextColor(UIColor value);

    @OccInstanceProperty("textAlignment")
    FoundationTextAlignment getTextAlignment();

    @OccInstanceProperty("textAlignment")
    void setTextAlignment(FoundationTextAlignment value);

    @OccInstanceProperty("lineBreakMode")
    LineBreakMode getLineBreakMode();

    @OccInstanceProperty("lineBreakMode")
    void setLineBreakMode(LineBreakMode value);

    @OccInstanceProperty("enabled")
    boolean isEnabled();

    @OccInstanceProperty("enabled")
    void setEnabled(boolean value);

    @OccInstanceProperty("adjustsFontSizeToFitWidth")
    boolean getAdjustsFontSizeToFitWidth();

    @OccInstanceProperty("adjustsFontSizeToFitWidth")
    void setAdjustsFontSizeToFitWidth(boolean value);

    @OccInstanceProperty("adjustsLetterSpacingToFitWidth")
    boolean getAdjustsLetterSpacingToFitWidth();

    @OccInstanceProperty("adjustsLetterSpacingToFitWidth")
    void setAdjustsLetterSpacingToFitWidth(boolean value);

    @OccInstanceProperty("numberOfLines")
    UIBaselineAdjustment getBaselineAdjustment();

    @OccInstanceProperty("numberOfLines")
    void setBaselineAdjustment(UIBaselineAdjustment value);

    @OccInstanceProperty("minimumScaleFactor")
    float getMinimumScaleFactor();

    @OccInstanceProperty("minimumScaleFactor")
    void setMinimumScaleFactor(float value);

    @OccInstanceProperty("numberOfLines")
    int getNumberOfLines();

    @OccInstanceProperty("numberOfLines")
    void setNumberOfLines(int value);

    @OccInstanceProperty("minimumFontSize")
    float getMinimumFontSize();

    @OccInstanceProperty("minimumFontSize")
    void setMinimumFontSize(float value);

    @OccInstanceProperty(value = "highlightedTextColor", argumentSemantic = ArgumentSemantic.RETAIN)
    UIColor getHighlightedTextColor();

    @OccInstanceProperty(value = "highlightedTextColor", argumentSemantic = ArgumentSemantic.RETAIN)
    void setHighlightedTextColor(UIColor value);

    @OccInstanceProperty("highlighted")
    boolean isHighlighted();

    @OccInstanceProperty("highlighted")
    void setHighlighted(boolean value);

    @OccInstanceProperty(value = "shadowColor", argumentSemantic = ArgumentSemantic.RETAIN)
    UIColor getShadowColor();

    @OccInstanceProperty(value = "shadowColor", argumentSemantic = ArgumentSemantic.RETAIN)
    void setShadowColor(UIColor value);

    @OccInstanceProperty("shadowOffset")
    GraphicsSize getShadowOffset();

    @OccInstanceProperty("shadowOffset")
    void setShadowOffset(GraphicsSize value);

    @OccInstanceMethod("textRectForBounds:limitedToNumberOfLines:")
    GraphicsRect textRectForBounds(GraphicsRect bounds, int numberOfLinesLimit);

    @OccInstanceMethod("drawTextInRect:")
    void drawText(GraphicsRect rect);

    @OccInstanceProperty("preferredMaxLayoutWidth")
    float getPreferredMaxLayoutWidth();

    @OccInstanceProperty("preferredMaxLayoutWidth")
    void setPreferredMaxLayoutWidth(float value);

    @Override
    UILabelFactory getFactory();
}
