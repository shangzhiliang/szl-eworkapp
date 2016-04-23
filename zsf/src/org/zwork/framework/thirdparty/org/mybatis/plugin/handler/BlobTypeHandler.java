package org.zwork.framework.thirdparty.org.mybatis.plugin.handler;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BlobTypeHandler extends BaseTypeHandler<String>
{
  private static final Logger LOGGER = LoggerFactory.getLogger(BlobTypeHandler.class);
  private static final String DEFAULT_CHARSET = "utf-8";
  
public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType)
  throws SQLException
{
	 ByteArrayInputStream bis;
  try
  {
    bis = new ByteArrayInputStream(parameter.getBytes("utf-8"));
  }
  catch (UnsupportedEncodingException e)
  {
    LOGGER.error("String转Blog出错，异常信息如下：", e);
    throw new RuntimeException("String转Blog出错");
  }
  ps.setBinaryStream(i, bis, parameter.length());
}

public String getNullableResult(ResultSet rs, String columnName) throws SQLException
{
  Blob blob = rs.getBlob(columnName);
  byte[] returnValue = null;
  String result = null;
  if (blob != null)
    returnValue = blob.getBytes(1L, (int)blob.length());
  try
  {
    if (returnValue != null)
    {
      result = new String(returnValue, "utf-8");
    }
  } catch (UnsupportedEncodingException e) {
    LOGGER.error("Blog转String出错，异常信息如下：", e);
    throw new RuntimeException("Blog转String出错");
  }
  return result;
}

public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException
{
  Blob blob = cs.getBlob(columnIndex);
  byte[] returnValue = null;
  String result = null;
  if (blob != null)
    returnValue = blob.getBytes(1L, (int)blob.length());
  try
  {
    if (returnValue != null)
      result = new String(returnValue, "utf-8");
  }
  catch (UnsupportedEncodingException e) {
    LOGGER.error("Blog转String出错，异常信息如下：", e);
    throw new RuntimeException("Blog转String出错");
  }
  return result;
}

public String getNullableResult(ResultSet rs, int columnName) throws SQLException
{
  String result = null;
  Blob blob = rs.getBlob(columnName);
  byte[] returnValue = null;
  if (blob != null) {
    returnValue = blob.getBytes(1L, (int)blob.length());
  }
  try
  {
    if (returnValue != null)
      result = new String(returnValue, "utf-8");
  }
  catch (UnsupportedEncodingException e) {
    LOGGER.error("Blog转String出错，异常信息如下：", e);
    throw new RuntimeException("Blog转String出错");
  }
  return result;
}
}