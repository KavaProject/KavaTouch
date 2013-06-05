/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.dispatch.staging;

import org.kavaproject.kavatouch.internal.CTypedef;

@CTypedef("dispatch_source_vnode_flags_t")
public enum DispatchSourceVnodeFlags {
    DISPATCH_VNODE_DELETE(0x1),

    DISPATCH_VNODE_WRITE(0x2),

    DISPATCH_VNODE_EXTEND(0x4),

    DISPATCH_VNODE_ATTRIB(0x8),

    DISPATCH_VNODE_LINK(0x10),

    DISPATCH_VNODE_RENAME(0x20),

    DISPATCH_VNODE_REVOKE(0x40);
    private final int value;

    DispatchSourceVnodeFlags(int value) {
        this.value = value;
    }
}
