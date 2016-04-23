package com.org.decp.msg.model.base;

import org.apache.commons.lang.SystemUtils;
import org.zwork.framework.base.BaseEntityPK;
import org.zwork.framework.base.support.BasePageQuery;


public class MsgTransferInfoPK extends BasePageQuery
  implements BaseEntityPK
{
  private static final long serialVersionUID = 1L;
  public static final String TABLE_NAME = "msg_transfer_Info";
  public static final String ALIAS_ID = "id";
  private String id;

  public String getId()
  {
    return this.id;
  }
  public void setId(String id) {
    this.id = id;
  }

  public String getEntityName()
  {
    return "msg_transfer_Info";
  }

  public boolean hasPKColums()
  {
    return true;
  }

  public String pkString()
  {
    StringBuffer str = new StringBuffer();
    str.append(getClass().getName()).append("@").append(hashCode()).append("{").append(SystemUtils.LINE_SEPARATOR);
    str.append("\tid=").append(getId()).append(SystemUtils.LINE_SEPARATOR);
    str.append("}").append(SystemUtils.LINE_SEPARATOR);
    return str.toString();
  }
}