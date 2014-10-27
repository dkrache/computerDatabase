package core;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

public class Computer extends Basic {

  private String   computerName;
  private DateTime introducedDate;
  private DateTime discontinuedDate;
  private Company  company;

  /**
   * @return the computerName
   */
  public String getComputerName() {
    return computerName;
  }

  /**
   * @return the companyName
   */
  public Company getCompany() {
    return company;
  }

  /**
   * @return the introducedDate
   */
  public DateTime getIntroducedDate() {
    return introducedDate;
  }

  /**
   * @return the discontinuedDate
   */
  public DateTime getDiscontinuedDate() {
    return discontinuedDate;
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

    public Builder introducedDate(final DateTime date) {
      c.introducedDate = date;
      return this;
    }

    public Builder discontinuedDate(final DateTime date) {
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
    result = prime * result + ((computerName == null) ? 0 : computerName.hashCode());
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
    if (obj == null || !(obj instanceof Computer)) {
      return false;
    }
    final Computer other = (Computer) obj;

    if (!StringUtils.equals(computerName, other.computerName)) {
      return false;
    }

    return super.id == other.id;
  }
}
