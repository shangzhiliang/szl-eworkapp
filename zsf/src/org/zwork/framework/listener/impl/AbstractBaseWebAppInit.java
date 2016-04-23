package org.zwork.framework.listener.impl;

import javax.servlet.ServletContextEvent;


public abstract class AbstractBaseWebAppInit
  implements WebAppInit
{
  private String description;

  public abstract void contextInitialized(ServletContextEvent paramServletContextEvent)
    throws Exception;

  public void contextDestroyed(ServletContextEvent event)
    throws Exception
  {
  }

  public String getDescription()
  {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}