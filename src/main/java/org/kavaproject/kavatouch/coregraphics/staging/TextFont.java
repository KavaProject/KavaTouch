/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.coregraphics.staging;

import org.kavaproject.kavatouch.coregraphics.GraphicsAffineTransform;
import org.kavaproject.kavatouch.internal.CFunction;
import org.kavaproject.kavatouch.internal.Header;
import org.kavaproject.kavatouch.internal.OpaqueType;

@Header("CTFont")
@OpaqueType("CTFontRef")
public class TextFont {
    @CFunction("CTFontCreateWithName")
    public TextFont(String name, float size, final GraphicsAffineTransform matrix) {
    }

    public static final void CTFontCreateWithNameAndOptions() {
    }

    public static final void CTFontCreateWithFontDescriptor() {
    }

    public static final void CTFontCreateWithFontDescriptorAndOptions() {
    }

    public static final void CTFontCreateUIFontForLanguage() {
    }

    public static final void CTFontCreateCopyWithAttributes() {
    }

    public static final void CTFontCreateCopyWithSymbolicTraits() {
    }

    public static final void CTFontCreateCopyWithFamily() {
    }

    public static final void CTFontCreateForString() {
    }

    public static final void CTFontCopyFontDescriptor() {
    }

    public static final void CTFontCopyAttribute() {
    }

    public static final void CTFontGetSize() {
    }

    public static final void CTFontGetMatrix() {
    }

    public static final void CTFontGetSymbolicTraits() {
    }

    public static final void CTFontCopyTraits() {
    }

    public static final void CTFontCopyPostScriptName() {
    }

    public static final void CTFontCopyFamilyName() {
    }

    public static final void CTFontCopyFullName() {
    }

    public static final void CTFontCopyDisplayName() {
    }

    public static final void CTFontCopyName() {
    }

    public static final void CTFontCopyLocalizedName() {
    }

    public static final void CTFontCopyCharacterSet() {
    }

    public static final void CTFontGetStringEncoding() {
    }

    public static final void CTFontCopySupportedLanguages() {
    }

    public static final void CTFontGetAscent() {
    }

    public static final void CTFontGetDescent() {
    }

    public static final void CTFontGetLeading() {
    }

    public static final void CTFontGetUnitsPerEm() {
    }

    public static final void CTFontGetGlyphCount() {
    }

    public static final void CTFontGetBoundingBox() {
    }

    public static final void CTFontGetUnderlinePosition() {
    }

    public static final void CTFontGetUnderlineThickness() {
    }

    public static final void CTFontGetSlantAngle() {
    }

    public static final void CTFontGetCapHeight() {
    }

    public static final void CTFontGetXHeight() {
    }

    public static final void CTFontCreatePathForGlyph() {
    }

    public static final void CTFontGetGlyphWithName() {
    }

    public static final void CTFontGetBoundingRectsForGlyphs() {
    }

    public static final void CTFontGetAdvancesForGlyphs() {
    }

    public static final void CTFontGetVerticalTranslationsForGlyphs() {
    }

    public static final void CTFontCopyVariationAxes() {
    }

    public static final void CTFontCopyVariation() {
    }

    public static final void CTFontCopyFeatures() {
    }

    public static final void CTFontCopyFeatureSettings() {
    }

    public static final void CTFontGetGlyphsForCharacters() {
    }

    public static final void CTFontDrawGlyphs() {
    }

    public static final void CTFontGetLigatureCaretPositions() {
    }

    public static final void CTFontCopyGraphicsFont() {
    }

    public static final void CTFontCreateWithGraphicsFont() {
    }

    public static final void CTFontGetPlatformFont() {
    }

    public static final void CTFontCreateWithPlatformFont() {
    }

    public static final void CTFontCreateWithQuickdrawInstance() {
    }

    public static final void CTFontCopyAvailableTables() {
    }

    public static final void CTFontCopyTable() {
    }
}
