package com.org.decp.msg.model.base;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.sun.istack.NotNull;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;


public class ReturnState
{

  @XStreamAlias("returnCode")
  @NotNull
  @Length(max=10, min=2)
  private String returnCode;

  @XStreamAlias("returnMessage")
  @NotNull
  @Length(max=100)
  private String returnMessage;

  @XStreamAlias("sourceMessageID")
  @NotNull
  @Length(max=26, min=26)
  private String sourceMessageID;

  @XStreamImplicit(itemFieldName="paramList")
  private List paramList;

  public String getReturnCode()
  {
    return this.returnCode;
  }

  public void setReturnCode(String returnCode) {
    this.returnCode = returnCode;
  }

  public String getReturnMessage() {
    return this.returnMessage;
  }

  public void setReturnMessage(String returnMessage) {
    this.returnMessage = returnMessage;
  }

  public String getSourceMessageID() {
    return this.sourceMessageID;
  }

  public void setSourceMessageID(String sourceMessageID) {
    this.sourceMessageID = sourceMessageID;
  }

  public List getParamList() {
    return this.paramList;
  }

  public void setParamList(List paramList) {
    this.paramList = paramList;
  }
}