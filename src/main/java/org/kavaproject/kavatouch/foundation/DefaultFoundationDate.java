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
import org.kavaproject.kavatouch.foundation.staging.FoundationLocale;
import org.kavaproject.kavatouch.util.AndroidConversions;
import org.kavaproject.kavatouch.util.NotImplementedException;

import java.text.DateFormat;
import java.util.Locale;

public class DefaultFoundationDate implements FoundationDate {
    private final FoundationDateFactory mFoundationDateFactory;
    private final CoreDate mCoreDate;

    protected DefaultFoundationDate(CoreDate coreDate, FoundationDateFactory foundationDateFactory) {
        mCoreDate = coreDate;
        mFoundationDateFactory = foundationDateFactory;
    }

    protected DefaultFoundationDate(Coder decoder, FoundationDateFactory foundationDateFactory) {
        mFoundationDateFactory = foundationDateFactory;
        mCoreDate = new CoreDate(0); //TODO
    }

    @Override
    public boolean equals(Object other) {
        return other != null && other instanceof DefaultFoundationDate && mCoreDate.compareTo((
                (DefaultFoundationDate) other).mCoreDate) == 0;
    }

    @Override
    public int hashCode() {
        return mCoreDate.hashCode();
    }

    @Override
    public String toString() {
        return toString(FoundationLocale.currentLocale().adaptee);
    }

    @Override
    public String toString(Locale locale) {
        DateFormat format = DateFormat.getDateInstance(DateFormat.DEFAULT, locale);
        java.util.Date date = new java.util.Date(AndroidConversions.toMillisSince1970(mCoreDate.getAbsoluteTime()));
        return format.format(date);
    }

    @Override
    public FoundationDate earlierDate(FoundationDate anotherDate) {
        return timeIntervalSinceReferenceDate() <= anotherDate.timeIntervalSinceReferenceDate() ? this : anotherDate;
    }

    @Override
    public double timeIntervalSinceReferenceDate() {
        return mCoreDate.getAbsoluteTime();
    }

    @Override
    public FoundationDate laterDate(FoundationDate anotherDate) {
        return timeIntervalSinceReferenceDate() >= anotherDate.timeIntervalSinceReferenceDate() ? this : anotherDate;
    }

    @Override
    public int compareTo(FoundationDate anotherDate) {
        return mCoreDate.compareTo(anotherDate.toCoreType());
    }

    @Override
    public double timeIntervalSinceNow() {
        return timeIntervalSinceDate(mFoundationDateFactory.create());
    }

    @Override
    public double timeIntervalSinceDate(FoundationDate anotherDate) {
        return timeIntervalSinceReferenceDate() - anotherDate.timeIntervalSinceReferenceDate();
    }

    @Override
    public double timeIntervalSince1970() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public FoundationDate addTimeInterval(double seconds) {
        return addSeconds(seconds);
    }

    @Override
    public FoundationDate addSeconds(double seconds) {
        return mFoundationDateFactory.createWithTimeIntervalSinceDate(seconds, this);
    }

    @Override
    public void encode(Coder encoder) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public FoundationDate copy() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public FoundationDateFactory getFactory() {
        return mFoundationDateFactory;
    }

    @Override
    public CoreDate toCoreType() {
        return mCoreDate;
    }
}
