package persistance.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import persistance.ConnectionDAO;
import persistance.exception.PersistenceException;
import persistance.mapper.ComputerRowMapper;
import persistance.mapper.RowMapper;
import core.Computer;

public enum ComputerDAO {
  INSTANCE;
  private static final RowMapper<Computer> MAPPER           = new ComputerRowMapper();
  private static final String              LIMIT_AND_OFFSET = "limit 10 offset ?";
  private static final String              SELECT_ALL       = "select id, name, introduced, discontinued, company_id from computer ";
  private static final String              SEARCH           = SELECT_ALL
                                                                + " where name like ? or company_id in (select id from company where name like ?) ";
  private static final String              SELECT           = "select id, name, introduced, discontinued, company_id from computer where id=?";
  private static final String              INSERT           = "insert into computer (name,introduced,discontinued,company_id) values (?,?,?,?)";
  private static final String              UPDATE           = "update computer set name=?, introduced=?, discontinued=?, company_id=? where id=?";
  private static final String              DELETE           = "delete from computer where id=?";

  private ComputerDAO() {}

  public List<Computer> selectAll(final int offset) throws PersistenceException {
    final Connection connection = ConnectionDAO.getConnection();
    try {
      final PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL
          + LIMIT_AND_OFFSET);
      preparedStatement.setInt(1, offset);
      final List<Computer> computers = MAPPER.convertResultSet(preparedStatement.executeQuery());
      return computers;
    } catch (final SQLException e) {
      throw new PersistenceException(e);
    } finally {
      ConnectionDAO.closeConnection(connection);
    }
  }

  public Computer select(final long idComputer) throws PersistenceException {
    final Connection connection = ConnectionDAO.getConnection();
    try {
      final PreparedStatement preparedStatement = connection.prepareStatement(SELECT);
      preparedStatement.setLong(1, idComputer);
      final List<Computer> computers = MAPPER.convertResultSet(preparedStatement.executeQuery());
      if (computers.size() > 0) {
        return computers.get(0);
      }
    } catch (final SQLException e) {
      throw new PersistenceException(e);
    } finally {
      ConnectionDAO.closeConnection(connection);
    }
    return null;
  }

  public void insert(final Computer computer) throws PersistenceException {
    final Connection connection = ConnectionDAO.getConnection();
    try {
      final PreparedStatement preparedStatement = connection.prepareStatement(INSERT,
          Statement.RETURN_GENERATED_KEYS);
      preparedStatement.setString(1, computer.getComputerName());
      preparedStatement.setDate(2, new Date(computer.getIntroducedDate().getTime()));
      preparedStatement.setDate(3, new Date(computer.getDiscontinuedDate().getTime()));
      preparedStatement.setLong(4, computer.getCompany() != null ? computer.getCompany().getId()
          : null);
      preparedStatement.execute();
      final ResultSet resultSet = preparedStatement.getGeneratedKeys();
      if (resultSet.next()) {
        computer.setId(resultSet.getLong(1));
      }
    } catch (final SQLException e) {
      throw new PersistenceException(e);
    } finally {
      ConnectionDAO.closeConnection(connection);
    }

  }

  /**
   * @param computer
   * @throws PersistenceException
   */
  public void update(final Computer computer) throws PersistenceException {
    final Connection connection = ConnectionDAO.getConnection();
    try {
      final PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
      preparedStatement.setString(1, computer.getComputerName());
      preparedStatement.setDate(2, new Date(computer.getIntroducedDate().getTime()));
      preparedStatement.setDate(3, new Date(computer.getDiscontinuedDate().getTime()));
      preparedStatement.setLong(4, computer.getCompany() != null ? computer.getCompany().getId()
          : null);
      preparedStatement.setLong(5, computer.getId());
      preparedStatement.execute();
    } catch (final SQLException e) {
      throw new PersistenceException(e);
    } finally {
      ConnectionDAO.closeConnection(connection);
    }
  }

  /**
   * @param idComputer
   * @throws PersistenceException
   */
  public void delete(final long idComputer) throws PersistenceException {
    final Connection connection = ConnectionDAO.getConnection();
    try {
      final PreparedStatement preparedStatement = connection.prepareStatement(DELETE);
      preparedStatement.setLong(1, idComputer);
      preparedStatement.execute();
    } catch (final SQLException e) {
      throw new PersistenceException(e);
    } finally {
      ConnectionDAO.closeConnection(connection);
    }
  }

  /**
   * Retourne tous les computers dont le nom de l'ordinateur ou de la compagnie ressemble.
   * @param name
   * @return
   * @throws PersistenceException
   */
  public List<Computer> search(final String name, final int offset) throws PersistenceException {
    final Connection connection = ConnectionDAO.getConnection();
    final char wildcard = '%';
    try {
      final PreparedStatement preparedStatement = connection.prepareStatement(SEARCH
          + LIMIT_AND_OFFSET);
      preparedStatement.setString(1, wildcard + name + wildcard);
      preparedStatement.setString(2, wildcard + name + wildcard);
      preparedStatement.setInt(3, offset);
      final List<Computer> computers = MAPPER.convertResultSet(preparedStatement.executeQuery());
      return computers;
    } catch (final SQLException e) {
      throw new PersistenceException(e);
    } finally {
      ConnectionDAO.closeConnection(connection);
    }
  }

}
