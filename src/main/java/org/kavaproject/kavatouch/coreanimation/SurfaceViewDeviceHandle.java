package org.kavaproject.kavatouch.coreanimation;

import android.app.Activity;
import android.content.Context;
import android.graphics.PixelFormat;
import android.util.TypedValue;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.WindowManager;
import org.kavaproject.R;
import org.kavaproject.kavatouch.DeviceHandle;
import org.kavaproject.kavatouch.internal.ImageScaleModifier;
import org.kavaproject.kavatouch.internal.JSONDeviceConfiguration;

import java.util.List;

class SurfaceViewDeviceHandle implements DeviceHandle {
    private final SurfaceViewAnimationEngine mSurfaceView;
    private final JSONDeviceConfiguration mJSONConfiguration;
    private final int mStatusBarHeight;
    private final int mActionBarHeight;
    private final int mScreenWidth;
    private final int mScreenHeight;

    public SurfaceViewDeviceHandle(Activity activity, SurfaceViewAnimationEngine surfaceView) {
        mSurfaceView = surfaceView;
        mStatusBarHeight = getStatusBarHeight(activity);
        mActionBarHeight = getActionBarHeight(activity);
        Display display = ((WindowManager) activity.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        mScreenWidth = display.getWidth();
        mScreenHeight = display.getHeight();
        mJSONConfiguration = new JSONDeviceConfiguration(activity.getResources(), R.raw.kava_device_configuration);
        SurfaceHolder holder = surfaceView.getHolder();
        holder.setFormat(PixelFormat.RGBA_8888);
    }

    private static int getStatusBarHeight(Activity activity) {
        int result = 0;
        int resourceId = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = activity.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    private static int getActionBarHeight(Activity activity) {
        TypedValue typeValue = new TypedValue();
        int actionBarSizeId = activity.getResources().getIdentifier("actionBarSize", "attr", activity.getPackageName());
        activity.getTheme().resolveAttribute(actionBarSizeId, typeValue, true);
        return TypedValue.complexToDimensionPixelSize(typeValue.data, activity.getResources().getDisplayMetrics());
    }

    @Override
    public void addObserver(Observer defaultUIScreen) {
    }

    @Override
    public int getScreenWidthPx() {
        return mScreenWidth;
    }

    @Override
    public int getScreenHeightPx() {
        return mScreenHeight;
    }

    @Override
    public AnimationEngine getAnimationEngine() {
        return mSurfaceView;
    }

    @Override
    public void setOnTouchListener(View.OnTouchListener onTouchListener) {
        mSurfaceView.setOnTouchListener(onTouchListener);
    }

    @Override
    public Context getCompositorContext() {
        return mSurfaceView.getContext();
    }

    public float getScale() {
        return mJSONConfiguration.scale;
    }

    public boolean getFlipYAxis() {
        return mJSONConfiguration.flipYAxis;
    }

    public boolean getUseIPadTheme() {
        return mJSONConfiguration.useIPadTheme;
    }

    public List<ImageScaleModifier> getImageScaleModifiers() {
        return mJSONConfiguration.imageScaleModifiers;
    }

    public List<String> getDeviceModifiers() {
        return mJSONConfiguration.deviceModifiers;
    }

    @Override
    public int getStatusBarHeightPx() {
        return mStatusBarHeight;
    }

    @Override
    public int getActionBarHeightPx() {
        return mActionBarHeight;
    }

    @Override
    public int getSurfaceHeightPx() {
        return mSurfaceView.getMeasuredHeight();
    }
}
