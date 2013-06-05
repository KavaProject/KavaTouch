/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.interfacebuilder;

import java.util.List;

public class IBObjectRecord {
    public static final String KeyObjectID = "objectID";
    public static final String KeyObject = "object";
    public static final String KeyChildren = "children";
    public static final String KeyObjectName = "objectName";
    public static final String KeyParent = "parent";
    public int objectID;
    public List object;
    public List children;
    public String objectName;
    public Object parent;
}
