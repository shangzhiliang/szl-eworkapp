package org.swork.common.utils;

import java.io.File;

public class SystemUtils {
    public static final String FILE_ENCODING = org.apache.commons.lang.SystemUtils.FILE_ENCODING;

    public static final String FILE_SEPARATOR = org.apache.commons.lang.SystemUtils.FILE_SEPARATOR;

    public static final String LINE_SEPARATOR = org.apache.commons.lang.SystemUtils.LINE_SEPARATOR;

    public static final String USER_DIR = org.apache.commons.lang.SystemUtils.USER_DIR;

    public static File getJavaIoTmpDir() {
      return org.apache.commons.lang.SystemUtils.getJavaHome();
    }
}
