package webapp.utils;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;

import core.Page;

public final class PageUtils {
  private static final Logger LOGGER = LoggerFactory.getLogger(PageUtils.class);

  /**
   * Constructor private for Util Class
   */
  private PageUtils() {
    //
  }

  public static Page createPage(final ModelMap model) {
    int currentPage = 0;
    int limit = 10;
    final String searchString = (String) model.get("searchString");
    final String order = (String) model.get("order");
    final String ascendancy = (String) model.get("ascendancy");
    try {
      currentPage = Integer.parseInt((String) model.get("currentPage"));
    } catch (final NumberFormatException e) {
      LOGGER.debug("currentPage not parsed but optional. it may be absent.");
    }
    try {
      limit = Integer.parseInt((String) model.get("limit"));
    } catch (final NumberFormatException e) {
      LOGGER.debug("limit not parsed but optional. it may be absent.");
    }

    final Page page = Page.builder().searchString(searchString).order(order).limit(limit)
        .currentPage(currentPage).ascendancy(ascendancy).build();
    model.addAttribute("page", page);
    return page;
  }

  public static Page createPage(final HttpServletRequest request) {
    int currentPage = 0;
    int limit = 10;
    final String searchString = (String) request.getParameter("searchString");
    final String order = (String) request.getParameter("order");
    final String ascendancy = (String) request.getParameter("ascendancy");
    try {
      currentPage = Integer.parseInt((String) request.getParameter("currentPage"));
    } catch (final NumberFormatException e) {
      LOGGER.debug("currentPage not parsed but optional. it may be absent.");
    }
    try {
      limit = Integer.parseInt((String) request.getParameter("limit"));
    } catch (final NumberFormatException e) {
      LOGGER.debug("limit not parsed but optional. it may be absent.");
    }

    final Page page = Page.builder().searchString(searchString).order(order).limit(limit)
        .currentPage(currentPage).ascendancy(ascendancy).build();
    request.setAttribute("page", page);
    return page;
  }
}
