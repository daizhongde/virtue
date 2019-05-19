package person.daizhongde.virtue.spring;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;
import person.daizhongde.virtue.assemble.hql.HQLAssembleQ;
import person.daizhongde.virtue.assemble.hql.HQLAssembleR;
import person.daizhongde.virtue.assemble.sql.SQLAssembleC;
import person.daizhongde.virtue.assemble.sql.SQLAssembleD;
import person.daizhongde.virtue.assemble.sql.SQLAssembleQ;
import person.daizhongde.virtue.assemble.sql.SQLAssembleR;
import person.daizhongde.virtue.assemble.sql.SQLAssembleU;
import person.daizhongde.virtue.constant.AbstractConstant;
import person.daizhongde.virtue.constant.INIT;
import person.daizhongde.virtue.constant.Operator;
import person.daizhongde.virtue.dao.SpringHibernateDao;

/**
 * Not use, Only for display
 * @author dzd
 *
 */
public class BaseServiceImpl implements BaseService{

	private SpringHibernateDao baseDao;
	 
   public void setBaseDao(SpringHibernateDao baseDao)
   {
     this.baseDao = baseDao;
   }
   
	public long getTotal(SQLAssembleQ sqlA) {
//		Object o= dataDAO.sqlQueryfindaValueByMap( sqlA.getCountSQL(), sqlA.getMap() );
		return Long.valueOf(
				baseDao.sqlQueryfindaValueByMap( sqlA.getCountSQL(), sqlA.getMap() ).toString()
			).longValue();
	}
	
	public List getRowsInMap(SQLAssembleQ sqlA) {
		return baseDao.sqlQuerylistAllByMap( sqlA.getSQL(), sqlA.getMap() );//use native sql, because of it less data 
	}

	public List getRowsInMap(SQLAssembleQ sqlA, int offset, int pageSize) {
		return baseDao.sqlQueryfindByPageByMap(sqlA.getSQL(), sqlA.getMap(), 
				offset, pageSize);//use native sql, because of it less data 
	}

	public long getTotal(HQLAssembleQ hqlA) {
		// TODO Auto-generated method stub
		return 0;
	}


	public List getRowsInMap(HQLAssembleQ hqlA) {
		// TODO Auto-generated method stub
		return null;
	}


	public List getRowsInMap(HQLAssembleQ hqlA, int offset, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	public List getRowsInArray(SQLAssembleQ sqlA) {
		return baseDao.sqlQuerylistAllRetArrayByMap(sqlA.getSQL(), sqlA.getMap());//use native sql, because of it less data 
	}

	public List getRowsInArray(SQLAssembleQ sqlA, int offset,
			int pageSize) {
		return baseDao.sqlQueryfindRetArrayByPageByMap(sqlA.getSQL(), sqlA.getMap(), 
				offset, pageSize);//use native sql, because of it less data 
	}

	public int add(String jdata) {
//		JSONObject jsonObject = JSONObject.fromObject(jdata);
//		AbstractConstant absConstant = new AuthorityButton();
//		
//		SQLAssembleC sqlA = new SQLAssembleC(
//				INIT.AUTH_schema,
//				absConstant.getTableName(),
//				jsonObject.getJSONObject("data"),
//				absConstant.getColumnTypes(),
//				absConstant.getFront2col()
//				);
//		
//		return baseDao.sqlQueryExeUByMap(sqlA.getSQL(), sqlA.getMap());
		return 0;
	}


	public int addRetId(String jdata) {
//		JSONObject jsonObject = JSONObject.fromObject(jdata);
//		AbstractConstant absConstant = new AuthorityButton();
//
//		SQLAssembleC sqlA = new SQLAssembleC(
//				INIT.AUTH_schema,
//				absConstant.getTableName(),
//				jsonObject.getJSONObject("data"),
//				absConstant.getColumnTypes(),
//				absConstant.getFront2col()
//				);
//		
//		return baseDao.sqlQueryExeUByMap(sqlA.getSQL(), sqlA.getMap());
		return 0;
	}

	public int addWithId( String jdata ){
//		JSONObject jsonObject = JSONObject.fromObject(jdata);
//		AbstractConstant absConstant = new AuthorityButton();
//
//		SQLAssembleC sqlA = new SQLAssembleC(
//				INIT.AUTH_schema,
//				absConstant.getTableName(),
//				jsonObject.getJSONObject("data"),
//				absConstant.getColumnTypes(),
//				absConstant.getFront2col()
//				);
//		
//		return baseDao.sqlQueryExeUByMap(sqlA.getSQL(), sqlA.getMap());
		return 0;
	}

	public int addWithIdRetId(String jdata) {
//		JSONObject jsonObject = JSONObject.fromObject(jdata);
//		AbstractConstant absConstant = new AuthorityButton();
//
//		SQLAssembleC sqlA = new SQLAssembleC(
//				INIT.AUTH_schema,
//				absConstant.getTableName(),
//				jsonObject.getJSONObject("data"),
//				absConstant.getColumnTypes(),
//				absConstant.getFront2col()
//				);
//		
//		baseDao.sqlQueryExeUByMap(sqlA.getSQL(), sqlA.getMap());
//		
////		return (Integer)sqlA.getMap().get(
////					absConstant.getBack2front().get("NLid")
////				);
//		/*The below Code is dependent on front field, 
//		 * but sometimes It's compatibility is better then the top Code  */
//		return new Integer( sqlA.getMap().get("id").toString() ).intValue();
		return 0;
	}
	
	public void addBySavePOJO(String jdata) {
		
	}
	public void addBySavePOJO2( Object pojo ){
		
	}
	
	public int add(Map data) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int modify( String jdata ){
//		JSONObject jsonObject = JSONObject.fromObject(jdata);
//		AbstractConstant absConstant = new AuthorityButton();
//
//		SQLAssembleU sqlA = new SQLAssembleU(
//				INIT.AUTH_schema,
//				absConstant.getSQLDOC(),
//				absConstant.getTableName(),
//				jsonObject.getJSONObject("data"),
//				jsonObject.getJSONObject("algorithm"),
//				jsonObject.getJSONObject("condition"),
//				jsonObject.getJSONObject("operator"),
//				absConstant.getColumnTypes(),
//				absConstant.getFront2col() );
//		
//		return baseDao.sqlQueryExeUByMap(sqlA.getSQL(), sqlA.getMap());
		
		return -1;
	}

	public Map browse(String jdata) {
//		JSONObject jsonObject = JSONObject.fromObject(jdata);
//		AbstractConstant absConstant = new AuthorityButton();
//
//		SQLAssembleR sqlA = new SQLAssembleR(
//				absConstant.getSQLDOC(),
//				absConstant.getRead_SQL(), 
//				jsonObject.getJSONObject("condition"),
//				jsonObject.getJSONObject("operator"),
//				absConstant.getColumnTypes(),
//				absConstant.getFront2col() );
//		
//		return (Map)baseDao.sqlQuerylistAllByMap(sqlA.getSQL(), sqlA.getMap()).get(0);
		return null;
	}

	public Map browseById(int id) {
//		AbstractConstant absConstant = new AuthorityButton();
//		String pkcolName = absConstant.getPrimaryKeyColumnName();
//		
//		Map cond = new HashMap(1);
//		cond.put( pkcolName, id);
//		Map oper = new HashMap(1); 
//		oper.put( pkcolName, Operator.EQUAL);
//		
//		SQLAssembleR sqlA = new SQLAssembleR(
//				absConstant.getSQLDOC(),
//				absConstant.getRead_SQL(), 
//				cond,
//				oper,
//				absConstant.getColumnTypes(),
//				absConstant.getFront2col() );
//		
//		return (Map)baseDao.sqlQuerylistAllByMap(sqlA.getSQL(), sqlA.getMap()).get(0);
		return null;
	}

	public Map browseById(String id) {
//		AbstractConstant absConstant = new AuthorityButton();
//		String pkcolName = absConstant.getPrimaryKeyColumnName();
//		
//		Map cond = new HashMap(1);
//		cond.put( pkcolName, id);
//		Map oper = new HashMap(1); 
//		oper.put( pkcolName, Operator.EQUAL);
//		
//		SQLAssembleR sqlA = new SQLAssembleR(
//				absConstant.getSQLDOC(),
//				absConstant.getRead_SQL(), 
//				cond,
//				oper,
//				absConstant.getColumnTypes(),
//				absConstant.getFront2col() );
//		
//		return (Map)baseDao.sqlQuerylistAllByMap(sqlA.getSQL(), sqlA.getMap()).get(0);
		return null;
	}

	public Object[] browseArray(String jdata) {
//		JSONObject jsonObject = JSONObject.fromObject(jdata);
//		AbstractConstant absConstant = new AuthorityButton();
//
//		SQLAssembleR sqlA = new SQLAssembleR(
//				absConstant.getSQLDOC(),
//				absConstant.getRead_SQL(), 
//				jsonObject.getJSONObject("condition"),
//				jsonObject.getJSONObject("operator"),
//				absConstant.getColumnTypes(),
//				absConstant.getFront2col() );
//		
//		return (Object[])baseDao.sqlQuerylistAllByMap( sqlA.getSQL(), sqlA.getMap() ).get(0);
		return null;
	}
	
	public Object browsePOJO(String jdata) {
//		JSONObject jsonObject = JSONObject.fromObject(jdata);
//		AbstractConstant absConstant = new AuthorityButton();
//
//		HQLAssembleR hqlA = new HQLAssembleR(absConstant.getSQLDOC(),
//				absConstant.getRead_SQL(), 
//				jsonObject.getJSONObject("condition"),
//				jsonObject.getJSONObject("operator"),
//				absConstant.getColumnTypes(),
//				absConstant.getFront2back() );
//		
//		return baseDao.listAllByMap( "from TAuthorityButton t1 where "+hqlA.getWhereBackHQL(), hqlA.getMap() ).get(0);
		return null;
	}
	public Object browsePOJOById(long id) {
//		return baseDao.findById( new Integer(id)  );
		return null;
	}
	public Object browsePOJOById(String id) {
//		return baseDao.findById( new Integer(id) );
		return null;
	}

	public int delete( String jdata ){
//		JSONObject jsonObject = JSONObject.fromObject(jdata);
//		AbstractConstant absConstant = new AuthorityButton();
//
//		SQLAssembleD sqlA = new SQLAssembleD(
//				INIT.AUTH_schema,
//				absConstant.getSQLDOC(),
//				absConstant.getTableName(), 
//				jsonObject.getJSONObject("condition"),
//				jsonObject.getJSONObject("operator"),
//				absConstant.getColumnTypes(),
//				absConstant.getFront2col() );
//		
//		return baseDao.sqlQueryExeUByMap(sqlA.getSQL(), sqlA.getMap());
		return 0;
	}

	public int deleteNP(String jdata) {
		return this.modify(jdata);
	}

	public void setbaseDao(SpringHibernateDao baseDao) {
		this.baseDao = baseDao;
	}

}
