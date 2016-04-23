package org.zwork.framework.listener.impl;

import java.net.URL;

import javax.servlet.ServletContextEvent;

import org.apache.log4j.xml.DOMConfigurator;
import org.springframework.util.ResourceUtils;

public class Log4jWebAppInit extends AbstractBaseWebAppInit
{
  private String log4jConfigFilePath;

  public void contextInitialized(ServletContextEvent event)
    throws Exception
  {
    if (this.log4jConfigFilePath != null) {
      System.out.println("Initializing log4j from [" + this.log4jConfigFilePath + "]");
      URL url = ResourceUtils.getURL(this.log4jConfigFilePath);
      DOMConfigurator.configure(url);
    }
  }

  public String getLog4jConfigFilePath()
  {
    return this.log4jConfigFilePath;
  }

  public void setLog4jConfigFilePath(String log4jConfigFilePath)
  {
    this.log4jConfigFilePath = log4jConfigFilePath;
  }
}