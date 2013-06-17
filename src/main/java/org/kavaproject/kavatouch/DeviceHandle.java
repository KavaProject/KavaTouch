package org.kavaproject.kavatouch;

import android.content.Context;
import android.view.View;
import org.kavaproject.kavatouch.coreanimation.AnimationEngine;
import org.kavaproject.kavatouch.internal.ImageScaleModifier;

import java.util.List;

public interface DeviceHandle {
    void addObserver(Observer defaultUIScreen);

    AnimationEngine getAnimationEngine();

    void setOnTouchListener(View.OnTouchListener onTouchListener);

    Context getCompositorContext();

    float getScale();

    boolean getFlipYAxis();

    boolean getUseIPadTheme();

    List<ImageScaleModifier> getImageScaleModifiers();

    List<String> getDeviceModifiers();

    int getStatusBarHeightPx();

    int getActionBarHeightPx();

    int getSurfaceHeightPx();

    int getScreenWidthPx();

    int getScreenHeightPx();

    public interface Observer {
        void onDeviceConfigurationChanged();
    }
}
