package org.zwork.framework.base;

import java.util.List;

import org.zwork.framework.base.support.Pagination;

public abstract interface BaseService<PK extends BaseEntityPK, Entity extends PK>
{
  public abstract int insert(Entity paramEntity);

  public abstract int deleteByPK(PK paramPK);

  public abstract int updateByPK(Entity paramEntity);

  public abstract Entity selectByPK(PK paramPK);

  public abstract List<Entity> selectAll();

  public abstract List<Entity> selectByEntity(Entity paramEntity);

  public abstract <EntityQueryObject extends Entity> Pagination pageQuery(EntityQueryObject paramEntityQueryObject);
}