package org.zwork.framework.struts2.interceptor;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.swork.common.utils.StringUtils;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;


public class IgnoreEmptyValueInterceptor extends AbstractInterceptor
{
  private static final long serialVersionUID = 1L;
  private static final Logger LOGGER = LoggerFactory.getLogger(IgnoreEmptyValueInterceptor.class);

  public String intercept(ActionInvocation invocation) throws Exception
  {
    Map parametersBeforeIgnore = invocation.getInvocationContext().getParameters();
    Map parameterAfterIgnore = new HashMap();
    LOGGER.debug("忽略空值前参数值为[{}]", parametersBeforeIgnore);
    if (parametersBeforeIgnore != null) {
      Iterator iterator = parametersBeforeIgnore.keySet().iterator();
      while (iterator.hasNext()) {
        String key = (String)iterator.next();
        Object[] valueArray = (Object[])parametersBeforeIgnore.get(key);
        if (valueArray != null) {
          if (valueArray.length > 1) {
            parameterAfterIgnore.put(key, parametersBeforeIgnore.get(key));
          }
          else if (!StringUtils.isEmpty((String)valueArray[0])) {
            parameterAfterIgnore.put(key, parametersBeforeIgnore.get(key));
          }
        }
      }

      LOGGER.debug("忽略空值后的参数值为[{}]" + parameterAfterIgnore);
      invocation.getInvocationContext().setParameters(parameterAfterIgnore);
    }
    return invocation.invoke();
  }
}