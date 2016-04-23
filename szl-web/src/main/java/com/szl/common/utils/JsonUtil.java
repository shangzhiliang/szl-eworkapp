package com.szl.common.utils;


import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringEscapeUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonUtil {
	/** 页面传至后台时，json数据在request的参数名称 */
	public final static String JSON_ATTRIBUTE = "json";
	public final static String JSON_ATTRIBUTE1 = "json1";
	public final static String JSON_ATTRIBUTE2 = "json2";
	public final static String JSON_ATTRIBUTE3 = "json3";
	public final static String JSON_ATTRIBUTE4 = "json4";

	/**
	 * 从一个JSON 对象字符格式中得到一个java对象，形如： {"id" : idValue, "name" : nameValue,
	 * "aBean" : {"aBeanId" : aBeanIdValue, ...}}
	 * 
	 * @param object
	 * @param clazz
	 * @return
	 */
	public static Object getDTO(String jsonString, @SuppressWarnings("rawtypes") Class clazz) {
		JSONObject jsonObject = null;
		try {
			setDataFormat2JAVA();
			jsonObject = JSONObject.fromObject(jsonString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSONObject.toBean(jsonObject, clazz);
	}

	/**
	 * 从一个JSON 对象字符格式中得到一个java对象，其中beansList是一类的集合，形如： {"id" : idValue, "name" :
	 * nameValue, "aBean" : {"aBeanId" : aBeanIdValue, ...}, beansList:[{}, {},
	 * ...]}
	 * 
	 * @param jsonString
	 * @param clazz
	 * @param map
	 *            集合属性的类型 (key : 集合属性名, value : 集合属性类型class) eg: ("beansList" :
	 *            Bean.class)
	 * @return
	 */
	public static Object getDTO(String jsonString, Class<?> clazz, Map<?, ?> map) {
		JSONObject jsonObject = null;
		try {
			setDataFormat2JAVA();
			jsonObject = JSONObject.fromObject(jsonString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSONObject.toBean(jsonObject, clazz, map);
	}

	/**
	 * 从一个JSON数组得到一个java对象数组，形如： [{"id" : idValue, "name" : nameValue}, {"id" :
	 * idValue, "name" : nameValue}, ...]
	 * 
	 * @param object
	 * @param clazz
	 * @return
	 */
	public static Object[] getDTOArray(String jsonString, Class<?> clazz) {
		setDataFormat2JAVA();
		JSONArray array = JSONArray.fromObject(jsonString);
		Object[] obj = new Object[array.size()];
		for (int i = 0; i < array.size(); i++) {
			JSONObject jsonObject = array.getJSONObject(i);
			obj[i] = JSONObject.toBean(jsonObject, clazz);
		}
		return obj;
	}

	/**
	 * 从一个JSON数组得到一个java对象数组，形如： [{"id" : idValue, "name" : nameValue}, {"id" :
	 * idValue, "name" : nameValue}, ...]
	 * 
	 * @param object
	 * @param clazz
	 * @param map
	 * @return
	 */
	public static Object[] getDTOArray(String jsonString, Class<?> clazz, Map<?, ?> map) {
		setDataFormat2JAVA();
		JSONArray array = JSONArray.fromObject(jsonString);
		Object[] obj = new Object[array.size()];
		for (int i = 0; i < array.size(); i++) {
			JSONObject jsonObject = array.getJSONObject(i);
			obj[i] = JSONObject.toBean(jsonObject, clazz, map);
		}
		return obj;
	}

	/**
	 * 从一个JSON数组得到一个java对象集合
	 * 
	 * @param object
	 * @param clazz
	 * @return
	 */
	public static List<Object> getDTOList(String jsonString, Class<?> clazz) {
		setDataFormat2JAVA();
		JSONArray array = JSONArray.fromObject(jsonString);
		List<Object> list = new ArrayList<Object>();
		for (Iterator<?> iter = array.iterator(); iter.hasNext();) {
			JSONObject jsonObject = (JSONObject) iter.next();
			list.add(JSONObject.toBean(jsonObject, clazz));
		}
		return list;
	}

	/**
	 * 从一个JSON数组得到一个java对象集合，其中对象中包含有集合属性
	 * 
	 * @param object
	 * @param clazz
	 * @param map
	 *            集合属性的类型
	 * @return
	 */
	public static List<Object> getDTOList(String jsonString, Class<?> clazz, Map<?, ?> map) {
		setDataFormat2JAVA();
		JSONArray array = JSONArray.fromObject(jsonString);
		List<Object> list = new ArrayList<Object>();
		for (Iterator<?> iter = array.iterator(); iter.hasNext();) {
			JSONObject jsonObject = (JSONObject) iter.next();
			list.add(JSONObject.toBean(jsonObject, clazz, map));
		}
		return list;
	}

	/**
	 * 从json HASH表达式中获取一个map，该map支持嵌套功能 形如：{"id" : "johncon", "name" : "小强"}
	 * 注意commons
	 * -collections版本，必须包含org.apache.commons.collections.map.MultiKeyMap
	 * 
	 * @param object
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Map<String, Object> getMapFromJson(String jsonString) {
		setDataFormat2JAVA();
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		Map<String, Object> map = new HashMap<String, Object>();
		for (Iterator iter = jsonObject.keys(); iter.hasNext();) {
			String key = (String) iter.next();
			map.put(key, jsonObject.get(key));
		}
		return map;
	}

	/**
	 * 从json数组中得到相应java数组 json形如：["123", "456"]
	 * 
	 * @param jsonString
	 * @return
	 */
	public static Object[] getObjectArrayFromJson(String jsonString) {
		JSONArray jsonArray = JSONArray.fromObject(jsonString);
		return jsonArray.toArray();
	}

	/**
	 * 把数据对象转换成json字符串 DTO对象形如：{"id" : idValue, "name" : nameValue, ...}
	 * 数组对象形如：[{}, {}, {}, ...] map对象形如：{key1 : {"id" : idValue, "name" :
	 * nameValue, ...}, key2 : {}, ...}
	 * 
	 * @param object
	 * @return
	 * 注意：对于基本类型返回为{}，对于日期类型（Date），返回为其各属性的值。
	 */
	public static String getJSONString(Object object){
		String jsonString = null;		
		try{
			//JsonConfig jsonConfig = new JsonConfig();		
			//jsonConfig.registerJsonValueProcessor(java.util.Date.class,new JsonDateValueProcessor());
			if (object != null) {
				if (object instanceof Collection || object instanceof Object[]) {
					//jsonString = JSONArray.fromObject(object, jsonConfig).toString();
					jsonString = JSONArray.fromObject(object).toString();
				} else {
					//jsonString = JSONObject.fromObject(object, jsonConfig).toString();
					jsonString = JSONObject.fromObject(object).toString();
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return jsonString == null ? "{}" : jsonString;
	}
	
	/**
	 * 生成json对象，通过反射获取属性的值
	 * 属性名为键
	 * @param obj
	 * @return
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws IOException
	 */
	public static JSONObject objectToJson(Object obj){
		JSONObject json = new JSONObject();
		Object value = "";
		if (null != obj) {
			//obj 不是bean对象，且为String时
			if(obj instanceof String){
				value = obj.toString();
				value = StringEscapeUtils.escapeXml(value.toString());//GZDSL1065384339@16900.gd
				json.put("val", value);
				return json;
			}
			Class<? extends Object> cls = obj.getClass();
			Field[] fields = cls.getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				String name = fields[i].getName();
				value = "";
				try {
					Method method = cls.getMethod("get" + name.substring(0, 1).toUpperCase() + name.substring(1), null);
					Object res_ = method.invoke(obj, null);
					if (null == res_){
						value = "";
					}else if (res_ instanceof String) {
						value = res_.toString();
						value = StringEscapeUtils.escapeXml(value.toString());//GZDSL1065384339@16900.gd
					}else if (res_ instanceof Date) {
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						value = sdf.format(res_);
					}else if (res_ instanceof Long || res_ instanceof Integer) {
						value = res_.toString();
					}else if (res_ instanceof Double) {
						value = String.valueOf(((Double)res_).doubleValue());
					}else if(res_ instanceof List){
						List list = (List)res_;
						JSONObject ijson = new JSONObject();
						int size = list.size();
						for (int j = 0; j < size; j++) {
							ijson.put("element" + j, toJson(list.get(j)));
						}
						value = ijson;
					}else{
						value = toJson(res_);
					}
				} catch (NoSuchMethodException e) {
					
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} catch (SecurityException e) {
					e.printStackTrace();
				}
				json.put(name, value);
			}
		}
		return json;
	}

	/**
	 * 将对象封装为json对象。主要用来区分数组和普通对象
	 * @param obj
	 * @return
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws IOException
	 */
	@SuppressWarnings("rawtypes")
	protected static JSONObject toJson(Object obj){
		JSONObject json = new JSONObject();
		if (null != obj) {
			//判断为数组的情况
			if (obj.getClass().isArray()) {
				int len = Array.getLength(obj);
				for (int i = 0; i < len; i++) {
					Object object = Array.get(obj, i);
					JSONObject ijson = objectToJson(object);
					json.put("element" + i, ijson);
				}
			//List对象 包含的是bean
			}else if(obj instanceof List){
				List list = (List)obj;
				int size = list.size();
				for (int i = 0; i < size; i++) {
					json.put("element" + i, toJson(list.get(i)));
				}
			//Map对象 包含的是bean
			}else if(obj instanceof Map){
				Map map = (Map)obj;
				for (Iterator iterator = map.keySet().iterator(); iterator.hasNext();) {
					String key = (String) iterator.next();
					json.put(key, toJson(map.get(key)));
				}
			//普通bean
			}else{	
				json = objectToJson(obj);
			}
		}
		return json;
	}
	private static void setDataFormat2JAVA() {
		// 设定日期转换格式
	//	JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(new String[] { "yyyy-MM-dd","yyyy-MM-dd HH:mm:ss" }));
	}
}	
