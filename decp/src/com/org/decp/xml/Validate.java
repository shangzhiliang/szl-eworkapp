package com.org.decp.xml;

public abstract interface Validate<T>
{
  public abstract boolean validate(T paramT)
    throws ValidateException;

  public abstract Validate<T> getValidate();

  public abstract ValidatorFactory<T> getValidatorFactory();
}