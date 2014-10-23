package persistence;

import java.sql.Connection;

import org.springframework.stereotype.Repository;

/**
 * @author excilys
 *
 */
@Repository
public interface IConnectionDAO {
  /**
   * Create a new connection
   * @return connectiondataSource
   */
  Connection getConnection();

  /**
   * Commit and close the connection
   */
  void commitAndCloseConnection();

  /**
   * RollBack and close the connection
   */
  void rollbackAndCloseConnection();
}
