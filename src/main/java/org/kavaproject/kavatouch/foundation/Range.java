/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.foundation;

import org.kavaproject.kavatouch.internal.CFunction;
import org.kavaproject.kavatouch.internal.CTypedef;

import java.util.Scanner;

@CTypedef
public class Range {

    public int location, length;

    private Range() {
    }

    @CFunction("NSMakeRange")
    public Range(int location, int length) {
        this.location = location;
        this.length = length;
    }

    @CFunction("NSRangeFromString")
    public Range(String string) {
        Scanner scanner = new Scanner(string);
//        //anything other than alphanumberic characters,
//        //comma, dot or negative sign is skipped
//        scanner.useDelimiter("[^\\p{Alnum},\\.-]");
        if (scanner.hasNextInt()) {
            location = scanner.nextInt();
        }
        if (scanner.hasNextInt()) {
            length = scanner.nextInt();
        }
    }

    @CFunction("NSEqualRanges")
    @Override
    public boolean equals(Object other) {
        return other instanceof Range && other != null && location == ((Range) other).location && length == ((Range)
                other).length;
    }

    @CFunction("NSStringFromRange")
    @Override
    public String toString() {
        return "{" + location + ", " + length + "}";
    }

    @CFunction("NSIntersectionRange")
    public Range intersection(Range other) {
        Range range = new Range();
        range.location = Math.max(location, other.location);
        int end = Math.min(location + length, other.location + other.length);
        range.length = Math.max(end - range.location, 0);
        return range;
    }

    @CFunction("NSLocationInRange")
    public boolean contains(int location) {
        return location >= this.location && location < this.location + length;
    }

    @CFunction("NSMaxRange")
    public int maxRange() {
        return location + length;
    }

    @CFunction("NSUnionRange")
    public Range union(Range other) {
        Range range = new Range();
        range.location = Math.min(location, other.location);
        range.length = Math.max(length, other.length);
        return range;
    }
}
