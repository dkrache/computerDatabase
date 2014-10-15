package service.exception;

/**
 * @author excilys
 *
 */
public class ServiceException extends Exception {

  /**
   * 
   */
  private static final long   serialVersionUID = 4675926754664457970L;
  private static final String DEFAULT_MESSAGE  = "hum, c'est embarrassant. Nos services rencontre des difficultés à répondre à votre requête";

  /**
   * 
   */
  public ServiceException() {
    super(DEFAULT_MESSAGE);
  }

  /**
   * @param e
   */
  public ServiceException(final Throwable e) {
    super(DEFAULT_MESSAGE, e);
  }

  /**
   * @param message
   * @param e
   */
  public ServiceException(final String message, final Throwable e) {
    super(message, e);
  }

}
