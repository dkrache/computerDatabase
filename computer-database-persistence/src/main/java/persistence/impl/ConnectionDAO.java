package persistence.impl;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import persistence.IConnectionDAO;

/**
 * @author excilys
 *
 */
@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class ConnectionDAO implements IConnectionDAO {
  private static final Logger                  LOGGER            = LoggerFactory
                                                                     .getLogger(ConnectionDAO.class);
  @Autowired
  private DataSource                           dataSource;
  private static final ThreadLocal<Connection> THREAD_CONNECTION = new ThreadLocal<>();

  /* (non-Javadoc)
   * @see persistance.IConnectionDAO#getConnection()
   */
  @Override
  public Connection getConnection() {
    try {
      if (THREAD_CONNECTION.get() == null) {
        final Connection connection = dataSource.getConnection();
        THREAD_CONNECTION.set(connection);
      }
      return THREAD_CONNECTION.get();
    } catch (final SQLException e) {
      LOGGER.warn("Error while getting connection: {}", e);
      throw new RuntimeException(e);
    }
  }

  /* (non-Javadoc)
   * @see persistance.IConnectionDAO#commitAndCloseConnection()
   */
  @Override
  public void closeConnection() {
    try {
      if (THREAD_CONNECTION.get() != null) {
        THREAD_CONNECTION.get().close();
        THREAD_CONNECTION.remove();
      }
    } catch (final SQLException e) {
      LOGGER.warn("Error while closing connection: {}", e);
      throw new RuntimeException(e);
    }
  }

}
