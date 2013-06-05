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

public class DefaultNotification implements Notification {
    private final NotificationFactory mNotificationFactory;

    protected DefaultNotification(NotificationFactory notificationFactory) {
        mNotificationFactory = notificationFactory;
    }

    protected DefaultNotification(Coder decoder, NotificationFactory notificationFactory) {
        mNotificationFactory = notificationFactory;
        //Todo
    }

    @Override
    public String name() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public Object object() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public NotificationUserInfo userInfo() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public NotificationFactory getFactory() {
        return mNotificationFactory;
    }

    @Override
    public void encode(Coder encoder) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public Notification copy() {
        throw new NotImplementedException(); //TODO
    }
}
