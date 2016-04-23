package com.org.decp.xml.xstream;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.thoughtworks.xstream.converters.ConversionException;
import com.thoughtworks.xstream.converters.basic.AbstractSingleValueConverter;

public class XStreamDateConverter extends AbstractSingleValueConverter
{
  private static final DateFormat DEFAULT_DATEFORMAT = new SimpleDateFormat(
    "yyyy-mm-dd hh:mm:ss.mmm ");

  public boolean canConvert(Class type)
  {
    return type.equals(Date.class);
  }

  public Object fromString(String str)
  {
    try
    {
      return DEFAULT_DATEFORMAT.parseObject(str);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    throw new ConversionException("Cannot parse date " + str);
  }

  public String toString(Object obj)
  {
    return DEFAULT_DATEFORMAT.format((Date)obj);
  }

  public static void main(String[] args)
  {
    XStreamDateConverter ccConverter = new XStreamDateConverter();

    System.out.println(ccConverter.toString(new Date()));
  }
}