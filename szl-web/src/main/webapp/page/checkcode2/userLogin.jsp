<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<table>
<tr>  
  <td width="100">  
    <span class="hint">*[验证码]:只能输入数字和负号"-"</span>  
  </td>  
</tr>  
<tr>  
  <td width="100">  
    <label for="userRegPassWord">验证码</label>  
          <input type="text" id="verificationcodereg" name="verificationcodereg" tabindex="3" maxlength="3" class="t_input" value="" style="ime-mode:disabled;"  onblur="checkuserverificationcode(this);" onkeypress="return (/[\d-]/.test(String.fromCharCode(event.keyCode)));"  />  
         <span id="usvcmsg"></span>  
        <input type="hidden" id="verificationcoderegflag" value="" />  
  </td>  
</tr>  
<tr>  
  <td width="140">  
    <div id="refreshvc">  
    <span onclick="refreshvc();" style="padding-left: 49px;cursor: pointer;"><img id="verificationcodeimg" src="${pageContext.request.contextPath}/servertempdictory/fileicon/defaultverificationcode.jpeg" />  
    <span style="height: 30px;width: 16px;padding-left: 4px;"><img src="image/refreshvc.png" /></span>  
    <span style="font-size: 12px;color:#2c629e; width: 20px;">换一张</span>  
    </span>  
    </div>  
  </td>  
</tr> 
</table>
</body>
</html>