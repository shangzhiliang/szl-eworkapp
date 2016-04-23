package com.szl.common.utils;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Method;

public class JsoupUtils {

	/**
	 * 根据指定url获取返回html源码
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public static String getHtmlByUrl(String url,String charsetName) throws IOException{
		//重试三次
		int retry = 0;
		
		while(retry < 3){
			try {
				 Connection.Response response = Jsoup
							.connect(url)
							.header("User-Agent",
								"Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.85 Safari/537.36")
							.method(Method.GET)
							.execute();
				 if(response.statusCode() == 200){
					return new String(response.bodyAsBytes(),charsetName);
				 } else {
					 break;
				 }
			} catch (Exception e){
				retry = retry + 1;
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
				}
			}
		}
		return null;
	}
}
