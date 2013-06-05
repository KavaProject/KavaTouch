/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.foundation;

import org.kavaproject.kavatouch.internal.Header;
import org.kavaproject.kavatouch.internal.OccClass;
import org.kavaproject.kavatouch.internal.OccInstanceMethod;
import org.kavaproject.kavatouch.runtime.Factory;

import java.util.Map;

@Header("NSAttributedString")
@OccClass("NSAttributedString")
public interface AttributedStringFactory extends CodingFactory, Factory {
    @OccInstanceMethod("initWithString:")
    AttributedString create(String string);

    @OccInstanceMethod("initWithAttributedString:")
    AttributedString create(AttributedString attributedString);

    @OccInstanceMethod("initWithString:attributes:")
    AttributedString create(String string, Map attributes);

    @Override
    AttributedString create(Coder decoder);

    AttributedString create();
}
