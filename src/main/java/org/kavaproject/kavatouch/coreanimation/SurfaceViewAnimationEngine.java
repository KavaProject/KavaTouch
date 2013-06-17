/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.coreanimation;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import org.kavaproject.kavatouch.DeviceHandle;
import org.kavaproject.kavatouch.coregraphics.*;
import org.kavaproject.kavatouch.uikit.Session;
import org.kavaproject.kavatouch.uikit.UIApplicationDelegate;
import org.kavaproject.kavatouch.util.NotImplementedException;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public abstract class SurfaceViewAnimationEngine extends SurfaceView implements AnimationEngine,
        SurfaceHolder.Callback2 {
    private final ReentrantLock mRenderLock = new ReentrantLock();
    private final Object mUpdateMonitor = new Object();
    private final Activity mActivity;
    private volatile boolean mWaiting;
    private volatile boolean mUpdated;
    private volatile AnimationLayer mRenderTree;
    private float mFPS;
    private long mLastUpdate = Long.MIN_VALUE;
    private Map<AnimationLayer, AnimationLayer> mRenderLayers = new WeakHashMap<AnimationLayer, AnimationLayer>();
    private Set<AnimationLayer> mChangedLayers = new HashSet<AnimationLayer>();
    private ArrayList<LayerHierarchyChange> mLayerHierarchyChanges = new ArrayList<LayerHierarchyChange>();
    private Thread mThread;
    private volatile boolean mRunning;
    private SurfaceViewDeviceHandle mDeviceHandle;

    public SurfaceViewAnimationEngine(Activity activity) {
        super(activity);
        mActivity = activity;
        getHolder().addCallback(this);
    }

    @Override
    public void addModelTree(AnimationLayer modelTree) {
        if (!(modelTree instanceof AnimationLayerInternal)) {
            throw new NotImplementedException();
        }
        modelTree.setChangeLogger(this);
        mRenderTree = getRenderLayer(modelTree);
        setNeedsUpdate();
        mThread = new Thread(SurfaceViewAnimationEngine.this);
        mThread.start();
    }

    @Override
    public void commitModelChanges() {
        if (mChangedLayers.isEmpty() && mLayerHierarchyChanges.isEmpty()) {
            return;
        } else {
            mRenderLock.lock();
            for (AnimationLayer modelLayer : mChangedLayers) {
                mRenderLayers.get(modelLayer).update(modelLayer);
            }
            mChangedLayers.clear();
            for (LayerHierarchyChange change : mLayerHierarchyChanges) {
                AnimationLayer renderLayer = mRenderLayers.get(change.layer);
                if (change.newSuperlayer == null) {
                    renderLayer.removeFromSuperlayer();
                } else {
                    AnimationLayer renderSuperlayer = mRenderLayers.get(change.newSuperlayer);
                    renderSuperlayer.insertSublayer(renderLayer, change.newIndex);
                }
            }
            mLayerHierarchyChanges.clear();
            mRenderLock.unlock();
            setNeedsUpdate();
        }
    }

    @Override
    public boolean isWaiting() {
        return mWaiting;
    }

    @Override
    public void resume() {
        setNeedsUpdate();
    }

    private AnimationLayer getRenderLayer(AnimationLayer modelLayer) {
        AnimationLayer renderLayer = mRenderLayers.get(modelLayer);
        if (renderLayer != null) {
            return renderLayer;
        }
        renderLayer = modelLayer.getFactory().create();
        mRenderLayers.put(modelLayer, renderLayer);
        return renderLayer;
    }

    private void setNeedsUpdate() {
        synchronized (mUpdateMonitor) {
            mUpdated = true;
            mUpdateMonitor.notify();
        }
    }

    @Override
    public void run() {
        while (mRunning) {
            waitForUpdate();
            if (!mRunning) {
                return;
            }
            float scale = mDeviceHandle.getScale();
            mRenderLock.lock();
            GraphicsRect windowFramePx = mRenderTree.getFrame();
            windowFramePx = GraphicsAffineTransform.makeScale(scale, scale).transformRect(windowFramePx);
            windowFramePx = windowFramePx.integral();
            float windowHeightPx = windowFramePx.size.height;
            Rect rect = new Rect((int) windowFramePx.origin.x, (int) windowFramePx.origin.y,
                    (int) windowFramePx.size.width, (int) windowFramePx.size.height);
            Canvas canvas = null;
            try {
                canvas = getHolder().lockCanvas(rect);
                if (canvas == null) {
                    return;
                }
                int saveCount = canvas.getSaveCount();
                canvas.save();
                int screenHeightPx = mDeviceHandle.getScreenHeightPx();
                int surfaceHeightPx = mDeviceHandle.getSurfaceHeightPx();
                canvas.translate(0, surfaceHeightPx - screenHeightPx);
                //Quartz' default coordinate system is flipped compared to Androids.
                canvas.translate(0, windowHeightPx);
                canvas.scale(1, -1);
                GraphicsContext context = GraphicsContext.Internal.create(canvas);
                //And iOS flips it again
                if (mDeviceHandle.getFlipYAxis()) {
                    context.scaleCTM(1, -1);
                    context.translateCTM(0, windowHeightPx / scale);
                }
                context.scaleCTM(scale, scale);
                mRenderTree.render(context);
                context.internal.canvas.restoreToCount(saveCount);
                if (Session.DEBUG_DISPLAY_FPS) {
                    drawFPS(canvas);
                }
            } finally {
                if (canvas != null) {
                    getHolder().unlockCanvasAndPost(canvas);
                }
                mRenderLock.unlock();
            }
        }
    }

    private void drawFPS(Canvas canvas) {
        long now = System.nanoTime();
        if (mLastUpdate == Long.MIN_VALUE) {
            mLastUpdate = now - 1000000000;
        }
        long passedNanos = now - mLastUpdate;
        mLastUpdate = now;
        float fps = 1000000000f / passedNanos;
        mFPS = (mFPS + 2 * fps) / 3;
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setFakeBoldText(true);
        paint.setShadowLayer(5, 5, 5, Color.GRAY);
        paint.setColor(Color.WHITE);
        paint.setTextSize(30);
        canvas.drawText("FPS: " + mFPS, 10, 35, paint);
    }

    private void waitForUpdate() {
        mWaiting = true;
        synchronized (mUpdateMonitor) {
            while ((!mUpdated || mRenderTree == null) && mRunning) {
                try {
                    mUpdateMonitor.wait();
                } catch (InterruptedException e) {
                    throw new Error();
                }
            }
        }
        mUpdated = false;
        mWaiting = false;
    }

    @Override
    public void logLayerHierarchyChange(AnimationLayer modelLayer, int newIndex, AnimationLayer newSuperlayer) {
        getRenderLayer(modelLayer);
        mLayerHierarchyChanges.add(new LayerHierarchyChange(modelLayer, newIndex, newSuperlayer));
    }

    @Override
    public void logLayerChange(AnimationLayer modelLayer) {
        getRenderLayer(modelLayer);
        mChangedLayers.add(modelLayer);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        mDeviceHandle = new SurfaceViewDeviceHandle(mActivity, this);
        getSession(mDeviceHandle).main(getDelegate());
        synchronized (mUpdateMonitor) {
            mRunning = true;
            mUpdateMonitor.notify();
        }
        if (mThread == null || !mThread.isAlive()) {
            mThread = new Thread(SurfaceViewAnimationEngine.this);
            mThread.start();
        }
    }

    protected abstract UIApplicationDelegate getDelegate();

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int format, int width, int height) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        synchronized (mUpdateMonitor) {
            mRunning = false;
            mUpdateMonitor.notify();
        }
        boolean retry = true;
        while (retry) {
            try {
                mThread.join();
                retry = false;
            } catch (InterruptedException e) {
                //empty
            }
        }
    }

    public abstract Session getSession(DeviceHandle deviceHandle);

    @Override
    public void surfaceRedrawNeeded(SurfaceHolder holder) {
        setNeedsUpdate();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
    }

    @Override
    protected void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    public static class LayerHierarchyChange {
        public final AnimationLayer layer;
        public final int newIndex;
        public final AnimationLayer newSuperlayer;

        public LayerHierarchyChange(AnimationLayer layer, int newIndex, AnimationLayer newSuperlayer) {
            this.layer = layer;
            this.newIndex = newIndex;
            this.newSuperlayer = newSuperlayer;
        }
    }
}
