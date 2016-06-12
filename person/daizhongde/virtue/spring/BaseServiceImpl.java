package person.daizhongde.virtue.spring;

import java.io.File;
import java.util.List;
import java.util.Map;

import person.daizhongde.virtue.assemble.hql.HQLAssembleQ;
import person.daizhongde.virtue.assemble.sql.SQLAssembleQ;

/**
 * Not use, Only for display
 * @author dzd
 *
 */
public class BaseServiceImpl implements BaseService {

	public long getTotal(SQLAssembleQ sqlA) {
		// TODO Auto-generated method stub
		return 0;
	}

	public List getRowsInMap(SQLAssembleQ sqlA) {
		// TODO Auto-generated method stub
		return null;
	}

	public List getRowsInMap(SQLAssembleQ sqlA, int offset, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	public long getTotal(HQLAssembleQ hqlA) {
		// TODO Auto-generated method stub
		return 0;
	}

	public List getRowsInMap(HQLAssembleQ hqlA) {
		// TODO Auto-generated method stub
		return null;
	}

	public List getRowsInMap(HQLAssembleQ sqlA, int offset, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}
	public List getRowsInArray(SQLAssembleQ sqlA) {
		// TODO Auto-generated method stub
		return null;
	}

	public List getRowsInArray(SQLAssembleQ sqlA, int offset,
			int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	public int add(String jdata) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int add(Map map) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public int addRetId(String jdata) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public int addWithId(String jdata) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int addWithIdRetId(String jdata) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public void addBySavePOJO( String jdata ){

	}
	public void addBySavePOJO2( Object pojo ){
		
	}
	
	public int modify(String jdata) {
		// TODO Auto-generated method stub
		return 0;
	}

	public Map browse(String jdata) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map browseById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map browseById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object[] browseArray(String jdata) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object browsePOJO(String jdata) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object browsePOJOById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	public Object browsePOJOById(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	public int delete(String jdata) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int deleteNP(String jdata) {
		// TODO Auto-generated method stub
		return 0;
	}


}
