package person.daizhongde.virtue.assemble.hql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import person.daizhongde.virtue.configutils.ConfigDocument_SQL;

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
public class HQLAssembleQ {
	
	private static Logger log = LogManager.getLogger(HQLAssembleQ.class.getName() );
	
//	/** value in the config file<p>There only used when refer to Nest sql  **/
//	private AbstractConstant absc;
	/** virtue sql document  **/
	private ConfigDocument_SQL  sqlDoc;
	
	/** parameter pass in by constructor,usually config in *.sql file **/
	private String selectHQL;
	/** parameter pass in by constructor<br>eg:{C_MNAME: 'system manage'} **/
	private Map condition;
	/** parameter pass in by constructor<br>eg:{C_MNAME: equal} **/
	private Map operator;//= != > >= < <=  like s%  like %s   like %s%
	/** parameter pass in by constructor , type: int value <br><String, Integer><br>eg:{C_MNAME: 12} **/
	private Map columnTypes2;
	/** parameter pass in by constructor, front to column name<br>eg:{c_mname: 'C_MNAME'} **/
	private Map front2back;
	
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
	
	private String whereBackHQL = "";// n_mid>1 and n_mlevel>1 and c_mname not like '%管理%'
	/**  target result sql **/
	private String HQL;
	/**  target count sql **/
	private String countHQL;
	
	/**Target HQL parameters map  **/
	private Map map = new HashMap();
	/** Object[] parameter invoke in dao can use, also reservered for extra use  **/
	private Object[] values = new Object[0];
	
	/**  
	 * No pagegation 
	 * <p>
	 * with front name to database table column name's convert
	 * @param jsonObject
	 * @param absConstant
	 */
	public HQLAssembleQ(ConfigDocument_SQL  sqlDoc, String selectHQL, Map condition, Map operator, Map columnTypes2,  Map front2back){
		this.sqlDoc = sqlDoc;
		this.selectHQL = selectHQL;
		this.condition = condition;
		this.operator = operator;
		this.columnTypes2 = columnTypes2;
		this.front2back = front2back;
		
		queryInitialize();
	}
	/**
	 * with pagegation 
	 * <p>
	 * with front name to database table column name's convert
	 * @param selectHQL
	 * @param condition
	 * @param operator
	 * @param columnTypes2
	 * @param front2back
	 * @param sort
	 */
	public HQLAssembleQ(ConfigDocument_SQL  sqlDoc, String selectHQL, Map condition, Map operator, Map columnTypes2,  Map front2back, List sort){
		this.selectHQL = selectHQL;
		this.condition = condition;
		this.operator = operator;
		this.columnTypes2 = columnTypes2;
		this.front2back = front2back;
		this.sort = sort;
		
		queryInitialize();
	}
	
	public void queryInitialize(){
//		System.out.println("####################################################");
//		System.out.println("##whereBackHQL:"+whereBackHQL);
//		System.out.println("##countHQL:"+countHQL);
//		System.out.println("##HQL:"+HQL);
//		System.out.println("##values.length:"+values.length);
//		System.out.println("##map.size():"+map.size());
		String fromHQL = HQLUtil.getFromHQL(selectHQL);
		
		boolean withAlias = fromHQL.indexOf(" t1 ")==-1?false:true;
		
		/* for other method access aviliable, muti-parameter */
		this.whereBackHQL = HQLUtil.getWhereBackHQL( this.condition, this.operator, 
				this.front2back, this.columnTypes2,
				this.map, withAlias, sqlDoc
			);
		
		this.values = this.map.values().toArray();
		
		if( this.condition == null || this.condition.size() == 0 ){
			this.countHQL = "select count(*) " + fromHQL;
			this.HQL = selectHQL;
		}else{
			if( fromHQL.indexOf(" where ") != -1){
				this.countHQL = "select count(*) " + fromHQL + " and "+ this.whereBackHQL;
				this.HQL = selectHQL + " and " + this.whereBackHQL;
			}else{
				this.countHQL = "select count(*) " + fromHQL + " " + "where " + this.whereBackHQL;
				this.HQL = selectHQL + " where " + this.whereBackHQL;
			}
		}
		
		this.HQL += HQLUtil.getOrderHQL(this.sort);
//		System.out.println("HQLA_Q countHQL:"+countHQL);
//		System.out.println("HQLA_Q HQL:"+HQL);
	}
	
	public ConfigDocument_SQL getSqlDoc() {
		return sqlDoc;
	}
	public void setSqlDoc(ConfigDocument_SQL sqlDoc) {
		this.sqlDoc = sqlDoc;
	}
	public String getSelectHQL() {
		return selectHQL;
	}

	public void setSelectHQL(String selectHQL) {
		this.selectHQL = selectHQL;
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

	public Map getColumnTypes2() {
		return columnTypes2;
	}

	public void setColumnTypes2(Map columnTypes2) {
		this.columnTypes2 = columnTypes2;
	}

	public Map getFront2back() {
		return front2back;
	}

	public void setFront2back(Map front2back) {
		this.front2back = front2back;
	}

	public List getSort() {
		return sort;
	}

	public void setSort(List sort) {
		this.sort = sort;
	}

	public String getWhereBackHQL() {
		return whereBackHQL;
	}

	public void setWhereBackHQL(String whereBackHQL) {
		this.whereBackHQL = whereBackHQL;
	}

	public String getHQL() {
		return HQL;
	}

	public void setHQL(String sQL) {
		HQL = sQL;
	}

	public String getCountHQL() {
		return countHQL;
	}

	public void setCountHQL(String countHQL) {
		this.countHQL = countHQL;
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
//		String jdata = "{act:\"query\",	operator : {'t1.nmid' : 14,cmname : 11,nmlevel : 13,cmtarget : 14}, condition:{'t1.nmid':[1,3], cmname:\"模块名称\", nmlevel:[1,2,3], cmtarget:[\"R\",\"B\"]}}";
//		JSONObject jsonObject = JSONObject.fromObject(jdata);
//		AbstractConstant absConstant = new AuthorityModule();
//		List<Map> sort = new ArrayList<Map>();
//		
//		HQLAssembleQ sqlA = new HQLAssembleQ(jsonObject, absConstant, sort );
//		String countHQL = sqlA.getCountHQL();
//		String HQL = sqlA.getHQL();
//		Object[] values= sqlA.getValues();
//		System.out.println("countHQL:\n"+countHQL);
//		System.out.println("HQL:\n"+HQL);
//	}
}