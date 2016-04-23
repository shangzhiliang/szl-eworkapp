package com.org.decp.msg.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zwork.framework.base.support.AbstractMybatisDao;

import com.org.decp.msg.model.base.MsgExchangePackage;
import com.org.decp.msg.model.base.MsgExchangePackagePK;


public class MsgExchangePackageDao extends AbstractMybatisDao<MsgExchangePackagePK, MsgExchangePackage>
{
  private static final Logger LOGGER = LoggerFactory.getLogger(MsgExchangePackageDao.class);
  protected static final String NAMESPACE = "MsgExchangePackage";

  public String getIbatisMapperNamesapce()
  {
    return "MsgExchangePackage";
  }
}