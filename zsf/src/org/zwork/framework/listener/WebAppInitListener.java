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
    LOGGER.info("��ʼ��WebӦ�ó���[��ʼ]");

    WebAppInitConfig config = (WebAppInitConfig)SpringBeanContext.getBean("webAppInitConfig");
    if (config != null) {
      List<WebAppInit> webAppInits = config.getWebAppInits();
      if (webAppInits != null) {
        for (WebAppInit webAppInit : webAppInits) {
          try {
            webAppInit.contextInitialized(event);
            LOGGER.info(webAppInit.getDescription() + "[���]");
          } catch (Exception e) {
            LOGGER.error(webAppInit.getDescription() + "[����]", e);
            throw new RuntimeException(e);
          }
        }
      }
    }

    LOGGER.info("��ʼ��WebӦ�ó���[����]:");
  }

  public void contextDestroyed(ServletContextEvent event)
  {
    LOGGER.info("�ͷ�WebӦ��������[��ʼ]");

    WebAppInitConfig config = (WebAppInitConfig)SpringBeanContext.getBean("webAppInitConfig");
    if (config != null) {
      List<WebAppInit> webAppInits = config.getWebAppInits();

      if (webAppInits != null) {
        for (WebAppInit webAppInit : webAppInits) {
          try {
            webAppInit.contextDestroyed(event);
            LOGGER.info(webAppInit.getDescription() + "[���]");
          } catch (Exception e) {
            LOGGER.info(webAppInit.getDescription() + "[����]");
            throw new RuntimeException(e);
          }
        }
      }
    }

    LOGGER.info("�ͷ�WebӦ��������[����]:");
  }
}