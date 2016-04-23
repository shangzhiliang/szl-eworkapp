package org.swork.common.utils;

import java.io.UnsupportedEncodingException;


public class StringUtils
{
  public static boolean isEmpty(String string)
  {
    if ((string == null) || ("".equals(string.trim()))) {
      return true;
    }
    return false;
  }

  public static boolean isNotEmpty(String string) {
    return !isEmpty(string);
  }

  public static String leftPad(String str, int size, char padChar)
  {
    return org.apache.commons.lang.StringUtils.leftPad(str, size, padChar);
  }

  public static int length(String string)
  {
    if (string == null)
      return 0;
    try
    {
      return new String(string.getBytes("GBK"), "8859_1").length(); } catch (UnsupportedEncodingException e) {
    }
    return -1;
  }
}