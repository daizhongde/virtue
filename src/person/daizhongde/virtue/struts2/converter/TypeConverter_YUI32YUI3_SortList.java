package person.daizhongde.virtue.struts2.converter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * YUI3 sortBy list, struts2 type converter
 * <p>data format:
 * <br>String[ { LEVEL1:'desc'  }, { LEAF:'asc'} ] 
 * <br>--> List [ { LEVEL1:'desc'  }, { LEAF:'asc'} ]
 * <p>These who use converter is not contains order parameter
 * @author dzd
 * @date 2013/9/30
 *
 */
public class TypeConverter_YUI32YUI3_SortList{

	/**
	 * 如果request中没有相应的参数，这个方法就不会执行
	 */
	public Object convertFromString(Map context, String[] values, Class toClass) {
		List sortBy = new ArrayList();
		
		String[] sl = values[0].replaceAll("[\\[\\]]", "").split(",");
		for(int i=0, j = sl.length; i<j; i++){
			String[] sm = sl[i].replaceAll("[{}\"]", "").split(":");
			if( sm.length == 2 ){
				Map map = new HashMap();
				map.put( sm[0], sm[1] );
				sortBy.add(map);
			}
		}
		return sortBy;
	}

	public String convertToString(Map context, Object o) {
		if (o instanceof ArrayList )
		{
			List sortBy = (ArrayList)o;
			return sortBy.toString();
		}
		else
		{
			return "";
		}
	}

}
