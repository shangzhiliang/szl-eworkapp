package com.org.decp.service;

import com.org.decp.service.mq.producer.DecpRabbitMqProducer;
import com.org.decp.service.webservice.client.DecpWebServiceClient;


public class DecpServerHandle
{
  private static DecpServerHandle instance = null;

  private DecpWebServiceClient decpWebServiceClient = null;

  private DecpRabbitMqProducer decpRabbitMqProducer = null;

  public static DecpServerHandle getInstance() {
    if (instance == null) {
      synchronized (DecpServerHandle.class) {
        if (instance == null) {
          instance = new DecpServerHandle();
        }
      }
    }
    return instance;
  }

  public DecpWebServiceClient getWebServiceClient()
  {
    if (this.decpWebServiceClient == null) {
      this.decpWebServiceClient = new DecpWebServiceClient();
    }
    return this.decpWebServiceClient;
  }

  public DecpRabbitMqProducer getDecpRabbitMqProducer()
  {
    if (this.decpRabbitMqProducer == null) {
      this.decpRabbitMqProducer = new DecpRabbitMqProducer();
    }
    return this.decpRabbitMqProducer;
  }
}