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

  public static Builder builder(final String name) {
    return new Builder(name);
  }

  //BUILDER
  public static final class Builder {
    private Computer c;

    private Builder(final String name) {
      c = new Computer();
      c.computerName = name;
    }

    public Builder id(final long id) {
      c.id = id;
      return this;
    }

    public Builder company(final Company company) {
      c.company = company;
      return this;
    }

    public Builder introducedDate(final Date date) {
      c.introducedDate = date;
      return this;
    }

    public Builder discontinuedDate(final Date date) {
      c.discontinuedDate = date;
      return this;
    }

    public Computer build() {
      return c;
    }
  }

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "Computer [computerName=" + computerName + ", introducedDate=" + introducedDate
        + ", discontinuedDate=" + discontinuedDate + ", company=" + company + ", id=" + id + "]";
  }

  /* (non-Javadoc)
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((company == null) ? 0 : company.hashCode());
    result = prime * result + ((computerName == null) ? 0 : computerName.hashCode());
    result = prime * result + ((discontinuedDate == null) ? 0 : discontinuedDate.hashCode());
    result = prime * result + ((introducedDate == null) ? 0 : introducedDate.hashCode());
    return result;
  }

  /* (non-Javadoc)
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (!(obj instanceof Computer)) {
      return false;
    }
    final Computer other = (Computer) obj;
    if (company == null) {
      if (other.company != null) {
        return false;
      }
    } else if (!company.equals(other.company)) {
      return false;
    }
    if (computerName == null) {
      if (other.computerName != null) {
        return false;
      }
    } else if (!computerName.equals(other.computerName)) {
      return false;
    }
    if (discontinuedDate == null) {
      if (other.discontinuedDate != null) {
        return false;
      }
    } else if (!discontinuedDate.equals(other.discontinuedDate)) {
      return false;
    }
    if (introducedDate == null) {
      if (other.introducedDate != null) {
        return false;
      }
    } else if (!introducedDate.equals(other.introducedDate)) {
      return false;
    }
    return true;
  }

}
