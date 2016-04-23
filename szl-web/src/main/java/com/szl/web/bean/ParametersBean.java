package com.szl.web.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * 抓取网站所需的参数配置
 * @author lx
 *
 */
public class ParametersBean {

	private String url;
	private boolean ignoreContentType;
	private Map<String,String> cookiesMap = new HashMap<String,String>();
	
	public boolean isIgnoreContentType() {
		return ignoreContentType;
	}
	public void setIgnoreContentType(boolean ignoreContentType) {
		this.ignoreContentType = ignoreContentType;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Map<String, String> getCookiesMap() {
		return cookiesMap;
	}
	public void setCookiesMap(Map<String, String> cookiesMap) {
		this.cookiesMap = cookiesMap;
	}
	
	
}
