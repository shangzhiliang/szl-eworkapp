package com.org.decp.msg.model.base;

import java.util.Date;
import java.util.List;

import org.swork.common.utils.DateUtils;
import org.swork.common.utils.SystemUtils;


public class MsgExchangePackage extends MsgExchangePackagePK
{
  private static final long serialVersionUID = 1L;
  public static final String ALIAS_SOURCEID = "sourceID";
  public static final String ALIAS_DESTINATIONID = "destinationID";
  public static final String ALIAS_DESTINATIONAPPID = "destinationAppID";
  public static final String ALIAS_SOURCESAPPID = "sourceAppID";
  public static final String ALIAS_GLOBALBUSINESSID = "globalBusinessID";
  public static final String ALIAS_MESSAGEID = "messageID";
  public static final String ALIAS_BUSINESSTYPE = "businessType";
  public static final String ALIAS_SYNCFLAG = "syncFlag";
  public static final String ALIAS_ISZIP = "isZip";
  public static final String ALIAS_ZIPTYPE = "zipType";
  public static final String ALIAS_ISENCRYPT = "isEncrypt";
  public static final String ALIAS_ENCRYTTYPE = "encrytType";
  public static final String ALIAS_SEQUENCE = "sequence";
  public static final String ALIAS_CONTENT = "content";
  public static final String ALIAS_PARAMLIST = "paramList";
  public static final String ALIAS_RETURNCODE = "returnCode";
  public static final String ALIAS_RETURNMESSAGE = "returnMessage";
  public static final String ALIAS_SOURCEMESSAGEID = "sourceMessageID";
  public static final String ALIAS_REMARK = "remark";
  public static final String ALIAS_CREATE_TIME = "create_time";
  public static final String ALIAS_LAST_UPDATE_TIME = "last_update_time";
  private String sourceID;
  private String destinationID;
  private String sourceAppID;
  private String destinationAppID;
  private String globalBusinessID;
  private String messageID;
  private String businessType;
  private String syncFlag;
  private String isZip;
  private String zipType;
  private String isEncrypt;
  private String encrytType;
  private Integer sequence;
  private String content;
  private String paramList;
  private String returnCode;
  private String returnMessage;
  private String sourceMessageID;
  private String remark;
  private Date createTime;
  private Date lastUpdateTime;
  private List<MsgTransferInfo> msgTransferInfos;

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
  public String getMessageID() {
    return this.messageID;
  }
  public void setMessageID(String messageID) {
    this.messageID = messageID;
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
  public String getIsZip() {
    return this.isZip;
  }
  public void setIsZip(String isZip) {
    this.isZip = isZip;
  }
  public String getZipType() {
    return this.zipType;
  }
  public void setZipType(String zipType) {
    this.zipType = zipType;
  }
  public String getIsEncrypt() {
    return this.isEncrypt;
  }
  public void setIsEncrypt(String isEncrypt) {
    this.isEncrypt = isEncrypt;
  }
  public String getEncrytType() {
    return this.encrytType;
  }
  public void setEncrytType(String encrytType) {
    this.encrytType = encrytType;
  }
  public Integer getSequence() {
    return this.sequence;
  }
  public void setSequence(Integer sequence) {
    this.sequence = sequence;
  }
  public String getContent() {
    return this.content;
  }
  public void setContent(String content) {
    this.content = content;
  }
  public String getParamList() {
    return this.paramList;
  }
  public void setParamList(String paramList) {
    this.paramList = paramList;
  }
  public String getReturnCode() {
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
  public String getRemark() {
    return this.remark;
  }
  public void setRemark(String remark) {
    this.remark = remark;
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

  public List<MsgTransferInfo> getMsgTransferInfos()
  {
    return this.msgTransferInfos;
  }
  public void setMsgTransferInfos(List<MsgTransferInfo> msgTransferInfos) {
    this.msgTransferInfos = msgTransferInfos;
  }

  public String getSourceAppID()
  {
    return this.sourceAppID;
  }
  public void setSourceAppID(String sourceAppID) {
    this.sourceAppID = sourceAppID;
  }

  public String toString()
  {
    StringBuffer str = new StringBuffer();
    str.append(getClass().getName()).append("@").append(hashCode()).append("{").append(SystemUtils.LINE_SEPARATOR);
    str.append("\tid=").append(getId()).append(SystemUtils.LINE_SEPARATOR);
    str.append("\tsourceID=").append(getSourceID()).append(SystemUtils.LINE_SEPARATOR);
    str.append("\tdestinationID=").append(getDestinationID()).append(SystemUtils.LINE_SEPARATOR);
    str.append("\testinationAppID=").append(getDestinationAppID()).append(SystemUtils.LINE_SEPARATOR);
    str.append("\tlobalBusinessID=").append(getGlobalBusinessID()).append(SystemUtils.LINE_SEPARATOR);
    str.append("\tmessageID=").append(getMessageID()).append(SystemUtils.LINE_SEPARATOR);
    str.append("\tbusinessType=").append(getBusinessType()).append(SystemUtils.LINE_SEPARATOR);
    str.append("\tsyncFlag=").append(getSyncFlag()).append(SystemUtils.LINE_SEPARATOR);
    str.append("\tisZip=").append(getIsZip()).append(SystemUtils.LINE_SEPARATOR);
    str.append("\tzipType=").append(getZipType()).append(SystemUtils.LINE_SEPARATOR);
    str.append("\tisEncrypt=").append(getIsEncrypt()).append(SystemUtils.LINE_SEPARATOR);
    str.append("\tencrytType=").append(getEncrytType()).append(SystemUtils.LINE_SEPARATOR);
    str.append("\tsequence=").append(getSequence()).append(SystemUtils.LINE_SEPARATOR);
    str.append("\tcontent=").append(getContent()).append(SystemUtils.LINE_SEPARATOR);
    str.append("\tparamList=").append(getParamList()).append(SystemUtils.LINE_SEPARATOR);
    str.append("\treturnCode=").append(getReturnCode()).append(SystemUtils.LINE_SEPARATOR);
    str.append("\treturnMessage=").append(getReturnMessage()).append(SystemUtils.LINE_SEPARATOR);
    str.append("\tsourceMessageID=").append(getSourceMessageID()).append(SystemUtils.LINE_SEPARATOR);
    str.append("\tremark=").append(getRemark()).append(SystemUtils.LINE_SEPARATOR);
    str.append("\tcreateTime=").append(DateUtils.dateTimeToString(getCreateTime())).append(SystemUtils.LINE_SEPARATOR);
    str.append("\tlastUpdateTime=").append(DateUtils.dateTimeToString(getLastUpdateTime())).append(SystemUtils.LINE_SEPARATOR);
    str.append("\tsourceAppID=").append(getSourceAppID()).append(SystemUtils.LINE_SEPARATOR);
    str.append("}").append(SystemUtils.LINE_SEPARATOR);
    return str.toString();
  }
}