/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.util;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public final class ReverseIterable<T> implements Iterable<T> {
    private final List<T> mList;

    public ReverseIterable(List<T> list) {
        mList = list;
    }

    public static <T> Iterable<T> wrap(final List<T> list) {
        return new ReverseIterable<T>(list);
    }

    @Override
    public Iterator<T> iterator() {
        final ListIterator<T> it = mList.listIterator(mList.size());
        return new Iterator<T>() {
            public boolean hasNext() {
                return it.hasPrevious();
            }

            public T next() {
                return it.previous();
            }

            public void remove() {
                it.remove();
            }
        };
    }
}
