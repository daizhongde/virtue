package person.daizhongde.virtue.spring;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import person.daizhongde.virtue.assemble.hql.HQLAssembleQ;
import person.daizhongde.virtue.assemble.sql.SQLAssembleQ;

public interface BaseService2<E extends Serializable, ID extends Serializable> {
	/**
	 * 分页查询时用到, parameter sqlA pass in, because of it can only assemble a time
	 * @param sqlA
	 * @return 记录总条数
	 */
	public abstract long getTotal(SQLAssembleQ sqlA);
	/**
	 * 分页查询时用到, parameter sqlA pass in, because of it can only assemble a time
	 * @param hqlA
	 * @return 记录总条数
	 */
	public abstract long getTotal(HQLAssembleQ hqlA);
	
	/**
	 * 查询, parameter sqlA pass in
	 * @param sqlA
	 * @return 指定页的所有行
	 */
	public abstract List getRowsInMap( SQLAssembleQ sqlA );
	/**
	 * 查询, parameter sqlA pass in
	 * @param hqlA
	 * @return 指定页的所有行
	 */
	public abstract List getRowsInMap( HQLAssembleQ hqlA );
	/**
	 * 分页查询, parameter sqlA pass in, because of it can only assemble a time
	 * @param hqlA
	 * @param offset
	 * @param pageSize
	 * @return 指定页的所有行
	 */
	public abstract List getRowsInMap(SQLAssembleQ hqlA, int offset, int pageSize);
	/**
	 * 分页查询, parameter sqlA pass in, because of it can only assemble a time
	 * @param sqlA
	 * @param offset
	 * @param pageSize
	 * @return 指定页的所有行
	 */
	public abstract List getRowsInMap(HQLAssembleQ hqlA, int offset, int pageSize);
	/**
	 * 分页查询, parameter sqlA pass in, because of it can only assemble a time
	 * @param sqlA
	 * @return 指定页的所有行
	 */
	public abstract List getRowsInArray(SQLAssembleQ sqlA);
	/**
	 * 分页查询, parameter sqlA pass in, because of it can only assemble a time
	 * @param sqlA
	 * @param offset
	 * @param pageSize
	 * @return 指定页的所有行
	 */
	public abstract List getRowsInArray(SQLAssembleQ sqlA, int offset, int pageSize);

	/** C sometime the implements need modify manually **/
	public abstract int add( String jdata );
	/** 
	 * 新增记录并返回记录ID
	 * <p>normal:
	 * <br>1.保存记录
	 * <br>2.查询记录ID
	 * <br>3.返回记录ID
	 * <p>not all table id use sequence, so the method for get id write in service  **/
	public abstract int addRetId( String jdata );
	
	/** remain for third part interface
	 * <br>because of addWithId is less than add, addWithId method name is longer than add **/
	public abstract int addWithId( String jdata );
	
	public abstract int addWithIdRetId( String jdata );
	
	public abstract void addBySavePOJO( String jdata );
	public abstract void addBySavePOJO2( E pojo );
	
//	public abstract void curdAllinOne( Object[] rows );
	
	/** C sometime the implements need modify manually 
	 * @deprecated
	 * **/
	public abstract int add( Map data );
	/** U  **/
	public abstract int modify( String jdata );
	/** R  **/
	public abstract Map browse( String jdata );
	public abstract Map browseById( int id );
	public abstract Map browseById( String id );
	/**
	 * @deprecated
	 * @param jdata
	 * @return
	 */
	public abstract Object[] browseArray( String jdata );
	
	public abstract E browsePOJO( String jdata );
//	/**
//	 * 
//	 * @param id 实例id
//	 * @return
//	 */
//	public abstract E browsePOJOById( int id );
//	public abstract E browsePOJOById( String id );
	
	/** D,permanent delete **/
	public abstract int delete( String jdata );
	/** NP:not permanent, just update delete flag  **/
	public abstract int deleteNP( String jdata );

}
