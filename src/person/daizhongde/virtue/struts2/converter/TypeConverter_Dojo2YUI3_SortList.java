package person.daizhongde.virtue.struts2.converter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * YUI3 sort list struts2 type converter
 * <p>data format:
 * <br>-LEVEL1,LEAF --> [ { LEVEL1:'desc'  }, { LEAF:'asc'} ]
 * <p>These who use converter is not contains order parameter
 * @author dzd
 * @date 2013/9/30
 *
 */
public class TypeConverter_Dojo2YUI3_SortList {

	
	/**
	 * 如果request中没有相应的参数，这个方法就不会执行
	 */
	public Object convertFromString(Map context, String[] values, Class toClass) {
		List sort = new ArrayList();
		String[] sortValues = values[0].split(",");

		for(int i = 0, j = sortValues.length; i < j; i ++ ){
			Map map = new HashMap();
			
			if( sortValues[i].indexOf("-") == -1 ){
				map.put( sortValues[i].replaceAll("[-+]", ""), "asc" );
			}else{
				map.put( sortValues[i].replaceAll("[-+]", ""), "desc" );
			}
			sort.add(map);
		}
		
		return sort;

	}

	public String convertToString(Map context, Object o) {
		if (o instanceof ArrayList )
		{
			List sort = (ArrayList)o;
			Map map = null;
			String result = "";
			String attribute;
			String order;
//			boolean desc;
			
			for (int i = 0, j = sort.size(); i < j; i ++ )
			{
				map.clear();
				map =  (Map)sort.get(i);
				attribute = (String)map.keySet().iterator().next();
				if( map.get( attribute ) == null ){
					order = "asc";
//					desc = false;
				}else{
					order = (String)map.get( attribute );
//					desc = String.valueOf( map.get( attribute ) ).trim().equalsIgnoreCase("desc");
				}
				if( i != j-1 ){
//					result += ( desc ? "-" : "" ) + attribute + ",";
					result += ( order.trim().equalsIgnoreCase("desc") ? "-" : "" ) + attribute + ",";
				}else{
//					result += ( desc ? "-" : "" ) + attribute;
					result += ( order.trim().equalsIgnoreCase("desc") ? "-" : "" ) + attribute + ",";
				}
			}
			return result;
		}
		else
		{
			return "";
		}
	}

}
