package com.szl.web.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Method;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.szl.common.utils.StrUtils;

/**
 * 房天下网站抓取小区售价变化趋势
 * @author lx
 * 非北京城市小区获取小区编码查询 列表
 * 1.
 * http://fangjia.fang.com/pinggu/ajax/suggestion.aspx?omitzero=true&q=%u534E%u666F%u65B0%u57CE&city=%u5E7F%u5DDE&0.2336572476197034 
 * 
 */
public class FangTxHousePriceCrawlerHandler {

	private static final String rootUrl = "http://fangjia.fang.com/";
	
	public static void main(String[] args) throws Exception {
		
		String escapeName = StrUtils.escape("华景新城");
		System.out.println(escapeName);
		StringBuilder sbuilder = new StringBuilder();
//		sbuilder.append("http://fangjia.fang.com/pinggu/ajax/searchtransfer.aspx?projname=");
//		sbuilder.append(escapeName);
//		sbuilder.append("&boolnewsearch=True&strnewcode=");
		
		sbuilder.append("http://fangjia.fang.com/pinggu/ajax/searchtransfer.aspx?projname=");
		sbuilder.append(escapeName);
		sbuilder.append("&boolnewsearch=True&strnewcode=1210053737_1");
		
		Connection.Response response;
		response = getRedrictUrl("http://fangjia.fang.com/pinggu/ajax/suggestion.aspx?omitzero=true&q="+StrUtils.escape("华景新城")+"&city="
						+StrUtils.escape("广州")+"&0.2336572476197034","广州","gz","");
		
		System.out.println(response.url().toString());
		System.out.println("statusCode2:"+response.statusCode());
		String html1 = new String(response.bodyAsBytes(),"gb2312");
		Document doc1 =  Jsoup.parse(html1);
		String cityCode = doc1.getElementsByTag("id").first().text();
		String district = doc1.getElementsByTag("district").first().text();
		
		System.out.println("cityCode:"+cityCode +"-district:"+district);
		
		response = getRedrictUrl("http://fangjia.fang.com/pinggu/ajax/searchtransfer.aspx?projname="
									+StrUtils.escape("华景新城")+"&boolnewsearch=True&strnewcode="
									+cityCode+"_1"
									,"广州","gz","");
		
		System.out.println(response.url().toString());
		System.out.println("statusCode2:"+response.statusCode());
		String html2 = new String(response.bodyAsBytes(),"gb2312");
		
		System.out.println("result:"+html2);
		
		Document doc2 = Jsoup.parse(html2);
		
		String cityConNameUrl = doc2.getElementsByTag("a").attr("href");
		
		cityConNameUrl = rootUrl + cityConNameUrl;
		
		System.out.println(cityConNameUrl);
		
		//获取指定城市的售价曲线图
		response = getRedrictUrl("http://fangjia.fang.com/PingGu/Chart/"
				+ "ChartRent.aspx?datatype=proj&width=850&height=350&KeyWord="+cityCode,"广州","gz",cityConNameUrl); 
		
		System.out.println(response.url().toString());
		//http://fangjia.fang.com/PingGu/Chart/ChartRent.aspx?datatype=proj&width=850&height=350&KeyWord=1010660823
		System.out.println("statusCode2:"+response.statusCode());
		System.out.println("url:"+response.url().toString());
		String html3 = new String(response.bodyAsBytes(),"gb2312");
//		System.out.println("result:"+html3);
		
		// 获取指定小区归属城市的售价曲线图数据
		response = getRedrictUrl("http://fangjia.fang.com/pinggu/ajax/chartajax.aspx?dataType=city","广州","gz",cityConNameUrl); 
		
		System.out.println("statusCode2:"+response.statusCode());
		System.out.println("url:"+response.url().toString());
		String html4 = new String(response.bodyAsBytes(),"gb2312");
		System.out.println("result:"+html4);
		
		//获取指定小区周边售价曲线图数据
		response = getRedrictUrl("http://fangjia.fang.com/pinggu/ajax/chartajax.aspx?dataType=com&KeyWord="
									+StrUtils.escape("华景新城")+"&district="+StrUtils.escape(district),"广州","gz",cityConNameUrl);
		System.out.println("statusCode2:"+response.statusCode());
		System.out.println("url:"+response.url().toString());
		String html5 = new String(response.bodyAsBytes(),"gb2312");
		System.out.println("result:"+html5);
		
		//本小区的售价
		response = getResponseByUrl("http://fangjia.fang.com/pinggu/ajax/chartajax.aspx?dataType=proj&KeyWord="+cityCode);
		System.out.println("statusCode2:"+response.statusCode());
		System.out.println("url:"+response.url().toString());
		String html6 = new String(response.bodyAsBytes(),"gb2312");
		
		System.out.println("result:"+html6);
	}

	/*
	 * 获取重定向url地址
	 */
	private static Connection.Response getRedrictUrl(String url,String sortdb,String city,String referer) throws IOException {
		Connection.Response response;
		response = Jsoup
				.connect(url).followRedirects(false)
				.cookie("pinggucitysort", StrUtils.escape(sortdb))
				.cookie("pinggucitysortdb",city)
				.referrer(referer)
		.header("User-Agent","Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.85 Safari/537.36")
		.method(Method.GET)
		.execute();
		return response;
	}
	
	private static Connection.Response getResponseByUrl(String url) throws IOException {
		Connection.Response response;
		response = Jsoup
				.connect(url)
		.header("User-Agent","Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.85 Safari/537.36")
		.method(Method.GET)
		.execute();
		return response;
	}
}
