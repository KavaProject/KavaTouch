/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.foundation;

import org.kavaproject.kavatouch.corefoundation.CoreErrorUserInfo;
import org.kavaproject.kavatouch.foundation.staging.SecTrust;
import org.kavaproject.kavatouch.foundation.staging.StringEncoding;
import org.kavaproject.kavatouch.internal.Header;
import org.kavaproject.kavatouch.internal.OccConstant;

import java.util.List;

@Header("NSError")
public class ErrorUserInfo extends CoreErrorUserInfo {
//    @OccConstant("NSLocalizedDescriptionKey")
//    public String localizedDescription;
    @OccConstant("NSErrorFailingURLStringKey")
    public String deprecatedFailingUrlString;

//    @OccConstant("NSFilePathErrorKey")
//    public String filePath;
    @OccConstant("NSStringEncodingErrorKey")
    public StringEncoding stringEncoding;
    @OccConstant("NSUnderlyingErrorKey")
    public FoundationError underlyingError;
    @OccConstant("NSURLErrorKey")
    public URL url;

//    @OccConstant("NSLocalizedFailureReasonErrorKey")
//    public String localizedFailureReason;

//    @OccConstant("NSLocalizedRecoverySuggestionErrorKey")
//    public String localizedRecoverySuggestion;
    @OccConstant("NSLocalizedRecoveryOptionsErrorKey")
    public List<String> localizedRecoveryOptions;
    @OccConstant("NSRecoveryAttempterErrorKey")
    public ErrorRecoveryAttempting recoveryAttempter;
    @OccConstant("NSHelpAnchorErrorKey")
    public String helpAnchor;
    @OccConstant("NSURLErrorFailingURLErrorKey")
    public URL failingUrl;
    @OccConstant("NSURLErrorFailingURLStringErrorKey")
    public String failingUrlString;
    @OccConstant("NSURLErrorFailingURLPeerTrustErrorKey")
    public SecTrust failingUrlPeerTrust;

    public ErrorUserInfo(CoreErrorUserInfo template, FoundationErrorFactory foundationErrorFactory) {
        super(template);
        this.underlyingError = foundationErrorFactory.create(template.underlyingError);
    }

    public ErrorUserInfo() {
    }
}
