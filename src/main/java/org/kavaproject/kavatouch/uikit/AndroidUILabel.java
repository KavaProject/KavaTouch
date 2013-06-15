/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.uikit;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import org.kavaproject.kavatouch.DeviceHandle;
import org.kavaproject.kavatouch.coreanimation.AnimationLayer;
import org.kavaproject.kavatouch.coregraphics.GraphicsImage;
import org.kavaproject.kavatouch.coregraphics.GraphicsRect;
import org.kavaproject.kavatouch.coregraphics.GraphicsSize;
import org.kavaproject.kavatouch.foundation.AttributedString;
import org.kavaproject.kavatouch.foundation.Coder;
import org.kavaproject.kavatouch.foundation.FoundationTextAlignment;
import org.kavaproject.kavatouch.runtime.MethodResolver;
import org.kavaproject.kavatouch.util.AndroidConversions;
import org.kavaproject.kavatouch.util.NotImplementedException;

public class AndroidUILabel extends DefaultUIView implements UILabel {
    private final UIFontFactory mUIFontFactory;
    private TextView mTextView;
    private LineBreakMode mLineBreakMode = LineBreakMode.TAIL_TRUNCATION;
    private FoundationTextAlignment mTextAlignment = FoundationTextAlignment.LEFT;
    private int mNumberOfLines = 1;
    private float mMinimumFontSize = 0;
    private UIColor mTextColor;
    private UIColor mHighlightedTextColor = null;
    private UIBaselineAdjustment mBaselineAdjustment = UIBaselineAdjustment.ALIGN_BASELINES;
    private boolean mAdjustsFontSizeToFitWidth = false;
    private UIFont mFont;
    private boolean mInteractionEnabled;
    private UIColor mBackgroundColor;

    public AndroidUILabel(Coder decoder, UILabelFactory uiLabelFactory, UIFontFactory uiFontFactory,
                          UIGraphics uiGraphics, UIColorFactory uiColorFactory, UIScreen mainScreen,
                          MethodResolver methodResolver, DeviceHandle deviceHandle) {
        super(decoder, uiLabelFactory, uiGraphics, uiColorFactory, mainScreen, methodResolver);
        mUIFontFactory = uiFontFactory;
        //TODO
    }

    protected AndroidUILabel(GraphicsRect frame, UILabelFactory uiLabelFactory, UIFontFactory uiFontFactory,
                             UIGraphics uiGraphics, UIColorFactory uiColorFactory, UIScreen mainScreen,
                             MethodResolver methodResolver, DeviceHandle deviceHandle) {
        super(frame, uiLabelFactory, uiGraphics, uiColorFactory, mainScreen, methodResolver);
        mUIFontFactory = uiFontFactory;
        mTextColor = getUIColorFactory().black();
        mFont = mUIFontFactory.systemFontOfSize(17);
        getLayer().setName("UILabel");
        getLayer().setContentsScale(mainScreen.getScale());
        mTextView = new TextView(deviceHandle.getCompositorContext()); //TODO This takes way too long
        mTextView.setEnabled(true);
        mTextView.setTextColor(Color.WHITE);
        mTextView.setIncludeFontPadding(false);
        mTextView.setPadding(0, 0, 0, 0);
        mTextView.setGravity(Gravity.LEFT | Gravity.TOP);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        mTextView.setLayoutParams(params);
        pushLineBreakMode();
        pushNumberOfLines();
        pushTextAlignment();
        pushMinimumFontSize();
        pushTextColor();
        pushBackgroundColor();
        pushAdjustsFontSizeToFitWidth();
        pushFont();
        updateLayout();
        setUserInteractionEnabled(false);
    }

    private void pushBackgroundColor() {
        if (mBackgroundColor == null) {
            return;
        }
        mTextView.setBackgroundColor(AndroidConversions.toColor(mBackgroundColor.toCoreType()));
    }

    private void pushMinimumFontSize() {
        //TODO No built-in solution on android
    }

    private void pushNumberOfLines() {
        mTextView.setMaxLines(mNumberOfLines == 0 ? Integer.MAX_VALUE : mNumberOfLines);
    }

    private void pushAdjustsFontSizeToFitWidth() {
        //TODO
    }

    private void pushLineBreakMode() {
        switch (mLineBreakMode) {
            case CHARACTER_WRAP:
                mTextView.setEllipsize(null);
                mTextView.setHorizontallyScrolling(false);
                //myString.replace(" ", "\u00A0");
                break;
            case CLIP:
                mTextView.setEllipsize(null);
//                mTextView.setHorizontallyScrolling(true);
                mTextView.setHorizontallyScrolling(false); //TODO Gravity handling needs extra care when true
                break;
            case HEAD_TRUNCATION:
                mTextView.setEllipsize(TextUtils.TruncateAt.START);
                mTextView.setHorizontallyScrolling(false);
                break;
            case MIDDLE_TRUNCATION:
                mTextView.setEllipsize(TextUtils.TruncateAt.MIDDLE);
                mTextView.setHorizontallyScrolling(false);
                break;
            case TAIL_TRUNCATION:
                mTextView.setEllipsize(TextUtils.TruncateAt.END);
                mTextView.setHorizontallyScrolling(false);
                break;
            case WORD_WRAP:
                mTextView.setEllipsize(null);
                mTextView.setHorizontallyScrolling(false);
                break;
        }
    }

    private void pushTextAlignment() {
        mTextView.setGravity(mTextAlignment.gravity);
    }

    private void pushTextColor() {
        mTextView.setTextColor(AndroidConversions.toColor(mTextColor));
    }

    private void pushFont() {
        String style = mFont.getFontName().substring(mFont.getFamilyName().length());
        if (style == "Bold") {
            mTextView.setTypeface(Typeface.create(mFont.getFamilyName(), Typeface.BOLD));
        } else if (style == "BoldItalic") {
            mTextView.setTypeface(Typeface.create(mFont.getFamilyName(), Typeface.BOLD_ITALIC));
        } else if (style == "Italic") {
            mTextView.setTypeface(Typeface.create(mFont.getFamilyName(), Typeface.ITALIC));
        } else if (style == "Normal") {
            mTextView.setTypeface(Typeface.create(mFont.getFamilyName(), Typeface.NORMAL));
        }
        mTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, mFont.getPointSize() * getContentScaleFactor());
        //We might use pt instead of px, but then we would have to reverse the scaling in the ctm
    }

    private void updateLayout() {
        GraphicsRect bounds = getBounds();
        int width = (int) (bounds.size.width * getContentScaleFactor());
        int height = (int) (bounds.size.height * getContentScaleFactor());
        mTextView.measure(View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY),
                View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.EXACTLY));
        mTextView.layout(0, 0, width, height);
    }

    @Override
    public String getText() {
        return mTextView.getText().toString();
    }

    @Override
    public void setText(String value) {
        mTextView.setText(value);
        updateLayout();
        setNeedsDisplay();
    }

    @Override
    public AttributedString getAttributedText() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void setAttributedText(AttributedString value) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public UIFont getFont() {
        return mFont;
    }

    @Override
    public void setFont(UIFont value) {
        mFont = value;
        pushFont();
        setNeedsDisplay();
    }

    @Override
    public UIColor getTextColor() {
        return mTextColor;
    }

    @Override
    public void setTextColor(UIColor value) {
        mTextColor = value;
        pushTextColor();
        setNeedsDisplay();
    }

    @Override
    public FoundationTextAlignment getTextAlignment() {
        return mTextAlignment;
    }

    @Override
    public void setTextAlignment(FoundationTextAlignment value) {
        mTextAlignment = value;
        pushTextAlignment();
        setNeedsDisplay();
    }

    @Override
    public LineBreakMode getLineBreakMode() {
        return mLineBreakMode;
    }

    @Override
    public void setLineBreakMode(LineBreakMode value) {
        mLineBreakMode = value;
        pushLineBreakMode();
        setNeedsDisplay();
    }

    @Override
    public boolean isEnabled() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void setEnabled(boolean value) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public boolean getAdjustsFontSizeToFitWidth() {
        return mAdjustsFontSizeToFitWidth;
    }

    @Override
    public void setAdjustsFontSizeToFitWidth(boolean value) {
        mAdjustsFontSizeToFitWidth = value;
        pushAdjustsFontSizeToFitWidth();
        setNeedsDisplay();
    }

    @Override
    public boolean getAdjustsLetterSpacingToFitWidth() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void setAdjustsLetterSpacingToFitWidth(boolean value) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public UIBaselineAdjustment getBaselineAdjustment() {
        return mBaselineAdjustment;
    }

    @Override
    public void setBaselineAdjustment(UIBaselineAdjustment value) {
        mBaselineAdjustment = value;
        setNeedsDisplay();
    }

    @Override
    public float getMinimumScaleFactor() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void setMinimumScaleFactor(float value) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public int getNumberOfLines() {
        return mNumberOfLines;
    }

    @Override
    public void setNumberOfLines(int value) {
        mNumberOfLines = value;
        pushNumberOfLines();
        setNeedsDisplay();
    }

    @Override
    public float getMinimumFontSize() {
        return mMinimumFontSize;
    }

    @Override
    public void setMinimumFontSize(float value) {
        mMinimumFontSize = value;
        pushMinimumFontSize();
        setNeedsDisplay();
    }

    @Override
    public UIColor getHighlightedTextColor() {
        return mHighlightedTextColor;
    }

    @Override
    public void setHighlightedTextColor(UIColor value) {
        mHighlightedTextColor = value;
        setNeedsDisplay();
    }

    @Override
    public boolean isHighlighted() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void setHighlighted(boolean value) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public UIColor getShadowColor() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void setShadowColor(UIColor value) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public GraphicsSize getShadowOffset() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void setShadowOffset(GraphicsSize value) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public GraphicsRect textRectForBounds(GraphicsRect bounds, int numberOfLinesLimit) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void drawText(GraphicsRect rect) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public float getPreferredMaxLayoutWidth() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void setPreferredMaxLayoutWidth(float value) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public UIColor getBackgroundColor() {
        return mBackgroundColor != null ? getUIColorFactory().create(mBackgroundColor.toCoreType()) : null;
    }

    @Override
    public void setBackgroundColor(UIColor value) {
        mBackgroundColor = value != null ? getUIColorFactory().create(value.toCoreType()) : null;
        pushBackgroundColor();
    }

    @Override
    public boolean isUserInteractionEnabled() {
        return mInteractionEnabled;
    }

    @Override
    public void setUserInteractionEnabled(boolean value) {
        mInteractionEnabled = value; //TODO
    }

    @Override
    public void setFrame(GraphicsRect value) {
        super.setFrame(value);
        if (mTextView == null) {
            return;
        }
        updateLayout();
    }

    @Override
    public void displayLayer(AnimationLayer layer) {
        Bitmap.Config config = Bitmap.Config.ARGB_8888;
        int height = mTextView.getHeight();
        int width = mTextView.getWidth();
        Bitmap bitmap = Bitmap.createBitmap(width, height, config);
        Canvas canvas = new Canvas(bitmap);
        canvas.translate(0, height);
        canvas.scale(1, -1);
        mTextView.draw(canvas);
        GraphicsImage image = GraphicsImage.Internal.create(bitmap);
        layer.setContents(image);
    }

    @Override
    public UILabelFactory getFactory() {
        return (UILabelFactory) super.getFactory();
    }

    @Override
    public void encode(Coder encoder) {
        throw new NotImplementedException(); //TODO
    }
}
