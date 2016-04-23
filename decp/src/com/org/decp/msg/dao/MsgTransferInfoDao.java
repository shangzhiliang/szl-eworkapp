package com.org.decp.msg.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zwork.framework.base.support.AbstractMybatisDao;

import com.org.decp.msg.model.base.MsgTransferInfo;
import com.org.decp.msg.model.base.MsgTransferInfoPK;

public class MsgTransferInfoDao extends AbstractMybatisDao<MsgTransferInfoPK, MsgTransferInfo>
{
  private static final Logger LOGGER = LoggerFactory.getLogger(MsgTransferInfoDao.class);
  protected static final String NAMESPACE = "MsgTransferInfo";

  public String getIbatisMapperNamesapce()
  {
    return "MsgTransferInfo";
  }
}
