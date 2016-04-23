package com.org.decp.msg;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.swork.common.key.KeyGeneratorContainer;
import org.zwork.framework.thirdparty.org.springframework.SpringBeanContext;

import com.org.decp.common.util.DateTimeUtil;
import com.org.decp.common.util.StringUtils;
import com.org.decp.msg.config.CommonMessageConfig;
import com.org.decp.msg.config.TradeMessageConfig;
import com.org.decp.msg.model.MsgExchangePackageQuery;
import com.org.decp.msg.model.base.MsgExchangePackage;
import com.org.decp.msg.model.base.MsgTransferInfo;
import com.org.decp.msg.model.base.ResultInfo;
import com.org.decp.msg.service.MsgExchangePackageService;
import com.org.decp.msg.service.MsgTransferInfoService;
import com.org.decp.msg.trade.server.AbstractBusiTrade;
import com.org.decp.msg.util.MessageUtil;
import com.org.decp.msg.util.PMManager;
import com.org.decp.msg.util.XmlDocumentUtil;
import com.org.decp.service.DecpServerHandle;
import com.org.decp.service.mq.producer.DecpRabbitMqProducer;
import com.org.decp.service.webservice.client.DecpWebServiceClient;


public class DecpMsgService
{
  private static final Logger LOGGER = LoggerFactory.getLogger(DecpMsgService.class);
  public CommonMessageConfig msgCfg;
  private KeyGeneratorContainer keyGeneratorContainer;
  private MsgExchangePackageService msgExchangePackageService;
  private MsgTransferInfoService msgTransferInfoService;

  public ResultInfo createAndsendMessage(String globalBusinessID, String businessType, String messageID, String content)
  {
    MsgExchangePackage messageData = null;

    ResultInfo resultInfo = new ResultInfo();
    TradeMessageConfig tradeMessageConfig = null;

    String isRetry = "0";
    try
    {
      tradeMessageConfig = (TradeMessageConfig)this.msgCfg.getTradeMsgCfgMap().get(businessType);
      tradeMessageConfig = (TradeMessageConfig)MessageUtil.copyProperties(this.msgCfg, tradeMessageConfig, null);
      if (tradeMessageConfig != null)
        messageData = MessageUtil.encapsuExchangePackage(globalBusinessID, businessType, messageID, content, tradeMessageConfig);
    }
    catch (Exception e) {
      LOGGER.error("创建消息对象失败,原因：{}", e);
      resultInfo.setReturnCode("M01");
      resultInfo.setReturnMessage("创建消息对象失败：" + e.toString());
    }

    if (messageData != null)
    {
      resultInfo = MessageUtil.createMessage(messageData, tradeMessageConfig, "1");
      if (resultInfo.getReturnCode().equals("M00")) {
        LOGGER.info("客户端发送消息报文创建成功，报文ID：{},报文messID:{},报文体：{}", new Object[] { messageData.getId(), messageData.getMessageID(), messageData.getContent() });

        if (isRetry.equals("0")) {
          this.msgExchangePackageService.insert(messageData);
          LOGGER.info("发送报文[msgExchangePackage数据插入成功,报文ID:{}，报文messId:{}", messageData.getId(), messageData.getMessageID());
          if ((messageData.getMsgTransferInfos() != null) && (!messageData.getMsgTransferInfos().isEmpty()))
          {
            MsgTransferInfo transferInfo = (MsgTransferInfo)messageData.getMsgTransferInfos().get(0);

            this.msgTransferInfoService.insert(transferInfo);
            LOGGER.info("发送报文路由信息[msgTransferInfo数据插入成功]，路由ID：{}", transferInfo.getId());
          }

        }

        if (tradeMessageConfig != null)
        {
          if ("WS".equals(tradeMessageConfig.getProtocolType())) {
            DecpWebServiceClient decpWebServiceClient = DecpServerHandle.getInstance().getWebServiceClient();
            String reciviMessage = "";
            try {
              reciviMessage = decpWebServiceClient.sendMessage(tradeMessageConfig.getServerUrl(), resultInfo.getReturnMessage());
            } catch (Exception e1) {
              resultInfo.setReturnCode("M02");
              resultInfo.setReturnMessage("decp报文发送(webservice)失败，异常原因：" + e1.toString());
            }
            MsgExchangePackage returnMsgExchangePackage = null;
            try {
              if (StringUtils.isNotEmpty(reciviMessage))
                returnMsgExchangePackage = XmlDocumentUtil.parseXml(reciviMessage);
            } catch (Exception e) {
              LOGGER.error("[解析返回报文失败，异常原因:{}", e);
              resultInfo.setReturnCode("M06");
              resultInfo.setReturnMessage("解析返回报文异常：" + e.toString());
            }
            if (returnMsgExchangePackage != null) {
              if (!"000000".equals(returnMsgExchangePackage.getBusinessType()))
              {
                saveMsgExchangeInfo(returnMsgExchangePackage);
                if ((returnMsgExchangePackage.getReturnCode() != null) && ("00".equals(returnMsgExchangePackage.getReturnCode()))) {
                  resultInfo.setReturnCode("M00");
                  resultInfo.setReturnMessage(returnMsgExchangePackage.getContent());
                } else {
                  resultInfo.setReturnCode("M90");
                  resultInfo.setReturnMessage(returnMsgExchangePackage.getReturnMessage());
                }
              }
              else {
                MsgExchangePackageQuery msgExchangePackageQuery = new MsgExchangePackageQuery();
                msgExchangePackageQuery.setMessageID(returnMsgExchangePackage.getMessageID());
                List msgList = this.msgExchangePackageService.selectByEntity(msgExchangePackageQuery);
                if ((msgList != null) && (!msgList.isEmpty())) {
                  MsgExchangePackage messageDataNew = (MsgExchangePackage)msgList.get(0);
                  messageDataNew.setReturnCode(returnMsgExchangePackage.getReturnCode());
                  messageDataNew.setReturnMessage(returnMsgExchangePackage.getReturnMessage());
                  messageDataNew.setLastUpdateTime(PMManager.getCurrentWorkDate());
                  this.msgExchangePackageService.updateByPK(messageDataNew);
                  if ((returnMsgExchangePackage.getReturnCode() != null) && ("00".equals(returnMsgExchangePackage.getReturnCode()))) {
                    resultInfo.setReturnCode("M00");
                    resultInfo.setReturnMessage(returnMsgExchangePackage.getContent());
                  } else {
                    resultInfo.setReturnCode("M90");
                    resultInfo.setReturnMessage(returnMsgExchangePackage.getReturnMessage());
                  }
                }
              }
            }

          }
          else if ("MQ".equals(tradeMessageConfig.getProtocolType())) {
            DecpRabbitMqProducer decpRabbitMqProducer = (DecpRabbitMqProducer)SpringBeanContext.getBean("decpRabbitMqProducer");
            decpRabbitMqProducer.sendMessage(tradeMessageConfig, resultInfo.getReturnMessage());
          }
        }
      } else { LOGGER.info("创建消息失败：returnCode:{};失败原因：{}", resultInfo.getReturnCode(), resultInfo.getReturnMessage()); }
    }
    else {
      resultInfo.setReturnCode("M01");
      resultInfo.setReturnMessage("封装报文异常，请查看报文信息xml配置文件配置是否正常");
    }
    return resultInfo;
  }

  public String parseAndForwardMessage(String message)
  {
    MsgExchangePackage msgExchangePackage = null;
    TradeMessageConfig tradeMessageConfig = new TradeMessageConfig();

    String returnContent = "";
    try
    {
      if (StringUtils.isNotEmpty(message)) {
        msgExchangePackage = XmlDocumentUtil.parseXml(message);
        if (msgExchangePackage != null)
        {
          saveMsgExchangeInfo(msgExchangePackage);
          String bussinessType = msgExchangePackage.getBusinessType();
          if (StringUtils.isNotEmpty(bussinessType))
          {
            if ((this.msgCfg.getBusiTradeMap() != null) && (this.msgCfg.getBusiTradeMap().get(bussinessType) != null)) {
              AbstractBusiTrade abstractBusiTrade = (AbstractBusiTrade)this.msgCfg.getBusiTradeMap().get(bussinessType);

              String bussContent = abstractBusiTrade.execute(msgExchangePackage.getContent());
              if (StringUtils.isNotEmpty(bussContent))
              {
                if (msgExchangePackage.getSyncFlag().equals("1"))
                {
                  MsgExchangePackage msExchangePackageNew = new MsgExchangePackage();

                  MessageUtil.copyMsgExchangePackage(msgExchangePackage, msExchangePackageNew);
                  msExchangePackageNew.setContent(bussContent);
                  msExchangePackageNew.setReturnCode("00");
                  ResultInfo resultInfo = MessageUtil.createMessage(msExchangePackageNew, tradeMessageConfig, "2");
                  if ((resultInfo != null) && 
                    (resultInfo.getReturnCode().equals("M00"))) {
                    LOGGER.info("回执报文创建成功，报文ID：{},报文messID:{},报文体：{}", new Object[] { msExchangePackageNew.getId(), msExchangePackageNew.getMessageID(), msExchangePackageNew.getContent() });

                    this.msgExchangePackageService.insert(msExchangePackageNew);
                    LOGGER.info("回执报文插入成功（msgExchangePackage），报文ID：{}", msExchangePackageNew.getId());
                    if ((msExchangePackageNew.getMsgTransferInfos() != null) && (!msExchangePackageNew.getMsgTransferInfos().isEmpty()))
                    {
                      MsgTransferInfo transferInfo = (MsgTransferInfo)msExchangePackageNew.getMsgTransferInfos().get(0);

                      this.msgTransferInfoService.insert(transferInfo);
                      LOGGER.info("报文路由信息插入成功（msgTransferInfo），路由ID：{}", transferInfo.getId());
                    }
                    returnContent = resultInfo.getReturnMessage();
                  }

                }
                else if (msgExchangePackage.getSyncFlag().equals("0")) {
                  msgExchangePackage.setReturnCode("00");
                  msgExchangePackage.setReturnMessage("回执成功！");

                  msgExchangePackage.setBusinessType("000000");
                  msgExchangePackage.setSourceMessageID(msgExchangePackage.getMessageID());

                  ResultInfo returnInfo2 = MessageUtil.createMessage(msgExchangePackage, tradeMessageConfig, "2");
                  if ((returnInfo2 != null) && (returnInfo2.getReturnCode().equals("M00"))) {
                    returnContent = returnInfo2.getReturnMessage();

                    this.msgExchangePackageService.updateByPK(msgExchangePackage);
                    LOGGER.info("更新报文成功报文ID：{}，messId:{}", msgExchangePackage.getId(), msgExchangePackage.getMessageID());
                  }
                }
              }
            }
          }
        }
      }

    }
    catch (Exception e)
    {
      LOGGER.error("解析发送报文失败，异常原因:{}", e);
    }
    return returnContent;
  }

  private void saveMsgExchangeInfo(MsgExchangePackage msgExchangePackage)
  {
    String id = this.keyGeneratorContainer.getNextKey("msgExchangePackageId");
    LOGGER.info("[saveMsgExchangeInfo-msgExchangePackageId]生成报文ID：{}", id);
    msgExchangePackage.setId(id);
    msgExchangePackage.setCreateTime(DateTimeUtil.getCurrentWorkDate());
    msgExchangePackage.setLastUpdateTime(DateTimeUtil.getCurrentWorkDate());
    this.msgExchangePackageService.insert(msgExchangePackage);
    LOGGER.info("[saveMsgExchangeInfo],插入报文成功，报文ID：{}", id);

    if ((msgExchangePackage.getMsgTransferInfos() != null) && (!msgExchangePackage.getMsgTransferInfos().isEmpty()))
      for (MsgTransferInfo msgTransferInfo : msgExchangePackage.getMsgTransferInfos()) {
        String transInfoId = this.keyGeneratorContainer.getNextKey("msgTransferInfoId");
        msgTransferInfo.setId(transInfoId);
        msgTransferInfo.setMsgId(id);
        msgTransferInfo.setCreateTime(DateTimeUtil.getCurrentWorkDate());
        msgTransferInfo.setLastUpdateTime(DateTimeUtil.getCurrentWorkDate());
        this.msgTransferInfoService.insert(msgTransferInfo);
        LOGGER.debug("[saveMsgExchangeInfo],插入报文路由信息成功，路由ID：{}", transInfoId);
      }
  }

  public void setMsgCfg(CommonMessageConfig msgCfg) {
    this.msgCfg = msgCfg;
  }

  public void setKeyGeneratorContainer(KeyGeneratorContainer keyGeneratorContainer) {
    this.keyGeneratorContainer = keyGeneratorContainer;
  }

  public void setMsgExchangePackageService(MsgExchangePackageService msgExchangePackageService) {
    this.msgExchangePackageService = msgExchangePackageService;
  }

  public void setMsgTransferInfoService(MsgTransferInfoService msgTransferInfoService) {
    this.msgTransferInfoService = msgTransferInfoService;
  }
}