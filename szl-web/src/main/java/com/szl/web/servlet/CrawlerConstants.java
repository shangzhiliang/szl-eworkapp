package com.szl.web.servlet;

/**
 *
 *<pre>
 *<b>类描述:</b>(爬取网页时使用的常量数据)
 *<b>作者:</b>商志亮
 *<b>创建日期:</b>2015年12月1日 下午4:42:07
 *</pre>
 */
public class CrawlerConstants {
	
	
//	var url= "/login/loginAction!checkCode.dhtml?check_code="+encodeURIComponent(document.getElementById("textfield").value)+"&currentTimeMillis="+document.getElementById("currentTimeMillis").value;
//	var cnum = "&random="+Math.ceil(Math.random()*100000);
//	var credit_ticket = "8B65DEA2891E827731C35E1B8C0EF88C";
//	var timemillis = "?currentTimeMillis="+document.getElementById("currentTimeMillis").value+ "&credit_ticket="+credit_ticket + "&check_code="+encodeURIComponent(document.getElementById("textfield").value );
//	
	
	/* 北京市企业信用报告信息链接*/
	/* -企业信用*/
	public static final String URL_BJ_QYXY = "http://qyxy.baic.gov.cn:80/simple/dealSimpleAction!transport_ww.dhtml?fourth=fourth&sysid=0150008788304366b7d3903b5067bb8c&module=wzsy&styleFlag=sy";

	public static final String rootUrl = "http://qyxy.baic.gov.cn/";
	
	public static final String titleRegex = "<title>(.*?)</title>";  
	//<frame src="http://qyxy.baic.gov.cn:80/simple/dealSimpleAction!transport_ww.dhtml?fourth=fourth&sysid=0150008788304366b7d3903b5067bb8c&module=wzsy&styleFlag=sy"
	public static final String frameSrcRegex = "<frame src=\"(.*?)\"";//"• <a href=\"(.*?)\""; 
	
	public static final String sessionRegex = "var credit_ticket\\s+=\\s+\"(.*?)\"";
	
	//<INPUT name="currentTimeMillis" id="currentTimeMillis" type="hidden" value="
	public static final String currentTimeMillisRegex = "src=\"\\/CheckCodeYunSuan\\?currentTimeMillis=(.*?)\"";
	
	//src="/CheckCodeYunSuan?currentTimeMillis=
	public static final String imgsrcRegex = "src=\"\\/CheckCodeYunSuan\\?currentTimeMillis=(.*?)\"";
	
	public static final String imgsrc = "CheckCodeYunSuan?currentTimeMillis=";
	
	public static final String luceneUrl = "http://qyxy.baic.gov.cn/lucene/luceneAction!NetCreditLucene.dhtml?currentTimeMillis=";
	//http://qyxy.baic.gov.cn/lucene/luceneAction!NetCreditLucene.dhtml?currentTimeMillis=1449136572707&credit_ticket=99FEA0302A6B59D6116C052A4CFBCBC0&check_code=18 
}
