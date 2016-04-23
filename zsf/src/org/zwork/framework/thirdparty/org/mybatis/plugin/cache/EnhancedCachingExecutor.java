package org.zwork.framework.thirdparty.org.mybatis.plugin.cache;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.session.RowBounds;
import org.zwork.framework.thirdparty.org.mybatis.plugin.cache.impl.EnhancedCachingManagerImpl;

@Intercepts({@org.apache.ibatis.plugin.Signature(args={MappedStatement.class, Object.class, RowBounds.class, org.apache.ibatis.session.ResultHandler.class}, method="query", type=Executor.class), @org.apache.ibatis.plugin.Signature(args={MappedStatement.class, Object.class}, method="update", type=Executor.class), @org.apache.ibatis.plugin.Signature(args={boolean.class}, method="commit", type=Executor.class), @org.apache.ibatis.plugin.Signature(args={boolean.class}, method="rollback", type=Executor.class), @org.apache.ibatis.plugin.Signature(args={boolean.class}, method="close", type=Executor.class)})
public class EnhancedCachingExecutor
  implements Interceptor
{
  private CacheKeysPool queryCacheOnCommit = new CacheKeysPool();
  private Set<String> updateStatementOnCommit = new HashSet();
  EnhancedCachingManager cachingManager = EnhancedCachingManagerImpl.getInstance();

  public Object intercept(Invocation invocation) throws Throwable {
    String name = invocation.getMethod().getName();
    Object result = null;
    if ("query".equals(name))
      result = processQuery(invocation);
    else if ("update".equals(name))
      result = processUpdate(invocation);
    else if ("commit".equals(name))
      result = processCommit(invocation);
    else if ("rollback".equals(name))
      result = processRollback(invocation);
    else if ("close".equals(name)) {
      result = processClose(invocation);
    }
    return result;
  }

  public Object plugin(Object target) {
    return Plugin.wrap(target, this);
  }

  protected Object processQuery(Invocation invocation)
    throws Throwable
  {
    Object result = invocation.proceed();
    if (this.cachingManager.isCacheEnabled()) {
      Object[] args = invocation.getArgs();
      MappedStatement mappedStatement = (MappedStatement)args[0];

      if (mappedStatement.isFlushCacheRequired()) {
        this.queryCacheOnCommit.clear();
      }

      if ((mappedStatement.isUseCache()) && (mappedStatement.getCache() != null)) {
        this.cachingManager.appendStatementCacheMap(mappedStatement.getId(), mappedStatement.getCache());
      }

      Object parameter = args[1];
      RowBounds rowBounds = (RowBounds)args[2];
      Executor executor = (Executor)invocation.getTarget();
      BoundSql boundSql = mappedStatement.getBoundSql(parameter);

      CacheKey cacheKey = executor.createCacheKey(mappedStatement, parameter, rowBounds, boundSql);
      this.queryCacheOnCommit.putElement(mappedStatement.getId(), cacheKey);
    }

    return result;
  }

  protected Object processUpdate(Invocation invocation) throws Throwable
  {
    Object result = invocation.proceed();
    MappedStatement mappedStatement = (MappedStatement)invocation.getArgs()[0];
    this.updateStatementOnCommit.add(mappedStatement.getId());
    return result;
  }

  protected Object processCommit(Invocation invocation) throws Throwable {
    Object result = invocation.proceed();
    refreshCache();
    return result;
  }

  protected Object processRollback(Invocation invocation) throws Throwable {
    Object result = invocation.proceed();
    clearSessionData();
    return result;
  }

  protected Object processClose(Invocation invocation) throws Throwable {
    Object result = invocation.proceed();
    boolean forceRollback = ((Boolean)invocation.getArgs()[0]).booleanValue();
    if (forceRollback)
      clearSessionData();
    else {
      refreshCache();
    }
    return result;
  }

  private synchronized void clearSessionData()
  {
    this.queryCacheOnCommit.clear();
    this.updateStatementOnCommit.clear();
  }

  private synchronized void refreshCache()
  {
    this.cachingManager.refreshCacheKey(this.queryCacheOnCommit);

    this.cachingManager.clearRelatedCaches(this.updateStatementOnCommit);
    clearSessionData();
  }

  public void setProperties(Properties properties)
  {
    if (!this.cachingManager.isInitialized())
      this.cachingManager.initialize(properties);
  }
}