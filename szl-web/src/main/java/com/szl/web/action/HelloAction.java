package com.szl.web.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Date;

import com.szl.common.utils.Struts2Utils;
import com.szl.web.httpserver.dto.PersonDto;

import net.sf.json.JSONObject;

/**
 * 处理所有请求的action
 *<pre>
 *<b>类描述:</b>(这里用一句话描述这个类的作用)
 *<b>作者:</b>szl
 *<b>创建日期:</b>2015年10月20日 下午6:16:51
 *</pre>
 */
public class HelloAction {

	public String execute(){
		System.out.println("执行了execute.方法。。。");
		return "success";
	}
	
	
	public String doPost() throws Throwable{
	
		InputStream is =  Struts2Utils.getRequest().getInputStream();
		InputStreamReader inputStreamReader = new InputStreamReader(is,"UTF-8");
        BufferedReader reader = new BufferedReader(inputStreamReader);// 读字符串用的。
		 
        StringBuilder result = new StringBuilder();
        
        String temp;
        
        while (((temp = reader.readLine()) != null)) {
            result.append(temp);
        }

        reader.close();// 关闭输入流
        
        System.out.println("get request :" + result.toString());
        
        //在这里把result这个字符串个给JSONObject。解读里面的内容。
        JSONObject jsonObject = JSONObject.fromObject(result.toString());

        JSONObject retJson = getOnePersonBean(jsonObject);
        
        System.out.println("get object :"+retJson.toString());
        
        Struts2Utils.getResponse().setHeader("content-type","text/html;charset=UTF-8");
        Struts2Utils.getResponse().setCharacterEncoding("UTF-8");
		
        PrintWriter pw;
		try {
			pw = Struts2Utils.getResponse().getWriter();
			pw.write(retJson.toString());
			pw.flush();
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		 
		return null;
	}
	
	 private JSONObject getOnePersonBean(JSONObject jsonObject){
			
		System.out.println("request obj :"+jsonObject.toString());
		 
		String code = (String)jsonObject.get("partner_code");
		
		String key = (String)jsonObject.get("partner_key");
		
		String id = (String)jsonObject.get("auth_id");
		
		String name = (String)jsonObject.get("auth_name");
		
		JSONObject returnJson = new JSONObject();
		
		if( id != null && name != null){
//			returnJson.put("success", true);
//			returnJson.put("result", "no");
//			returnJson.put("reason_code", "605:no record");
//			returnJson.put("seq_id", "1429237147381-85043846");
			
			PersonDto person = new PersonDto();
			person.setName("商志亮");
			person.setAge(28);
			person.setBrithday(new Date(System.currentTimeMillis()-2323232));
			person.setSex("男");
			person.setEmail("szl@yinker.com");
			person.setPhone("15811120435");
			
			returnJson = JSONObject.fromObject(person);
			
		}
		
		return returnJson;
		 
	 }
	
}
