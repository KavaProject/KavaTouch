package org.kavaproject.kavatouch.coreanimation;

import android.app.Activity;
import android.content.Context;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.Window;
import org.kavaproject.R;
import org.kavaproject.kavatouch.DeviceHandle;
import org.kavaproject.kavatouch.coregraphics.GraphicsPoint;
import org.kavaproject.kavatouch.coregraphics.GraphicsRect;
import org.kavaproject.kavatouch.internal.ImageScaleModifier;
import org.kavaproject.kavatouch.internal.JSONDeviceConfiguration;

import java.util.List;

class SurfaceViewDeviceHandle implements DeviceHandle {
    private final SurfaceViewAnimationEngine mSurfaceView;
    private final GraphicsRect mScreenRect;
    //    private final int mTitleBarHeight;
    private final int mStatusBarHeight;
    private final JSONDeviceConfiguration mJSONConfiguration;
    private final GraphicsPoint mScreenOffset;

    public SurfaceViewDeviceHandle(Activity activity, SurfaceViewAnimationEngine surfaceView) {
        mSurfaceView = surfaceView;
        Rect visibleDisplayFrame = new Rect();
        Window window = activity.getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(visibleDisplayFrame);
        mStatusBarHeight = visibleDisplayFrame.top;
//        int contentViewTop = window.findViewById(Window.ID_ANDROID_CONTENT).getTop();
//        mTitleBarHeight = contentViewTop - mStatusBarHeight;
//        Display display = ((WindowManager) activity.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
//        int width = display.getWidth();
//        int height = display.getHeight();
//        int[] location = new int[2];
//        mSurfaceView.getLocationOnScreen(location);
//        mScreenOffset = new GraphicsPoint(location[0], location[1]);
//        mScreenOffset = new GraphicsPoint(0, mStatusBarHeight + mTitleBarHeight);
//        mScreenRect = new GraphicsRect(0, 0, width, height);
        mScreenOffset = new GraphicsPoint(0, mStatusBarHeight);
        mScreenRect = new GraphicsRect(0, 0, surfaceView.getMeasuredWidth(), surfaceView.getMeasuredHeight() +
                mStatusBarHeight);
        mJSONConfiguration = new JSONDeviceConfiguration(activity.getResources(), R.raw.kava_device_configuration);
        SurfaceHolder holder = surfaceView.getHolder();
        holder.setFormat(PixelFormat.RGBA_8888);
    }

    @Override
    public void addObserver(Observer defaultUIScreen) {
    }

    @Override
    public GraphicsRect getScreenRect() {
        return mScreenRect;
    }

    @Override
    public AnimationEngine getAnimationEngine() {
        return mSurfaceView;
    }

    @Override
    public GraphicsPoint getScreenOffset() {
        return mScreenOffset;
    }

    @Override
    public void setOnTouchListener(View.OnTouchListener onTouchListener) {
        mSurfaceView.setOnTouchListener(onTouchListener);
    }

    @Override
    public Context getContext() {
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
    public int getStatusBarHeight() {
        return mStatusBarHeight;
    }
}
