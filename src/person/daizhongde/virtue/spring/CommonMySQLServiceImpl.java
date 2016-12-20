package person.daizhongde.virtue.spring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import person.daizhongde.virtue.dao.SpringHibernateDao;
import person.daizhongde.virtue.util.List2Map;
import net.sf.json.JSONObject;

public class CommonMySQLServiceImpl implements CommonService {
	
	private SpringHibernateDao virtueDAO;

	private static String TableNames;
	private static Map TableNames_Map = new HashMap();
	
	public void init(){
//		System.out.println("init CommonService..");
		Map map = new HashMap(2);
		long total = 0;
		List rows = new ArrayList();
				
		total = Long.valueOf(
				virtueDAO.sqlQueryfindaValue(
//				"select count(*) from all_tables where OWNER='CPAB'"
				"SELECT count(*) FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'tool'"
			).toString()
		);
		rows = virtueDAO.sqlQuerylistAll(
//			"select TABLE_NAME from all_tables where OWNER='CPAB'"
//				"select a.TABLE_NAME \"tname\",b.COMMENTS \"comments\"" +
//				"  from all_tables a, all_tab_comments b " +
//				" where a.OWNER='CPAB' and a.OWNER=b.OWNER and a.TABLE_NAME=b.TABLE_NAME(+) and b.TABLE_TYPE='TABLE' and b.TABLE_NAME not like 'BIN$%'"
				
				"SELECT table_name,table_comment FROM INFORMATION_SCHEMA.TABLES "+
				"WHERE TABLE_SCHEMA = 'tool' "
		);
		
		map.put("total", total );
		map.put("rows", rows );
		
		TableNames = JSONObject.fromObject(map).toString();
		TableNames_Map = List2Map.toMap(rows,"tname","comments");
		
//		System.out.println("TableNames:"+TableNames);
	}

	public String getTableNames() {
		return TableNames;
	}

	public Map getTableNames_Map() {
		return TableNames_Map;
	}

	public SpringHibernateDao getVirtueDAO() {
		return virtueDAO;
	}

	public void setVirtueDAO(SpringHibernateDao virtueDAO) {
		this.virtueDAO = virtueDAO;
	}

}
