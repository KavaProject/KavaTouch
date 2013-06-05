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
import org.kavaproject.kavatouch.util.NotImplementedException;

public class FileReadSource extends DispatchSource {
    private final BufferedFile mFile;
    private final Thread mThread;

    @CFunction("dispatch_source_create")
    public FileReadSource(BufferedFile handle, DispatchQueue queue) {
        super(queue);
        mFile = handle;
        mThread = new Thread(new Runnable() {
            @Override
            public void run() {
                mFile.fillBuffer();
                while (!Thread.interrupted() && mFile.available() > 0) {
                    FileReadSource.this.getQueue().dispatchAsync(new DispatchBlock() {
                        @Override
                        public void execute(int index, Object context) {
                            FileReadSource.this.getEventHandler().run();
                        }
                    });
                    mFile.fillBuffer();
                }
            }
        });
    }

    @Override
    public void onActivate() {
        mThread.start();
    }

    @Override
    public int getData() {
        return mFile.available();
    }

    @Override
    protected void onDeactivate() {
        throw new NotImplementedException(); //TODO
    }
}
