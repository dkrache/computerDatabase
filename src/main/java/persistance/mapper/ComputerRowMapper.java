package persistance.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import persistance.exception.PersistenceException;
import persistance.impl.CompanyDAO;
import binding.DateUtils;
import core.Computer;

/**
 * @author excilys
 *
 */
public class ComputerRowMapper implements RowMapper<Computer> {

  /* (non-Javadoc)
   * @see persistance.mapper.RowMapper#convertResultSet(java.sql.ResultSet)
   */
  @Override
  public List<Computer> convertResultSet(final ResultSet resultSet) throws SQLException,
      PersistenceException {
    final List<Computer> computers = new ArrayList<>();

    while (resultSet.next()) {
      computers.add(Computer.builder(resultSet.getString("name"))
          .company(CompanyDAO.INSTANCE.select(resultSet.getInt("company_id")))
          .discontinuedDate(DateUtils.getDate(resultSet.getTimestamp("discontinued")))
          .introducedDate(DateUtils.getDate(resultSet.getTimestamp("introduced")))
          .id(resultSet.getInt("id")).build());
    }
    return computers;
  }
}
