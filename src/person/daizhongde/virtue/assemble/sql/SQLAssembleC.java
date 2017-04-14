package person.daizhongde.virtue.assemble.sql;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import person.daizhongde.virtue.util.FIELDUtil;

/**
 * 单条新增
 * <p>
 * Description:
 * <br/>网站: <a href="http://www.crazyjava.org">疯狂Java联盟</a> 
 * <br/>Copyright (C), 2013-2014, Dai zhongde 
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name: The daizhongde's super platform for development 
 * <br/>Date:20130809
 * <br/>
 * @version 1.2 20150106 daizd 增加front2col为空的支持,即:front直接使用列名
 * @author dzd dzd2746679@163.com 
 * @version  1.1
 *
 */
public class SQLAssembleC {
	
	private static Logger log = LogManager.getLogger(SQLAssembleC.class.getName() );
	
	/** database schema name **/
	private String schema;
	
	/** database table name **/
	private String tableName;

	/** data: {order : 1}  **/
	private Map data;

	/** parameter pass in by constructor , type: int value <br><String, Integer><br>eg:{C_MNAME: 12} **/
	private Map columnTypes;
	/** parameter pass in by constructor, front to column name<br>eg:{c_mname: 'C_MNAME'} **/
	private Map front2col;
	
	private String columnNames = "";
	private String columnValues = "";
	
	/**  target result sql **/
	private String SQL;
	
	/**Target SQL parameters map  **/
	private Map map = new HashMap();
	/** Object[] parameter invoke in dao can use, also reservered for extra use  **/
	private Object[] values = new Object[0];
	
	/** only join sql in this class's method addInitialize<br/>for example: sequence can't setParameters **/
	private Map mapForSQLJoin = new HashMap();
	
	public SQLAssembleC( String tableName, Map data, Map columnTypes,  Map front2col  ){
		this.tableName = tableName;
		this.data = data;
		this.columnTypes = columnTypes;
		this.front2col = front2col;
		
		addInitialize();
	}
	public SQLAssembleC( String tableName, Map data, Map columnTypes,  Map front2col  , Map sqlString ){

		if( sqlString != null ){
			this.mapForSQLJoin = sqlString;
		}
		this.tableName = tableName;
		this.data = data;
		this.columnTypes = columnTypes;
		this.front2col = front2col;
		
		addInitialize();
	}
	public SQLAssembleC( String schema, String tableName, Map data, Map columnTypes,  Map front2col  ){
		this.schema = schema;
		this.tableName = tableName;
		this.data = data;
		this.columnTypes = columnTypes;
		this.front2col = front2col;
		
		addInitialize();
	}
	/**
	 * 
	 * @param schema
	 * @param tableName
	 * @param data
	 * @param columnTypes
	 * @param front2col
	 * @param sqlString 直接以name和value的形式拼接到sqlstring中
	 */
	public SQLAssembleC( String schema, String tableName, Map data, Map columnTypes,  Map front2col  , Map sqlString ){
		this.schema = schema;
		if( sqlString != null ){
			this.mapForSQLJoin = sqlString;
		}
		this.tableName = tableName;
		this.data = data;
		this.columnTypes = columnTypes;
		this.front2col = front2col;
		
		addInitialize();
	}
	public void addInitialize(){
		Object[] fields = this.data.keySet().toArray();
		/* front: page post parameter, may have relevant table alias, or havn't
		 * frontNoAlias: page post parameter
		 * back: Back Field name, no alias
		 * backWithAlias:  Back Field with relevant table alias
		 * column: pure column name, no alias
		 * columnWithAlias: column name with table's alias
		 * parameter: SQL parameter
		 */
		String front, alias, frontNoAlias, column, columnWithAlias, parameter;
		int j=fields.length;
		this.values = new Object[j];

		Iterator it = this.mapForSQLJoin.keySet().iterator();
//		Object o;
		while(it.hasNext()){
			column = it.next().toString();
//			o = this.map.get(column);
			this.columnNames += column + ",";
			this.columnValues += this.mapForSQLJoin.get(column) + ",";
		}
		
		for(int i=0; i<j; i++){
			front = fields[i].toString();
			alias = FIELDUtil.getAlias(front);
			frontNoAlias = FIELDUtil.getFrontNoAlias(front);
			
			log.debug("front:"+front+",alias:"+alias+",frontNoAlias:"+frontNoAlias);
			
			column = null == front2col ? frontNoAlias : front2col.get( frontNoAlias ).toString();
			columnWithAlias = alias + "." +column;
			
			parameter = " :"+front+" ";
			
			this.map.put( front, this.data.get(front) );
			
			if(i == j-1){
				this.columnNames += column;
				this.columnValues += parameter;
			}else{
				this.columnNames += column + ",";
				this.columnValues += parameter + ",";
			}
			
		}
		this.values = this.map.values().toArray();
//		insert into T_AUTHORITY_MODULE (n_mid, c_mname, n_mlevel, c_mleaf, n_morder, n_mparent, c_mtarget, c_miconcls, c_mexpanded, c_mchecked, c_mpath, c_mnote, c_mctime, c_mcip, n_mcuser, c_mmtime, c_mmip, n_mmuser)
//		values (1, '湖南社保金融卡省内前置系统', 0, 'false', 1, null, 'R', 'icon-web', 'true', 'false', null, '系统名称', to_date('22-07-2013 11:23:01', 'dd-mm-yyyy hh24:mi:ss'), null, null, null, null, null);
//		this.SQL = "insert into " + this.absc.getTableName() + " (n_mid, " + this.columnNames + ") " +
//				"values (SEQ_AUTHORITY_MODULE_ID.nextval," + this.columnValues + ")";
		
		if( this.schema == null )
		{
			this.SQL = "insert into " + tableName + " (" + this.columnNames + ") " +
					"values (" + this.columnValues + ")";
		}
		else
		{
			this.SQL = "insert into " + this.schema + "." + tableName + " (" + this.columnNames + ") " +
					"values (" + this.columnValues + ")";
		}
		
		log.debug( this.SQL );
	}

	public String getSchema() {
		return schema;
	}
	public void setSchema(String schema) {
		this.schema = schema;
	}
	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public Map getData() {
		return data;
	}

	public void setData(Map data) {
		this.data = data;
	}

	public Map getColumnTypes() {
		return columnTypes;
	}

	public void setColumnTypes(Map columnTypes) {
		this.columnTypes = columnTypes;
	}

	public Map getFront2col() {
		return front2col;
	}

	public void setFront2col(Map front2col) {
		this.front2col = front2col;
	}

	public String getColumnNames() {
		return columnNames;
	}

	public void setColumnNames(String columnNames) {
		this.columnNames = columnNames;
	}

	public String getColumnValues() {
		return columnValues;
	}

	public void setColumnValues(String columnValues) {
		this.columnValues = columnValues;
	}

	public String getSQL() {
		return SQL;
	}

	public void setSQL(String sQL) {
		SQL = sQL;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public Object[] getValues() {
		return values;
	}

	public void setValues(Object[] values) {
		this.values = values;
	}

//	public static void main(String args[]){
//		String jdata = "{act : \"add\",	data : {nmid : 1,cmname : \"模块名称\",	nmlevel : \"\"}}";
//		JSONObject jsonObject = JSONObject.fromObject(jdata);
//		AbstractConstant absConstant = new TAuthorityModule_Constant();
//		SQLAssembleC sqlA = new SQLAssembleC(jsonObject, absConstant );
//		String SQL = sqlA.getSQL();
//		Object[] values= sqlA.getValues();
//		System.out.println("SQL:\n"+SQL);
//	}
}