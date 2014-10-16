package binding;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public final class DateUtils {

  private DateUtils() {

  }

  public static Calendar createStringToCalendar(final String date) throws ParseException {
    final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    final Calendar cal = GregorianCalendar.getInstance();
    cal.setTime(dateFormat.parse(date));
    return cal;
  }

  public static String createCalendarToString(final Calendar calendar) throws ParseException {
    final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    dateFormat.setCalendar(calendar);
    return dateFormat.format(calendar.getTime());
  }

  public static Date createStringToDate(final String date) throws ParseException {
    final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    final Calendar cal = GregorianCalendar.getInstance();
    cal.setTime(dateFormat.parse(date));
    return cal.getTime();
  }

  public static String createDateToString(final Date date) {
    if (date == null) {
      return null;
    }
    final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    return dateFormat.format(date);

  }

  public static Date getDate(final Timestamp timestamp) {
    if (timestamp == null) {
      return null;
    }
    return new Date(timestamp.getTime());
  }

  public static Date getDate(final long timeInMillis) {
    if (timeInMillis <= 0) {
      return null;
    }
    return new Date(timeInMillis);
  }

}
