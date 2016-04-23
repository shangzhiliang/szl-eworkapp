package com.org.decp.msg.model;

import java.util.Date;
import com.org.decp.xml.validate.ObjectHolder;

public abstract interface DataExchangePackageHolder<DataExchangePackage> 
	extends ObjectHolder<DataExchangePackage>{
	
	public abstract String getSourceID();

	  public abstract void setSourceID(String paramString);

	  public abstract String getDestinationID();

	  public abstract void setDestinationID(String paramString);

	  public abstract String getDestinationAppID();

	  public abstract void setDestinationAppID(String paramString);

	  public abstract String getGlobalBusinessID();

	  public abstract void setGlobalBusinessID(String paramString);

	  public abstract String getBusinessType();

	  public abstract void setBusinessType(String paramString);

	  public abstract String getSyncFlag();

	  public abstract void setSyncFlag(String paramString);

	  public abstract String getMessageID();

	  public abstract void setMessageID(String paramString);

	  public abstract String getSourceAppID();

	  public abstract void setSourceAppID(String paramString);

	  public abstract String getSenderID();

	  public abstract void setSenderID(String paramString);

	  public abstract String getReceiverID();

	  public abstract void setReceiverID(String paramString);

	  public abstract String getIsRetry();

	  public abstract void setIsRetry(String paramString);

	  public abstract Date getSendTime();

	  public abstract void setSendTime(Date paramDate);

	  public abstract String getSenderAppID();

	  public abstract void setSenderAppID(String paramString);

	  public abstract String getReceiverAppID();

	  public abstract void setReceiverAppID(String paramString);

	  public abstract String getIsZip();

	  public abstract void setIsZip(String paramString);

	  public abstract String getZipType();

	  public abstract void setZipType(String paramString);

	  public abstract String getIsEncrypt();

	  public abstract void setIsEncrypt(String paramString);

	  public abstract String getEncrytType();

	  public abstract void setEncrytType(String paramString);

	  public abstract Integer getSequence();

	  public abstract void setSequence(Integer paramInteger);

	  public abstract String getContent();

	  public abstract void setContent(String paramString);

	  public abstract String getReturnCode();

	  public abstract void setReturnCode(String paramString);

	  public abstract String getReturnMessage();

	  public abstract void setReturnMessage(String paramString);

	  public abstract void setTransferInfoSourceMessageID(String paramString);

	  public abstract String getTransferInfoSourceMessageID();

	  public abstract String getReturnStateSourceMessageID();

	  public abstract void setReturnStateSourceMessageID(String paramString);

	  public abstract DataExchangePackage getData();

	  public abstract void flush();
}
