package com.org.decp.msg.model;

import java.util.Date;

import org.apache.commons.lang.SystemUtils;
import org.swork.common.utils.DateUtils;
import com.org.decp.msg.model.base.MsgExchangePackage;

public class MsgExchangePackageQuery extends MsgExchangePackage
{
  private static final long serialVersionUID = 1L;
  private Integer sequenceStart;
  private Integer sequenceEnd;
  private Date createTimeStart;
  private Date createTimeEnd;
  private Date lastUpdateTimeStart;
  private Date lastUpdateTimeEnd;

  public Integer getSequenceStart()
  {
    return this.sequenceStart;
  }

  public void setSequenceStart(Integer sequenceStart) {
    this.sequenceStart = sequenceStart;
  }

  public Integer getSequenceEnd() {
    return this.sequenceEnd;
  }

  public void setSequenceEnd(Integer sequenceEnd) {
    this.sequenceEnd = sequenceEnd;
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
    str.append("\tsequenceStart=").append(getSequenceStart()).append(SystemUtils.LINE_SEPARATOR);
    str.append("\tsequence=").append(getSequence()).append(SystemUtils.LINE_SEPARATOR);
    str.append("\tsequenceEnd=").append(getSequenceEnd()).append(SystemUtils.LINE_SEPARATOR);
    str.append("\tcontent=").append(getContent()).append(SystemUtils.LINE_SEPARATOR);
    str.append("\tparamList=").append(getParamList()).append(SystemUtils.LINE_SEPARATOR);
    str.append("\treturnCode=").append(getReturnCode()).append(SystemUtils.LINE_SEPARATOR);
    str.append("\treturnMessage=").append(getReturnMessage()).append(SystemUtils.LINE_SEPARATOR);
    str.append("\tsourceMessageID=").append(getSourceMessageID()).append(SystemUtils.LINE_SEPARATOR);
    str.append("\tremark=").append(getRemark()).append(SystemUtils.LINE_SEPARATOR);
    str.append("\tcreateTimeStart=").append(DateUtils.dateTimeToString(getCreateTimeStart())).append(SystemUtils.LINE_SEPARATOR);
    str.append("\tcreateTime=").append(DateUtils.dateTimeToString(getCreateTime())).append(SystemUtils.LINE_SEPARATOR);
    str.append("\tcreateTimeEnd=").append(DateUtils.dateTimeToString(getCreateTimeEnd())).append(SystemUtils.LINE_SEPARATOR);
    str.append("\tlastUpdateTimeStart=").append(DateUtils.dateTimeToString(getLastUpdateTimeStart())).append(SystemUtils.LINE_SEPARATOR);
    str.append("\tlastUpdateTime=").append(DateUtils.dateTimeToString(getLastUpdateTime())).append(SystemUtils.LINE_SEPARATOR);
    str.append("\tlastUpdateTimeEnd=").append(DateUtils.dateTimeToString(getLastUpdateTimeEnd())).append(SystemUtils.LINE_SEPARATOR);
    str.append("\tsourceAppID=").append(getSourceAppID()).append(SystemUtils.LINE_SEPARATOR);
    str.append("}").append(SystemUtils.LINE_SEPARATOR);
    return str.toString();
  }
}