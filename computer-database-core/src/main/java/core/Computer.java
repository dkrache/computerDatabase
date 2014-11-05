package core;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlElement;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

/**
 * @author excilys
 *
 */
@Entity(name = "computer")
@Table(name = "computer")
public class Computer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private long     id;
  @Column(name = "name")
  private String   computerName;
  @Column(name = "introduced")
  @Temporal(TemporalType.TIMESTAMP)
  @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
  private DateTime introducedDate;
  @Column(name = "discontinued")
  @Temporal(TemporalType.TIMESTAMP)
  @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
  private DateTime discontinuedDate;
  @ManyToOne
  private Company  company;

  /**
   * @return the id
   */
  @XmlElement(name = "id")
  public long getId() {
    return id;
  }

  /**
   * @return the computerName
   */
  @XmlElement(name = "computerName")
  public String getComputerName() {
    return computerName;
  }

  /**
   * @return the introducedDate
   */
  @XmlElement(name = "introducedDate")
  public DateTime getIntroducedDate() {
    return introducedDate;
  }

  /**
   * @return the discontinuedDate
   */
  @XmlElement(name = "discontinuedDate")
  public DateTime getDiscontinuedDate() {
    return discontinuedDate;
  }

  /**
   * @return the company
   */
  @XmlElement(name = "company")
  public Company getCompany() {
    return company;
  }

  /**
   * @param id the id to set
   */
  public void setId(final long id) {
    this.id = id;
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
  public void setIntroducedDate(final DateTime introducedDate) {
    this.introducedDate = introducedDate;
  }

  /**
   * @param discontinuedDate the discontinuedDate tCompanyo set
   */
  public void setDiscontinuedDate(final DateTime discontinuedDate) {
    this.discontinuedDate = discontinuedDate;
  }

  /**
   * @param company the company to set
   */
  public void setCompany(final Company company) {
    this.company = company;
  }

  public static Builder builder(final String name) {
    return new Builder(name);
  }

  /**
   * @author excilys
   *  Builder
   */
  public static final class Builder {
    private transient Computer c;

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

}
