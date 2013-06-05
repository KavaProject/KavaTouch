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
import org.kavaproject.kavatouch.internal.*;
import org.kavaproject.kavatouch.runtime.Factory;
import org.kavaproject.kavatouch.util.OutArg;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Header("NSString")
@OccClass("NSStringAdapter")
public interface FoundationStringFactory extends Factory, CodingFactory, CoreBridgeFactory<CoreString> {
    @OccInstanceMethod("init")
    FoundationString create();

    @OccInstanceMethod("initWithBytes:length:encoding:")
    FoundationString createWithBytes(byte[] bytes, int length, StringEncoding encoding);

    @OccInstanceMethod("initWithBytesNoCopy:length:encoding:freeWhenDone:")
    FoundationString createWithBytes(byte[] bytes, StringEncoding encoding, boolean freeWhenDone);

    @OccInstanceMethod("initWithCharacters:length:")
    FoundationString createWithCharacters(char[] characters);

    @OccInstanceMethod("initWithCharactersNoCopy:length:freeWhenDone")
    FoundationString createWithCharacters(char[] characters, boolean freeWhenDone);

    @OccInstanceMethod("initWithCString:encoding:")
    FoundationString createWithCString(byte[] nullTerminatedCString, StringEncoding encoding);

    @OccInstanceMethod("initWithUTF8String:")
    FoundationString createWithUTF8String(byte[] bytes);

    @OccInstanceMethod("initWithFormat:")
    FoundationString createWithFormat(String format, Object... args);

    @OccInstanceMethod("initWithFormat:arguments:")
    FoundationString createWithFormat(String format, List args);

    @OccInstanceMethod("initWithFormat:locale:")
    FoundationString createWithFormatLocale(String format, Locale locale, Object... args);

    @OccInstanceMethod("initWithFormat:locale:")
    FoundationString createWithFormat(String format, Map locale, Object... args);

    @OccInstanceMethod("initWithFormat:locale:arguments:")
    FoundationString createWithFormatLocaleArguments(String format, Locale locale, List args);

    @OccInstanceMethod("initWithFormat:locale:arguments:")
    FoundationString createWithFormat(String format, Map locale, List args);

    @OccInstanceMethod("initWithData:encoding:")
    FoundationString createWithData(ByteBuffer data, StringEncoding encoding);

    @OccClassMethod("stringWithFormat:")
    FoundationString createString(String string, Object... args);

    @OccClassMethod("localizedStringWithFormat:")
    FoundationString localizedFrom(String format, Object... args);

    @OccInstanceMethod("initWithCString:length:")
    @Deprecated
    FoundationString createWithCString(byte[] cString);

    @OccInstanceMethod("initWithCStringNoCopy:length:freeWhenDone:")
    @Deprecated
    FoundationString createWithCString(byte[] cString, boolean freeWhenDone);

    @OccInstanceMethod("initWithContentsOfFile:encoding:error:")
    FoundationString createWithContentsOfFile(FoundationString path, StringEncoding encoding) throws RuntimeException;

    @OccInstanceMethod("initWithContentsOfFile:usedEncoding:error:")
    FoundationString createWithContentsOfFile(FoundationString path, OutArg<StringEncoding> usedEncoding) throws
            RuntimeException;

    @OccInstanceMethod("initWithContentsOfFile:")
    @Deprecated
    FoundationString createWithContentsOfFile(String path);

    @OccInstanceMethod("initWithContentsOfURL:encoding:error:")
    FoundationString createWithContentsOfURL(URL url, StringEncoding encoding) throws RuntimeException;

    @OccInstanceMethod("initWithContentsOfURL:usedEncoding:error:")
    FoundationString createWithContentsOfURL(URL url, OutArg<StringEncoding> usedEncoding) throws RuntimeException;

    @OccInstanceMethod("initWithContentsOfURL:")
    @Deprecated
    FoundationString createWithContentsOfURL(URL url);

    @OccClassMethod("availableStringEncodings")
    StringEncoding[] availableStringEncodings();

    @OccClassMethod("defaultCStringEncoding")
    StringEncoding defaultCStringEncoding();

    @OccClassMethod("localizedNameOfStringEncoding:")
    FoundationString localizedNameOfString(StringEncoding encoding);

    @OccClassMethod("pathWithComponents:")
    FoundationString pathWithComponents(List<String> components);

    @OccInstanceMethod("initWithString:")
    FoundationString create(String string);

    @Header("UIGeometry")
    @CFunction(value = "NSStringFromCGAffineTransform", tokenGroup = "UIKit")
    FoundationString parse(GraphicsAffineTransform transform);

    @Header("UIGeometry")
    @CFunction(value = "NSStringFromCGPoint", tokenGroup = "UIKit")
    FoundationString parse(GraphicsPoint point);

    @Header("UIGeometry")
    @CFunction(value = "NSStringFromCGRect", tokenGroup = "UIKit")
    FoundationString parse(GraphicsRect rect);

    @Header("UIGeometry")
    @CFunction(value = "NSStringFromCGSize", tokenGroup = "UIKit")
    FoundationString parse(GraphicsSize size);
}
