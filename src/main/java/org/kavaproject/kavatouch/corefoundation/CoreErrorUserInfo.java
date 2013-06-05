/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.corefoundation;

import org.kavaproject.kavatouch.internal.CData;
import org.kavaproject.kavatouch.internal.Header;

@Header("CFError")
public class CoreErrorUserInfo {
    @CData("kCFErrorLocalizedDescriptionKey")
    public String localizedDescription;
    @CData("kCFErrorLocalizedFailureReasonKey")
    public String localizedFailureReason;
    @CData("kCFErrorLocalizedRecoverySuggestionKey")
    public String localizedRecoverySuggestion;
    @CData("kCFErrorDescriptionKey")
    public String description;
    @CData("kCFErrorUnderlyingErrorKey")
    public CoreError underlyingError;
    @CData("kCFErrorURLKey")
    public CoreURL url;
    @CData("kCFErrorFilePathKey")
    public String filePath;

    public CoreErrorUserInfo(CoreErrorUserInfo template) {
        this.localizedDescription = template.localizedDescription;
        this.localizedFailureReason = template.localizedFailureReason;
        this.localizedRecoverySuggestion = template.localizedRecoverySuggestion;
        this.description = template.description;
        this.underlyingError = template.underlyingError;
        this.filePath = template.filePath;
    }

    public CoreErrorUserInfo() {
    }
}
