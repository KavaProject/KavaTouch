/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.corefoundation;

import org.kavaproject.kavatouch.corefoundation.staging.CoreBundleExecutableArchitecture;
import org.kavaproject.kavatouch.corefoundation.staging.CorePlugIn;
import org.kavaproject.kavatouch.internal.*;
import org.kavaproject.kavatouch.util.NotImplementedException;
import org.kavaproject.kavatouch.util.OutArg;

import java.util.List;
import java.util.Map;

@Header("CFBundle")
@OpaqueType("CFBundleRef")
public class CoreBundle implements CoreType {
    @CData("kCFBundleInfoDictionaryVersionKey")
    public static final String KEY_INFO_DICTIONARY_VERSION = "kCFBundleInfoDictionaryVersionKey";
    @CData("kCFBundleExecutableKey")
    public static final String KEY_EXECUTABLE = "kCFBundleExecutableKey";
    @CData("kCFBundleIdentifierKey")
    public static final String KEY_IDENTIFIER = "kCFBundleIdentifierKey";
    @CData("kCFBundleVersionKey")
    public static final String KEY_VERSION = "kCFBundleVersionKey";
    @CData("kCFBundleDevelopmentRegionKey")
    public static final String KEY_DEVELOPMENT_REGION = "kCFBundleDevelopmentRegionKey";
    @CData("kCFBundleNameKey")
    public static final String KEY_NAME = "kCFBundleNameKey";
    @CData("kCFBundleLocalizationsKey")
    public static final String KEY_LOCALIZATIONS = "kCFBundleLocalizationsKey";
    private final CoreURL mBundleURL;
    private String mIdentifier;
    private String mDevelopmentRegion;
    private int mVersionNumber;

    private CoreBundle(CoreURL bundleURL) {
        mBundleURL = bundleURL;
        mIdentifier = bundleURL.copyLastPathComponent();
    }

    @CFunction("CFBundleCreate")
    public static CoreBundle create(CoreURL bundleURL) {
        String path = bundleURL.toString();
        String id = SessionProvider.session.getBundleID(path);
        if (id == null) {
            CoreBundle bundle = new CoreBundle(bundleURL);
            id = bundle.getIdentifier();
            SessionProvider.session.putBundle(id, path, bundle);
            return bundle;
        } else {
            return getBundle(id);
        }
    }

    @CFunction("CFBundleGetBundleWithIdentifier")
    public static CoreBundle getBundle(String bundleID) {
        return SessionProvider.session.getBundle(bundleID);
    }

    @CFunction("CFBundleGetIdentifier")
    public String getIdentifier() {
        return mIdentifier;
    }

    @CFunction("CFBundleCreateBundlesFromDirectory")
    public static List<CoreBundle> createBundles(CoreURL directoryURL, String bundleType) {
        throw new NotImplementedException();
    }

    @CFunction("CFBundleGetAllBundles")
    public static final List<CoreBundle> getAllBundles() {
        throw new NotImplementedException();
    }

    @CFunction("CFBundleGetMainBundle")
    public static CoreBundle getMainBundle() {
        return getBundle(SessionProvider.session.getMainBundleID());
    }

    @CFunction("CFBundleCopyLocalizationsForPreferences")
    public static List copyLocalizationsForPreferences(List possibleLocalizations, List preferredLocalizations) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CFBundleCopyLocalizationsForURL")
    public static List copyLocalizationsForURL(CoreURL url) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CFBundleCopyPreferredLocalizationsFromArray")
    public static List copyPreferredLocalizationsFromArray(List locArray) {
        throw new NotImplementedException(); //TODO
    }

    @CMacro("CFCopyLocalizedString")
    public static String copyDefaultLocalizedString(String key, String comment) {
        throw new NotImplementedException(); //TODO
    }

    @CMacro("CFCopyLocalizedStringFromTable")
    public static String copyDefaultLocalizedString(String key, String tableName, String comment) {
        throw new NotImplementedException(); //TODO
    }

    @CMacro("CFCopyLocalizedStringFromTableInBundle")
    public static String copyDefaultLocalizedString(String key, String tableName, CoreBundle bundle, String comment) {
        throw new NotImplementedException(); //TODO
    }

    @CMacro("CFCopyLocalizedStringWithDefaultValue")
    public static String copyDefaultLocalizedString(String key, String tableName, CoreBundle bundle, String value,
                                                    String comment) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CFBundleCopyInfoDictionaryInDirectory")
    public static Map<String, Object> copyInfoDictionaryInDirectory(CoreURL bundleURL) {
        throw new NotImplementedException();
    }

    @CFunction("CFBundleCopyInfoDictionaryForURL")
    public static Map<String, Object> copyInfoDictionaryForURL(CoreURL url) {
        throw new NotImplementedException();
    }

    @CFunction("CFBundleGetPackageInfoInDirectory")
    public static boolean getPackageInfo(CoreURL url, OutArg<String> packageType, OutArg<String> packageCreator) {
        throw new NotImplementedException();
    }

    @CFunction("CFBundleCopyExecutableArchitecturesForURL")
    public static List<CoreBundleExecutableArchitecture> copyExecutableArchitectures(CoreURL url) {
        throw new NotImplementedException();
    }

    @CFunction("CFBundleCopyResourceURLInDirectory")
    public static CoreURL copyResourceURL(CoreURL bundleURL, String resourceName, String resourceType,
                                          String subDirName) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CFBundleCopyResourceURLsOfTypeInDirectory")
    public static List<CoreURL> copyResourceURLs(CoreURL bundleURL, String resourceType, String subDirName) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public String toString() {
        return mIdentifier;
    }

    @CFunction("CFBundleIsExecutableLoaded")
    public boolean isExecutableLoaded() {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CFBundlePreflightExecutable")
    public boolean preflightExecutable() throws RuntimeException {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CFBundleLoadExecutableAndReturnError")
    public boolean loadExecutable() throws RuntimeException {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CFBundleUnloadExecutable")
    public void unloadExecutable() {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CFBundleCopyAuxiliaryExecutableURL")
    public CoreURL copyAuxiliaryExecutableURL(String executableName) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CFBundleCopyBuiltInPlugInsURL")
    public CoreURL copyBuiltInPlugInsURL() {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CFBundleCopyExecutableURL")
    public CoreURL copyExecutableURL() {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CFBundleCopyPrivateFrameworksURL")
    public CoreURL copyPrivateFrameworksURL() {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CFBundleCopyResourcesDirectoryURL")
    public CoreURL copyResourcesDirectoryURL() {
        return mBundleURL;
    }

    @CFunction("CFBundleCopySharedFrameworksURL")
    public CoreURL copySharedFrameworksURL() {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CFBundleCopySharedSupportURL")
    public CoreURL copySharedSupportURL() {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CFBundleCopySupportFilesDirectoryURL")
    public CoreURL copySupportFilesDirectoryURL() {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CFBundleCloseBundleResourceMap")
    public void closeBundleResourceMap(CFBundleRefNum refNum) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CFBundleCopyResourceURL")
    public CoreURL copyResourceURL(String resourceName, String resourceType, String subDirName) {
        CoreURL resourceDirUrl = subDirName == null ? mBundleURL : mBundleURL.copyAppendingPathComponent(subDirName,
                true);
        return resourceDirUrl.copyAppendingPathComponent(resourceName + "." + resourceType, false);
    }

    @CFunction("CFBundleCopyResourceURLsOfType")
    public List<CoreURL> copyResourceURLs(String resourceType, String subDirName) {
        throw new NotImplementedException();
    }

    @CFunction("CFBundleCopyResourceURLForLocalization")
    public CoreURL copyResourceURL(String resourceName, String resourceType, String subDirName,
                                   String localizationName) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CFBundleCopyResourceURLsOfTypeForLocalization")
    public List<CoreURL> copyResourceURLs(String resourceType, String subDirName, String localizationName) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CFBundleOpenBundleResourceFiles")
    public int openBundleResourceFiles(CFBundleRefNum refNum, CFBundleRefNum localizedRefNum) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CFBundleOpenBundleResourceMap")
    public CFBundleRefNum openBundleResourceMap() {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CFBundleCopyBundleLocalizations")
    public List copyBundleLocalizations() {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CFBundleCopyLocalizedString")
    public String copyLocalizedString(String key, String value, String tableName) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CFBundleGetDataPointerForName")
    public Object getDataPointerForName(String symbolName) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CFBundleGetDataPointersForNames")
    public void getDataPointersForNames(List<String> symbolNames, List stbl) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CFBundleGetFunctionPointerForName")
    public Runnable getFunctionPointerForName(String functionName) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CFBundleGetFunctionPointersForNames")
    public void getFunctionPointersForNames(List<String> functionNames, List<Runnable> ftbl) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CFBundleGetPlugIn")
    public CorePlugIn getPlugIn() {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CFBundleCopyBundleURL")
    public CoreURL copyBundleURL() {
        return mBundleURL;
    }

    @CFunction("CFBundleGetDevelopmentRegion")
    public String getDevelopmentRegion() {
        return mDevelopmentRegion;
    }

    @CFunction("CFBundleGetInfoDictionary")
    public Map<String, Object> getInfoDictionary() {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CFBundleGetLocalInfoDictionary")
    public Map<String, Object> getLocalInfoDictionary() {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CFBundleGetValueForInfoDictionaryKey")
    public Object getValueForInfoDictionaryKey(String key) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CFBundleGetPackageInfo")
    public void getPackageInfo(OutArg<String> packageType, OutArg<String> packageCreator) {
        throw new NotImplementedException();
    }

    @CFunction("CFBundleCopyExecutableArchitectures")
    public List<CoreBundleExecutableArchitecture> copyExecutableArchitectures() {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CFBundleGetVersionNumber")
    public int getVersionNumber() {
        return mVersionNumber;
    }

    public static class CFBundleRefNum {
        private CFBundleRefNum() {
            //empty
        }
    }
}
