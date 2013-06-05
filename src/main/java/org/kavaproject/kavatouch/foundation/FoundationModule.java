/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.foundation;

import org.kavaproject.kavatouch.internal.Module;
import com.google.inject.Provides;
import org.kavaproject.kavatouch.runtime.MethodDispatcher;
import org.kavaproject.kavatouch.runtime.MethodPerformer;
import org.kavaproject.kavatouch.runtime.MethodSignatureService;

import javax.inject.Singleton;

@Module
public class FoundationModule {
    @Provides
    @Singleton
    public AttributedStringFactory provideAttributedStringFactory() {
        return new DefaultAttributedStringFactory();
    }

    @Provides
    @Singleton
    public BlockOperationFactory provideBlockOperationFactory() {
        return new DefaultBlockOperationFactory();
    }

    @Provides
    @Singleton
    public BundleFactory provideBundleFactory(URLFactory urlFactory) {
        return new DefaultBundleFactory(urlFactory);
    }

    @Provides
    @Singleton
    public FoundationDateFactory provideFoundationDateFactory() {
        return new DefaultFoundationDateFactory();
    }

    @Provides
    @Singleton
    public FoundationErrorFactory provideFoundationErrorFactory() {
        return new DefaultFoundationErrorFactory();
    }

    @Provides
    @Singleton
    public FoundationExceptionFactory provideFoundationExceptionFactory() {
        return new DefaultFoundationExceptionFactory();
    }

    @Provides
    @Singleton
    public FoundationStringFactory provideFoundationStringFactory() {
        return new DefaultFoundationStringFactory();
    }

    @Provides
    @Singleton
    public FoundationThreadFactory provideFoundationThreadFactory(MethodPerformer methodPerformer) {
        return new DefaultFoundationThreadFactory(methodPerformer);
    }

    @Provides
    @Singleton
    public InvocationFactory provideInvocationFactory(MethodDispatcher methodDispatcher) {
        return new DefaultInvocationFactory(methodDispatcher);
    }

    @Provides
    @Singleton
    public InvocationOperationFactory provideInvocationOperationFactory() {
        return new DefaultInvocationOperationFactory();
    }

    @Provides
    @Singleton
    public KeyedArchiverFactory provideKeyedArchiverFactory() {
        return new DefaultKeyedArchiverFactory();
    }

    @Provides
    @Singleton
    public KeyedUnarchiverFactory provideKeyedUnarchiverFactory() {
        return new DefaultKeyedUnarchiverFactory();
    }

    @Provides
    @Singleton
    public MachPortFactory provideMachPortFactory(PortMessageFactory portMessageFactory) {
        return new DefaultMachPortFactory(portMessageFactory);
    }

    @Provides
    @Singleton
    public MessagePortNameServer provideSharedMessagePortNameServer() {
        return new DefaultMessagePortNameServerFactory().sharedInstance();
    }

    @Provides
    @Singleton
    public MethodSignatureFactory provideMethodSignatureFactory() {
        return new DefaultMethodSignatureFactory();
    }

    @Provides
    @Singleton
    public NotificationCenterFactory provideNotificationCenterFactory() {
        return new DefaultNotificationCenterFactory();
    }

    @Provides
    @Singleton
    public NotificationFactory provideNotificationFactory() {
        return new DefaultNotificationFactory();
    }

    @Provides
    @Singleton
    public NotificationQueueFactory provideNotificationQueueFactory() {
        return new DefaultNotificationQueueFactory();
    }

    @Provides
    @Singleton
    public OperationQueueFactory provideOperationQueueFactory() {
        return new DefaultOperationQueueFactory();
    }

    @Provides
    @Singleton
    public PortMessageFactory providePortMessageFactory() {
        return new DefaultPortMessageFactory();
    }

    @Provides
    @Singleton
    public PortNameServer provideSystemDefaultPortNameServer() {
        return new DefaultPortNameServerFactory().systemDefaultPortNameServer();
    }

    @Provides
    @Singleton
    public ProcessInfo provideProcessInfo() {
        return new DefaultProcessInfoFactory().processInfo();
    }

    @Provides
    @Singleton
    public PropertyListSerializationFactory providePropertyListSerializationFactory() {
        return new DefaultPropertyListSerializationFactory();
    }

    @Provides
    @Singleton
    public RunLoopFactory provideRunLoopFactory(FoundationDateFactory foundationDateFactory) {
        return new DefaultRunLoopFactory(foundationDateFactory);
    }

    @Provides
    @Singleton
    public TimerFactory provideTimerFactory(FoundationDateFactory foundationDateFactory,
                                            RunLoopFactory runLoopFactory, InvocationFactory invocationFactory,
                                            MethodSignatureService methodSignatureService) {
        return new DefaultTimerFactory(foundationDateFactory, runLoopFactory, invocationFactory,
                methodSignatureService);
    }

    @Provides
    @Singleton
    public URLFactory provideURLFactory() {
        return new DefaultURLFactory();
    }

    @Provides
    @Singleton
    public NotificationCenter provideDefaultNotificationCenter(NotificationCenterFactory notificationCenterFactory) {
        return notificationCenterFactory.create();
    }
}
