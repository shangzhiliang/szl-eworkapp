package com.org.decp.msg.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.swork.common.key.KeyGeneratorContainer;
import org.zwork.framework.base.support.BaseStruts2Action;

import com.org.decp.common.util.StringUtils;
import com.org.decp.msg.model.MsgTransferInfoQuery;
import com.org.decp.msg.model.base.MsgTransferInfo;
import com.org.decp.msg.model.base.MsgTransferInfoPK;
import com.org.decp.msg.service.MsgTransferInfoService;


public class MsgTransferInfoAction extends BaseStruts2Action
{
  private static final long serialVersionUID = 1L;
  private static final Logger LOGGER = LoggerFactory.getLogger(MsgTransferInfoAction.class);
  protected MsgTransferInfo entity;
  protected MsgTransferInfoQuery queryEntity;
  protected MsgTransferInfoService entityService;
  private KeyGeneratorContainer keyGeneratorContainer;

  public void init()
  {
    this.entity = new MsgTransferInfo();
    this.queryEntity = new MsgTransferInfoQuery();
  }

  public String create()
    throws Exception
  {
    if ("toCreate".equals(this.operate))
      return "create";
    if ("save".equals(this.operate))
    {
      String id = this.keyGeneratorContainer.getNextKey("msgTransferInfoId");
      this.entity.setId(id);
      LOGGER.debug("新增数据，新增前对象信息：{}", this.entity);
      this.entityService.insert(this.entity);
      setInfoMessage("操作成功!");
      LOGGER.debug("新增数据成功，转向修改页面");
      return "edit";
    }
    throw new Exception("无效operate参数值[" + this.operate + "]");
  }

  public String edit()
    throws Exception
  {
    if ("toEdit".equals(this.operate))
    {
      LOGGER.debug("准备更新user对象,主键信息：{}", this.entity.pkString());
      this.entity = ((MsgTransferInfo)this.entityService.selectByPK(this.entity));
      LOGGER.debug("更新前数据库中user信息[{}]" + this.entity);
      return "edit";
    }if ("save".equals(this.operate))
    {
      LOGGER.debug("更新数据：{}", this.entity);
      this.entityService.updateByPK(this.entity);
      setInfoMessage("操作成功!");
      LOGGER.debug("更新数据成功!");
      return "operate-prompt";
    }
    throw new Exception("无效operate参数值[" + this.operate + "]");
  }

  public String delete()
    throws Exception
  {
    String selectedIds = this.request.getParameter("selectedIds");
    if (StringUtils.isNotEmpty(selectedIds)) {
      String[] idArray = selectedIds.split("\\|");
      for (int i = 0; i < idArray.length; i++) {
        MsgTransferInfoPK pk = new MsgTransferInfoPK();
        pk.setId(idArray[i]);
        LOGGER.debug("删除数据:{}", pk.pkString());
        this.entityService.deleteByPK(pk);
      }
    }

    LOGGER.debug("删除数据:{}", this.entity.pkString());
    this.entityService.deleteByPK(this.entity);
    return "operate-prompt";
  }

  public String view()
    throws Exception
  {
    LOGGER.debug("根据主键查询数据，主键信息:{}", this.entity.pkString());
    this.entity = ((MsgTransferInfo)this.entityService.selectByPK(this.entity));
    if (this.entity == null) {
      throw new Exception("对象不存在,查询条件:" + this.entity.pkString());
    }
    LOGGER.debug("查询结果:{}", this.entity);
    return "view";
  }

  public String list()
    throws Exception
  {
    if ("query".equals(this.operate)) {
      LOGGER.debug("开始执行查询操作，查询条件:{}", this.queryEntity);
      this.page = this.entityService.pageQuery(this.queryEntity);
      if (this.page.getCount() <= 0) {
        setWarnMessage("未找到匹配的数据记录,请更换查询条件后再试!");
      }
      LOGGER.debug("查询结果:[count={},page data size={}]", Integer.valueOf(this.page.getCount()), Integer.valueOf(this.page.getData().size()));
      return "list";
    }
    return "list";
  }

  public MsgTransferInfo getEntity()
  {
    return this.entity;
  }

  public void setEntity(MsgTransferInfo entity) {
    this.entity = entity;
  }

  public MsgTransferInfoQuery getQueryEntity() {
    return this.queryEntity;
  }

  public void setQueryEntity(MsgTransferInfoQuery queryEntity) {
    this.queryEntity = queryEntity;
  }

  public void setEntityService(MsgTransferInfoService entityService) {
    this.entityService = entityService;
  }

  public void setKeyGeneratorContainer(KeyGeneratorContainer keyGeneratorContainer) {
    this.keyGeneratorContainer = keyGeneratorContainer;
  }
}
