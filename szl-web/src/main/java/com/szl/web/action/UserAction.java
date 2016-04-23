package com.szl.web.action;

import java.util.Date;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport {

	private String userName;
	private int age;
	private Date brithday;
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Date getBrithday() {
		return brithday;
	}

	public void setBrithday(Date brithday) {
		this.brithday = brithday;
	}

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -3294228972044222372L;

	public String addInput(){
		System.out.println(" addInput ...");
		return "success";
	}
	
	public String add(){
		System.out.println(" add ...");
		return "r_list";
	}
	
	public String list(){
		//第一种方式传值，action中设置属性，提供get，set方法。
		this.setUserName("张三");
		this.setAge(18);
		this.setBrithday(new Date());
		//第二种方式通过ActionContext来传值。
		ActionContext.getContext().put("aaa", 12345);
		ActionContext.getContext().put("bbb", 67890);
		ActionContext.getContext().put("ccc", "abc");
		
		//第三种方式通过ServletActionContext内置对象设置requet作用域的值
		ServletActionContext.getRequest().setAttribute("sss", "1111111");
		ServletActionContext.getRequest().setAttribute("www", "baidu");
		
		System.out.println(" list ...");
		return "success";
	}

}
