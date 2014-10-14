package persistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author excilys
 *
 */
public class ConnectionDAO {
  private static final String DB_URL = "jdbc:mysql://localhost:3306/computer-database-db?zeroDateTimeBehavior=convertToNull";

  //  Database credentials
  private static final String USER   = "jee-cdb";
  private static final String PASS   = "password";

  /**
   * Création d'une nouvelle connection
   * @return
   * @throws ClassNotFoundException
   */
  public static Connection getConnection() {
    final Connection connection;
    try {
      Class.forName("com.mysql.jdbc.Driver");
      connection = DriverManager.getConnection(DB_URL, USER, PASS);
    } catch (final SQLException | ClassNotFoundException e) {
      e.printStackTrace();
      return null;
    }
    return connection;
  }

  /**
   * Fermeture de la connection 
   * @param connection
   */
  public static void closeConnection(final Connection connection) {
    try {
      connection.close();
    } catch (final SQLException e) {
      e.printStackTrace();
    }
  }
}
