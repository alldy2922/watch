package com.datn.watch.common.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

/**
 * @author tobi
 * @desc : ...
 */
public class DateUtils {

  public static Date getDate(String dateString, String dateFormat) {
    try {
      return new SimpleDateFormat(dateFormat).parse(dateString);
    } catch (ParseException e) {
      return null;
    }
  }

  public static Instant calculateExpiryDate(int expireMinute) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(new Timestamp(cal.getTime().getTime()));
    cal.add(Calendar.MINUTE, expireMinute);
    return cal.toInstant();
  }
}
