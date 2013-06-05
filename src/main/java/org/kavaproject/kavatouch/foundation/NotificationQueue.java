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

import java.util.EnumSet;
import java.util.List;

@Header("NSNotificationQueue")
@OccClass("NSNotificationQueue")
public interface NotificationQueue extends Creatable {
    @OccInstanceMethod("enqueueNotification:postingStyle:")
    void enqueue(Notification notification, PostingStyle postingStyle);

    @OccInstanceMethod("enqueueNotification:postingStyle:coalesceMask:forModes:")
    void enqueue(Notification notification, PostingStyle postingStyle, EnumSet<NotificationCoalescing> coalesceMask,
                 List modes);

    @OccInstanceMethod("dequeueNotificationsMatching:coalesceMask:")
    void dequeueNotifications(Notification notification, EnumSet<NotificationCoalescing> coalesceMask);

    @Override
    NotificationQueueFactory getFactory();
}
