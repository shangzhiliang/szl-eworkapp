package com.szl.web.action;

import com.szl.common.action.BaseStruts2Action;

/**
 * 
 *<pre>
 *<b>类描述:</b>角色类配置
 *<b>作者:</b>szl
 *<b>创建日期:</b>2015年10月21日 上午10:43:36
 *</pre>
 */
public class RoleAction extends BaseStruts2Action {
	
	public String add(){
		System.out.println(" add role 。。");
		return "success";
	}
	
	public String delete(){
		System.out.println(" delete role 。。");
		return "success";
	}
	
	public String edit(){
		System.out.println(" edit role 。。");
		return "success";
	}
	
	public String view(){
		System.out.println(" view role 。。");
		return "success";
	}
}
