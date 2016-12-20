package person.daizhongde.virtue.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import person.daizhongde.virtue.testutil.Printer;
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
		
		Iterator it = map.keySet().iterator();
		HashMap hashMap = new HashMap(map.size()+ map.size()/2);
		
		while(it.hasNext()){
			Object k = it.next();
			hashMap.put(k, map.get(k) );
		}
		return hashMap;
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
