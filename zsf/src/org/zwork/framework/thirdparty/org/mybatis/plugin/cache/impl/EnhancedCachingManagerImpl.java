package org.zwork.framework.thirdparty.org.mybatis.plugin.cache.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.ibatis.cache.Cache;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.parsing.XNode;
import org.apache.ibatis.parsing.XPathParser;
import org.zwork.framework.thirdparty.org.mybatis.plugin.cache.CacheKeysPool;
import org.zwork.framework.thirdparty.org.mybatis.plugin.cache.EnhancedCachingManager;

public class EnhancedCachingManagerImpl
  implements EnhancedCachingManager
{
  private Map<String, Set<String>> observers = new ConcurrentHashMap();

  private CacheKeysPool sharedCacheKeysPool = new CacheKeysPool();

  private Map<String, Cache> holds = new ConcurrentHashMap();
  private boolean initialized = false;
  private boolean cacheEnabled = false;
  private static EnhancedCachingManagerImpl enhancedCacheManager;

  public static EnhancedCachingManagerImpl getInstance()
  {
    return enhancedCacheManager == null ? (EnhancedCachingManagerImpl.enhancedCacheManager = new EnhancedCachingManagerImpl()) : enhancedCacheManager;
  }

  public void refreshCacheKey(CacheKeysPool keysPool) {
    this.sharedCacheKeysPool.putAll(keysPool);
  }

  public void clearRelatedCaches(Set<String> set)
  {
    for (String observable : set)
    {
      Set relatedStatements = (Set)this.observers.get(observable);
      Iterator localIterator3;
      for (Iterator localIterator2 = relatedStatements.iterator(); localIterator2.hasNext(); 
        localIterator3.hasNext())
      {
        String statementId = (String)localIterator2.next();

        Cache cache = (Cache)this.holds.get(statementId);
        Set cacheKeys = this.sharedCacheKeysPool.get(statementId);
        localIterator3 = cacheKeys.iterator(); 
        Object cacheKey = localIterator3.next();

        cache.removeObject(cacheKey);
      }

      this.sharedCacheKeysPool.remove(observable);
    }
  }

  public boolean isInitialized() { return this.initialized; }


  public void initialize(Properties properties)
  {
    String dependency = properties.getProperty("dependency");
    if ((!"".equals(dependency)) && (dependency != null))
    {
      try
      {
        InputStream inputStream = Resources.getResourceAsStream(dependency);
        XPathParser parser = new XPathParser(inputStream);
        List<XNode> statements = parser.evalNodes("/dependencies/statements/statement");
        for (XNode node : statements)
        {
          Set temp = new HashSet();
          List<XNode> obs = node.evalNodes("observer");
          for (XNode observer : obs)
          {
            temp.add(observer.getStringAttribute("id"));
          }
          this.observers.put(node.getStringAttribute("id"), temp);
        }
        this.initialized = true;
      } catch (IOException e) {
        e.printStackTrace();
      }

    }

    String cacheEnabled = properties.getProperty("cacheEnabled", "true");
    if ("true".equals(cacheEnabled))
    {
      this.cacheEnabled = true;
    }
  }

  public void appendStatementCacheMap(String statementId, Cache cache) {
    if ((this.holds.containsKey(statementId)) && (this.holds.get(statementId) != null))
    {
      return;
    }
    this.holds.put(statementId, cache);
  }

  public boolean isCacheEnabled() {
    return this.cacheEnabled;
  }
}