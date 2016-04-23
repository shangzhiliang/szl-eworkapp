package com.org.decp.msg.model.base;

import java.util.Date;

import org.apache.commons.lang.SystemUtils;
import org.swork.common.utils.DateUtils;

public class MsgTransferInfo extends MsgTransferInfoPK
{
  private static final long serialVersionUID = 1L;
  public static final String ALIAS_MSG_ID = "msg_id";
  public static final String ALIAS_SENDERID = "senderID";
  public static final String ALIAS_RECEIVERID = "receiverID";
  public static final String ALIAS_SENDERAppID = "senderAppID";
  public static final String ALIAS_RECEIVERAppID = "receiverAppID";
  public static final String ALIAS_SOURCEMESSAGEID = "sourceMessageID";
  public static final String ALIAS_ISRETRY = "isRetry";
  public static final String ALIAS_SENDTIME = "sendTime";
  public static final String ALIAS_CREATE_TIME = "create_time";
  public static final String ALIAS_LAST_UPDATE_TIME = "last_update_time";
  private String msgId;
  private String senderID;
  private String receiverID;
  private String sourceMessageID;
  private String senderAppID;
  private String receiverAppID;
  private String isRetry;
  private Date sendTime;
  private Date createTime;
  private Date lastUpdateTime;
  private MsgExchangePackage msgExchangePackage;

  public String getMsgId()
  {
    return this.msgId;
  }
  public void setMsgId(String msgId) {
    this.msgId = msgId;
  }
  public String getSenderID() {
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
  public String getSourceMessageID() {
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
  public Date getCreateTime() {
    return this.createTime;
  }
  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public Date getLastUpdateTime() {
    return this.lastUpdateTime;
  }
  public void setLastUpdateTime(Date lastUpdateTime) {
    this.lastUpdateTime = lastUpdateTime;
  }

  public MsgExchangePackage getMsgExchangePackage()
  {
    return this.msgExchangePackage;
  }
  public void setMsgExchangePackage(MsgExchangePackage msgExchangePackage) {
    this.msgExchangePackage = msgExchangePackage;
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

  public String toString()
  {
    StringBuffer str = new StringBuffer();
    str.append(getClass().getName()).append("@").append(hashCode()).append("{").append(SystemUtils.LINE_SEPARATOR);
    str.append("\tid=").append(getId()).append(SystemUtils.LINE_SEPARATOR);
    str.append("\tmsgId=").append(getMsgId()).append(SystemUtils.LINE_SEPARATOR);
    str.append("\tsenderID=").append(getSenderID()).append(SystemUtils.LINE_SEPARATOR);
    str.append("\treceiverID=").append(getReceiverID()).append(SystemUtils.LINE_SEPARATOR);
    str.append("\tsourceMessageID=").append(getSourceMessageID()).append(SystemUtils.LINE_SEPARATOR);
    str.append("\tisRetry=").append(getIsRetry()).append(SystemUtils.LINE_SEPARATOR);
    str.append("\tsendTime=").append(DateUtils.dateTimeToString(getSendTime())).append(SystemUtils.LINE_SEPARATOR);
    str.append("\tcreateTime=").append(DateUtils.dateTimeToString(getCreateTime())).append(SystemUtils.LINE_SEPARATOR);
    str.append("\tlastUpdateTime=").append(DateUtils.dateTimeToString(getLastUpdateTime())).append(SystemUtils.LINE_SEPARATOR);
    str.append("\tlastUpdateTime=").append(DateUtils.dateTimeToString(getLastUpdateTime())).append(SystemUtils.LINE_SEPARATOR);
    str.append("\tlastUpdateTime=").append(DateUtils.dateTimeToString(getLastUpdateTime())).append(SystemUtils.LINE_SEPARATOR);
    str.append("\treceiverAppID=").append(getReceiverAppID()).append(SystemUtils.LINE_SEPARATOR);
    str.append("\tsenderAppID=").append(getSenderAppID()).append(SystemUtils.LINE_SEPARATOR);
    str.append("}").append(SystemUtils.LINE_SEPARATOR);
    return str.toString();
  }
}