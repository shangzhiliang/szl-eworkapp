package org.zwork.framework.listener.impl;

import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletContextEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SysPropertyWebAppInit extends AbstractBaseWebAppInit
{
  private Map<String, String> properties;
  private static final Logger LOGGER = LoggerFactory.getLogger(SysPropertyWebAppInit.class);

  public void contextInitialized(ServletContextEvent event) throws Exception
  {
    if (this.properties != null) {
      Properties props = System.getProperties();
      Iterator keys = this.properties.keySet().iterator();
      while (keys.hasNext()) {
        String key = (String)keys.next();
        String value = (String)this.properties.get(key);
        LOGGER.info("property key[{}]:newValue={},oldValue={}", 
          new Object[] { key, value, (String)props.get(key) });
        props.put(key, value);
      }
    }
  }

  public void setProperties(Map<String, String> properties)
  {
    this.properties = properties;
  }
}