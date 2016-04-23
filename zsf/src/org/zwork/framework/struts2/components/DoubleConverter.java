package org.zwork.framework.struts2.components;

import java.util.Map;

import org.apache.commons.beanutils.converters.BooleanConverter;
import org.apache.struts2.util.StrutsTypeConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DoubleConverter extends StrutsTypeConverter
{
  private static final Logger LOGGER = LoggerFactory.getLogger(BooleanConverter.class);

  public Object convertFromString(Map context, String[] values, Class toClass)
  {
    if (Double.class == toClass) {
      String doubleStr = values[0];
      LOGGER.debug("获取到的字符串为[{}]", doubleStr);
      Double d = Double.valueOf(Double.parseDouble(doubleStr));
      return d;
    }
    return Integer.valueOf(0);
  }

  public String convertToString(Map context, Object o)
  {
    return o.toString();
  }
}