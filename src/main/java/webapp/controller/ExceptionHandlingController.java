package webapp.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import service.exception.ServiceException;
import webapp.exception.BadRequestException;
import webapp.exception.ResourceNotFoundException;

/**
 * @author excilys
 *
 */
@ControllerAdvice
@RequestMapping("/**")
public class ExceptionHandlingController {

  @RequestMapping(method = RequestMethod.GET)
  protected String resourceNotFoundGet() {
    throw new ResourceNotFoundException();
  }

  @RequestMapping(method = RequestMethod.POST)
  protected String resourceNotFoundPost() {
    throw new ResourceNotFoundException();
  }

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
