/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.interfacebuilder;

import org.kavaproject.kavatouch.uikit.UIStatusBarStyle;

import java.util.List;

/**
 * TODO
 */
public class InfoDictionary {
    public static final String CF_BUNDLE_PACKAGE_TYPE = "CFBundlePackageType";
    public static final String CF_BUNDLE_PACKAGE_TYPE_APPL = "APPL";
    public static final String CF_BUNDLE_PACKAGE_TYPE_FMWK = "FMWK";
    public static final String CF_BUNDLE_PACKAGE_TYPE_BNDL = "BNDL";
    public static InfoDictionary mainBundleDictionary = new InfoDictionary();
    public String developmentRegion;
    public String displayName;
    public List documentTypes;
    public String executable;
    public List<String> iconFiles;
    public String identifier;
    public String infoDictionaryVersion;
    public String name;
    public String packageType;
    public String signature;
    public List urlTypes;
    public String version;
    public boolean requiresIPhoneOS;
    public IBDocument mainNibFile;
    public UIStatusBarStyle statusBarStyle;
    public List exportedTypeDeclarations;
    public List importedTypeDeclarations;
}
