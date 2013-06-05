/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.dispatch;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.concurrent.Exchanger;

public class BufferedFile {
    private final Exchanger<ByteBuffer> mExchanger = new Exchanger<ByteBuffer>();
    private FileInputStream mStream;
    private ByteBuffer mWriteBuffer = ByteBuffer.allocateDirect(1024 * 1024);
    private ByteBuffer mReadBuffer = ByteBuffer.allocateDirect(1024 * 1024);
    private FileChannel mChannel;
    private boolean mFirstRun = true;
    private int mAvailable;

    public BufferedFile(String filename) {
        try {
            mStream = new FileInputStream(filename);
            mChannel = mStream.getChannel();
        } catch (FileNotFoundException e) {
            throw new Error(e);
        }
    }

    public void close() {
        try {
            mStream.close();
        } catch (IOException e) {
            throw new Error(e);
        }
    }

    public int read(byte[] dst, int estimated) {
        if (mReadBuffer.position() == mAvailable) {
            try {
                mReadBuffer = mExchanger.exchange(mReadBuffer);
            } catch (InterruptedException e) {
                return 0;
            }
        }
        int read = Math.min(estimated, mAvailable - mReadBuffer.position());
        mReadBuffer.get(dst, 0, read);
        mAvailable -= read;
        return read;
    }

    public void fillBuffer() {
        try {
            while (mWriteBuffer.remaining() > 0 && mChannel.read(mWriteBuffer) > 0) ;
        } catch (IOException e) {
            throw new Error(e);
        }
        mAvailable += mWriteBuffer.position();
        mWriteBuffer.flip();
        if (mFirstRun) {
            mFirstRun = false;
            ByteBuffer tmp = mReadBuffer;
            mReadBuffer = mWriteBuffer;
            mWriteBuffer = tmp;
        } else {
            try {
                mWriteBuffer = mExchanger.exchange(mWriteBuffer);
            } catch (InterruptedException e) {
                throw new Error(e);
            }
        }
    }

    public int available() {
        return mAvailable;
    }
}
