package persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import persistence.ICompanyDAO;
import persistence.exception.PersistenceException;
import persistence.mapper.CompanyRowMapper;
import core.Company;

/**
 * @author excilys
 *
 */
@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class CompanyDAO implements ICompanyDAO {
  private static final String SELECT_ALL = "select id, name from company";
  private static final String SELECT     = "select id, name from company where id=?";
  private static final String INSERT     = "insert into company (name) values (?)";
  private static final String UPDATE     = "update company set name=? where id=?";
  private static final String DELETE     = "delete from company where id=?";
  @Autowired
  private ConnectionDAO       connectionDAO;

  /**
   * @return
   * @throws PersistenceException
   */
  public List<Company> readAll() throws PersistenceException {
    final Connection connection = connectionDAO.getConnection();
    try {
      final PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);
      return CompanyRowMapper.convertResultSet(preparedStatement.executeQuery());

    } catch (final SQLException e) {
      throw new PersistenceException(e);
    }
  }

  /**
   * @param idCompany
   * @return
   * @throws PersistenceException
   */
  public Company read(final int idCompany) throws PersistenceException {
    final Connection connection = connectionDAO.getConnection();
    try {
      final PreparedStatement preparedStatement = connection.prepareStatement(SELECT);
      preparedStatement.setInt(1, idCompany);
      final List<Company> companys = CompanyRowMapper.convertResultSet(preparedStatement
          .executeQuery());
      if (!companys.isEmpty()) {
        return companys.get(0);
      }
    } catch (final SQLException e) {
      throw new PersistenceException(e);
    }
    return null;
  }

  /**
   * @param company
   * @throws PersistenceException
   */
  public void create(final Company company) throws PersistenceException {
    final Connection connection = connectionDAO.getConnection();
    try {
      final PreparedStatement preparedStatement = connection.prepareStatement(INSERT);
      preparedStatement.setString(1, company.getName());
      preparedStatement.execute();
    } catch (final SQLException e) {
      throw new PersistenceException(e);
    }

  }

  /**
   * @param company
   * @throws PersistenceException
   */
  public void update(final Company company) throws PersistenceException {
    final Connection connection = connectionDAO.getConnection();
    try {
      final PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
      preparedStatement.setString(1, company.getName());
      preparedStatement.setLong(2, company.getId());
      preparedStatement.execute();
    } catch (final SQLException e) {
      throw new PersistenceException(e);
    }
  }

  /**
   * @param idCompany
   * @throws PersistenceException
   */
  public void delete(final int idCompany) throws PersistenceException {
    final Connection connection = connectionDAO.getConnection();
    try {
      final PreparedStatement preparedStatement = connection.prepareStatement(DELETE);
      preparedStatement.setInt(1, idCompany);
      preparedStatement.execute();
    } catch (final SQLException e) {
      throw new PersistenceException(e);
    }
  }

}
