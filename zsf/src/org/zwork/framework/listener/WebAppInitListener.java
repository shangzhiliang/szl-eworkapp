package org.zwork.framework.listener;

import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zwork.framework.listener.impl.WebAppInit;
import org.zwork.framework.listener.impl.WebAppInitConfig;
import org.zwork.framework.thirdparty.org.springframework.SpringBeanContext;


public class WebAppInitListener
  implements ServletContextListener
{
  private static final Logger LOGGER = LoggerFactory.getLogger(WebAppInitListener.class);

  public void contextInitialized(ServletContextEvent event)
  {
    LOGGER.info("初始化Web应用程序[开始]");

    WebAppInitConfig config = (WebAppInitConfig)SpringBeanContext.getBean("webAppInitConfig");
    if (config != null) {
      List<WebAppInit> webAppInits = config.getWebAppInits();
      if (webAppInits != null) {
        for (WebAppInit webAppInit : webAppInits) {
          try {
            webAppInit.contextInitialized(event);
            LOGGER.info(webAppInit.getDescription() + "[完成]");
          } catch (Exception e) {
            LOGGER.error(webAppInit.getDescription() + "[出错]", e);
            throw new RuntimeException(e);
          }
        }
      }
    }

    LOGGER.info("初始化Web应用程序[结束]:");
  }

  public void contextDestroyed(ServletContextEvent event)
  {
    LOGGER.info("释放Web应用上下文[开始]");

    WebAppInitConfig config = (WebAppInitConfig)SpringBeanContext.getBean("webAppInitConfig");
    if (config != null) {
      List<WebAppInit> webAppInits = config.getWebAppInits();

      if (webAppInits != null) {
        for (WebAppInit webAppInit : webAppInits) {
          try {
            webAppInit.contextDestroyed(event);
            LOGGER.info(webAppInit.getDescription() + "[完成]");
          } catch (Exception e) {
            LOGGER.info(webAppInit.getDescription() + "[出错]");
            throw new RuntimeException(e);
          }
        }
      }
    }

    LOGGER.info("释放Web应用上下文[结束]:");
  }
}