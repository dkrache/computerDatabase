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
   * close the connection
   */
  void closeConnection();

}
