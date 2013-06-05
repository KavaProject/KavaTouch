/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.foundation;

import org.kavaproject.kavatouch.util.NotImplementedException;

import java.util.EnumSet;
import java.util.List;

public class DefaultNotificationQueue implements NotificationQueue {
    private final NotificationQueueFactory mNotificationQueueFactory;

    protected DefaultNotificationQueue(NotificationQueueFactory notificationQueueFactory) {
        mNotificationQueueFactory = notificationQueueFactory;
    }

    @Override
    public void enqueue(Notification notification, PostingStyle postingStyle) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void enqueue(Notification notification, PostingStyle postingStyle, EnumSet<NotificationCoalescing>
            coalesceMask, List modes) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void dequeueNotifications(Notification notification, EnumSet<NotificationCoalescing> coalesceMask) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public NotificationQueueFactory getFactory() {
        return mNotificationQueueFactory;
    }
}
