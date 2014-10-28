package persistence.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import core.MyLogger;

/**
 * @author excilys
 *
 */
@Component
public class LoggerRowMapper implements RowMapper<MyLogger> {

  /**
   * Get Companys from resultSet
   * @param resultSet
   * @return
   * @throws SQLException
   */
  @Override
  public MyLogger mapRow(final ResultSet resultSet, final int rowNum) throws SQLException {
    return MyLogger.builder().log(resultSet.getString("log")).id(resultSet.getInt("id"))
        .time(resultSet.getDate("time")).exception(new Exception(resultSet.getString("exception")))
        .build();
  }
}