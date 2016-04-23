package org.zwork.framework.base.support;

import java.io.Serializable;
import java.util.List;


public class Pagination
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private List<?> data;
  private int count;
  private int pageSize;
  private int pageCount;
  private int pageNumber;

  private void init()
  {
    setPageNumber(1);
  }

  public Pagination() {
    init();
  }

  public List<?> getData()
  {
    return this.data;
  }

  public void setData(List<?> data) {
    this.data = data;
  }

  public int getCount()
  {
    return this.count;
  }

  public void setCount(int count)
  {
    if (this.pageSize != 0) {
      this.pageCount = (count / this.pageSize);
      if (count % this.pageSize != 0) {
        this.pageCount += 1;
      }
    }
    this.count = count;
  }

  public int getPageSize()
  {
    return this.pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }

  public int getPageCount()
  {
    return this.pageCount;
  }

  public int getPageNumber()
  {
    return this.pageNumber;
  }

  public void setPageNumber(int pageNumber) {
    this.pageNumber = pageNumber;
  }
}