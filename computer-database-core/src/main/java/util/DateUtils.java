package util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * @author excilys
 *
 */
public final class DateUtils {

  private static final String FORMAT_DD_MM_YYYY = "dd/MM/yyyy";

  /**
   * private constructor for util class
   */
  private DateUtils() {

  }

  /**
   * Get the calendar of a String 
   * @param date
   * @return
   * @throws ParseException
   */
  public static Calendar createStringToCalendar(final String date) throws ParseException {
    final SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT_DD_MM_YYYY);
    final Calendar cal = GregorianCalendar.getInstance();
    cal.setTime(dateFormat.parse(date));
    return cal;
  }

  /**
   * Get a String date of a Calendar
   * @param calendar
   * @return
   * @throws ParseException
   */
  public static String createCalendarToString(final Calendar calendar) throws ParseException {
    final SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT_DD_MM_YYYY);
    dateFormat.setCalendar(calendar);
    return dateFormat.format(calendar.getTime());
  }

  /**
   * create the DateTime with a date
   * @param date
   * @return
   * @throws ParseException
   */
  public static DateTime createStringToDate(final String date) throws ParseException {
    final DateTimeFormatter formatter = DateTimeFormat.forPattern(FORMAT_DD_MM_YYYY);
    return formatter.parseDateTime(date);
  }

  /**
   * @param dateTime
   * @return
   */
  public static String createDateToString(final DateTime dateTime) {
    if (dateTime == null) {
      return "";
    }
    return dateTime.toString(FORMAT_DD_MM_YYYY);

  }

  public static DateTime getDate(final Timestamp timestamp) {
    if (timestamp == null) {
      return null;
    }
    // Turn it into a Joda DateTime with time zone.
    return new DateTime(timestamp);
  }

}
