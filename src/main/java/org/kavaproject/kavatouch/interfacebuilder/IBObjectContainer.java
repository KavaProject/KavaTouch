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
import java.util.Map;

public class IBObjectContainer {
    public static final String KeyConnectionRecords = "connectionRecords";
    public static final String KeyObjectRecords = "objectRecords";
    public static final String KeyFlattenedProperties = "flattenedProperties";
    public static final String KeyUnlocalizedProperties = "unlocalizedProperties";
    public static final String KeyActiveLocalization = "activeLocalization";
    public static final String KeyLocalizations = "localizations";
    public static final String KeySourceID = "sourceID";
    public static final String KeyMaxID = "maxID";
    public List<IBConnectionRecord> connectionRecords;
    public IBMutableOrderedSet objectRecords;
    public Map flattenedProperties; //TODO
    public Map unlocalizedProperties; //TODO
    public Object activeLocalization;
    public Map localizations;
    public Object sourceID;
    public int maxID;
}
