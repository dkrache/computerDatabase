package service.exception;

/**
 * @author excilys
 *
 */
public class ServiceException extends RuntimeException {

  /**
   * 
   */
  private static final long serialVersionUID = 4675926754664457970L;
  private static final String DEFAULT_MESSAGE  = "Impossible to evaluate your request. Please try later. ";

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
