package webapp.dto;

/**
 * @author excilys
 *
 */
public class CompanyDto extends BasicDto {
  private String name;

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name the name to set
   */
  public void setName(final String name) {
    this.name = name;
  }

}
