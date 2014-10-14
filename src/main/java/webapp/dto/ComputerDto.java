package webapp.dto;

/**
 * @author excilys
 *
 */
public class ComputerDto extends BasicDto {
  private String     computerName;
  private String     introducedDate;
  private String     discontinuedDate;
  private CompanyDto companyDto;      //J'aurais bien fait une énumération mais pour l'évolutivité, ce n'est pas le meilleur choix en cas de nouvelle company.

  public ComputerDto() {
    super();
  }

  /**
   * @return the computerName
   */
  public String getComputerName() {
    return computerName;
  }

  /**
   * @return the introducedDate
   */
  public String getIntroducedDate() {
    return introducedDate;
  }

  /**
   * @return the discontinuedDate
   */
  public String getDiscontinuedDate() {
    return discontinuedDate;
  }

  /**name
   * @return the companyDto
   */
  public CompanyDto getCompanyDto() {
    return companyDto;
  }

  /**
   * @param computerName the computerName to set
   */
  public void setComputerName(final String computerName) {
    this.computerName = computerName;
  }

  /**
   * @param introducedDate the introducedDate to set
   */
  public void setIntroducedDate(final String introducedDate) {
    this.introducedDate = introducedDate;
  }

  /**
   * @param discontinuedDate the discontinuedDate to set
   */
  public void setDiscontinuedDate(final String discontinuedDate) {
    this.discontinuedDate = discontinuedDate;
  }

  /**
   * @param companyDto the companyDto to set
   */
  public void setCompanyDto(final CompanyDto companyDto) {
    this.companyDto = companyDto;
  }

  public String getSupprimer() {

    return "<a href=\"DeleteComputer?codereq=" + externalId + "\">"
        + "<img src=\"img/trash_icon.png\" width=32 height=32 border=\"0\" alt=\"Supprimer "
        + computerName + " \">" + "</a>";

  }

  public String getModifier() {

    return "<a href=\"UpdateComputer?codereq=" + externalId + "\">"
        + "<img src=\"img/update_icon.jpeg\" width=32 height=32 border=\"0\" alt=\"Supprimer "
        + computerName + " \">" + "</a>";

  }
}
