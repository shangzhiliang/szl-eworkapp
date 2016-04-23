package org.zwork.framework.base.support;

import java.util.List;

import org.zwork.framework.base.BaseDao;
import org.zwork.framework.base.BaseEntityPK;


public class BaseService<EntityPK extends BaseEntityPK, Entity extends EntityPK, ModelDao extends BaseDao<EntityPK, Entity>>
  implements org.zwork.framework.base.BaseService<EntityPK, Entity>
{
  protected ModelDao modelDao;

  public void setModelDao(ModelDao modelDao)
  {
    this.modelDao = modelDao;
  }

  public int insert(Entity entity)
  {
    return this.modelDao.insert(entity);
  }

  public int deleteByPK(EntityPK pk) {
    return this.modelDao.deleteByPK(pk);
  }

  public int updateByPK(Entity entity) {
    return this.modelDao.updateByPK(entity);
  }

  public Entity selectByPK(EntityPK primaryKey) {
    return this.modelDao.selectByPK(primaryKey);
  }

  public List<Entity> selectAll() {
    return this.modelDao.selectAll();
  }

  public List<Entity> selectByEntity(Entity entity) {
    return this.modelDao.selectByEntity(entity);
  }

  public <EntityQueryObject extends Entity> Pagination pageQuery(EntityQueryObject queryObject) {
    return this.modelDao.pageQuery(queryObject);
  }
}