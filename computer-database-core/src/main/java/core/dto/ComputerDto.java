package core.dto;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import util.Constants;

/**
 * @author excilys
 *
 */
public class ComputerDto extends BasicDto {
  @NotBlank(message = "Please enter the name")
  @Size(min = 3, message = "the length must be 3 at least")
  private String     computerName;
  @Pattern(regexp = "(\\d\\d/){2}(\\d){4}")
  private String     introducedDate;
  @Pattern(regexp = "(\\d\\d/){2}(\\d){4}")
  private String     discontinuedDate;
  private CompanyDto companyDto;

  /**
   * Default constructor
   */
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

  /**
   * @return
   */
  public String getSupprimer() {

    return new StringBuilder().append("<a href=\"/computer-database-web/")
        .append(Constants.VUE_DASHBOARD).append(Constants.VUE_DELETE).append("?codereq=")
        .append(externalId).append("\"> <img src=\"/computer-database-web/img/trash_icon.png\"")
        .append(" width=32 height=32 border=\"0\" alt=\"Supprimer ")
        .append(computerName + " \">" + "</a>").toString();

  }

  /**
   * @return
   */
  public String getModifier() {

    return new StringBuilder()
        .append("<a href=\"/computer-database-web/")
        .append(Constants.VUE_DASHBOARD)
        .append(Constants.VUE_UPDATE)
        .append("?codereq=")
        .append(externalId)
        .append(
            "\"> <img src=\"/computer-database-web/img/update_icon.jpeg\" width=32 height=32 border=\"0\" alt=\"Modifier ")
        .append(computerName + " \">" + "</a>").toString();

  }
}
