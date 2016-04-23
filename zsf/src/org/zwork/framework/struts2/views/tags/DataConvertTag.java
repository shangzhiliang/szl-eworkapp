package org.zwork.framework.struts2.views.tags;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ui.AbstractRequiredListTag;
import org.zwork.framework.struts2.components.DataConvert;

import com.opensymphony.xwork2.util.ValueStack;


public class DataConvertTag extends AbstractRequiredListTag
{
  protected String defaultValue;
  protected String withCode;
  protected String separator;

  public Component getBean(ValueStack stack, HttpServletRequest req, HttpServletResponse res)
  {
    return new DataConvert(stack, req, res);
  }

  protected void populateParams() {
    super.populateParams();

    DataConvert dataConvert = (DataConvert)this.component;
    dataConvert.setDefault(this.defaultValue);
    dataConvert.setWithCode(this.withCode);
    dataConvert.setSeparator(this.separator);
  }

  public void setDefault(String defaultValue) {
    this.defaultValue = defaultValue;
  }

  public void setWithCode(String withCode) {
    this.withCode = withCode;
  }

  public void setSeparator(String separator) {
    this.separator = separator;
  }
}