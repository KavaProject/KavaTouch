/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.uikit;

import org.kavaproject.kavatouch.corefoundation.CoreBundle;
import org.kavaproject.kavatouch.internal.CFunction;
import org.kavaproject.kavatouch.internal.MachPort;

public interface Session extends Runnable {
    public static final boolean DEBUG = true;
    public static final boolean DEBUG_MEASSURE_CONTEXT_TIME = DEBUG && false;
    public static final boolean DEBUG_MEASSURE_RENDERING_TIME = DEBUG && false;
    public static final boolean DEBUG_DISPLAY_FPS = DEBUG && false;
    public static final int DEFAULT_MACH_QUEUE_CAPACITY = 256;

    @CFunction(value = "UIApplicationMain", tokenGroup = "UIKit")
    void main(UIApplicationDelegate applicationDelegate);

    MachPort getMainPort();

    String getBundleID(String path);

    void putBundle(String id, String path, CoreBundle bundle);

    CoreBundle getBundle(String bundleID);

    String getMainBundleID();
}
