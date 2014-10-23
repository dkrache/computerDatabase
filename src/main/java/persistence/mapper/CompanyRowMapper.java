package persistence.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import core.Company;

/**
 * @author excilys
 *
 */
public final class CompanyRowMapper {

  private CompanyRowMapper() {

  }

  /**
   * Get List of COmpanys from resultSet
   * @param resultSet
   * @return
   * @throws SQLException
   */
  public static List<Company> convertResultSet(final ResultSet resultSet) throws SQLException {
    final List<Company> companys = new ArrayList<>();
    while (resultSet.next()) {
      companys.add(Company.builder(resultSet.getString("name")).id(resultSet.getInt("id")).build());
    }
    return companys;
  }
}