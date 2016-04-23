package com.org.decp.msg.util;

import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.swork.common.utils.DateUtils;

import com.org.decp.msg.model.base.MsgExchangePackage;
import com.org.decp.msg.model.base.MsgTransferInfo;


public class XmlDocumentUtil
{
  private static final Logger LOGGER = LoggerFactory.getLogger(XmlDocumentUtil.class);

  public static <T> String createXml(T obj)
  {
    try
    {
      Document document = DocumentHelper.createDocument();

      String className = obj.getClass().getSimpleName();

      String rootname = className.substring(0, 1).toLowerCase() + className.substring(1);
      Element root = document.addElement(rootname);
      Field[] properties = obj.getClass().getDeclaredFields();
      for (int i = 0; i < properties.length; i++)
      {
        Method meth = obj.getClass().getMethod("get" + properties[i].getName().substring(0, 1).toUpperCase() + properties[i].getName().substring(1), new Class[0]);

        if (meth.invoke(obj, new Object[0]) != null) {
          Element element = root.addElement(properties[i].getName());

          Object object = meth.invoke(obj, new Object[0]);

          if (!isWrapClass(object.getClass()))
          {
            Field[] childProperties = object.getClass().getDeclaredFields();
            for (int j = 0; j < childProperties.length; j++)
            {
              Method meth2 = object.getClass().getMethod("get" + childProperties[j].getName().substring(0, 1).toUpperCase() + childProperties[j].getName().substring(1), new Class[0]);

              Object object2 = meth2.invoke(object, new Object[0]);
              if (object2 != null)
              {
                if (childProperties[j].getName().equals("content"))
                {
                  element.addElement(childProperties[j].getName()).addCDATA(object2.toString());
                } else if (childProperties[j].getName().equals("sendTime"))
                {
                  element.addElement(childProperties[j].getName()).setText(DateUtils.dateTimeToString((Date)object2));
                } else if (childProperties[j].getName().equals("sequence"))
                  element.addElement(childProperties[j].getName()).setText(((Integer)object2).toString());
                else
                  element.addElement(childProperties[j].getName()).setText(object2.toString());
              }
            }
          }
          else {
            element.addElement(properties[i].getName()).setText(meth.invoke(object, new Object[0]).toString());
          }
        }
      }

      OutputFormat format = OutputFormat.createPrettyPrint();

      format.setEncoding("UTF-8");
      StringWriter sw = new StringWriter();
      XMLWriter xw = new XMLWriter(sw, format);
      try {
        xw.write(document);
        xw.flush();
        xw.close();
      } catch (IOException e) {
        LOGGER.error("格式化XML文档发生异常，请检查！", e);
      }
      return sw.toString();
    } catch (Exception e) {
      LOGGER.error("生成XML格式报文发生异常，请检查！", e);
    }return null;
  }

  public static MsgExchangePackage parseXml(String xml)
    throws Exception
  {
    MsgExchangePackage messageData = new MsgExchangePackage();
    Document document = DocumentHelper.parseText(xml);

    Element rootElm = document.getRootElement();

    Element envelopElm = rootElm.element("envelopInfo");
    for (Iterator it = envelopElm.elements().iterator(); it.hasNext(); ) {
      Element elm = (Element)it.next();

      invokeMethod(messageData, "set" + elm.getName().substring(0, 1).toUpperCase() + elm.getName().substring(1), new Object[] { elm.getTextTrim() });
    }

    Element contentContElm = rootElm.element("contentControl");
    for (Iterator it = contentContElm.elements().iterator(); it.hasNext(); ) {
      Element elm = (Element)it.next();

      invokeMethod(messageData, "set" + elm.getName().substring(0, 1).toUpperCase() + elm.getName().substring(1), new Object[] { elm.getTextTrim() });
    }

    Element returnStateElm = rootElm.element("returnState");
    for (Iterator it = returnStateElm.elements().iterator(); it.hasNext(); ) {
      Element elm = (Element)it.next();

      invokeMethod(messageData, "set" + elm.getName().substring(0, 1).toUpperCase() + elm.getName().substring(1), new Object[] { elm.getTextTrim() });
    }

    Iterator transInfoIte = rootElm.elementIterator("transferInfo");
    List transInfoList = new ArrayList();
    while (transInfoIte.hasNext()) {
      Element elm = (Element)transInfoIte.next();
      MsgTransferInfo transferInfo = new MsgTransferInfo();
      for (Iterator it = elm.elements().iterator(); it.hasNext(); ) {
        Element childelm = (Element)it.next();
        if (childelm.getName().equals("sendTime")) {
          transferInfo.setSendTime(DateUtils.stringToDateTime(childelm.getTextTrim()));
        }
        else {
          invokeMethod(transferInfo, "set" + childelm.getName().substring(0, 1).toUpperCase() + childelm.getName().substring(1), new Object[] { childelm.getTextTrim() });
        }
      }
      if (transferInfo != null) {
        transInfoList.add(transferInfo);
      }
    }
    if (!transInfoList.isEmpty()) {
      messageData.setMsgTransferInfos(transInfoList);
    }

    Element packageInfo = rootElm.element("packageInfo");
    for (Iterator it = packageInfo.elements().iterator(); it.hasNext(); ) {
      Element elm = (Element)it.next();
      if (elm.getName().equals("sequence")) {
        messageData.setSequence(Integer.valueOf(Integer.parseInt(elm.getTextTrim())));
      }
      else {
        invokeMethod(messageData, "set" + elm.getName().substring(0, 1).toUpperCase() + elm.getName().substring(1), new Object[] { elm.getData() });
      }
    }
    return messageData;
  }

  private static void invokeMethod(Object owner, String methodName, Object[] args)
  {
    Class ownerClass = owner.getClass();
    Class[] argsClass = new Class[args.length];
    int i = 0; for (int j = args.length; i < j; i++)
      argsClass[i] = args[i].getClass();
    try
    {
      Method method = ownerClass.getMethod(methodName, argsClass);
      method.invoke(owner, args);
    } catch (SecurityException e) {
      LOGGER.error("解析报文，调用反射机制方法出错" + e);
    } catch (IllegalArgumentException e) {
      LOGGER.error("解析报文，调用 机制方法出错" + e);
    } catch (NoSuchMethodException e) {
      LOGGER.error("解析报文，调用反射机制方法出错" + e);
    } catch (IllegalAccessException e) {
      LOGGER.error("解析报文，调用反射机制方法出错" + e);
    } catch (InvocationTargetException e) {
      LOGGER.error("解析报文，调用反射机制方法出错" + e);
    }
  }

  public static boolean isWrapClass(Class cla)
  {
    boolean result = false;
    if ((cla.isPrimitive()) || (cla.isAssignableFrom(String.class)) || 
      (cla.isAssignableFrom(Number.class)) || (cla.isAssignableFrom(Date.class)) || (cla.isAssignableFrom(Integer.class))) {
      result = true;
    }
    return result;
  }
}