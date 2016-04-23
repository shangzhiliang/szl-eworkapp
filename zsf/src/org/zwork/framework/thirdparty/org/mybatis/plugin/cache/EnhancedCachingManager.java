package org.zwork.framework.thirdparty.org.mybatis.plugin.cache;

import java.util.Properties;
import java.util.Set;

import org.apache.ibatis.cache.Cache;


public abstract interface EnhancedCachingManager
{
  public abstract boolean isInitialized();

  public abstract boolean isCacheEnabled();

  public abstract void initialize(Properties paramProperties);

  public abstract void refreshCacheKey(CacheKeysPool paramCacheKeysPool);

  public abstract void clearRelatedCaches(Set<String> paramSet);

  public abstract void appendStatementCacheMap(String paramString, Cache paramCache);
}