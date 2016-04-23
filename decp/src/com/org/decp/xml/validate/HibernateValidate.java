package com.org.decp.xml.validate;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.org.decp.xml.Validate;

public abstract class HibernateValidate<T>
implements Validate<T>
{
	protected Set<ConstraintViolation<T>> violations(T t)
	{
	  ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
	  Validator validator = validatorFactory.getValidator();
	  return validator.validate(t, new Class[0]);
	}
}