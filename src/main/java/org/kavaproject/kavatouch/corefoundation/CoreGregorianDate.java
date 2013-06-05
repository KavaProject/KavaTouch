/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.corefoundation;

import org.kavaproject.kavatouch.internal.CFunction;
import org.kavaproject.kavatouch.internal.CTypedef;
import org.kavaproject.kavatouch.internal.Header;
import org.kavaproject.kavatouch.util.AndroidConversions;

import java.util.Calendar;
import java.util.EnumSet;
import java.util.GregorianCalendar;

@Header("CFDate")
@CTypedef(value = "CFGregorianDate", tokenGroup = "CFTimeUtils")
public class CoreGregorianDate {
    public int year;
    public int month;
    public int day;
    public int hour;
    public int minute;
    public double second;

    @CFunction("CFGregorianDateGetAbsoluteTime")
    public static final double getAbsoluteTime(CoreGregorianDate gregorianDate, CoreTimeZone timeZone) {
        int intSecond = (int) Math.floor(gregorianDate.second);
        GregorianCalendar gregorianCalendar = new GregorianCalendar(gregorianDate.year, gregorianDate.month,
                gregorianDate.day, gregorianDate.hour, gregorianDate.minute, intSecond);
        gregorianCalendar.setTimeZone(timeZone.toTimeZone());
        return AndroidConversions.toSecondsAbsoluteTime(gregorianCalendar.getTimeInMillis());
    }

    @CFunction("CFGregorianDateIsValid")
    public static final boolean isValid(CoreGregorianDate gregorianDate, EnumSet<CoreGregorianUnitFlags> unitFlags) {
        GregorianCalendar calendar = new GregorianCalendar();
        if (unitFlags.contains(CoreGregorianUnitFlags.YEARS)) {
            calendar.set(Calendar.YEAR, gregorianDate.year);
        }
        if (unitFlags.contains(CoreGregorianUnitFlags.MONTHS)) {
            calendar.set(Calendar.MONTH, gregorianDate.month);
        }
        if (unitFlags.contains(CoreGregorianUnitFlags.DAYS)) {
            calendar.set(Calendar.DATE, gregorianDate.day);
        }
        if (unitFlags.contains(CoreGregorianUnitFlags.HOURS)) {
            calendar.set(Calendar.HOUR_OF_DAY, gregorianDate.hour);
        }
        if (unitFlags.contains(CoreGregorianUnitFlags.MINUTES)) {
            calendar.set(Calendar.MINUTE, gregorianDate.minute);
        }
        if (unitFlags.contains(CoreGregorianUnitFlags.SECONDS)) {
            int intSecond = (int) Math.floor(gregorianDate.second);
            calendar.set(Calendar.SECOND, intSecond);
        }
        calendar.setLenient(false);
        try {
            calendar.getTime();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
