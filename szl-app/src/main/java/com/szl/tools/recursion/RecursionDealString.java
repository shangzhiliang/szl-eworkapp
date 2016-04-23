package com.szl.tools.recursion;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 本demo实现用递归方式处理给定字符串内容指定字符的统计个数
 * @author lx
 *
 */
public class RecursionDealString {

	private static final Logger LOGGER = LoggerFactory.getLogger(RecursionDealString.class);
	
	public int countANumber(String str){
		if (StringUtils.isEmpty(str)) {
			return 0;
		}
		
		int count = 0;
		
		if(str.substring(0,1).equals("A")){
			count = 1;
		}
		
		return count + countANumber(str.substring(1));
	}
	
	public int countA(String str){
		if(StringUtils.isEmpty(str)){
			return 0;
		}
		
		return countA(str,0);
	}
	
	public int countA(String str, int count){
		if(StringUtils.isEmpty(str)){
			return count;
		}
		
		if(str.substring(0, 1).equals("A")){
			count = count + 1;
		}
		
		return countA(str.substring(1),count);
	}
	
	public static void main(String[] args) {
		RecursionDealString obj = new RecursionDealString();
		int count = obj.countA("AAbbbcc");
		System.out.println(count);
		LOGGER.debug("-->count:{}",count);
	}
}
