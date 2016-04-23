package com.szl.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 使用正则表达式获取给定正则的目标字符串
 * @author lx
 *
 */
public class DoRegex {

	/**
	 * 从给定的源字符串文本中搜索指定正则表达式匹配的字符串，并获取列表指定索引下的值
	 * @param srcString
	 * @param regex
	 * @param pos
	 * @return
	 */
	public static String getFirstString(String srcString,String regex, int pos){
		
		Pattern p = Pattern.compile(regex);
		Matcher matcher = p.matcher(srcString);
		String result = null;
		
		if(matcher.find()){
			result = matcher.group(pos);
		}
		return result;
	}
	
	public static void main(String[] args) {
		Pattern pattern = Pattern.compile("href=\"(.+?)\"");
		Matcher matcher = pattern.matcher("<a href=\"index.html\">主页</a>");
		if(matcher.find()){
		  System.out.println(matcher.group(1));
		}
	}
}
