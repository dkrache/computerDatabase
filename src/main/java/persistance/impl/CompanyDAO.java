package persistance.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import persistance.ConnectionDAO;
import persistance.exception.PersistenceException;
import persistance.mapper.CompanyRowMapper;
import persistance.mapper.RowMapper;
import core.Company;

/**
 * @author excilys
 *
 */
public enum CompanyDAO {
  INSTANCE;
  private static final RowMapper<Company> MAPPER     = new CompanyRowMapper();
  private static final String             SELECT_ALL = "select id, name from company";
  private static final String             SELECT     = "select id, name from company where id=?";
  private static final String             INSERT     = "insert into company (name) values (?)";
  private static final String             UPDATE     = "update company set name=? where id=?";
  private static final String             DELETE     = "delete from company where id=?";

  /**
   * 
   */
  private CompanyDAO() {}

  /**
   * @return
   * @throws PersistenceException
   */
  public List<Company> selectAll() throws PersistenceException {
    final Connection connection = ConnectionDAO.getConnection();
    try {
      final PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);
      final List<Company> companys = MAPPER.convertResultSet(preparedStatement.executeQuery());
      return companys;
    } catch (final SQLException e) {
      throw new PersistenceException(e);
    } finally {
      ConnectionDAO.closeConnection(connection);
    }
  }

  /**
   * @param idCompany
   * @return
   * @throws PersistenceException
   */
  public Company select(final int idCompany) throws PersistenceException {
    final Connection connection = ConnectionDAO.getConnection();
    try {
      final PreparedStatement preparedStatement = connection.prepareStatement(SELECT);
      preparedStatement.setInt(1, idCompany);
      final List<Company> companys = MAPPER.convertResultSet(preparedStatement.executeQuery());
      if (companys.size() > 0) {
        return companys.get(0);
      }
    } catch (final SQLException e) {
      throw new PersistenceException(e);
    } finally {
      ConnectionDAO.closeConnection(connection);
    }
    return null;
  }

  /**
   * @param company
   * @throws PersistenceException
   */
  public void insert(final Company company) throws PersistenceException {
    final Connection connection = ConnectionDAO.getConnection();
    try {
      final PreparedStatement preparedStatement = connection.prepareStatement(INSERT);
      preparedStatement.setString(1, company.getName());

      preparedStatement.execute();
    } catch (final SQLException e) {
      throw new PersistenceException(e);
    } finally {
      ConnectionDAO.closeConnection(connection);
    }

  }

  /**
   * @param company
   * @throws PersistenceException
   */
  public void update(final Company company) throws PersistenceException {
    final Connection connection = ConnectionDAO.getConnection();
    try {
      final PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
      preparedStatement.setString(1, company.getName());
      preparedStatement.setLong(5, company.getId());
      preparedStatement.execute();
    } catch (final SQLException e) {
      throw new PersistenceException(e);
    } finally {
      ConnectionDAO.closeConnection(connection);
    }
  }

  /**
   * @param idCompany
   * @throws PersistenceException
   */
  public void delete(final int idCompany) throws PersistenceException {
    final Connection connection = ConnectionDAO.getConnection();
    try {
      final PreparedStatement preparedStatement = connection.prepareStatement(DELETE);
      preparedStatement.setInt(1, idCompany);
      preparedStatement.execute();
    } catch (final SQLException e) {
      throw new PersistenceException(e);
    } finally {
      ConnectionDAO.closeConnection(connection);
    }
  }

}
