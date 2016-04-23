package org.zwork.framework.base;

public abstract interface BaseEntityPK
{
  public abstract String getEntityName();

  public abstract boolean hasPKColums();

  public abstract String pkString();
}