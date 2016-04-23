package com.org.decp.xml.validate;

import com.org.decp.xml.DefautObjectWrapper;
import com.org.decp.xml.Validate;
import com.org.decp.xml.ValidatorFactory;

public class DefautValidatorFactory<T> implements ValidatorFactory<T>
{
	public Validate<T> getValidate()
	{
	  return new DefautValidate();
	}

	public DefautObjectWrapper<T> getObjectWrapper()
	{
	  return getObjectWrapper();
	}
}
