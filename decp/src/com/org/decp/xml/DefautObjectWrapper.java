package com.org.decp.xml;

import com.org.decp.xml.validate.DefautValidatorFactory;
import com.org.decp.xml.validate.ObjectHolder;

public class DefautObjectWrapper<T> {
	  private Validate<T> validate;
	  private Convert<T> convert;
	  private ValidatorFactory<T> validatorFactory;

	  public String toXml(ObjectHolder<T> objectHolder)
	    throws ValidateException
	  {
	    T t = objectHolder.getData();
	    if ((t != null) && (this.validate.validate(t)))
	    {
	      return this.convert.toXml(objectHolder);
	    }
	    return null;
	  }

	  public T toBean(String xmlStr, ObjectHolder<T> objectHolder)
	    throws ValidateException
	  {
	    T t = this.convert.toBean(xmlStr, objectHolder);

	    if (this.validate.validate(t))
	    {
	      return t;
	    }
	    
	    return null;
	  }

	  public DefautObjectWrapper(Convert<T> convert)
	  {
	    this.convert = convert;
	    this.validatorFactory = new DefautValidatorFactory();
	    this.validate = this.validatorFactory.getValidate();
	  }

	  public DefautObjectWrapper(Convert<T> convert, Validate<T> validate)
	  {
	    this.convert = convert;
	    this.validate = validate;
	    this.validatorFactory = validate.getValidatorFactory();
	  }

	  public DefautObjectWrapper(Convert<T> convert, ValidatorFactory<T> validatorFactory)
	  {
	    this.convert = convert;
	    this.validate = validatorFactory.getValidate();
	    this.validatorFactory = validatorFactory;
	  }

	  public ValidatorFactory<T> getValidatorFactory()
	  {
	    if (this.validatorFactory == null)
	    {
	      return this.validate.getValidatorFactory();
	    }

	    return this.validatorFactory;
	  }

	  public Validate<T> getValidate() {
	    return this.validate;
	  }

	  public void setValidate(Validate<T> validate) {
	    this.validate = validate;
	  }

	  public Convert<T> getConvert() {
	    return this.convert;
	  }

	  public void setConvert(Convert<T> convert) {
	    this.convert = convert;
	  }

	  public void setValidatorFactory(ValidatorFactory<T> validatorFactory)
	  {
	    this.validatorFactory = validatorFactory;
	  }
}
