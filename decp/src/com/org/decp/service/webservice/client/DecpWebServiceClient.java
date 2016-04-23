package com.org.decp.service.webservice.client;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import com.org.decp.service.webservice.server.DataExchangeServices;


public class DecpWebServiceClient
{
  public String sendMessage(String addredd, String message)
    throws Exception
  {
    JaxWsProxyFactoryBean svr = new JaxWsProxyFactoryBean();
    svr.setServiceClass(DataExchangeServices.class);
    svr.setAddress(addredd);
    DataExchangeServices decpService = (DataExchangeServices)svr.create();
    return decpService.receiveMessage(message);
  }
}