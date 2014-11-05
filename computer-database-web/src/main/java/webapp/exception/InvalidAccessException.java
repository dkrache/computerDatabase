package webapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class InvalidAccessException extends RuntimeException {

  /**
   * 
   */
  private static final long serialVersionUID = -4080047949703396493L;

}
