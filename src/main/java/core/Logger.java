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
    private Logger myLogger;

    private Builder() {
      myLogger = new Logger();
      myLogger.time = new Date(System.currentTimeMillis());
    }

    public Builder id(final int id) {
      myLogger.id = id;
      return this;
    }

    public Builder log(final String log) {
      myLogger.log = log;
      return this;
    }

    public Builder time(final Date time) {
      myLogger.time = time;
      return this;
    }

    public Builder exception(final Exception exception) {
      myLogger.exception = exception;
      return this;
    }

    public Logger build() {
      return myLogger;
    }
  }

}
