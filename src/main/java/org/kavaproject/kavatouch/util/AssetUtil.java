/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.util;

import android.content.res.AssetManager;

import java.io.*;

/**
 * PackageManager public abstract Resources getResourcesForApplication (String appPackageName)
 * <p/>
 * String file = "raw/test.txt"; // res/raw/test.txt also work.
 * InputStream in = this.getClass().getClassLoader().getResourceAsStream(file);
 * <p/>
 * getResources().getIdentifier("res_name", "res_type", "com.library.package");
 */
public final class AssetUtil {
    public static boolean copyAssetsToInternalStorage(File assetFile, File storageFile, AssetManager assetManager) {
        try {
            String[] assets = assetManager.list(assetFile.getPath());
            if (assets.length == 0) {
//                return copyFile(assetManager.openFd(assetFile.getPath()).getFileDescriptor(), storageFile); //Fail!!!
                return copyFile(assetFile, storageFile, assetManager);
            } else {
                storageFile.mkdir();
                for (String file : assets) {
                    copyAssetsToInternalStorage(new File(assetFile, file), new File(storageFile, file), assetManager);
                }
            }
            return true;
        } catch (IOException ex) {
            return false;
        }
    }


//    private static boolean copyFile(FileDescriptor assetFD, File storageFile) {
//        try {
//            FileChannel inChannel = new FileInputStream(assetFD).getChannel();
//            FileChannel outChannel = new FileOutputStream(storageFile).getChannel();
//            inChannel.transferTo(0, inChannel.size(), outChannel);
//            inChannel.close();
//            outChannel.close();
//            return true;
//        } catch (FileNotFoundException e) {
//            return false;
//        } catch (IOException e) {
//            return false;
//        }
//    }

    private static boolean copyFile(File assetFile, File storageFile, AssetManager assetManager) {
        try {
            InputStream in = assetManager.open(assetFile.getPath());
            FileOutputStream out = new FileOutputStream(storageFile);
            byte[] buffer = new byte[1024];
            int read;
            while ((read = in.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }
            in.close();
            out.close();
            return true;
        } catch (FileNotFoundException e) {
            return false;
        } catch (IOException e) {
            return false;
        }
    }
}
