/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.foundation;

import org.kavaproject.kavatouch.corefoundation.CoreBundle;
import org.kavaproject.kavatouch.internal.Header;
import org.kavaproject.kavatouch.internal.OccClass;
import org.kavaproject.kavatouch.internal.OccConstant;
import org.kavaproject.kavatouch.internal.OccInstanceMethod;
import org.kavaproject.kavatouch.runtime.Creatable;
import org.kavaproject.kavatouch.runtime.Factory;

import java.util.List;
import java.util.Map;

@Header("NSBundle")
@OccClass("NSBundle")
public interface Bundle extends Creatable, CoreBridge<CoreBundle> {
    @OccConstant("NSBundleDidLoadNotification")
    String NOTIFICATION_DID_LOAD = "NSBundleDidLoadNotification";

    @OccInstanceMethod("classNamed:")
    Factory factoryNamed(String name);

    @OccInstanceMethod("principalClass")
    Factory principalFactory();

    @OccInstanceMethod("URLForResource:withExtension:subdirectory:")
    URL urlForResource(String name, String extension, String subpath);

    @OccInstanceMethod("URLForResource:withExtension:")
    URL urlForResource(String name, String extension);

    @OccInstanceMethod(value = "pathForResource:ofType:")
    String pathForResourceOfType(String resourceName, String extension);

    @OccInstanceMethod("URLsForResourcesWithExtension:subdirectory:")
    List<URL> urlsForResources(String extension, String subdirectory);

    @OccInstanceMethod("pathForResource:ofType:inDirectory:")
    String pathForResource(String resourceName, String extension, String directory);

    @OccInstanceMethod("URLForResource:withExtension:subdirectory:localization:")
    URL urlForResource(String name, String extension, String subdirectory, String localizationName);

    @OccInstanceMethod("pathForResource:ofType:inDirectory:forLocalization:")
    String pathForResource(String name, String typeExtension, String directory, String localizationName);

    @OccInstanceMethod(value = "pathsForResourcesOfType:inDirectory:")
    List<String> pathsForResources(String extension, String directory);

    @OccInstanceMethod("URLsForResourcesWithExtension:subdirectory:localization:")
    List<URL> urlsForResources(String extension, String subdirectory, String localizationName);

    @OccInstanceMethod("pathsForResourcesOfType:inDirectory:forLocalization:")
    List<String> pathsForResources(String extension, String directory, String localizationName);

    @OccInstanceMethod("resourcePath")
    String resourcePath();

    @OccInstanceMethod("bundleURL")
    URL bundleURL();

    @OccInstanceMethod("bundlePath")
    String bundlePath();

    @OccInstanceMethod("bundleIdentifier")
    String bundleIdentifier();

    @OccInstanceMethod("infoDictionary")
    Map<String, Object> infoDictionary();

    @OccInstanceMethod("objectForInfoDictionaryKey:")
    Object objectForInfoDictionaryKey();

    @OccInstanceMethod("builtInPlugInsURL")
    URL builtInPlugInsURL();

    @OccInstanceMethod("builtInPlugInsPath")
    String builtInPlugInsPath();

    @OccInstanceMethod("executableURL")
    URL executableURL();

    @OccInstanceMethod("executablePath")
    String executablePath();

    @OccInstanceMethod("URLForAuxiliaryExecutable:")
    URL URLForAuxiliaryExecutable(String executableName);

    @OccInstanceMethod("pathForAuxiliaryExecutable:")
    String pathForAuxiliaryExecutable(String executableName);

    @OccInstanceMethod("privateFrameworksURL")
    URL privateFrameworksURL();

    @OccInstanceMethod("privateFrameworksPath")
    String privateFrameworksPath();

    @OccInstanceMethod("sharedFrameworksURL")
    URL sharedFrameworksURL();

    @OccInstanceMethod("sharedFrameworksPath")
    String sharedFrameworksPath();

    @OccInstanceMethod("sharedSupportURL")
    URL sharedSupportURL();

    @OccInstanceMethod("sharedSupportPath")
    String sharedSupportPath();

    @OccInstanceMethod("resourceURL")
    URL resourceURL();

    @OccInstanceMethod("localizedStringForKey:value:table:")
    String localizedStringForKeyValueTable(FoundationString key, String value, FoundationString tableName);

    @OccInstanceMethod("executableArchitectures")
    List executableArchitectures();

    @OccInstanceMethod("preflightAndReturnError:")
    boolean preflight() throws RuntimeException;

    @OccInstanceMethod("loadAndReturnError:")
    boolean load() throws RuntimeException;

    @OccInstanceMethod("isLoaded")
    boolean isLoaded();

    @OccInstanceMethod("unload")
    boolean unload();

    @OccInstanceMethod("localizations")
    List<String> localizations();

    @OccInstanceMethod("developmentLocalization")
    String developmentLocalization();

    @OccInstanceMethod("preferredLocalizations")
    List<String> preferredLocalizations();

    @OccInstanceMethod("localizedInfoDictionary")
    Map<String, Object> localizedInfoDictionary();

    @Header("UINibLoading")
    @OccInstanceMethod("loadNibNamed:owner:options:")
    List loadNib(String filename, Object owner, UINibLoadingOptions options);

    @Override
    BundleFactory getFactory();
}
