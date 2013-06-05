/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.uikit;

import org.kavaproject.kavatouch.internal.Header;
import org.kavaproject.kavatouch.internal.OccProtocol;
import org.kavaproject.kavatouch.internal.OccProtocolInstanceMethod;
import org.kavaproject.kavatouch.runtime.Creatable;
import org.kavaproject.kavatouch.uikit.staging.UIActionSheet;

@Header("UIActionSheet")
@OccProtocol("UIActionSheetDelegate")
public interface UIActionSheetDelegate extends Creatable {
    @OccProtocolInstanceMethod("willPresentActionSheet:")
    void onWillPresentActionSheet(UIActionSheet actionSheet);

    @OccProtocolInstanceMethod("didPresentActionSheet:")
    void onDidPresentActionSheet(UIActionSheet actionSheet);

    @OccProtocolInstanceMethod("actionSheet:willDismissWithButtonIndex:")
    void onWillDismiss(UIActionSheet actionSheet, int buttonIndex);

    @OccProtocolInstanceMethod("actionSheet:didDismissWithButtonIndex:")
    void onDidDismiss(UIActionSheet actionSheet, int buttonIndex);

    public interface ActionResponding {
        @OccProtocolInstanceMethod("actionSheet:clickedButtonAtIndex:")
        void onClickedButton(UIActionSheet actionSheet, int buttonIndex);
    }

    public interface Canceling {
        @OccProtocolInstanceMethod("actionSheetCancel:")
        void cancel(UIActionSheet actionSheet);
    }
}
