/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.foundation;

import org.kavaproject.kavatouch.runtime.Runnable1;
import org.kavaproject.kavatouch.runtime.SEL;
import org.kavaproject.kavatouch.util.NotImplementedException;

import java.util.Map;

public class DefaultNotificationCenter implements NotificationCenter {
    private final NotificationCenterFactory mNotificationCenterFactory;

    protected DefaultNotificationCenter(NotificationCenterFactory notificationCenterFactory) {
        mNotificationCenterFactory = notificationCenterFactory;
    }

    @Override
    public Object addObserver(String name, Object object, OperationQueue queue, Runnable1<Notification> block) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void addObserver(Object observer, SEL selector, String name, Object sender) {
        //TODO
    }

    @Override
    public void removeObserver(Object observer) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void removeObserver(Object observer, String notificationName, Object sender) {
        //TODO
    }

    @Override
    public void postNotification(Notification notification) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void postNotificationName(String notificationName, Object sender) {
    }

    @Override
    public void postNotificationName(String notificationName, Object sender, Map userInfo) {
    }

    @Override
    public NotificationCenterFactory getFactory() {
        return mNotificationCenterFactory;
    }
}
