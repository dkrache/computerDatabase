package persistence;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

/**
 * @author excilys
 *
 */
public final class ConnectionDAO {
  private static final Properties PROPERTIES = new Properties();
  private static final String     JDBC_DRIVER;
  private static final String     DB_URL;
  private static final String     USER;
  private static final String     PASS;
  private static final Logger     LOGGER     = LoggerFactory.getLogger(ConnectionDAO.class);
  private static final BoneCP     connectionPool;

  //  Database credentials

  private ConnectionDAO() {
    super();
  }

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
    try {
      /*
       * Création d'une configuration de pool de connexions via l'objet
       * BoneCPConfig et les différents setters associés.
       */
      final BoneCPConfig configuration = new BoneCPConfig();
      /* Mise en place de l'URL, du nom et du mot de passe */
      configuration.setJdbcUrl(DB_URL);
      configuration.setUsername(USER);
      configuration.setPassword(PASS);
      /* Paramétrage de la taille du pool */
      configuration.setMinConnectionsPerPartition(Integer.parseInt(PROPERTIES
          .getProperty("jdbc.MinConnectionsPerPartition")));
      configuration.setMaxConnectionsPerPartition(Integer.parseInt(PROPERTIES
          .getProperty("jdbc.MaxConnectionsPerPartition")));
      configuration.setPartitionCount(Integer.parseInt(PROPERTIES
          .getProperty("jdbc.PartitionCount")));
      /* Création du pool à partir de la configuration, via l'objet BoneCP */
      connectionPool = new BoneCP(configuration);
    } catch (final SQLException e) {
      LOGGER.warn("Error with the configuration of the connection pool : {}", e);
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
      return connectionPool.getConnection();
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
