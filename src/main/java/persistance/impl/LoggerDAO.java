package persistance.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import persistance.exception.PersistenceException;
import persistance.mapper.LoggerRowMapper;
import persistence.ConnectionDAO;
import core.Logger;

/**
 * @author excilys
 *
 */
public enum LoggerDAO {
  INSTANCE;
  private static final String SELECT_ALL = "select id, log, time, exception from logger";
  private static final String SELECT     = "select id, log, time, exception from logger where id=?";
  private static final String INSERT     = "insert into logger (log, time, exception) values (?,?,?)";
  private static final String UPDATE     = "update logger set log=? and time=? exception=? where id=?";
  private static final String DELETE     = "delete from logger where id=?";

  /**
   * 
   */
  private LoggerDAO() {
    //do Nothing
  }

  /**
   * @return
   * @throws PersistenceException
   */
  public List<Logger> selectAll() throws PersistenceException {
    final Connection connection = ConnectionDAO.getConnection();
    try {
      final PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);
      final List<Logger> loggers = LoggerRowMapper.convertResultSet(preparedStatement
          .executeQuery());
      return loggers;

    } catch (final SQLException e) {
      throw new PersistenceException(e);
    }
  }

  /**
   * @param idLogger
   * @return
   * @throws PersistenceException
   */
  public Logger select(final int idLogger) throws PersistenceException {
    final Connection connection = ConnectionDAO.getConnection();
    try {
      final PreparedStatement preparedStatement = connection.prepareStatement(SELECT);
      preparedStatement.setInt(1, idLogger);
      final List<Logger> loggers = LoggerRowMapper.convertResultSet(preparedStatement
          .executeQuery());
      if (!loggers.isEmpty()) {
        return loggers.get(0);
      }
    } catch (final SQLException e) {
      throw new PersistenceException(e);
    }
    return null;
  }

  /**
   * @param logger
   * @throws PersistenceException
   */
  public void insert(final Logger logger) throws PersistenceException {
    final Connection connection = ConnectionDAO.getConnection();
    try {
      final PreparedStatement preparedStatement = connection.prepareStatement(INSERT);
      preparedStatement.setString(1, logger.getLog());
      preparedStatement.setDate(2, new Date(logger.getTime().getTime()));
      preparedStatement.setString(3, logger.getException() != null ? logger.getException()
          .getMessage() : null);
      preparedStatement.execute();

    } catch (final SQLException e) {
      throw new PersistenceException(e);
    }

  }

  /**
   * @param logger
   * @throws PersistenceException
   */
  public void update(final Logger logger) throws PersistenceException {
    final Connection connection = ConnectionDAO.getConnection();
    try {
      final PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
      preparedStatement.setString(1, logger.getLog());
      preparedStatement.setDate(2, new Date(logger.getTime().getTime()));
      if (logger.getException() != null) {
        preparedStatement.setString(3, logger.getException().getMessage());
      }
      preparedStatement.execute();
    } catch (final SQLException e) {
      throw new PersistenceException(e);
    }
  }

  /**
   * @param idLogger
   * @throws PersistenceException
   */
  public void delete(final int idLogger) throws PersistenceException {
    final Connection connection = ConnectionDAO.getConnection();
    try {
      final PreparedStatement preparedStatement = connection.prepareStatement(DELETE);
      preparedStatement.setInt(1, idLogger);
      preparedStatement.execute();
    } catch (final SQLException e) {
      throw new PersistenceException(e);
    }
  }

}
