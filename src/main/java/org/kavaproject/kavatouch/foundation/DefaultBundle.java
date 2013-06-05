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
import org.kavaproject.kavatouch.corefoundation.CoreURL;
import org.kavaproject.kavatouch.internal.Header;
import org.kavaproject.kavatouch.runtime.Factory;
import org.kavaproject.kavatouch.util.NotImplementedException;

import java.util.List;
import java.util.Map;

public class DefaultBundle implements Bundle {
    private final URLFactory mURLFactory;
    private final BundleFactory mBundleFactory;
    private CoreBundle mCoreBundle;

    protected DefaultBundle(CoreBundle coreBundle, BundleFactory bundleFactory, URLFactory urlFactory) {
        mBundleFactory = bundleFactory;
        mURLFactory = urlFactory;
        mCoreBundle = coreBundle;
    }

    @Override
    public Factory factoryNamed(String name) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public Factory principalFactory() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public URL urlForResource(String name, String extension, String subpath) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public URL urlForResource(String name, String extension) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public String pathForResourceOfType(String resourceName, String extension) {
        throw new NotImplementedException();
    }

    @Override
    public List<URL> urlsForResources(String extension, String subdirectory) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public String pathForResource(String resourceName, String extension, String directory) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public URL urlForResource(String name, String extension, String subdirectory, String localizationName) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public String pathForResource(String name, String typeExtension, String directory, String localizationName) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public List<String> pathsForResources(String extension, String directory) {
        throw new NotImplementedException();
    }

    @Override
    public List<URL> urlsForResources(String extension, String subdirectory, String localizationName) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public List<String> pathsForResources(String extension, String directory, String localizationName) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public String resourcePath() {
        CoreURL cfURL = mCoreBundle.copyResourcesDirectoryURL();
        return mURLFactory.create(cfURL).path();
    }

    @Override
    public URL bundleURL() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public String bundlePath() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public String bundleIdentifier() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public Map<String, Object> infoDictionary() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public Object objectForInfoDictionaryKey() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public URL builtInPlugInsURL() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public String builtInPlugInsPath() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public URL executableURL() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public String executablePath() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public URL URLForAuxiliaryExecutable(String executableName) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public String pathForAuxiliaryExecutable(String executableName) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public URL privateFrameworksURL() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public String privateFrameworksPath() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public URL sharedFrameworksURL() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public String sharedFrameworksPath() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public URL sharedSupportURL() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public String sharedSupportPath() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public URL resourceURL() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public String localizedStringForKeyValueTable(FoundationString key, String value, FoundationString tableName) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public List executableArchitectures() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public boolean preflight() throws RuntimeException {
        throw new NotImplementedException(); //TODO
    }

//    @OccInstanceMethod("load")
//    public boolean load() {
//        throw new NotImplementedException(); //TODO
//    }

    @Override
    public boolean load() throws RuntimeException {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public boolean isLoaded() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public boolean unload() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public List<String> localizations() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public String developmentLocalization() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public List<String> preferredLocalizations() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public Map<String, Object> localizedInfoDictionary() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    @Header("UINibLoading")
    public List loadNib(String filename, Object owner, UINibLoadingOptions options) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public BundleFactory getFactory() {
        return mBundleFactory;
    }

    @Override
    public CoreBundle toCoreType() {
        return mCoreBundle;
    }
}
