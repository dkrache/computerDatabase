package core;

public class Page {
  private static final String PARAM_ORDER         = "&order=";
  private static final String PARAM_ASCENDANCY    = "&ascendancy=";
  private static final String PARAM_LIMIT         = "limit=";
  private static final String PARAM_CURRENT_PAGE  = "&currentPage=";
  private static final String PARAM_SEARCH_STRING = "&searchString=";
  public static final String  UP                  = "up";
  public static final String  DOWN                = "down";
  private int                 limit;
  private int                 currentPage;
  private long                totalCount;
  private String              searchString;
  private String              order;
  private String              ascendancy;

  /**
   * @param limit
   * @param currentPageCount
   * @param totalCount
   * @param searchString
   */
  protected Page(final int limit, final int currentPage, final int totalCount,
      final String searchString, final String order) {
    super();
    this.limit = limit;
    this.currentPage = currentPage;
    this.totalCount = totalCount;
    this.searchString = searchString;
    this.order = order;

  }

  /**
   * Default constructor
   */
  public Page() {
    this(10, 0, 0, "", "");
  }

  /**
   * @return theCount nbPages
   */
  public long getNbPages() {

    return limit <= 0 ? 1 : totalCount / limit;
  }

  /**
   * @return the pageSize  private static final String UP = "up";
  private static final String DOWN = "down";
   */
  public int getLimit() {
    return limit;
  }

  /**
   * @return the currentPage
   */
  public int getCurrentPage() {
    return currentPage;
  }

  /**
   * @return the totalCount
   */
  public long getTotalCount() {
    return totalCount;
  }

  /**
   * @return the order
   */
  public String getOrder() {
    return order;
  }

  /**
   * @return the searchString
   */
  public String getSearchString() {
    return searchString;
  }

  /**
   * @return
   */
  public int getOffset() {
    return currentPage * limit;
  }

  /**
   * @param l the totalCount to set
   */
  public void setTotalCount(final long l) {
    this.totalCount = l;
  }

  public static Builder builder() {
    return new Builder();
  }

  public String getBackLink() {
    return new StringBuilder().append(PARAM_LIMIT).append(limit).append(PARAM_CURRENT_PAGE)
        .append(currentPage - 1).append(PARAM_SEARCH_STRING).append(searchString)
        .append(PARAM_ORDER).append(order).append(PARAM_ASCENDANCY).append(ascendancy).toString();

  }

  public String getnextLink() {
    return new StringBuilder().append(PARAM_LIMIT).append(limit).append(PARAM_CURRENT_PAGE)
        .append(currentPage + 1).append(PARAM_SEARCH_STRING).append(searchString)
        .append(PARAM_ORDER).append(order).append(PARAM_ASCENDANCY).append(ascendancy).toString();
  }

  public String getCurrentLink() {
    return new StringBuilder().append(PARAM_LIMIT).append(limit).append(PARAM_CURRENT_PAGE)
        .append(currentPage).append(PARAM_SEARCH_STRING).append(searchString)
        .append(PARAM_ASCENDANCY).append(getNextAscendancy()).toString();
  }

  /**
   * @return
   */
  public String getAscendancy() {
    return ascendancy;
  }

  /**
   * @return
   */
  public String getNextAscendancy() {
    return DOWN.equals(ascendancy) ? UP : DOWN;
  }

  //BUILDER
  public static final class Builder {
    private Page page;

    private Builder() {
      page = new Page();
    }

    public Builder limit(final Integer limit) {
      page.limit = limit != null && limit != 0 ? limit : 10;
      return this;
    }

    public Builder currentPage(final int currentPage) {
      page.currentPage = currentPage;
      return this;
    }

    public Builder totalCount(final long totalCount) {
      page.totalCount = totalCount;
      return this;
    }

    public Builder searchString(final String searchString) {
      page.searchString = searchString != null ? searchString : "";
      return this;
    }

    public Builder order(final String order) {
      page.order = order != null ? order : "id";
      return this;
    }

    public Builder ascendancy(final String ascendancy) {
      page.ascendancy = ascendancy != null ? ascendancy : "";
      return this;
    }

    public Page build() {
      return page;
    }

  }

}
