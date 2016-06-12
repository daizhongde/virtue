package person.daizhongde.virtue.assemble.jpql;

import java.sql.Types;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import person.daizhongde.virtue.configutils.ConfigDocument_SQL;
import person.daizhongde.virtue.configutils.SQLNode;
import person.daizhongde.virtue.constant.Operator;
import person.daizhongde.virtue.util.FIELDUtil;
import person.daizhongde.virtue.util.SQLManySwitch;

public class JPQLUtil {
	private static Logger log = Logger.getLogger(JPQLUtil.class.getClass());
	
	public static String getFromJPQL(String selectJPQL) {
		
		int query_JPQL_fromIndex = selectJPQL.toLowerCase().indexOf("from ");
		/**  from t where a='f' and b='cf', include 'from' keywords **/
		String query_JPQL_fromJPQL = selectJPQL.substring( query_JPQL_fromIndex );
		return query_JPQL_fromJPQL;
	}
	/**
	 * 
	 * @param condition eg:{C_MNAME: 'system manage'}
	 * @param operator eg:{C_MNAME: equal}
	 * @param front2col eg:{c_mname: 'C_MNAME'}
	 * @param columnTypes eg:{C_MNAME: 12}
	 * @param map Target JPQL parameters map
	 * @param withAlias Wethether table name with alias
	 * @return
	 */
	public static String getWhereBackJPQL( Map condition, Map operator, 
			Map front2back, Map columnTypes,
			Map map, boolean withAlias, ConfigDocument_SQL SQLDOC
			 ){
		String whereBackJPQL = "";
		/* Use JSONArray because it has order */
		Set fields = condition.keySet();
		Iterator it = fields.iterator();
		/* front: page post parameter, may have relevant table alias, or havn't
		 * frontNoAlias: page post parameter
		 * back: Back Field name, no alias
		 * backWithAlias:  Back Field with relevant table alias
		 * column: pure column name, no alias
		 * columnWithAlias: column name with table's alias
		 * parameter: JPQL parameter
		 */
		String front, alias, frontNoAlias, column, columnWithAlias, parameter;
		int j=fields.size(), operatorIndex;
		Object[] values = new Object[j];
		int i = 0;
		while( it.hasNext() ){
			front = it.next().toString();
			alias = FIELDUtil.getAlias(front);
			frontNoAlias = FIELDUtil.getFrontNoAlias(front);
			
//			back = absc.getFront2back().get( frontNoAlias );
//			backWithAlias = alias + "." +back;

			System.out.println("front:"+front);
			if( alias.trim().toLowerCase().equalsIgnoreCase("t1") )
			{
				column = front2back.get( frontNoAlias ).toString();
				
//				System.out.println("column:"+column);
				columnWithAlias = alias + "." +column;
				
				//Operator.EQUAL;//defalut: equal     //ConfigConstants.defaultOperatorIndex;
				operatorIndex = ( operator != null && operator.get(front) != null ) ? 
						new Integer(
								operator.get(front).toString()
						).intValue()
						: Operator.EQUAL;//defalut: equal
						
				//switch to deal with,if have problem rise in switch class
				values[i] = SQLManySwitch.getParameterValue( operatorIndex , 
						new Integer(
								columnTypes.get( column.toUpperCase() ).toString()
						).intValue(),
						condition.get(front) );
				
				//use front to avoid duplication of parameter name
				map.put( front, values[i] );
				
				if(  new Integer( columnTypes.get(column.toUpperCase()).toString() ).intValue()  == Types.TIMESTAMP 
						&& operatorIndex == Operator.EQUAL ){
					//EQUAL isn't recommend,Use between instead 
					// Types.TIMESTAMP && Operator.EQUAL 
					//to_char( d_xfrq , 'yyyymmdd' ) = 20130914
					//to_date(to_char(d_xfrq, 'yyyymmdd'), 'yyyymmdd') = ? //java.sql.date
					column = "to_date(to_char( " + column + " , 'yyyymmdd'), 'yyyymmdd')";
					columnWithAlias = "to_date(to_char( " + columnWithAlias + " , 'yyyymmdd'), 'yyyymmdd')";
					
				}else if( operatorIndex == Operator.EXISTS
							|| operatorIndex == Operator.NOTEXISTS ){
					column = "";
					columnWithAlias = "";
				}
			}
			else
			{
				column = front;
				columnWithAlias = alias + "." +column;
				
				//Operator.EQUAL;//defalut: equal     //ConfigConstants.defaultOperatorIndex;
				operatorIndex = ( operator != null && operator.get(front) != null ) ? 
						new Integer(
								operator.get(front).toString()
							).intValue()
						: Operator.EQUAL;//defalut: equal
				//switch to deal with,if have problem rise in switch class
				values[i] = condition.get(front);
				//use front to avoid duplication of parameter name
				map.put( front, values[i] );
				//No date type process, remain to improve......
			}
			
			
			switch( operatorIndex ){
				case 1: case 2: case 3: case 4: case 5: 
				case 6: case 7: case 8: case 9: case 10: 
				case 11: case 12: 
					parameter = " :"+front+" ";
					break;
				case Operator.BETWEEN:
				case Operator.NOTBETWEEN: 
					parameter = " :"+front+"0 and "+" :"+front + "1 ";
					break;
				case Operator.IN: 
				case Operator.NOTIN: 
					/* condition:{ nmparent : [ 'nest_JPQL']} 
					 * list.size() == 0
					 *  */
					List list = (List)values[i];
					if( list.size() != 0 ){
						
						parameter = " ( :"+front+" ) ";
						
					}else{
//						parameter = "(" + absc.getNest_JPQL() + ")";//nest JPQL, 
//						nmparent : [ 'nest_JPQL1'],//exists,exists1,exists2.....(nestJPQL现在假定没有参数)
//						nmexpanded : [ 'nest_JPQL2'],//nest_JPQL,nest_JPQL1,nest_JPQL2...
//						JPQLMAP.get("nest.nest.JPQL"");
						
						List o = (List)condition.get(front);
						String nest = o.get(0).toString();
//						String nestKey = "nest."+o.get(0).toString().replaceFirst("_", ".");
//						parameter = "(" + absc.getJPQLMAP().get( nestKey ) + ")";//nest JPQL, 
						parameter = "(" + ((SQLNode)( SQLDOC.getNest().get(nest) )).getJPQL() + ")";//nest JPQL,
						/*  There nestJPQL don't support setParameter
						 *  After a time a you meet .....you develop .......extend....
						 *  **/
					}
					break;
				case Operator.EXISTS:
				case Operator.NOTEXISTS: 
//					parameter = "(" + absc.getNest_JPQL() + ")";//nest JPQL,
					List o = (List)condition.get(front);
					String nest = o.get(0).toString();
//					String nestKey = "nest."+o.get(0).toString().replaceFirst("_", ".");
//					parameter = "(" + absc.getJPQLMAP().get( nestKey ) + ")";//nest JPQL, 
					parameter = "(" + ((SQLNode)( SQLDOC.getNest().get(nest))).getJPQL() + ")";//nest JPQL,
					break;
					
				default : 
					parameter = " :"+front+" ";
			}
			
			columnWithAlias = withAlias?columnWithAlias:column;
			
			if(i == 0){
				whereBackJPQL = columnWithAlias + " " +
						SQLManySwitch.getOperator( operatorIndex ) + parameter;
			}else{
				whereBackJPQL += "and " + columnWithAlias + " " +
						SQLManySwitch.getOperator( operatorIndex ) + parameter;
			}
			i++;
		}//end of for field.size()
		
		log.debug( whereBackJPQL );
		return whereBackJPQL;
	}

	/**
	 * post:
	 *     sort: [ { LEVEL:'desc'  }, { LEAF:'asc'} ]  -- YUI3 style
	 * JPQL:
	 *     order by LEVEL desc, LEAF asc
	 * @param sort
	 * @return
	 */
	public static String getOrderJPQL(List sort){
		String JPQL = "";
		/*//order by LEVEL1 desc, LEAF asc , dojo style
		//[{attribute: "LEVEL1", descending: true},{attribute: "LEAF", descending: false}]
		if( sort != null && sort.size() != 0 ){
			JPQL += " order by ";
			boolean b;
			for( int m = 0, n = sort.size(); m < n; m ++ ){
				if( sort.get( m ).get("descending") == null ){//default asc
					b = false;
				}else{
					b = (Boolean)sort.get( m ).get("descending");
				}
				if( m != n - 1){
					JPQL += sort.get( m ).get("attribute") + " " + 
							( b ? "desc" : "asc" ) + ", ";
				}else{
					JPQL += sort.get( m ).get("attribute") + " " + 
						    ( b ? "desc" : "asc" );
				}
			}
		}*/
		/* post:
		 *     order: [ { LEVEL:'desc'  }, { LEAF:'asc'} ]  -- YUI3 style
		 * JPQL:
		 *     order by LEVEL desc, LEAF asc
		 **/
		if( sort != null && sort.size() != 0 ){
			JPQL += " order by ";
			String attribute;
			String order;
			for( int m = 0, n = sort.size(); m < n; m ++ ){
				//haven't check may size, default size is 1
				attribute = (String)((Map)sort.get( m )).keySet().iterator().next();
				order = ((Map)sort.get( m )).get( attribute ).toString();
				if( order == null ){//default asc
					order = "asc";
				}
				
				if( m != n - 1){
					JPQL += "\"" + attribute + "\" " + order + ", ";
				}else{
					JPQL += "\"" + attribute + "\" " + order;
				}
			}
		}
		/*//order by -LEVEL1, LEAF. sort is array
		if( sort != null && sort.length != 0 ){
			JPQL += " order by ";
			for( int m = 0, n = sort.length; m < n; m ++ ){
				if( m != n - 1){
					JPQL += sort[m] + ", ";
				}else{
					JPQL += sort[m];
				}
			}
		}*/
		return JPQL;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
