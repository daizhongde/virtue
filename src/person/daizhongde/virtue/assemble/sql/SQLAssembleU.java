package person.daizhongde.virtue.assemble.sql;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import person.daizhongde.virtue.configutils.ConfigDocument_SQL;
import person.daizhongde.virtue.sql.SQLManySwitch;
import person.daizhongde.virtue.util.field.FIELDUtil;

/**
 * 更新记录-单条/批量
 * <p>
 * Description:
 * <br/>网站: <a href="http://www.crazyjava.org">疯狂Java联盟</a> 
 * <br/>Copyright (C), 2013-2014, Dai zhongde 
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name: The daizhongde's super platform for development 
 * <br/>Date:20130726
 * assemble sql where condition, generate paramters value's array
 * <br/>update 2013/7/28 by dai zhongde
 * <br/> operator 0   1=   2!=    3>    4>=     5<     6<=   7like s% 8     9like %s  10  11like %s%  12    13
 * <br/> {" - 选择 - ","等于","不等于","大于","大于或等于","小于","小于或等于","开头是","开头不是","结尾是","结尾不是","包含","不包含"};    in (1,2,3)
 * <br/> {" - 选择 - ","等于","不等于","在以下日期之后","在以下日期之后或与之相同","在以下日期之前","在以下日期之前或与之相同","开头是","开头不是","结尾是","结尾不是","包含","不包含"};
 * <br/> {" - 选择 - ","等于","不等于","大于","大于或等于","小于","小于或等于"};
 * <br/>
 * <br/>
 * VIRTUE.update.jdata = { act: VIRTUE.act.UPDATE, data : {}, algorithm:{}, condition: {}, operator : {} };
 * @version 1.2 20150106 daizd 增加front2col为空的支持,即:front直接使用列名
 * @version 1.1
 *
 */
public class SQLAssembleU {
	private static Logger log = LogManager.getLogger(SQLAssembleU.class.getName() );
	
//	/** value in the config file<p>There only used when refer to Nest sql  **/
//	private AbstractConstant absc;
	/** virtue sql document  **/
	private ConfigDocument_SQL  sqlDoc;
	
	/** database schema name **/
	private String schema;
	
	/** database table name **/
	private String tableName;
	
	/*
	set order = order+1, level = 1

	data : { order:1, level:1 },
	algorithm : { order: VIRTUE.algorithm.PLUS }
*/
	/** data: {order : 1}  **/
	private Map data;
	/** algorithm: {order : plus}  **/
	private Map algorithm;
	/** parameter pass in by constructor<br>eg:{C_MNAME: 'system manage'} **/
	private Map condition;
	/** parameter pass in by constructor<br>eg:{C_MNAME: equal} **/
	private Map operator;//= != > >= < <=  like s%  like %s   like %s%
	/** parameter pass in by constructor , type: int value <br><String, Integer><br>eg:{C_MNAME: 12} **/
	private Map columnTypes;
	/** parameter pass in by constructor, front to column name<br>eg:{c_mname: 'C_MNAME'} **/
	private Map front2col;
	
	/** set col1=val1 */
	private String sqlSet = "";//is not contain set 
	private String whereBackSQL = "";// n_mid>1 and n_mlevel>1 and c_mname not like '%管理%'
	/**  target result sql **/
	private String SQL;
	
	/**Target SQL parameters map  **/
	private Map map = new HashMap();
	/** Object[] parameter invoke in dao can use, also reservered for extra use  **/
	private Object[] values = new Object[0];
	
	public SQLAssembleU( ConfigDocument_SQL  sqlDoc, String tableName, Map data, Map algorithm, Map condition, Map operator, Map columnTypes,  Map front2col  ){
		this.sqlDoc = sqlDoc;
		this.tableName = tableName;
		this.data = data;
		this.algorithm = algorithm;
		this.condition = condition;
		this.operator = operator;
		this.columnTypes = columnTypes;
		this.front2col = front2col;
		
		updateInitialize();

	}
	
	public SQLAssembleU( String schema, ConfigDocument_SQL  sqlDoc, String tableName, Map data, Map algorithm, Map condition, Map operator, Map columnTypes,  Map front2col  ){
		this.schema = schema;
		this.sqlDoc = sqlDoc;
		this.tableName = tableName;
		this.data = data;
		this.algorithm = algorithm;
		this.condition = condition;
		this.operator = operator;
		this.columnTypes = columnTypes;
		this.front2col = front2col;
		
		updateInitialize();

	}
	
	public void updateInitialize(){
		Object[] fields_Data = this.data.keySet().toArray();
		Object[] fields_Cond = this.condition.keySet().toArray();
		/* front: page post parameter, may have relevant table alias, or havn't
		 * frontNoAlias: page post parameter
		 * back: Back Field name, no alias
		 * backWithAlias:  Back Field with relevant table alias
		 * column: pure column name, no alias
		 * columnWithAlias: column name with table's alias
		 * parameter: SQL parameter
		 */
		String front, alias, frontNoAlias, column, columnWithAlias, parameter;
		
		int j=fields_Data.length, where_j=fields_Cond.length;
		
		this.values = new Object[j+where_j];
		/** set string **/
		for(int i = 0; i < j; i ++){
			front = fields_Data[i].toString();
			alias = FIELDUtil.getAlias(front);
			frontNoAlias = FIELDUtil.getFrontNoAlias(front);
						
			column = front2col==null ? frontNoAlias : front2col.get( frontNoAlias ).toString();
			columnWithAlias = alias + "." +column;
			
//			System.out.println("this.data.get(front).toString():"+this.data.get(front).toString());
			if( !this.data.get(front).toString().equalsIgnoreCase("null") ){
				/*
				 * To avoid duplication of parameter name, so add 'd_' in the front of the 'front'
				 *   eg:set order=order+1 where order between :order0 and :order1
				 */
				parameter = " :d_"+front+" ";//
				
				this.map.put( "d_"+front, this.data.get(front) );
				
				if( algorithm != null && algorithm.containsKey( front ) ){
					parameter = column + 
						SQLManySwitch.getAlgorithm( 
								new Integer(algorithm.get( front ).toString()).intValue()
						)  + parameter;
				}
				
				if(i == j-1){
					this.sqlSet += "`"+column + "`=" + parameter;
				}else{
					this.sqlSet += "`"+column + "`=" + parameter + ",";
				}
			}else{
				parameter = " null ";//
								
				if(i == j-1){
					this.sqlSet += "`"+column + "`=" + parameter;
				}else{
					this.sqlSet += "`"+column + "`=" + parameter + ",";
				}
			}
			
		}
		
		/* for other method access aviliable, muti-parameter */
		this.whereBackSQL = SQLUtil.getWhereBackSQL( this.condition, this.operator, 
				this.front2col, this.columnTypes,
				this.map, false, sqlDoc
			);
		
//		this.values = this.map.values().toArray();
		
		Object[] values1 = this.map.values().toArray();
		Object[] values2 = this.condition.values().toArray();

        int strLen1=values1.length;//保存第一个数组长度
        int strLen2=values2.length;//保存第二个数组长度
        values1= Arrays.copyOf(values1,strLen1+ strLen2);//扩容
        System.arraycopy(values2, 0, values1, strLen1,strLen2 );//将第二个数组与第一个数组合并
//    	System.out.println(Arrays.toString(values1));//输出数组
    	this.values = values2;
    	
//		update t_authority_module set c_mleaf='false', n_mlevel=2 where n_mid=82 and n_mlevel=2
//		this.SQL = "update " + this.absc.getTableName() + " t1 set " + this.sqlSet + " " +
		
		if( this.schema == null )
		{
			this.SQL = "update `" + tableName + "` set " + this.sqlSet + " " +
					"where " + this.whereBackSQL;
		}
		else
		{
			this.SQL = "update `" + this.schema + "`.`" + tableName + "` set " + this.sqlSet + " " +
					"where " + this.whereBackSQL;
		}
		log.debug( this.SQL );
	}
	
	public String getSchema() {
		return schema;
	}

	public void setSchema(String schema) {
		this.schema = schema;
	}

	public ConfigDocument_SQL getSqlDoc() {
		return sqlDoc;
	}

	public void setSqlDoc(ConfigDocument_SQL sqlDoc) {
		this.sqlDoc = sqlDoc;
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


	public Map getAlgorithm() {
		return algorithm;
	}

	public void setAlgorithm(Map algorithm) {
		this.algorithm = algorithm;
	}

	public Map getCondition() {
		return condition;
	}

	public void setCondition(Map condition) {
		this.condition = condition;
	}

	public Map getOperator() {
		return operator;
	}

	public void setOperator(Map operator) {
		this.operator = operator;
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

	public String getSqlSet() {
		return sqlSet;
	}

	public void setSqlSet(String sqlSet) {
		this.sqlSet = sqlSet;
	}

	public String getWhereBackSQL() {
		return whereBackSQL;
	}

	public void setWhereBackSQL(String whereBackSQL) {
		this.whereBackSQL = whereBackSQL;
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
//		String jdata = "{act:\"modify\",data : {nmid : 1,cmname : \"模块名称\", nmorder: 3, nmlevel : \"\"}, algorithm:{nmorder: 2},operator : {nmid : 1,cmname : 11,nmlevel : 13,cmtarget : 13}, condition:{nmid:1, cmname:\"模块名称\", nmlevel:'[1,2,3]', cmtarget:[\"R\",\"B\"]}}";
//		JSONObject jsonObject = JSONObject.fromObject(jdata);
//		AbstractConstant absConstant = new AuthorityModule();
//		SQLAssembleU sqlA = new SQLAssembleU( jsonObject, absConstant );
//		String WhereBackSQL = sqlA.getWhereBackSQL();
//		String sqlSet = sqlA.getSqlSet();
//		Object[] values= sqlA.getValues();
//		System.out.println("WhereBackSQL:"+WhereBackSQL);
//		System.out.println("sqlSet:"+sqlSet);
//	}
}