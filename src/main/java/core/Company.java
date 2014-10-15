package core;

/**
 * 
 * @author excilys
 *
 */
public class Company extends Basic {
  private String name;

  /**
   * @return the name
   */
  public String getName() {
    return name;
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

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "Company [name=" + name + ", id=" + super.id + "]";
  }

  /* (non-Javadoc)
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((name == null) ? 0 : name.hashCode());
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
    if (!(obj instanceof Company)) {
      return false;
    }
    final Company other = (Company) obj;
    if (name == null) {
      if (other.name != null) {
        return false;
      }
    } else if (!name.equals(other.name)) {
      return false;
    }
    return true;
  }

}
