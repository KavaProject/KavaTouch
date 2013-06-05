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

import javax.inject.Inject;
import java.util.Map;

public class DefaultNotificationFactory implements NotificationFactory {
    @Inject
    protected DefaultNotificationFactory() {
    }

    @Override
    public Notification create(Coder decoder) {
        return new DefaultNotification(decoder, this);
    }

    @Override
    public Notification create(String name, Object object) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public Notification create(String name, Object object, Map userInfo) {
        throw new NotImplementedException(); //TODO
    }
}
