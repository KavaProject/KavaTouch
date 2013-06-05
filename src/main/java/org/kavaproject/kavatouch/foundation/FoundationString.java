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
import org.kavaproject.kavatouch.internal.CFunction;
import org.kavaproject.kavatouch.internal.Header;
import org.kavaproject.kavatouch.internal.OccClass;
import org.kavaproject.kavatouch.internal.OccInstanceMethod;
import org.kavaproject.kavatouch.runtime.Creatable;
import org.kavaproject.kavatouch.runtime.Runnable2;
import org.kavaproject.kavatouch.runtime.Runnable4;
import org.kavaproject.kavatouch.uikit.LineBreakMode;
import org.kavaproject.kavatouch.uikit.UIBaselineAdjustment;
import org.kavaproject.kavatouch.uikit.UIFont;
import org.kavaproject.kavatouch.util.OutArg;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Header("NSString")
@OccClass("NSString")
public interface FoundationString extends Coding, Copying, MutableCopying, Comparable<FoundationString>, Creatable,
        CoreBridge<CoreString> {
    @OccInstanceMethod("writeToFile:atomically:encoding:error:")
    boolean writeToFile(String path, boolean atomically, StringEncoding encoding) throws RuntimeException;

    @OccInstanceMethod("writeToURL:atomically:encoding:error:")
    boolean writeToURL(URL url, boolean atomically, StringEncoding encoding) throws RuntimeException;

    @OccInstanceMethod("writeToFile:atomically:")
    @Deprecated
    boolean writeToFile(String path, boolean atomically);

    @OccInstanceMethod("writeToURL:atomically:")
    @Deprecated
    boolean writeToURL(URL url, boolean atomically);

    @OccInstanceMethod("lengthOfBytesUsingEncoding:")
    int lengthOfBytes(StringEncoding encoding);

    @OccInstanceMethod("maximumLengthOfBytesUsingEncoding:")
    int maximumLengthOfBytes(StringEncoding encoding);

    @OccInstanceMethod("characterAtIndex:")
    char at(int index);

    @OccInstanceMethod("getCharacters:range:")
    void getCharacters(OutArg<char[]> buffer, Range range);

    @OccInstanceMethod("getBytes:maxLength:usedLength:encoding:options:range:remainingRange:")
    boolean getBytes(OutArg<byte[]> buffer, int maxLength, OutArg<Integer> usedLength, StringEncoding encoding,
                     StringEncodingConversionOptions options, Range range, RangePointer remainingRange);

    @OccInstanceMethod("getCharacters:")
    @Deprecated
    void getCharacters(OutArg<char[]> buffer);

    @OccInstanceMethod("cStringUsingEncoding:")
    byte[] toCString(StringEncoding encoding);

    @OccInstanceMethod("getCString:maxLength:encoding:")
    boolean getCString(OutArg<byte[]> buffer, int maxLength, StringEncoding encoding);

    @OccInstanceMethod("UTF8String")
    byte[] toUTF8String();

    @OccInstanceMethod("cString")
    @Deprecated
    byte[] toCString();

    @OccInstanceMethod("cStringLength")
    @Deprecated
    int cStringLength();

    @OccInstanceMethod("getCString:")
    @Deprecated
    void getCString(OutArg<byte[]> buffer);

    @OccInstanceMethod("getCString:maxLength:")
    @Deprecated
    void getCString(OutArg<byte[]> buffer, int maxLength);

    @OccInstanceMethod("getCString:maxLength:range:remainingRange:")
    @Deprecated
    void getCString(OutArg<byte[]> buffer, int maxLength, Range range, Range remainingRange);

    @OccInstanceMethod("lossyCString")
    @Deprecated
    byte[] lossyCString();

    @OccInstanceMethod("stringByAppendingFormat:")
    FoundationString appendFormat(String format, Object... args);

    FoundationString stringByAppendingString(FoundationString instance);

    @OccInstanceMethod("stringByAppendingString:")
    FoundationString append(String s);

    @OccInstanceMethod("componentsSeparatedByString:")
    List<FoundationString> components(String separator);

    @OccInstanceMethod("componentsSeparatedByCharactersInSet:")
    List<FoundationString> components(CharacterSet separators);

    @OccInstanceMethod("stringByTrimmingCharactersInSet:")
    FoundationString trim(CharacterSet characterSet);

    @OccInstanceMethod("substringFromIndex:")
    FoundationString substringFromIndex(int index);

    @OccInstanceMethod("substringWithRange:")
    FoundationString substringWithRange(Range range);

    @OccInstanceMethod("substringToIndex:")
    FoundationString substringToIndex(int index);

    @OccInstanceMethod("rangeOfCharacterFromSet:")
    Range range(CharacterSet characterSet);

    @OccInstanceMethod("rangeOfCharacterFromSet:options:")
    Range range(CharacterSet characterSet, StringCompareOptions options);

    @OccInstanceMethod("rangeOfCharacterFromSet:options:range:")
    Range range(CharacterSet characterSet, StringCompareOptions options, Range range);

    @OccInstanceMethod("rangeOfString:")
    Range range(String string);

    @OccInstanceMethod("rangeOfString:options:")
    Range range(String string, StringCompareOptions options);

    @OccInstanceMethod("rangeOfString:options:range:")
    Range range(String string, StringCompareOptions options, Range range);

    @OccInstanceMethod("rangeOfString:options:range:locale:")
    Range range(String string, StringCompareOptions options, Range range, Locale locale);

    @OccInstanceMethod("enumerateLinesUsingBlock:")
    void enumerateLines(Runnable2<String, Boolean> block);

    @OccInstanceMethod("enumerateSubstringsInRange:options:usingBlock:")
    void enumerateSubstrings(Range range, StringEnumerationOptions options, Runnable4<String, Range, Range,
            Boolean> block);

    @OccInstanceMethod("stringByReplacingOccurrencesOfString:withString:")
    FoundationString replace(String old, String replacement);

    @OccInstanceMethod("stringByReplacingOccurrencesOfString:withString:options:range:")
    FoundationString replace(String target, String replacement, StringCompareOptions options, Range searchRange);

    @OccInstanceMethod("stringByReplacingCharactersInRange:withString:")
    FoundationString replace(Range range, String replacement);

    @OccInstanceMethod("getLineStart:end:contentsEnd:forRange:")
    void getLine(OutArg<Integer> startIndex, OutArg<Integer> endIndex, OutArg<Integer> contentsEndIndex, Range range);

    @OccInstanceMethod("lineRangeForRange:")
    Range lineRange(Range range);

    @OccInstanceMethod("getParagraphStart:end:contentsEnd:forRange:")
    void getParagraph(OutArg<Integer> startIndex, OutArg<Integer> endIndex, OutArg<Integer> contentsEndIndex,
                      Range range);

    @OccInstanceMethod("paragraphRangeForRange:")
    Range paragraphRange(Range range);

    @OccInstanceMethod("rangeOfComposedCharacterSequenceAtIndex:")
    Range rangeOfComposedCharacterSequence(int index);

    @OccInstanceMethod("rangeOfComposedCharacterSequencesForRange:")
    Range rangeOfComposedCharacterSequences(Range range);

    @OccInstanceMethod("propertyList")
    CorePropertyList propertyList();

    @OccInstanceMethod("propertyListFromStringsFileFormat")
    Map<String, String> propertyListFromStringsFileFormat();

    @OccInstanceMethod("caseInsensitiveCompare:")
    int caseInsensitiveCompare(String other);

    @OccInstanceMethod("localizedCaseInsensitiveCompare:")
    int localizedCaseInsensitiveCompare(String other);

    @OccInstanceMethod("compare:")
    @Override
    int compareTo(FoundationString other);

    @OccInstanceMethod("localizedCompare:")
    int localizedCompare(String other);

    @OccInstanceMethod("compare:options:")
    int compareTo(String other, StringCompareOptions options);

    @OccInstanceMethod("compare:options:range:")
    int compareTo(String other, StringCompareOptions options, Range range);

    @OccInstanceMethod("compare:options:range:locale:")
    int compareTo(String other, StringCompareOptions options, Range range, Locale locale);

    @OccInstanceMethod("localizedStandardCompare:")
    int localizedStandardCompare(String other);

    @OccInstanceMethod("hasPrefix:")
    boolean hasPrefix(String string);

    @OccInstanceMethod("hasSuffix:")
    boolean hasSuffix(String string);

    @OccInstanceMethod("stringByFoldingWithOptions:locale:")
    FoundationString fold(StringCompareOptions options, Locale locale);

    @OccInstanceMethod("commonPrefixWithString:options:")
    FoundationString commonPrefix(String string, StringCompareOptions options);

    @OccInstanceMethod("capitalizedString")
    FoundationString capitalize();

    @OccInstanceMethod("capitalizedStringWithLocale:")
    FoundationString capitalize(Locale locale);

    @OccInstanceMethod("lowercaseString")
    FoundationString toLower();

    @OccInstanceMethod("lowercaseStringWithLocale:")
    FoundationString toLower(Locale locale);

    @OccInstanceMethod("uppercaseString")
    FoundationString toUpper();

    @OccInstanceMethod("uppercaseStringWithLocale:")
    FoundationString toUpper(Locale locale);

    @OccInstanceMethod("decomposedStringWithCanonicalMapping")
    FoundationString decomposedStringWithCanonicalMapping();

    @OccInstanceMethod("decomposedStringWithCompatibilityMapping")
    FoundationString decomposedStringWithCompatibilityMapping();

    @OccInstanceMethod("precomposedStringWithCanonicalMapping")
    FoundationString precomposedStringWithCanonicalMapping();

    @OccInstanceMethod("precomposedStringWithCompatibilityMapping")
    FoundationString precomposedStringWithCompatibilityMapping();

    @OccInstanceMethod("doubleValue")
    double doubleValue();

    @OccInstanceMethod("floatValue")
    float floatValue();

    @OccInstanceMethod("intValue")
    int intValue();

    @OccInstanceMethod("integerValue")
    Integer integerValue();

    @OccInstanceMethod("longLongValue")
    long longLongValue();

    @OccInstanceMethod("boolValue")
    boolean boolValue();

    @OccInstanceMethod("canBeConvertedToEncoding:")
    boolean canBeConvertedToEncoding(StringEncoding encoding);

    @OccInstanceMethod("dataUsingEncoding:")
    ByteBuffer dataUsingEncoding(StringEncoding encoding);

    @OccInstanceMethod("dataUsingEncoding:allowLossyConversion:")
    ByteBuffer dataUsingEncoding(StringEncoding encoding, boolean allowLossyConversion);

    @OccInstanceMethod("fastestEncoding")
    StringEncoding fastestEncoding();

    @OccInstanceMethod("smallestEncoding")
    StringEncoding smallestEncoding();

    @OccInstanceMethod("completePathIntoString:caseSensitive:matchesIntoArray:filterTypes:")
    int completePath(OutArg<String> outputName, boolean caseSensitive, List<String> outputArray,
                     List<FoundationString> filterTypes);

    @OccInstanceMethod("fileSystemRepresentation")
    FoundationString fileSystemRepresentation();

    @OccInstanceMethod("getFileSystemRepresentation:maxLength:")
    boolean getFileSystemRepresentation(char[] buffer, int maxLength);

    @OccInstanceMethod("isAbsolutePath")
    boolean isAbsolutePath();

    @OccInstanceMethod("lastPathComponent")
    FoundationString lastPathComponent();

    @OccInstanceMethod("pathComponents")
    List<FoundationString> pathComponents();

    @OccInstanceMethod("stringByAbbreviatingWithTildeInPath")
    FoundationString abbreviateWithTildeInPath();

    FoundationString appendPathComponent(FoundationString name);

    @OccInstanceMethod("stringByAppendingPathComponent:")
    FoundationString appendPathComponent(String pathComponent);

    FoundationString appendPathExtension(FoundationString extension);

    @OccInstanceMethod("stringByAppendingPathExtension:")
    FoundationString appendPathExtension(String extension);

    @OccInstanceMethod("stringByDeletingLastPathComponent")
    FoundationString deleteLastPathComponent();

    @OccInstanceMethod("stringByDeletingPathExtension")
    FoundationString deletePathExtension();

    @OccInstanceMethod("pathExtension")
    FoundationString pathExtension();

    @OccInstanceMethod("stringByPaddingToLength:withString:startingAtIndex:")
    FoundationString pad(int newLength, String padString, int startIndex);

    @OccInstanceMethod("length")
    int length();

    @OccInstanceMethod("stringByExpandingTildeInPath")
    FoundationString expandTildeInPath();

    @OccInstanceMethod("stringByResolvingSymlinksInPath")
    FoundationString resolveSymlinksInPath();

    @OccInstanceMethod("stringByStandardizingPath")
    FoundationString standardizePath();

    @OccInstanceMethod("stringsByAppendingPaths:")
    List<FoundationString> appendPaths(List<String> paths);

    @OccInstanceMethod("stringByAddingPercentEscapesUsingEncoding:")
    FoundationString addPercentEscapes(StringEncoding encoding);

    @OccInstanceMethod("stringByReplacingPercentEscapesUsingEncoding:")
    FoundationString replacePercentEscapes(StringEncoding encoding);

    @OccInstanceMethod("enumerateLinguisticTagsInRange:scheme:options:orthography:usingBlock:")
    void enumerateLinguisticTags(Range range, String tagScheme, LinguisticTaggerOptions options,
                                 Orthography orthography, Runnable4<String, Range, Range, Boolean> block);

    @OccInstanceMethod("linguisticTagsInRange:scheme:options:orthography:tokenRanges:")
    List<LinguisticTag> linguisticTags(Range range, String tagScheme, LinguisticTaggerOptions options,
                                       Orthography orthography, List<Range> tokenRanges);

    @Header("UIStringDrawing")
    @OccInstanceMethod(value = "sizeWithFont:", tokenGroup = "NSString_UIKit_Additions")
    GraphicsSize stringSize(UIFont font);

    @Header("UIStringDrawing")
    @OccInstanceMethod(value = "sizeWithFont:forWidth:lineBreakMode:", tokenGroup = "NSString_UIKit_Additions")
    GraphicsSize stringSize(UIFont font, float width, LineBreakMode lineBreakMode);

    @Header("UIStringDrawing")
    @OccInstanceMethod(value = "sizeWithFont:minFontSize:actualFontSize:forWidth:lineBreakMode:",
            tokenGroup = "NSString_UIKit_Additions")
    GraphicsSize stringSize(UIFont font, float minFontSize, float actualFontSize, float width,
                            UILineBreakMode lineBreakMode);

    @Header("UIStringDrawing")
    @OccInstanceMethod(value = "sizeWithFont:constrainedToSize:", tokenGroup = "NSString_UIKit_Additions")
    GraphicsSize stringSize(UIFont font, GraphicsSize size);

    @Header("UIStringDrawing")
    @OccInstanceMethod(value = "sizeWithFont:constrainedToSize:lineBreakMode:", tokenGroup = "NSString_UIKit_Additions")
    GraphicsSize stringSize(UIFont font, GraphicsSize size, UILineBreakMode lineBreakMode);

    @Header("UIStringDrawing")
    @OccInstanceMethod(value = "drawAtPoint:withFont:", tokenGroup = "NSString_UIKit_Additions")
    GraphicsSize drawString(GraphicsPoint point, UIFont font);

    @Header("UIStringDrawing")
    @OccInstanceMethod(value = "drawAtPoint:forWidth:withFont:lineBreakMode:", tokenGroup = "NSString_UIKit_Additions")
    GraphicsSize drawString(GraphicsPoint point, float width, UIFont font, UILineBreakMode lineBreakMode);

    @Header("UIStringDrawing")
    @OccInstanceMethod(value = "drawAtPoint:forWidth:withFont:fontSize:lineBreakMode:baselineAdjustment:",
            tokenGroup = "NSString_UIKit_Additions")
    GraphicsSize drawString(GraphicsPoint point, float width, UIFont font, float fontSize,
                            UILineBreakMode lineBreakMode, UIBaselineAdjustment baselineAdjustment);

    @Header("UIStringDrawing")
    @OccInstanceMethod(value = "drawAtPoint:forWidth:withFont:minFontSize:actualFontSize:lineBreakMode" +
            ":baselineAdjustment:",
            tokenGroup = "NSString_UIKit_Additions")
    GraphicsSize drawString(GraphicsPoint point, float width, UIFont font, float minFontSize, float actualFontSize,
                            UILineBreakMode lineBreakMode, UIBaselineAdjustment baselineAdjustment);

    @Header("UIStringDrawing")
    @OccInstanceMethod(value = "drawInRect:withFont:", tokenGroup = "NSString_UIKit_Additions")
    GraphicsSize drawString(GraphicsRect rect, UIFont font);

    @Header("UIStringDrawing")
    @OccInstanceMethod(value = "drawInRect:withFont:lineBreakMode:", tokenGroup = "NSString_UIKit_Additions")
    GraphicsSize drawString(GraphicsRect rect, UIFont font, UILineBreakMode lineBreakMode);

    @Header("UIStringDrawing")
    @OccInstanceMethod(value = "drawInRect:withFont:lineBreakMode:alignment:", tokenGroup = "NSString_UIKit_Additions")
    GraphicsSize drawString(GraphicsRect rect, UIFont font, UILineBreakMode lineBreakMode, UITextAlignment alignment);

    @Header("UIGeometry")
    @CFunction(value = "CGAffineTransformFromString", tokenGroup = "UIKit")
    GraphicsAffineTransform toCGAffineTransform();

    @Header("UIGeometry")
    @CFunction(value = "CGPointFromString", tokenGroup = "UIKit")
    GraphicsPoint toCGPoint();

    @Header("UIGeometry")
    @CFunction(value = "CGRectFromString", tokenGroup = "UIKit")
    GraphicsRect toCGRect();

    @Header("UIGeometry")
    @CFunction(value = "CGSizeFromString", tokenGroup = "UIKit")
    GraphicsSize toCGSize();

    @Override
    FoundationStringFactory getFactory();
}
