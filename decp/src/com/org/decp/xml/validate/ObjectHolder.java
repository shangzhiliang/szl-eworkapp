package com.org.decp.xml.validate;

import com.org.decp.xml.ValidateException;


public abstract interface ObjectHolder<T>
{
  public abstract String beanToXml()
    throws ValidateException;

  public abstract Object xmlToBean(String paramString)
    throws ValidateException;

  public abstract T getData();
}