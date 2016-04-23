package com.org.decp.msg.model.base;

import com.org.decp.common.util.StringUtils;
import com.org.decp.msg.model.DataExchangePackageHolder;
import com.org.decp.xml.Convert;
import com.org.decp.xml.DefautObjectWrapper;
import com.org.decp.xml.ValidatorFactory;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class DefaultDataExchangePackageHolder extends PreDataExchangePackageHolder
  implements DataExchangePackageHolder<DataExchangePackage>
{
  public DefaultDataExchangePackageHolder(DefautObjectWrapper<DataExchangePackage> defautObjectWrapper)
  {
    super(defautObjectWrapper);
  }

  public DefaultDataExchangePackageHolder(Convert<DataExchangePackage> convert)
  {
    this.defautObjectWrapper = new DefautObjectWrapper(convert);
    this.dataExchangePackage = new DataExchangePackage();
  }

  public DefaultDataExchangePackageHolder(ValidatorFactory<DataExchangePackage> validatorFactory)
  {
    this.defautObjectWrapper = validatorFactory.getObjectWrapper();
    if (this.defautObjectWrapper != null)
      this.dataExchangePackage = new DataExchangePackage();
  }

  public void flush()
  {
    this.defautObjectWrapper = null;
    this.dataExchangePackage = null;
  }

  public String getSourceID()
  {
    return this.dataExchangePackage.getEnvelopInfo().getSourceID();
  }

  public void setSourceID(String sourceID)
  {
    this.dataExchangePackage.getEnvelopInfo().setSourceID(sourceID);
  }

  public String getDestinationID()
  {
    return this.dataExchangePackage.getEnvelopInfo().getDestinationID();
  }

  public void setDestinationID(String destinationID)
  {
    this.dataExchangePackage.getEnvelopInfo().setDestinationID(destinationID);
  }

  public String getDestinationAppID()
  {
    return this.dataExchangePackage.getEnvelopInfo().getDestinationAppID();
  }

  public void setDestinationAppID(String destinationAppID)
  {
    this.dataExchangePackage.getEnvelopInfo().setDestinationAppID(destinationAppID);
  }

  public String getGlobalBusinessID()
  {
    return this.dataExchangePackage.getEnvelopInfo().getGlobalBusinessID();
  }

  public void setGlobalBusinessID(String globalBusinessID)
  {
    this.dataExchangePackage.getEnvelopInfo().setGlobalBusinessID(globalBusinessID);
  }

  public String getBusinessType()
  {
    return this.dataExchangePackage.getEnvelopInfo().getBusinessType();
  }

  public void setBusinessType(String businessType)
  {
    this.dataExchangePackage.getEnvelopInfo().setBusinessType(businessType);
  }

  public String getSyncFlag()
  {
    return this.dataExchangePackage.getEnvelopInfo().getSyncFlag();
  }

  public void setSyncFlag(String syncFlag)
  {
    this.dataExchangePackage.getEnvelopInfo().setSyncFlag(syncFlag);
  }

  public String getMessageID()
  {
    return this.dataExchangePackage.getEnvelopInfo().getMessageID();
  }

  public void setMessageID(String messageID)
  {
    this.dataExchangePackage.getEnvelopInfo().setMessageID(messageID);
  }

  public String getSourceAppID()
  {
    return this.dataExchangePackage.getEnvelopInfo().getSourceAppID();
  }

  public void setSourceAppID(String sourceAppID)
  {
    this.dataExchangePackage.getEnvelopInfo().setSourceAppID(sourceAppID);
  }

  public String getSenderID()
  {
    return this.dataExchangePackage.getTransferInfo().getSenderID();
  }

  public void setSenderID(String senderID)
  {
    this.dataExchangePackage.getTransferInfo().setSenderID(senderID);
  }

  public String getReceiverID()
  {
    return this.dataExchangePackage.getTransferInfo().getReceiverID();
  }

  public void setReceiverID(String receiverID)
  {
    this.dataExchangePackage.getTransferInfo().setReceiverID(receiverID);
  }

  public String getReturnStateSourceMessageID()
  {
    return this.dataExchangePackage.getReturnState().getSourceMessageID();
  }

  public void setReturnStateSourceMessageID(String sourceMessageID)
  {
    this.dataExchangePackage.getReturnState().setSourceMessageID(sourceMessageID);
  }

  public void setTransferInfoSourceMessageID(String sourceMessageID)
  {
    this.dataExchangePackage.getTransferInfo().setSourceMessageID(sourceMessageID);
  }

  public String getTransferInfoSourceMessageID()
  {
    return this.dataExchangePackage.getTransferInfo().getSourceMessageID();
  }

  public String getIsRetry()
  {
    return this.dataExchangePackage.getTransferInfo().getIsRetry();
  }

  public void setIsRetry(String isRetry)
  {
    this.dataExchangePackage.getTransferInfo().setIsRetry(isRetry);
  }

  public Date getSendTime()
  {
    return this.dataExchangePackage.getTransferInfo().getSendTime();
  }

  public void setSendTime(Date sendTime)
  {
    this.dataExchangePackage.getTransferInfo().setSendTime(sendTime);
  }

  public String getSenderAppID()
  {
    return this.dataExchangePackage.getTransferInfo().getSenderAppID();
  }

  public void setSenderAppID(String senderAppID)
  {
    this.dataExchangePackage.getTransferInfo().setSenderAppID(senderAppID);
  }

  public String getReceiverAppID()
  {
    return this.dataExchangePackage.getTransferInfo().getReceiverAppID();
  }

  public void setReceiverAppID(String receiverAppID)
  {
    this.dataExchangePackage.getTransferInfo().setReceiverAppID(receiverAppID);
  }

  public String getIsZip()
  {
    return this.dataExchangePackage.getContentControl().getIsZip();
  }

  public void setIsZip(String isZip)
  {
    this.dataExchangePackage.getContentControl().setIsZip(isZip);
  }

  public String getZipType()
  {
    return this.dataExchangePackage.getContentControl().getZipType();
  }

  public void setZipType(String zipType)
  {
    this.dataExchangePackage.getContentControl().setZipType(zipType);
  }

  public String getIsEncrypt()
  {
    return this.dataExchangePackage.getContentControl().getIsEncrypt();
  }

  public void setIsEncrypt(String isEncrypt)
  {
    this.dataExchangePackage.getContentControl().setIsEncrypt(isEncrypt);
  }

  public String getEncrytType()
  {
    return this.dataExchangePackage.getContentControl().getEncrytType();
  }

  public void setEncrytType(String encrytType)
  {
    this.dataExchangePackage.getContentControl().setEncrytType(encrytType);
  }

  public Integer getSequence()
  {
    return this.dataExchangePackage.getPackageInfo().getSequence();
  }

  public void setSequence(Integer sequence)
  {
    this.dataExchangePackage.getPackageInfo().setSequence(sequence);
  }

  public String getContent()
  {
    String content = this.dataExchangePackage.getPackageInfo().getContent();
    if (!StringUtils.isEmpty(content)) {
      Pattern p = Pattern.compile(".<![CDATA[(.*?)]]>.*");
      Matcher m = p.matcher(content);
      if (m.matches()) {
        return m.group(1);
      }
    }

    return content;
  }

  public void setContent(String content)
  {
    this.dataExchangePackage.getPackageInfo().setContent(content);
  }

  public String getReturnCode()
  {
    return this.dataExchangePackage.getReturnState().getReturnCode();
  }

  public void setReturnCode(String returnCode)
  {
    this.dataExchangePackage.getReturnState().setReturnCode(returnCode);
  }

  public String getReturnMessage()
  {
    return this.dataExchangePackage.getReturnState().getReturnMessage();
  }

  public void setReturnMessage(String returnMessage)
  {
    this.dataExchangePackage.getReturnState().setReturnMessage(returnMessage);
  }

  public DataExchangePackage getData()
  {
    return this.dataExchangePackage;
  }
}