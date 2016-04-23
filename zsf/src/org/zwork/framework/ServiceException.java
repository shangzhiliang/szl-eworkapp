package org.zwork.framework;


public class ServiceException extends RuntimeException
{
  private static final long serialVersionUID = -7750687782421743004L;
  private String level;
  private String code;
  public static final String EXCEPTION_LEVEL_WARN = "warn";
  public static final String EXCEPTION_LEVEL_ERROR = "error";

  public ServiceException(String level, String code, String msg)
  {
    super(msg);
    setLevel(level);
    setCode(code);
  }

  public String getLevel() {
    return this.level;
  }

  public void setLevel(String level) {
    this.level = level;
  }

  public String getCode() {
    return this.code;
  }

  public void setCode(String code) {
    this.code = code;
  }
}