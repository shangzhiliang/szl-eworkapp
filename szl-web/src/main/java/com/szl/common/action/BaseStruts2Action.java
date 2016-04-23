package com.szl.common.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

/**
 *<pre>
 *<b>类描述:</b>定义实现了struts2，实例了request，response，session对象的基类，
 *          所有action均可以继承此基类来实现struts2的功能。
 *          此基类不允许实例化，因此定义为抽象类。
 *<b>作者:</b>szl
 *<b>创建日期:</b>2015年10月21日 上午10:45:38
 *</pre>
 */
public abstract class BaseStruts2Action extends ActionSupport implements ServletResponseAware,
		ServletRequestAware, SessionAware {
	
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 6092297660303432223L;
	protected HttpServletResponse response;
	protected HttpServletRequest request;
	protected Map<String, Object> session;

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public Map<String, Object> getSession() {
		return session;
	}
}
