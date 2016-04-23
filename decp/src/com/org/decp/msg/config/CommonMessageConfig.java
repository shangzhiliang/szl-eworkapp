package com.org.decp.msg.config;

import java.util.Map;

import com.org.decp.msg.trade.server.AbstractBusiTrade;

public class CommonMessageConfig {
	  public String sourceID;
	  public String destinationID;
	  public String sourceAppID;
	  public String destinationAppID;
	  public String syncFlag;
	  public String senderID;
	  public String receiverID;
	  public String senderAppID;
	  public String receiverAppID;
	  public Map<String, TradeMessageConfig> tradeMsgCfgMap;
	  public Map<String, AbstractBusiTrade> busiTradeMap;

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

	  public String getSourceAppID() {
	    return this.sourceAppID;
	  }

	  public void setSourceAppID(String sourceAppID) {
	    this.sourceAppID = sourceAppID;
	  }

	  public String getDestinationAppID() {
	    return this.destinationAppID;
	  }

	  public void setDestinationAppID(String destinationAppID) {
	    this.destinationAppID = destinationAppID;
	  }

	  public String getSyncFlag() {
	    return this.syncFlag;
	  }

	  public void setSyncFlag(String syncFlag) {
	    this.syncFlag = syncFlag;
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

	  public Map<String, TradeMessageConfig> getTradeMsgCfgMap() {
	    return this.tradeMsgCfgMap;
	  }

	  public void setTradeMsgCfgMap(Map<String, TradeMessageConfig> tradeMsgCfgMap) {
	    this.tradeMsgCfgMap = tradeMsgCfgMap;
	  }

	  public Map<String, AbstractBusiTrade> getBusiTradeMap() {
	    return this.busiTradeMap;
	  }

	  public void setBusiTradeMap(Map<String, AbstractBusiTrade> busiTradeMap) {
	    this.busiTradeMap = busiTradeMap;
	  }
}
