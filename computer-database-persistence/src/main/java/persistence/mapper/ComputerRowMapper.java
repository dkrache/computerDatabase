package persistence.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import persistence.exception.PersistenceException;
import persistence.impl.CompanyDAO;
import util.DateUtils;
import core.Computer;

/**
 * @author excilys
 *
 */
@Component
public class ComputerRowMapper {
  @Autowired
  private CompanyDAO companyDAO;

  public ComputerRowMapper() {

  }

  /**
   * Get List of computers from resultSet
   * @param resultSet
   * @return
   * @throws SQLException
   * @throws PersistenceException
   */
  public List<Computer> convertResultSet(final ResultSet resultSet) throws PersistenceException {
    final List<Computer> computers = new ArrayList<>();

    try {
      while (resultSet.next()) {
        computers.add(Computer.builder(resultSet.getString("name"))
            .company(companyDAO.read(resultSet.getInt("company_id")))
            .discontinuedDate(DateUtils.getDate(resultSet.getTimestamp("discontinued")))
            .introducedDate(DateUtils.getDate(resultSet.getTimestamp("introduced")))
            .id(resultSet.getInt("id")).build());
      }
    } catch (final SQLException e) {
      throw new PersistenceException(e);
    }
    return computers;
  }

  /**
   * @param companyDAO the companyDAO to set
   */
  public void setCompanyDAO(final CompanyDAO companyDAO) {
    this.companyDAO = companyDAO;
  }
}
