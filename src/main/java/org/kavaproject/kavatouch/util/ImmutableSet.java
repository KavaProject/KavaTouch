/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.util;

import java.util.*;

public class ImmutableSet<T> implements Set<T> {
    private HashSet<T> elements = new HashSet<T>();

    public static <T> ImmutableSet<T> of(T... elements) {
        ImmutableSet<T> set = new ImmutableSet<T>();
        Collections.addAll(set.elements, elements);
        return set;
    }

    public static <T> ImmutableSet<T> union(Collection<T> first, Collection<T> second) {
        ImmutableSet<T> set = new ImmutableSet<T>();
        set.elements.addAll(first);
        set.elements.addAll(second);
        return set;
    }

    @Override
    public boolean add(T object) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean contains(Object object) {
        return elements.contains(object);
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        return elements.containsAll(collection);
    }

    @Override
    public boolean isEmpty() {
        return elements.isEmpty();
    }

    @Override
    public Iterator<T> iterator() {
        return Collections.unmodifiableSet(elements).iterator();
    }

    @Override
    public boolean remove(Object object) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        return elements.size();
    }

    @Override
    public Object[] toArray() {
        return elements.toArray();
    }

    @Override
    public <T1 extends Object> T1[] toArray(T1[] array) {
        return elements.toArray(array);
    }
}
