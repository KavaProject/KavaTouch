/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.interfacebuilder;

/**
 * TODO
 */
public class IBUILabel extends IBUIView {
    public static final String KeyText = "IBUIText";
    public static final String KeyTextColor = "IBUITextColor";
    public static final String KeyHighlightedColor = "IBUIHighlightedColor";
    public static final String KeyBaselineAdjustment = "IBUIBaselineAdjustment";
    public static final String KeyMinimumFontSize = "IBUIMinimumFontSize";
    public static final String KeyTextAlignment = "IBUITextAlignment";
    public static final String KeyLineBreakMode = "IBUILineBreakMode";
    public String text;
    public NSColor textColor;
    public NSColor highlightedColor;
    public int baselineAdjustment;
    public float minimumFontSizet;
    public int textAlignment;
    public int lineBreakMode;
}
