package webapp.utils;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.Page;

public final class PageUtils {
  private static final Logger LOGGER = LoggerFactory.getLogger(PageUtils.class);

  /**
   * Constructor private for Util Class
   */
  private PageUtils() {
    //
  }

  /**
   * Create the page using the request 
   * @param request
   * @return
   */
  public static Page createPage(final HttpServletRequest request) {
    int currentPage = 0;
    int limit = 10;
    final String searchString = request.getParameter("searchString");
    final String order = request.getParameter("order");
    final String ascendancy = request.getParameter("ascendancy");
    try {
      currentPage = Integer.parseInt(request.getParameter("currentPage"));
    } catch (final NumberFormatException e) {
      LOGGER.debug("currentPage not parsed but optional. it may be absent.");
    }
    try {
      limit = Integer.parseInt(request.getParameter("limit"));
    } catch (final NumberFormatException e) {
      LOGGER.debug("limit not parsed but optional. it may be absent.");
    }

    final Page page = Page.builder().searchString(searchString).order(order).limit(limit)
        .currentPage(currentPage).ascendancy(ascendancy).build();
    request.setAttribute("page", page);
    return page;
  }
}
