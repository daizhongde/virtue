package person.daizhongde.virtue.util.collection;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class MapUtils {

	/**
	 * 
	 * @param list
	 * @param key
	 * @param value
	 * @return
	 */
	public static HashMap Map2HashMap(Map map){
		HashMap hashMap = new HashMap(map.size()+ map.size()/2);
		
		Iterator it = map.keySet().iterator();
		
		while(it.hasNext()){
			Object k = it.next();
			hashMap.put(k, map.get(k) );
		}
		return hashMap;
	}
	/**
	 * map reverse
	 * @param map
	 * @return
	 */
	public static Map reverse(Map map){
		Map map2 = new HashMap(map.size()+ map.size()/2);

		Iterator it = map.keySet().iterator();
		while(it.hasNext()){
			Object k = it.next();
			map2.put(map.get(k), k  );
		}
		return map2;
	}
	
	public static void main(String args[]){
		String s="{'tname1':'TB1', 'tname2':'TB2'}";
		
		JSONObject ja = JSONObject.fromObject(s);  
		
		HashMap hashMap = new HashMap();
		
		MapUtils mu = new MapUtils();
		hashMap = mu.Map2HashMap(ja);
		
		System.out.println("map.size():"+ja.size());
		System.out.println("map:"+JSONObject.fromObject(ja).toString() );
		System.out.println("HashMap:"+JSONObject.fromObject(hashMap).toString());
		
		
	}
	
}
