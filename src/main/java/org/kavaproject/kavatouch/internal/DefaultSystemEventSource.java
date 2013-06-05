/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.internal;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import org.kavaproject.kavatouch.DeviceHandle;
import org.kavaproject.kavatouch.corefoundation.CoreRunLoop;
import org.kavaproject.kavatouch.corefoundation.CoreRunLoopSourceCustom;
import org.kavaproject.kavatouch.uikit.Session;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;

public class DefaultSystemEventSource implements SystemEventSource, View.OnTouchListener {
    private static final Object EVENT_SURFACE_CHANGED = new Object();
    private final MotionEventHandler mMotionEventHandler;
    private final DeviceHandle mDeviceHandle;
    private ArrayList<MotionEvent> mMotionEvents = new ArrayList<MotionEvent>();
    private CoreRunLoopSourceCustom mRLSource;
    private ConcurrentLinkedQueue mEvents = new ConcurrentLinkedQueue();
    private CoreRunLoop mScheduledRunLoop;

    public DefaultSystemEventSource(MotionEventHandler motionEventHandler, DeviceHandle deviceHandle) {
        mMotionEventHandler = motionEventHandler;
        mDeviceHandle = deviceHandle;
        mRLSource = new CoreRunLoopSourceCustom(0) {
            @Override
            public void onCancel(CoreRunLoop rl, String mode) {
                mDeviceHandle.setOnTouchListener(null);
            }

            @Override
            public void onPerform() {
                sourceFired();
            }

            @Override
            public void onSchedule(CoreRunLoop rl, String mode) {
                mScheduledRunLoop = rl;
                mDeviceHandle.setOnTouchListener(DefaultSystemEventSource.this);
            }
        };
    }

    @Override
    public void sourceFired() {
        if (Session.DEBUG) {
            Log.d("KAVA", "Fired");
        }
        Object event = mEvents.poll();
        while (event != null) {
            if (event instanceof MotionEvent) {
                mMotionEvents.add((MotionEvent) event);
            } else {
                dispatchMotionEvents();
            }
            if (event == EVENT_SURFACE_CHANGED) {
                //TODO Handle in AnimationEngine
            }
            event = mEvents.poll();
        }
        dispatchMotionEvents();
    }

    @Override
    public void addToCurrentRunLoop() {
        CoreRunLoop runLoop = CoreRunLoop.getCurrent();
        runLoop.addSource(mRLSource, CoreRunLoop.MODE_DEFAULT);
    }

    @Override
    public void invalidate() {
        mRLSource.invalidate();
    }

    @Override
    public void onTouch(MotionEvent event) {
        mEvents.add(MotionEvent.obtain(event));
    }

    @Override
    public void fireAllCommandsOnRunLoop(CoreRunLoop runloop) {
        mRLSource.signal();
        runloop.wakeUp();
    }

    @Override
    public void screenChanged() {
        mEvents.add(EVENT_SURFACE_CHANGED);
    }

    private void dispatchMotionEvents() {
        if (mMotionEvents.isEmpty()) {
            return;
        }
        mMotionEventHandler.dispatch(mMotionEvents);
        for (MotionEvent motionEvent : mMotionEvents) {
            motionEvent.recycle();
        }
        mMotionEvents.clear();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        onTouch(event);
        fireAllCommandsOnRunLoop(mScheduledRunLoop);
        return true;
    }
}
