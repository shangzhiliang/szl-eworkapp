package org.zwork.framework.listener.impl;

import javax.servlet.ServletContextEvent;


public abstract interface WebAppInit
{
  public abstract String getDescription();

  public abstract void contextInitialized(ServletContextEvent paramServletContextEvent)
    throws Exception;

  public abstract void contextDestroyed(ServletContextEvent paramServletContextEvent)
    throws Exception;
}