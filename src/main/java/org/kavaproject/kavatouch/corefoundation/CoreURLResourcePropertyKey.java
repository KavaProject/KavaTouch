/*
 * Copyright 2013 The Kava Project Developers. See the COPYRIGHT file at the top-level directory of this distribution
 * and at http://kavaproject.org/COPYRIGHT.
 *
 * Licensed under the Apache License, Version 2.0 <LICENSE-APACHE or http://www.apache.org/licenses/LICENSE-2.0> or the
 * MIT license <LICENSE-MIT or http://opensource.org/licenses/MIT>, at your option. This file may not be copied,
 * modified, or distributed except according to those terms.
 */

package org.kavaproject.kavatouch.corefoundation;

import org.kavaproject.kavatouch.corefoundation.staging.CoreFileSecurity;
import org.kavaproject.kavatouch.coregraphics.GraphicsColor;
import org.kavaproject.kavatouch.coregraphics.GraphicsImage;
import org.kavaproject.kavatouch.internal.CData;
import org.kavaproject.kavatouch.internal.Header;

import java.util.List;

@Header("CFURL")
public class CoreURLResourcePropertyKey<T> {
    @CData("kCFURLNameKey")
    public static final CoreURLResourcePropertyKey<String> NAME = new CoreURLResourcePropertyKey<String>();
    @CData("kCFURLLocalizedNameKey")
    public static final CoreURLResourcePropertyKey<String> LOCALIZED_NAME = new CoreURLResourcePropertyKey<String>();
    @CData("kCFURLIsRegularFileKey")
    public static final CoreURLResourcePropertyKey<Boolean> IS_REGULAR_FILE = new CoreURLResourcePropertyKey<Boolean>();
    @CData("kCFURLIsDirectoryKey")
    public static final CoreURLResourcePropertyKey<Boolean> IS_DIRECTORY = new CoreURLResourcePropertyKey<Boolean>();
    @CData("kCFURLIsSymbolicLinkKey")
    public static final CoreURLResourcePropertyKey<Boolean> IS_SYMBOLIC_LINK = new
            CoreURLResourcePropertyKey<Boolean>();
    @CData("kCFURLIsVolumeKey")
    public static final CoreURLResourcePropertyKey<Boolean> IS_VOLUME = new CoreURLResourcePropertyKey<Boolean>();
    @CData("kCFURLIsPackageKey")
    public static final CoreURLResourcePropertyKey<Boolean> IS_PACKAGE = new CoreURLResourcePropertyKey<Boolean>();
    @CData("kCFURLIsSystemImmutableKey")
    public static final CoreURLResourcePropertyKey<Boolean> IS_SYSTEM_IMMUTABLE = new
            CoreURLResourcePropertyKey<Boolean>();
    @CData("kCFURLIsUserImmutableKey")
    public static final CoreURLResourcePropertyKey<Boolean> IS_USER_IMMUTABLE = new
            CoreURLResourcePropertyKey<Boolean>();
    @CData("kCFURLIsHiddenKey")
    public static final CoreURLResourcePropertyKey<Boolean> IS_HIDDEN = new CoreURLResourcePropertyKey<Boolean>();
    @CData("kCFURLHasHiddenExtensionKey")
    public static final CoreURLResourcePropertyKey<Boolean> HAS_HIDDEN_EXTENSION = new
            CoreURLResourcePropertyKey<Boolean>();
    @CData("kCFURLCreationDateKey")
    public static final CoreURLResourcePropertyKey<CoreDate> CREATION_DATE = new CoreURLResourcePropertyKey<CoreDate>();
    @CData("kCFURLContentAccessDateKey")
    public static final CoreURLResourcePropertyKey<CoreDate> CONTENT_ACCESS_DATE = new
            CoreURLResourcePropertyKey<CoreDate>();
    @CData("kCFURLContentModificationDateKey")
    public static final CoreURLResourcePropertyKey<CoreDate> CONTENT_MODIFICATION_DATE = new
            CoreURLResourcePropertyKey<CoreDate>();
    @CData("kCFURLAttributeModificationDateKey")
    public static final CoreURLResourcePropertyKey<CoreDate> ATTRIBUTE_MODIFICATION_DATE = new
            CoreURLResourcePropertyKey<CoreDate>();
    @CData("kCFURLLinkCountKey")
    public static final CoreURLResourcePropertyKey<Integer> LINK_COUNT = new CoreURLResourcePropertyKey<Integer>();
    @CData("kCFURLParentDirectoryURLKey")
    public static final CoreURLResourcePropertyKey<CoreURL> PARENT_DIRECTORY_URL = new
            CoreURLResourcePropertyKey<CoreURL>();
    @CData("kCFURLVolumeURLKey")
    public static final CoreURLResourcePropertyKey<CoreURL> VOLUME_URL = new CoreURLResourcePropertyKey<CoreURL>();
    @CData("kCFURLTypeIdentifierKey")
    public static final CoreURLResourcePropertyKey<String> TYPE_IDENTIFIER = new CoreURLResourcePropertyKey<String>();
    @CData("kCFURLLocalizedTypeDescriptionKey")
    public static final CoreURLResourcePropertyKey<String> LOCALIZED_TYPE_DESCRIPTION = new
            CoreURLResourcePropertyKey<String>();
    @CData("kCFURLLabelNumberKey")
    public static final CoreURLResourcePropertyKey<Integer> LABEL_NUMBER = new CoreURLResourcePropertyKey<Integer>();
    @CData("kCFURLLabelColorKey")
    public static final CoreURLResourcePropertyKey<GraphicsColor> LABEL_COLOR = new
            CoreURLResourcePropertyKey<GraphicsColor>();
    @CData("kCFURLLocalizedLabelKey")
    public static final CoreURLResourcePropertyKey<String> LOCALIZED_LABEL = new CoreURLResourcePropertyKey<String>();
    @CData("kCFURLEffectiveIconKey")
    public static final CoreURLResourcePropertyKey<GraphicsImage> EFFECTIVE_ICON = new
            CoreURLResourcePropertyKey<GraphicsImage>();
    @CData("kCFURLCustomIconKey")
    public static final CoreURLResourcePropertyKey<GraphicsImage> CUSTOM_ICON = new
            CoreURLResourcePropertyKey<GraphicsImage>();
    @CData("kCFURLFileResourceIdentifierKey")
    public static final CoreURLResourcePropertyKey<CoreType> FILE_RESOURCE_IDENTIFIER = new
            CoreURLResourcePropertyKey<CoreType>();
    @CData("kCFURLVolumeIdentifierKey")
    public static final CoreURLResourcePropertyKey<CoreType> VOLUME_IDENTIFIER = new
            CoreURLResourcePropertyKey<CoreType>();
    @CData("kCFURLPreferredIOBlockSizeKey")
    public static final CoreURLResourcePropertyKey<Integer> PREFERRED_IO_BLOCK_SIZE = new
            CoreURLResourcePropertyKey<Integer>();
    @CData("kCFURLIsReadableKey")
    public static final CoreURLResourcePropertyKey<Boolean> IS_READABLE = new CoreURLResourcePropertyKey<Boolean>();
    @CData("kCFURLIsWritableKey")
    public static final CoreURLResourcePropertyKey<Boolean> IS_WRITABLE = new CoreURLResourcePropertyKey<Boolean>();
    @CData("kCFURLIsExecutableKey")
    public static final CoreURLResourcePropertyKey<Boolean> IS_EXECUTABLE = new CoreURLResourcePropertyKey<Boolean>();
    @CData("kCFURLFileSecurityKey")
    public static final CoreURLResourcePropertyKey<CoreFileSecurity> FILE_SECURITY = new
            CoreURLResourcePropertyKey<CoreFileSecurity>();
    @CData("kCFURLIsExcludedFromBackupKey")
    public static final CoreURLResourcePropertyKey<Boolean> IS_EXCLUDED_FROM_BACKUP = new
            CoreURLResourcePropertyKey<Boolean>();
    @CData("kCFURLFileResourceTypeKey")
    public static final CoreURLResourcePropertyKey<CoreURLFileResourceType> FILE_RESOURCE_TYPE = new
            CoreURLResourcePropertyKey<CoreURLFileResourceType>();
    @CData("kCFURLFileAllocatedSizeKey")
    public static final CoreURLResourcePropertyKey<Integer> FILE_ALLOCATED_SIZE = new
            CoreURLResourcePropertyKey<Integer>();
    @CData("kCFURLFileSizeKey")
    public static final CoreURLResourcePropertyKey<Integer> FILE_SIZE = new CoreURLResourcePropertyKey<Integer>();
    @CData("kCFURLIsAliasFileKey")
    public static final CoreURLResourcePropertyKey<Boolean> FILE_IS_ALIAS = new CoreURLResourcePropertyKey<Boolean>();
    @CData("kCFURLIsMountTriggerKey")
    public static final CoreURLResourcePropertyKey<Boolean> FILE_IS_MOUNT_TRIGGER = new
            CoreURLResourcePropertyKey<Boolean>();
    @CData("kCFURLTotalFileAllocatedSizeKey")
    public static final CoreURLResourcePropertyKey<Integer> FILE_TOTAL_ALLOCATED_SIZE = new
            CoreURLResourcePropertyKey<Integer>();
    @CData("kCFURLTotalFileSizeKey")
    public static final CoreURLResourcePropertyKey<Integer> FILE_TOTAL_SIZE = new CoreURLResourcePropertyKey<Integer>();
    @CData("kCFURLVolumeNameKey")
    public static final CoreURLResourcePropertyKey<String> VOLUME_NAME = new CoreURLResourcePropertyKey<String>();
    @CData("kCFURLVolumeLocalizedNameKey")
    public static final CoreURLResourcePropertyKey<String> VOLUME_LOCALIZED_NAME = new
            CoreURLResourcePropertyKey<String>();
    @CData("kCFURLVolumeLocalizedFormatDescriptionKey")
    public static final CoreURLResourcePropertyKey<String> VOLUME_LOCALIZED_FORMAT_DESCRIPTION = new
            CoreURLResourcePropertyKey<String>();
    @CData("kCFURLVolumeTotalCapacityKey")
    public static final CoreURLResourcePropertyKey<Integer> VOLUME_TOTAL_CAPACITY = new
            CoreURLResourcePropertyKey<Integer>();
    @CData("kCFURLVolumeAvailableCapacityKey")
    public static final CoreURLResourcePropertyKey<Integer> VOLUME_AVAILABLE_CAPACITY = new
            CoreURLResourcePropertyKey<Integer>();
    @CData("kCFURLVolumeResourceCountKey")
    public static final CoreURLResourcePropertyKey<Integer> VOLUME_RESOURCE_COUNT = new
            CoreURLResourcePropertyKey<Integer>();
    @CData("kCFURLVolumeSupportsPersistentIDsKey")
    public static final CoreURLResourcePropertyKey<Boolean> VOLUME_SUPPORTS_PERSISTENT_IDS = new
            CoreURLResourcePropertyKey<Boolean>();
    @CData("kCFURLVolumeSupportsSymbolicLinksKey")
    public static final CoreURLResourcePropertyKey<Boolean> VOLUME_SUPPORTS_SYMBOLIC_LINKS = new
            CoreURLResourcePropertyKey<Boolean>();
    @CData("kCFURLVolumeSupportsHardLinksKey")
    public static final CoreURLResourcePropertyKey<Boolean> VOLUME_SUPPORTS_HARD_LINKS = new
            CoreURLResourcePropertyKey<Boolean>();
    @CData("kCFURLVolumeSupportsJournalingKey")
    public static final CoreURLResourcePropertyKey<Boolean> VOLUME_SUPPORTS_JOURNALING = new
            CoreURLResourcePropertyKey<Boolean>();
    @CData("kCFURLVolumeIsJournalingKey")
    public static final CoreURLResourcePropertyKey<Boolean> VOLUME_IS_JOURNALING = new
            CoreURLResourcePropertyKey<Boolean>();
    @CData("kCFURLVolumeSupportsSparseFilesKey")
    public static final CoreURLResourcePropertyKey<Boolean> VOLUME_SUPPORTS_SPARSE_FILES = new
            CoreURLResourcePropertyKey<Boolean>();
    @CData("kCFURLVolumeSupportsZeroRunsKey")
    public static final CoreURLResourcePropertyKey<Boolean> VOLUME_SUPPORTS_ZERO_RUNS = new
            CoreURLResourcePropertyKey<Boolean>();
    @CData("kCFURLVolumeSupportsCaseSensitiveNamesKey")
    public static final CoreURLResourcePropertyKey<Boolean> VOLUME_SUPPORTS_CASE_SENSITIVE_NAMES = new
            CoreURLResourcePropertyKey<Boolean>();
    @CData("kCFURLVolumeSupportsCasePreservedNamesKey")
    public static final CoreURLResourcePropertyKey<Boolean> VOLUME_SUPPORTS_CASE_PRESERVED_NAMES = new
            CoreURLResourcePropertyKey<Boolean>();
    @CData("kCFURLVolumeSupportsRootDirectoryDatesKey")
    public static final CoreURLResourcePropertyKey<Boolean> VOLUME_SUPPORTS_ROOT_DIRECTORY_DATES = new
            CoreURLResourcePropertyKey<Boolean>();
    @CData("kCFURLVolumeSupportsVolumeSizesKey")
    public static final CoreURLResourcePropertyKey<Boolean> VOLUME_SUPPORTS_VOLUME_SIZES = new
            CoreURLResourcePropertyKey<Boolean>();
    @CData("kCFURLVolumeSupportsRenamingKey")
    public static final CoreURLResourcePropertyKey<Boolean> VOLUME_SUPPORTS_RENAMING = new
            CoreURLResourcePropertyKey<Boolean>();
    @CData("kCFURLVolumeSupportsAdvisoryFileLockingKey")
    public static final CoreURLResourcePropertyKey<Boolean> VOLUME_SUPPORTS_ADVISORY_FILE_LOCKING = new
            CoreURLResourcePropertyKey<Boolean>();
    @CData("kCFURLVolumeSupportsExtendedSecurityKey")
    public static final CoreURLResourcePropertyKey<Boolean> VOLUME_SUPPORTS_EXTENDED_SECURITY = new
            CoreURLResourcePropertyKey<Boolean>();
    @CData("kCFURLVolumeIsBrowsableKey")
    public static final CoreURLResourcePropertyKey<Boolean> VOLUME_IS_BROWSABLE = new
            CoreURLResourcePropertyKey<Boolean>();
    @CData("kCFURLVolumeMaximumFileSizeKey")
    public static final CoreURLResourcePropertyKey<Integer> VOLUME_MAXIMUM_FILE_SIZE = new
            CoreURLResourcePropertyKey<Integer>();
    @CData("kCFURLVolumeIsEjectableKey")
    public static final CoreURLResourcePropertyKey<Boolean> VOLUME_IS_EJECTABLE = new
            CoreURLResourcePropertyKey<Boolean>();
    @CData("kCFURLVolumeIsRemovableKey")
    public static final CoreURLResourcePropertyKey<Boolean> VOLUME_IS_REMOVABLE = new
            CoreURLResourcePropertyKey<Boolean>();
    @CData("kCFURLVolumeIsInternalKey")
    public static final CoreURLResourcePropertyKey<Boolean> VOLUME_IS_INTERNAL = new
            CoreURLResourcePropertyKey<Boolean>();
    @CData("kCFURLVolumeIsAutomountedKey")
    public static final CoreURLResourcePropertyKey<Boolean> VOLUME_IS_AUTOMOUNTED = new
            CoreURLResourcePropertyKey<Boolean>();
    @CData("kCFURLVolumeIsLocalKey")
    public static final CoreURLResourcePropertyKey<Boolean> VOLUME_IS_LOCAL = new CoreURLResourcePropertyKey<Boolean>();
    @CData("kCFURLVolumeIsReadOnlyKey")
    public static final CoreURLResourcePropertyKey<Boolean> VOLUME_IS_READ_ONLY = new
            CoreURLResourcePropertyKey<Boolean>();
    @CData("kCFURLVolumeCreationDateKey")
    public static final CoreURLResourcePropertyKey<CoreDate> VOLUME_CREATION_DATE = new
            CoreURLResourcePropertyKey<CoreDate>();
    @CData("kCFURLVolumeURLForRemountingKey")
    public static final CoreURLResourcePropertyKey<CoreURL> VOLUME_URL_FOR_REMOUNTING = new
            CoreURLResourcePropertyKey<CoreURL>();
    @CData("kCFURLVolumeUUIDStringKey")
    public static final CoreURLResourcePropertyKey<String> VOLUME_UUID_STRING = new
            CoreURLResourcePropertyKey<String>();
    @Header("CFURLAccess")
    @CData("kCFURLFileExists")
    public static final CoreURLResourcePropertyKey<Boolean> FILE_EXISTS = new CoreURLResourcePropertyKey<Boolean>();
    @Header("CFURLAccess")
    @CData("kCFURLFileDirectoryContents")
    public static final CoreURLResourcePropertyKey<List<CoreURL>> FILE_DIRECTORY_CONTENTS = new
            CoreURLResourcePropertyKey<List<CoreURL>>();
    @Header("CFURLAccess")
    @CData("kCFURLFileLength")
    public static final CoreURLResourcePropertyKey<Integer> FILE_LENGTH = new CoreURLResourcePropertyKey<Integer>();
    @Header("CFURLAccess")
    @CData("kCFURLFileLastModificationTime")
    public static final CoreURLResourcePropertyKey<CoreDate> FILE_LAST_MODIFICATION_TIME = new
            CoreURLResourcePropertyKey<CoreDate>();
    @Header("CFURLAccess")
    @CData("kCFURLFilePOSIXMode")
    public static final CoreURLResourcePropertyKey<Integer> FILE_POSIX_MODE = new CoreURLResourcePropertyKey<Integer>();
    @Header("CFURLAccess")
    @CData("kCFURLFileOwnerID")
    public static final CoreURLResourcePropertyKey<Integer> FILE_OWNER_ID = new CoreURLResourcePropertyKey<Integer>();
    @Header("CFURLAccess")
    @CData("kCFURLHTTPStatusCode")
    public static final CoreURLResourcePropertyKey<Integer> HTTP_STATUS_CODE = new CoreURLResourcePropertyKey<Integer>();
    @Header("CFURLAccess")
    @CData("kCFURLHTTPStatusLine")
    public static final CoreURLResourcePropertyKey<String> HTTP_STATUS_LINE = new CoreURLResourcePropertyKey<String>();

    private CoreURLResourcePropertyKey() {
        //empty
    }
}
