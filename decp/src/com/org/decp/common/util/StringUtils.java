package com.org.decp.common.util;

public class StringUtils {
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
}
