package org.zwork.framework.base.support;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class BaseStruts2Action extends ActionSupport
  implements ServletRequestAware, ServletResponseAware
{
  private static final long serialVersionUID = 1L;
  protected HttpServletRequest request;
  protected HttpServletResponse response;
  protected HttpSession session;
  protected static final String VIEW = "view";
  protected static final String CREATE = "create";
  protected static final String EDIT = "edit";
  protected static final String LIST = "list";
  protected static final String TEXT = "text";
  protected static final String PROMPT = "operate-prompt";
  protected String operate;
  private String errorMessage;
  private String warnMessage;
  private String infoMessage;
  protected Pagination page;
  private String text;

  public Pagination getPage()
  {
    return this.page;
  }

  public void setPage(Pagination page)
  {
    this.page = page;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public void setWarnMessage(String warnMessage) {
    this.warnMessage = warnMessage;
  }

  public void setInfoMessage(String infoMessage) {
    this.infoMessage = infoMessage;
  }

  public String getMessage() {
    if (StringUtils.isNotEmpty(this.errorMessage))
      return "<div id=\"system_msg\" class=\"error\">" + this.errorMessage + "</div>";
    if (StringUtils.isNotEmpty(this.warnMessage))
      return "<div id=\"system_msg\" class=\"warn\">" + this.warnMessage + "</div>";
    if (StringUtils.isNotEmpty(this.infoMessage)) {
      return "<div id=\"system_msg\" class=\"info\">" + this.infoMessage + "</div>";
    }
    return "";
  }

  public String getText()
  {
    return this.text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public void setServletRequest(HttpServletRequest request) {
    this.request = request;
    if (request != null)
      this.session = request.getSession();
  }

  public void setServletResponse(HttpServletResponse response)
  {
    this.response = response;
  }

  public String getOperate() {
    return this.operate;
  }

  public void setOperate(String operate) {
    this.operate = operate;
  }
}