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
import org.kavaproject.kavatouch.internal.OccClass;
import org.kavaproject.kavatouch.internal.OccConstant;
import org.kavaproject.kavatouch.internal.OccInstanceMethod;
import org.kavaproject.kavatouch.runtime.Creatable;

import java.util.Locale;

@OccClass("NSDate")
public interface FoundationDate extends Copying, Coding, Comparable<FoundationDate>, CoreBridge<CoreDate>, Creatable {
    @OccConstant(value = "NSTimeIntervalSince1970")
    double TIME_INTERVAL_SINCE_1970 = CoreAbsoluteTime.INTERVAL_SINCE_1970;
    @OccConstant(value = "NSSystemClockDidChangeNotification")
    String NOTIFICATION_SYSTEM_CLOCK_DID_CHANGE = "NSSystemClockDidChangeNotification";

    @OccInstanceMethod("isEqualToDate:")
    @Override
    boolean equals(Object other);

    @OccInstanceMethod("description")
    @Override
    String toString();

    @OccInstanceMethod("descriptionWithLocale:")
    String toString(Locale locale);

    @OccInstanceMethod("earlierDate:")
    FoundationDate earlierDate(FoundationDate anotherDate);

    @OccInstanceMethod("timeIntervalSinceReferenceDate")
    double timeIntervalSinceReferenceDate();

    @OccInstanceMethod("laterDate:")
    FoundationDate laterDate(FoundationDate anotherDate);

    @OccInstanceMethod("compare:")
    int compareTo(FoundationDate anotherDate);

    @OccInstanceMethod("timeIntervalSinceNow")
    double timeIntervalSinceNow();

    @OccInstanceMethod("timeIntervalSinceDate:")
    double timeIntervalSinceDate(FoundationDate anotherDate);

    @OccInstanceMethod("timeIntervalSince1970")
    double timeIntervalSince1970();

    @OccInstanceMethod("addTimeInterval:")
    @Deprecated
    FoundationDate addTimeInterval(double seconds);

    @OccInstanceMethod("dateByAddingTimeInterval:")
    FoundationDate addSeconds(double seconds);

    @Override
    void encode(Coder encoder);

    @Override
    FoundationDate copy();

    @Override
    FoundationDateFactory getFactory();

    @Override
    CoreDate toCoreType();
}
