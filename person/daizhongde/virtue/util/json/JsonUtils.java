/**
 * Project Name:oms-web
 * File Name:JsonUtils.java
 * Package Name:com.snto.oms.util
 * Date:2014-9-26上午9:55:29
 *
*/

package person.daizhongde.virtue.util.json;

import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

import org.apache.commons.lang3.StringUtils;

/**
 * ClassName:JsonUtils <br/>
 * Function: Json 操作工具类. <br/>
 * Date:     2014-9-26 上午9:55:29 <br/>
 * @author   d26297
 * @since    JDK 1.7
 */
public class JsonUtils {
	
	 /**
	 * jsonStr2List:(Json 字符串转List). <br/>
	 * @author z144746
	 * @param str
	 * @param clazz
	 * @since JDK 1.7
	 */
	@SuppressWarnings("unchecked")
	public static List<?> jsonStr2List(String str,Class<?> clazz){
		 	if(StringUtils.isEmpty(str)){
		 		return null;
		 	}
		 	List<?> objList = null;
		 	try{
		 		 JSONArray jarr=JSONArray.fromObject(str);
		 		objList = (List<Object>)JSONArray.toCollection(jarr,clazz);
		 	}catch(Exception e){
		 		e.printStackTrace();
		 	}
	        return objList;
	}
	/**
	 * jsonStr2List:(Json 字符串转实体). <br/>
	 * @author d26297
	 * @param str
	 * @param clazz
	 * @since JDK 1.7
	 */
	public static Object jsonStr2Entity(String str,Class<?> clazz){
		 	if(StringUtils.isEmpty(str)){
		 		return null;
		 	}
		 	Object obj = null;
		 	try{
		 		JSONUtils.getMorpherRegistry().registerMorpher(
		                new DateMorpherEx(new String[] { "yyyy-MM-dd HH:mm:ss",
		                        "yyyy-MM-dd", "yyyy-MM-dd't'HH:mm:ss" }, (Date)null));
		 		obj = JSONObject.toBean( JSONObject.fromObject(str), clazz);
		 	}catch(Exception e){
		 		e.printStackTrace();
		 	}
	        return obj;
	}
}

