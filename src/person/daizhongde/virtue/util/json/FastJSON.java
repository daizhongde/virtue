package person.daizhongde.virtue.util.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;

/**
 * 	Fastjson API入口类是com.alibaba.fastjson.JSON，常用的序列化操作都可以在JSON类上的静态方法直接完成。
	public static final Object parse(String text); // 把JSON文本parse为JSONObject或者JSONArray 
	public static final JSONObject parseObject(String text)； // 把JSON文本parse成JSONObject    
	public static final <T> T parseObject(String text, Class<T> clazz); // 把JSON文本parse为JavaBean 
	public static final JSONArray parseArray(String text); // 把JSON文本parse成JSONArray 
	public static final <T> List<T> parseArray(String text, Class<T> clazz); //把JSON文本parse成JavaBean集合 
	public static final String toJSONString(Object object); // 将JavaBean序列化为JSON文本 
	public static final String toJSONString(Object object, boolean prettyFormat); // 将JavaBean序列化为带格式的JSON文本 
	public static final Object toJSON(Object javaObject); 将JavaBean转换为JSONObject或者JSONArray。
	
 * @author daizd
 *
 */
public class FastJSON {
	/**
	 * 使用泛型方法，把json字符串转换为相应的JavaBean对象。
	 * (1)转换为普通JavaBean：readValue(json,Student.class)
	 * (2)转换为List,如List<Student>,将第二个参数传递为Student
	 * [].class.然后使用Arrays.asList();方法把得到的数组转换为特定类型的List
	 * 
	 * @param jsonStr
	 * @param valueType
	 * @return
	 */
	public static <T> T readValue(String jsonStr, Class<T> clazz) {
		return JSON.parseObject(jsonStr, clazz);
	}
	
	/**
	 * json数组转List
	 * @param jsonStr
	 * @param valueTypeRef
	 * @return
	 */
	public static <T> T readValue(String jsonStr, TypeReference<T> valueTypeRef){
		Feature[] ob = new Feature[]{Feature.IgnoreNotMatch, Feature.AllowSingleQuotes};  
		return JSON.parseObject(jsonStr, valueTypeRef, ob );
	}

	/**
	 * 把JavaBean转换为json字符串
	 * 
	 * @param object
	 * @return
	 */
	public static String toJSon(Object object) {
		return JSON.toJSONString(object);
	}
}
