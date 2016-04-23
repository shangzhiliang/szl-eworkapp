package com.org.decp.service.webservice.server.impl;

import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zwork.framework.thirdparty.org.springframework.SpringBeanContext;

import com.org.decp.msg.DecpMsgService;
import com.org.decp.service.webservice.server.DataExchangeServices;

@WebService(endpointInterface="com.yinker.decp.service.webservice.server.DataExchangeServices", targetNamespace="http://server.webservice.service.decp.yinker.com")
public class DataExchangeServicesImpl
  implements DataExchangeServices
{
  private static final Logger LOGGER = LoggerFactory.getLogger(DataExchangeServicesImpl.class);

  public String receiveMessage(String message)
  {
    DecpMsgService decpMsgService = (DecpMsgService)SpringBeanContext.getBean("decpMsgService");
    LOGGER.debug("报文接收成功" + message);

    return decpMsgService.parseAndForwardMessage(message);
  }
}