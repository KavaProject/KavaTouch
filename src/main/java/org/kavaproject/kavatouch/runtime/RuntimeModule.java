/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.runtime;

import org.kavaproject.kavatouch.internal.Module;
import com.google.inject.Provides;
import org.kavaproject.kavatouch.foundation.*;

import javax.inject.Singleton;

@Module
public class RuntimeModule {
    @Provides
    @Singleton
    public FactoryRegistry provideFactoryRegistry() {
        return new DefaultFactoryRegistry();
    }

    @Provides
    @Singleton
    public FactoryService provideFactoryService(FactoryRegistry factoryRegistry) {
        return new DefaultFactoryService(factoryRegistry);
    }

    @Provides
    @Singleton
    public MethodResolver provideMethodResolver(FactoryRegistry factoryRegistry) {
        return new DefaultMethodResolver(factoryRegistry);
    }

    @Provides
    @Singleton
    public MethodPerformer provideMethodPerformer(InvocationFactory invocationFactory, TimerFactory timerFactory,
                                                  FoundationDateFactory foundationDateFactory,
                                                  RunLoopFactory runLoopFactory, MethodDispatcher methodDispatcher,
                                                  MethodSignatureService methodSignatureService) {
        return new DefaultMethodPerformer(invocationFactory, timerFactory, foundationDateFactory, runLoopFactory,
                methodDispatcher, methodSignatureService);
    }

    @Provides
    @Singleton
    public MethodDispatcher provideMethodDispatcher(MethodResolver methodRegistry, FactoryRegistry factoryRegistry,
                                                    FoundationExceptionFactory foundationExceptionFactory,
                                                    MethodSignatureFactory methodSignatureFactory) {
        return new DefaultMethodDispatcher(methodRegistry, factoryRegistry, foundationExceptionFactory,
                methodSignatureFactory);
    }

    @Provides
    @Singleton
    public MethodSignatureService provideMethodSignatureService(MethodResolver methodResolver,
                                                                MethodSignatureFactory methodSignatureFactory) {
        return new DefaultMethodSignatureService(methodResolver, methodSignatureFactory);
    }

    @Provides
    @Singleton
    public MethodBackgroundPerformer provideMethodBackgroundPerformer(FoundationThreadFactory foundationThreadFactory) {
        return new DefaultMethodBackgroundPerformer(foundationThreadFactory);
    }
}
