package com.szl.web.httpserver.dto;

import java.util.Date;

/**
 * 
 *<pre>
 *<b>类描述:</b> 自然人的实体对象
 *<b>作者:</b>szl
 *<b>创建日期:</b>2015年10月20日 下午3:10:12
 *</pre>
 */
public class PersonDto implements java.io.Serializable {

	/**
	 * 实现序列化
	 */
	private static final long serialVersionUID = 7247414764992890129L;
	
	private String name;
	private String sex;
	private int age;
	private Date brithday;
	private String email;
	private String phone;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@SuppressWarnings("deprecation")
	public String toString(){
		StringBuffer sbf = new StringBuffer();
		sbf.append("{ name :" + this.name);
		sbf.append(" sex :" + this.sex);
		sbf.append(" age :" + this.age);
		sbf.append(" brithday :" + this.brithday.toLocaleString());
		sbf.append(" email :" + this.email);
		sbf.append(" phone :" + this.phone);
		sbf.append(" }");
		
		return sbf.toString();
		
	}
}
