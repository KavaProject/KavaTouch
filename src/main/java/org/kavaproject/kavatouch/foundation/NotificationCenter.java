/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.foundation;

import org.kavaproject.kavatouch.internal.Header;
import org.kavaproject.kavatouch.internal.OccClass;
import org.kavaproject.kavatouch.internal.OccInstanceMethod;
import org.kavaproject.kavatouch.runtime.Creatable;
import org.kavaproject.kavatouch.runtime.Runnable1;
import org.kavaproject.kavatouch.runtime.SEL;

import java.util.Map;

@Header("NSNotification")
@OccClass("NSNotificationCenter")
public interface NotificationCenter extends Creatable {
    @OccInstanceMethod("addObserverForName:object:queue:usingBlock:")
    Object addObserver(String name, Object object, OperationQueue queue, Runnable1<Notification> block);

    @OccInstanceMethod("addObserver:selector:name:object:")
    void addObserver(Object observer, SEL selector, String name, Object sender);

    @OccInstanceMethod("removeObserver:")
    void removeObserver(Object observer);

    @OccInstanceMethod("removeObserver:name:object:")
    void removeObserver(Object observer, String notificationName, Object sender);

    @OccInstanceMethod("postNotification:")
    void postNotification(Notification notification);

    @OccInstanceMethod("postNotificationName:object:")
    void postNotificationName(String notificationName, Object sender);

    @OccInstanceMethod("postNotificationName:object:userInfo:")
    void postNotificationName(String notificationName, Object sender, Map userInfo);

    @Override
    NotificationCenterFactory getFactory();
}
