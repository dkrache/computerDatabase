package webapp.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import service.exception.ServiceException;
import webapp.exception.BadRequestException;
import webapp.exception.InvalidAccessException;
import webapp.exception.ResourceNotFoundException;

/**
 * @author excilys
 *
 */
@ControllerAdvice
@RequestMapping("/**")
public class ExceptionHandlingController {

  /**
   * @return
   */
  @RequestMapping(method = RequestMethod.GET)
  protected String resourceNotFoundGet() {
    throw new ResourceNotFoundException();
  }

  /**
   * @return
   */
  @RequestMapping(method = RequestMethod.POST)
  protected String resourceNotFoundPost() {
    throw new ResourceNotFoundException();
  }

  @RequestMapping(value = "/403", method = RequestMethod.GET)
  protected String invalidAccess() {
    throw new InvalidAccessException();
  }

  /**
   * @return
   */
  @ExceptionHandler(BadRequestException.class)
  public String badRequestError() {
    return "error/400";
  }

  /**
   * @return
   */
  @ExceptionHandler(value = ResourceNotFoundException.class)
  public ModelAndView resourceNotFoundError() {
    return new ModelAndView("error/404");
  }

  /**
   * @return
   */
  @ExceptionHandler(value = InvalidAccessException.class)
  public String invalidAccessError() {
    return "error/403";
  }

  /**
   * @return
   */
  @ExceptionHandler(ServiceException.class)
  public String internalError() {
    return "error/500";
  }
}
