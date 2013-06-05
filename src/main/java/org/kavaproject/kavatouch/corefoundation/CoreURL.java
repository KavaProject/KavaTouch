/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.corefoundation;

import org.apache.http.util.ByteArrayBuffer;
import org.kavaproject.kavatouch.internal.CFunction;
import org.kavaproject.kavatouch.internal.Header;
import org.kavaproject.kavatouch.internal.OpaqueType;
import org.kavaproject.kavatouch.util.NotImplementedException;
import org.kavaproject.kavatouch.util.OutArg;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;

@Header("CFURL")
@OpaqueType("CFURLRef")
public class CoreURL implements CoreType {
    private File mDirectoryPath;
    private String mFileName;
    private boolean mIsFilePathURL;

    private CoreURL(CoreURL other) {
        mDirectoryPath = other.mDirectoryPath;
        mFileName = other.mFileName;
    }

    @CFunction("CFURLCreateWithFileSystemPath")
    public CoreURL(String filePath, CoreURLPathStyle pathStyle, boolean isDirectory) {
        File file = new File(filePath);
        if (isDirectory) {
            mDirectoryPath = file;
        } else {
            mDirectoryPath = file.getParentFile();
            mFileName = file.getName();
        }
    }

    @CFunction("CFURLCreateWithFileSystemPathRelativeToBase")
    public CoreURL(String filePath, CoreURLPathStyle pathStyle, boolean isDirectory, CoreURL baseURL) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CFURLCreateWithString")
    public CoreURL(String urlString, CoreURL baseURL) {
        throw new NotImplementedException();
    }

    @CFunction("CFURLCreateFromFSRef")
    public CoreURL(File fsRef) {
        throw new NotImplementedException();
    }

    @CFunction("CFURLCreateWithBytes")
    public CoreURL(byte[] URLBytes, int length, CoreStringEncoding encoding, CoreURL baseURL) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CFURLCreateAbsoluteURLWithBytes")
    public static final CoreURL createAbsoluteURL(byte[] relativeURLBytes, int length, CoreStringEncoding encoding,
                                                  CoreURL baseURL, boolean useCompatibilityMode) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CFURLCreateByResolvingBookmarkData")
    public static final CoreURL createByResolvingBookmarkData(ByteBuffer bookmark,
                                                              CoreURLBookmarkResolutionAndFileCreationOptions
                                                                      options, CoreURL relativeToURL,
                                                              List resourcePropertiesToInclude,
                                                              OutArg<Boolean> isStale) throws RuntimeException {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CFURLCreateFromFileSystemRepresentation")
    public static CoreURL createFromFileSystemRepresentation(char[] buffer, int bufLen, boolean isDirectory) {
        return new CoreURL(buffer.toString(), CoreURLPathStyle.POSIX, isDirectory);
    }

    @CFunction("CFURLCreateFromFileSystemRepresentationRelativeToBase")
    public static CoreURL createFromFileSystemRepresentation(byte[] buffer, int bufLen, boolean isDirectory,
                                                             CoreURL baseURL) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CFURLCreateStringByAddingPercentEscapes")
    public static String createStringByAddingPercentEscapes(String originalString, String charactersToLeaveUnescaped,
                                                            String legalURLCharactersToBeEscaped,
                                                            CoreStringEncoding encoding) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CFURLCreateStringByReplacingPercentEscapes")
    public static String createStringByReplacingPercentEscapes(String originalString, String charactersToLeaveEscaped) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CFURLCreateStringByReplacingPercentEscapesUsingEncoding")
    public static String createStringByReplacingPercentEscapes(String origString, String charsToLeaveEscaped,
                                                               CoreStringEncoding encoding) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CFURLCreateResourcePropertiesForKeysFromBookmarkData")
    public static Map<CoreURLResourcePropertyKey, Object> createResourceProperties(List<CoreURLResourcePropertyKey>
                                                                                               resourcePropertiesToReturn, ByteBuffer bookmark) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CFURLCreateResourcePropertyForKeyFromBookmarkData")
    public static <T> T createResourceProperty(CoreURLResourcePropertyKey<T> resourcePropertyKey, ByteBuffer bookmark) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CFURLCreateBookmarkDataFromFile")
    public static ByteBuffer createBookmarkData(CoreURL fileURL) throws RuntimeException {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CFURLWriteBookmarkDataToFile")
    public static boolean writeBookmarkData(ByteBuffer bookmarkRef, CoreURL fileURL,
                                            CoreURLBookmarkResolutionAndFileCreationOptions options) throws
            RuntimeException {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CFURLCopyAbsoluteURL")
    public CoreURL copyAbsoluteURL() {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CFURLCreateCopyAppendingPathComponent")
    public CoreURL copyAppendingPathComponent(String pathComponent, boolean isDirectory) {
        File file = new File(mDirectoryPath, pathComponent);
        try {
            file.getCanonicalPath();
        } catch (IOException e) {
            return null;
        }
        CoreURL copy = new CoreURL(this);
        if (isDirectory) {
            copy.mDirectoryPath = file;
            copy.mFileName = null;
        } else {
            copy.mDirectoryPath = file.getParentFile();
            copy.mFileName = file.getName();
        }
        return copy;
    }

    @CFunction("CFURLCreateCopyAppendingPathExtension")
    public CoreURL copyAppendingPathExtension(String extension) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CFURLCreateCopyDeletingLastPathComponent")
    public CoreURL copyDeletingLastPathComponent() {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CFURLCreateCopyDeletingPathExtension")
    public CoreURL copyDeletingPathExtension() {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CFURLCreateFilePathURL")
    public CoreURL createFilePathURL() throws RuntimeException {
        //Path-based URL: file://localhost/Users/steve/Documents/MyFile.txt
        CoreURL copy = new CoreURL(this);
        copy.mIsFilePathURL = true;
        return copy;
    }

    @CFunction("CFURLCreateFileReferenceURL")
    public CoreURL createFileReferenceURL() throws RuntimeException {
        //File reference URL: file:///.file/id=6571367.2773272/
        throw new NotImplementedException();
    }

    @CFunction("CFURLCanBeDecomposed")
    public boolean canBeDecomposed() {
        throw new NotImplementedException();
    }

    @CFunction("CFURLCopyFileSystemPath")
    public String copyFileSystemPath(CoreURLPathStyle pathStyle) {
        throw new NotImplementedException();
    }

    @CFunction("CFURLCopyFragment")
    public String copyFragment(String charactersToLeaveEscaped) {
        throw new NotImplementedException();
    }

    @CFunction("CFURLCopyHostName")
    public String copyHostName() {
        throw new NotImplementedException();
    }

    @CFunction("CFURLCopyLastPathComponent")
    public String copyLastPathComponent() {
        return mFileName != null ? mFileName : mDirectoryPath.getName();
    }

    @CFunction("CFURLCopyNetLocation")
    public String copyNetLocation() {
        throw new NotImplementedException();
    }

    @CFunction("CFURLCopyParameterString")
    public String copyParameterString(String charactersToLeaveEscaped) {
        throw new NotImplementedException();
    }

    @CFunction("CFURLCopyPassword")
    public String copyPassword() {
        throw new NotImplementedException();
    }

    @CFunction("CFURLCopyPath")
    public String copyPath() {
        return mFileName != null ? new File(mDirectoryPath, mFileName).getPath() : mDirectoryPath.getPath();
    }

    @CFunction("CFURLCopyPathExtension")
    public String copyPathExtension() {
        throw new NotImplementedException();
    }

    @CFunction("CFURLCopyQueryString")
    public String copyQueryString(String charactersToLeaveEscaped) {
        throw new NotImplementedException();
    }

    @CFunction("CFURLCopyResourceSpecifier")
    public String copyResourceSpecifier() {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CFURLCopyScheme")
    public String copyScheme() {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CFURLCopyStrictPath")
    public String copyStrictPath(boolean isAbsolute) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CFURLCopyUserName")
    public String copyUserName() {
        throw new NotImplementedException();
    }

    @CFunction("CFURLGetPortNumber")
    public int getPortNumber() {
        throw new NotImplementedException();
    }

    @CFunction("CFURLHasDirectoryPath")
    public boolean hasDirectoryPath() {
        throw new NotImplementedException();
    }

    @CFunction("CFURLCreateData")
    public ByteBuffer createData(CoreStringEncoding encoding, boolean escapeWhitespace) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CFURLGetFileSystemRepresentation")
    public boolean getFileSystemRepresentation(boolean resolveAgainstBase, OutArg<String> buffer, int maxBufLen) {
        throw new NotImplementedException();
    }

    @CFunction("CFURLGetFSRef")
    public boolean getFSRef(OutArg<File> fsRef) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CFURLGetString")
    @Override
    public String toString() {
        return "file://localhost" + (mFileName != null ? new File(mDirectoryPath,
                mFileName) : mDirectoryPath).getPath();
    }

    @CFunction("CFURLGetBaseURL")
    public CoreURL getBaseURL() {
        throw new NotImplementedException();
    }

    @CFunction("CFURLGetBytes")
    public int getBytes(OutArg<byte[]> buffer) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CFURLGetByteRangeForComponent")
    public CoreRange getByteRangeForComponent(CoreURLComponentType component, CoreRange rangeIncludingSeparators) {
        throw new NotImplementedException();
    }

    @CFunction("CFURLResourceIsReachable")
    public boolean resourceIsReachable() throws RuntimeException {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CFURLClearResourcePropertyCache")
    public void clearResourcePropertyCache() {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CFURLClearResourcePropertyCacheForKey")
    public void clearResourcePropertyCacheForKey(CoreURLResourcePropertyKey key) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CFURLCopyResourcePropertiesForKeys")
    public Map<CoreURLResourcePropertyKey, Object> copyResourceProperties(List<CoreURLResourcePropertyKey> keys)
            throws RuntimeException {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CFURLCopyResourcePropertyForKey")
    public <T> boolean copyResourceProperty(CoreURLResourcePropertyKey<T> key) throws RuntimeException {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CFURLSetResourcePropertiesForKeys")
    public boolean setResourceProperties(Map<CoreURLResourcePropertyKey, Object> keyedPropertyValues) throws
            RuntimeException {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CFURLSetResourcePropertyForKey")
    public <T> boolean setResourceProperty(CoreURLResourcePropertyKey<T> key, T propertyValue) throws RuntimeException {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CFURLSetTemporaryResourcePropertyForKey")
    public <T> void setTemporaryResourceProperty(CoreURLResourcePropertyKey<T> key, T propertyValue) {
        throw new NotImplementedException(); //TODO
    }

    @CFunction("CFURLCreateBookmarkData")
    public ByteBuffer createBookmarkData(CoreURLBookmarkCreationOptions options, List<CoreURLResourcePropertyKey>
            resourcePropertiesToInclude, CoreURL relativeToURL) throws RuntimeException {
        throw new NotImplementedException(); //TODO
    }

    @Header("CFURLAccess")
    @CFunction("CFURLCreateDataAndPropertiesFromResource")
    public boolean createDataAndPropertiesFromResource(OutArg<ByteBuffer> resourceData,
                                                       Map<CoreURLResourcePropertyKey, CoreType> properties,
                                                       List<CoreURLResourcePropertyKey> desiredProperties) throws CoreURLException {
        throw new NotImplementedException(); //TODO
    }

    @Header("CFURLAccess")
    @CFunction("CFURLCreatePropertyFromResource")
    public <T> T createPropertyFromResource(CoreURLResourcePropertyKey<T> property) throws CoreURLException {
        throw new NotImplementedException(); //TODO
    }

    @Header("CFURLAccess")
    @CFunction("CFURLDestroyResource")
    public boolean destroyResource() throws CoreURLException {
        throw new NotImplementedException(); //TODO
    }

    @Header("CFURLAccess")
    @CFunction("CFURLWriteDataAndPropertiesToResource")
    public boolean writeDataAndPropertiesToResource(ByteArrayBuffer dataToWrite, Map<CoreURLResourcePropertyKey, Object> propertiesToWrite) throws CoreURLException {
        throw new NotImplementedException(); //TODO
    }
}
