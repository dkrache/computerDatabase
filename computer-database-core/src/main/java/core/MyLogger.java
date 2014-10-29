package core;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "myLogger")
@Table(name = "logger")
public final class MyLogger {
  @Id
  @GeneratedValue
  private long      id;
  @Column(name = "log")
  private String    log;
  @Column(name = "time")
  private Date      time = new Date(System.currentTimeMillis());
  @Column(name = "exception")
  private Exception exception;

  /**
   * @return the log
   */
  public String getLog() {
    return log;
  }

  /**
   * @return the time
   */
  public Date getTime() {
    return new Date(time.getTime());
  }

  public static Builder builder() {
    return new Builder();
  }

  /**
   * @return the exception
   */
  public Exception getException() {
    return exception;
  }

  /**
   * @return the id
   */
  public long getId() {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId(final long id) {
    this.id = id;
  }

  public static final class Builder {
    private MyLogger logger;

    private Builder() {
      logger = new MyLogger();
      logger.time = new Date(System.currentTimeMillis());
    }

    public Builder id(final int id) {
      logger.setId(id);
      return this;
    }

    public Builder log(final String log) {
      logger.log = log;
      return this;
    }

    public Builder time(final Date time) {
      logger.time = time;
      return this;
    }

    public Builder exception(final Exception exception) {
      logger.exception = exception;
      return this;
    }

    public MyLogger build() {
      return logger;
    }
  }

}
