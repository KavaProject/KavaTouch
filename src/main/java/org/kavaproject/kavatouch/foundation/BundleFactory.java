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
import org.kavaproject.kavatouch.internal.OccClassMethod;
import org.kavaproject.kavatouch.internal.OccInstanceMethod;
import org.kavaproject.kavatouch.runtime.Factory;

import java.util.List;

@Header("NSBundle")
@OccClass("NSBundle")
public interface BundleFactory extends Factory, CoreBridgeFactory<CoreBundle> {
    @OccInstanceMethod(value = "initWithPath:")
    Bundle create(String fullPath);

    @OccInstanceMethod(value = "initWithURL:")
    Bundle create(URL url);

    @OccClassMethod(value = "bundleForClass:")
    Bundle create(Factory factory);

    @OccClassMethod(value = "bundleWithIdentifier:")
    Bundle createBundleForIdentifier(String identifier);

    @OccClassMethod("mainBundle")
    Bundle mainBundle();

    @OccClassMethod("allBundles")
    List<Bundle> allBundles();

    @OccClassMethod("allFrameworks")
    List<Bundle> allFrameworks();

    @OccClassMethod("pathForResource:ofType:inDirectory:")
    String pathForResource(String name, String extension, String bundleDirectory);

    @OccClassMethod("pathsForResourcesOfType:inDirectory:")
    List<String> pathsForResources(String extension, String bundleDirectory);

    @OccClassMethod("URLForResource:withExtension:subdirectory:inBundleWithURL:")
    URL urlForResource(String name, String extension, String subdirectory, URL bundleURL);

    @OccClassMethod("URLsForResourcesWithExtension:subdirectory:inBundleWithURL:")
    List<URL> urlsForResources(String extension, String subdirectory, URL bundleURL);

    @OccClassMethod("preferredLocalizationsFromArray:")
    List<String> preferredLocalizations(List<String> localizationsArray);

    @OccClassMethod("preferredLocalizationsFromArray:forPreferences:")
    List<String> preferredLocalizations(List<String> localizationsArray, List<String> preferencesArray);
}
