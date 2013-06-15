package org.kavaproject.kavatouch.util;

import android.content.Context;
import android.view.View;
import org.kavaproject.kavatouch.DeviceHandle;
import org.kavaproject.kavatouch.coreanimation.AnimationEngine;
import org.kavaproject.kavatouch.coregraphics.GraphicsPoint;
import org.kavaproject.kavatouch.coregraphics.GraphicsRect;
import org.kavaproject.kavatouch.internal.ImageScaleModifier;

import java.util.Collections;
import java.util.List;

public class DeviceHandleMock implements DeviceHandle {
    @Override
    public void addObserver(Observer defaultUIScreen) {
    }

    @Override
    public GraphicsRect getScreenRect() {
        return GraphicsRect.ZERO;
    }

    @Override
    public AnimationEngine getAnimationEngine() {
        return null;
    }

    @Override
    public GraphicsPoint getScreenOffset() {
        return GraphicsPoint.ZERO;
    }

    @Override
    public void setOnTouchListener(View.OnTouchListener onTouchListener) {
    }

    @Override
    public Context getCompositorContext() {
        throw new UnsupportedOperationException();
    }

    @Override
    public float getScale() {
        return 1;
    }

    @Override
    public boolean getFlipYAxis() {
        return true;
    }

    @Override
    public boolean getUseIPadTheme() {
        return true;
    }

    @Override
    public List<ImageScaleModifier> getImageScaleModifiers() {
        return Collections.EMPTY_LIST;
    }

    @Override
    public List<String> getDeviceModifiers() {
        return Collections.EMPTY_LIST;
    }

    @Override
    public int getStatusBarHeight() {
        return 0;
    }
}
