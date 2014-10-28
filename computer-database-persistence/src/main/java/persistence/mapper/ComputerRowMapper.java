package persistence.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import persistence.ICompanyDAO;
import util.DateUtils;
import core.Computer;

/**
 * @author excilys
 *
 */
@Component
public class ComputerRowMapper implements RowMapper<Computer> {
  @Autowired
  private ICompanyDAO companyDAO;

  public Computer mapRow(final ResultSet resultSet, final int rowNum) throws SQLException {
    return Computer.builder(resultSet.getString("name"))
        .company(companyDAO.read(resultSet.getInt("company_id")))
        .discontinuedDate(DateUtils.getDate(resultSet.getTimestamp("discontinued")))
        .introducedDate(DateUtils.getDate(resultSet.getTimestamp("introduced")))
        .id(resultSet.getInt("id")).build();

  }

}
