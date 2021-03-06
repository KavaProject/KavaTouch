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

@Header("CFPropertyList")
@CTypedef("CFPropertyListMutabilityOptions")
public enum CorePropertyListMutabilityOptions {
    @CEnumConstant(value = "kCFPropertyListImmutable", constantValue = 0)
    IMMUTABLE(0),

    @CEnumConstant(value = "kCFPropertyListMutableContainers", constantValue = 1)
    MUTABLE_CONTAINERS(1),

    @CEnumConstant(value = "kCFPropertyListMutableContainersAndLeaves", constantValue = 2)
    MUTABLE_CONTAINERS_AND_LEAVES(2);

    CorePropertyListMutabilityOptions(int value) {
    }
}
