/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.corefoundation;

import org.kavaproject.kavatouch.internal.CEnumConstant;
import org.kavaproject.kavatouch.internal.CTypedef;
import org.kavaproject.kavatouch.internal.Header;

import java.util.EnumSet;

@Header("CFDate")
@CTypedef("CFGregorianUnitFlags")
public enum CoreGregorianUnitFlags {
    @CEnumConstant(value = "kCFGregorianUnitsYears", constantValue = 1 << 0)
    YEARS(1 << 0),

    @CEnumConstant(value = "kCFGregorianUnitsMonths", constantValue = 1 << 1)
    MONTHS(1 << 1),

    @CEnumConstant(value = "kCFGregorianUnitsDays", constantValue = 1 << 2)
    DAYS(1 << 2),

    @CEnumConstant(value = "kCFGregorianUnitsHours", constantValue = 1 << 3)
    HOURS(1 << 3),

    @CEnumConstant(value = "kCFGregorianUnitsMinutes", constantValue = 1 << 4)
    MINUTES(1 << 4),

    @CEnumConstant(value = "kCFGregorianUnitsSeconds", constantValue = 1 << 5)
    SECONDS(1 << 5);
    @CEnumConstant(value = "kCFGregorianAllUnits", constantValue = 0x00FFFFFF)
    public static final EnumSet<CoreGregorianUnitFlags> ALL_UNITS = EnumSet.allOf(CoreGregorianUnitFlags.class);

    CoreGregorianUnitFlags(int value) {
    }
}
