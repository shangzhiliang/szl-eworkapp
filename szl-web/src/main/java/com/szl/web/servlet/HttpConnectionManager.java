package com.szl.web.servlet;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang3.StringEscapeUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Method;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.szl.common.utils.DoRegex;
import com.szl.common.utils.JsoupUtils;
import com.szl.common.utils.StrUtils;
import com.szl.web.bean.ParametersBean;

/**
 * 数据抓取通用方法定义
 * @author lx
 *
 */
public class HttpConnectionManager {

	private static volatile HttpConnectionManager httpClient = null;
	
	
	//2. ThreadLocal 实现安全单例
//	private static final ThreadLocal<HttpConnectionManager> perThreadInstance = new ThreadLocal<HttpConnectionManager>(); 
	
	private HttpConnectionManager(){}
	
	public static HttpConnectionManager getInstance(){
		/* 1. 双重检查*/
		if(httpClient == null){
			synchronized(HttpConnectionManager.class){
				if(httpClient == null){
					return new HttpConnectionManager();
				}
			}
		}
		
		// 2. 
//		if(perThreadInstance.get() == null){
//			synchronized(HttpConnectionManager.class){
//				if(httpClient ==  null){
//					httpClient = new HttpConnectionManager();
//				}
//			}
//			
//			perThreadInstance.set(httpClient);
//		}
		
		// 3.内部类方式实例单例
//		return Holder.instance; 
		
		return httpClient;
	}
	
	/**
	 * 使用给定链接获取html源码
	 * @param paramBean
	 * @return
	 * @throws Exception 
	 * @throws IOException
	 */
	public String getHtml(ParametersBean paramBean) throws Exception{
		Connection.Response response;
		String html = null;
		try {
			response = Jsoup
				.connect(paramBean.getUrl())
				.header("User-Agent",
					"Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.85 Safari/537.36")
				.method(Method.GET)
				.ignoreContentType(paramBean.isIgnoreContentType())
				.execute();
			html = new String(response.bodyAsBytes(),"gb2312");
		} catch (IOException e) {
			e.printStackTrace();
			throw new Exception("--> 采集失败",e);
		}
		
		return html;
	}
	
	/**
	 *内部类实例单例对象
	 * @author lx
	 */
	 private static class Holder {
//		  static HttpConnectionManager instance = new HttpConnectionManager();  
	 }
	 
	 /**
	  * 使用内部类获取实例对象
	  * @return
	  */
//	 public static HttpConnectionManager getInstanceWithInnerClass() {  
//		  // 
//		  return Holder.instance;  
//	}  
	 
	 /**
	  * 测试方法
	  * @param args
	  */
	 public static void main(String[] args) {
//		System.setProperty("http.proxyHost", "127.0.0.1");
//		System.setProperty("http.proxyPort", "8082");
		 
		 //1. http://fangjia.fang.com/pinggu/ajax/searchtransfer.aspx?projname=%C1%FA%BD%F5%D4%B7%B6%AB&boolnewsearch=True&strnewcode= 
		 ParametersBean paramBean = new ParametersBean();
		 paramBean.setUrl("http://fangjia.fang.com/");
	
		 try {
			Map<String,String> submitData = new HashMap<String,String>();
			submitData.put("projname", "龙锦苑东四区");
			submitData.put("boolnewsearch", "True");
			submitData.put("strnewcode", "");
			
			String escapeName = StrUtils.escape("龙锦苑东四区");
			System.out.println(escapeName);
			StringBuilder sbuilder = new StringBuilder();
			sbuilder.append("http://fangjia.fang.com/pinggu/ajax/searchtransfer.aspx?projname=");
			sbuilder.append(escapeName);
			sbuilder.append("&boolnewsearch=True&strnewcode=");
			
			Connection.Response response;
			String html = null;
			response = Jsoup
					.connect(sbuilder.toString())
			.header("User-Agent","Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.85 Safari/537.36")
			.method(Method.GET)
			.execute();
			
			System.out.println(response.url().toString());
			//http://fangjia.fang.com/PingGu/Chart/ChartRent.aspx?datatype=proj&width=850&height=350&KeyWord=1010660823
			System.out.println("statusCode2:"+response.statusCode());
			String html2 = new String(response.bodyAsBytes(),"gb2312");
			
			Document doc = Jsoup.parse(html2);
			
			response = Jsoup
					.connect("http://fangjia.fang.com/PingGu/Chart/ChartRent.aspx?datatype=proj&width=850&height=350&KeyWord=1010660823")
			.header("User-Agent","Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.85 Safari/537.36")
			.method(Method.GET)
			.execute();
			
			System.out.println(response.url().toString());
			//http://fangjia.fang.com/PingGu/Chart/ChartRent.aspx?datatype=proj&width=850&height=350&KeyWord=1010660823
			System.out.println("statusCode2:"+response.statusCode());
			
			String html3 = new String(response.bodyAsBytes(),"gb2312");

			response = Jsoup
					.connect("http://fangjia.fang.com/pinggu/ajax/chartajax.aspx?dataType=city")
			.header("User-Agent","Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.85 Safari/537.36")
			.method(Method.GET)
			.execute();
			
			System.out.println("statusCode2:"+response.statusCode());
			
			String html4 = new String(response.bodyAsBytes(),"gb2312");
			
			Element ele = doc.select("div.moreinfo.clearfix").first();
			
			String firstUrl = ele.select("a[href]").first().attr("href");
			
			System.out.println("将要访问的url:"+firstUrl);
			
			saveFile(html2,"xiaoqu.html");
			
//			System.out.println(html2);
			
			//暂停一下
			Thread.sleep(1000);
			
			response = Jsoup
					.connect(firstUrl)
//					.data(submitData)
					.header("User-Agent",
						"Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.85 Safari/537.36")
					.method(Method.GET)
//					.cookie("city", "www")
					.ignoreContentType(true)
					.execute();
//					
			System.out.println("statusCode3:"+response.statusCode());
			html = new String(response.bodyAsBytes(),"gb2312");

			Document doc2 = Jsoup.parse(html);
			
			Element plptinfoDiv = doc2.select("div.floatr.plptinfo_txt").first();
			
			String xiangqing = plptinfoDiv.select("a.more").first().attr("href");
			
			System.out.println("详情url:"+xiangqing);
			
			saveFile(html,"xiaoquMain.html");
			
			//暂停一下
			Thread.sleep(1000);
			
			response = Jsoup
					.connect(xiangqing)
//					.data(submitData)
					.header("User-Agent",
						"Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.85 Safari/537.36")
					.method(Method.GET)
//					.cookie("city", "www")
					.ignoreContentType(true)
					.execute();
//					
			System.out.println("statusCode3:"+response.statusCode());
			html = new String(response.bodyAsBytes(),"gb2312");
			
			Document xiangqingDoc = Jsoup.parse(html);
			//获取详情目录
			Element menuboxDl = xiangqingDoc.select("dl.infomenu").first();
			menuboxDl.children();
			
			Elements lboxEle = xiangqingDoc.select("dl.lbox");
			
			if(lboxEle != null){
				for(Iterator<Element> iter = lboxEle.iterator();iter.hasNext();){
					Element tempEle = iter.next();
					Elements eles = tempEle.children();
					for(int i =0 ;i < eles.size();i++){
						Element item = eles.get(i);
						String key = item.children().text();
						String value = item.ownText();
						System.out.println(key + "  "+ value);
					}
					
				}
			}
			
			saveFile(html,"xiaoquDetail.html");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void saveFile(String html2,String fileName) throws FileNotFoundException,
			IOException, UnsupportedEncodingException {
		File saveFile = new File("d:/temp/html/"+fileName);
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(saveFile));
		bos.write(html2.getBytes("gb2312"));
		bos.flush();
		bos.close();
	}
}
