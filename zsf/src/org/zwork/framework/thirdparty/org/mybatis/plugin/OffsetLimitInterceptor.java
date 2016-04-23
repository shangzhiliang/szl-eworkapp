package org.zwork.framework.thirdparty.org.mybatis.plugin;

import java.util.Properties;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zwork.framework.jdbc.dialect.Dialect;


@Intercepts({@org.apache.ibatis.plugin.Signature(type=org.apache.ibatis.executor.Executor.class, method="query", args={MappedStatement.class, Object.class, RowBounds.class, org.apache.ibatis.session.ResultHandler.class})})
public class OffsetLimitInterceptor
  implements Interceptor
{
  protected static int INDEX_MAPPED_STATEMENT = 0;
  protected static int INDEX_PARAMETER = 1;
  protected static int INDEX_ROWBOUNDS = 2;
  protected static int INDEX_RESULT_HANDLER = 3;

  private static final Logger LOGGER = LoggerFactory.getLogger(OffsetLimitInterceptor.class);
  private Dialect dialect;

  public Object intercept(Invocation invocation)
    throws Throwable
  {
    processIntercept(invocation.getArgs());
    return invocation.proceed();
  }

  public Object plugin(Object target) {
    return Plugin.wrap(target, this);
  }

  public void setProperties(Properties properties) {
    String dialectClass = properties.getProperty("dialectClass");
    try {
      this.dialect = ((Dialect)Class.forName(dialectClass).newInstance());
    } catch (Exception e) {
      throw new RuntimeException("cannot create dialect instance by dialectClass:" + dialectClass, e);
    }
    LOGGER.debug(OffsetLimitInterceptor.class.getSimpleName() + ".dialect=" + dialectClass);
  }

  private void processIntercept(Object[] queryArgs) {
    MappedStatement ms = (MappedStatement)queryArgs[INDEX_MAPPED_STATEMENT];
    Object parameter = queryArgs[INDEX_PARAMETER];
    RowBounds rowBounds = (RowBounds)queryArgs[INDEX_ROWBOUNDS];
    int offset = rowBounds.getOffset();
    int limit = rowBounds.getLimit();

    if ((this.dialect.supportsLimit()) && ((offset != 0) || (limit != 2147483647))) {
      BoundSql boundSql = ms.getBoundSql(parameter);
      String sql = boundSql.getSql().trim();
      if (this.dialect.supportsLimitOffset()) {
        sql = this.dialect.getLimitString(sql, offset, limit);
        offset = 0;
      } else {
        sql = this.dialect.getLimitString(sql, 0, limit);
      }
      limit = 2147483647;

      queryArgs[INDEX_ROWBOUNDS] = new RowBounds(offset, limit);
      BoundSql newBoundSql = new BoundSql(ms.getConfiguration(), sql, boundSql.getParameterMappings(), 
        boundSql.getParameterObject());
      MappedStatement newMs = copyFromMappedStatement(ms, new BoundSqlSqlSource(newBoundSql));
      queryArgs[INDEX_MAPPED_STATEMENT] = newMs;
    }
  }

  private MappedStatement copyFromMappedStatement(MappedStatement ms, SqlSource newSqlSource)
  {
    MappedStatement.Builder builder = new MappedStatement.Builder(ms.getConfiguration(), ms.getId(), newSqlSource, 
      ms.getSqlCommandType());

    builder.resource(ms.getResource());
    builder.fetchSize(ms.getFetchSize());
    builder.statementType(ms.getStatementType());
    builder.keyGenerator(ms.getKeyGenerator());
    String[] keyProperties = ms.getKeyProperties();
    String keyProperty = "";
    if (keyProperties != null) {
      for (int i = 0; i < keyProperties.length; i++) {
        keyProperty = keyProperty + keyProperties[i] + ",";
      }
    }
    builder.keyProperty("".equals(keyProperty) ? null : keyProperty.substring(0, keyProperty.length() - 1));

    builder.timeout(ms.getTimeout());

    builder.parameterMap(ms.getParameterMap());

    builder.resultMaps(ms.getResultMaps());
    builder.resultSetType(ms.getResultSetType());

    builder.cache(ms.getCache());
    builder.flushCacheRequired(ms.isFlushCacheRequired());
    builder.useCache(ms.isUseCache());

    return builder.build();
  }

  public static class BoundSqlSqlSource implements SqlSource {
    BoundSql boundSql;

    public BoundSqlSqlSource(BoundSql boundSql) {
      this.boundSql = boundSql;
    }

    public BoundSql getBoundSql(Object parameterObject) {
      return this.boundSql;
    }
  }
}