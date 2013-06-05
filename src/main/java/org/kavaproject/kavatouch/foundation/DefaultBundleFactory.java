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
import org.kavaproject.kavatouch.runtime.Factory;
import org.kavaproject.kavatouch.util.NotImplementedException;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultBundleFactory implements BundleFactory {
    private final URLFactory mURLFactory;
    private Map<String, Bundle> mInstances = new HashMap<String, Bundle>();

    @Inject
    protected DefaultBundleFactory(URLFactory urlFactory) {
        mURLFactory = urlFactory;
    }

    @Override
    public Bundle create(String fullPath) {
        URL url = mURLFactory.createWithPath(fullPath);
        return create(url);
    }

    @Override
    public Bundle create(URL url) {
        Bundle bundle = mInstances.get(url.path());
        if (bundle == null) {
            bundle = new DefaultBundle(CoreBundle.create(url.toCoreType()), this, mURLFactory);
            mInstances.put(url.path(), bundle);
        }
        return bundle;
    }

    @Override
    public Bundle create(Factory factory) {
        throw new NotImplementedException();
    }

    @Override
    public Bundle createBundleForIdentifier(String identifier) {
        CoreBundle cfBundle = CoreBundle.getBundle(identifier);
        if (cfBundle == null) {
            return null;
        }
        CoreURL coreURL = cfBundle.copyBundleURL();
        return create(mURLFactory.create(coreURL));
    }

    @Override
    public Bundle mainBundle() {
        CoreBundle coreBundle = CoreBundle.getMainBundle();
        if (coreBundle == null) {
            return null;
        }
        CoreURL coreURL = coreBundle.copyBundleURL();
        return create(mURLFactory.create(coreURL));
    }

    @Override
    public List<Bundle> allBundles() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public List<Bundle> allFrameworks() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public String pathForResource(String name, String extension, String bundleDirectory) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public List<String> pathsForResources(String extension, String bundleDirectory) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public URL urlForResource(String name, String extension, String subdirectory, URL bundleURL) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public List<URL> urlsForResources(String extension, String subdirectory, URL bundleURL) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public List<String> preferredLocalizations(List<String> localizationsArray) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public List<String> preferredLocalizations(List<String> localizationsArray, List<String> preferencesArray) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public Bundle create(CoreBundle coreBundle) {
        return new DefaultBundle(coreBundle, this, mURLFactory);
    }
}
