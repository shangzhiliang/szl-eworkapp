package com.org.decp.msg.model.base;

import org.hibernate.validator.constraints.Length;

import com.thoughtworks.xstream.annotations.XStreamAlias;


public class EnvelopInfo
{

  @XStreamAlias("sourceID")
  @Length(max=4, min=4)
  private String sourceID;

  @XStreamAlias("destinationID")
  @Length(max=4, min=4)
  private String destinationID;

  @XStreamAlias("sourceAppID")
  @Length(max=6, min=6)
  private String sourceAppID;

  @XStreamAlias("destinationAppID")
  @Length(max=6, min=6)
  private String destinationAppID;

  @XStreamAlias("globalBusinessID")
  @Length(max=26, min=26)
  private String globalBusinessID;

  @XStreamAlias("messageID")
  @Length(max=26, min=26)
  private String messageID;

  @XStreamAlias("businessType")
  @Length(max=6, min=6)
  private String businessType;

  @XStreamAlias("syncFlag")
  @Length(max=6, min=6)
  private String syncFlag;

  public String getSourceID()
  {
    return this.sourceID;
  }
  public void setSourceID(String sourceID) {
    this.sourceID = sourceID;
  }
  public String getDestinationID() {
    return this.destinationID;
  }
  public void setDestinationID(String destinationID) {
    this.destinationID = destinationID;
  }
  public String getDestinationAppID() {
    return this.destinationAppID;
  }
  public void setDestinationAppID(String destinationAppID) {
    this.destinationAppID = destinationAppID;
  }
  public String getGlobalBusinessID() {
    return this.globalBusinessID;
  }
  public void setGlobalBusinessID(String globalBusinessID) {
    this.globalBusinessID = globalBusinessID;
  }
  public String getBusinessType() {
    return this.businessType;
  }
  public void setBusinessType(String businessType) {
    this.businessType = businessType;
  }
  public String getSyncFlag() {
    return this.syncFlag;
  }
  public void setSyncFlag(String syncFlag) {
    this.syncFlag = syncFlag;
  }
  public String getMessageID() {
    return this.messageID;
  }
  public void setMessageID(String messageID) {
    this.messageID = messageID;
  }
  public String getSourceAppID() {
    return this.sourceAppID;
  }
  public void setSourceAppID(String sourceAppID) {
    this.sourceAppID = sourceAppID;
  }
}