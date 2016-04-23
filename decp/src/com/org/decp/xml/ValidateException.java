package com.org.decp.xml;

public class ValidateException extends Exception {
	private static final long serialVersionUID = 1L;
	  private String message;

	  public ValidateException()
	  {
	  }

	  public ValidateException(String message)
	  {
	    super(message);
	    this.message = message;
	  }

	  public String getMessage()
	  {
	    return this.message;
	  }

	  public void setMessage(String message) {
	    this.message = message;
	  }
}
