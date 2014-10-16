package persistence;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author excilys
 *
 */
public class ConnectionDAO {
  private static final Properties PROPERTIES = new Properties();
  private static final String     JDBC_DRIVER;
  private static final String     DB_URL;
  private static final String     USER;
  private static final String     PASS;
  private static final Logger     LOGGER     = LoggerFactory.getLogger(ConnectionDAO.class);
  //  Database credentials

  static {
    // Initialization when JVM start.
    try {
      // load a properties file
      PROPERTIES.load(Thread.currentThread().getContextClassLoader()
          .getResourceAsStream("jdbc.properties"));
    } catch (final IOException e) {
      LOGGER.warn("Error: Impossible to load the properties jdbc: {}", e);
      throw new RuntimeException(e);
    }

    // Initialization of the constant
    JDBC_DRIVER = PROPERTIES.getProperty("jdbc.driverClassName");
    DB_URL = PROPERTIES.getProperty("jdbc.url");
    USER = PROPERTIES.getProperty("jdbc.username");
    PASS = PROPERTIES.getProperty("jdbc.password");

    try {
      Class.forName(JDBC_DRIVER);
    } catch (final ClassNotFoundException e) {
      LOGGER.warn("Error: Impossible to load the driver jdbc: {}", e);
      throw new RuntimeException(e);
    }
  }

  /**
   * Create a new connection
   * @return
   * @throws ClassNotFoundException
   */
  public static Connection getConnection() {
    try {
      return DriverManager.getConnection(DB_URL, USER, PASS);
    } catch (final SQLException e) {
      LOGGER.warn("Error while getting connection: {}", e);
      throw new RuntimeException(e);
    }
  }

  /**
   * Close the connection
   * @param connection
   */
  public static void closeConnection(final Connection connection) {
    try {
      if (connection != null) {
        connection.close();
      }
    } catch (final SQLException e) {
      LOGGER.warn("Error while closing connection: {}", e);
      throw new RuntimeException(e);
    }
  }
}
