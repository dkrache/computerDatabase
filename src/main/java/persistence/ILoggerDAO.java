package persistence;

import java.util.List;

import persistence.exception.PersistenceException;
import core.MyLogger;

public interface ILoggerDAO {

  /**
   * @return
   * @throws PersistenceException
   */
  List<MyLogger> selectAll() throws PersistenceException;

  /**
   * @param idLogger
   * @return
   * @throws PersistenceException
   */
  MyLogger select(final int idLogger) throws PersistenceException;

  /**
   * @param myLogger
   * @throws PersistenceException
   */
  void insert(final MyLogger myLogger) throws PersistenceException;

  /**
   * @param myLogger
   * @throws PersistenceException
   */
  void update(final MyLogger myLogger) throws PersistenceException;

  /**
   * @param idLogger
   * @throws PersistenceException
   */
  void delete(final int idLogger) throws PersistenceException;
}
