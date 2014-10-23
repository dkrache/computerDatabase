package persistence.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import core.MyLogger;

/**
 * @author excilys
 *
 */
public final class LoggerRowMapper {

  private LoggerRowMapper() {

  }

  /**
   * Get List of COmpanys from resultSet
   * @param resultSet
   * @return
   * @throws SQLException
   */
  public static List<MyLogger> convertResultSet(final ResultSet resultSet) throws SQLException {
    final List<MyLogger> loggers = new ArrayList<>();
    while (resultSet.next()) {
      loggers.add(MyLogger.builder().log(resultSet.getString("log")).id(resultSet.getInt("id"))
          .time(resultSet.getDate("time"))
          .exception(new Exception(resultSet.getString("exception"))).build());
    }
    return loggers;
  }
}