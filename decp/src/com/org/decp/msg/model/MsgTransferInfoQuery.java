package com.org.decp.msg.model;

import java.util.Date;

import org.swork.common.utils.DateUtils;
import org.swork.common.utils.SystemUtils;

import com.org.decp.msg.model.base.MsgTransferInfo;


public class MsgTransferInfoQuery extends MsgTransferInfo
{
  private static final long serialVersionUID = 1L;
  private Date sendTimeStart;
  private Date sendTimeEnd;
  private Date createTimeStart;
  private Date createTimeEnd;
  private Date lastUpdateTimeStart;
  private Date lastUpdateTimeEnd;

  public Date getSendTimeStart()
  {
    return this.sendTimeStart;
  }

  public void setSendTimeStart(Date sendTimeStart) {
    this.sendTimeStart = sendTimeStart;
  }

  public Date getSendTimeEnd() {
    return this.sendTimeEnd;
  }

  public void setSendTimeEnd(Date sendTimeEnd) {
    this.sendTimeEnd = sendTimeEnd;
  }

  public Date getCreateTimeStart() {
    return this.createTimeStart;
  }

  public void setCreateTimeStart(Date createTimeStart) {
    this.createTimeStart = createTimeStart;
  }

  public Date getCreateTimeEnd() {
    return this.createTimeEnd;
  }

  public void setCreateTimeEnd(Date createTimeEnd) {
    this.createTimeEnd = createTimeEnd;
  }
  public Date getLastUpdateTimeStart() {
    return this.lastUpdateTimeStart;
  }

  public void setLastUpdateTimeStart(Date lastUpdateTimeStart) {
    this.lastUpdateTimeStart = lastUpdateTimeStart;
  }

  public Date getLastUpdateTimeEnd() {
    return this.lastUpdateTimeEnd;
  }

  public void setLastUpdateTimeEnd(Date lastUpdateTimeEnd) {
    this.lastUpdateTimeEnd = lastUpdateTimeEnd;
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
    str.append("\tsendTimeStart=").append(DateUtils.dateTimeToString(getSendTimeStart())).append(SystemUtils.LINE_SEPARATOR);
    str.append("\tsendTime=").append(DateUtils.dateTimeToString(getSendTime())).append(SystemUtils.LINE_SEPARATOR);
    str.append("\tsendTimeEnd=").append(DateUtils.dateTimeToString(getSendTimeEnd())).append(SystemUtils.LINE_SEPARATOR);
    str.append("\tcreateTimeStart=").append(DateUtils.dateTimeToString(getCreateTimeStart())).append(SystemUtils.LINE_SEPARATOR);
    str.append("\tcreateTime=").append(DateUtils.dateTimeToString(getCreateTime())).append(SystemUtils.LINE_SEPARATOR);
    str.append("\tcreateTimeEnd=").append(DateUtils.dateTimeToString(getCreateTimeEnd())).append(SystemUtils.LINE_SEPARATOR);
    str.append("\tlastUpdateTimeStart=").append(DateUtils.dateTimeToString(getLastUpdateTimeStart())).append(SystemUtils.LINE_SEPARATOR);
    str.append("\tlastUpdateTime=").append(DateUtils.dateTimeToString(getLastUpdateTime())).append(SystemUtils.LINE_SEPARATOR);
    str.append("\tlastUpdateTimeEnd=").append(DateUtils.dateTimeToString(getLastUpdateTimeEnd())).append(SystemUtils.LINE_SEPARATOR);
    str.append("\treceiverAppID=").append(getReceiverAppID()).append(SystemUtils.LINE_SEPARATOR);
    str.append("\tsenderAppID=").append(getSenderAppID()).append(SystemUtils.LINE_SEPARATOR);
    str.append("}").append(SystemUtils.LINE_SEPARATOR);
    return str.toString();
  }
}