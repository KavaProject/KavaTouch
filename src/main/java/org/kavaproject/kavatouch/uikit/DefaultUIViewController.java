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
import org.kavaproject.kavatouch.runtime.MethodResolver;
import org.kavaproject.kavatouch.runtime.Runnable1;
import org.kavaproject.kavatouch.runtime.SEL;
import org.kavaproject.kavatouch.uikit.staging.*;
import org.kavaproject.kavatouch.util.NotImplementedException;

import java.util.List;

public class DefaultUIViewController extends SimpleUIResponder implements UIViewController {
    private final UIViewFactory mUIViewFactory;
    private UIView mView;
    private String mNibName;
    private Bundle mNibBundle;
    private UIStoryboard mStoryboard;
    private String mTitle;
    private boolean mWantsFullScreenLayout;
    private UIInterfaceOrientation mInterfaceOrientation;
    private boolean mEditing;
    private String mRestorationIdentifier;
    private UIViewControllerRestorationFactory mRestorationClass;
    private UIModalTransitionStyle mModalTransitionStyle;
    private UIModalPresentationStyle mModalPresentationStyle;
    private boolean mDefinesPresentationContext;
    private boolean mProvidesPresentationContextTransitionStyle;
    private UIViewController mPresentingViewController;
    private UIViewController mPresentedViewController;
    private UIViewController mParentViewController;
    private UINavigationController mNavigationController;
    private UISplitViewController mSplitViewController;
    private UITabBarController mTabBarController;
    private UISearchDisplayController mSearchDisplayController;
    private UIViewController mModalViewController;
    private List<UIViewController> mChildViewControllers;
    private UINavigationItem mNavigationItem;
    private boolean mHidesBottomBarWhenPushed;
    private List<UIBarButtonItem> mToolbarItems;
    private UITabBarItem mTabBarItem;
    private GraphicsSize mContentSizeForViewInPopover;
    private boolean mModalInPopover;

    protected DefaultUIViewController(UIViewControllerFactory uiViewControllerFactory, UIViewFactory uiViewFactory,
                                      MethodResolver methodResolver) {
        super(uiViewControllerFactory, methodResolver);
        mUIViewFactory = uiViewFactory;
    }

    protected DefaultUIViewController(String nibName, Bundle nibBundle,
                                      UIViewControllerFactory uiViewControllerFactory, UIViewFactory uiViewFactory,
                                      MethodResolver methodResolver) {
        super(uiViewControllerFactory, methodResolver);
        mUIViewFactory = uiViewFactory;
        mNibName = nibName;
        mNibBundle = nibBundle;
    }

    @Override
    public Bundle getNibBundle() {
        return mNibBundle;
    }

    @Override
    public boolean shouldPerformSegue(String identifier, Object sender) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void performSegue(String identifier, Object sender) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void prepareForSegue(UIStoryboardSegue segue, Object sender) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public UIStoryboard getStoryboard() {
        return mStoryboard; //TODO
    }

    @Override
    public boolean canPerformUnwind(SEL action, UIViewController fromViewController, Object sender) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public UIView getView() {
        if (mView == null) {
            loadView();
        }
        return mView;
    }

    @Override
    public void setView(UIView value) {
        mView = value;
    }

    @Override
    public void loadView() {
        if (getNibName() != null) {
            //TODO nib handling
        } else {
            setView(mUIViewFactory.create());
        }
        onViewDidLoad();
    }

    @Override
    public String getNibName() {
        return mNibName;
    }

    @Override
    public void onViewDidLoad() {

    }

    @Override
    public boolean isViewLoaded() {
        return mView != null;
    }

    @Override
    public String getTitle() {
        return mTitle; //TODO
    }

    @Override
    public void setTitle(String value) {
        mTitle = value; //TODO
    }

    @Override
    public void onViewDidUnload() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void onViewWillUnload() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void onDidReceiveMemoryWarning() {

    }

    @Override
    public void onViewWillAppear(boolean animated) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void onViewDidAppear(boolean animated) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void onViewWillDisappear(boolean animated) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void onViewDidDisappear(boolean animated) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void onViewWillLayoutSubviews() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void onViewDidLayoutSubviews() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public boolean onIsMovingFromParentViewController() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public boolean onIsMovingToParentViewController() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public boolean onIsBeingPresented() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public boolean onIsBeingDismissed() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void updateViewConstraints() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public boolean getWantsFullScreenLayout() {
        return mWantsFullScreenLayout; //TODO
    }

    @Override
    public void setWantsFullScreenLayout(boolean value) {
        mWantsFullScreenLayout = value; //TODO
    }

    @Override
    public boolean shouldAutorotate() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public UIInterfaceOrientationMask supportedInterfaceOrientations() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public UIInterfaceOrientation preferredInterfaceOrientationForPresentation() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public UIInterfaceOrientation getInterfaceOrientation() {
        return mInterfaceOrientation; //TODO
    }

    @Override
    public UIView rotatingHeaderView() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public UIView rotatingFooterView() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public boolean shouldAutorotateToInterfaceOrientation(UIInterfaceOrientation orientation) {
        return orientation == UIInterfaceOrientation.PORTRAIT;
    }

    @Override
    public void onWillRotate(UIInterfaceOrientation toInterfaceOrientation, double duration) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void onWillAnimateRotation(UIInterfaceOrientation interfaceOrientation, double duration) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void onDidRotate(UIInterfaceOrientation fromOrientation) {

    }

    @Override
    public void onDidAnimateFirstHalfOfRotation(UIInterfaceOrientation toInterfaceOrientation) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void onWillAnimateFirstHalfOfRotation(UIInterfaceOrientation toInterfaceOrientation, double duration) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void onWillAnimateSecondHalfOfRotation(UIInterfaceOrientation fromInterfaceOrientation, double duration) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void onWillMoveToParentViewController(UIViewController parentViewController) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void onDidMoveToParentViewController(UIViewController parentViewController) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public boolean isEditing() {
        return mEditing; //TODO
    }

    @Override
    public void setEditing(boolean value) {
        mEditing = value; //TODO
    }

    @Override
    public void setEditing(boolean editing, boolean animated) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public String getRestorationIdentifier() {
        return mRestorationIdentifier; //TODO
    }

    @Override
    public void setRestorationIdentifier(String value) {
        mRestorationIdentifier = value; //TODO
    }

    @Override
    public UIViewControllerRestorationFactory getRestorationClass() {
        return mRestorationClass; //TODO
    }

    @Override
    public void setRestorationClass(UIViewControllerRestorationFactory value) {
        mRestorationClass = value; //TODO
    }

    @Override
    public void encodeRestorableState(Coder coder) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void decodeRestorableState(Coder coder) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void presentViewController(UIViewController viewController, boolean animated, Runnable completion) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void dismissViewController(boolean animated, Runnable completion) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public UIModalTransitionStyle getModalTransitionStyle() {
        return mModalTransitionStyle; //TODO
    }

    @Override
    public void setModalTransitionStyle(UIModalTransitionStyle value) {
        mModalTransitionStyle = value; //TODO
    }

    @Override
    public UIModalPresentationStyle getModalPresentationStyle() {
        return mModalPresentationStyle; //TODO
    }

    @Override
    public void setModalPresentationStyle(UIModalPresentationStyle value) {
        mModalPresentationStyle = value; //TODO
    }

    @Override
    public boolean getDefinesPresentationContext() {
        return mDefinesPresentationContext; //TODO
    }

    @Override
    public void setDefinesPresentationContext(boolean value) {
        mDefinesPresentationContext = value; //TODO
    }

    @Override
    public boolean getProvidesPresentationContextTransitionStyle() {
        return mProvidesPresentationContextTransitionStyle; //TODO
    }

    @Override
    public void setProvidesPresentationContextTransitionStyle(boolean value) {
        mProvidesPresentationContextTransitionStyle = value; //TODO
    }

    @Override
    public boolean disablesAutomaticKeyboardDismissal() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void dismissModalViewControllerAnimated(UIViewController controller, boolean animated) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void presentModalViewController(UIViewController controller, boolean animated) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public UIViewController getPresentingViewController() {
        return mPresentingViewController; //TODO
    }

    @Override
    public UIViewController getPresentedViewController() {
        return mPresentedViewController; //TODO
    }

    @Override
    public UIViewController getParentViewController() {
        return mParentViewController; //TODO
    }

    @Override
    public UINavigationController getNavigationController() {
        return mNavigationController; //TODO
    }

    @Override
    public UISplitViewController getSplitViewController() {
        return mSplitViewController; //TODO
    }

    @Override
    public UITabBarController getTabBarController() {
        return mTabBarController; //TODO
    }

    @Override
    public UISearchDisplayController getSearchDisplayController() {
        return mSearchDisplayController; //TODO
    }

    @Override
    public UIViewController getModalViewController() {
        return mModalViewController; //TODO
    }

    @Override
    public List<UIViewController> getChildViewControllers() {
        return mChildViewControllers; //TODO
    }

    @Override
    public void addChildViewController(UIViewController controller) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void removeFromParentViewController() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public boolean shouldAutomaticallyForwardRotationMethods() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public boolean shouldAutomaticallyForwardAppearanceMethods() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void transition(UIViewController fromViewController, UIViewController toViewController, double duration,
                           UIViewAnimationOptions options, Runnable animations, Runnable1<Boolean> completion) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void beginAppearanceTransition(boolean isAppearing, boolean animated) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public void endAppearanceTransition() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public UIViewController getViewControllerForUnwind(SEL action, UIViewController fromController, Object sender) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public UIStoryboardSegue getSegueForUnwinding(UIViewController toController, UIViewController fromController,
                                                  String identifier) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public boolean automaticallyForwardAppearanceAndRotationMethodsToChildViewControllers() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public UINavigationItem getNavigationItem() {
        return mNavigationItem; //TODO
    }

    @Override
    public UIBarButtonItem editButtonItem() {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public boolean getHidesBottomBarWhenPushed() {
        return mHidesBottomBarWhenPushed; //TODO
    }

    @Override
    public void setHidesBottomBarWhenPushed(boolean value) {
        mHidesBottomBarWhenPushed = value; //TODO
    }

    @Override
    public void setToolbarItems(List<UIBarButtonItem> toolbarItems, boolean animated) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public List<UIBarButtonItem> getToolbarItems() {
        return mToolbarItems; //TODO
    }

    @Override
    public void setToolbarItems(List<UIBarButtonItem> value) {
        mToolbarItems = value; //TODO
    }

    @Override
    public UITabBarItem getTabBarItem() {
        return mTabBarItem; //TODO
    }

    @Override
    public void setTabBarItem(UITabBarItem value) {
        mTabBarItem = value; //TODO
    }

    @Override
    public GraphicsSize getContentSizeForViewInPopover() {
        return mContentSizeForViewInPopover; //TODO
    }

    @Override
    public void setContentSizeForViewInPopover(GraphicsSize value) {
        mContentSizeForViewInPopover = value; //TODO
    }

    @Override
    public boolean isModalInPopover() {
        return mModalInPopover; //TODO
    }

    @Override
    public void setModalInPopover(boolean value) {
        mModalInPopover = value; //TODO
    }

    @Override
    public void encode(Coder encoder) {
        throw new NotImplementedException(); //TODO
    }

    @Override
    public UIViewControllerFactory getFactory() {
        return (UIViewControllerFactory) super.getFactory();
    }
}
