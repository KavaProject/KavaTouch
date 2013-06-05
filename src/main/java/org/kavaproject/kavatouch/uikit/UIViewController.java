/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.uikit;

import org.kavaproject.kavatouch.coregraphics.GraphicsSize;
import org.kavaproject.kavatouch.foundation.Bundle;
import org.kavaproject.kavatouch.foundation.Coder;
import org.kavaproject.kavatouch.foundation.Coding;
import org.kavaproject.kavatouch.internal.ArgumentSemantic;
import org.kavaproject.kavatouch.internal.OccInstanceMethod;
import org.kavaproject.kavatouch.internal.OccInstanceProperty;
import org.kavaproject.kavatouch.runtime.Creatable;
import org.kavaproject.kavatouch.runtime.Runnable1;
import org.kavaproject.kavatouch.runtime.SEL;
import org.kavaproject.kavatouch.uikit.staging.*;

import java.util.List;

public interface UIViewController extends UIResponder, Coding, Creatable {
    @OccInstanceProperty(value = "nibBundle", argumentSemantic = ArgumentSemantic.RETAIN)
    Bundle getNibBundle();

    @OccInstanceMethod("shouldPerformSegueWithIdentifier:sender:")
    boolean shouldPerformSegue(String identifier, Object sender);

    @OccInstanceMethod("performSegueWithIdentifier:sender:")
    void performSegue(String identifier, Object sender);

    @OccInstanceMethod("prepareForSegue:sender:")
    void prepareForSegue(UIStoryboardSegue segue, Object sender);

    @OccInstanceProperty(value = "storyboard", argumentSemantic = ArgumentSemantic.RETAIN)
    UIStoryboard getStoryboard();

    @OccInstanceMethod("canPerformUnwindSegueAction:fromViewController:withSender:")
    boolean canPerformUnwind(SEL action, UIViewController fromViewController, Object sender);

    @OccInstanceProperty(value = "view", argumentSemantic = ArgumentSemantic.RETAIN)
    UIView getView();

    @OccInstanceProperty(value = "view", argumentSemantic = ArgumentSemantic.RETAIN)
    void setView(UIView value);

    @OccInstanceMethod("loadView")
    void loadView();

    @OccInstanceProperty(value = "nibName", argumentSemantic = ArgumentSemantic.COPY)
    String getNibName();

    @OccInstanceMethod("viewDidLoad")
    void onViewDidLoad();

    @OccInstanceMethod("isViewLoaded")
    boolean isViewLoaded();

    @OccInstanceProperty(value = "title", argumentSemantic = ArgumentSemantic.COPY)
    String getTitle();

    @OccInstanceProperty(value = "title", argumentSemantic = ArgumentSemantic.COPY)
    void setTitle(String value);

    @OccInstanceMethod("viewDidUnload")
    @Deprecated
    void onViewDidUnload();

    @OccInstanceMethod("viewWillUnload")
    @Deprecated
    void onViewWillUnload();

    @OccInstanceMethod("didReceiveMemoryWarning")
    void onDidReceiveMemoryWarning();

    @OccInstanceMethod("viewWillAppear:")
    void onViewWillAppear(boolean animated);

    @OccInstanceMethod("viewDidAppear:")
    void onViewDidAppear(boolean animated);

    @OccInstanceMethod("viewWillDisappear:")
    void onViewWillDisappear(boolean animated);

    @OccInstanceMethod("viewDidDisappear:")
    void onViewDidDisappear(boolean animated);

    @OccInstanceMethod("viewWillLayoutSubviews")
    void onViewWillLayoutSubviews();

    @OccInstanceMethod("viewDidLayoutSubviews")
    void onViewDidLayoutSubviews();

    @OccInstanceMethod("isMovingFromParentViewController")
    boolean onIsMovingFromParentViewController();

    @OccInstanceMethod("isMovingToParentViewController")
    boolean onIsMovingToParentViewController();

    @OccInstanceMethod("isBeingPresented")
    boolean onIsBeingPresented();

    @OccInstanceMethod("isBeingDismissed")
    boolean onIsBeingDismissed();

    @OccInstanceMethod("updateViewConstraints")
    void updateViewConstraints();

    @OccInstanceProperty(value = "wantsFullScreenLayout", argumentSemantic = ArgumentSemantic.ASSIGN)
    boolean getWantsFullScreenLayout();

    @OccInstanceProperty(value = "wantsFullScreenLayout", argumentSemantic = ArgumentSemantic.ASSIGN)
    void setWantsFullScreenLayout(boolean value);

    @OccInstanceMethod("shouldAutorotate")
    boolean shouldAutorotate();

    @OccInstanceMethod("supportedInterfaceOrientations")
    UIInterfaceOrientationMask supportedInterfaceOrientations();

    @OccInstanceMethod("preferredInterfaceOrientationForPresentation")
    UIInterfaceOrientation preferredInterfaceOrientationForPresentation();

    @OccInstanceProperty("interfaceOrientation")
    UIInterfaceOrientation getInterfaceOrientation();

    @OccInstanceMethod("rotatingHeaderView")
    UIView rotatingHeaderView();

    @OccInstanceMethod("rotatingFooterView")
    UIView rotatingFooterView();

    @OccInstanceMethod("shouldAutorotateToInterfaceOrientation:")
    @Deprecated
    boolean shouldAutorotateToInterfaceOrientation(UIInterfaceOrientation orientation);

    @OccInstanceMethod("willRotateToInterfaceOrientation:duration:")
    void onWillRotate(UIInterfaceOrientation toInterfaceOrientation, double duration);

    @OccInstanceMethod("willAnimateRotationToInterfaceOrientation:duration:")
    void onWillAnimateRotation(UIInterfaceOrientation interfaceOrientation, double duration);

    @OccInstanceMethod("didRotateFromInterfaceOrientation:")
    void onDidRotate(UIInterfaceOrientation fromOrientation);

    @OccInstanceMethod("didAnimateFirstHalfOfRotationToInterfaceOrientation:")
    @Deprecated
    void onDidAnimateFirstHalfOfRotation(UIInterfaceOrientation toInterfaceOrientation);

    @OccInstanceMethod("willAnimateFirstHalfOfRotationToInterfaceOrientation:duration:")
    @Deprecated
    void onWillAnimateFirstHalfOfRotation(UIInterfaceOrientation toInterfaceOrientation, double duration);

    @OccInstanceMethod("willAnimateSecondHalfOfRotationFromInterfaceOrientation:duration:")
    @Deprecated
    void onWillAnimateSecondHalfOfRotation(UIInterfaceOrientation fromInterfaceOrientation, double duration);

    @OccInstanceMethod("willMoveToParentViewController:")
    void onWillMoveToParentViewController(UIViewController parentViewController);

    @OccInstanceMethod("didMoveToParentViewController:")
    void onDidMoveToParentViewController(UIViewController parentViewController);

    @OccInstanceProperty("editing")
    boolean isEditing();

    @OccInstanceProperty("editing")
    void setEditing(boolean value);

    @OccInstanceMethod("setEditing:animated:")
    void setEditing(boolean editing, boolean animated);

    @OccInstanceProperty("restorationIdentifier")
    String getRestorationIdentifier();

    @OccInstanceProperty("restorationIdentifier")
    void setRestorationIdentifier(String value);

    @OccInstanceProperty("restorationClass")
    UIViewControllerRestorationFactory getRestorationClass();

    @OccInstanceProperty("restorationClass")
    void setRestorationClass(UIViewControllerRestorationFactory value);

    @OccInstanceMethod("encodeRestorableStateWithCoder:")
    void encodeRestorableState(Coder coder);

    @OccInstanceMethod("decodeRestorableStateWithCoder:")
    void decodeRestorableState(Coder coder);

    @OccInstanceMethod("presentViewController:animated:completion:")
    void presentViewController(UIViewController viewController, boolean animated, Runnable completion);

    @OccInstanceMethod("dismissViewControllerAnimated:completion:")
    void dismissViewController(boolean animated, Runnable completion);

    @OccInstanceProperty(value = "modalTransitionStyle", argumentSemantic = ArgumentSemantic.ASSIGN)
    UIModalTransitionStyle getModalTransitionStyle();

    @OccInstanceProperty(value = "modalTransitionStyle", argumentSemantic = ArgumentSemantic.ASSIGN)
    void setModalTransitionStyle(UIModalTransitionStyle value);

    @OccInstanceProperty(value = "modalPresentationStyle", argumentSemantic = ArgumentSemantic.ASSIGN)
    UIModalPresentationStyle getModalPresentationStyle();

    @OccInstanceProperty(value = "modalPresentationStyle", argumentSemantic = ArgumentSemantic.ASSIGN)
    void setModalPresentationStyle(UIModalPresentationStyle value);

    @OccInstanceProperty(value = "definesPresentationContext", argumentSemantic = ArgumentSemantic.ASSIGN)
    boolean getDefinesPresentationContext();

    @OccInstanceProperty(value = "definesPresentationContext", argumentSemantic = ArgumentSemantic.ASSIGN)
    void setDefinesPresentationContext(boolean value);

    @OccInstanceProperty(value = "providesPresentationContextTransitionStyle", argumentSemantic = ArgumentSemantic
            .ASSIGN)
    boolean getProvidesPresentationContextTransitionStyle();

    @OccInstanceProperty(value = "providesPresentationContextTransitionStyle", argumentSemantic = ArgumentSemantic
            .ASSIGN)
    void setProvidesPresentationContextTransitionStyle(boolean value);

    @OccInstanceMethod("disablesAutomaticKeyboardDismissal")
    boolean disablesAutomaticKeyboardDismissal();

    @OccInstanceMethod("dismissModalViewControllerAnimated:")
    @Deprecated
    void dismissModalViewControllerAnimated(UIViewController controller, boolean animated);

    @OccInstanceMethod("presentModalViewController:animated:")
    @Deprecated
    void presentModalViewController(UIViewController controller, boolean animated);

    @OccInstanceProperty("presentingViewController")
    UIViewController getPresentingViewController();

    @OccInstanceProperty("presentedViewController")
    UIViewController getPresentedViewController();

    @OccInstanceProperty("parentViewController")
    UIViewController getParentViewController();

    @OccInstanceProperty(value = "navigationController", argumentSemantic = ArgumentSemantic.RETAIN)
    UINavigationController getNavigationController();

    @OccInstanceProperty(value = "splitViewController", argumentSemantic = ArgumentSemantic.RETAIN)
    UISplitViewController getSplitViewController();

    @OccInstanceProperty(value = "tabBarController", argumentSemantic = ArgumentSemantic.RETAIN)
    UITabBarController getTabBarController();

    @OccInstanceProperty(value = "searchDisplayController", argumentSemantic = ArgumentSemantic.RETAIN)
    UISearchDisplayController getSearchDisplayController();

    @OccInstanceProperty("modalViewController")
    @Deprecated
    UIViewController getModalViewController();

    @OccInstanceProperty("childViewControllers")
    List<UIViewController> getChildViewControllers();

    @OccInstanceMethod("addChildViewController:")
    void addChildViewController(UIViewController controller);

    @OccInstanceMethod("removeFromParentViewController")
    void removeFromParentViewController();

    @OccInstanceMethod("shouldAutomaticallyForwardRotationMethods")
    boolean shouldAutomaticallyForwardRotationMethods();

    @OccInstanceMethod("shouldAutomaticallyForwardAppearanceMethods")
    boolean shouldAutomaticallyForwardAppearanceMethods();

    @OccInstanceMethod("transitionFromViewController:toViewController:duration:options:animations:completion:")
    void transition(UIViewController fromViewController, UIViewController toViewController, double duration,
                    UIViewAnimationOptions options, Runnable animations, Runnable1<Boolean> completion);

    @OccInstanceMethod("beginAppearanceTransition:animated:")
    void beginAppearanceTransition(boolean isAppearing, boolean animated);

    @OccInstanceMethod("endAppearanceTransition")
    void endAppearanceTransition();

    @OccInstanceMethod("viewControllerForUnwindSegueAction:fromViewController:withSender:")
    UIViewController getViewControllerForUnwind(SEL action, UIViewController fromController, Object sender);

    @OccInstanceMethod("segueForUnwindingToViewController:fromViewController:identifier:")
    UIStoryboardSegue getSegueForUnwinding(UIViewController toController, UIViewController fromController,
                                           String identifier);

    @OccInstanceMethod("automaticallyForwardAppearanceAndRotationMethodsToChildViewControllers")
    @Deprecated
    boolean automaticallyForwardAppearanceAndRotationMethodsToChildViewControllers();

    @OccInstanceProperty(value = "navigationItem", argumentSemantic = ArgumentSemantic.RETAIN)
    UINavigationItem getNavigationItem();

    @OccInstanceMethod("editButtonItem")
    UIBarButtonItem editButtonItem();

    @OccInstanceProperty("hidesBottomBarWhenPushed")
    boolean getHidesBottomBarWhenPushed();

    @OccInstanceProperty("hidesBottomBarWhenPushed")
    void setHidesBottomBarWhenPushed(boolean value);

    @OccInstanceMethod("setToolbarItems:animated:")
    void setToolbarItems(List<UIBarButtonItem> toolbarItems, boolean animated);

    @OccInstanceProperty(value = "toolbarItems", argumentSemantic = ArgumentSemantic.RETAIN)
    List<UIBarButtonItem> getToolbarItems();

    @OccInstanceProperty(value = "toolbarItems", argumentSemantic = ArgumentSemantic.RETAIN)
    void setToolbarItems(List<UIBarButtonItem> value);

    @OccInstanceProperty(value = "tabBarItem", argumentSemantic = ArgumentSemantic.RETAIN)
    UITabBarItem getTabBarItem();

    @OccInstanceProperty(value = "tabBarItem", argumentSemantic = ArgumentSemantic.RETAIN)
    void setTabBarItem(UITabBarItem value);

    @OccInstanceProperty("contentSizeForViewInPopover")
    GraphicsSize getContentSizeForViewInPopover();

    @OccInstanceProperty("contentSizeForViewInPopover")
    void setContentSizeForViewInPopover(GraphicsSize value);

    @OccInstanceProperty("modalInPopover")
    boolean isModalInPopover();

    @OccInstanceProperty("modalInPopover")
    void setModalInPopover(boolean value);

    @Override
    UIViewControllerFactory getFactory();
}
