package org.zwork.framework.base.support;

import java.io.Serializable;

import org.swork.common.utils.StringUtils;
import org.zwork.framework.base.BaseQuery;


public class BasePageQuery
  implements BaseQuery, Serializable
{
  private static final long serialVersionUID = 1L;
  private String sortColumns;
  private Integer pageNumber;
  private Integer pageSize;

  public BasePageQuery()
  {
    setSortColumns("");
    setPageNumber(Integer.valueOf(1));
    setPageSize(Integer.valueOf(20));
  }

  public String getSortColumns() {
    return this.sortColumns;
  }

  public void setSortColumns(String sortColumns) {
    checkOrderBySqlInjection(sortColumns);
    this.sortColumns = sortColumns;
  }

  public Integer getPageNumber() {
    return this.pageNumber;
  }

  public void setPageNumber(Integer pageNumber) {
    this.pageNumber = pageNumber;
  }

  public Integer getPageSize() {
    return this.pageSize;
  }

  public void setPageSize(Integer pageSize) {
    this.pageSize = pageSize;
  }

  private void checkOrderBySqlInjection(String orderBy)
  {
    if (StringUtils.isEmpty(orderBy)) {
      return;
    }
    if ((orderBy.indexOf("'") >= 0) || (orderBy.indexOf("\\") >= 0))
      throw new IllegalArgumentException("orderBy[" + orderBy + "]has SQL Injection risk");
  }
}