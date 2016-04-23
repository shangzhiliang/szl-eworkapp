package org.zwork.framework.struts2.interceptor;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class CheckSessionIsAvailableInterceptor extends AbstractInterceptor
{
  private static final long serialVersionUID = 1L;
  private static final Logger LOGGER = LoggerFactory.getLogger(CheckSessionIsAvailableInterceptor.class);
  private static final String SESSION_KEY = "user";

  public String intercept(ActionInvocation invocation)
    throws Exception
  {
    LOGGER.debug("��ʼ����session��Ч�Լ��");

    Map session = invocation.getInvocationContext().getSession();
    if (session == null) {
      return "session-timeout";
    }
    Object object = session.get("user");
    if (object == null) {
      LOGGER.debug("session��Ч!");
      return "session-timeout";
    }
    LOGGER.debug("session��Ч�Լ�������session��ǰΪ��Ч״̬");
    return invocation.invoke();
  }
}