package org.zwork.framework.thirdparty.org.springframework.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.zwork.framework.thirdparty.org.springframework.datasource.util.DataSourceKeyHolder;

public class MultipleDataSource extends AbstractRoutingDataSource
{
  private static final Logger LOGGER = LoggerFactory.getLogger(MultipleDataSource.class);

  protected Object determineCurrentLookupKey()
  {
    Object dataSourceKey = DataSourceKeyHolder.getDataSourceKey();
    if (dataSourceKey != null)
      LOGGER.info("采用数据源[dataSourceKey={}]", dataSourceKey);
    else {
      LOGGER.debug("采用默认数据源!");
    }
    return dataSourceKey;
  }
}