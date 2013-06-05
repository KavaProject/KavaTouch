/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.corefoundation;

import org.kavaproject.kavatouch.internal.CEnumConstant;
import org.kavaproject.kavatouch.internal.CTypedef;
import org.kavaproject.kavatouch.internal.Header;

@Header("CFString")
@CTypedef("CFStringBuiltInEncodings")
public enum CoreStringBuiltInEncodings {
    @CEnumConstant(value = "kCFStringEncodingMacRoman", constantValue = 0)
    MAC_ROMAN(0),
    @CEnumConstant(value = "kCFStringEncodingWindowsLatin1", constantValue = 0x0500)
    WINDOWS_LATIN_1(0x0500),
    @CEnumConstant(value = "kCFStringEncodingISOLatin1", constantValue = 0x0201)
    ISO_LATIN_1(0x0201),
    @CEnumConstant(value = "kCFStringEncodingNextStepLatin", constantValue = 0x0B01)
    NEXT_STEP_LATIN(0x0B01),
    @CEnumConstant(value = "kCFStringEncodingASCII", constantValue = 0x0600)
    ASCII(0x0600),
    @CEnumConstant(value = "kCFStringEncodingUnicode", constantValue = 0x0100)
    UNICODE(0x0100),
    @CEnumConstant(value = "kCFStringEncodingUTF8", constantValue = 0x08000100)
    UTF8(0x08000100),
    @CEnumConstant(value = "kCFStringEncodingNonLossyASCII", constantValue = 0x0BFF)
    NON_LOSSY_ASCII(0x0BFF),
    @CEnumConstant(value = "kCFStringEncodingUTF16", constantValue = 0x0100)
    UTF16(0x0100),
    @CEnumConstant(value = "kCFStringEncodingUTF16BE", constantValue = 0x10000100)
    UTF16BE(0x10000100),
    @CEnumConstant(value = "kCFStringEncodingUTF16LE", constantValue = 0x14000100)
    UTF16LE(0x14000100),
    @CEnumConstant(value = "kCFStringEncodingUTF32", constantValue = 0x0c000100)
    UTF32(0x0c000100),
    @CEnumConstant(value = "kCFStringEncodingUTF32BE", constantValue = 0x18000100)
    UTF32BE(0x18000100),
    @CEnumConstant(value = "kCFStringEncodingUTF32LE", constantValue = 0x1c000100)
    UTF32LE(0x1c000100);
    public final int value;

    CoreStringBuiltInEncodings(int value) {
        this.value = value;
    }
}
