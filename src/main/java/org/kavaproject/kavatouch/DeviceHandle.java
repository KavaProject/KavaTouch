package org.kavaproject.kavatouch;

import android.content.Context;
import android.view.View;
import org.kavaproject.kavatouch.coreanimation.AnimationEngine;
import org.kavaproject.kavatouch.coregraphics.GraphicsPoint;
import org.kavaproject.kavatouch.coregraphics.GraphicsRect;
import org.kavaproject.kavatouch.internal.ImageScaleModifier;

import java.util.List;

public interface DeviceHandle {
    void addObserver(Observer defaultUIScreen);

    GraphicsRect getScreenRect();

    AnimationEngine getAnimationEngine();

    GraphicsPoint getScreenOffset();

    void setOnTouchListener(View.OnTouchListener onTouchListener);

    Context getCompositorContext();

    float getScale();

    boolean getFlipYAxis();

    boolean getUseIPadTheme();

    List<ImageScaleModifier> getImageScaleModifiers();

    List<String> getDeviceModifiers();

    int getStatusBarHeight();

    public interface Observer {
        void onDeviceConfigurationChanged();
    }
}
