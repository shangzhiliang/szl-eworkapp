package com.org.decp.xml;

public abstract interface ValidatorFactory<T> {
	
	  public abstract Validate<T> getValidate();

	  public abstract DefautObjectWrapper<T> getObjectWrapper();
	
}
