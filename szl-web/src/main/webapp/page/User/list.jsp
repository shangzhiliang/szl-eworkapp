<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>
	 这 是 list 页面
	</h1>
	
	<br>
	${userName} --- ${age} --- ${brithday}
	<br/>
	<s:property value="userName"/>----<s:property value="age"/>---<s:property value="brithday"/>
	<br/>
	${aaa }----${bbb }---
	<br/>
	<!-- 使用s:property 访问ActionContext 中设置的值都需要加#，在struts2.3之后如果ActionContext
	中设置的是string类型的，不需要加#，实际现在测试数字也不需要加呢？？？-->
	<s:property value="#aaa"/>----<s:property value="bbb"/>----<s:property value="ccc"/>
	<br/>
	${sss } ----${www }
	<br/>
	<!-- 使用 s:property 访问ServletActionContext中设置的值，要用#request.属性 获取-->
	<s:property value="#request.sss"/> ----<s:property value="#request.www"/>
</body>
</html>