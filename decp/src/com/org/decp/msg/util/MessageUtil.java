package com.org.decp.msg.util;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.swork.common.key.KeyGeneratorContainer;
import org.zwork.framework.thirdparty.org.springframework.SpringBeanContext;

import com.org.decp.common.util.DateTimeUtil;
import com.org.decp.common.util.StringUtils;
import com.org.decp.msg.config.TradeMessageConfig;
import com.org.decp.msg.model.base.ContentControl;
import com.org.decp.msg.model.base.DataExchangePackage;
import com.org.decp.msg.model.base.EnvelopInfo;
import com.org.decp.msg.model.base.MsgExchangePackage;
import com.org.decp.msg.model.base.MsgTransferInfo;
import com.org.decp.msg.model.base.PackageInfo;
import com.org.decp.msg.model.base.ResultInfo;
import com.org.decp.msg.model.base.ReturnState;
import com.org.decp.msg.model.base.TransferInfo;


public class MessageUtil
{
  private static final Logger LOGGER = LoggerFactory.getLogger(MessageUtil.class);

  public static ResultInfo createMessage(MsgExchangePackage messageData, TradeMessageConfig tradeMessageConfig, String requestFlag)
  {
    ResultInfo resultInfo = new ResultInfo();

    if (messageData != null)
    {
      DataExchangePackage dataExchangePackage = new DataExchangePackage();

      EnvelopInfo envelopInfo = new EnvelopInfo();

      String checkResult = validateString(messageData.getSourceID(), "M", 4);
      if (checkResult.equals("00")) {
        envelopInfo.setSourceID(messageData.getSourceID());
      } else {
        resultInfo.setReturnCode(checkResult);
        resultInfo.setReturnMessage("信封内容：sourceID不合法！");
        return resultInfo;
      }

      checkResult = validateString(messageData.getDestinationID(), "M", 4);
      if (checkResult.equals("00")) {
        envelopInfo.setDestinationID(messageData.getDestinationID());
      } else {
        resultInfo.setReturnCode(checkResult);
        resultInfo.setReturnMessage("信封内容：destinationID不合法！");
        return resultInfo;
      }

      checkResult = validateString(messageData.getSourceAppID(), "M", 6);
      if (checkResult.equals("00")) {
        envelopInfo.setSourceAppID(messageData.getSourceAppID());
      } else {
        resultInfo.setReturnCode(checkResult);
        resultInfo.setReturnMessage("信封内容：sourceAppID不合法！");
        return resultInfo;
      }

      checkResult = validateString(messageData.getDestinationAppID(), "M", 6);
      if (checkResult.equals("00")) {
        envelopInfo.setDestinationAppID(messageData.getDestinationAppID());
      } else {
        resultInfo.setReturnCode(checkResult);
        resultInfo.setReturnMessage("信封内容：destinationAppID不合法！");
        return resultInfo;
      }
      checkResult = validateString(messageData.getGlobalBusinessID(), "M", 26);
      if (checkResult.equals("00")) {
        envelopInfo.setGlobalBusinessID(messageData.getGlobalBusinessID());
      } else {
        resultInfo.setReturnCode(checkResult);
        resultInfo.setReturnMessage("信封内容：globalBusinessID不合法！");
        return resultInfo;
      }

      checkResult = validateString(messageData.getMessageID(), "M", 26);
      if (checkResult.equals("00")) {
        envelopInfo.setMessageID(messageData.getMessageID());
      } else {
        resultInfo.setReturnCode(checkResult);
        resultInfo.setReturnMessage("信封内容：messageID不合法！");
        return resultInfo;
      }

      checkResult = validateString(messageData.getBusinessType(), "M", 6);
      if (checkResult.equals("00")) {
        envelopInfo.setBusinessType(messageData.getBusinessType());
      } else {
        resultInfo.setReturnCode(checkResult);
        resultInfo.setReturnMessage("信封内容：businessType不合法！");
        return resultInfo;
      }

      checkResult = validateString(messageData.getSyncFlag(), "M", 1);
      if (checkResult.equals("00")) {
        envelopInfo.setSyncFlag(messageData.getSyncFlag());
      } else {
        resultInfo.setReturnCode(checkResult);
        resultInfo.setReturnMessage("信封内容：syncFlag不合法！");
        return resultInfo;
      }
      if (envelopInfo != null) {
        dataExchangePackage.setEnvelopInfo(envelopInfo);
      }

      ContentControl contentControl = new ContentControl();

      checkResult = validateString(messageData.getIsZip(), "M", 1);
      if (checkResult.equals("00")) {
        contentControl.setIsZip(messageData.getIsZip());
      } else {
        resultInfo.setReturnCode(checkResult);
        resultInfo.setReturnMessage("内容控制信息：isZip不合法！");
        return resultInfo;
      }

      checkResult = validateString(messageData.getZipType(), "M", 1);
      if (checkResult.equals("00")) {
        contentControl.setZipType(messageData.getZipType());
      } else {
        resultInfo.setReturnCode(checkResult);
        resultInfo.setReturnMessage("内容控制信息：zipType不合法！");
        return resultInfo;
      }

      checkResult = validateString(messageData.getIsEncrypt(), "M", 1);
      if (checkResult.equals("00")) {
        contentControl.setIsEncrypt(messageData.getIsEncrypt());
      } else {
        resultInfo.setReturnCode(checkResult);
        resultInfo.setReturnMessage("内容控制信息：isEncrypt不合法！");
        return resultInfo;
      }
      if (messageData.getIsEncrypt().equals("1")) {
        if (StringUtils.isNotEmpty(messageData.getEncrytType())) {
          checkResult = validateString(messageData.getEncrytType(), "M", 1);
          if (checkResult.equals("00")) {
            contentControl.setEncrytType(messageData.getEncrytType());
          } else {
            resultInfo.setReturnCode(checkResult);
            resultInfo.setReturnMessage("内容控制信息：encrytTyp不合法！");
            return resultInfo;
          }
        } else {
          resultInfo.setReturnCode("M01");
          resultInfo.setReturnMessage("内容控制信息：encrytTyp不合法！");
          return resultInfo;
        }
      }
      if (StringUtils.isNotEmpty(messageData.getEncrytType())) {
        checkResult = validateString(messageData.getEncrytType(), "O", 1);
        if (checkResult.equals("00")) {
          contentControl.setEncrytType(messageData.getEncrytType());
        } else {
          resultInfo.setReturnCode(checkResult);
          resultInfo.setReturnMessage("内容控制信息：encrytTyp不合法！");
          return resultInfo;
        }
      }
      dataExchangePackage.setContentControl(contentControl);

      TransferInfo transferInfo = new TransferInfo();

      if ((messageData.getMsgTransferInfos() != null) && (messageData.getMsgTransferInfos().size() > 0)) {
        MsgTransferInfo msgTransferInfo = (MsgTransferInfo)messageData.getMsgTransferInfos().get(0);

        checkResult = validateString(msgTransferInfo.getSenderID(), "O", 4);
        if (checkResult.equals("00")) {
          transferInfo.setSenderID(msgTransferInfo.getSenderID());
        } else {
          resultInfo.setReturnCode(checkResult);
          resultInfo.setReturnMessage("路由信息：senderID不合法！");
          return resultInfo;
        }
        checkResult = validateString(msgTransferInfo.getReceiverID(), "O", 4);
        if (checkResult.equals("00")) {
          transferInfo.setReceiverID(msgTransferInfo.getReceiverID());
        } else {
          resultInfo.setReturnCode(checkResult);
          resultInfo.setReturnMessage("路由信息：receiverID不合法！");
          return resultInfo;
        }
        checkResult = validateString(msgTransferInfo.getSenderAppID(), "O", 6);
        if (checkResult.equals("00")) {
          transferInfo.setSenderAppID(msgTransferInfo.getSenderAppID());
        } else {
          resultInfo.setReturnCode(checkResult);
          resultInfo.setReturnMessage("路由信息：senderAppID不合法！");
          return resultInfo;
        }
        checkResult = validateString(msgTransferInfo.getReceiverAppID(), "O", 6);
        if (checkResult.equals("00")) {
          transferInfo.setReceiverAppID(msgTransferInfo.getReceiverAppID());
        } else {
          resultInfo.setReturnCode(checkResult);
          resultInfo.setReturnMessage("路由信息：receiverAppID不合法！");
          return resultInfo;
        }
        checkResult = validateString(msgTransferInfo.getSourceMessageID(), "O", 26);
        if (checkResult.equals("00")) {
          transferInfo.setSourceMessageID(msgTransferInfo.getSourceMessageID());
        } else {
          resultInfo.setReturnCode(checkResult);
          resultInfo.setReturnMessage("路由信息：sourceMessageID不合法！");
          return resultInfo;
        }
        checkResult = validateString(msgTransferInfo.getIsRetry(), "O", 1);
        if (checkResult.equals("00")) {
          transferInfo.setIsRetry(msgTransferInfo.getIsRetry());
        } else {
          resultInfo.setReturnCode(checkResult);
          resultInfo.setReturnMessage("路由信息：isRetry不合法！");
          return resultInfo;
        }
        transferInfo.setSendTime(msgTransferInfo.getSendTime());
        dataExchangePackage.setTransferInfo(transferInfo);
      } else {
        resultInfo.setReturnCode("M01");
        resultInfo.setReturnMessage("报文信息为空！");
        return resultInfo;
      }

      PackageInfo packageInfo = new PackageInfo();
      packageInfo.setSequence(messageData.getSequence());
      if (StringUtils.isNotEmpty(messageData.getContent())) {
        String encryptContent = messageData.getContent();

        if ((StringUtils.isNotEmpty(messageData.getIsEncrypt())) && (messageData.getIsEncrypt().equals("1")))
        {
          if (messageData.getEncrytType().equals("1"))
            try {
              encryptContent = AESUtil.aesEncrypt(messageData.getContent(), tradeMessageConfig.getEncrytKey());
            } catch (Exception e) {
              LOGGER.error("aes加密异常");
              resultInfo.setReturnCode("02");
              resultInfo.setReturnMessage("报文加密异常！" + e.toString());
              return resultInfo;
            }
          else if (messageData.getEncrytType().equals("2")) {
            try
            {
              if (requestFlag.equals("1"))
                encryptContent = RSAUtil.encryptByPrivateKey(messageData.getContent().getBytes(), tradeMessageConfig.getEncrytKey());
              else if (requestFlag.equals("2"))
                encryptContent = RSAUtil.encryptByPublicKey(messageData.getContent().getBytes(), tradeMessageConfig.getEncrytKey());
            }
            catch (Exception e) {
              LOGGER.error("RSA加密异常", e.toString());
              resultInfo.setReturnCode("02");
              resultInfo.setReturnMessage("报文加密异常！" + e.toString());
              return resultInfo;
            }
          }
        }
        packageInfo.setContent(encryptContent);
      } else {
        resultInfo.setReturnCode("M01");
        resultInfo.setReturnMessage("报文内容：报文内容为空！");
        return resultInfo;
      }
      if (StringUtils.isNotEmpty(messageData.getParamList())) {
        packageInfo.setParamList(messageData.getParamList());
      }

      dataExchangePackage.setPackageInfo(packageInfo);

      if (messageData.getBusinessType().equals("000000")) {
        ReturnState returnState = new ReturnState();
        checkResult = validateString(messageData.getReturnCode(), "M", 2);
        if (checkResult.equals("00")) {
          returnState.setReturnCode(messageData.getReturnCode());
        } else {
          resultInfo.setReturnCode(checkResult);
          resultInfo.setReturnMessage("返回状态内容：returnCode不合法！");
          return resultInfo;
        }

        checkResult = validateString(messageData.getReturnMessage(), "O", 100);
        if (StringUtils.isNotEmpty(messageData.getReturnMessage())) {
          if (checkResult.equals("00")) {
            returnState.setReturnMessage(messageData.getReturnMessage());
          } else {
            resultInfo.setReturnCode(checkResult);
            resultInfo.setReturnMessage("返回状态内容：returnMessage不合法！");
            return resultInfo;
          }
        }
        checkResult = validateString(messageData.getSourceMessageID(), "M", 26);
        if (checkResult.equals("00")) {
          returnState.setSourceMessageID(messageData.getSourceMessageID());
        } else {
          resultInfo.setReturnCode(checkResult);
          resultInfo.setReturnMessage("返回状态内容：sourceMessageID不合法！");
          return resultInfo;
        }
        dataExchangePackage.setReturnState(returnState);
      } else {
        ReturnState returnState = new ReturnState();
        if (StringUtils.isNotEmpty(messageData.getReturnCode()))
          returnState.setReturnCode(messageData.getReturnCode());
        if (StringUtils.isNotEmpty(messageData.getReturnMessage()))
          returnState.setReturnCode(messageData.getReturnMessage());
        if (StringUtils.isNotEmpty(messageData.getSourceMessageID()))
          returnState.setSourceMessageID(messageData.getSourceMessageID());
        dataExchangePackage.setReturnState(returnState);
      }

      String xmlMessage = XmlDocumentUtil.createXml(dataExchangePackage);
      if (StringUtils.isNotEmpty(xmlMessage)) {
        LOGGER.info("[报文创建成功：，报文id:{},报文messId:{}", messageData.getId(), messageData.getMessageID());
        LOGGER.debug("创建报文内容：{}", xmlMessage);
        resultInfo.setReturnCode("M00");
        resultInfo.setReturnMessage(xmlMessage);
        LOGGER.debug(xmlMessage);
        return resultInfo;
      }
      resultInfo.setReturnCode("M01");
      resultInfo.setReturnMessage("报文创建失败");
      return resultInfo;
    }

    resultInfo.setReturnCode("M01");
    resultInfo.setReturnMessage("报文创建失败");
    return resultInfo;
  }

  public static MsgExchangePackage encapsuExchangePackage(String globalBusinessID, String businessType, String messageID, String content, TradeMessageConfig tradeMessageConfig)
    throws Exception
  {
    KeyGeneratorContainer keyGeneratorContainer = (KeyGeneratorContainer)SpringBeanContext.getBean("decpKeyGeneratorContainerMsg");
    MsgExchangePackage messageData = new MsgExchangePackage();
    messageData.setGlobalBusinessID(globalBusinessID);
    messageData.setMessageID(messageID);
    messageData.setBusinessType(businessType);
    messageData.setContent(content);
    messageData.setSequence(Integer.valueOf(1));

    if ((businessType != null) && (!"".equals(businessType.trim())))
    {
      if (!"000000".equals(businessType))
      {
        if (tradeMessageConfig != null) {
          messageData.setSourceID(tradeMessageConfig.getSourceID());
          messageData.setDestinationID(tradeMessageConfig.getDestinationID());
          messageData.setSourceAppID(tradeMessageConfig.getSourceAppID());
          messageData.setDestinationAppID(tradeMessageConfig.getDestinationAppID());
          messageData.setIsZip(tradeMessageConfig.getIsZip());
          messageData.setZipType(tradeMessageConfig.getZipType());
          messageData.setIsEncrypt(tradeMessageConfig.getIsEncrypt());
          messageData.setEncrytType(tradeMessageConfig.getEncrytType());
          messageData.setSyncFlag(tradeMessageConfig.getSyncFlag());
        }
      }

      String id = keyGeneratorContainer.getNextKey("msgExchangePackageId");
      LOGGER.info("[encapsuExchangePackage-msgExchangePackageId]生成报文ID：{}", id);

      messageData.setId(id);
      messageData.setCreateTime(DateTimeUtil.getCurrentWorkDate());
      messageData.setLastUpdateTime(DateTimeUtil.getCurrentWorkDate());

      MsgTransferInfo transferInfo = new MsgTransferInfo();
      transferInfo.setSenderID(messageData.getSourceID());
      transferInfo.setReceiverID(messageData.getDestinationID());
      transferInfo.setSenderAppID(messageData.getSourceAppID());
      transferInfo.setReceiverAppID(messageData.getDestinationAppID());
      transferInfo.setIsRetry("0");
      transferInfo.setSourceMessageID(messageID);
      String transInfoId = keyGeneratorContainer.getNextKey("msgTransferInfoId");
      transferInfo.setId(transInfoId);
      transferInfo.setMsgId(id);
      transferInfo.setSendTime(DateTimeUtil.getCurrentWorkDate());
      transferInfo.setCreateTime(DateTimeUtil.getCurrentWorkDate());
      transferInfo.setLastUpdateTime(DateTimeUtil.getCurrentWorkDate());
      List transferInfos = new ArrayList();
      transferInfos.add(transferInfo);
      messageData.setMsgTransferInfos(transferInfos);
    }
    return messageData;
  }

  public static void copyMsgExchangePackage(MsgExchangePackage sourceExchangePackage, MsgExchangePackage targetExchangePackage)
  {
    KeyGeneratorContainer keyGeneratorContainer = (KeyGeneratorContainer)SpringBeanContext.getBean("decpKeyGeneratorContainerMsg");
    targetExchangePackage.setBusinessType(sourceExchangePackage.getBusinessType());
    targetExchangePackage.setSourceID(sourceExchangePackage.getDestinationID());
    targetExchangePackage.setDestinationID(sourceExchangePackage.getSourceID());
    targetExchangePackage.setSourceAppID(sourceExchangePackage.getDestinationAppID());
    targetExchangePackage.setDestinationAppID(sourceExchangePackage.getSourceAppID());
    targetExchangePackage.setMessageID(sourceExchangePackage.getMessageID());
    targetExchangePackage.setGlobalBusinessID(sourceExchangePackage.getGlobalBusinessID());
    targetExchangePackage.setSequence(Integer.valueOf(1));
    targetExchangePackage.setIsZip(sourceExchangePackage.getIsZip());
    targetExchangePackage.setZipType(sourceExchangePackage.getZipType());
    targetExchangePackage.setIsEncrypt(sourceExchangePackage.getIsEncrypt());
    targetExchangePackage.setEncrytType(sourceExchangePackage.getEncrytType());
    targetExchangePackage.setSyncFlag(sourceExchangePackage.getSyncFlag());
    String id = keyGeneratorContainer.getNextKey("msgExchangePackageId");
    LOGGER.info("[copyMsgExchangePackage-msgExchangePackageId]生成报文ID：{}", id);

    targetExchangePackage.setId(id);
    targetExchangePackage.setCreateTime(DateTimeUtil.getCurrentWorkDate());
    targetExchangePackage.setLastUpdateTime(DateTimeUtil.getCurrentWorkDate());
    MsgTransferInfo transferInfo = new MsgTransferInfo();
    MsgTransferInfo sourceTransferInfo = (MsgTransferInfo)sourceExchangePackage.getMsgTransferInfos().get(0);

    transferInfo.setSenderID(sourceTransferInfo.getReceiverID());
    transferInfo.setReceiverID(sourceTransferInfo.getSenderID());
    transferInfo.setSenderAppID(sourceTransferInfo.getReceiverAppID());
    transferInfo.setReceiverAppID(sourceTransferInfo.getSenderAppID());
    transferInfo.setIsRetry(sourceTransferInfo.getIsRetry());
    transferInfo.setSourceMessageID(sourceExchangePackage.getMessageID());
    String transInfoId = keyGeneratorContainer.getNextKey("msgTransferInfoId");
    transferInfo.setId(transInfoId);
    transferInfo.setMsgId(id);
    transferInfo.setSendTime(DateTimeUtil.getCurrentWorkDate());
    transferInfo.setCreateTime(DateTimeUtil.getCurrentWorkDate());
    transferInfo.setLastUpdateTime(DateTimeUtil.getCurrentWorkDate());
    List transferInfos = new ArrayList();
    transferInfos.add(transferInfo);
    targetExchangePackage.setMsgTransferInfos(transferInfos);
  }

  private static String validateString(String text, String isMust, int length)
  {
    if (isMust.equals("M"))
    {
      if (StringUtils.isEmpty(text))
        return "M01";
      if (text.length() != length)
      {
        return "M01";
      }
    }
    else if ((StringUtils.isNotEmpty(text)) && (text.length() != length)) {
      return "M01";
    }

    return "00";
  }

  public static Object copyProperties(Object source, Object target, String[] ignoreProperties)
    throws Exception
  {
    Class targetClass = target.getClass();
    Class sourceClass = source.getClass();

    PropertyDescriptor[] targetPds = Introspector.getBeanInfo(targetClass)
      .getPropertyDescriptors();
    PropertyDescriptor[] sourcetPds = Introspector.getBeanInfo(sourceClass)
      .getPropertyDescriptors();

    List ignoreList = ignoreProperties == null ? null : 
      Arrays.asList(ignoreProperties);

    Map sourcePropertyMap = new HashMap();
    for (int i = 0; i < sourcetPds.length; i++) {
      PropertyDescriptor pd = sourcetPds[i];
      sourcePropertyMap.put(pd.getName(), pd);
    }
    for (int i = 0; i < targetPds.length; i++) {
      PropertyDescriptor targetPd = targetPds[i];

      if ((targetPd.getWriteMethod() != null) && ((ignoreProperties == null) || 
        (!ignoreList.contains(targetPd.getName()))))
      {
        PropertyDescriptor sourcePd = (PropertyDescriptor)sourcePropertyMap.get(targetPd
          .getName());
        if ((sourcePd != null) && (sourcePd.getReadMethod() != null))
        {
          try
          {
            Method readMethodTarget = targetPd.getReadMethod();
            if (!Modifier.isPublic(readMethodTarget.getDeclaringClass().getModifiers()))
              readMethodTarget.setAccessible(true);
            Object Targetvalue = readMethodTarget.invoke(target, new Object[0]);

            if (Targetvalue == null) {
              Method readMethod = sourcePd.getReadMethod();

              if (!Modifier.isPublic(readMethod.getDeclaringClass()
                .getModifiers()))
                readMethod.setAccessible(true);
              Object value = readMethod.invoke(source, new Object[0]);

              Method writeMethod = targetPd.getWriteMethod();

              if (!Modifier.isPublic(writeMethod.getDeclaringClass()
                .getModifiers()))
                writeMethod.setAccessible(true);
              writeMethod.invoke(target, new Object[] { value });
            }
          } catch (Throwable ex) {
            throw new IllegalArgumentException(
              "Could not copy properties from source to target", ex);
          }
        }
      }
    }
    return target;
  }
}