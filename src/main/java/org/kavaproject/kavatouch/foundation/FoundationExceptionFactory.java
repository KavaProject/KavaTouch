/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.foundation;

import org.kavaproject.kavatouch.internal.OccClass;
import org.kavaproject.kavatouch.internal.OccClassMethod;
import org.kavaproject.kavatouch.internal.OccInstanceMethod;
import org.kavaproject.kavatouch.runtime.Factory;

import java.util.List;

@OccClass("NSException")
public interface FoundationExceptionFactory extends CodingFactory, Factory {
    @OccClassMethod("raise:format:")
    RuntimeException getAdaptee(String name, String format, Object... args);

    @OccInstanceMethod("initWithName:reason:userInfo:")
    FoundationException create(String name, String reason, ExceptionUserInfo userInfo);

    @OccClassMethod("raise:format:arguments:")
    RuntimeException getAdaptee(String name, String format, List argsList);
}
