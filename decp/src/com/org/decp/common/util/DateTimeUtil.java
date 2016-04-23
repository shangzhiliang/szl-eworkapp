package com.org.decp.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateTimeUtil {
	 private static String deaultDateFormat = "yyyy-MM-dd";

	  private static String deaultDateTimeFormat = "yyyy-MM-dd HH:mm:ss";

	  private static String millDateTimeFormat = "yyyy-MM-dd HH:mm:ss.SSS";
	  private static String deaultDateFormat2 = "yyyyMMdd";

	  public static Date getCurrentWorkDate()
	  {
	    return new Date();
	  }

	  public static String dateToOriStr(Date date)
	  {
	    if (date == null)
	      return "1900-01-01";
	    SimpleDateFormat dateFormat = new SimpleDateFormat(deaultDateFormat);

	    return dateFormat.format(date);
	  }

	  public static String dateToOriStr2(Date date) {
	    if (date == null)
	      return "19000101";
	    SimpleDateFormat dateFormat = new SimpleDateFormat(deaultDateFormat2);

	    return dateFormat.format(date);
	  }

	  public static String dateToStr(Date date)
	  {
	    String retDate = dateToOriStr(date);
	    if (retDate.equals("1900-01-01")) {
	      retDate = "";
	    }
	    return retDate;
	  }

	  public static String formatDate(String dt)
	  {
	    String retDt = "";
	    try {
	      if ((dt == null) || (dt.length() == 0)) {
	        return "";
	      }
	      String year = "";
	      String month = "";
	      String date = "";
	      int idx = dt.indexOf("-", 5);
	      if (idx == -1)
	        return "";
	      year = dt.substring(0, 4);
	      month = dt.substring(5, idx);
	      if (month.length() == 1)
	        month = "0" + month;
	      date = dt.substring(idx + 1);
	      if (date.length() == 1)
	        date = "0" + date;
	      return year + "-" + month + "-" + date;
	    }
	    catch (Exception e) {
	    }
	    return "";
	  }

	  static String timeToStr()
	  {
	    return timeToStr(new Date());
	  }

	  public static String timeToStr(Date time) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat(deaultDateTimeFormat);
	    return dateFormat.format(time);
	  }

	  public static String timeToStrMill(Date time)
	  {
	    SimpleDateFormat dateFormat = new SimpleDateFormat(millDateTimeFormat);
	    return dateFormat.format(time);
	  }

	  public static String getCurrentDate()
	  {
	    return dateToOriStr(new Date());
	  }

	  public static String getYesTodayDate()
	  {
	    Calendar calendar = Calendar.getInstance();
	    calendar.add(5, -1);
	    String yestedayDate = new SimpleDateFormat(deaultDateFormat).format(calendar.getTime());
	    return yestedayDate;
	  }

	  public static int getYear(Date d)
	  {
	    Calendar cl = Calendar.getInstance();
	    cl.setTime(d);
	    return cl.get(1);
	  }

	  public static String getNeedDay(String format, int index)
	  {
	    Date date = new Date();
	    GregorianCalendar gc = new GregorianCalendar();
	    SimpleDateFormat sf = new SimpleDateFormat(format);
	    gc.setTime(date);
	    gc.add(5, index);
	    return sf.format(gc.getTime());
	  }

	  public static String getJidu(int month)
	  {
	    int season = 1;
	    if ((month >= 1) && (month <= 3)) {
	      season = 1;
	    }
	    if ((month >= 4) && (month <= 6)) {
	      season = 2;
	    }
	    if ((month >= 7) && (month <= 9)) {
	      season = 3;
	    }
	    if ((month >= 10) && (month <= 12)) {
	      season = 4;
	    }
	    return String.valueOf(season);
	  }

	  public static String getSystemSimpleDate(String stringformat)
	  {
	    SimpleDateFormat sdf = new SimpleDateFormat(stringformat);

	    Date date = new Date();

	    String systime = sdf.format(date);

	    return systime;
	  }

	  public static long calcSeconds(String startDate, String endDate)
	    throws ParseException
	  {
	    String format = "yyyy-MM-dd hh:mm:ss";
	    SimpleDateFormat sf = new SimpleDateFormat(format);
	    Date sDate = sf.parse(startDate);
	    Date eDate = sf.parse(endDate);
	    return (eDate.getTime() - sDate.getTime()) / 1000L;
	  }

	  public static Date addSeconds(Date date, int second)
	  {
	    Date newDate = null;
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(date);
	    calendar.add(13, second);
	    newDate = calendar.getTime();
	    return newDate;
	  }

	  public static String getnextTime(String nowtime)
	  {
	    String nexttime = "";
	    if ((nowtime == null) || ("".equals(nowtime))) {
	      nexttime = getCurrentDate();
	      return nexttime;
	    }
	    if (nowtime.length() >= 10) {
	      String year = nowtime.substring(0, 4);
	      String monthday = "-12-31";
	      int nextyear = Integer.parseInt(year) + 1;
	      nexttime = String.valueOf(nextyear) + monthday;
	    }
	    return nexttime;
	  }

	  public static long getTimeLong(Date date)
	  {
	    long a = 0L;
	    Calendar cl = Calendar.getInstance();
	    cl.setTime(date);
	    a = cl.getTimeInMillis();
	    return a;
	  }
}
