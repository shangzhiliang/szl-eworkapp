package org.zwork.framework.base;

import java.util.List;

import org.zwork.framework.base.support.Pagination;

public abstract interface BaseDao<EntityPK extends BaseEntityPK, Entity extends EntityPK>
{
  public abstract int insert(Entity paramEntity);

  public abstract int deleteByPK(EntityPK paramEntityPK);

  public abstract int updateByPK(Entity paramEntity);

  public abstract Entity selectByPK(EntityPK paramEntityPK);

  public abstract List<Entity> selectAll();

  public abstract List<Entity> selectByEntity(Entity paramEntity);

  public abstract <EntityQueryObject extends Entity> Pagination pageQuery(EntityQueryObject paramEntityQueryObject);
}