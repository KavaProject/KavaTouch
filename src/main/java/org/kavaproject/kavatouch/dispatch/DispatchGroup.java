/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.dispatch;

import org.kavaproject.kavatouch.internal.CFunction;
import org.kavaproject.kavatouch.internal.CTypedef;
import org.kavaproject.kavatouch.internal.Header;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Header("group")
@CTypedef("dispatch_group_t")
public class DispatchGroup extends DispatchObject {
    final ConcurrentMap<DispatchQueue, List<DispatchBlock>> notifyBlocks = new ConcurrentHashMap<DispatchQueue,
            List<DispatchBlock>>();
    AdjustableSemaphore semaphore = new AdjustableSemaphore(1);

    @CFunction("dispatch_group_create")
    public DispatchGroup() {
    }

    @CFunction("dispatch_group_wait")
    public long waitForGroup(DispatchTime timeout) {
        if (DispatchSemaphore.waitForSemaphore(semaphore, timeout)) {
            for (Map.Entry<DispatchQueue, List<DispatchBlock>> entry : notifyBlocks.entrySet()) {
                for (DispatchBlock block : entry.getValue()) {
                    entry.getKey().dispatchAsync(block);
                }
            }
            notifyBlocks.clear();
            return 0;
        } else {
            return -1;
        }
    }

    @CFunction("dispatch_group_notify")
    public void notify(DispatchQueue queue, DispatchBlock block) {
        CopyOnWriteArrayList<DispatchBlock> value = new CopyOnWriteArrayList<DispatchBlock>();
        List<DispatchBlock> blocks = notifyBlocks.putIfAbsent(queue, value);
        blocks = blocks != null ? blocks : value;
        blocks.add(block);
    }

    @CFunction("dispatch_group_enter")
    public void enter() {
        semaphore.reducePermits(1);
    }

    @CFunction("dispatch_group_leave")
    public void leave() {
        semaphore.release();
    }

    @CFunction("dispatch_group_async")
    public void dispatchAsync(DispatchQueue queue, DispatchBlock block) {
        semaphore.reducePermits(1);
        queue.dispatch(block, semaphore);
    }
}
