package core;

import java.util.Date;

public class Computer extends Basic {
  private String  computerName;
  private Date    introducedDate;
  private Date    discontinuedDate;
  private Company company;         //J'aurais bien fait une énumération mais pour l'évolutivité, ce n'est pas le meilleur choix en cas de nouvelle company.

  /**
   * @return the computerName
   */
  public String getComputerName() {
    return computerName;
  }

  /**
   * @return the introducedDate
   */
  public Date getIntroducedDate() {
    return introducedDate;
  }

  /**
   * @return the discontinuedDate
   */
  public Date getDiscontinuedDate() {
    return discontinuedDate;
  }

  /**
   * @return the companyName
   */
  public Company getCompany() {
    return company;
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
  public void setIntroducedDate(final Date introducedDate) {
    this.introducedDate = introducedDate;
  }

  /**
   * @param discontinuedDate the discontinuedDateCo to set
   */
  public void setDiscontinuedDate(final Date discontinuedDate) {
    this.discontinuedDate = discontinuedDate;
  }

  /**
   * @param companyName the companyName to set
   */
  public void setCompany(final Company company) {
    this.company = company;
  }

}
