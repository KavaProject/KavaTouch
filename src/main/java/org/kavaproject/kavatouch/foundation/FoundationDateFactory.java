/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.foundation;

import org.kavaproject.kavatouch.corefoundation.CoreDate;
import org.kavaproject.kavatouch.internal.OccClass;
import org.kavaproject.kavatouch.internal.OccClassMethod;
import org.kavaproject.kavatouch.internal.OccInstanceMethod;
import org.kavaproject.kavatouch.runtime.Factory;

@OccClass("NSDate")
public interface FoundationDateFactory extends CodingFactory, CoreBridgeFactory<CoreDate>, Factory {
    FoundationDate create();

    @OccInstanceMethod("initWithTimeIntervalSinceReferenceDate:")
    FoundationDate createWithTimeIntervalSinceReferenceDate(double seconds);

    @OccInstanceMethod("initWithTimeIntervalSinceNow:")
    FoundationDate createWithTimeIntervalSinceNow(double seconds);

    @OccInstanceMethod("initWithTimeInterval:sinceDate:")
    FoundationDate createWithTimeIntervalSinceDate(double seconds, FoundationDate refDate);

    @OccInstanceMethod("initWithTimeIntervalSince1970:")
    FoundationDate createWithTimeIntervalSince1970(double seconds);

    @OccClassMethod("distantFuture")
    FoundationDate distantFuture();

    @OccClassMethod("distantPast")
    FoundationDate distantPast();

    @OccClassMethod("timeIntervalSinceReferenceDate")
    double timeIntervalSinceReferenceDate();
}
