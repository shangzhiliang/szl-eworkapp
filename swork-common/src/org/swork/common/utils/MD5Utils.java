package org.swork.common.utils;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MD5Utils
{
  private static final Logger LOGGER = LoggerFactory.getLogger(MD5Utils.class);

  public static final String MD5(String s)
  {
    char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
    byte[] btInput = s.getBytes();
    try
    {
      MessageDigest mdInst = MessageDigest.getInstance("MD5");

      mdInst.update(btInput);

      byte[] md = mdInst.digest();

      int j = md.length;
      char[] str = new char[j * 2];
      int k = 0;
      for (int i = 0; i < j; i++) {
        byte byte0 = md[i];
        str[(k++)] = hexDigits[(byte0 >>> 4 & 0xF)];
        str[(k++)] = hexDigits[(byte0 & 0xF)];
      }
      return new String(str);
    } catch (NoSuchAlgorithmException e) {
      LOGGER.error("生成MD5签名异常!", e);
    }return null;
  }
}