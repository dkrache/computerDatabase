package persistance.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import core.Company;

/**
 * @author excilys
 *
 */
public class CompanyRowMapper implements RowMapper<Company> {

  /* (non-Javadoc)
   * @see persistance.mapper.RowMapper#convertResultSet(java.sql.ResultSet)
   */
  @Override
  public List<Company> convertResultSet(final ResultSet resultSet) throws SQLException {
    final List<Company> companys = new ArrayList<>();
    while (resultSet.next()) {
      companys.add(Company.builder(resultSet.getString("name")).id(resultSet.getInt("id")).build());
    }
    return companys;
  }
}