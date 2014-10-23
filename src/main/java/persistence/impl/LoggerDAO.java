package persistence.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import persistence.ILoggerDAO;
import persistence.exception.PersistenceException;
import persistence.mapper.LoggerRowMapper;
import core.MyLogger;

/**
 * @author excilys
 *
 */
@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class LoggerDAO implements ILoggerDAO {
  private static final String SELECT_ALL = "select id, log, time, exception from logger";
  private static final String SELECT     = "select id, log, time, exception from logger where id=?";
  private static final String INSERT     = "insert into logger (log, time, exception) values (?,?,?)";
  private static final String UPDATE     = "update logger set log=? and time=? exception=? where id=?";
  private static final String DELETE     = "delete from logger where id=?";
  @Autowired
  private ConnectionDAO       connectionDAO;

  /**
   * @return
   * @throws PersistenceException
   */
  public List<MyLogger> selectAll() throws PersistenceException {
    final Connection connection = connectionDAO.getConnection();
    try {
      final PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);
      return LoggerRowMapper.convertResultSet(preparedStatement.executeQuery());

    } catch (final SQLException e) {
      throw new PersistenceException(e);
    }
  }

  /**
   * @param idLogger
   * @return
   * @throws PersistenceException
   */
  public MyLogger select(final int idLogger) throws PersistenceException {
    final Connection connection = connectionDAO.getConnection();
    try {
      final PreparedStatement preparedStatement = connection.prepareStatement(SELECT);
      preparedStatement.setInt(1, idLogger);
      final List<MyLogger> loggers = LoggerRowMapper.convertResultSet(preparedStatement
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
   * @param myLogger
   * @throws PersistenceException
   */
  public void insert(final MyLogger myLogger) throws PersistenceException {
    final Connection connection = connectionDAO.getConnection();
    try {
      final PreparedStatement preparedStatement = connection.prepareStatement(INSERT);
      preparedStatement.setString(1, myLogger.getLog());
      preparedStatement.setDate(2, new Date(myLogger.getTime().getTime()));
      preparedStatement.setString(3, myLogger.getException() != null ? myLogger.getException()
          .getMessage() : null);
      preparedStatement.execute();

    } catch (final SQLException e) {
      throw new PersistenceException(e);
    }

  }

  /**
   * @param myLogger
   * @throws PersistenceException
   */
  public void update(final MyLogger myLogger) throws PersistenceException {
    final Connection connection = connectionDAO.getConnection();
    try {
      final PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
      preparedStatement.setString(1, myLogger.getLog());
      preparedStatement.setDate(2, new Date(myLogger.getTime().getTime()));
      if (myLogger.getException() != null) {
        preparedStatement.setString(3, myLogger.getException().getMessage());
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
    final Connection connection = connectionDAO.getConnection();
    try {
      final PreparedStatement preparedStatement = connection.prepareStatement(DELETE);
      preparedStatement.setInt(1, idLogger);
      preparedStatement.execute();
    } catch (final SQLException e) {
      throw new PersistenceException(e);
    }
  }

 

}
