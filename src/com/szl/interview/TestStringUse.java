package com.szl.interview;

import java.util.HashMap;
import java.util.Map;

import examples.chargen;


/**
 * 记录考验操作string 字符传相关方法
 * @author shangzhiliang
 *
 */
public class TestStringUse {

	private static StringBuffer sbf = new StringBuffer();
	/**
	 * 题目：
	 * 给定串带空格的字符串，输出倒序后的字符串，不能使用split。
	 * 例如：i like the apple
	 *  输出 apple the like i
	 *  思路：使用递归循环截取字符串，判定条件是输入的字符串中不包括空格则是最后一次截取
	 * @return
	 */
	public static void getReverseString(String inputStr){
		if(inputStr.indexOf(" ") > -1){
			
			sbf.append(inputStr.substring(inputStr.lastIndexOf(" ")+1)+" ");
			
			getReverseString(inputStr.substring(0, inputStr.lastIndexOf(" ")));
		} else {
			sbf.append(inputStr.trim());
		} 
		
	}
	
	/**
	 * 需求描述：
	 *    给定一个5位整数，得道其大写的表示形式。
	 *    如：35462＝叁万伍仟肆佰陆拾贰
	 *   思路：
	 *   1. 初始化map存储数字字符与大写的对应表。
	 *       map.put("0","零");
	 *       。。。
	 *   2. 再初始化一个mapc存储数字所在位对应的单位值。
	 *    由于从左往右遍历字符的话无法判定（或判定较难）是多少位上的值。要从个位开始遍历依次到最大位。
	 *      map.put("4","万");  
	 *   3. 将字符串倒序以获取数字所在位的索引。
	 *   4.根据索引所在位得到位的大写值并拼接数字大写。
	 *   5. 排除两种情况拼接
	 *      a. 位数是0
	 *      b. 最有一位不加单位
	 */
	public static void getBigNumber(int inputNum){
		StringBuffer retBuffer = new StringBuffer();
		Map<String, String> cacheBigDataMap = new HashMap<String, String>();
		cacheBigDataMap.put("0", "零");
		cacheBigDataMap.put("1", "壹");
		cacheBigDataMap.put("2", "贰");
		cacheBigDataMap.put("3", "叁");
		cacheBigDataMap.put("4", "肆");
		cacheBigDataMap.put("5", "伍");
		cacheBigDataMap.put("6", "陆");
		cacheBigDataMap.put("7", "柒");
		cacheBigDataMap.put("8", "捌");
		cacheBigDataMap.put("9", "玖");
		
		Map<String, String> cacheUnitMap = new HashMap<String, String>();
		cacheUnitMap.put("4", "万");
		cacheUnitMap.put("3", "仟");
		cacheUnitMap.put("2", "佰");
		cacheUnitMap.put("1", "拾");
		
		//获取倒序字符串
		String invertedStr = "";
		for(int i = String.valueOf(inputNum).toCharArray().length -1 ; i > -1 ;i -- ){
			invertedStr +=  String.valueOf(inputNum).toCharArray()[i];
		}
		
		for(int i = 0 ; i < String.valueOf(invertedStr).toCharArray().length;i++){
			retBuffer.append(cacheBigDataMap.get(String.valueOf(inputNum).toCharArray()[i]+"") );
			if(i != invertedStr.length() -1 
					&& !( String.valueOf(inputNum).toCharArray()[i]+"").equals("0")){
				retBuffer.append(cacheUnitMap.get((String.valueOf(inputNum).length()- i -1+"")));
			}
		}
		
		System.out.println(retBuffer.toString());
	}
	
	/**
	 * 第一个人10岁，第二个人比第一个人大2岁， 依次类推。问第8个人多少岁，用递归的方式编写代码。
	 * 
	 * @param person
	 */
	
	public static int getPersonAge(int person){
		if(person == 1){
			return 10;
			
		} else {
			return getPersonAge(person-1) +2;
		}
	}
	
	public static void main(String[] args) {
//		getReverseString("i like you very much");
//		System.out.println(sbf.toString());
		
//		getBigNumber(25064);
		
		
		getPersonAge(8);
		
	}
}
