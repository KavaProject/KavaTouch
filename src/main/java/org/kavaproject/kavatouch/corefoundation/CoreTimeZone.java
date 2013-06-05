/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.corefoundation;

import org.kavaproject.kavatouch.corefoundation.staging.CoreLocale;
import org.kavaproject.kavatouch.corefoundation.staging.CoreTimeZoneNameStyle;
import org.kavaproject.kavatouch.internal.CFunction;
import org.kavaproject.kavatouch.internal.Header;
import org.kavaproject.kavatouch.internal.OpaqueType;
import org.kavaproject.kavatouch.util.NotImplementedException;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

@Header("CFTimeZone")
@OpaqueType("CFTimeZoneRef")
public class CoreTimeZone implements CoreType {
    private TimeZone mAdaptee;

    @CFunction("CFTimeZoneCreateWithName")
    public CoreTimeZone(String name, boolean tryAbbrev) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CFTimeZoneCreateWithTimeIntervalFromGMT")
    public CoreTimeZone(double secondsFromGMT) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CFTimeZoneCreate")
    public CoreTimeZone(String name, ByteBuffer data) {
        throw new NotImplementedException(); //TODO
    }

    private CoreTimeZone(TimeZone adaptee) {
        mAdaptee = adaptee;
    }

    @CFunction("CFTimeZoneCopyAbbreviationDictionary")
    public static Map<String, String> copyAbbreviations() {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CFTimeZoneCopyDefault")
    public static final CoreTimeZone copyDefault() {
        return copySystem();
    }

    @CFunction("CFTimeZoneCopySystem")
    public static CoreTimeZone copySystem() {
        return new CoreTimeZone(TimeZone.getDefault());
    }

    @CFunction("CFTimeZoneSetDefault")
    public static void setDefault(CoreTimeZone timeZone) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CFTimeZoneCopyKnownNames")
    public static List<String> copyKnownNames() {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CFTimeZoneResetSystem")
    public static void resetSystem() {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CFTimeZoneSetAbbreviationDictionary")
    public static void setAbbreviations(Map<String, String> abbreviations) {
        throw new NotImplementedException(); //TODO
    }

    public TimeZone toTimeZone() {
        return mAdaptee;
    }

    @CFunction("CFTimeZoneCopyAbbreviation")
    public String copyAbbreviation(double secondsSince2001) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CFTimeZoneGetName")
    public String getName() {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CFTimeZoneCopyLocalizedName")
    public String copyLocalizedName(CoreTimeZoneNameStyle style, CoreLocale locale) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CFTimeZoneGetSecondsFromGMT")
    public double getSecondsFromGMT(double secondsSince2001) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CFTimeZoneGetData")
    public ByteBuffer getData() {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CFTimeZoneIsDaylightSavingTime")
    public boolean isDaylightSavingTime(double secondsSince2001) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CFTimeZoneGetDaylightSavingTimeOffset")
    public double getDaylightSavingTimeOffset(double secondsSince2001) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CFTimeZoneGetNextDaylightSavingTimeTransition")
    public double getNextDaylightSavingTimeTransition(double secondsSince2001) {
        throw new NotImplementedException(); //TODO
    }
}
