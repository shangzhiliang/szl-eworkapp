package org.swork.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtils
{
  @SuppressWarnings("unused")
private static final String defaultDateFormat = "yyyy-MM-dd";
  @SuppressWarnings("unused")
private static final String defaultDateTimeFormat = "yyyy-MM-dd HH:mm:ss";
  public static final int DATE_UNIT_YEAR = 0;
  public static final int DATE_UNIT_MONTH = 1;
  public static final int DATE_UNIT_DAY = 2;

  public static final String dateToString(Date date)
  {
    if (date == null) {
      return null;
    }
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    return dateFormat.format(date);
  }

  public static final Date stringToDate(String dateStr)
  {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date date = null;
    try {
      date = dateFormat.parse(formatDate(dateStr));
    } catch (ParseException e) {
      throw new IllegalArgumentException("日期[" + dateStr + "]转换失败!", e);
    }
    return date;
  }

  public static final String dateTimeToString(Date date)
  {
    if (date == null) {
      return null;
    }
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    return dateFormat.format(date);
  }

  public static final Date stringToDateTime(String dateTimeStr)
  {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date date = null;
    try {
      date = dateFormat.parse(dateTimeStr);
    } catch (ParseException e) {
      throw new IllegalArgumentException("日期[" + dateTimeStr + "]转换失败!", e);
    }
    return date;
  }

  public static final int dateDiff(int unit, String startDate, String endDate)
  {
    startDate = formatDate(startDate);
    endDate = formatDate(endDate);
    if (unit == 0) {
      int startYear = Integer.parseInt(startDate.substring(0, 4));
      int endYear = Integer.parseInt(endDate.substring(0, 4));
      return endYear - startYear;
    }if (unit == 1) {
      int elapsed = 0;
      GregorianCalendar start = new GregorianCalendar(); GregorianCalendar end = new GregorianCalendar();

      start.set(1, Integer.parseInt(startDate.substring(0, 4)));
      start.set(2, Integer.parseInt(startDate.substring(5, 7)));

      end.set(1, Integer.parseInt(endDate.substring(0, 4)));
      end.set(2, Integer.parseInt(endDate.substring(5, 7)));
      if (end.after(start))
        while (start.before(end)) {
          start.add(2, 1);
          elapsed++;
        }
      else {
        while (end.before(start)) {
          end.add(2, 1);
          elapsed--;
        }
      }
      return elapsed;
    }if (unit == 2) {
      int elapsed = 0;
      GregorianCalendar start = new GregorianCalendar(); GregorianCalendar end = new GregorianCalendar();
      start.set(1, Integer.parseInt(startDate.substring(0, 4)));
      start.set(2, Integer.parseInt(startDate.substring(5, 7)) - 1);
      start.set(5, Integer.parseInt(startDate.substring(8)));

      end.set(1, Integer.parseInt(endDate.substring(0, 4)));
      end.set(2, Integer.parseInt(endDate.substring(5, 7)) - 1);
      end.set(5, Integer.parseInt(endDate.substring(8)));
      if (end.after(start))
        while (start.before(end)) {
          start.add(5, 1);
          elapsed++;
        }
      else {
        while (end.before(start)) {
          end.add(5, 1);
          elapsed--;
        }
      }
      return elapsed;
    }
    return 0;
  }

  public static final String dateDiff(Date startDate, Date endDate, String format)
  {
    GregorianCalendar start = new GregorianCalendar(); GregorianCalendar end = new GregorianCalendar();
    start.setTime(startDate);
    end.setTime(endDate);
    long diff = Math.abs(end.getTimeInMillis() - start.getTimeInMillis()) / 1000L;
    long days = -1L; long hours = -1L; long minutes = -1L; long seconds = -1L;
    if (format != null) {
      if (format.indexOf("d") >= 0) {
        days = diff / 86400L;
        diff %= 86400L;
        format = format.replaceAll("d", String.valueOf(days));
      }
      if (format.indexOf("h") >= 0) {
        hours = diff / 3600L;
        diff %= 3600L;
        format = format.replaceAll("h", String.valueOf(hours));
      }
      if (format.indexOf("m") >= 0) {
        minutes = diff / 60L;
        diff %= 60L;
        format = format.replaceAll("m", String.valueOf(minutes));
      }
      if (format.indexOf("s") >= 0) {
        seconds = diff;
        format = format.replaceAll("s", String.valueOf(seconds));
      }
    }
    if (start.after(end)) {
      format = "-" + format;
    }
    return format;
  }

  public static final int dateDiff(Date startDate, Date endDate)
  {
    GregorianCalendar start = new GregorianCalendar();
    GregorianCalendar end = new GregorianCalendar();
    start.setTime(startDate);
    end.setTime(endDate);
    return end.compareTo(start);
  }

  public static final String formatDate(String dateStr)
  {
    dateStr = dateStr.replace('.', '-').replace('/', '-');

    int firstIndex = dateStr.indexOf("-");
    int lastIndex = dateStr.lastIndexOf("-");
    if ((firstIndex != 4) || (lastIndex < firstIndex)) {
      throw new IllegalArgumentException("不支持此日期值[" + dateStr + "],日期格式化失败，!");
    }
    String year = dateStr.substring(0, firstIndex);
    String month = dateStr.substring(firstIndex + 1, lastIndex);
    String day = dateStr.substring(lastIndex + 1);
    return year + "-" + (month.length() < 2 ? "0" + month : month) + "-" + (day.length() < 2 ? "0" + day : day);
  }

  public static final String nDaysAfter(String dateStr, int n)
  {
    long addTime = 86400000L * n;
    Date date = stringToDate(dateStr);
    return dateToString(new Date(date.getTime() + addTime));
  }

  public static final String nDaysBefore(String dateStr, int n)
  {
    long minusTime = 86400000L * n;
    Date date = stringToDate(dateStr);
    return dateToString(new Date(date.getTime() - minusTime));
  }

  public static final String nHoursAfter(String dateStr, int n)
  {
    long addTime = 3600000L * n;
    Date date = stringToDateTime(dateStr);
    return dateTimeToString(new Date(date.getTime() + addTime));
  }

  public static final boolean isWeekend(String str)
  {
    boolean flag = false;
    GregorianCalendar calendar = new GregorianCalendar();
    calendar.setTime(stringToDate(str));
    int dayOfWeek = calendar.get(7);
    if ((dayOfWeek == 1) || (dayOfWeek == 7)) {
      flag = true;
    }
    return flag;
  }

  public static final String getDayOfWeekName(String str)
  {
    String[] dayNames = { "星期日", "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
    GregorianCalendar calendar = new GregorianCalendar();
    calendar.setTime(stringToDate(str));
    int dayOfWeek = calendar.get(7);
    return dayNames[dayOfWeek];
  }
}