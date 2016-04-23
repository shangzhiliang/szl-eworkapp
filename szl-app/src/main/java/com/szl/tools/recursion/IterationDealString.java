package com.szl.tools.recursion;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 本demo实现迭代方式处理统计一串字符串内
 * 指定字符的个数
 * 比如给定字符串：AAAA pay a monment wating...
 * 查出上述串中A的个数
 * @author lx
 *
 */
public class IterationDealString {
	
	  private static final Logger LOGGER = LoggerFactory.getLogger(IterationDealString.class);
	  
	  public int countStrNumber(String str){
		  int count = 0;
		  //首先判断传入字符是否为空
		  if(StringUtils.isEmpty(str)){
			  return 0;
		  }
		  
		  for(int i = 0 ; i < str.length() ; i++ ){
			 char currentChar =  str.charAt(i);
			 if(currentChar == 'A'){
				 count ++;
			 }
			 
//			 if(str.substring(i, i+1).equals("A")){
//				 count ++;
//			 }
		  }
		  
		  return count;
	  }
	  
	  public static void main(String[] args) {
		  LOGGER.debug("A length:{}",new IterationDealString().countStrNumber("AAAAA bbAbb cccddde"));
	  }
}
