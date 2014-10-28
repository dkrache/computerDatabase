package persistence.exception;

import java.sql.SQLException;

public class PersistenceException extends SQLException {
  /**
   * 
   */
  private static final long serialVersionUID = -5850320959920083900L;

  public PersistenceException() {
    super();
  }

  public PersistenceException(final Throwable e) {
    super(e);
  }

}
