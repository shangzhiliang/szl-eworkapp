package com.szl.pinyin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class Covert {
	
	/**
	 * 汉字转拼音，并将结果保存到省或市的map中
	 * @param words 需要转换的字符串
	 * @param provinceOrCity 省或市的Map
	 */
	public static void wordToPinYinSZM(String words, Map<String, String> provinceOrCity) {
		//调用Pinyin4J的方法
		
		//定义拼音输出格式
		HanyuPinyinOutputFormat hof = new HanyuPinyinOutputFormat();
		//大写
		hof.setCaseType(HanyuPinyinCaseType.UPPERCASE);
		//不包含声调
		hof.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		//u用V表示
		hof.setVCharType(HanyuPinyinVCharType.WITH_V);
		
		//由于只能对单字转换，所以需要把字符串转换为字符数组
		char[] characters = words.toCharArray();
		try {
			//StringBuilder存放结果
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < characters.length; i++) {
				//调用汉字转拼音核心方法，大家可以看到返回的是一个数组，因为有的汉字有多音字，所以可能返回多个
				//拼音结果，对此，我只取第一个,pinyinArray[0]，这样做不好的地方就是可能有的简拼不准确，例如
				//“重庆”我得到的是ZQ，zhong qing，这一点有待完善
				String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(characters[i], hof);
				//substring(0, 1)则表示只取首字母
				sb.append(pinyinArray[0].substring(0, 1));
			}
			//放入省或市的Map中
			provinceOrCity.put(words, sb.toString());
		} catch (BadHanyuPinyinOutputFormatCombination e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		//读取事先定义好的省市文本文件，第一行为省/直辖市，第二行为各地级市
		
		System.out.println();
		File provinceFile = new File("source/province.txt");
		
		//定义保存省、市数据的数据结构。从里向外看，Map<String, String>保存的是 “省=>简拼” 这样的数据
		//如“河北=>HB”。List里面的Map<String, String>则保存着“市=>简拼”的数据，如“保定=>BD”，而list集合
		//保存该省下所有市的集合。最外层的Map就是“省=>各市集合”的关联集合，这样省市关系就确定了。
		Map<Map<String, String>, List<Map<String, String>>> provinceCity = new LinkedHashMap<Map<String, String>, List<Map<String, String>>>();
		try {
			//定义输入流，可以使用readline()读取一行
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(provinceFile), "UTF-8"));
			String provinceName = "";
			while ((provinceName = br.readLine()) != null) { //先读第一行，省的名字
				//定义保存“省=>简拼”的Map
				Map<String, String> province = new LinkedHashMap<String, String>(); 
				//定义保存“市=>简拼”Map的List集合，结构为[{保定=>BD}, {沧州=>CZ},……]
				List<Map<String, String>> citiesArray = new ArrayList<Map<String, String>>();
				
				//调用汉字转拼音方法
				wordToPinYinSZM(provinceName.trim(), province);
				//读取第二行，该省下的所有市区
				String cityNames = br.readLine();
				//文件中的每个市区用两个空格格开，所以以两个空格分隔成市区的数组
				String[] cities = cityNames.split("  ");
				for(int j = 0; j < cities.length; j++) {
					//定义保存“市=>简拼”Map
					Map<String, String> city = new LinkedHashMap<String, String>();
					String cityName = cities[j].trim();
					//调用汉字转拼音方法
					wordToPinYinSZM(cityName, city);
					//添加到list集合中
					citiesArray.add(city);
				}
				//添加到“省=>市的集合”的Map中
				provinceCity.put(province, citiesArray);
			}
			//调用转换为sql语句的代码
			convertMapToSql(provinceCity);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 把“省=>市的集合”Map转换为SQL语句
	 * @param provinceCity
	 */
	public static void convertMapToSql(Map<Map<String, String>, List<Map<String, String>>> provinceCity) {
		//获取最外层Map的EntrySet 结构为[{河北=>HB, [{保定=>BD}, {石家庄=>sjz},{……}]},{……}]
		Set<Entry<Map<String, String>, List<Map<String, String>>>> provinceEnties = provinceCity.entrySet();
		//获取迭代器
		Iterator<Entry<Map<String, String>, List<Map<String, String>>>> itPE = provinceEnties.iterator();
		//定义数据库中，省的ID从1000000开始
		long provinceId = 1000000;
		while(itPE.hasNext()) {
			//获取一个Entry键-值对
			Entry<Map<String, String>, List<Map<String, String>>> proviceEntry = itPE.next();
			//Key为省，Value为市的List集合
			Map<String, String> provinces = proviceEntry.getKey();
			
			//获取省份的Entry，结构为[{河北=>HB}, {北京=>BJ}] key为中文，value为简拼
			Set<Entry<String, String>> proEntries = provinces.entrySet();
			Iterator<Entry<String, String>> proIt = proEntries.iterator();
			while(proIt.hasNext()) {
				Entry<String, String> proEntry = proIt.next();
				String provinceName = proEntry.getKey();
				String provincePinYin = proEntry.getValue();
				
				//组合sql语句
				StringBuilder sb = new StringBuilder();
				//定义的表结构为:id, type, name, short，分别为id,类型（1为1级，如省，2为2级，如市）,父ID即市的所属省份, 名字, 简拼
				sb.append("insert into city values ('");
				sb.append(provinceId).append("','").append("1','','").append(provinceName).append("', '").append(provincePinYin).append("');");
				//我没有生成sql文件，而是直接显示在console里面，大家可以直接从里面复制
				System.out.println(sb.toString());
			}
			
			
			//获取当前省下所有市区，迭代方法与上面类似，请注意这里面的循环比较多，请大家注意分辨。
			List<Map<String, String>> cities = proviceEntry.getValue();
			
			Iterator<Map<String, String>> citiesIt = cities.iterator();
			long cityId = provinceId;//市的ID直接从省的ID开始递增，每次递增50
			while (citiesIt.hasNext()) {
				Map<String, String> city = citiesIt.next();
				Set<Entry<String, String>> cityEntries = city.entrySet();
				Iterator<Entry<String, String>> cityIt = cityEntries.iterator();
				cityId += 50;//市的ID，先递增50，避免与省份ID相同
				while(cityIt.hasNext()) {
					Entry<String, String> cityEntry = cityIt.next();
					String cityName = cityEntry.getKey();
					String cityPinYin = cityEntry.getValue();
					
					StringBuilder sb = new StringBuilder();
					sb.append("insert into city values ('");
					sb.append(cityId).append("','").append("2','").append(provinceId).append("','").append(cityName).append("', '").append(cityPinYin).append("');");
					System.out.println(sb.toString());
				}
			}
			//省份ID以10000递增
			provinceId += 10000;
		}
		
	}

}
