/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.corefoundation;

import org.kavaproject.kavatouch.internal.CData;
import org.kavaproject.kavatouch.internal.CFunction;
import org.kavaproject.kavatouch.internal.CTypedef;
import org.kavaproject.kavatouch.internal.Header;
import org.kavaproject.kavatouch.util.AndroidConversions;

import java.util.Calendar;
import java.util.Date;
import java.util.EnumSet;
import java.util.GregorianCalendar;

@Header("CFDate")
@CTypedef(value = "CFAbsoluteTime", tokenGroup = "CFTimeUtils")
public class CoreAbsoluteTime {
    @CData(value = "kCFAbsoluteTimeIntervalSince1970", tokenGroup = "CFTimeUtils")
    public static final double INTERVAL_SINCE_1970 = 978307200.0;
    @CData("kCFAbsoluteTimeIntervalSince1904")
    public static final double INTERVAL_SINCE_1904 = 3061152000.0;

    @CFunction("CFAbsoluteTimeAddGregorianUnits")
    public static final double addGregorianUnits(double seconds, CoreTimeZone timeZone, CoreGregorianUnits units) {
        long secondsAsMillis = (long) (units.seconds * 1000);
        int fullSeconds = (int) (secondsAsMillis / 1000);
        int millis = (int) (secondsAsMillis % 1000);
        GregorianCalendar jCalendar = new GregorianCalendar(timeZone.toTimeZone());
        jCalendar.setTimeInMillis(AndroidConversions.toMillisSince1970(seconds));
        jCalendar.add(Calendar.YEAR, units.years);
        jCalendar.add(Calendar.MONTH, units.months);
        jCalendar.add(Calendar.DATE, units.days);
        jCalendar.add(Calendar.HOUR, units.hours);
        jCalendar.add(Calendar.MINUTE, units.minutes);
        jCalendar.add(Calendar.SECOND, fullSeconds);
        jCalendar.add(Calendar.MILLISECOND, millis);
        return AndroidConversions.toSecondsAbsoluteTime(jCalendar.getTimeInMillis());
    }

    @CFunction("CFAbsoluteTimeGetCurrent")
    public static final double getCurrent() {
        return AndroidConversions.toSecondsAbsoluteTime(new Date());
    }

    @CFunction("CFAbsoluteTimeGetDayOfWeek")
    public static final int getDayOfWeek(double seconds, CoreTimeZone timeZone) {
        GregorianCalendar jCalendar = new GregorianCalendar(timeZone.toTimeZone());
        jCalendar.setTimeInMillis(AndroidConversions.toMillisSince1970(seconds));
        switch (jCalendar.get(Calendar.DAY_OF_WEEK)) {
            case Calendar.MONDAY:
                return 1;
            case Calendar.TUESDAY:
                return 2;
            case Calendar.WEDNESDAY:
                return 3;
            case Calendar.THURSDAY:
                return 4;
            case Calendar.FRIDAY:
                return 5;
            case Calendar.SATURDAY:
                return 6;
            case Calendar.SUNDAY:
                return 7;
            default:
                throw new IndexOutOfBoundsException();
        }
    }

    @CFunction("CFAbsoluteTimeGetDayOfYear")
    public static final int getDayOfYear(double seconds, CoreTimeZone timeZone) {
        GregorianCalendar jCalendar = new GregorianCalendar(timeZone.toTimeZone());
        jCalendar.setTimeInMillis(AndroidConversions.toMillisSince1970(seconds));
        return jCalendar.get(Calendar.DAY_OF_YEAR);
    }

    @CFunction("CFAbsoluteTimeGetDifferenceAsGregorianUnits")
    public static final CoreGregorianUnits getDifferenceAsGregorianUnits(double seconds1, double seconds2,
                                                                         CoreTimeZone timeZone,
                                                                         EnumSet<CoreGregorianUnitFlags> unitFlags) {
        GregorianCalendar endCalendar = new GregorianCalendar(timeZone.toTimeZone());
        GregorianCalendar cursor = new GregorianCalendar(timeZone.toTimeZone());
        if (seconds1 < seconds2) {
            endCalendar.setTimeInMillis(AndroidConversions.toMillisSince1970(seconds2));
            cursor.setTimeInMillis(AndroidConversions.toMillisSince1970(seconds1));
        } else {
            endCalendar.setTimeInMillis(AndroidConversions.toMillisSince1970(seconds1));
            cursor.setTimeInMillis(AndroidConversions.toMillisSince1970(seconds2));
        }
        CoreGregorianUnits result = new CoreGregorianUnits();
        if (unitFlags.contains(CoreGregorianUnitFlags.YEARS)) {
            result.years = endCalendar.get(Calendar.YEAR) - cursor.get(Calendar.YEAR);
            cursor.add(Calendar.YEAR, result.years);
        }
        if (unitFlags.contains(CoreGregorianUnitFlags.MONTHS)) {
            while (cursor.before(endCalendar)) {
                cursor.add(Calendar.MONTH, 1);
                result.months++;
            }
            if (result.months > 0) {
                cursor.add(Calendar.MONTH, -1);
            }
        }
        if (unitFlags.contains(CoreGregorianUnitFlags.DAYS)) {
            while (cursor.before(endCalendar)) {
                cursor.add(Calendar.DATE, 1);
                result.days++;
            }
            if (result.days > 0) {
                cursor.add(Calendar.DATE, -1);
            }
        }
        if (unitFlags.contains(CoreGregorianUnitFlags.HOURS)) {
            while (cursor.before(endCalendar)) {
                cursor.add(Calendar.HOUR, 1);
                result.hours++;
            }
            if (result.hours > 0) {
                cursor.add(Calendar.HOUR, -1);
            }
        }
        if (unitFlags.contains(CoreGregorianUnitFlags.MINUTES)) {
            while (cursor.before(endCalendar)) {
                cursor.add(Calendar.MINUTE, 1);
                result.minutes++;
            }
            if (result.minutes > 0) {
                cursor.add(Calendar.MINUTE, -1);
            }
        }
        if (unitFlags.contains(CoreGregorianUnitFlags.SECONDS)) {
            result.seconds = seconds2 - (double) cursor.getTimeInMillis() / 1000;
        }
        if (seconds1 < seconds2) {
            result.years *= -1;
            result.months *= -1;
            result.days *= -1;
            result.hours *= -1;
            result.minutes *= -1;
            result.seconds *= -1;
        }
        return result;
    }

    @CFunction("CFAbsoluteTimeGetGregorianDate")
    public static final CoreGregorianDate getGregorianDate(double seconds, CoreTimeZone timeZone) {
        GregorianCalendar jCalendar = new GregorianCalendar(timeZone.toTimeZone());
        jCalendar.setTimeInMillis(AndroidConversions.toMillisSince1970(seconds));
        CoreGregorianDate date = new CoreGregorianDate();
        date.year = jCalendar.get(Calendar.YEAR);
        date.month = jCalendar.get(Calendar.MONTH);
        date.day = jCalendar.get(Calendar.DATE);
        date.hour = jCalendar.get(Calendar.HOUR);
        date.minute = jCalendar.get(Calendar.MINUTE);
        date.second = (double) jCalendar.get(Calendar.MILLISECOND) / 1000;
        return date;
    }

    @CFunction("CFAbsoluteTimeGetWeekOfYear")
    public static final int getWeekOfYear(double seconds, CoreTimeZone timeZone) {
        GregorianCalendar jCalendar = new GregorianCalendar(timeZone.toTimeZone());
        jCalendar.setTimeInMillis(AndroidConversions.toMillisSince1970(seconds));
        return jCalendar.get(Calendar.WEEK_OF_YEAR);
    }
}
