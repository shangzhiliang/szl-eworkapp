package org.zwork.framework.base.support;

import java.util.List;

import ognl.Ognl;
import ognl.OgnlException;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zwork.framework.base.BaseDao;
import org.zwork.framework.base.BaseEntityPK;


public abstract class AbstractMybatisDao<EntityPK extends BaseEntityPK, Entity extends EntityPK> extends SqlSessionDaoSupport
  implements BaseDao<EntityPK, Entity>
{
  private static final Logger LOGGER = LoggerFactory.getLogger(AbstractMybatisDao.class);
  protected static final String METHOD_PREFIX_INSERT = "insert";
  protected static final String METHOD_PREFIX_DELETE = "delete";
  protected static final String METHOD_PREFIX_UPDATE = "update";
  protected static final String METHOD_PREFIX_SELECT = "select";

  protected SqlSession getSqlSessionTemplate()
  {
    return getSqlSession();
  }

  public int insert(Entity entity)
  {
    int affectCount = getSqlSessionTemplate().insert(getInsertStatement(), entity);
    return affectCount;
  }

  public int deleteByPK(EntityPK pk) {
    if (!pk.hasPKColums()) {
      LOGGER.warn("非法调用，当前表[{}]不存在主鍵!", pk.getEntityName());
      return -1;
    }
    int affectCount = getSqlSessionTemplate().delete(getDeleteByPKStatement(), pk);
    return affectCount;
  }

  public int updateByPK(Entity entity) {
    if (!entity.hasPKColums()) {
      LOGGER.warn("非法调用，当前表[{}]不存在主鍵!", entity.getEntityName());
      return -1;
    }
    int affectCount = getSqlSessionTemplate().update(getUpdateByPKStatement(), entity);
    return affectCount;
  }

  public Entity selectByPK(EntityPK pk)
  {
    if (!pk.hasPKColums()) {
      LOGGER.warn("非法调用，当前表[{}]不存在主鍵!", pk.getEntityName());
      return null;
    }
    
    @SuppressWarnings("unchecked")
	Entity entity = (Entity)getSqlSessionTemplate().selectOne(getSelectByPKStatement(), pk);
    return  entity;
  }

  public List<Entity> selectAll() {
    return selectByEntity(null);
  }

  public List<Entity> selectByEntity(Entity entity) {
    if (entity == null) {
      this.logger.warn("查询条件为null,执行全表查询!");
    }
    List list = getSqlSessionTemplate().selectList(getSelectByEntityStatement(), entity);
    return list;
  }

  public <EntityQueryObject extends Entity> Pagination pageQuery(EntityQueryObject queryObject) {
    return pageQuery(getPageQueryStatement(), queryObject);
  }

  public Pagination pageQuery(String statement, Object queryObject) {
    Pagination page = null;
    try
    {
      Integer count = (Integer)getSqlSessionTemplate().selectOne(statement + "Count", queryObject);

      Integer pageNumber = (Integer)Ognl.getValue("pageNumber", queryObject);
      Integer pageSize = (Integer)Ognl.getValue("pageSize", queryObject);
      RowBounds rowBounds = new RowBounds(pageSize.intValue() * (pageNumber.intValue() - 1), pageSize.intValue());

      List data = getSqlSessionTemplate().selectList(statement, queryObject, rowBounds);
      page = new Pagination();
      page.setData(data);
      page.setPageNumber(pageNumber.intValue());
      page.setPageSize(pageSize.intValue());
      page.setCount(count.intValue());
    } catch (OgnlException e) {
      LOGGER.error("分页查询出错!", e);
    }
    return page;
  }

  public abstract String getIbatisMapperNamesapce();

  private String getInsertStatement()
  {
    return getIbatisMapperNamesapce() + "." + "insert";
  }

  private String getDeleteByPKStatement() {
    return getIbatisMapperNamesapce() + "." + "delete" + "ByPK";
  }

  private String getUpdateByPKStatement() {
    return getIbatisMapperNamesapce() + "." + "update" + "ByPK";
  }

  private String getSelectByPKStatement() {
    return getIbatisMapperNamesapce() + "." + "select" + "ByPK";
  }

  private String getSelectByEntityStatement() {
    return getIbatisMapperNamesapce() + "." + "select" + "ByEntity";
  }

  private String getPageQueryStatement() {
    return getIbatisMapperNamesapce() + "." + "select" + "ByPage";
  }
}