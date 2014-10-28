package persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import core.MyLogger;

@Repository
public interface ILoggerDAO {

  /**
   * @return
   */
  List<MyLogger> selectAll();

  /**
   * @param idLogger
   * @return
   */
  MyLogger select(final int idLogger);

  /**
   * @param myLogger
   */
  void insert(final MyLogger myLogger);

  /**
   * @param myLogger
   */
  void update(final MyLogger myLogger);

  /**
   * @param idLogger
   */
  void delete(final int idLogger);
}
