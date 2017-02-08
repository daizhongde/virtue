package person.daizhongde.virtue.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.orm.hibernate5.SessionFactoryUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.HibernateException;
import org.hibernate.type.StandardBasicTypes;

import person.daizhongde.virtue.constant.INIT;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
 
/**
 * Description:
 * <br/>网站: <a href="http://www.crazyjava.org">疯狂Java联盟</a> 
 * <br/>Copyright (C), 2011-2015, Dai zhongde 
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name: The daizhongde's super platform for development
 * <br/>Date:20110518
 * <br/>update 2013/8/27 by dai zhongde
 * 
 * <p>
 * 		hibernate3
		Connection conn = session.connection();
		
		hibernate4
		SessionFactoryImplementor sessionFactory = (SessionFactoryImplementor)new Configuration().configure().buildSessionFactory();   
		Connection conn = sessionFactory.getConnectionProvider().getConnection();
		
		hibernate4
		ConnectionProvider cp =((SessionFactoryImplementor)session.getSessionFactory()).getConnectionProvider();  
		Connection conn = cp.getConnection();  
		
		hibernate4
		getSession().doWork(
				  new Work() {
				    public void execute(Connection connection) {  
				      // 这里已经得到connection了，可以继续你的JDBC代码。  
				      // 注意不要close了这个connection。  
				    }  
				  }  
				);
	 
 * @author  daizhongde dzd2746679@163.com 
 * @version  1.1
 */ 
public class SpringHibernate5DaoSupport extends HibernateDaoSupport implements SpringHibernateDao
{
	private static Logger log = LoggerFactory.getLogger(SpringHibernate5DaoSupport.class);

	/**
	 * 使用HQL 语句进行分页查询操作
	 * @param HQL 需要查询的HQL语句
	 * @param offset 第一条记录索引
	 * @param pageSize 每页需要显示的记录数
	 * @return 当前页的所有记录
	 */
	@SuppressWarnings("rawtypes")
	public List findByPage(final String HQL, 
		 final int offset, final int pageSize)
	{
		List list = (List)getHibernateTemplate().execute(
			new HibernateCallback()
			{
				public Object doInHibernate(Session session)
					throws HibernateException
				{
					List result = session.createQuery(HQL)
						.setFirstResult(offset)
						.setMaxResults(pageSize)
						.list();
					return result;
				}
			});
		return list;
	}

	/**
	 * 使用HQL 语句进行分页查询操作
	 * @param HQL 需要查询的HQL语句
	 * @param condition
	 * @param offset 第一条记录索引
	 * @param pageSize 每页需要显示的记录数
	 * @return 当前页的所有记录
	 */
	public List findByPageByMap(final String HQL, final Map condition,
		 final int offset, final int pageSize)
	{
		if(condition == null){
			return findByPage( HQL, offset, pageSize);
		}
		
		@SuppressWarnings("deprecation")
		List list = (List)getHibernateTemplate().execute(
			new HibernateCallback()
			{
				public Object doInHibernate(Session session)
					throws HibernateException
				{
					Query query = session.createQuery(HQL);
					
					//noAlias: no table alias
					String fieldName;//multiple talble have the same column use column's alias
					Iterator it = condition.keySet().iterator();
					Object o;
					while(it.hasNext()){
						fieldName = it.next().toString();
						o = condition.get(fieldName);
						Class type = o.getClass();
						if( !(o instanceof java.util.List) && !(type.isArray() ) ){
							query.setParameter(fieldName, condition.get(fieldName));
						}else if( type.isArray() ){
							Object[] array = (Object[])condition.get(fieldName);
							query.setParameter(fieldName+"0", array[0] );
							query.setParameter(fieldName+"1", array[1] );
						}else if( ((List)o).size() != 0 ){//
//							/** o instanceof java.util.List **/
							if(INIT.supportSetParameterList == null || Boolean.valueOf( INIT.supportSetParameterList ).booleanValue() ){
								query.setParameterList(fieldName, (List)condition.get(fieldName));
							}else{
								//not support setParameterList
								List list = (List)o;
								for(int m=0, n=list.size(); m<n; m++){
									query.setParameter(fieldName+m, list.get(m) );
								}
							}
						}
					}
					List result = query.setFirstResult(offset)
						.setMaxResults(pageSize)
						.list();
					return result;
				}
			});
		return list;
	}
	
	/**
	 * 使用HQL 语句进行查询操作
	 * @param HQL 需要查询的HQL语句
	 * @return 当前页的所有记录
	 */
	public List listAll(final String HQL)
	{
		return getHibernateTemplate().find(HQL);
	}

	/**
	 * 使用HQL 语句进行查询操作
	 * @param HQL 需要查询的HQL语句
	 * @param condition 如果HQL有多个参数需要传入，值就是传入的参数名值对
	 * @return 查询的所有记录(pojo类型)
	 */
	public List listAllByMap( final String HQL,final Map condition )
	{
		if(condition == null){
			return listAll(HQL);
		}
		
		List list = (List)getHibernateTemplate().execute(
			new HibernateCallback()
			{
				public Object doInHibernate(Session session)
					throws HibernateException
				{
//					System.out.println("HQL:"+HQL);
					Query query = session.createQuery(HQL);
					
					//noAlias: no table alias
					String fieldName;//multiple talble have the same column use column's alias
					Iterator it = condition.keySet().iterator();
					Object o;
					int i=0;
					while(it.hasNext()){
						fieldName = it.next().toString();
//						System.out.println("fieldName:"+fieldName);
						o = condition.get(fieldName);
//						System.out.println("o.toString():"+o.toString());
						Class type = o.getClass();
						if( !(o instanceof java.util.List) && !(type.isArray() ) ){
							query.setParameter(fieldName, condition.get(fieldName));
						}else if( type.isArray() ){
							Object[] array = (Object[])condition.get(fieldName);
							query.setParameter(fieldName+"0", array[0] );
							query.setParameter(fieldName+"1", array[1] );
						}else if( ((List)o).size() != 0 ){//
//							/** o instanceof java.util.List **/
							if(INIT.supportSetParameterList == null || Boolean.valueOf( INIT.supportSetParameterList ).booleanValue() ){
								query.setParameterList(fieldName, (List)condition.get(fieldName));
							}else{
								//not support setParameterList
								List list = (List)o;
								for(int m=0, n=list.size(); m<n; m++){
									query.setParameter(fieldName+m, list.get(m) );
								}
							}
						}
					}
					List result = query.list();
					return result;
				}
			});
		return list;
	}
	/**
	 * 使用HQL 查询语句进行单值查询操作
	 * @param HQL 需要查询的HQL语句
	 * @return 一个值对象
	 */
	public Object findaValue(final String HQL)
	{
		return getHibernateTemplate().find(HQL).get( 0 );
	}
	
	/**
	 * 使用HQL 查询语句进行单值查询操作
	 * @param HQL 需要查询的HQL语句
	 * @param condition
	 * @return 一个值对象
	 */
	public Object findaValueByMap( final String HQL,final Map condition )
	{
		if(condition == null){
			return findaValue(HQL);
		}
		
		List list = (List)getHibernateTemplate().execute(
				new HibernateCallback()
				{
					public Object doInHibernate(Session session)
						throws HibernateException
					{
//						System.out.println("HQL:"+HQL);
						Query query = session.createQuery(HQL);
						
						//noAlias: no table alias
						String fieldName;//multiple talble have the same column use column's alias
						Iterator it = condition.keySet().iterator();
						Object o;
						int i=0;
						while(it.hasNext()){
							fieldName = it.next().toString();
							o = condition.get(fieldName);
							Class type = o.getClass();
							if( !(o instanceof java.util.List) && !(type.isArray() ) ){
								query.setParameter(fieldName, condition.get(fieldName));
							}else if( type.isArray() ){
								Object[] array = (Object[])condition.get(fieldName);
								query.setParameter(fieldName+"0", array[0] );
								query.setParameter(fieldName+"1", array[1] );
							}else if( ((List)o).size() != 0 ){//
//								/** o instanceof java.util.List **/
								if(INIT.supportSetParameterList == null 
										|| Boolean.valueOf( INIT.supportSetParameterList ).booleanValue() ){
									query.setParameterList(fieldName, (List)condition.get(fieldName));
								}else{
									//not support setParameterList
									List list = (List)o;
									for(int m=0, n=list.size(); m<n; m++){
										query.setParameter(fieldName+m, list.get(m) );
									}
								}
							}
						}
						List result = query.list();
						return result;
					}
				});
			return list.get(0);
	}
	
	/**
	 * 使用HQL 语句执行update或者delete
	 * @param HQL 需要查询的HQL语句
	 * @return 当前页的所有记录
	 */
	public int exeU(final String HQL){
		Integer i = (Integer)this.getHibernateTemplate().execute(
			new HibernateCallback(){
				public Object doInHibernate(final Session session)
					throws HibernateException
				{
					int result = session.createQuery(HQL).executeUpdate();
					return new Integer(result);
				}
			});
		return i.intValue();
	}

	/**
	 * 使用HQL 语句执行update或者delete
	 * @param HQL 需要查询的HQL语句
	 * @param condition
	 * @return 当前页的所有记录
	 */
	public int exeUByMap(final String HQL,final Map condition){
		if(condition == null){
			return exeU(HQL);
		}
		Integer i = (Integer)this.getHibernateTemplate().execute(
			new HibernateCallback(){
				public Object doInHibernate(Session session)
					throws HibernateException{
					Query query = session.createQuery(HQL);
					
					//noAlias: no table alias
					String fieldName;//multiple table have the same column use column's alias
					Iterator it = condition.keySet().iterator();
					Object o;
					while(it.hasNext()){
						fieldName = it.next().toString();
						o = condition.get(fieldName);
						Class type = o.getClass();
						if( !(o instanceof java.util.List) && !(type.isArray() ) ){
							query.setParameter(fieldName, condition.get(fieldName));
						}else if( type.isArray() ){
							Object[] array = (Object[])condition.get(fieldName);
							query.setParameter(fieldName+"0", array[0] );
							query.setParameter(fieldName+"1", array[1] );
						}else if( ((List)o).size() != 0 ){
//							/** o instanceof java.util.List **/
							if(INIT.supportSetParameterList == null || Boolean.valueOf( INIT.supportSetParameterList ).booleanValue() ){
								query.setParameterList(fieldName, (List)condition.get(fieldName));
							}else{
								//not support setParameterList
								List list = (List)o;
								for(int m=0, n=list.size(); m<n; m++){
									query.setParameter(fieldName+m, list.get(m) );
								}
							}
						}
					}
					int result = query.executeUpdate();
					return new Integer(result);
				}
			});
		return i.intValue();
	}
	
	/**
	 * 通过ID删除持久化对象
	 * @param clazz
	 * @param id
	 */
	public void delete(final Class clazz,final Serializable id)
	{
		getHibernateTemplate().delete(getHibernateTemplate().load(clazz, id));
//		this.getSession().delete(this.getSession().load(clazz, id));
//		this.getSessionFactory().getCurrentSession().delete(this.getSession().load(clazz, id));
//		this.getHibernateTemplate().execute(
//			new HibernateCallback(){
//				public Object doInHibernate(Session session)
//					throws HibernateException
//				{
//					Object result = session.load(clazz, id);
//					return result;
//				}
//				session.delete(result);
//			});
		
	}
	
	
	/**
	 * 使用Native SQL 查询语句进行分页查询操作
	 * @param sql 需要查询的sql语句
	 * @param offset 第一条记录索引
	 * @param pageSize 每页需要显示的记录数
	 * @return 当前页的所有记录
	 */
	public List sqlQueryfindByPage(final String sql, 
		 final int offset, final int pageSize)
	{
		List list = (List)getHibernateTemplate().execute(
			new HibernateCallback()
			{
				public Object doInHibernate(Session session)
					throws HibernateException
				{
					List result = session.createSQLQuery(sql)
						.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP)
						.setFirstResult(offset)
						.setMaxResults(pageSize)
						.list();
					return result;
				}
			});
		return list;
	}

	/**
	 * 使用Native SQL 查询 语句进行分页查询操作
	 * @param sql 需要查询的sql语句
	 * @param condition
	 * @param offset 第一条记录索引
	 * @param pageSize 每页需要显示的记录数
	 * @return 当前页的所有记录
	 */
	public List sqlQueryfindByPageByMap(final String sql, final Map condition,
		 final int offset, final int pageSize)
	{
		if(condition == null){
			return sqlQueryfindByPage(sql,offset,pageSize);
		}
		
//		log.debug( sql );
		List list = (List)getHibernateTemplate().execute(
			new HibernateCallback()
			{
				public Object doInHibernate(Session session)
					throws HibernateException
				{
//					Query query = session.createSQLQuery(sql).setResultSetMapping(name);
					Query query = session.createSQLQuery(sql)
						.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
					
					//noAlias: no table alias
					String fieldName;//multiple talble have the same column use column's alias
					Iterator it = condition.keySet().iterator();
					Object o;
					//if nest sql need pass parameter, 
					//need another collection type which distinguish this type from list and array
					while(it.hasNext()){
						fieldName = it.next().toString();
						o = condition.get(fieldName);
						Class type = o.getClass();
						if( !(o instanceof java.util.List) && !(type.isArray() ) ){
							query.setParameter(fieldName, condition.get(fieldName));
						}else if( type.isArray() ){
							Object[] array = (Object[])condition.get(fieldName);
							query.setParameter(fieldName+"0", array[0] );
							query.setParameter(fieldName+"1", array[1] );
						}else if( ((List)o).size() != 0 ){//
//							/** o instanceof java.util.List **/
							if(INIT.supportSetParameterList == null || Boolean.valueOf( INIT.supportSetParameterList ).booleanValue()){
								query.setParameterList(fieldName, (List)condition.get(fieldName));
							}else{
								//not support setParameterList
								List list = (List)o;
								for(int m=0, n=list.size(); m<n; m++){
									query.setParameter(fieldName+m, list.get(m) );
								}
							}
						}
					}
					
					List result = query.setFirstResult(offset)
							.setMaxResults(pageSize)
							.list();
					return result;
				}
			});
		return list;
	}
	/**
	 * 使用Native SQL 查询语句进行分页查询操作
	 * @param sql 需要查询的sql语句
	 * @param offset 第一条记录索引
	 * @param pageSize 每页需要显示的记录数
	 * @return 当前页的所有记录
	 */
	public List sqlQueryfindRetArrayByPage(final String sql, 
		 final int offset, final int pageSize)
	{
		List list = (List)getHibernateTemplate().execute(
			new HibernateCallback()
			{
				public Object doInHibernate(Session session)
					throws HibernateException
				{
					List result = session.createSQLQuery(sql)
						.setFirstResult(offset)
						.setMaxResults(pageSize)
						.list();
					return result;
				}
			});
		return list;
	}
	/**
	 * 使用Native SQL 查询 语句进行分页查询操作
	 * @param sql 需要查询的sql语句
	 * @param condition
	 * @param offset 第一条记录索引
	 * @param pageSize 每页需要显示的记录数
	 * @return 当前页的所有记录
	 */
	public List sqlQueryfindRetArrayByPageByMap(final String sql, final Map condition,
		 final int offset, final int pageSize)
	{
		
		if(condition == null){
			return sqlQueryfindRetArrayByPage(sql,offset,pageSize);
		}
		
		log.debug( sql );
		List list = (List)getHibernateTemplate().execute(
			new HibernateCallback()
			{
				public Object doInHibernate(Session session)
					throws HibernateException
				{
					Query query = session.createSQLQuery(sql);
					
					//noAlias: no table alias
					String fieldName;//multiple talble have the same column use column's alias
					Iterator it = condition.keySet().iterator();
					Object o;
					while(it.hasNext()){
						fieldName = it.next().toString();
						o = condition.get(fieldName);
						Class type = o.getClass();
						if( !(o instanceof java.util.List) && !(type.isArray() ) ){
							query.setParameter(fieldName, condition.get(fieldName));
						}else if( type.isArray() ){
							Object[] array = (Object[])condition.get(fieldName);
							query.setParameter(fieldName+"0", array[0] );
							query.setParameter(fieldName+"1", array[1] );
						}else if( ((List)o).size() != 0 ){//
//							/** o instanceof java.util.List **/
							if(INIT.supportSetParameterList == null || Boolean.valueOf( INIT.supportSetParameterList ).booleanValue() ){
								query.setParameterList(fieldName, (List)condition.get(fieldName));
							}else{
								//not support setParameterList
								List list = (List)o;
								for(int m=0, n=list.size(); m<n; m++){
									query.setParameter(fieldName+m, list.get(m) );
								}
							}
						}
					}
					
					List result = query.setFirstResult(offset)
							.setMaxResults(pageSize)
							.list();
					return result;
				}
			});
		return list;
	}
	/**
	 * 使用Native SQL 查询语句进行分页查询操作
	 * @param sql 需要查询的sql语句
	 * @param offset 第一条记录索引
	 * @param pageSize 每页需要显示的记录数
	 * @param c 数据实体
	 * @return 当前页的所有记录(pojo类型)
	 */
	public List sqlQueryfindByPage(final String sql, 
		 final int offset, final int pageSize, final Class c)
	{
		List list = (List)getHibernateTemplate().execute(
			new HibernateCallback()
			{
				public Object doInHibernate(Session session)
					throws HibernateException
				{
					List result = session.createSQLQuery(sql).addEntity(c)
						.setFirstResult(offset)
						.setMaxResults(pageSize)
						.list();
					return result;
				}
			});
		return list;
	}

	/**
	 * 使用natvie sql 语句进行查询操作
	 * @param sql 需要查询的sql语句
	 * @param condition 如果sql有多个参数需要传入，值就是传入的参数名值对
	 * @param offset 第一条记录索引
	 * @param pageSize 每页需要显示的记录数
	 * @param c 数据实体
	 * @return 查询的所有记录(pojo类型)
	 */
	public List sqlQueryfindByPageByMap(final String sql,final Map condition,
			final int offset, final int pageSize, final Class c )
	{
		if(condition == null){
			return sqlQueryfindByPage(sql,offset,pageSize);
		}
		
		List list = (List)getHibernateTemplate().execute(
			new HibernateCallback()
			{
				public Object doInHibernate(Session session)
					throws HibernateException
				{
					Query query = session.createSQLQuery(sql).addEntity(c);
					
					//noAlias: no table alias
					String fieldName;//multiple talble have the same column use column's alias
					Iterator it = condition.keySet().iterator();
					Object o;
					while(it.hasNext()){
						fieldName = it.next().toString();
						o = condition.get(fieldName);
						Class type = o.getClass();
						if( !(o instanceof java.util.List) && !(type.isArray() ) ){
							query.setParameter(fieldName, condition.get(fieldName));
						}else if( type.isArray() ){
							Object[] array = (Object[])condition.get(fieldName);
							query.setParameter(fieldName+"0", array[0] );
							query.setParameter(fieldName+"1", array[1] );
						}else if( ((List)o).size() != 0 ){//
//							/** o instanceof java.util.List **/
							if(INIT.supportSetParameterList == null || Boolean.valueOf( INIT.supportSetParameterList ).booleanValue() ){
								query.setParameterList(fieldName, (List)condition.get(fieldName));
							}else{
								//not support setParameterList
								List list = (List)o;
								for(int m=0, n=list.size(); m<n; m++){
									query.setParameter(fieldName+m, list.get(m) );
								}
							}
						}
					}
					List result = query.setFirstResult(offset)
							.setMaxResults(pageSize)
							.list();
					return result;
				}
			});
		return list;
	}
	/**
	 * 使用natvie sql 语句进行查询操作
	 * @param sql 需要查询的sql语句
	 * @return 查询的所有记录  ordinary return array type data
	 */
	public List sqlQuerylistAll(final String sql)
	{
		List list = (List)getHibernateTemplate().execute(
			new HibernateCallback()
			{
				public Object doInHibernate(Session session)
					throws HibernateException
				{
					List result = session.createSQLQuery(sql)
							.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP)
							.list();
					return result;
				}
			});
		return list;
	}

	/**
	 * 
	 * 使用natvie sql 语句进行查询操作
	 * <br>who used eg: getComboboxData
	 * @param sql 需要查询的sql语句
	 * @param condition 如果sql有多个参数需要传入，值就是传入的参数名值对
	 * @return 查询的所有记录(pojo类型) list.size()=1
	 */
	public List sqlQuerylistAllByMap( final String sql,final Map condition )
	{
		System.out.println("sql:"+sql);
		if(condition == null){
			return sqlQuerylistAll(sql);
		}
		
		List list = (List)getHibernateTemplate().execute(
			new HibernateCallback()
			{
				public Object doInHibernate(Session session)
					throws HibernateException
				{
//					@SuppressWarnings("deprecation")
//					Query query = session.createSQLQuery(sql)
//							.addScalar("id", StandardBasicTypes.INTEGER)
//							.addScalar("lotno", StandardBasicTypes.STRING)
//							.addScalar("ht", StandardBasicTypes.STRING)
//							.addScalar("path", StandardBasicTypes.STRING);
					
//					 String nativeSql = "SELECT ID as ID,name as NAME,LOGIN_NAME as LOGINNAME,CREATE_TIME as TIME FROM T_USER WHERE LOGIN_NAME=?";   
//					 List scalarList = new ArrayList();   
//					 Map map = new HashMap();   
					 //hibernate4写法   
//					 map.put("ID", LongType.INSTANCE);   
//					 map.put("NAME", StringType.INSTANCE);   
//					 map.put("LOGINNAME", StringType.INSTANCE);   
//					 map.put("TIME", DateType.INSTANCE);   
					
//					scalarList.addAll(map.entrySet());   
//					type convert lay in sql, there is don't deal with type problem
//					attention: oracle query result column name is UpperCase, so Condition key is UpperCase
//					we need make front name Condition back name possibility
					Query query = session.createSQLQuery(sql)
							.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
					
					//noAlias: no table alias
					String fieldName;//multiple talble have the same column use column's alias
					Iterator it = condition.keySet().iterator();
					Object o;
					while(it.hasNext()){
						fieldName = it.next().toString();
						o = condition.get(fieldName);
						Class type = o.getClass();
						if( !(o instanceof java.util.List) && !(type.isArray() ) ){
							query.setParameter(fieldName, condition.get(fieldName));
						}else if( type.isArray() ){
							Object[] array = (Object[])condition.get(fieldName);
							query.setParameter(fieldName+"0", array[0] );
							query.setParameter(fieldName+"1", array[1] );
						}else if( ((List)o).size() != 0 ){//
//							/** o instanceof java.util.List **/
							if(INIT.supportSetParameterList == null || Boolean.valueOf( INIT.supportSetParameterList ).booleanValue() ){
								query.setParameterList(fieldName, (List)condition.get(fieldName));
							}else{
								//not support setParameterList
								List list = (List)o;
								for(int m=0, n=list.size(); m<n; m++){
									query.setParameter(fieldName+m, list.get(m) );
								}
							}
						}
					}
					List result = query.list();
					return result;
				}
			});
		return list;
	}
	/**
	 * 使用natvie sql 语句进行查询操作
	 * @param sql 需要查询的sql语句
	 * @return 查询的所有记录  ordinary return array type data
	 */
	public List sqlQuerylistAllRetArray(final String sql)
	{
		List list = (List)getHibernateTemplate().execute(
			new HibernateCallback()
			{
				public Object doInHibernate(Session session)
					throws HibernateException
				{
					List result = session.createSQLQuery(sql).list();
					return result;
				}
			});
		return list;
	}
	/**
	 * 使用natvie sql 语句进行查询操作
	 * @param sql 需要查询的sql语句
	 * @return 查询的所有记录  ordinary return array type data
	 */
	public List sqlQuerylistAllRetArray(final String sql, final String col )
	{
		List list = (List)getHibernateTemplate().execute(
			new HibernateCallback()
			{
				public Object doInHibernate(Session session)
					throws HibernateException
				{
					List result = session.createSQLQuery(sql)
							.addScalar(col, StandardBasicTypes.STRING )
							.list();
					return result;
				}
			});
		return list;
	}
	/**
	 * 使用natvie sql 语句进行查询操作
	 * @param sql 需要查询的sql语句
	 * @param condition 如果sql有多个参数需要传入，值就是传入的参数名值对
	 * @return 查询的所有记录  ordinary return array type data
	 */
	public List sqlQuerylistAllRetArrayByMap( final String sql,final Map condition )
	{
		if(condition == null){
			return sqlQuerylistAllRetArray(sql);
		}
		
		List list = (List)getHibernateTemplate().execute(
			new HibernateCallback()
			{
				public Object doInHibernate(Session session)
					throws HibernateException
				{
					Query query = session.createSQLQuery(sql);

					//noAlias: no table alias
					String fieldName;//multiple talble have the same column use column's alias
					Iterator it = condition.keySet().iterator();
					Object o;
					while(it.hasNext()){
						fieldName = it.next().toString();
						o = condition.get(fieldName);
						Class type = o.getClass();
						if( !(o instanceof java.util.List) && !(type.isArray() ) ){
							query.setParameter(fieldName, condition.get(fieldName));
						}else if( type.isArray() ){
							Object[] array = (Object[])condition.get(fieldName);
							query.setParameter(fieldName+"0", array[0] );
							query.setParameter(fieldName+"1", array[1] );
						}else if( ((List)o).size() != 0 ){//
//							/** o instanceof java.util.List **/
							if(INIT.supportSetParameterList == null || Boolean.valueOf( INIT.supportSetParameterList ).booleanValue() ){
								query.setParameterList(fieldName, (List)condition.get(fieldName));
							}else{
								//not support setParameterList
								List list = (List)o;
								for(int m=0, n=list.size(); m<n; m++){
									query.setParameter(fieldName+m, list.get(m) );
								}
							}
						}
					}
					List result = query.list();
					return result;
				}
			});
		return list;
	}

	/**
	 * 使用natvie sql 语句进行查询操作
	 * @param sql 需要查询的sql语句
	 * @return 查询的所有记录(pojo类型)
	 */
	public List sqlQuerylistAll(final String sql,final Class c)
	{
		List list = (List)getHibernateTemplate().execute(
			new HibernateCallback()
			{
				public Object doInHibernate(Session session)
					throws HibernateException
				{
					List result = session.createSQLQuery(sql)
						.addEntity(c).list();
					return result;
				}
			});
		return list;
	}

	/**
	 * 使用natvie sql 语句进行查询操作
	 * @param sql 需要查询的sql语句
	 * @param condition 如果sql有多个参数需要传入，值就是传入的参数名值对
	 * @return 查询的所有记录(pojo类型)
	 */
	public List sqlQuerylistAllByMap(final String sql,final Map condition,final Class c)
	{
		if(condition == null){
			return sqlQuerylistAll(sql,c);
		}
		
		List list = (List)getHibernateTemplate().execute(
			new HibernateCallback()
			{
				public Object doInHibernate(Session session)
					throws HibernateException
				{
					Query query = session.createSQLQuery(sql).addEntity(c);

					//noAlias: no table alias
					String fieldName;//multiple talble have the same column use column's alias
					Iterator it = condition.keySet().iterator();
					Object o;
					while(it.hasNext()){
						fieldName = it.next().toString();
						o = condition.get(fieldName);
						Class type = o.getClass();
						if( !(o instanceof java.util.List) && !(type.isArray() ) ){
							query.setParameter(fieldName, condition.get(fieldName));
						}else if( type.isArray() ){
							Object[] array = (Object[])condition.get(fieldName);
							query.setParameter(fieldName+"0", array[0] );
							query.setParameter(fieldName+"1", array[1] );
						}else if( ((List)o).size() != 0 ){//
//							/** o instanceof java.util.List **/
							if(INIT.supportSetParameterList == null || Boolean.valueOf( INIT.supportSetParameterList ).booleanValue() ){
								query.setParameterList(fieldName, (List)condition.get(fieldName));
							}else{
								//not support setParameterList
								List list = (List)o;
								for(int m=0, n=list.size(); m<n; m++){
									query.setParameter(fieldName+m, list.get(m) );
								}
							}
						}
					}
					List result = query.list();
					return result;
				}
			});
		return list;
	}
	/**
	 * 使用Native SQL 查询语句进行单值查询操作
	 * @param sql 需要查询的sql语句
	 * @return 一个值对象
	 */
	public Object sqlQueryfindaValue(final String sql)
	{
		return sqlQuerylistAllRetArray( sql ).get( 0 );
	}
	/**
	 * 使用Native SQL 查询语句进行单值查询操作
	 * @param sql 需要查询的sql语句
	 * @return 一个值对象
	 */
	public Object sqlQueryfindaValue(final String sql, final String col )
	{
		return sqlQuerylistAllRetArray( sql, col ).get( 0 );
	}
	/**
	 * 使用Native SQL 查询语句进行单值查询操作
	 * @param sql 需要查询的sql语句
	 * @param condition
	 * @return 一个值对象
	 */
	public Object sqlQueryfindaValueByMap(final String sql, final Map condition )
	{
//		Object o = getHibernateTemplate().execute(
//			new HibernateCallback()
//			{
//				public Object doInHibernate(Session session)
//					throws HibernateException
//				{
//					Query query = session.createSQLQuery(sql);
//					//noAlias: no table alias
//					String fieldName;//multiple talble have the same column use column's alias
//					Iterator it = condition.keySet().iterator();
//					Object o;
//					while(it.hasNext()){
//						fieldName = it.next().toString();
//						o = condition.get(fieldName);
//						if( !(o instanceof java.util.List) ){
//							query.setParameter(fieldName, condition.get(fieldName));
//						}else{
//							query.setParameterList(fieldName, (List)condition.get(fieldName));
//						}
//					}
//					return query.setMaxResults(1).uniqueResult();
//				}
//			});
//		return o;
		return sqlQuerylistAllRetArrayByMap( sql , condition ).get( 0 );
	}
	
	/**
	 * 使用Native SQL 查询序列当前值
	 * @param sql 需要查询的sql语句
	 * @return 一个值对象
	 */
	public Long sqlQuerySequenceCURRVAL(final String sequence){
		return new Long(this.sqlQueryfindaValue("select " + sequence+".CURRVAL " +
				"from dual"
				).toString()
			);
	}
	/**
	 * 使用Native SQL 查询序列下一个可用值
	 * @param sql 需要查询的sql语句
	 * @return 一个值对象
	 */
	public Long sqlQuerySequenceNEXTVAL(final String sequence){
		return new Long(this.sqlQueryfindaValue("select " + sequence+".NEXTVAL " +
				"from dual"
				).toString()
			);
	}
	
	
	/**
	 * 使用Native SQL 查询当前值--用于MySQL
	 * @param sql 需要查询的sql语句
	 * @return 一个值对象
	 */
	public int sqlQueryAuto_increment(final String tableName){
		Map map = (Map)this.sqlQuerylistAll("show table status like '"+ tableName +"'").get(0);
		
		return Integer.valueOf( map.get("Auto_increment").toString() ).intValue();
	}
	/**
	 * 使用native sql 语句执行update或者delete
	 * @param sql 需要查询的sql语句
	 * @return 当前页的所有记录
	 */
	public int sqlQueryExeU(final String sql){
		Integer i = (Integer)getHibernateTemplate().execute(
			new HibernateCallback(){
				public Object doInHibernate(Session session)
					throws HibernateException
				{
					int result = session.createSQLQuery(sql).executeUpdate();
					return new Integer(result);
				}
			});
		return i.intValue();
	}

	/**
	 * 使用native sql 语句执行update或者delete
	 * @param sql 需要查询的sql语句
	 * @param values 如果sql有多个参数需要传入，values就是传入的参数数组
	 * @return 当前页的所有记录
	 */
	public int sqlQueryExeUByMap( final String sql, final Map condition ){
		if(condition == null){
			return sqlQueryExeU(sql);
		}
		
		log.debug( sql );
		Integer i = (Integer)this.getHibernateTemplate().execute(
			new HibernateCallback(){
				public Object doInHibernate(Session session)
					throws HibernateException{
					Query query = session.createSQLQuery(sql);
					
					//noAlias: no table alias
					String fieldName;//multiple table have the same column use column's alias
					Iterator it = condition.keySet().iterator();
					Object o;
					while(it.hasNext()){
						fieldName = it.next().toString();
						o = condition.get(fieldName);
//						log.debug("fieldName:"+fieldName+",value:"+o);
						
						Class type = o.getClass();
						if( !(o instanceof java.util.List) && !(type.isArray() ) ){
							query.setParameter(fieldName, condition.get(fieldName));
						}else if( type.isArray() ){
							Object[] array = (Object[])condition.get(fieldName);
							query.setParameter(fieldName+"0", array[0] );
							query.setParameter(fieldName+"1", array[1] );
						}else if( ((List)o).size() != 0 ){//
//							/** o instanceof java.util.List **/
							if(INIT.supportSetParameterList == null || Boolean.valueOf( INIT.supportSetParameterList ).booleanValue()){
								query.setParameterList(fieldName, (List)condition.get(fieldName));
							}else{
								//not support setParameterList
								List list = (List)o;
								for(int m=0, n=list.size(); m<n; m++){
									query.setParameter(fieldName+m, list.get(m) );
								}
							}
						}
					}
					int result = query.executeUpdate();
					return new Integer(result);
				}
			});
		return i.intValue();
	}
	
	/**
	 * 命名查询
	 * @param QueryName
	 * @param condition
	 * @return
	 */
	public List NamedQuery( final String QueryName ){
		List list = (List)getHibernateTemplate().execute(
				new HibernateCallback()
				{
					public Object doInHibernate(Session session)
						throws HibernateException
					{
						List result = session.getNamedQuery(QueryName)
								.list();
						return result;
					}
				});
			return list;
	}
	/**
	 * 命名查询
	 * @param QueryName
	 * @param condition
	 * @return
	 */
	public List NamedQuery( final String QueryName, final Map condition ){
		if(condition == null){
			return NamedQuery(QueryName);
		}
		
		List list = (List)getHibernateTemplate().execute(
			new HibernateCallback()
			{
				public Object doInHibernate(Session session)
					throws HibernateException
				{
					Query query = session.getNamedQuery(QueryName);
					
					//noAlias: no table alias
					String fieldName;//multiple talble have the same column use column's alias
					Iterator it = condition.keySet().iterator();
					Object o;
					while(it.hasNext()){
						fieldName = it.next().toString();
						o = condition.get(fieldName);
						Class type = o.getClass();
						if( !(o instanceof java.util.List) && !(type.isArray() ) ){
							query.setParameter(fieldName, condition.get(fieldName));
						}else if( type.isArray() ){
							Object[] array = (Object[])condition.get(fieldName);
							query.setParameter(fieldName+"0", array[0] );
							query.setParameter(fieldName+"1", array[1] );
						}else if( ((List)o).size() != 0 ){//
//							/** o instanceof java.util.List **/
							if(INIT.supportSetParameterList == null || Boolean.valueOf( INIT.supportSetParameterList ).booleanValue() ){
								query.setParameterList(fieldName, (List)condition.get(fieldName));
							}else{
								//not support setParameterList
								List list = (List)o;
								for(int m=0, n=list.size(); m<n; m++){
									query.setParameter(fieldName+m, list.get(m) );
								}
							}
						}
					}
					List result = query.list();
					return result;
				}
			});
		return list;
	}

	/**
	 * 调用存储过程
	 * @param sql eg: "{call testProc()}"
	 * @return
	 */
	public int callProcedure( final String sql ){
		log.debug( sql );
		Integer i =  (Integer)this.getHibernateTemplate().execute(
				new HibernateCallback(){
					public Object doInHibernate(Session session)
						throws HibernateException{
						
						CallableStatement cstat;
						ResultSet rs = null;
						int ret = -1;
						try {
							cstat = SessionFactoryUtils.getDataSource(getSessionFactory()).
									getConnection().prepareCall( sql );
//							CallableStatement cstat = session..connection().prepareCall( sql );
							rs = cstat.executeQuery();
							ret = new Integer(rs.getInt(1));
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						return ret;
						
//						return session.createSQLQuery( sql ).executeUpdate();
						
						/*org.hibernate.jdbc.Work work = new org.hibernate.jdbc.Work()
						session.doWork(work);*/
					}
				});
		return i.intValue();
	}
}