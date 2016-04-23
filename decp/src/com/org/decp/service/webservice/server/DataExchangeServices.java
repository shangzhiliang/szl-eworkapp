package com.org.decp.service.webservice.server;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(targetNamespace="http://server.webservice.service.decp.yinker.com", name="decpHandle", serviceName="decpHandle")
public abstract interface DataExchangeServices
{
  @WebMethod(operationName="receiveMessage")
  public abstract String receiveMessage(String paramString);
}