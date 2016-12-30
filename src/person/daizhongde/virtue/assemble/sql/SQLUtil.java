package person.daizhongde.virtue.assemble.sql;

import java.sql.Types;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import person.daizhongde.virtue.configutils.ConfigDocument_SQL;
import person.daizhongde.virtue.configutils.SQLNode;
import person.daizhongde.virtue.constant.INIT;
import person.daizhongde.virtue.constant.Operator;
import person.daizhongde.virtue.util.FIELDUtil;
import person.daizhongde.virtue.util.SQLManySwitch;
/**
 * @version 1.2 20150106 daizd 增加front2col为空的支持,即:front直接使用列名
 * @version 1.1
 * @author daizd
 *
 */
public class SQLUtil {
	private static Logger log = LogManager.getLogger(SQLUtil.class.getName() );
	
	public static String getSelectColSQL(String selectSQL) {
		
		//sql is consisted of selectColSQL and fromSQL
		String query_SQL_selectColSQL = "";
		String query_SQL_fromSQL = "";
		
		char[] b = selectSQL.toCharArray();
		
//		boolean parenthesesFlag = false;
		int parentheses = 0;//unclosed parentheses count
		
		for(char e : b){
			if( e=='(' ){
				parentheses++;//init
			}else if(e ==')' ){
				parentheses--;
			}
			query_SQL_selectColSQL +=e;
			if( parentheses==0 
					&& query_SQL_selectColSQL.toLowerCase().endsWith(" from ")){
				query_SQL_selectColSQL = selectSQL.substring(0, 
						query_SQL_selectColSQL.length()-5 );

				return query_SQL_selectColSQL;
			}
		}
		return query_SQL_fromSQL;
	}
	
	public static String getFromSQL(String selectSQL) {
//		int query_SQL_fromIndex = selectSQL.toLowerCase().indexOf("from ");
//		/**  from table1 t1 where a='f' and b='cf', include 'from' keywords **/
//		String query_SQL_fromSQL = selectSQL.substring( query_SQL_fromIndex );
//		return query_SQL_fromSQL;
		
		//sql is consisted of selectColSQL and fromSQL
		String query_SQL_selectColSQL = "";
		String query_SQL_fromSQL = "";
		
		char[] b = selectSQL.toCharArray();
		
//		boolean parenthesesFlag = false;
		int parentheses = 0;//unclosed parentheses count
		
		for(char e : b){
			if( e=='(' ){
				parentheses++;
			}else if( e ==')' ){
				parentheses--;
			}
			query_SQL_selectColSQL +=e;
			if( parentheses==0
					&& query_SQL_selectColSQL.toLowerCase().endsWith(" from ")){
				query_SQL_fromSQL = selectSQL.substring( 
						query_SQL_selectColSQL.length()-5 );

				return query_SQL_fromSQL;
			}
		}
		return query_SQL_fromSQL;
	}
	/**
	 * 
	 * @param condition eg:{C_MNAME: 'system manage'}
	 * @param operator eg:{C_MNAME: equal}
	 * @param front2col eg:{c_mname: 'C_MNAME'}
	 * @param columnTypes eg:{C_MNAME: 12}
	 * @param map Target SQL parameters map
	 * @param withAlias Wethether table name with alias
	 * @return
	 */
	public static String getWhereBackSQL( Map condition, Map operator, 
			Map front2col, Map columnTypes,
			Map map, boolean withAlias, ConfigDocument_SQL SQLDOC
			 ){
		String whereBackSQL = "";
		/* Use JSONArray because it has order */
		Set fields = condition.keySet();
		Iterator it = fields.iterator();
		/* front: page post parameter, may have relevant table alias, or havn't
		 * frontNoAlias: page post parameter
		 * back: Back Field name, no alias
		 * backWithAlias:  Back Field with relevant table alias
		 * column: pure column name, no alias
		 * columnWithAlias: column name with table's alias
		 * parameter: SQL parameter
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

//			System.out.println("front:"+front);

//			column = front2col.get( frontNoAlias ).toString();
			if( null != front2col && front2col.containsKey( frontNoAlias ) )
			{
				column = front2col.get( frontNoAlias ).toString();
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
			//To support no config(columnTypes) condition. modify by daizhongde,date:2015/4/17
			values[i] = (
					//If types have not config(include common config file), treat value as string(type:12)
					null == columnTypes || !columnTypes.containsKey(column.toUpperCase()) ?  
							SQLManySwitch.getParameterValue( operatorIndex , 
									12,
									condition.get(front) )
						: SQLManySwitch.getParameterValue( operatorIndex , 
								new Integer(
										columnTypes.get( column.toUpperCase() ).toString()
								).intValue(),
								condition.get(front) )
			);
//			values[i] = SQLManySwitch.getParameterValue( operatorIndex , 
//					new Integer(
//							columnTypes.get( column.toUpperCase() ).toString()
//					).intValue(),
//					condition.get(front) );
			//if operate is equal another column, continue( no code wait for finish .... implement to column compare )
			
			
			
			//use front to avoid duplication of parameter name
			map.put( front, values[i] );
			
			if(  null != columnTypes
					&& columnTypes.containsKey(column.toUpperCase())
					&& new Integer( columnTypes.get(column.toUpperCase()).toString() ).intValue()  == Types.TIMESTAMP 
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
					/* condition:{ nmparent : [ 'nest_SQL']} 
					 * list.size() == 0
					 *  */
					List list = (List)values[i];
					if( list.size() != 0 ){
						
//						parameter = " ( :"+front+" ) ";
						//support setParameterList(String name, Collection vals), supportSetParameterList=true, default: true (when haven't config)
						if( INIT.supportSetParameterList == null || Boolean.valueOf( INIT.supportSetParameterList ).booleanValue() ){
							parameter = " ( :" + front + " ) ";
						}else{
							parameter = "";
							//supportSetParameterList=false
							for(int m=0, n=list.size(); m<n; m++){//haven't complete, values haven't synchronize update
								if( m == 0 ){
									parameter += " ( :"+front + m + " , ";
								}else if( m < n-1 ){
									parameter += " :"+front + m + " , ";
								}else{
									parameter += " :"+front + m + " ) ";
								}
							}
						}
					}else{
//						parameter = "(" + absc.getNest_SQL() + ")";//nest SQL, 
//						nmparent : [ 'nest_SQL1'],//exists,exists1,exists2.....(nestSQL现在假定没有参数)
//						nmexpanded : [ 'nest_SQL2'],//nest_SQL,nest_SQL1,nest_SQL2...
//						SQLMAP.get("nest.nest.SQL"");
						
						List o = (List)condition.get(front);
						String nest = o.get(0).toString();
//						String nestKey = "nest."+o.get(0).toString().replaceFirst("_", ".");
//						parameter = "(" + absc.getSQLMAP().get( nestKey ) + ")";//nest SQL, 
						parameter = "(" + ((SQLNode)( SQLDOC.getNest().get(nest) )).getSQL() + ")";//nest SQL,
						/*  There nestSQL don't support setParameter
						 *  After a time a you meet .....you develop .......extend....
						 *  **/
					}
					break;
				case Operator.EXISTS:
				case Operator.NOTEXISTS: 
//					parameter = "(" + absc.getNest_SQL() + ")";//nest SQL,
					List o = (List)condition.get(front);
					String nest = o.get(0).toString();
//					String nestKey = "nest."+o.get(0).toString().replaceFirst("_", ".");
//					parameter = "(" + absc.getSQLMAP().get( nestKey ) + ")";//nest SQL, 
					parameter = "(" + ((SQLNode)( SQLDOC.getNest().get(nest))).getSQL() + ")";//nest SQL,
					break;
					
				default : 
					parameter = " :"+front+" ";
			}
			
			columnWithAlias = withAlias?columnWithAlias:column;
			
			if(i == 0){
				whereBackSQL = columnWithAlias + " " +
						SQLManySwitch.getOperator( operatorIndex ) + parameter;
			}else{
				whereBackSQL += "and " + columnWithAlias + " " +
						SQLManySwitch.getOperator( operatorIndex ) + parameter;
			}
			i++;
		}//end of for field.size()


		return whereBackSQL;
	}
//	/**
//	 * 
//	 * @param condition eg:{C_MNAME: 'system manage'}
//	 * @param operator eg:{C_MNAME: equal}
//	 * @param front2col eg:{c_mname: 'C_MNAME'}
//	 * @param columnTypes eg:{C_MNAME: 12}
//	 * @param map Target SQL parameters map
//	 * @param withAlias Wethether table name with alias
//	 * @return
//	 */
//	public static String getWhereBackSQL( Map condition, Map operator, 
//			Map front2col, Map columnTypes,
//			Map map, boolean withAlias, ConfigDocument_SQL SQLDOC, String andor
//			 ){
//		String whereBackSQL = "";
//		/* Use JSONArray because it has order */
//		Set fields = condition.keySet();
//		Iterator it = fields.iterator();
//		/* front: page post parameter, may have relevant table alias, or havn't
//		 * frontNoAlias: page post parameter
//		 * back: Back Field name, no alias
//		 * backWithAlias:  Back Field with relevant table alias
//		 * column: pure column name, no alias
//		 * columnWithAlias: column name with table's alias
//		 * parameter: SQL parameter
//		 */
//		String front, alias, frontNoAlias, column, columnWithAlias, parameter;
//		int j=fields.size(), operatorIndex;
//		Object[] values = new Object[j];
//		int i = 0;
//		while( it.hasNext() ){
//			front = it.next().toString();
//			alias = FIELDUtil.getAlias(front);
//			frontNoAlias = FIELDUtil.getFrontNoAlias(front);
//			
////			back = absc.getFront2back().get( frontNoAlias );
////			backWithAlias = alias + "." +back;
//
//			System.out.println("front:"+front);
//
////			column = front2col.get( frontNoAlias ).toString();
//			if( null != front2col && front2col.containsKey( frontNoAlias ) )
//			{
//				column = front2col.get( frontNoAlias ).toString();
//			}
//			else
//			{
//				column = frontNoAlias;
//			}
//			
//			columnWithAlias = alias + "." +column;
//			
//			//Operator.EQUAL;//defalut: equal     //ConfigConstants.defaultOperatorIndex;
//			operatorIndex = ( operator != null && operator.get(front) != null ) ? 
//					new Integer(
//							operator.get(front).toString()
//					).intValue()
//					: Operator.EQUAL;//defalut: equal
//					
//			//switch to deal with,if have problem rise in switch class
//			//To support no config(columnTypes) condition. modify by daizhongde,date:2015/4/17
//			values[i] = (
//					//If types have not config(include common config file), treat value as string(type:12)
//					null == columnTypes || !columnTypes.containsKey(column.toUpperCase()) ?  
//							SQLManySwitch.getParameterValue( operatorIndex , 
//									12,
//									condition.get(front) )
//						: SQLManySwitch.getParameterValue( operatorIndex , 
//								new Integer(
//										columnTypes.get( column.toUpperCase() ).toString()
//								).intValue(),
//								condition.get(front) )
//			);
////			values[i] = SQLManySwitch.getParameterValue( operatorIndex , 
////					new Integer(
////							columnTypes.get( column.toUpperCase() ).toString()
////					).intValue(),
////					condition.get(front) );
//			//if operate is equal another column, continue( no code wait for finish .... implement to column compare )
//			
//			
//			
//			//use front to avoid duplication of parameter name
//			map.put( front, values[i] );
//			
//			if(  null != columnTypes
//					&& columnTypes.containsKey(column.toUpperCase())
//					&& new Integer( columnTypes.get(column.toUpperCase()).toString() ).intValue()  == Types.TIMESTAMP 
//					&& operatorIndex == Operator.EQUAL ){
//				//EQUAL isn't recommend,Use between instead 
//				// Types.TIMESTAMP && Operator.EQUAL 
//				//to_char( d_xfrq , 'yyyymmdd' ) = 20130914
//				//to_date(to_char(d_xfrq, 'yyyymmdd'), 'yyyymmdd') = ? //java.sql.date
//				column = "to_date(to_char( " + column + " , 'yyyymmdd'), 'yyyymmdd')";
//				columnWithAlias = "to_date(to_char( " + columnWithAlias + " , 'yyyymmdd'), 'yyyymmdd')";
//				
//			}else if( operatorIndex == Operator.EXISTS
//						|| operatorIndex == Operator.NOTEXISTS ){
//				column = "";
//				columnWithAlias = "";
//			}
//
//			switch( operatorIndex ){
//				case 1: case 2: case 3: case 4: case 5: 
//				case 6: case 7: case 8: case 9: case 10: 
//				case 11: case 12: 
//					parameter = " :"+front+" ";
//					break;
//				case Operator.BETWEEN:
//				case Operator.NOTBETWEEN: 
//					parameter = " :"+front+"0 and "+" :"+front + "1 ";
//					break;
//				case Operator.IN: 
//				case Operator.NOTIN: 
//					/* condition:{ nmparent : [ 'nest_SQL']} 
//					 * list.size() == 0
//					 *  */
//					List list = (List)values[i];
//					if( list.size() != 0 ){
//						
////						parameter = " ( :"+front+" ) ";
//						//support setParameterList(String name, Collection vals), supportSetParameterList=true, default: true (when haven't config)
//						if( INIT.supportSetParameterList == null || Boolean.valueOf( INIT.supportSetParameterList ).booleanValue() ){
//							parameter = " ( :" + front + " ) ";
//						}else{
//							parameter = "";
//							//supportSetParameterList=false
//							for(int m=0, n=list.size(); m<n; m++){//haven't complete, values haven't synchronize update
//								if( m == 0 ){
//									parameter += " ( :"+front + m + " , ";
//								}else if( m < n-1 ){
//									parameter += " :"+front + m + " , ";
//								}else{
//									parameter += " :"+front + m + " ) ";
//								}
//							}
//						}
//					}else{
////						parameter = "(" + absc.getNest_SQL() + ")";//nest SQL, 
////						nmparent : [ 'nest_SQL1'],//exists,exists1,exists2.....(nestSQL现在假定没有参数)
////						nmexpanded : [ 'nest_SQL2'],//nest_SQL,nest_SQL1,nest_SQL2...
////						SQLMAP.get("nest.nest.SQL"");
//						
//						List o = (List)condition.get(front);
//						String nest = o.get(0).toString();
////						String nestKey = "nest."+o.get(0).toString().replaceFirst("_", ".");
////						parameter = "(" + absc.getSQLMAP().get( nestKey ) + ")";//nest SQL, 
//						parameter = "(" + ((SQLNode)( SQLDOC.getNest().get(nest) )).getSQL() + ")";//nest SQL,
//						/*  There nestSQL don't support setParameter
//						 *  After a time a you meet .....you develop .......extend....
//						 *  **/
//					}
//					break;
//				case Operator.EXISTS:
//				case Operator.NOTEXISTS: 
////					parameter = "(" + absc.getNest_SQL() + ")";//nest SQL,
//					List o = (List)condition.get(front);
//					String nest = o.get(0).toString();
////					String nestKey = "nest."+o.get(0).toString().replaceFirst("_", ".");
////					parameter = "(" + absc.getSQLMAP().get( nestKey ) + ")";//nest SQL, 
//					parameter = "(" + ((SQLNode)( SQLDOC.getNest().get(nest))).getSQL() + ")";//nest SQL,
//					break;
//					
//				default : 
//					parameter = " :"+front+" ";
//			}
//			
//			columnWithAlias = withAlias?columnWithAlias:column;
//			
//			if(i == 0){
//				whereBackSQL = columnWithAlias + " " +
//						SQLManySwitch.getOperator( operatorIndex ) + parameter;
//			}else{
//				whereBackSQL += andor + " " + columnWithAlias + " " +
//						SQLManySwitch.getOperator( operatorIndex ) + parameter;
//			}
//			i++;
//		}//end of for field.size()
//		

//		return whereBackSQL;
//	}
	/**
	 * post:
	 *     sort: [ { LEVEL:'desc'  }, { LEAF:'asc'} ]  -- YUI3 style
	 * SQL:
	 *     order by LEVEL desc, LEAF asc
	 * @param sort
	 * @return
	 */
	public static String getOrderSQL(List sort){
		String SQL = "";
		/*
		 * dojo style
		 * 	Param:
		 * 		[{attribute: "LEVEL1", descending: true},{attribute: "LEAF", descending: false}]
		 * 	SQL:
		 * 		order by LEVEL1 desc, LEAF asc
		
		if( sort != null && sort.size() != 0 ){
			SQL += " order by ";
			boolean b;
			for( int m = 0, n = sort.size(); m < n; m ++ ){
				if( sort.get( m ).get("descending") == null ){//default asc
					b = false;
				}else{
					b = (Boolean)sort.get( m ).get("descending");
				}
				if( m != n - 1){
					SQL += sort.get( m ).get("attribute") + " " + 
							( b ? "desc" : "asc" ) + ", ";
				}else{
					SQL += sort.get( m ).get("attribute") + " " + 
						    ( b ? "desc" : "asc" );
				}
			}
		}*/
		/* 
		 * YUI3 style
		 * Param:
		 *     order: [ { LEVEL:'desc'  }, { LEAF:'asc'} ] 
		 * SQL:
		 *     order by LEVEL desc, LEAF asc
		 **/
		if( sort != null && sort.size() != 0 ){
			SQL += " order by ";
			String attribute;
			String order;
			for( int m = 0, n = sort.size(); m < n; m ++ ){
				//haven't check may size, default size is 1
				attribute = (String)((Map)sort.get( m )).keySet().iterator().next();
				order = ((Map)sort.get( m )).get( attribute ).toString();
				if( order == null ){//default asc
					order = "asc";
				}
				
				if( m != n - 1)
				{
//					SQL += "\"" + attribute + "\" " + order + ", ";//Oracle
					SQL += attribute + " " + order + ", ";//MySQL
				}
				else
				{
//					SQL += "\"" + attribute + "\" " + order;//Oracle
					SQL += attribute + " " + order;//MySQL
				}
			}
		}
		/* order by -LEVEL1, LEAF. sort is array
		if( sort != null && sort.length != 0 ){
			SQL += " order by ";
			for( int m = 0, n = sort.length; m < n; m ++ ){
				if( m != n - 1){
					SQL += sort[m] + ", ";
				}else{
					SQL += sort[m];
				}
			}
		}*/
		return SQL;
	}
	/**
	 * whether need where keywords when add sql condition,
	 * currently, can't support parentheses exists in parentheses
	 * <p>
	 * 
	 * select t1.FAUDIT_ID "faudit_id",
			(select value 
			   from t_pub_dictionary 
			  where type='audit_type' 
			    and code=t1.faudit_type ) "faudit_type"
		from tool.Mig_Auditf_Main t1
		
		-->formatSQL: 
		select t1.FAUDIT_ID "faudit_id",
			(#nestSQL) "faudit_type"
		from tool.Mig_Auditf_Main t1
	 * @param selectSQL
	 * @return
	 */
	public static boolean isConditionNeedWhere(String selectSQL) {
		
//		log.debug("selectSQL:"+selectSQL);
		String selectSQL_New = "";
				
		char[] b = selectSQL.toCharArray();
		Map nestSQL = new HashMap();
		String tempNestSQL="";
		
//		boolean parenthesesFlag = false;
		int parentheses = 0;//unclosed parentheses count
		
		for(char e : b){
			if( e=='(' ){
				parentheses++;
				tempNestSQL="";
			}else if( e ==')' ){
				parentheses--;
				selectSQL_New += "("+"#nestSQL"+nestSQL.size()+")";
				if( tempNestSQL.toLowerCase().indexOf(" where ") != -1 ){
					nestSQL.put( "nestSQL"+nestSQL.size(), tempNestSQL );
				}
				tempNestSQL = "";
			}else if(parentheses>0){
				tempNestSQL+=e;
			}else{
				selectSQL_New+=e;
			}
		}
		
		if( selectSQL_New.toLowerCase().indexOf(" where ") != -1){
			return false;
		}else{
			return true;
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
		 * 
		 * @param condition eg:{C_MNAME: 'system manage'}
		 * @param operator eg:{C_MNAME: equal}
		 * @param front2col eg:{c_mname: 'C_MNAME'}
		 * @param columnTypes eg:{C_MNAME: 12}
		 * @param map Target SQL parameters map
		 * @param withAlias Wethether table name with alias
		 * @return
		 */
		public static String getWhereBackSQL2( Map condition, Map operator, 
				Map front2col, Map columnTypes,
				Map map, boolean withAlias, ConfigDocument_SQL SQLDOC,
				String onlyParams
				 ){
			onlyParams = ( null==onlyParams ? "":onlyParams );
			String[] cols = onlyParams.split(",");
			List colslist = Arrays.asList(cols);  
			
			String whereBackSQL = "";
			/* Use JSONArray because it has order */
			Set fields = condition.keySet();
			Iterator it = fields.iterator();
			/* front: page post parameter, may have relevant table alias, or havn't
			 * frontNoAlias: page post parameter
			 * back: Back Field name, no alias
			 * backWithAlias:  Back Field with relevant table alias
			 * column: pure column name, no alias
			 * columnWithAlias: column name with table's alias
			 * parameter: SQL parameter
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
	
	//			System.out.println("front:"+front);
	
	//			column = front2col.get( frontNoAlias ).toString();
				if( null != front2col && front2col.containsKey( frontNoAlias ) )
				{
					column = front2col.get( frontNoAlias ).toString();
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
				//To support no config(columnTypes) condition. modify by daizhongde,date:2015/4/17
				values[i] = (
						//If types have not config(include common config file), treat value as string(type:12)
						null == columnTypes || !columnTypes.containsKey(column.toUpperCase()) ?  
								SQLManySwitch.getParameterValue( operatorIndex , 
										12,
										condition.get(front) )
							: SQLManySwitch.getParameterValue( operatorIndex , 
									new Integer(
											columnTypes.get( column.toUpperCase() ).toString()
									).intValue(),
									condition.get(front) )
				);
	//			values[i] = SQLManySwitch.getParameterValue( operatorIndex , 
	//					new Integer(
	//							columnTypes.get( column.toUpperCase() ).toString()
	//					).intValue(),
	//					condition.get(front) );
				//if operate is equal another column, continue( no code wait for finish .... implement to column compare )
				
				
				
				//use front to avoid duplication of parameter name
				map.put( front, values[i] );
				
				/* if this parameter is special parameter, only as parameter, don't assemble where condition  */
				if(colslist.contains(front)){
					continue;
				}
				if(  null != columnTypes
						&& columnTypes.containsKey(column.toUpperCase())
						&& new Integer( columnTypes.get(column.toUpperCase()).toString() ).intValue()  == Types.TIMESTAMP 
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
						/* condition:{ nmparent : [ 'nest_SQL']} 
						 * list.size() == 0
						 *  */
						List list = (List)values[i];
						if( list.size() != 0 ){
							
	//						parameter = " ( :"+front+" ) ";
							//support setParameterList(String name, Collection vals), supportSetParameterList=true, default: true (when haven't config)
							if( INIT.supportSetParameterList == null || Boolean.valueOf( INIT.supportSetParameterList ).booleanValue() ){
								parameter = " ( :" + front + " ) ";
							}else{
								parameter = "";
								//supportSetParameterList=false
								for(int m=0, n=list.size(); m<n; m++){//haven't complete, values haven't synchronize update
									if( m == 0 ){
										parameter += " ( :"+front + m + " , ";
									}else if( m < n-1 ){
										parameter += " :"+front + m + " , ";
									}else{
										parameter += " :"+front + m + " ) ";
									}
								}
							}
						}else{
	//						parameter = "(" + absc.getNest_SQL() + ")";//nest SQL, 
	//						nmparent : [ 'nest_SQL1'],//exists,exists1,exists2.....(nestSQL现在假定没有参数)
	//						nmexpanded : [ 'nest_SQL2'],//nest_SQL,nest_SQL1,nest_SQL2...
	//						SQLMAP.get("nest.nest.SQL"");
							
							List o = (List)condition.get(front);
							String nest = o.get(0).toString();
	//						String nestKey = "nest."+o.get(0).toString().replaceFirst("_", ".");
	//						parameter = "(" + absc.getSQLMAP().get( nestKey ) + ")";//nest SQL, 
							parameter = "(" + ((SQLNode)( SQLDOC.getNest().get(nest) )).getSQL() + ")";//nest SQL,
							/*  There nestSQL don't support setParameter
							 *  After a time a you meet .....you develop .......extend....
							 *  **/
						}
						break;
					case Operator.EXISTS:
					case Operator.NOTEXISTS: 
	//					parameter = "(" + absc.getNest_SQL() + ")";//nest SQL,
						List o = (List)condition.get(front);
						String nest = o.get(0).toString();
	//					String nestKey = "nest."+o.get(0).toString().replaceFirst("_", ".");
	//					parameter = "(" + absc.getSQLMAP().get( nestKey ) + ")";//nest SQL, 
						parameter = "(" + ((SQLNode)( SQLDOC.getNest().get(nest))).getSQL() + ")";//nest SQL,
						break;
						
					default : 
						parameter = " :"+front+" ";
				}
				
				columnWithAlias = withAlias?columnWithAlias:column;
				
				
				if(i == 0){
					whereBackSQL = columnWithAlias + " " +
							SQLManySwitch.getOperator( operatorIndex ) + parameter;
				}else{
					whereBackSQL += "and " + columnWithAlias + " " +
							SQLManySwitch.getOperator( operatorIndex ) + parameter;
				}
				i++;
			}//end of for field.size()
	
	
			return whereBackSQL;
		}

}
