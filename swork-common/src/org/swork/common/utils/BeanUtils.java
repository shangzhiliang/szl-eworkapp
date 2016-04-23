package org.swork.common.utils;

import java.lang.reflect.InvocationTargetException;

public class BeanUtils
{
  public static void copyProperties(Object dest, Object orig)
    throws IllegalAccessException, InvocationTargetException
  {
    org.apache.commons.beanutils.BeanUtils.copyProperties(dest, orig);
  }
}