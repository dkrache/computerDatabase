package persistence.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import core.Company;

/**
 * @author excilys
 *
 */
@Component
public final class CompanyRowMapper implements RowMapper<Company> {

  @Override
  public Company mapRow(final ResultSet resultSet, final int rowNum) throws SQLException {
    return Company.builder(resultSet.getString("name")).id(resultSet.getInt("id")).build();
  }

}