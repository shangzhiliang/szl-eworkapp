package com.org.decp.msg.model.base;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.org.decp.xml.xstream.XStreamDateConverter;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;


public class TransferInfo
{

  @XStreamAlias("senderID")
  @Length(max=4, min=4)
  private String senderID;

  @XStreamAlias("receiverID")
  @Length(max=4, min=4)
  private String receiverID;

  @XStreamAlias("senderAppID")
  @Length(max=6, min=6)
  private String senderAppID;

  @XStreamAlias("receiverAppID")
  @Length(max=6, min=6)
  private String receiverAppID;

  @XStreamAlias("sourceMessageID")
  @Length(max=24, min=24)
  private String sourceMessageID;

  @XStreamAlias("isRetry")
  @Length(max=1, min=1)
  private String isRetry;

  @XStreamAlias("sendTime")
  @XStreamConverter(XStreamDateConverter.class)
  private Date sendTime;

  public String getSenderID()
  {
    return this.senderID;
  }

  public void setSenderID(String senderID) {
    this.senderID = senderID;
  }

  public String getReceiverID() {
    return this.receiverID;
  }

  public void setReceiverID(String receiverID) {
    this.receiverID = receiverID;
  }

  public String getSourceMessageID()
  {
    return this.sourceMessageID;
  }

  public void setSourceMessageID(String sourceMessageID) {
    this.sourceMessageID = sourceMessageID;
  }

  public String getIsRetry() {
    return this.isRetry;
  }

  public void setIsRetry(String isRetry) {
    this.isRetry = isRetry;
  }

  public Date getSendTime() {
    return this.sendTime;
  }

  public void setSendTime(Date sendTime) {
    this.sendTime = sendTime;
  }

  public String getSenderAppID() {
    return this.senderAppID;
  }

  public void setSenderAppID(String senderAppID) {
    this.senderAppID = senderAppID;
  }

  public String getReceiverAppID() {
    return this.receiverAppID;
  }

  public void setReceiverAppID(String receiverAppID) {
    this.receiverAppID = receiverAppID;
  }
}