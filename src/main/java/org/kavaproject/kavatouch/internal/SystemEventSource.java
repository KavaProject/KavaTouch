/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.internal;

import android.view.MotionEvent;
import org.kavaproject.kavatouch.corefoundation.CoreRunLoop;

/**
 * 1. surface view calls addMotionEvent() => event is wrapped and pushed to queue
 * 2. surface view calls fireAllCommandsOnRunLoop()
 * 3. the source is signalled and the run loop woken up
 * 4. run loop calls sourceFired() => events are polled from queue and dispatched to UIKit
 */
public interface SystemEventSource {
    //Kava main thread
    void sourceFired();

    void addToCurrentRunLoop();

    void invalidate();

    //Android main thread
    void onTouch(MotionEvent event);

    //Android main thread
    void fireAllCommandsOnRunLoop(CoreRunLoop runloop);

    void screenChanged();
}
