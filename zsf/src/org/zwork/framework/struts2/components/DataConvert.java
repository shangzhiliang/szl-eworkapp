package org.zwork.framework.struts2.components;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.ListUIBean;
import org.apache.struts2.util.StrutsTypeConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.util.ValueStack;

public class DataConvert extends ListUIBean
{
  public static final String TEMPLATE = "dataConvert";
  protected String defaultValue;
  protected String withCode;
  protected String separator;

  public DataConvert(ValueStack stack, HttpServletRequest request, HttpServletResponse response)
  {
    super(stack, request, response);
  }

  protected String getDefaultTemplate() {
    return "dataConvert";
  }

  public void evaluateExtraParams() {
    super.evaluateExtraParams();

    if (this.defaultValue != null) {
      addParameter("defaultValue", this.defaultValue);
    }
    if (this.withCode != null) {
      addParameter("withCode", this.withCode);
    }
    if (this.separator != null)
      addParameter("separator", this.separator);
  }

  public void setDefault(String defaultValue)
  {
    this.defaultValue = defaultValue;
  }

  public void setWithCode(String withCode) {
    this.withCode = withCode;
  }

  public void setSeparator(String separator) {
    this.separator = separator;
  }
}