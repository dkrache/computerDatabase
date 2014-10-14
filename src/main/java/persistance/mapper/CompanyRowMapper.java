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
      final Company company = new Company();
      company.setId(resultSet.getInt("id"));
      // TODO DKR : CRUD for COMPANY
      //computer.setCompany();
      company.setName(resultSet.getString("name"));
      companys.add(company);
    }
    return companys;
  }

}