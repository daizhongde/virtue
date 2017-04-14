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
import person.daizhongde.virtue.util.ReflectUtil;

/**
 * Not use, Only for display
 * @author dzd
 *
 */
public class BaseServiceImpl2<E extends Serializable, ID extends Serializable> implements BaseService2<E, ID> {

	private SpringHibernateDao baseDao;
	private String constantClassName;
	
   public void setBaseDao(SpringHibernateDao baseDao)
   {
     this.baseDao = baseDao;
   }

	public void setConstantClassName(String constantClassName) {
	this.constantClassName = constantClassName;
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
	/**
	 * return The number of entities updated or deleted.
	 */
	public int add(String jdata) {
		JSONObject jsonObject = JSONObject.fromObject(jdata);
//		AbstractConstant absConstant = new AuthorityButton();
		
		AbstractConstant absConstant=null;
		try {
			absConstant = (AbstractConstant)ReflectUtil.newInstance(constantClassName);
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException e) {
			new RuntimeException("reflect-instance->error!");
		}
		
		SQLAssembleC sqlA = new SQLAssembleC(
				INIT.AUTH_schema,
				absConstant.getTableName(),
				jsonObject.getJSONObject("data"),
				absConstant.getColumnTypes(),
				absConstant.getFront2col()
				);
		
		return baseDao.sqlQueryExeUByMap(sqlA.getSQL(), sqlA.getMap());
	}

	public int addRetId(String jdata) {
		JSONObject jsonObject = JSONObject.fromObject(jdata);
		AbstractConstant absConstant=null;
		try {
			absConstant = (AbstractConstant)ReflectUtil.newInstance(constantClassName);
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException e) {
			new RuntimeException("reflect-instance->error!");
		}
		
		Map valueMap = new HashMap();
//		valueMap.put(absConstant.getPrimaryKeyColumnName(), INIT.AUTH_schema + "." + absConstant.getPrimaryKeySequence()+".nextval");
		int id = baseDao.sqlQueryAuto_increment( absConstant.getTableName() );
		valueMap.put(absConstant.getPrimaryKeyColumnName(), id );
		
		SQLAssembleC sqlA = new SQLAssembleC(
				INIT.AUTH_schema,
				absConstant.getTableName(),
				jsonObject.getJSONObject("data"),
				absConstant.getColumnTypes(),
				absConstant.getFront2col(),
				valueMap 
				);
		
		baseDao.sqlQueryExeUByMap(sqlA.getSQL(), sqlA.getMap());
		return id;
	}
	/**
	 * return The number of entities updated or deleted.
	 */
	public int addWithId( String jdata ){
		JSONObject jsonObject = JSONObject.fromObject(jdata);
		AbstractConstant absConstant=null;
		try {
			absConstant = (AbstractConstant)ReflectUtil.newInstance(constantClassName);
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException e) {
			new RuntimeException("reflect-instance->error!");
		}
		SQLAssembleC sqlA = new SQLAssembleC(
				INIT.AUTH_schema,
				absConstant.getTableName(),
				jsonObject.getJSONObject("data"),
				absConstant.getColumnTypes(),
				absConstant.getFront2col()
				);
		
		return baseDao.sqlQueryExeUByMap(sqlA.getSQL(), sqlA.getMap());
	}

	public int addWithIdRetId(String jdata) {
		JSONObject jsonObject = JSONObject.fromObject(jdata);
		AbstractConstant absConstant=null;
		try {
			absConstant = (AbstractConstant)ReflectUtil.newInstance(constantClassName);
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException e) {
			new RuntimeException("reflect-instance->error!");
		}
		SQLAssembleC sqlA = new SQLAssembleC(
				INIT.AUTH_schema,
				absConstant.getTableName(),
				jsonObject.getJSONObject("data"),
				absConstant.getColumnTypes(),
				absConstant.getFront2col()
				);
		
		baseDao.sqlQueryExeUByMap(sqlA.getSQL(), sqlA.getMap());
		
//		return (Integer)sqlA.getMap().get(
//					absConstant.getBack2front().get("NLid")
//				);
		/*The below Code is dependent on front field, 
		 * but sometimes It's compatibility is better then the top Code  */
		return new Integer( sqlA.getMap().get("id").toString() ).intValue();
	}
	
	public void addBySavePOJO(String jdata) {
		JSONObject jsonObject = JSONObject.fromObject(jdata);
		AbstractConstant absConstant=null;
		try {
			absConstant = (AbstractConstant)ReflectUtil.newInstance(constantClassName);
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException e) {
			new RuntimeException("reflect-instance->error!");
		}

		SQLAssembleC sqlA = new SQLAssembleC(
				absConstant.getTableName(),
				jsonObject.getJSONObject("data"),
				absConstant.getColumnTypes(),
				absConstant.getFront2col()
				);
		
		Object pojo;
//		TAuthorityModule pojo = new TAuthorityModule();
		Map map =sqlA.getMap();

//		pojo.setCMname(String.valueOf(map.get("cmname")));
//		pojo.setCMpath(String.valueOf(map.get("cmpath")));
//		pojo.setCMleaf(String.valueOf(map.get("cmleaf")));
//		pojo.setNMparent(Integer.valueOf(map.get("nmparent").toString()));
//		pojo.setNMlevel(Short.valueOf(map.get("nmlevel").toString()));
//		pojo.setNMorder(Short.valueOf(map.get("nmorder").toString()));
//		pojo.setCMnote(String.valueOf(map.get("cmnote")));

//		baseDao.save(pojo);
	}
	public void addBySavePOJO2( E pojo ){
		
	}
	
	public int add(Map data) {
		AbstractConstant absConstant=null;
		try {
			absConstant = (AbstractConstant)ReflectUtil.newInstance(constantClassName);
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException e) {
			new RuntimeException("reflect-instance->error!");
		}
		
		SQLAssembleC sqlA = new SQLAssembleC(
				INIT.AUTH_schema,
				absConstant.getTableName(),
				data,
				absConstant.getColumnTypes(),
				absConstant.getFront2col()
				);
		
		return baseDao.sqlQueryExeUByMap(sqlA.getSQL(), sqlA.getMap());
	}

	public int modify( String jdata ){
		JSONObject jsonObject = JSONObject.fromObject(jdata);
		AbstractConstant absConstant=null;
		try {
			absConstant = (AbstractConstant)ReflectUtil.newInstance(constantClassName);
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException e) {
			new RuntimeException("reflect-instance->error!");
		}
		SQLAssembleU sqlA = new SQLAssembleU(
				INIT.AUTH_schema,
				absConstant.getSQLDOC(),
				absConstant.getTableName(),
				jsonObject.getJSONObject("data"),
				jsonObject.getJSONObject("algorithm"),
				jsonObject.getJSONObject("condition"),
				jsonObject.getJSONObject("operator"),
				absConstant.getColumnTypes(),
				absConstant.getFront2col() );
		
		return baseDao.sqlQueryExeUByMap(sqlA.getSQL(), sqlA.getMap());
	}

	public Map browse(String jdata) {
		JSONObject jsonObject = JSONObject.fromObject(jdata);
		AbstractConstant absConstant=null;
		try {
			absConstant = (AbstractConstant)ReflectUtil.newInstance(constantClassName);
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException e) {
			new RuntimeException("reflect-instance->error!");
		}
		SQLAssembleR sqlA = new SQLAssembleR(
				absConstant.getSQLDOC(),
				absConstant.getRead_SQL(), 
				jsonObject.getJSONObject("condition"),
				jsonObject.getJSONObject("operator"),
				absConstant.getColumnTypes(),
				absConstant.getFront2col() );
		
		return (Map)baseDao.sqlQuerylistAllByMap(sqlA.getSQL(), sqlA.getMap()).get(0);
	}

	public Map browseById(int id) {
		AbstractConstant absConstant=null;
		try {
			absConstant = (AbstractConstant)ReflectUtil.newInstance(constantClassName);
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException e) {
			new RuntimeException("reflect-instance->error!");
		}
		
		String pkcolName = absConstant.getPrimaryKeyColumnName();
		
		Map cond = new HashMap(1);
		cond.put( pkcolName, id);
		Map oper = new HashMap(1); 
		oper.put( pkcolName, Operator.EQUAL);
		
		SQLAssembleR sqlA = new SQLAssembleR(
				absConstant.getSQLDOC(),
				absConstant.getRead_SQL(), 
				cond,
				oper,
				absConstant.getColumnTypes(),
				absConstant.getFront2col() );
		
		return (Map)baseDao.sqlQuerylistAllByMap(sqlA.getSQL(), sqlA.getMap()).get(0);
	}

	public Map browseById(String id) {
		AbstractConstant absConstant=null;
		try {
			absConstant = (AbstractConstant)ReflectUtil.newInstance(constantClassName);
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException e) {
			new RuntimeException("reflect-instance->error!");
		}
		String pkcolName = absConstant.getPrimaryKeyColumnName();
		
		Map cond = new HashMap(1);
		cond.put( pkcolName, id);
		Map oper = new HashMap(1); 
		oper.put( pkcolName, Operator.EQUAL);
		
		SQLAssembleR sqlA = new SQLAssembleR(
				absConstant.getSQLDOC(),
				absConstant.getRead_SQL(), 
				cond,
				oper,
				absConstant.getColumnTypes(),
				absConstant.getFront2col() );
		
		return (Map)baseDao.sqlQuerylistAllByMap(sqlA.getSQL(), sqlA.getMap()).get(0);
	}

	public Object[] browseArray(String jdata) {
		JSONObject jsonObject = JSONObject.fromObject(jdata);
		AbstractConstant absConstant=null;
		try {
			absConstant = (AbstractConstant)ReflectUtil.newInstance(constantClassName);
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException e) {
			new RuntimeException("reflect-instance->error!");
		}

		SQLAssembleR sqlA = new SQLAssembleR(
				absConstant.getSQLDOC(),
				absConstant.getRead_SQL(), 
				jsonObject.getJSONObject("condition"),
				jsonObject.getJSONObject("operator"),
				absConstant.getColumnTypes(),
				absConstant.getFront2col() );
		
		return (Object[])baseDao.sqlQuerylistAllByMap( sqlA.getSQL(), sqlA.getMap() ).get(0);
	}
	
	public E browsePOJO(String jdata) {
		JSONObject jsonObject = JSONObject.fromObject(jdata);
		AbstractConstant absConstant=null;
		try {
			absConstant = (AbstractConstant)ReflectUtil.newInstance(constantClassName);
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException e) {
			new RuntimeException("reflect-instance->error!");
		}

		HQLAssembleR hqlA = new HQLAssembleR(absConstant.getSQLDOC(),
				absConstant.getRead_SQL(), 
				jsonObject.getJSONObject("condition"),
				jsonObject.getJSONObject("operator"),
				absConstant.getColumnTypes(),
				absConstant.getFront2back() );
		
		return (E)baseDao.listAllByMap( "from TAuthorityButton t1 where "+hqlA.getWhereBackHQL(), hqlA.getMap() ).get(0);
	}
//	public E browsePOJOById(int id) {
//		return baseDao.findById(E, new Integer(id)  );
//	}
//	public E browsePOJOById(String id) {
//		return baseDao.findById(E, new Integer(id) );
//	}
	public E browsePOJOById(final Class clazz,final Serializable id ) {
		return (E)baseDao.findById(clazz, id );
	}
	public E browsePOJOById(final String entityName,final Serializable id ) {
		return (E)baseDao.findById(entityName, id );
	}
	public int delete( String jdata ){
		JSONObject jsonObject = JSONObject.fromObject(jdata);
		AbstractConstant absConstant=null;
		try {
			absConstant = (AbstractConstant)ReflectUtil.newInstance(constantClassName);
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException e) {
			new RuntimeException("reflect-instance->error!");
		}

		SQLAssembleD sqlA = new SQLAssembleD(
				INIT.AUTH_schema,
				absConstant.getSQLDOC(),
				absConstant.getTableName(), 
				jsonObject.getJSONObject("condition"),
				jsonObject.getJSONObject("operator"),
				absConstant.getColumnTypes(),
				absConstant.getFront2col() );
		
		return baseDao.sqlQueryExeUByMap(sqlA.getSQL(), sqlA.getMap());
	}

	public int deleteNP(String jdata) {
		return this.modify(jdata);
	}

	public void setbaseDao(SpringHibernateDao baseDao) {
		this.baseDao = baseDao;
	}

}
