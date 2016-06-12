package person.daizhongde.virtue.assemble.hql;

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

public class HQLUtil {
	private static Logger log = Logger.getLogger(HQLUtil.class.getClass());
	
	public static String getFromHQL(String selectHQL) {
		
		int query_HQL_fromIndex = selectHQL.toLowerCase().indexOf("from ");
		/**  from t where a='f' and b='cf', include 'from' keywords **/
		String query_HQL_fromHQL = selectHQL.substring( query_HQL_fromIndex );
		return query_HQL_fromHQL;
	}
	/**
	 * 
	 * @param condition eg:{C_MNAME: 'system manage'}
	 * @param operator eg:{C_MNAME: equal}
	 * @param front2col eg:{c_mname: 'C_MNAME'}
	 * @param columnTypes eg:{C_MNAME: 12}
	 * @param map Target HQL parameters map
	 * @param withAlias Wethether table name with alias
	 * @return
	 */
	public static String getWhereBackHQL( Map condition, Map operator, 
			Map front2back, Map columnTypes2,
			Map map, boolean withAlias, ConfigDocument_SQL SQLDOC
			 ){
		String whereBackHQL = "";
		/* Use JSONArray because it has order */
		Set fields = condition.keySet();
		Iterator it = fields.iterator();
		/* front: page post parameter, may have relevant table alias, or havn't
		 * frontNoAlias: page post parameter
		 * back: Back Field name, no alias
		 * backWithAlias:  Back Field with relevant table alias
		 * column: pure column name, no alias
		 * columnWithAlias: column name with table's alias
		 * parameter: HQL parameter
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
			
			if( null != front2back && front2back.containsKey( frontNoAlias ) )
			{
				column = front2back.get( frontNoAlias ).toString();
			}
			else
			{
				column = frontNoAlias;
			}
			
			columnWithAlias = alias + "." +column;
			
			//Operator.EQUAL;//defalut: equal     //ConfigConstants.defaultOperatorIndex;
			operatorIndex = ( operator != null && operator.get(front) != null ) ? 
					new Integer(
							operator.get(front).toString()
					).intValue()
					: Operator.EQUAL;//defalut: equal
					
			//switch to deal with,if have problem rise in switch class
			
			values[i] = (
					//If types have not config(include common config file), treat value as string(type:12)
					null == columnTypes2 || !columnTypes2.containsKey(column.toUpperCase()) ?  
							SQLManySwitch.getParameterValue( operatorIndex , 
									12,
									condition.get(front) )
						: SQLManySwitch.getParameterValue( operatorIndex , 
								new Integer(
										columnTypes2.get( column.toUpperCase() ).toString()
								).intValue(),
								condition.get(front) )
			);
			
			//use front to avoid duplication of parameter name
			map.put( front, values[i] );
			
			if(  null != columnTypes2
					&& columnTypes2.containsKey(column.toUpperCase())
					&& new Integer( columnTypes2.get( FIELDUtil.getHQLColumnSuffix(column) ).toString() ).intValue()  == Types.TIMESTAMP 
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
					/* condition:{ nmparent : [ 'nest_HQL']} 
					 * list.size() == 0
					 *  */
					List list = (List)values[i];
					if( list.size() != 0 ){
						
						parameter = " ( :"+front+" ) ";
						
					}else{
//						parameter = "(" + absc.getNest_HQL() + ")";//nest HQL, 
//						nmparent : [ 'nest_HQL1'],//exists,exists1,exists2.....(nestHQL现在假定没有参数)
//						nmexpanded : [ 'nest_HQL2'],//nest_HQL,nest_HQL1,nest_HQL2...
//						HQLMAP.get("nest.nest.HQL"");
						
						List o = (List)condition.get(front);
						String nest = o.get(0).toString();
//						String nestKey = "nest."+o.get(0).toString().replaceFirst("_", ".");
//						parameter = "(" + absc.getHQLMAP().get( nestKey ) + ")";//nest HQL, 
						parameter = "(" + ((SQLNode)( SQLDOC.getNest().get(nest) )).getHQL() + ")";//nest HQL,
						/*  There nestHQL don't support setParameter
						 *  After a time a you meet .....you develop .......extend....
						 *  **/
					}
					break;
				case Operator.EXISTS:
				case Operator.NOTEXISTS: 
//					parameter = "(" + absc.getNest_HQL() + ")";//nest HQL,
					List o = (List)condition.get(front);
					String nest = o.get(0).toString();
//					String nestKey = "nest."+o.get(0).toString().replaceFirst("_", ".");
//					parameter = "(" + absc.getHQLMAP().get( nestKey ) + ")";//nest HQL, 
					parameter = "(" + ((SQLNode)( SQLDOC.getNest().get(nest))).getHQL() + ")";//nest HQL,
					break;
					
				default : 
					parameter = " :"+front+" ";
			}
			
			columnWithAlias = withAlias?columnWithAlias:column;
			
			if(i == 0){
				whereBackHQL = columnWithAlias + " " +
						SQLManySwitch.getOperator( operatorIndex ) + parameter;
			}else{
				whereBackHQL += "and " + columnWithAlias + " " +
						SQLManySwitch.getOperator( operatorIndex ) + parameter;
			}
			i++;
		}//end of for field.size()
		
		log.debug( whereBackHQL );
		return whereBackHQL;
	}

	/**
	 * post:
	 *     sort: [ { LEVEL:'desc'  }, { LEAF:'asc'} ]  -- YUI3 style
	 * HQL:
	 *     order by LEVEL desc, LEAF asc
	 * @param sort
	 * @return
	 */
	public static String getOrderHQL(List sort){
		String HQL = "";
		/*//order by LEVEL1 desc, LEAF asc , dojo style
		//[{attribute: "LEVEL1", descending: true},{attribute: "LEAF", descending: false}]
		if( sort != null && sort.size() != 0 ){
			HQL += " order by ";
			boolean b;
			for( int m = 0, n = sort.size(); m < n; m ++ ){
				if( sort.get( m ).get("descending") == null ){//default asc
					b = false;
				}else{
					b = (Boolean)sort.get( m ).get("descending");
				}
				if( m != n - 1){
					HQL += sort.get( m ).get("attribute") + " " + 
							( b ? "desc" : "asc" ) + ", ";
				}else{
					HQL += sort.get( m ).get("attribute") + " " + 
						    ( b ? "desc" : "asc" );
				}
			}
		}*/
		/* post:
		 *     order: [ { LEVEL:'desc'  }, { LEAF:'asc'} ]  -- YUI3 style
		 * HQL:
		 *     order by LEVEL desc, LEAF asc
		 **/
		if( sort != null && sort.size() != 0 ){
			HQL += " order by ";
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
					HQL += "\"" + attribute + "\" " + order + ", ";
				}else{
					HQL += "\"" + attribute + "\" " + order;
				}
			}
		}
		/*//order by -LEVEL1, LEAF. sort is array
		if( sort != null && sort.length != 0 ){
			HQL += " order by ";
			for( int m = 0, n = sort.length; m < n; m ++ ){
				if( m != n - 1){
					HQL += sort[m] + ", ";
				}else{
					HQL += sort[m];
				}
			}
		}*/
		return HQL;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
