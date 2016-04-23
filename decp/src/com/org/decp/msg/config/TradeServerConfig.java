package com.org.decp.msg.config;

public class TradeServerConfig {
	public String type;
	  public String serviceUrl;
	  public String userName;
	  public String password;
	  public String port;
	  public String queueName;
	  public String virtualHost;
	  public String connectionTimeout;
	  public String routintKey;
	  public String exchangeName;
	  public String exchangeType;

	  public String getType()
	  {
	    return this.type;
	  }

	  public void setType(String type) {
	    this.type = type;
	  }

	  public String getServiceUrl() {
	    return this.serviceUrl;
	  }

	  public void setServiceUrl(String serviceUrl) {
	    this.serviceUrl = serviceUrl;
	  }

	  public String getUserName() {
	    return this.userName;
	  }

	  public void setUserName(String userName) {
	    this.userName = userName;
	  }

	  public String getPassword() {
	    return this.password;
	  }

	  public void setPassword(String password) {
	    this.password = password;
	  }

	  public String getPort() {
	    return this.port;
	  }

	  public void setPort(String port) {
	    this.port = port;
	  }

	  public String getQueueName() {
	    return this.queueName;
	  }

	  public void setQueueName(String queueName) {
	    this.queueName = queueName;
	  }

	  public String getVirtualHost() {
	    return this.virtualHost;
	  }

	  public void setVirtualHost(String virtualHost) {
	    this.virtualHost = virtualHost;
	  }

	  public String getConnectionTimeout() {
	    return this.connectionTimeout;
	  }

	  public void setConnectionTimeout(String connectionTimeout) {
	    this.connectionTimeout = connectionTimeout;
	  }

	  public String getRoutintKey() {
	    return this.routintKey;
	  }

	  public void setRoutintKey(String routintKey) {
	    this.routintKey = routintKey;
	  }

	  public String getExchangeName() {
	    return this.exchangeName;
	  }

	  public void setExchangeName(String exchangeName) {
	    this.exchangeName = exchangeName;
	  }

	  public String getExchangeType() {
	    return this.exchangeType;
	  }

	  public void setExchangeType(String exchangeType) {
	    this.exchangeType = exchangeType;
	  }
}
