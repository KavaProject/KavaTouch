/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.foundation;

import org.kavaproject.kavatouch.corefoundation.CoreString;
import org.kavaproject.kavatouch.coregraphics.GraphicsAffineTransform;
import org.kavaproject.kavatouch.coregraphics.GraphicsPoint;
import org.kavaproject.kavatouch.coregraphics.GraphicsRect;
import org.kavaproject.kavatouch.coregraphics.GraphicsSize;
import org.kavaproject.kavatouch.foundation.staging.StringEncoding;
import org.kavaproject.kavatouch.util.NotImplementedException;
import org.kavaproject.kavatouch.util.OutArg;

import javax.inject.Inject;
import java.io.File;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class DefaultFoundationStringFactory implements FoundationStringFactory {
    @Inject
    protected DefaultFoundationStringFactory() {
    }

    @Override
    public FoundationString create() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public FoundationString createWithBytes(byte[] bytes, int length, StringEncoding encoding) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public FoundationString createWithBytes(byte[] bytes, StringEncoding encoding, boolean freeWhenDone) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public FoundationString createWithCharacters(char[] characters) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public FoundationString createWithCharacters(char[] characters, boolean freeWhenDone) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public FoundationString createWithCString(byte[] nullTerminatedCString, StringEncoding encoding) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public FoundationString createWithUTF8String(byte[] bytes) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public FoundationString createWithFormat(String format, Object... args) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public FoundationString createWithFormat(String format, List args) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public FoundationString createWithFormatLocale(String format, Locale locale, Object... args) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public FoundationString createWithFormat(String format, Map locale, Object... args) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public FoundationString createWithFormatLocaleArguments(String format, Locale locale, List args) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public FoundationString createWithFormat(String format, Map locale, List args) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public FoundationString createWithData(ByteBuffer data, StringEncoding encoding) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public FoundationString createString(String string, Object... args) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public FoundationString localizedFrom(String format, Object... args) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public FoundationString createWithCString(byte[] cString) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public FoundationString createWithCString(byte[] cString, boolean freeWhenDone) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public FoundationString createWithContentsOfFile(FoundationString path, StringEncoding encoding) throws
            RuntimeException {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public FoundationString createWithContentsOfFile(FoundationString path, OutArg<StringEncoding> usedEncoding)
            throws RuntimeException {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public FoundationString createWithContentsOfFile(String path) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public FoundationString createWithContentsOfURL(URL url, StringEncoding encoding) throws RuntimeException {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public FoundationString createWithContentsOfURL(URL url, OutArg<StringEncoding> usedEncoding) throws
            RuntimeException {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public FoundationString createWithContentsOfURL(URL url) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public StringEncoding[] availableStringEncodings() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public StringEncoding defaultCStringEncoding() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public FoundationString localizedNameOfString(StringEncoding encoding) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public FoundationString pathWithComponents(List<String> components) {
        StringBuilder sb = new StringBuilder();
        sb.append(components.get(0));
        if (components.size() == 1) {
            return create(sb.toString());
        }
        for (int i = 1; i < components.size() - 1; i++) {
            sb.append(components.get(i)).append(File.separator);
        }
        sb.append(components.get(components.size() - 1));
        return create(sb.toString());
    }

    @Override
    public FoundationString create(String string) {
        if (string == null) {
            throw new IllegalArgumentException();
        }
        return new DefaultFoundationString(string, this);
    }

    @Override
    public FoundationString parse(GraphicsAffineTransform transform) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public FoundationString parse(GraphicsPoint point) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public FoundationString parse(GraphicsRect rect) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public FoundationString parse(GraphicsSize size) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public FoundationString create(CoreString coreObject) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public FoundationString create(Coder decoder) {
        return new DefaultFoundationString(decoder, this);
    }
}
