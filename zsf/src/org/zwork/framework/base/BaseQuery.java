package org.zwork.framework.base;

public abstract interface BaseQuery
{
  public abstract String getSortColumns();

  public abstract Integer getPageNumber();

  public abstract Integer getPageSize();
}