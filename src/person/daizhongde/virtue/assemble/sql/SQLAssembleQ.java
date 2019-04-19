package person.daizhongde.virtue.assemble.sql;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import person.daizhongde.virtue.codec.SHA1_Encoding;
import person.daizhongde.virtue.configutils.ConfigDocument_SQL;
import person.daizhongde.virtue.constant.Lic;
import person.daizhongde.virtue.sigar.ComputerIdentifier;

/**
 * 查询
 * <p>
 * Description:
 * <br/>网站: <a href="http://www.crazyjava.org">疯狂Java联盟</a> 
 * <br/>Copyright (C), 2013-2014, Dai zhongde 
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name: The daizhongde's super platform for development
 * <br/>Date:20130726
 * assemble sql where condition, generate paramters value's array
 * <br/>update 2013/9/24 by dai zhongde
 * <br/> operator 0   1=   2!=    3>    4>=     5<     6<=   7like s% 8     9like %s  10  11like %s%  12   
 * <br/> {" - 选择 - ","等于","不等于","大于","大于或等于","小于","小于或等于","开头是","开头不是","结尾是","结尾不是","包含","不包含"};    
 * <br/> {" - 选择 - ","等于","不等于","在以下日期之后","在以下日期之后或与之相同","在以下日期之前","在以下日期之前或与之相同","开头是","开头不是","结尾是","结尾不是","包含","不包含"};
 * <br/> {" - 选择 - ","等于","不等于","大于","大于或等于","小于","小于或等于"};
 * <br/>
 * <br/>
 * 这里都是需要有front2Column映射关系的 
 * @modify 2014/3/5
 * @author dzd dzd2746679@163.com 
 * @version  1.1
 *
 */
public class SQLAssembleQ {
	
	private static String lic = "";
	static {
		String identifier="";
		try {
			identifier = ComputerIdentifier.generateLicenseKey();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lic = SHA1_Encoding.toSHA1(identifier+"82019102");
	}
	public static void check(String locallic){
//		if(!locallic.equalsIgnoreCase(lic)){
//			throw new RuntimeException("请购买正版license!QQ:413881461;公众号：德软集团");
//		}
		if(lic.equalsIgnoreCase("0")){
			throw new RuntimeException("请购买正版license!QQ:413881461;公众号：德软集团");
		}else if(Integer.valueOf(locallic+"1028")
				<Integer.valueOf((new SimpleDateFormat("yyyyMMdd")).format(new Date())) ){
			throw new RuntimeException("license已过期!购买 QQ:413881461;公众号：德软集团");
		}
	}
	
	private static Logger log = LogManager.getLogger(SQLAssembleQ.class.getName() );
	
//	/** value in the config file<p>There only used when refer to Nest sql  **/
//	private AbstractConstant absc;
	/** virtue sql document  **/
	private ConfigDocument_SQL  sqlDoc;
	
	/** database schema name **/
	private String schema;
	
	/** parameter pass in by constructor,usually config in *.sql file **/
	private String selectSQL;
	/** parameter pass in by constructor<br>eg:{C_MNAME: 'system manage'} **/
	private Map condition;
	/** parameter pass in by constructor<br>eg:{C_MNAME: equal} **/
	private Map operator;//= != > >= < <=  like s%  like %s   like %s%
	/** parameter pass in by constructor , type: int value <br><String, Integer><br>eg:{C_MNAME: 12} **/
	private Map columnTypes;
	/** parameter pass in by constructor, front to column name<br>eg:{c_mname: 'C_MNAME'} **/
	private Map front2col;
	
	/** 
	 * <p>dojo style
	 * <br>[{attribute: "LEVEL1", descending: true},{attribute: "LEAF", descending: false}]
	 * <br>[{attribute: "LEVEL1"}] 
	 * <br>descending: default: false 默认情况下，数值排序是升序的
	 * <br>default: asc
	 * <p>YUI3 style
	 * <br>[ { LEVEL1:'desc'  }, { LEAF:'asc'} ]
	 * 
	 * <p>paremeter:
	 * <br>dojo: -LEVEL1,LEAF 
	 * <br>YUI3: [ { LEVEL1:'desc'  }, { LEAF:'asc'} ]
	 *    **/
	private List sort;
//	@SuppressWarnings("rawtypes")
//	private List<Map> sort;
//	private String[] sort;
	
	private String whereBackSQL = "";// n_mid>1 and n_mlevel>1 and c_mname not like '%管理%'
	/**  target result sql **/
	private String SQL;
	/**  target count sql **/
	private String countSQL;
	
	/**Target SQL parameters map  **/
	private Map map = new HashMap();
	/** Object[] parameter invoke in dao can use, also reservered for extra use  **/
	private Object[] values = new Object[0];
	
	private String onlyParams;
	
	public SQLAssembleQ(){
		
	};
	/**  
	 * No pagegation 
	 * <p>
	 * with front name to database table column name's convert
	 * @param jsonObject
	 * @param absConstant
	 */
	public SQLAssembleQ(ConfigDocument_SQL  sqlDoc, String selectSQL, Map condition, Map operator, 
			Map columnTypes,  Map front2col){
		this.sqlDoc = sqlDoc;
		this.selectSQL = selectSQL;
		this.condition = condition;
		this.operator = operator;
		this.columnTypes = columnTypes;
		this.front2col = front2col;
		
		queryInitialize();
	}
	/**
	 * with pagegation 
	 * <p>
	 * with front name to database table column name's convert
	 * @param selectSQL
	 * @param condition
	 * @param operator
	 * @param columnTypes
	 * @param front2col
	 * @param sort
	 */
	public SQLAssembleQ(ConfigDocument_SQL  sqlDoc, String selectSQL, Map condition, Map operator, 
			Map columnTypes,  Map front2col, List sort){
		this.sqlDoc = sqlDoc;
		this.selectSQL = selectSQL;
		this.condition = condition;
		this.operator = operator;
		this.columnTypes = columnTypes;
		this.front2col = front2col;
		this.sort = sort;
		
		queryInitialize();
	}
	/**
	 * with pagegation 
	 * <p>
	 * with front name to database table column name's convert
	 * @param selectSQL
	 * @param condition
	 * @param operator
	 * @param columnTypes
	 * @param front2col
	 * @param sort
	 * @param exceptColumns only as parameter, don't need assemble where conditon
	 */
	public SQLAssembleQ(ConfigDocument_SQL  sqlDoc, String selectSQL, Map condition, Map operator, 
			Map columnTypes,  Map front2col, List sort, String onlyParams){
		this.sqlDoc = sqlDoc;
		this.selectSQL = selectSQL;
		this.condition = condition;
		this.operator = operator;
		this.columnTypes = columnTypes;
		this.front2col = front2col;
		this.sort = sort;
		this.onlyParams = onlyParams;
		
		queryInitialize();

	}
	public void queryInitialize(){
		SQLAssembleQ.check(String.valueOf( Lic.getYear()));
//		System.out.println("####################################################");
//		System.out.println("##whereBackSQL:"+whereBackSQL);
//		System.out.println("##countSQL:"+countSQL);
//		System.out.println("##SQL:"+SQL);
//		System.out.println("##values.length:"+values.length);
//		System.out.println("##map.size():"+map.size());
		String fromSQL = SQLUtil.getFromSQL(selectSQL);
		
		boolean withAlias = fromSQL.indexOf(" t1 ")==-1 && !fromSQL.endsWith(" t1") ?false:true;
				
		if(this.condition != null && this.condition.size() != 0){
			/* for other method access aviliable, muti-parameter */
			this.whereBackSQL = SQLUtil.getWhereBackSQL2( this.condition, this.operator, 
					this.front2col, this.columnTypes,
					this.map, withAlias, sqlDoc, onlyParams
				);
		}
		
		this.values = this.map.values().toArray();
		
		if( this.condition == null || this.condition.size() == 0 ){
			this.countSQL = "select count(*) " + fromSQL;
			this.SQL = selectSQL;
		}else{
			if( SQLUtil.isConditionNeedWhere(selectSQL) ){
				this.countSQL = "select count(*) " + fromSQL + " " + "where " + this.whereBackSQL;
				this.SQL = selectSQL + " where " + this.whereBackSQL;
			}else{
				this.countSQL = "select count(*) " + fromSQL + " and "+ this.whereBackSQL;
				this.SQL = selectSQL + " and " + this.whereBackSQL;
			}
		}
		
		this.SQL += SQLUtil.getOrderSQL( this.sort );
//		System.out.println("SQLA_Q countSQL:"+countSQL);
		System.out.println("SQLA_Q SQL:"+SQL);
	}
	
	public ConfigDocument_SQL getSqlDoc() {
		return sqlDoc;
	}
	public void setSqlDoc(ConfigDocument_SQL sqlDoc) {
		this.sqlDoc = sqlDoc;
	}
	
	public String getSchema() {
		return schema;
	}
	public void setSchema(String schema) {
		this.schema = schema;
	}
	public String getSelectSQL() {
		return selectSQL;
	}

	public void setSelectSQL(String selectSQL) {
		this.selectSQL = selectSQL;
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

	public List getSort() {
		return sort;
	}

	public void setSort(List sort) {
		this.sort = sort;
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

	public String getCountSQL() {
		return countSQL;
	}

	public void setCountSQL(String countSQL) {
		this.countSQL = countSQL;
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
	public String getOnlyParams() {
		return onlyParams;
	}
	public void setOnlyParams(String onlyParams) {
		this.onlyParams = onlyParams;
	}

//	public static void main(String args[]){
//		String jdata = "{act:\"query\",	operator : {'t1.nmid' : 14,cmname : 11,nmlevel : 13,cmtarget : 14}, condition:{'t1.nmid':[1,3], cmname:\"模块名称\", nmlevel:[1,2,3], cmtarget:[\"R\",\"B\"]}}";
//		JSONObject jsonObject = JSONObject.fromObject(jdata);
//		AbstractConstant absConstant = new AuthorityModule();
//		List<Map> sort = new ArrayList<Map>();
//		
//		SQLAssembleQ sqlA = new SQLAssembleQ(jsonObject, absConstant, sort );
//		String countSQL = sqlA.getCountSQL();
//		String SQL = sqlA.getSQL();
//		Object[] values= sqlA.getValues();
//		System.out.println("countSQL:\n"+countSQL);
//		System.out.println("SQL:\n"+SQL);
//	}
}