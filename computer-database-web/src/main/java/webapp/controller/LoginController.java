package webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Servlet implementation class ComputerCrudServlet
 */
@Controller
@RequestMapping("/login")
public class LoginController {

  /**
   * @param error
   * @param logout
   * @return
   */
  @RequestMapping(method = RequestMethod.GET)
  public ModelAndView login(@RequestParam(value = "error", required = false)
  final String error, @RequestParam(value = "logout", required = false)
  final String logout) {

    final ModelAndView model = new ModelAndView();
    if (error != null) {
      model.addObject("error", "Invalid username and password!");
    }

    if (logout != null) {
      model.addObject("msg", "You've been logged out successfully.");
    }
    model.setViewName("login");

    return model;

  }

}
