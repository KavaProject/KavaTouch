/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.foundation;

import org.kavaproject.kavatouch.corefoundation.CoreAbsoluteTime;
import org.kavaproject.kavatouch.corefoundation.CoreDate;

import org.kavaproject.kavatouch.util.inject.Inject;

public class DefaultFoundationDateFactory implements FoundationDateFactory {
    @Inject
    protected DefaultFoundationDateFactory() {
    }

    @Override
    public FoundationDate create(Coder decoder) {
        return new DefaultFoundationDate(decoder, this);
    }

    @Override
    public FoundationDate create() {
        return createWithTimeIntervalSinceReferenceDate(CoreAbsoluteTime.getCurrent());
    }

    @Override
    public FoundationDate createWithTimeIntervalSinceReferenceDate(double seconds) {
        return create(new CoreDate(seconds));
    }

    @Override
    public FoundationDate create(CoreDate coreObject) {
        return new DefaultFoundationDate(coreObject, this);
    }

    @Override
    public FoundationDate createWithTimeIntervalSinceNow(double seconds) {
        return createWithTimeIntervalSinceReferenceDate(CoreAbsoluteTime.getCurrent() + seconds);
    }

    @Override
    public FoundationDate createWithTimeIntervalSinceDate(double seconds, FoundationDate refDate) {
        return createWithTimeIntervalSinceReferenceDate(refDate.timeIntervalSinceReferenceDate() + seconds);
    }

    @Override
    public FoundationDate createWithTimeIntervalSince1970(double seconds) {
        return createWithTimeIntervalSinceReferenceDate(seconds - CoreAbsoluteTime.INTERVAL_SINCE_1970);
    }

    @Override
    public FoundationDate distantFuture() {
        return createWithTimeIntervalSinceReferenceDate(Double.MAX_VALUE);
    }

    @Override
    public FoundationDate distantPast() {
        return createWithTimeIntervalSinceReferenceDate(Double.MIN_VALUE);
    }

    @Override
    public double timeIntervalSinceReferenceDate() {
        return CoreAbsoluteTime.getCurrent();
    }
}
