package service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author excilys
 *
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ServiceException extends RuntimeException {

  /**
   * 
   */
  private static final long   serialVersionUID = 4675926754664457970L;
  private static final String DEFAULT_MESSAGE  = "back.message.error";

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
