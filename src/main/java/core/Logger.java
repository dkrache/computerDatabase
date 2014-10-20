package core;

import java.util.Date;

public final class Logger extends Basic {
  private String    log;

  private Date      time;

  private Exception exception;

  private Logger() {

  }

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

  public static final class Builder {
    private Logger logger;

    private Builder() {
      logger = new Logger();
      logger.time = new Date(System.currentTimeMillis());
    }

    public Builder id(final int id) {
      logger.id = id;
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

    public Logger build() {
      return logger;
    }
  }

}
