/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.interfacebuilder;

import org.kavaproject.kavatouch.coregraphics.GraphicsRect;
import org.kavaproject.kavatouch.coregraphics.GraphicsSize;
import org.kavaproject.kavatouch.uikit.staging.PSMatrix;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 */
public class IBUIView extends IBUIResponder {
    public static final String KeyNextResponder = "NSNextResponder";
    public static final String KeyVFlags = "NSvFlags";
    public static final String KeyFrame = "NSFrame";
    public static final String KeyFrameMatrix = "NSFrameMatrix";
    public static final String KeyFrameSize = "NSFrameSize";
    public static final String KeySubviews = "NSSubviews";
    public static final String KeySuperview = "NSSuperview";
    public static final String KeyBackgroundColor = "IBUIBackgroundColor";
    public static final String KeyOpaque = "IBUIOpaque";
    public static final String KeyClearsContextBeforeDrawing = "IBUIClearsContextBeforeDrawing";
    public static final String KeyAlpha = "IBUIAlpha";
    public static final String KeyContentMode = "IBUIContentMode";
    public static final String KeyClipsSubviews = "IBUIClipsSubviews";
    public static final String KeyUserInteractionEnabled = "IBUIUserInteractionEnabled";
    public static final String KeyMultipleTouchEnabled = "IBUIMultipleTouchEnabled";
    public IBUIResponder nextResponder;
    public int vFlags;
    public GraphicsRect frame;
    public PSMatrix frameMatrix;
    public GraphicsSize frameSize;
    public List<IBUIView> subviews = new ArrayList<IBUIView>();
    public IBUIView superview;
    public NSColor backgroundColor;
    public boolean opaque;
    public boolean clearsContextBeforeDrawing;
    public float alpha;
    public float contentMode;
    public boolean clipsSubviews;
    public boolean userInteractionEnabled;
    public boolean multipleTouchEnabled;
}
