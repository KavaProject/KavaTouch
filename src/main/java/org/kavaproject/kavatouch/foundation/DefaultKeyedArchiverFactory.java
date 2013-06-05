/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.foundation;

import org.apache.http.util.ByteArrayBuffer;
import org.kavaproject.kavatouch.runtime.Factory;
import org.kavaproject.kavatouch.util.NotImplementedException;

import javax.inject.Inject;
import java.nio.ByteBuffer;

public class DefaultKeyedArchiverFactory extends CoderBaseFactory implements KeyedArchiverFactory {
    @Inject
    protected DefaultKeyedArchiverFactory() {
    }

    @Override
    public KeyedArchiver createForWriting(ByteArrayBuffer data) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public ByteBuffer archivedData(Object rootObject) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public boolean archiveToFile(Object rootObject, String path) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void setFactory(String codedName, Factory factory) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public String getFactoryName(Factory factory) {
        throw new NotImplementedException(); //TODO
    }
}
