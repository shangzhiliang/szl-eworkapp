package com.org.decp.xml.validate;

import java.util.Iterator;
import java.util.Set;

import javax.validation.ConstraintViolation;

import com.org.decp.xml.Validate;
import com.org.decp.xml.ValidateException;
import com.org.decp.xml.ValidatorFactory;

public class DefautValidate<T> extends HibernateValidate<T>
implements Validate<T>{
	  private DefautValidatorFactory<T> validatorFactory;
	  private Validate<T> validate;

	  public DefautValidate()
	  {
	  }

	  public DefautValidate(DefautValidatorFactory<T> validatorFactory)
	  {
	    this.validatorFactory = validatorFactory;
	    this.validate = validatorFactory.getValidate();
	  }

	  public DefautValidate(Validate<T> validate) {
	    this.validate = validate;
	    this.validatorFactory = new DefautValidatorFactory();
	  }

	  public boolean validate(T t) throws ValidateException {
	    Set error = violations(t);
	    if (error.isEmpty()) {
	      return true;
	    }

	    StringBuilder stringBuilder = new StringBuilder();
	    for (Iterator iterator = error.iterator(); iterator.hasNext(); ) {
	      ConstraintViolation constraintViolation = (ConstraintViolation)iterator.next();
	      stringBuilder.append(constraintViolation.getPropertyPath() + ":" + constraintViolation.getMessage() + System.getProperty("line.separator"));
	    }
	    throw new ValidateException(stringBuilder.toString());
	  }

	  public Validate<T> getValidate()
	  {
	    if (this.validate == null) {
	      return this.validatorFactory.getValidate();
	    }

	    return this.validate;
	  }

	  public ValidatorFactory<T> getValidatorFactory()
	  {
	    return this.validatorFactory;
	  }
}
