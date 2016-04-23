package com.org.decp.key;

import java.util.Date;

import com.org.decp.common.util.DateTimeUtil;

public class KeyGeneratorContext {
	protected String currentDate;

	  public String getCurrentDate()
	  {
	    return DateTimeUtil.timeToStr(new Date());
	  }
}
