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
import org.kavaproject.kavatouch.internal.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

@Header("CFRunLoop")
@OpaqueType("CFRunLoopRef")
public class CoreRunLoop implements CoreType {
    public static final Internal INTERNAL = new Internal();
    public static final String MODES_COMMON = "kCFRunLoopCommonModes";
    public static final String MODE_DEFAULT = "kCFRunLoopDefaultMode";
    final MachPort wakeUpPort;
    volatile boolean stop;
    volatile boolean waiting;
    boolean processingMainQueue;
    List<String> commonModeNames = new ArrayList<String>();
    Set<CoreRunLoopObserver> commonModeObservers = new HashSet<CoreRunLoopObserver>();
    Set<CoreRunLoopTimer> commonModeTimers = new HashSet<CoreRunLoopTimer>();
    Set<CoreRunLoopSource> commonModeSources = new HashSet<CoreRunLoopSource>();
    Map<String, CoreRunLoopMode> modes = new HashMap<String, CoreRunLoopMode>();
    CoreRunLoopMode currentMode;
    Map<Runnable, List<String>> blockModeNames = new HashMap<Runnable, List<String>>();
    Queue<Runnable> blocks = new ConcurrentLinkedQueue<Runnable>();

    private CoreRunLoop() {
        wakeUpPort = new MachPort("org.kavaproject.corefoundation.CFRunLoop/WakeUpPort", 1);
        wakeUpPort.releaseReceiveRights();
        addCommonMode(CoreRunLoop.MODE_DEFAULT);
    }

    @CFunction("CFRunLoopAddCommonMode")
    public void addCommonMode(String mode) {
        if (commonModeNames.contains(mode)) {
            return;
        }
        commonModeNames.add(mode);
        modes.put(mode, new CoreRunLoopMode(mode));
        for (CoreRunLoopSource source : commonModeSources) {
            addSource(source, mode);
        }
        for (CoreRunLoopObserver observer : commonModeObservers) {
            addObserver(observer, mode);
        }
        for (CoreRunLoopTimer timer : commonModeTimers) {
            addTimer(timer, mode);
        }
    }

    @CFunction("CFRunLoopAddSource")
    public void addSource(CoreRunLoopSource source, String mode) {
        assert source instanceof CoreRunLoopSourceCustom;
        if (mode == CoreRunLoop.MODES_COMMON) {
            commonModeSources.add(source);
            for (String commonModeName : commonModeNames) {
                CoreRunLoopMode commonMode = modes.get(commonModeName);
                if (source instanceof CoreRunLoopSourcePort) {
                    addV1Source(commonMode, (CoreRunLoopSourcePort) source);
                } else {
                    addV0Source(commonMode, (CoreRunLoopSourceCustom) source);
                }
            }
        } else {
            CoreRunLoopMode modeObj = modes.get(mode);
            if (modeObj == null) {
                modeObj = new CoreRunLoopMode(mode);
                modes.put(mode, modeObj);
            }
            if (source instanceof CoreRunLoopSourcePort) {
                addV1Source(modeObj, (CoreRunLoopSourcePort) source);
            } else {
                addV0Source(modeObj, (CoreRunLoopSourceCustom) source);
            }
        }
    }

    private void addV0Source(CoreRunLoopMode rlMode, CoreRunLoopSourceCustom source) {
        rlMode.v0Sources.add(source);
        source.runloops.add(this);
        source.onSchedule(this, rlMode.mode);
    }

    private void addV1Source(CoreRunLoopMode rlMode, CoreRunLoopSourcePort source) {
        MachPort nativePort = source.onGetPort();
        rlMode.v1Sources.put(nativePort, source);
        source.runloops.add(this);
    }

    @CFunction("CFRunLoopAddObserver")
    public void addObserver(CoreRunLoopObserver observer, String mode) {
        if (mode == CoreRunLoop.MODES_COMMON) {
            commonModeObservers.add(observer);
            for (String commonModeName : commonModeNames) {
                CoreRunLoopMode commonMode = modes.get(commonModeName);
                commonMode.observers.add(observer);
            }
        } else {
            CoreRunLoopMode modeObj = modes.get(mode);
            if (modeObj == null) {
                modeObj = new CoreRunLoopMode(mode);
                modes.put(mode, modeObj);
            }
            modeObj.observers.add(observer);
        }
        observer.rl = this;
    }

    @CFunction("CFRunLoopAddTimer")
    public void addTimer(CoreRunLoopTimer timer, String mode) {
        if (mode == CoreRunLoop.MODES_COMMON) {
            commonModeTimers.add(timer);
            for (String commonModeName : commonModeNames) {
                CoreRunLoopMode commonMode = modes.get(commonModeName);
                commonMode.timers.add(timer);
                commonMode.armNextTimer();
                timer.modes.add(commonMode);
            }
        } else {
            CoreRunLoopMode modeObj = modes.get(mode);
            if (modeObj == null) {
                modeObj = new CoreRunLoopMode(mode);
                modes.put(mode, modeObj);
            }
            modeObj.timers.add(timer);
            modeObj.armNextTimer();
            timer.modes.add(modeObj);
        }
        timer.rl = this;
    }

    @CFunction("CFRunLoopGetMain")
    public static CoreRunLoop getMain() {
        return INTERNAL.getRL(DispatchQueue.mainThread);
    }

    @CFunction("CFRunLoopRun")
    public static void run() {
        runInMode(CoreRunLoop.MODE_DEFAULT, Double.MAX_VALUE, false);
    }

    @CFunction("CFRunLoopRunInMode")
    public static CoreRunLoopExitReason runInMode(String mode, double seconds, boolean returnAfterSourceHandled) {
        CoreRunLoop rl = getCurrent();
        CoreRunLoopMode rlMode = rl.modes.get(mode);
        if (rlMode == null) {
            rlMode = new CoreRunLoopMode(mode);
            rl.modes.put(mode, rlMode);
        }
        CoreRunLoopMode tmpMode = rl.currentMode;
        rl.currentMode = rlMode;
        CoreRunLoopExitReason exitCode = new ThreadOwnerRun().start(rl, rlMode, seconds, returnAfterSourceHandled,
                tmpMode);
        rl.currentMode = tmpMode;
        return exitCode;
    }

    @CFunction("CFRunLoopGetCurrent")
    public static CoreRunLoop getCurrent() {
        Thread thread = Thread.currentThread();
        return INTERNAL.getRL(thread);
    }

    @CFunction("CFRunLoopStop")
    public void stop() {
        stop = true;
        wakeUp();
    }

    @CFunction("CFRunLoopWakeUp")
    public void wakeUp() {
        MachMessage message = new MachMessage();
        message.destinationPort = wakeUpPort;
        message.send(0, TimeUnit.NANOSECONDS);
    }

    @CFunction("CFRunLoopIsWaiting")
    public boolean isWaiting() {
        return waiting;
    }

    @CFunction("CFRunLoopContainsSource")
    public boolean containsSource(CoreRunLoopSource source, String mode) {
        if (mode == CoreRunLoop.MODES_COMMON) {
            return commonModeSources.contains(source);
        }
        CoreRunLoopMode rlMode = modes.get(mode);
        if (rlMode == null) {
            return false;
        }
        if (source instanceof CoreRunLoopSourcePort) {
            return rlMode.v1Sources.containsValue(source);
        } else if (source instanceof CoreRunLoopSourceCustom) {
            return rlMode.v0Sources.contains(source);
        } else {
            throw new IllegalArgumentException();
        }
    }

    @CFunction("CFRunLoopRemoveSource")
    public void removeSource(CoreRunLoopSource source, String mode) {
        assert source instanceof CoreRunLoopSource;
        if (mode == CoreRunLoop.MODES_COMMON) {
            commonModeSources.remove(source);
            for (String commonModeName : commonModeNames) {
                CoreRunLoopMode commonMode = modes.get(commonModeName);
                if (source instanceof CoreRunLoopSourcePort) {
                    removeV1Source(commonMode, (CoreRunLoopSourcePort) source);
                } else {
                    removeV0Source(commonMode, (CoreRunLoopSourceCustom) source);
                }
            }
        } else {
            CoreRunLoopMode modeObj = modes.get(mode);
            if (source instanceof CoreRunLoopSourcePort) {
                removeV1Source(modeObj, (CoreRunLoopSourcePort) source);
            } else {
                removeV0Source(modeObj, (CoreRunLoopSourceCustom) source);
            }
        }
    }

    private void removeV0Source(CoreRunLoopMode rlMode, CoreRunLoopSourceCustom source) {
        if (!rlMode.v0Sources.contains(source)) {
            return;
        }
        rlMode.v0Sources.remove(source);
        source.onCancel(this, rlMode.mode);
    }

    private void removeV1Source(CoreRunLoopMode rlMode, CoreRunLoopSourcePort source) {
        MachPort nativePort = source.onGetPort();
        rlMode.v1Sources.remove(nativePort);
        source.runloops.remove(this);
    }

    @CFunction("CFRunLoopContainsObserver")
    public boolean containsObserver(CoreRunLoopObserver observer, String mode) {
        CoreRunLoopMode rlMode = modes.get(mode);
        return rlMode != null && observer.rl == this && rlMode.observers.contains(observer);
    }

    @CFunction("CFRunLoopRemoveObserver")
    public void removeObserver(CoreRunLoopObserver observer, String mode) {
        if (mode == CoreRunLoop.MODES_COMMON) {
            commonModeTimers.remove(observer);
            for (String commonModeName : commonModeNames) {
                CoreRunLoopMode commonMode = modes.get(commonModeName);
                commonMode.observers.remove(observer);
            }
        } else {
            CoreRunLoopMode modeObj = modes.get(mode);
            if (modeObj != null) {
                modeObj.observers.remove(observer);
            }
        }
    }

    @CFunction("CFRunLoopCopyAllModes")
    public List<String> copyAllModes() {
        ArrayList<String> modes = new ArrayList<String>(this.modes.keySet());
        return modes;
    }

    @CFunction("CFRunLoopCopyCurrentMode")
    public final String copyCurrentMode() {
        return currentMode != null ? currentMode.mode : null;
    }

    @CFunction("CFRunLoopGetNextTimerFireDate")
    public double getNextTimerFireDate(String mode) {
        CoreRunLoopMode rlMode = modes.get(mode);
        if (rlMode == null || rlMode.timers.isEmpty()) {
            return Double.MAX_VALUE;
        }
        return rlMode.timers.first().nextFireDate;
    }

    @CFunction("CFRunLoopRemoveTimer")
    public void removeTimer(CoreRunLoopTimer timer, String mode) {
        if (mode == CoreRunLoop.MODES_COMMON) {
            commonModeTimers.remove(timer);
            for (String commonModeName : commonModeNames) {
                CoreRunLoopMode commonMode = modes.get(commonModeName);
                commonMode.timers.remove(timer);
                commonMode.armNextTimer();
                timer.modes.remove(commonMode);
            }
        } else {
            CoreRunLoopMode modeObj = modes.get(mode);
            if (modeObj != null) {
                modeObj.timers.remove(timer);
                modeObj.armNextTimer();
                timer.modes.remove(modeObj);
            }
        }
        if (timer.modes.isEmpty()) {
            timer.rl = null;
        }
    }

    @CFunction("CFRunLoopContainsTimer")
    public boolean containsTimer(CoreRunLoopTimer timer, String mode) {
        CoreRunLoopMode rlMode = modes.get(mode);
        return rlMode != null && rlMode.timers.contains(timer);
    }

    @CFunction("CFRunLoopPerformBlock")
    public void performBlock(String mode, Runnable block) {
        performBlock(Collections.singletonList(mode), block);
    }

    @CFunction("CFRunLoopPerformBlock")
    public void performBlock(List<String> mode, Runnable block) {
        blockModeNames.put(block, mode);
        blocks.add(block);
    }

    public static class Internal {
        private ConcurrentMap<Thread, CoreRunLoop> mRunLoops = new ConcurrentHashMap<Thread, CoreRunLoop>();

        public CoreRunLoop getRL(Thread thread) {
            CoreRunLoop rl = mRunLoops.get(thread);
            if (rl == null) {
                rl = new CoreRunLoop();
                mRunLoops.put(thread, rl);
            }
            return rl;
        }
    }
}
