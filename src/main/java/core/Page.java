package core;

public class Page {
  private int    limit;
  private int    currentPage;
  private int    totalCount;
  private String searchString;

  /**
   * @param nbPages
   * @param pageSize
   * @param currentPage
   * @param totalCount
   * @param searchString
   * @param computers
   */
  protected Page(final int limit, final int currentPage, final int totalCount,
      final String searchString) {
    super();
    this.limit = limit;
    this.currentPage = currentPage;
    this.totalCount = totalCount;
    this.searchString = searchString;
  }

  /**
   * Default constructor
   */
  public Page() {
    this(10, 0, 0, "");
  }

  /**
   * @return the nbPages
   */
  public int getNbPages() {

    return limit <= 0 ? 1 : totalCount / limit;
  }

  /**
   * @return the pageSize
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
  public int getTotalCount() {
    return totalCount;
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
   * @param totalCount the totalCount to set
   */
  public void setTotalCount(final int totalCount) {
    this.totalCount = totalCount;
  }

  public static Builder builder() {
    return new Builder();
  }

  //BUILDER
  public static final class Builder {
    private Page page;

    private Builder() {
      page = new Page();
    }

    public Builder limit(final int limit) {
      page.limit = limit != 0 ? limit : 10;
      return this;
    }

    public Builder currentPage(final int currentPage) {
      page.currentPage = currentPage;
      return this;
    }

    public Builder totalCount(final int totalCount) {
      page.totalCount = totalCount;
      return this;
    }

    public Builder searchString(final String searchString) {
      page.searchString = searchString != null ? searchString : "";
      return this;
    }

    public Page build() {
      return page;
    }
  }
}
