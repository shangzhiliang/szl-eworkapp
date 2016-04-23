package com.szl.web.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	public void checkverificationcode(){
		//request  
		   HttpServletRequest request = ServletActionContext.getRequest();  
		   //response  
		   HttpServletResponse response = ServletActionContext.getResponse();  
		   //output stream  
		   PrintWriter out = null;  
		   //session  
		   HttpSession session = request.getSession();  
		   //verification code from client  
		   String vcclient = null;  
		   //verification code from server  
		   String vcserver = null;  
		   //verification code demander  
		   String vCdemander = request.getParameter("vcdemander");  
		   //check verification code to clien flag  
		   String checktoclienflag = null;  
		   try{  
		    //get verificationcode to session  
		    //login  
		    if("userlogin".equals(vCdemander)){  
		        vcclient = request.getParameter("vclogincode");  
		        vcserver = String.valueOf(session.getAttribute("verificationcodeuserlogin"));  
		    }  
		    //regiser  
		    if("userregister".equals(vCdemander)){  
		        vcclient = request.getParameter("vcregcode");  
		        vcserver = String.valueOf(session.getAttribute("verificationcodeuserregister"));  
		    }  
//		     vcclient = UserController.encodingconvert(vcclient);
		     
		     vcclient = vcclient == null ? "" : vcclient;  
		     if(vcclient.equals(vcserver)){  
		         checktoclienflag = "vcok";  
		     }else{  
		         checktoclienflag = "vcwrong";  
		     }  
		       //set no cache,content type ,encoding  
		       response.setHeader("Cache-Control", "no-cache");  
		       response.setContentType("text/html");  
		       response.setCharacterEncoding("GBK");  
		       //output stream  
		       out = response.getWriter();  
		       out.print(checktoclienflag);  
		       logger.info("验证码：vcclient: "+vcclient+"vcserver: "+vcserver+"验证顺利");  
		       
		   }catch (Exception e) {  
			   logger.error("vcclient: "+vcclient+"vcserver: "+vcserver+"验证失败",e);  
		   } finally {  
		       out.flush();  
		       out.close();  
		   }  
	}
}
