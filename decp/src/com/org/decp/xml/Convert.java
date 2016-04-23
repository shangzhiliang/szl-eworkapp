package com.org.decp.xml;

import com.org.decp.xml.validate.ObjectHolder;

public abstract interface Convert<T>
{
  public abstract String toXml(ObjectHolder<T> paramObjectHolder);

  public abstract T toBean(String paramString, ObjectHolder<T> paramObjectHolder);

  public abstract ValidatorFactory<T> getValidatorFactory();
}
