/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.interfacebuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class IBDocument {
    public static final String KeySystemTarget = "IBDocument.SystemTarget";
    public static final String KeySystemVersion = "IBDocument.SystemVersion";
    public static final String KeyInterfaceBuilderVersion = "IBDocument.InterfaceBuilderVersion";
    public static final String KeyAppKitVersion = "IBDocument.AppKitVersion";
    public static final String KeyHIToolboxVersion = "IBDocument.HIToolboxVersion";
    public static final String KeyEditedObjectIDs = "IBDocument.EditedObjectIDs";
    public static final String KeyPluginDependencies = "IBDocument.PluginDependencies";
    public static final String KeyMetadata = "IBDocument.Metadata";
    public static final String KeyRootObjects = "IBDocument.RootObjects";
    public static final String KeyObjects = "IBDocument.Objects";
    public static final String KeyClasses = "IBDocument.Classes";
    public static final String KeyLocalizationMode = "IBDocument.localizationMode";
    public static final String KeyLastKnownRelativeProjectPath = "IBDocument.LastKnownRelativeProjectPath";
    public static final String KeyDefaultPropertyAccessControl = "IBDocument.defaultPropertyAccessControl";
    public int systemTarget;
    public String systemVersion;
    public String interfaceBuilderVersion;
    public String appKitVersion;
    public String hiToolboxVersion;
    public List<Integer> editedObjectIDs;
    public List<String> pluginDependencies;
    public Map metadata;
    public List rootObjects = new ArrayList();
    public IBObjectContainer objects;
    public IBClassDescriber classes;
    public int localizationMode;
    public String lastKnownRelativeProjectPath;
    public int defaultPropertyAccessControl;
}
