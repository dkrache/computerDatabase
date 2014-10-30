package core;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author excilys
 *
 */

@Entity(name = "company")
@Table(name = "company")
public class Company {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private long   id;

  @Column(name = "name")
  private String name;

  /**
   * @return the id
   */
  public long getId() {
    return id;
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
    * @param id the id to set
    */
  public void setId(final long id) {
    this.id = id;
  }

  /**
   * @param name the name to set
   */
  public void setName(final String name) {
    this.name = name;
  }

  public static Builder builder(final String name) {
    return new Builder(name);
  }

  //BUILDER
  public static final class Builder {
    private Company company;

    private Builder(final String name) {
      company = new Company();
      company.name = name;
    }

    public Builder id(final long id) {
      company.id = id;
      return this;
    }

    public Company build() {
      return company;
    }
  }
}
