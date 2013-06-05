/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.corefoundation;

import org.kavaproject.kavatouch.dispatch.DispatchQueue;
import org.kavaproject.kavatouch.dispatch.DispatchQueuePriority;
import org.kavaproject.kavatouch.dispatch.DispatchTime;
import org.kavaproject.kavatouch.dispatch.TimerSource;
import org.kavaproject.kavatouch.internal.*;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.TimeUnit;

class ThreadOwnerRun {
    private LinkedList<CoreRunLoopSourceCustom> tV0Sources = new LinkedList<CoreRunLoopSourceCustom>();
    private List<CoreRunLoopTimer> tTimers = new ArrayList<CoreRunLoopTimer>();
    private List<Runnable> tThreadBlocks = new ArrayList<Runnable>();
    private List<CoreRunLoopObserver> tObservers = new ArrayList<CoreRunLoopObserver>();

    public CoreRunLoopExitReason start(final CoreRunLoop rl, CoreRunLoopMode mode, double seconds,
                                       Boolean stopAfterHandle, CoreRunLoopMode previousMode) {
        if (rl.stop) {
            return CoreRunLoopExitReason.STOPPED;
        }
        MachPort dispatchPort = null;
        if (rl == CoreRunLoop.getMain() && rl.commonModeNames.contains(mode.mode) && !rl.processingMainQueue) {
            dispatchPort = SessionProvider.session.getMainPort();
        }
        TimerSource timeoutTimer = null;
        long timeout;
        if (seconds <= 0) {
            timeout = 0;
        } else {
            long startTime = System.nanoTime();
            if (seconds <= (Long.MAX_VALUE / DispatchTime.NSEC_PER_SEC) - (startTime / DispatchTime.NSEC_PER_SEC)) {
                BigDecimal bigDecimal = new BigDecimal(seconds);
                bigDecimal = bigDecimal.multiply(new BigDecimal(DispatchTime.NSEC_PER_SEC));
                long nanos = bigDecimal.longValue();
                DispatchQueue queue = DispatchQueue.getGlobalQueue(DispatchQueuePriority.HIGH);
                timeoutTimer = new TimerSource(queue);
                timeoutTimer.setEventHandler(new Runnable() {
                    @Override
                    public void run() {
                        rl.wakeUp();
                    }
                });
                timeoutTimer.setTimer(DispatchTime.NOW.plus(nanos, TimeUnit.NANOSECONDS), Long.MAX_VALUE, 0);
                timeoutTimer.resume();
                timeout = startTime + (long) (seconds * DispatchTime.NSEC_PER_SEC);
            } else {
                timeout = Long.MAX_VALUE;
            }
        }
        boolean dispatchPortProcessed = true;
        MachMessage message = new MachMessage();
        MachPortSet waitSet = new MachPortSet();
        CoreRunLoopExitReason retVal = null;
        while (retVal == null) {
            waitSet.clear();
            for (MachPort port : mode.v1Sources.keySet()) {
                waitSet.add(port);
            }
            waitSet.add(mode.timerPort);
            waitSet.add(rl.wakeUpPort);
            notifyObservers(mode, CoreRunLoopActivity.BEFORE_TIMERS);
            notifyObservers(mode, CoreRunLoopActivity.BEFORE_SOURCES);
            executeBlocks(rl, mode);
            boolean sourceProcessed = processNonPortBasedInputSources(mode, stopAfterHandle);
            if (sourceProcessed) {
                executeBlocks(rl, mode);
            }
            if (dispatchPort == null || dispatchPortProcessed || dispatchPort.receive(message, 0,
                    TimeUnit.DAYS) != MachResult.SUCCESS) {
                dispatchPortProcessed = false;
                boolean wait = !sourceProcessed && timeout > 0;
                if (wait) {
                    notifyObservers(mode, CoreRunLoopActivity.BEFORE_WAITING);
                }
                if (dispatchPort != null) {
                    waitSet.add(dispatchPort);
                }
                rl.waiting = true;
                waitSet.receive(message, wait ? Long.MAX_VALUE : 0, TimeUnit.DAYS);
                rl.waiting = false;
                waitSet.remove(dispatchPort);
                notifyObservers(mode, CoreRunLoopActivity.AFTER_WAITING); //8
            }
            MachPort receivingPort = message != null ? message.destinationPort : null;
            if (receivingPort == null || receivingPort == rl.wakeUpPort) {
                //empty
            } else if (receivingPort == mode.timerPort) {
                processTimers(mode);
            } else if (receivingPort == dispatchPort) {
                rl.processingMainQueue = true;
                DispatchQueue mainQueue = DispatchQueue.getMainQueue();
                while (mainQueue.executeNextBlock()) {
                    //empty
                }
                rl.processingMainQueue = false;
                sourceProcessed = true;
                dispatchPortProcessed = true;
            } else {
                sourceProcessed = processPortBasedInputSources(mode, message);
            }
            executeBlocks(rl, mode);

            if (sourceProcessed && stopAfterHandle) {
                retVal = CoreRunLoopExitReason.HANDLED_SOURCE;
            } else if (timeout < System.nanoTime()) {
                retVal = CoreRunLoopExitReason.TIMED_OUT;
            } else if (rl.stop) {
                retVal = CoreRunLoopExitReason.STOPPED;
            } else if (isEmpty(rl, mode)) {
                retVal = CoreRunLoopExitReason.FINISHED;
            }
        }
        if (timeoutTimer != null) {
            timeoutTimer.cancel();
        }
        return retVal;
    }

    private void notifyObservers(CoreRunLoopMode rlm, CoreRunLoopActivity activity) {
        tObservers.clear();
        tObservers.addAll(rlm.observers);
        for (CoreRunLoopObserver observer : tObservers) {
            EnumSet<CoreRunLoopActivity> activities = observer.getActivities();
//            CFRunLoopObserverContext context = new CFRunLoopObserverContext();
//            observer.getContext(context);
            if (activities.contains(activity)) {
//                observer.callout.execute(observer, activity, context.info);
                observer.handler.execute(observer, activity);
                if (!observer.doesRepeat()) {
                    observer.invalidate();
                }
            }
        }
    }

    private void executeBlocks(CoreRunLoop rl, CoreRunLoopMode rlm) {
        tThreadBlocks.clear();
        Iterator<Runnable> it = rl.blocks.iterator();
        while (it.hasNext()) {
            Runnable block = it.next();
            List<String> modes = rl.blockModeNames.get(block);
            if (modes.contains(rlm.mode)) {
                it.remove();
                List<String> removed = rl.blockModeNames.remove(block);
                if (removed != null) {
                    tThreadBlocks.add(block);
                }
            }
        }
        for (Runnable block : tThreadBlocks) {
            block.run();
        }
    }

    private boolean processTimers(CoreRunLoopMode rlm) {
        double now = CoreAbsoluteTime.getCurrent();
        tTimers.clear();
        for (CoreRunLoopTimer timer : rlm.timers) {
            if (timer.nextFireDate <= now) {
                if (!timer.firing) {
                    tTimers.add(timer);
                }
            } else {
                break;
            }
        }
        boolean processedOne = false;
        for (CoreRunLoopTimer timer : tTimers) {
            processedOne = processTimer(timer, rlm) || processedOne;
        }
        return processedOne;
    }

    private boolean processTimer(CoreRunLoopTimer timer, CoreRunLoopMode rlm) {
        boolean processed = false;
        double origFireDate = timer.getNextFireDate();
        if (timer.isValid() && origFireDate <= CoreAbsoluteTime.getCurrent() && !timer.firing) {
            timer.firing = true;
            rlm.armNextTimer();
            timer.handler.execute(timer);
            timer.firing = false;
            if (!timer.doesRepeat()) {
                timer.invalidate();
            }
            processed = true;
        }
        if (timer.isValid() && processed) {
            double now = CoreAbsoluteTime.getCurrent();
            double newFireDate = timer.getNextFireDate();
            if (origFireDate >= newFireDate) {
                double passedTime = now - origFireDate;
                double interval = timer.getInterval();
                int passedIntervals = (int) (passedTime / interval);
                newFireDate += interval * (passedIntervals + 1);
                timer.setNextFireDate(newFireDate);
            } else {
                rlm.armNextTimer();
            }
        }
        return processed;
    }

    private boolean processNonPortBasedInputSources(CoreRunLoopMode mode, boolean stopAfterHandle) {
        tV0Sources.clear();
        tV0Sources.addAll(mode.v0Sources);
        boolean processedOne = false;
        for (CoreRunLoopSourceCustom source : tV0Sources) {
            synchronized (source) {
                if (!source.signalled) {
                    continue;
                }
                source.signalled = false;
                source.onPerform();
                processedOne = true;
                if (stopAfterHandle) {
                    break;
                }
            }
        }
        return processedOne;
    }

    private boolean processPortBasedInputSources(CoreRunLoopMode mode, MachMessage message) {
        CoreRunLoopSourcePort source = mode.v1Sources.get(message.destinationPort);
        synchronized (source) {
            if (!source.isValid()) {
                return false;
            }
            MachMessage replyMsg = source.onMachPerform(message, 0);
            if (replyMsg != null) {
                replyMsg.send();
            }
        }
        return true;
    }

    private boolean isEmpty(CoreRunLoop rl, CoreRunLoopMode rlm) {
        if (!rlm.v1Sources.isEmpty() || !rlm.v0Sources.isEmpty() || !rlm.timers.isEmpty()) {
            return false;
        }
        for (List<String> modes : rl.blockModeNames.values()) {
            if (modes.contains(rlm.mode)) {
                return false;
            }
        }
        return true;
    }
}
