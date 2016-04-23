<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% 
	String context = request.getContextPath();
	System.out.println("context:"+context);
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>查询企业征信系统信息</title>
<script type="text/javascript" src="<%=context %>/js/jquery-1.7.2.min.js"></script>

<script type="text/javascript">

function checkcodeYz(){
	//alert(" 获取验证码地址。");
	var key_word = document.getElementById("key_word").value;
	$.ajax({
		   type:"post",
		   url:"http://localhost:8080/szl-web/checkCode",
		   dataType:"text",
           error:function(){
               alert("error occured!!!");  
           },
		   success:function(data){
				//alert(data);
				var img = document.getElementById("MzImgExpPwd");
	          	img.src = data;
		   }
	});
}
	

</script>
</head>
<body>
<form action="">
	<table border="1" cellpadding="0" cellspacing="0" style="width:50%">
		<tr>
			<td align="center"><input type="text" id="key_word" name="key_word" width="300px;"/></td>
			<td>
 				<H3 align="right">
 				<A hidefocus="true" onclick="checkcodeYz()" 
     				 href="javascript:void(0)">
     				<IMG width="94" height="44" src="<%= request.getContextPath()%>/image/h-sou-an.jpg"></A>
     			</H3>
			</td>
		</tr>
		<tr>
			<td>
				 	<input name="currentTimeMillis" id="currentTimeMillis" type="hidden" value="${currentTimeMillis}">
					<img id="MzImgExpPwd" style="width: 200px;height: 50px" 
						alt="" src="http://query.5184.com/query/image.jsp"/> 
					<a style="line-height: 56px; display: block;" href="javascript:void(0)">换一张</A>
			</td>
			<td>
				
			</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
		</tr>
	</table>
	
	<br/>
	
</form>
</body>
</html>