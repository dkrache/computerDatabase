package persistence.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import persistence.ILoggerDAO;
import core.MyLogger;

/**
 * @author excilys
 *
 */
@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class LoggerDAO implements ILoggerDAO {

  @PersistenceContext(unitName = "persistenceUnit")
  private EntityManager entityManager;

  /**
   * request = private static final String SELECT_ALL = "select id, log, time, exception from logger";
   * @return
   */
  public List<MyLogger> selectAll() {
    throw new UnsupportedOperationException("useless pour l'instant");
  }

  /**
   * request = private static final String SELECT     = "select id, log, time, exception from logger where id=?";
   * @param idLogger
   * @return
   */
  public MyLogger select(final int idLogger) {
    throw new UnsupportedOperationException("useless pour l'instant");
  }

  /**
   * @param myLogger
   */
  public void insert(final MyLogger myLogger) {
    entityManager.persist(myLogger);
  }

  /**
   * request = private static final String UPDATE     = "update logger set log=? and time=? exception=? where id=?";
   * @param myLogger
   */
  public void update(final MyLogger myLogger) {
    throw new UnsupportedOperationException("useless pour l'instant");
  }

  /**
   * request = private static final String DELETE     = "delete from logger where id=?";
   * @param idLogger
   */
  public void delete(final int idLogger) {
    throw new UnsupportedOperationException("useless pour l'instant");
  }

}
