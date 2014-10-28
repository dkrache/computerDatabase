package persistence.impl;

import java.sql.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import persistence.ILoggerDAO;
import persistence.mapper.LoggerRowMapper;
import core.MyLogger;

/**
 * @author excilys
 *
 */
@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class LoggerDAO extends JdbcDaoSupport implements ILoggerDAO {
  @Autowired
  private DataSource          dataSource;
  @Autowired
  private LoggerRowMapper     loggerRowMapper;

  private static final String INSERT = "insert into logger (log, time, exception) values (?,?,?)";

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
    getJdbcTemplate().update(INSERT, myLogger.getLog(), new Date(myLogger.getTime().getTime()),
        myLogger.getException());
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

  @PostConstruct
  private void initialize() {
    setDataSource(dataSource);
  }
}
