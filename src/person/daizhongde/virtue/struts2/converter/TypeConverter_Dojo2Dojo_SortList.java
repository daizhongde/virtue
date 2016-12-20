package person.daizhongde.virtue.struts2.converter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * dojo sort list struts2 type converter
 * <p>data format:
 * <br>-LEVEL1,LEAF --> [{attribute: "LEVEL1", descending: true},{attribute: "LEAF", descending: false}]
 *<p>recommend: YUI3 style
 * <br>[ { LEVEL1:'desc'  }, { LEAF:'asc'} ]
 * @author dzd
 * @date 2013/9/24
 *
 */
public class TypeConverter_Dojo2Dojo_SortList {

	public Object convertFromString(Map context, String[] values, Class toClass) {
		/**
		 * values.length = 2
		 * 
		<tr>
            <td>请输入用户1信息：<input type="text" name="user"/></td>
        </tr>
        <tr>
            <td>请输入用户2信息：<input type="text" name="user"/></td>
        </tr>
        if (values.length >= 1){
			List list = new ArrayList();
			for (int i = 0; i < values.length ; i++ )
			{
				
			}
			return list;	
		}else{
			String[] strArray={};
			return strArray;
		}
		 * 
		 */
		List sort = new ArrayList();
		String[] sortValues = values[0].split(",");
		
		for(int i = 0, j = sortValues.length; i < j; i ++ ){
			Map map = new HashMap();
			map.put( "attribute", sortValues[i].replaceAll("[-+]", "") );
//			String str="-LEVEL1,LEAF";//"-LEVEL1,+LEAF"
//			String s = str.replaceAll("[-+]", "");
//			System.out.println("str:"+str+", s:"+s );
			
			if( sortValues[i].indexOf("-") == -1 ){
				map.put( "descending", Boolean.FALSE );
			}else{
				map.put( "descending", Boolean.TRUE );
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
			boolean desc;
			
			for (int i = 0, j = sort.size(); i < j; i ++ )
			{
				map.clear();
				map =  (Map)sort.get(i);
				String attr ="";
				if( map.get("descending") == null ){
					desc = false;
				}else{
					desc = ((Boolean)map.get("descending")).booleanValue();
				}
				if( i != j-1 ){
					result += ( desc ? "-" : "" ) + map.get("attribute") + ",";
				}else{
					result += ( desc ? "-" : "" ) + map.get("attribute");
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
