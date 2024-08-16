package io.github.px86.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateUtil {

  public static String timeStringOf(Date date) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
    sdf.setTimeZone(TimeZone.getTimeZone("IST"));
    return sdf.format(date);
  }

  public static Date dateOf(String timeString) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
    sdf.setTimeZone(TimeZone.getTimeZone("IST"));
    try {
      Date date = sdf.parse(timeString);
      return date;
    } catch (ParseException e) {
      return null;
    }
  }
}
