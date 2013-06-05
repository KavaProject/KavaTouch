/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.runtime;

import org.kavaproject.kavatouch.internal.Header;
import org.kavaproject.kavatouch.internal.OccClass;
import org.kavaproject.kavatouch.internal.OccClassMethod;
import org.kavaproject.kavatouch.internal.OccProtocolInstanceMethod;

@Header("NSObject")
@OccClass("NSObject")
public interface FactoryRegistry {
    Factory ROOT = new RootFactory();

    void register(Class<? extends Factory> clazz, Factory factory);

    @OccProtocolInstanceMethod("isMemberOfClass:")
    boolean isProductMember(Object receiver, Factory factory);

    @OccClassMethod(value = "class")
    Factory getFactory(Object product);

    @OccProtocolInstanceMethod("isKindOfClass:")
    boolean isKindOfCreatable(Object receiver, Factory factory);

    @OccClassMethod(value = "isSubclassOfClass:")
    boolean isSubfactory(Factory factory, Factory occClass);

    @OccClassMethod(value = "superclass")
    Factory getSuperfactory(Object product);

    public static class RootFactory implements Factory {
    }
}
