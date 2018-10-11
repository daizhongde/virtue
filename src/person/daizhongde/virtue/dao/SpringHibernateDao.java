package person.daizhongde.virtue.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
/**
 * 43个接口方法  4*10+3
 * --->25个接口方法  2*12+1
 * Three DAO types: Base DAO, Spring DAO, JNDI DAO
 * <p>Description:
 * <br/>网站: <a href="http://www.crazyjava.org">疯狂Java联盟</a> 
 * <br/>Copyright (C), 2011-2015, Dai zhongde 
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name: The daizhongde's super platform for development
 * <br/>Date:20110518
 * <br/>update 2013/8/27 by dai zhongde
 * @author  daizhongde dzd2746679@163.com 
 * @version  1.1
 */ 
public interface SpringHibernateDao {

//	public abstract Object findById(final Class clazz,final Serializable id);
//	public abstract Object findById(final String entityName,final Serializable id);
	/**
	 * 使用HQL 语句进行分页查询操作
	 * @param HQL 需要查询的HQL语句
	 * @param offset 第一条记录索引
	 * @param pageSize 每页需要显示的记录数
	 * @return 当前页的所有记录
	 */
	public abstract List findByPage(final String HQL, final int offset,
			final int pageSize);

	/**
	 * 使用HQL 语句进行分页查询操作
	 * @param HQL 需要查询的HQL语句
	 * @param condition 如果HQL有多个参数需要传入，values就是传入的参数数组
	 * @param offset 第一条记录索引
	 * @param pageSize 每页需要显示的记录数
	 * @return 当前页的所有记录
	 */
	public abstract List findByPageByMap(final String HQL, final Map condition,
			final int offset, final int pageSize);

	
	/**
	 * 使用HQL 语句进行查询操作
	 * @param HQL 需要查询的HQL语句
	 * @return 当前页的所有记录
	 */
	public abstract List listAll(final String HQL);
	/**
	 * 使用HQL 语句进行查询操作
	 * @param HQL 需要查询的HQL语句
	 * @param condition 如果HQL有多个参数需要传入，值就是传入的参数名值对
	 * @return 查询的所有记录(pojo类型)
	 */
	public abstract List listAllByMap( final String HQL,final Map condition );
	
	/**
	 * 使用HQL 查询语句进行单值查询操作
	 * @param HQL 需要查询的HQL语句
	 * @return 一个值对象
	 */
	public abstract Object findaValue(final String HQL);
	
	/**
	 * 使用HQL 查询语句进行单值查询操作
	 * @param HQL 需要查询的HQL语句
	 * @param condition
	 * @return 一个值对象
	 */
	public abstract Object findaValueByMap(final String HQL,
			final Map condition);
	
	/**
	 * 使用HQL 语句执行update或者delete
	 * @param HQL 需要查询的HQL语句
	 * @return 当前页的所有记录
	 */
	public abstract int exeU(final String HQL);

	/**
	 * 使用HQL 语句执行update或者delete
	 * @param HQL 需要查询的HQL语句
	 * @param condition 
	 * @return 当前页的所有记录
	 */
	public abstract int exeUByMap(final String HQL,final Map condition);
	
	/**
	 * 通过ID删除持久化对象
	 * @param clazz
	 * @param id
	 */
	public abstract void delete(final Class clazz,final Serializable id);
	
	/**
	 * 使用Native SQL 查询语句进行分页查询操作
	 * @param sql 需要查询的sql语句
	 * @param offset 第一条记录索引
	 * @param pageSize 每页需要显示的记录数
	 * @return 当前页的所有记录
	 */
	public abstract List sqlQueryfindByPage(final String sql, final int offset,
			final int pageSize);

	/**
	 * 使用Native SQL 查询 语句进行分页查询操作
	 * @param sql 需要查询的sql语句
	 * @param condition
	 * @param offset 第一条记录索引
	 * @param pageSize 每页需要显示的记录数
	 * @return 当前页的所有记录
	 */
	public abstract List sqlQueryfindByPageByMap(final String sql, final Map condition,
		 final int offset, final int pageSize);
	/**
	 * 使用Native SQL 查询语句进行分页查询操作
	 * @param sql 需要查询的sql语句
	 * @param offset 第一条记录索引
	 * @param pageSize 每页需要显示的记录数
	 * @return 当前页的所有记录
	 */
	public abstract List sqlQueryfindRetArrayByPage(final String sql, final int offset,
			final int pageSize);
	/**
	 * 使用Native SQL 查询 语句进行分页查询操作
	 * @param sql 需要查询的sql语句
	 * @param condition
	 * @param offset 第一条记录索引
	 * @param pageSize 每页需要显示的记录数
	 * @return 当前页的所有记录
	 */
	public abstract List sqlQueryfindRetArrayByPageByMap(final String sql, final Map condition,
		 final int offset, final int pageSize);
	/**
	 * 使用Native SQL 查询语句进行分页查询操作
	 * @param sql 需要查询的sql语句
	 * @param offset 第一条记录索引
	 * @param pageSize 每页需要显示的记录数
	 * @param c 数据实体
	 * @return 当前页的所有记录(pojo类型)
	 */
	public abstract List sqlQueryfindByPage(final String sql, 
		 final int offset, final int pageSize, final Class c);
	/**
	 * 使用natvie sql 语句进行查询操作
	 * @param sql 需要查询的sql语句
	 * @param condition 如果sql有多个参数需要传入，值就是传入的参数名值对
	 * @param offset 第一条记录索引
	 * @param pageSize 每页需要显示的记录数
	 * @param c 数据实体
	 * @return 查询的所有记录(pojo类型)
	 */
	public abstract List sqlQueryfindByPageByMap(final String sql,final Map condition,
			final int offset, final int pageSize, final Class c );
	/**
	 * 使用natvie sql 语句进行查询操作
	 * @param sql 需要查询的sql语句
	 * @return 查询的所有记录
	 */
	public abstract List sqlQuerylistAll(final String sql);

	/**
	 * 
	 * 使用natvie sql 语句进行查询操作
	 * <br>who used eg: buildComboboxData
	 * @param sql 需要查询的sql语句
	 * @param condition 如果sql有多个参数需要传入，值就是传入的参数名值对
	 * @return 查询的所有记录(map类型) list.size()=1
	 */
	public abstract List sqlQuerylistAllByMap( final String sql,final Map condition );
	/**
	 * 使用natvie sql 语句进行查询操作
	 * @param sql 需要查询的sql语句
	 * @return 查询的所有记录 array data
	 */
	public abstract List sqlQuerylistAllRetArray(final String sql);
	/**
	 * 使用natvie sql 语句进行查询操作
	 * @param sql 需要查询的sql语句
	 * @param condition 如果sql有多个参数需要传入，值就是传入的参数名值对
	 * @return 查询的所有记录  array data
	 */
	public abstract List sqlQuerylistAllRetArrayByMap( final String sql,final Map condition );
	/**
	 * 使用natvie sql 语句进行查询操作
	 * @param sql
	 * @param c
	 * @return 所有记录(pojo类型)
	 */
	public abstract List sqlQuerylistAll(final String sql,final Class c);
	/**
	 * 使用natvie sql 语句进行查询操作
	 * @param sql 需要查询的sql语句
	 * @param condition 如果sql有多个参数需要传入，值就是传入的参数名值对
	 * @return 所有记录(pojo类型)
	 */
	public abstract List sqlQuerylistAllByMap(final String sql,final Map condition,final Class c);
	
	/**
	 * 使用Native SQL 查询语句进行单值查询操作
	 * @param sql 需要查询的sql语句
	 * @return 一个值对象
	 */
	public abstract Object sqlQueryfindaValue(final String sql);

	/**
	 * 使用Native SQL 查询语句进行单值查询操作
	 * @param sql 需要查询的sql语句
	 * @param condition
	 * @return 一个值对象
	 */
	public abstract Object sqlQueryfindaValueByMap(final String sql, final Map condition );
	/**
	 * 使用Native SQL 查询序列当前值
	 * @param sql 需要查询的sql语句
	 * @return 一个值对象
	 */
	public abstract Long sqlQuerySequenceCURRVAL(final String sequence);
	/**
	 * 使用Native SQL 查询序列下一个可用值
	 * @param sql 需要查询的sql语句
	 * @return 一个值对象
	 */
	public abstract Long sqlQuerySequenceNEXTVAL(final String sequence);
	/**
	 * 使用Native SQL 查询当前值,用于MySQL
	 * @param sql 需要查询的sql语句
	 * @return 一个值对象
	 */
	public abstract int sqlQueryAuto_increment(final String tableName);
	/**
	 * 使用native sql 语句执行update或者delete
	 * @param sql 需要查询的sql语句
	 * @return 当前页的所有记录
	 */
	public abstract int sqlQueryExeU(final String sql);
	/**
	 * 使用native sql 语句执行update或者delete
	 * @param sql
	 * @param condition
	 * @return
	 */
	public abstract int sqlQueryExeUByMap( final String sql, final Map condition );
	
	/**
	 * 命名查询
	 * @param HQL
	 * @param condition
	 * @return
	 */
	public abstract List NamedQuery( final String HQL );
	/**
	 * 命名查询
	 * @param HQL
	 * @param condition
	 * @return
	 */
	public abstract List NamedQuery( final String HQL, final Map condition );

}