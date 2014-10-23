package webapp.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import service.exception.ServiceException;
import webapp.exception.BadRequestException;
import webapp.exception.ResourceNotFoundException;

/**
 * @author excilys
 *
 */
@ControllerAdvice
public class ExceptionHandlingController {

  @ExceptionHandler(BadRequestException.class)
  public String badRequestError() {
    return "error/400";
  }

  @ExceptionHandler(value = ResourceNotFoundException.class)
  public ModelAndView resourceNotFoundError() {
    return new ModelAndView("error/404");
  }

  @ExceptionHandler(ServiceException.class)
  public String internalError() {
    return "error/500";
  }
}
