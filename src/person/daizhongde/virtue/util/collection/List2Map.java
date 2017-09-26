package person.daizhongde.virtue.util.collection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class List2Map {

	/**
	 * 
	 * @param list
	 * @param key
	 * @param value
	 * @return
	 */
	public static Map toMap(List list,String keyColumn, String valColumn){
		
		Map<String,String> map = new HashMap<String,String>();
		
		for(int i=0,j=list.size(); i<j; i++){
//			Map<String,String> temp = new HashMap();
//			temp = (Map<String,String>)JSONObject.fromObject(list.get(i));
			@SuppressWarnings("unchecked")
			Map<String,String> temp = (Map<String,String> )list.get(i);
			
//			map.put(temp.get("tname"), temp.get("comments"));
			map.put(temp.get( keyColumn ), temp.get( valColumn ));
		}
		return map;
	}
	
	public static void main(String args[]){
		String s="[{'tname':'TB1','comments':'表1'}," +
				"{ tname:'TB2',comments:null}]";
		
		JSONArray ja = JSONArray.fromObject(s);  
		
		List list = JSONArray.toList(ja);
		
		System.out.println("list.size():"+list.size());
		
		Map map = new HashMap();
		
		for(int i=0,j=list.size(); i<j; i++){
			Map temp = new HashMap();
			temp = (Map)JSONObject.fromObject(list.get(i));
			map.put(temp.get("tname"), temp.get("comments"));
		}
		System.out.println("map.size():"+map.size());
		System.out.println("list:"+JSONArray.fromObject(list).toString() );
		System.out.println("map:"+JSONObject.fromObject(map).toString());
	}
}