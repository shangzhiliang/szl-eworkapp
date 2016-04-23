package com.org.decp.msg.model.base;

public class ResultInfo
{
  public String returnCode = "";

  public String returnMessage = "";

  public String getReturnCode() { return this.returnCode; }

  public void setReturnCode(String returnCode) {
    this.returnCode = returnCode;
  }
  public String getReturnMessage() {
    return this.returnMessage;
  }
  public void setReturnMessage(String returnMessage) {
    this.returnMessage = returnMessage;
  }
}