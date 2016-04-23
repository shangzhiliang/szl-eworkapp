package org.zwork.framework.thirdparty.org.springframework.datasource.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class DataSourceKeyHolder
{
  private static Logger logger = LoggerFactory.getLogger(DataSourceKeyHolder.class);

  private static final ThreadLocal<String> contextHolder = new ThreadLocal();

  public static void setDataSourceKey(String dataSourceKey)
  {
    contextHolder.set(dataSourceKey);
  }

  public static String getDataSourceKey()
  {
    String key = (String)contextHolder.get();
    logger.info("Thread:" + Thread.currentThread().getName() + "dataSource key is " + key);
    return key;
  }

  public static void clearDataSourceKey()
  {
    contextHolder.remove();
  }
}