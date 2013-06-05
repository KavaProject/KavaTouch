/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.foundation;

import org.kavaproject.kavatouch.corefoundation.CorePropertyList;
import org.kavaproject.kavatouch.corefoundation.CoreString;
import org.kavaproject.kavatouch.coregraphics.GraphicsAffineTransform;
import org.kavaproject.kavatouch.coregraphics.GraphicsPoint;
import org.kavaproject.kavatouch.coregraphics.GraphicsRect;
import org.kavaproject.kavatouch.coregraphics.GraphicsSize;
import org.kavaproject.kavatouch.foundation.staging.*;
import org.kavaproject.kavatouch.runtime.Runnable2;
import org.kavaproject.kavatouch.runtime.Runnable4;
import org.kavaproject.kavatouch.uikit.LineBreakMode;
import org.kavaproject.kavatouch.uikit.UIBaselineAdjustment;
import org.kavaproject.kavatouch.uikit.UIFont;
import org.kavaproject.kavatouch.util.NotImplementedException;
import org.kavaproject.kavatouch.util.OutArg;

import java.io.File;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

public class DefaultFoundationString implements FoundationString {
    private final FoundationStringFactory mFoundationStringFactory;
    private String mAdaptee;

    protected DefaultFoundationString(String adaptee, FoundationStringFactory foundationStringFactory) {
        mFoundationStringFactory = foundationStringFactory;
        mAdaptee = adaptee;
    }

    public DefaultFoundationString(Coder decoder, DefaultFoundationStringFactory foundationStringFactory) {
        mFoundationStringFactory = foundationStringFactory;
    }

    @Override
    public boolean writeToFile(String path, boolean atomically, StringEncoding encoding) throws RuntimeException {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public boolean writeToURL(URL url, boolean atomically, StringEncoding encoding) throws RuntimeException {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public boolean writeToFile(String path, boolean atomically) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public boolean writeToURL(URL url, boolean atomically) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public int lengthOfBytes(StringEncoding encoding) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public int maximumLengthOfBytes(StringEncoding encoding) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public char at(int index) {
        return mAdaptee.charAt(index);
    }

    @Override
    public void getCharacters(OutArg<char[]> buffer, Range range) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public boolean getBytes(OutArg<byte[]> buffer, int maxLength, OutArg<Integer> usedLength,
                            StringEncoding encoding, StringEncodingConversionOptions options, Range range,
                            RangePointer remainingRange) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void getCharacters(OutArg<char[]> buffer) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public byte[] toCString(StringEncoding encoding) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public boolean getCString(OutArg<byte[]> buffer, int maxLength, StringEncoding encoding) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public byte[] toUTF8String() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public byte[] toCString() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public int cStringLength() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void getCString(OutArg<byte[]> buffer) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void getCString(OutArg<byte[]> buffer, int maxLength) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void getCString(OutArg<byte[]> buffer, int maxLength, Range range, Range remainingRange) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public byte[] lossyCString() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public FoundationString appendFormat(String format, Object... args) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public FoundationString stringByAppendingString(FoundationString instance) {
        return append(instance.toString());
    }

    @Override
    public FoundationString append(String s) {
        return getFactory().create(mAdaptee + s);
    }

    @Override
    public List<FoundationString> components(String separator) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public List<FoundationString> components(CharacterSet separators) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public FoundationString trim(CharacterSet characterSet) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public FoundationString substringFromIndex(int index) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public FoundationString substringWithRange(Range range) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public FoundationString substringToIndex(int index) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public Range range(CharacterSet characterSet) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public Range range(CharacterSet characterSet, StringCompareOptions options) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public Range range(CharacterSet characterSet, StringCompareOptions options, Range range) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public Range range(String string) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public Range range(String string, StringCompareOptions options) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public Range range(String string, StringCompareOptions options, Range range) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public Range range(String string, StringCompareOptions options, Range range, Locale locale) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void enumerateLines(Runnable2<String, Boolean> block) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void enumerateSubstrings(Range range, StringEnumerationOptions options, Runnable4<String, Range, Range,
            Boolean> block) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public FoundationString replace(String old, String replacement) {
        return getFactory().create(mAdaptee.replaceAll(Pattern.quote(old), replacement));
    }

    @Override
    public FoundationString replace(String target, String replacement, StringCompareOptions options,
                                    Range searchRange) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public FoundationString replace(Range range, String replacement) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void getLine(OutArg<Integer> startIndex, OutArg<Integer> endIndex, OutArg<Integer> contentsEndIndex,
                        Range range) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public Range lineRange(Range range) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void getParagraph(OutArg<Integer> startIndex, OutArg<Integer> endIndex, OutArg<Integer> contentsEndIndex,
                             Range range) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public Range paragraphRange(Range range) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public Range rangeOfComposedCharacterSequence(int index) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public Range rangeOfComposedCharacterSequences(Range range) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public CorePropertyList propertyList() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public Map<String, String> propertyListFromStringsFileFormat() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public int caseInsensitiveCompare(String other) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public int localizedCaseInsensitiveCompare(String other) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public int compareTo(FoundationString other) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public int localizedCompare(String other) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public int compareTo(String other, StringCompareOptions options) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public int compareTo(String other, StringCompareOptions options, Range range) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public int compareTo(String other, StringCompareOptions options, Range range, Locale locale) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public int localizedStandardCompare(String other) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public boolean hasPrefix(String string) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public boolean hasSuffix(String string) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public FoundationString fold(StringCompareOptions options, Locale locale) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public FoundationString commonPrefix(String string, StringCompareOptions options) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public FoundationString capitalize() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public FoundationString capitalize(Locale locale) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public FoundationString toLower() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public FoundationString toLower(Locale locale) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public FoundationString toUpper() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public FoundationString toUpper(Locale locale) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public FoundationString decomposedStringWithCanonicalMapping() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public FoundationString decomposedStringWithCompatibilityMapping() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public FoundationString precomposedStringWithCanonicalMapping() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public FoundationString precomposedStringWithCompatibilityMapping() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public double doubleValue() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public float floatValue() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public int intValue() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public Integer integerValue() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public long longLongValue() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public boolean boolValue() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public boolean canBeConvertedToEncoding(StringEncoding encoding) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public ByteBuffer dataUsingEncoding(StringEncoding encoding) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public ByteBuffer dataUsingEncoding(StringEncoding encoding, boolean allowLossyConversion) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public StringEncoding fastestEncoding() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public StringEncoding smallestEncoding() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public int completePath(OutArg<String> outputName, boolean caseSensitive, List<String> outputArray,
                            List<FoundationString> filterTypes) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public FoundationString fileSystemRepresentation() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public boolean getFileSystemRepresentation(char[] buffer, int maxLength) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public boolean isAbsolutePath() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public FoundationString lastPathComponent() {
        List<FoundationString> components = pathComponents();
        return components.get(components.size() - 1);
    }

    @Override
    public List<FoundationString> pathComponents() {
        String[] splitted = mAdaptee.split(Pattern.quote(File.separator));
        ArrayList<FoundationString> components = new ArrayList<FoundationString>(splitted.length);
        for (String s : splitted) {
            components.add(s.equals("") ? getFactory().create(File.separator) : getFactory().create(s));
        }
        if (mAdaptee.endsWith(File.separator)) {
            components.add(getFactory().create(File.separator));
        }
        return components;
    }

    @Override
    public FoundationString abbreviateWithTildeInPath() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public FoundationString appendPathComponent(FoundationString name) {
        return appendPathComponent(name.toString());
    }

    @Override
    public FoundationString appendPathComponent(String pathComponent) {
        if (mAdaptee.isEmpty()) {
            return getFactory().create(pathComponent);
        }
        if (!mAdaptee.endsWith(File.separator)) {
            return getFactory().create(mAdaptee + File.separator + pathComponent);
        } else {
            return getFactory().create(mAdaptee + pathComponent);
        }
    }

    @Override
    public FoundationString appendPathExtension(FoundationString extension) {
        return appendPathExtension(extension.toString());
    }

    @Override
    public FoundationString appendPathExtension(String extension) {
        if (mAdaptee.endsWith(File.separator)) {
            return getFactory().create(mAdaptee.substring(0, mAdaptee.length() - 1) + "." + extension);
        } else {
            return getFactory().create(mAdaptee + "." + extension);
        }
    }

    @Override
    public FoundationString deleteLastPathComponent() {
        List<FoundationString> components = pathComponents();
        ArrayList<String> res = new ArrayList<String>(components.size());
        for (FoundationString component : components) {
            res.add(component.toString());
        }
        res.remove(res.size() - 1);
        return mFoundationStringFactory.pathWithComponents(res);
    }

    @Override
    public FoundationString deletePathExtension() {
        FoundationString paddedString = this;
        if (length() == 1) {
            return this;
        }
        if (mAdaptee.endsWith(File.separator)) {
            paddedString = getFactory().create(mAdaptee.substring(0, mAdaptee.length() - File.separator.length()));
        }
        FoundationString extension = paddedString.pathExtension();
        if (extension.length() > 0 && extension.length() + 1 < paddedString.length()) {
            return paddedString.pad(paddedString.length() - extension.length() - 1, null, 0);
        } else {
            return paddedString;
        }
    }

    @Override
    public FoundationString pathExtension() {
        List<FoundationString> components = pathComponents();
        String[] fileComponents = components.get(components.size() - 1).toString().split(Pattern.quote("."));
        int length = fileComponents.length;
        return getFactory().create(length > 1 ? fileComponents[length - 1] : "");
    }

    @Override
    public FoundationString pad(int newLength, String padString, int startIndex) {
        if (newLength < mAdaptee.length()) {
            return getFactory().create(mAdaptee.substring(0, newLength));
        }
        StringBuilder sb = new StringBuilder(mAdaptee);
        String shuffledPadding = padString.substring(startIndex) + padString.substring(0, startIndex);
        for (int i = mAdaptee.length(); i < newLength; i += shuffledPadding.length()) {
            int remaining = newLength - i;
            if (remaining > shuffledPadding.length()) {
                sb.append(shuffledPadding);
            } else {
                sb.append(shuffledPadding.substring(0, remaining));
            }
        }
        return getFactory().create(sb.toString());
    }

    @Override
    public int length() {
        return mAdaptee.length();
    }

    @Override
    public FoundationString expandTildeInPath() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public FoundationString resolveSymlinksInPath() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public FoundationString standardizePath() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public List<FoundationString> appendPaths(List<String> paths) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public FoundationString addPercentEscapes(StringEncoding encoding) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public FoundationString replacePercentEscapes(StringEncoding encoding) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void enumerateLinguisticTags(Range range, String tagScheme, LinguisticTaggerOptions options,
                                        Orthography orthography, Runnable4<String, Range, Range, Boolean> block) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public List<LinguisticTag> linguisticTags(Range range, String tagScheme, LinguisticTaggerOptions options,
                                              Orthography orthography, List<Range> tokenRanges) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public GraphicsSize stringSize(UIFont font) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public GraphicsSize stringSize(UIFont font, float width, LineBreakMode lineBreakMode) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public GraphicsSize stringSize(UIFont font, float minFontSize, float actualFontSize, float width,
                                   UILineBreakMode lineBreakMode) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public GraphicsSize stringSize(UIFont font, GraphicsSize size) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public GraphicsSize stringSize(UIFont font, GraphicsSize size, UILineBreakMode lineBreakMode) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public GraphicsSize drawString(GraphicsPoint point, UIFont font) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public GraphicsSize drawString(GraphicsPoint point, float width, UIFont font, UILineBreakMode lineBreakMode) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public GraphicsSize drawString(GraphicsPoint point, float width, UIFont font, float fontSize,
                                   UILineBreakMode lineBreakMode, UIBaselineAdjustment baselineAdjustment) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public GraphicsSize drawString(GraphicsPoint point, float width, UIFont font, float minFontSize,
                                   float actualFontSize, UILineBreakMode lineBreakMode,
                                   UIBaselineAdjustment baselineAdjustment) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public GraphicsSize drawString(GraphicsRect rect, UIFont font) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public GraphicsSize drawString(GraphicsRect rect, UIFont font, UILineBreakMode lineBreakMode) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public GraphicsSize drawString(GraphicsRect rect, UIFont font, UILineBreakMode lineBreakMode,
                                   UITextAlignment alignment) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public GraphicsAffineTransform toCGAffineTransform() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public GraphicsPoint toCGPoint() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public GraphicsRect toCGRect() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public GraphicsSize toCGSize() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public FoundationStringFactory getFactory() {
        return mFoundationStringFactory;
    }

    @Override
    public boolean equals(Object other) {
        return other != null && other instanceof DefaultFoundationString && mAdaptee.equals(other.toString());
    }

    @Override
    public int hashCode() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public String toString() {
        return mAdaptee;
    }

    @Override
    public void encode(Coder encoder) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public FoundationString copy() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public FoundationString mutableCopy() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public CoreString toCoreType() {
        throw new NotImplementedException(); //TODO
    }
}
