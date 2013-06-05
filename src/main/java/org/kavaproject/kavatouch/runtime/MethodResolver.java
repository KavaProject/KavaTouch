/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.runtime;

import org.kavaproject.kavatouch.internal.OccClassMethod;
import org.kavaproject.kavatouch.internal.OccInstanceMethod;
import org.kavaproject.kavatouch.internal.OccProtocolInstanceMethod;

public interface MethodResolver {
    <T> void register(Class<T> clazz, SEL selector, IMP<?, T> imp);

    String[] getTypeEncoding(Object receiver, SEL selector);

    @OccClassMethod(value = "resolveInstanceMethod:")
    boolean resolveInstanceMethod(Factory factory, SEL selector);

    @OccProtocolInstanceMethod("respondsToSelector:")
    boolean respondsToSelector(Object receiver, SEL sel);

    @OccClassMethod(value = "instancesRespondToSelector:")
    boolean productsRespondToSelector(Factory receiver, SEL selector);

    @OccInstanceMethod(value = "methodForSelector:")
    IMP findMethod(Object receiver, SEL selector);
}
