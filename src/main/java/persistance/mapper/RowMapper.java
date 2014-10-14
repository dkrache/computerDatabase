package persistance.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import persistance.exception.PersistenceException;

/**
 * @author excilys
 *
 * @param <T>
 */
public interface RowMapper<T> {
  /**
   * @param resultSet
   * @return
   * @throws SQLException
   * @throws PersistenceException
   */
  List<T> convertResultSet(final ResultSet resultSet) throws SQLException, PersistenceException;
}
