package org.zwork.framework.thirdparty.org.springframework;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;


public class SpringBeanContext
  implements ApplicationContextAware
{
  private static Logger logger = LoggerFactory.getLogger(SpringBeanContext.class);
  protected static ApplicationContext applicationContext;

  public void setApplicationContext(ApplicationContext applicationContext)
    throws BeansException
  {
    applicationContext = applicationContext;
  }

  public static Object getBean(String beanName)
  {
    if (applicationContext == null) {
      logger.error("未初始化Spring上下文");
      return null;
    }
    if (!applicationContext.containsBean(beanName)) {
      logger.warn("Spring上下文中不存在要查找的对象[{}]", beanName);
      return null;
    }
    return applicationContext.getBean(beanName);
  }

  public String[] getBeanNamesForType(Class<?> type)
  {
    return applicationContext.getBeanNamesForType(type);
  }
}