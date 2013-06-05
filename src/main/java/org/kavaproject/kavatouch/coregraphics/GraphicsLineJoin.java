/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.coregraphics;

import android.graphics.Paint;
import org.kavaproject.kavatouch.internal.CEnumConstant;
import org.kavaproject.kavatouch.internal.CTypedef;
import org.kavaproject.kavatouch.internal.Header;

@Header("CGPath")
@CTypedef("CGLineJoin")
public enum GraphicsLineJoin {
    @CEnumConstant("kCGLineJoinBevel")
    BEVEL(Paint.Join.BEVEL),
    @CEnumConstant("kCGLineJoinMiter")
    MITER(Paint.Join.MITER),
    @CEnumConstant("kCGLineJoinRound")
    ROUND(Paint.Join.ROUND);
    public Paint.Join androidJoin;

    GraphicsLineJoin(Paint.Join join) {
        androidJoin = join;
    }
}
