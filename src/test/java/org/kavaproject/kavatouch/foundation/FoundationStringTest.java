/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.foundation;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kavaproject.kavatouch.Injector;
import org.kavaproject.kavatouch.util.DeviceHandleMock;

import java.util.List;

public class FoundationStringTest {
    private FoundationStringFactory mFoundationStringFactory;

    @Before
    public void setUp() throws Exception {
        Injector.setInstance(new Injector(new DeviceHandleMock()));
        mFoundationStringFactory = Injector.getInstance().injectFoundationStringFactory();
    }

    @Test
    public void testPad() throws Exception {
        FoundationString abc = mFoundationStringFactory.create("abc");
        Assert.assertEquals("abc......", abc.pad(9, ".", 0).toString());
        Assert.assertEquals("ab", abc.pad(2, ".", 0).toString());
        Assert.assertEquals("abc . . .", abc.pad(9, ". ", 1).toString());
    }

    @Test
    public void testAppendPathComponent() throws Exception {
        FoundationString component = mFoundationStringFactory.create("scratch.tiff");
        Assert.assertEquals("/tmp/scratch.tiff", mFoundationStringFactory.create("/tmp").appendPathComponent
                (component).toString());
        Assert.assertEquals("/tmp/scratch.tiff", mFoundationStringFactory.create("/tmp/").appendPathComponent
                (component).toString());
        Assert.assertEquals("/scratch.tiff", mFoundationStringFactory.create("/").appendPathComponent(component)
                .toString());
        Assert.assertEquals("scratch.tiff", mFoundationStringFactory.create("").appendPathComponent(component)
                .toString());
    }

    @Test
    public void testAppendPathExtension() throws Exception {
        FoundationString tiff = mFoundationStringFactory.create("tiff");
        Assert.assertEquals("/tmp/scratch.old.tiff", mFoundationStringFactory.create("/tmp/scratch.old")
                .appendPathExtension(tiff).toString());
        Assert.assertEquals("/tmp/scratch..tiff", mFoundationStringFactory.create("/tmp/scratch.")
                .appendPathExtension(tiff).toString());
        Assert.assertEquals("/tmp.tiff", mFoundationStringFactory.create("/tmp/").appendPathExtension(tiff).toString());
        Assert.assertEquals("scratch.tiff", mFoundationStringFactory.create("scratch").appendPathExtension(tiff)
                .toString());
    }

    @Test
    public void testPathComponents() throws Exception {
        FoundationString stringAdapter = mFoundationStringFactory.create("tmp/scratch");
        List<FoundationString> components = stringAdapter.pathComponents();
        Assert.assertArrayEquals(new FoundationString[]{mFoundationStringFactory.create("tmp"),
                mFoundationStringFactory.create("scratch")}, components.toArray());

        stringAdapter = mFoundationStringFactory.create("/tmp/scratch");
        components = stringAdapter.pathComponents();
        Assert.assertArrayEquals(new FoundationString[]{mFoundationStringFactory.create("/"),
                mFoundationStringFactory.create("tmp"), mFoundationStringFactory.create("scratch")},
                components.toArray());

        stringAdapter = mFoundationStringFactory.create("tmp/scratch/");
        components = stringAdapter.pathComponents();
        Assert.assertArrayEquals(new FoundationString[]{mFoundationStringFactory.create("tmp"),
                mFoundationStringFactory.create("scratch"), mFoundationStringFactory.create("/")},
                components.toArray());

        stringAdapter = mFoundationStringFactory.create("scratch");
        components = stringAdapter.pathComponents();
        Assert.assertArrayEquals(new FoundationString[]{mFoundationStringFactory.create("scratch")},
                components.toArray());
    }

    @Test
    public void testDeletePathExtension() throws Exception {
        Assert.assertEquals("/tmp/scratch", mFoundationStringFactory.create("/tmp/scratch.tiff").deletePathExtension
                ().toString());
        Assert.assertEquals("/tmp", mFoundationStringFactory.create("/tmp/").deletePathExtension().toString());
        Assert.assertEquals("scratch", mFoundationStringFactory.create("scratch.bundle/").deletePathExtension()
                .toString());
        Assert.assertEquals("scratch.", mFoundationStringFactory.create("scratch..tiff").deletePathExtension()
                .toString());
        Assert.assertEquals(".tiff", mFoundationStringFactory.create(".tiff").deletePathExtension().toString());
        Assert.assertEquals("/", mFoundationStringFactory.create("/").deletePathExtension().toString());
    }

    @Test
    public void testPathExtension() throws Exception {
        Assert.assertEquals("tiff", mFoundationStringFactory.create("/tmp/scratch.tiff").pathExtension().toString());
        Assert.assertEquals("tiff", mFoundationStringFactory.create(".scratch.tiff").pathExtension().toString());
        Assert.assertEquals("", mFoundationStringFactory.create("/tmp/scratch").pathExtension().toString());
        Assert.assertEquals("", mFoundationStringFactory.create("/tmp/").pathExtension().toString());
        Assert.assertEquals("tiff", mFoundationStringFactory.create("/tmp/scratch..tiff").pathExtension().toString());
    }
}
