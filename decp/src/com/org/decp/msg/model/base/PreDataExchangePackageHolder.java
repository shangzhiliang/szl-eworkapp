package com.org.decp.msg.model.base;

import com.org.decp.msg.model.DataExchangePackageHolder;
import com.org.decp.xml.DefautObjectWrapper;
import com.org.decp.xml.ValidateException;


public abstract class PreDataExchangePackageHolder
  implements DataExchangePackageHolder<DataExchangePackage>
{
  protected DataExchangePackage dataExchangePackage;
  protected DefautObjectWrapper<DataExchangePackage> defautObjectWrapper;

  public PreDataExchangePackageHolder()
  {
  }

  public PreDataExchangePackageHolder(DefautObjectWrapper<DataExchangePackage> defautObjectWrapper)
  {
    this.defautObjectWrapper = defautObjectWrapper;
    this.dataExchangePackage = new DataExchangePackage();
  }

  public String beanToXml() throws ValidateException
  {
    String result = this.defautObjectWrapper.toXml(this);
    return result;
  }

  public DataExchangePackage xmlToBean(String xmlStr) throws ValidateException
  {
    DataExchangePackage result = (DataExchangePackage)this.defautObjectWrapper.toBean(xmlStr, this);
    if (result != null) {
      setDataExchangePackage(result);
    }
    return result;
  }

  protected DefautObjectWrapper<DataExchangePackage> getDefautObjectWrapper()
  {
    return this.defautObjectWrapper;
  }

  protected void setDefautObjectWrapper(DefautObjectWrapper<DataExchangePackage> defautObjectWrapper) {
    this.defautObjectWrapper = defautObjectWrapper;
  }

  protected DataExchangePackage getDataExchangePackage()
  {
    return this.dataExchangePackage;
  }

  protected void setDataExchangePackage(DataExchangePackage dataExchangePackage) {
    this.dataExchangePackage = dataExchangePackage;
  }
}