package person.daizhongde.virtue.sql;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.List;
import java.util.Date;

import person.daizhongde.virtue.constant.Algorithm;
import person.daizhongde.virtue.constant.Operator;
import person.daizhongde.virtue.util.date.DateUtils;

/**
 * 通过比较运算符下拉框的索引返回SQL运算符(CS),recently deal with first six conditions
 * 通过标志返回SQL运算符(BS),recently deal wiht first six conditions
 * <br/>update 2013/7/26 by dai zhongde
 * <br/>       0      1=   2!=    3>    4>=     5<     6<=   7like s% 8     9like %s  10  11like %s%  12    13
 * <br/> {" - 选择 - ","等于","不等于","大于","大于或等于","小于","小于或等于","开头是","开头不是","结尾是","结尾不是","包含","不包含"};    in (1,2,3)
 * <br/> {" - 选择 - ","等于","不等于","在以下日期之后","在以下日期之后或与之相同","在以下日期之前","在以下日期之前或与之相同","开头是","开头不是","结尾是","结尾不是","包含","不包含"};
 * <br/> {" - 选择 - ","等于","不等于","大于","大于或等于","小于","小于或等于"};
 * oracle11gR2: 
 * 12   VARCHAR2 
 * -9   NVARCHAR2
 * 1    CHAR
 * 
 * 2    NUMBER
 * 100  BINARY_FLOAT
 * 101  BINARY_DOUBLE
 * 
 * 93   DATE  TIMESTAMP
 * -101 TIMESTAMP WITH TIME ZONE
 * -102 TIMESTAMP WITH LOCAL TIME ZONE
 * 
 * SQLServer2008R2
 * 12   varchar 
 * -9   nvarchar
 * 1    char
 * -15  nchar
 * 
 * 2    numeric
 * 3    money  smallmoney
 * 4    int
 * 5    smallint
 * 8    float
 * -5   bigint
 * 
 * 91   date 
 * 92   time
 * 93   datetime datetime2
 * -2   timestamp
 * 
 * MySQL 5.1
 * 1  char
 * 12 varchar
 * -1 text
 * 
 * 4  int unsigned,mediumint,int
 * 7  float,float undigned
 * 8  double
 * -6 tinyint unsigned
 * 5  smallint
 * -5 bigint
 * 3  deciaml

 * 91 date,year
 * 92 time
 * 93 timestamp,datetime
 * 
 * 静态方法在执行时，并不一定存在对象,所以此类的成员方法可以改为静态的
 * @author dzd
 *
 */
public class SQLManySwitch {
	/**
	 * 通过比较运算符下拉框的索引返回SQL运算符(CS),recently deal with first six conditions
	 * 通过标志返回SQL运算符(BS),recently deal wiht first six conditions
	 * <br/>update 2013/7/26 by dai zhongde
	 * <br/>       0      1=   2!=    3>    4>=     5<     6<=   7like s% 8     9like %s  10  11like %s%  12    13
	 * <br/> {" - 选择 - ","等于","不等于","大于","大于或等于","小于","小于或等于","开头是","开头不是","结尾是","结尾不是","包含","不包含"};    in (1,2,3)
	 * <br/> {" - 选择 - ","等于","不等于","在以下日期之后","在以下日期之后或与之相同","在以下日期之前","在以下日期之前或与之相同","开头是","开头不是","结尾是","结尾不是","包含","不包含"};
	 * <br/> {" - 选择 - ","等于","不等于","大于","大于或等于","小于","小于或等于"};
	 * @param index (operator's index)
	 * @return
	 */
	public static String getOperator(int index){
		switch(index){
			case 1: return "=";
			case 2: return "!=";
			case 3: return ">";
			case 4: return ">=";
			case 5: return "<";
			case 6: return "<=";
			case 7: return "like";
			case 8: return "not like";
			case 9: return "like";
			case 10: return "not like";
			case 11: return "like";
			case 12: return "not like";
			case Operator.BETWEEN:		return "between";
			case Operator.NOTBETWEEN:	return "not between";
			case Operator.IN:			return "in";
			case Operator.NOTIN:		return "not in";
			case Operator.EXISTS:		return "exists";
			case Operator.NOTEXISTS:	return "not exists";
			default : System.out.println("default");  return "=";
		}
	}
	/**
	 * User for file import
	 * 
	 * <p>Set parameter value according to column type
	 * @param index (operator's index)
	 * @param coltype
	 * @return
	 * @throws SQLException 
	 */
	public static void setParameterValue(int parameterIndex, int coltype, Object o, PreparedStatement stmt) throws SQLException{
//		System.out.println("o:"+o.toString());
		if(null == o){
			stmt.setNull(parameterIndex, coltype);
			return;
		}
		switch(coltype){
			case Types.VARCHAR: //12
			case Types.CHAR://1 
			case Types.LONGVARCHAR://-1
			case Types.NVARCHAR: //-9
			case Types.NCHAR://-15
			case Types.LONGNVARCHAR://-16
				stmt.setString(parameterIndex, o.toString() );break;
			case Types.NUMERIC: //2
				stmt.setLong(parameterIndex, Double.valueOf(o.toString()).longValue() );break;
			case Types.DECIMAL: //3 
				stmt.setBigDecimal(parameterIndex, new BigDecimal(o.toString()) );break;
			case Types.INTEGER: //4 
				stmt.setInt(parameterIndex, Double.valueOf(o.toString()).intValue() );break;
			case Types.SMALLINT: //5
				stmt.setShort(parameterIndex, Double.valueOf(o.toString()).shortValue() );break;
			case Types.FLOAT: //6
				stmt.setFloat(parameterIndex, Float.valueOf(o.toString()) );break;
			case Types.REAL: //7
				stmt.setBigDecimal(parameterIndex, new BigDecimal(o.toString()) );break;
			case Types.DOUBLE: //8
				stmt.setDouble(parameterIndex, Double.valueOf(o.toString()) );break;
			case Types.BIGINT://-5
				stmt.setInt(parameterIndex, Double.valueOf(o.toString()).intValue() );break;
			case Types.TINYINT://-6
//			case 100:  	
//			case 101:
//				stmt.setInt(parameterIndex, Double.valueOf(o.toString()).intValue()  );break;
			case Types.DATE://91
//				java.sql.Date date = DateUtils.parseSQLDate( o.toString() );
//				stmt.setDate(parameterIndex, date );break;
				stmt.setDate(parameterIndex, (java.sql.Date)o );break;
			case Types.TIMESTAMP: //93
//				Timestamp timestamp = DateUtils.parseSQLTimestamp( o.toString() );
//				stmt.setTimestamp(parameterIndex, timestamp );break;
				stmt.setTimestamp(parameterIndex, (java.sql.Timestamp)o );break;
				
			default : System.out.println("没有对应的列类型！");
		}
	}
	/**
	 * for assemble sql condition
	 * @param index (operator's index)
	 * @param coltype
	 * @return
	 */
	public static Object getParameterValue(int operator , int coltype, Object o){
//		System.out.println("o:"+o.toString());
		switch(coltype){
			case Types.VARCHAR: //12
			case Types.CHAR://1 
			case Types.LONGVARCHAR://-1
			case Types.NVARCHAR: //-9
			case Types.NCHAR://-15
			case Types.LONGNVARCHAR://-16
				switch( operator ){
					case 1:
					case 2:
					case 3:
					case 4:
					case 5:
					case 6: return o;
					case 7:
					case 8: return o.toString()+"%";
					case 9:
					case 10: return "%"+o.toString();
					case 11:
					case 12: return "%"+o.toString()+"%";
					
					case Operator.BETWEEN:
					case Operator.NOTBETWEEN: 
						List between = (List)o;//between value
						String[] twoValue = new String[2];//between value
						twoValue[0] = String.valueOf( between.get(0) );
						twoValue[1] = String.valueOf( between.get(1) );
						return twoValue;
						
					case Operator.IN:
					case Operator.NOTIN: 
//						List<String> list = new LinkedList<String>();
						List list = (List)o;
						if( list.get(0).toString().startsWith("nest_") ){
							list.remove(0);
						}
						return list;
					
					case Operator.EXISTS:
					case Operator.NOTEXISTS: 
						List exists = (List)o;
						if( exists.get(0).toString().startsWith("nest_") ){
							exists.remove(0);
						}
						return exists;
						
					default : System.out.println("default");  return o;
				}
			case Types.NUMERIC: //2
			case Types.DECIMAL: //3 
			case Types.INTEGER: //4 
			case Types.SMALLINT: //5
			case Types.FLOAT: //6
			case Types.REAL: //7
			case Types.DOUBLE: //8
			case Types.BIGINT://-5
			case Types.TINYINT://-6
//			case 100:  	
//			case 101: 
				/** Precision, Scale may use in the future. It will return Long[], BigDecimal[]  **/
				switch( operator ){
					case 1: 
					case 2: 
					case 3: 
					case 4: 
					case 5: 
					case 6: return new Long(o.toString());
					case 7: 
					case 8: return o.toString()+"%";
					case 9: 
					case 10: return "%"+o.toString();
					case 11: 
					case 12: return "%"+o.toString()+"%";
					
					case Operator.BETWEEN:
					case Operator.NOTBETWEEN: 
						List between = (List)o;//between value
						Integer[] twoValue = new Integer[2];//between value
//						Long[] twoValue = new Long[2];//between value
//						BigDecimal[] twoValue = new BigDecimal[2];//between value
						twoValue[0] = Integer.valueOf( between.get(0).toString() );
						twoValue[1] = Integer.valueOf( between.get(1).toString() );
						return twoValue;
						
					case Operator.IN: 
					case Operator.NOTIN: 
//						List<Integer> list = new LinkedList<Integer>();
						List list = (List)o;
						if( list.get(0).toString().startsWith("nest_") ){
							list.remove(0);
						}
						return list;
						
					case Operator.EXISTS:
					case Operator.NOTEXISTS: 
						List exists = (List)o;
						if( exists.get(0).toString().startsWith("nest_") ){
							exists.remove(0);
						}
						return exists;
						
					default : System.out.println("default");  return o;
				}
	//		case 93: return "to_date( ? , 'yyyymmdd')";
	//		case 93: return "to_date( ? , 'yyyy-mm-dd')";
			case Types.DATE://91
			case Types.TIMESTAMP: //93
				//1900-01-01 , 1900-01-01 00:00:01,
				//1900/01/01 , 1900/01/01 00:00:01,
				//["1900/01/01","1900/01/01"]
				//["1900/01/01 00:00:01","1900/01/01 00:00:01"]
//				String[] s = o.toString().split("[-/ :]");
//				java.util.Date d = DateUtils.parseDate( o.toString() );
				java.util.Date d;
				
				switch( operator ){
					case Operator.EQUAL: 
					case Operator.NOTEQUAL:  
						d = DateUtils.parseDate( o.toString() );
						return d; 
					
					case Operator.GT: 
					case Operator.GE: 
						d = DateUtils.parseDate( o.toString() );
						return d;
					
					case Operator.LT: 
					case Operator.LE: 
						d = DateUtils.parseDate( o.toString() );
						String[] s = o.toString().split("[-/ :]");
						if( s.length == 3 ){
							/** format:'1900-01-01'  <, <=  **/
							return new java.util.Date( d.getTime() + 24*60*60*1000 );
						}
						return d;
					
					case 7: 
					case 8: return o.toString()+"%";
					case 9: 
					case 10: return "%"+o.toString();
					case 11: 
					case 12: return "%"+o.toString()+"%";
					
					case Operator.BETWEEN: 
					case Operator.NOTBETWEEN: 
						List between = (List)o;//between value
//						System.out.println( "between.size():"+between.size() );
						
						Date[] twoValue = new Date[2];//between value

						twoValue[0] = DateUtils.parseDate( between.get(0).toString() );
						
						if( between.get(0).toString().split("[-/ :]").length == 3 ){
							/** format:'1900-01-01' **/
							twoValue[1] = new java.util.Date(
										(DateUtils.parseDate( between.get(1).toString() )
											).getTime() + 24*60*60*1000 
									);
						}else{
							twoValue[1] = DateUtils.parseDate( between.get(1).toString() );
						}
						return twoValue;
						
					case Operator.IN: 
					case Operator.NOTIN: 
						List list = (List)o;
						if( list.get(0).toString().startsWith("nest_") ){
							list.remove(0);
						}
						for(int i=0, j=list.size(); i<j; i++){
							list.set( i, DateUtils.parseDate( list.get(i).toString() ) );
						}
						return list;
						
					case Operator.EXISTS:
					case Operator.NOTEXISTS: 
						List exists = (List)o;
						if( exists.get(0).toString().startsWith("nest_") ){
							exists.remove(0);
						}
						return exists;
						
					default : System.out.println("default operator!");  return o;
				}
				
			default : System.out.println("没有对应的列类型！");  return o;
		}
	}
	/**
	 * for insert sql column value algorithm
	 * <br/>       0       1(+) 2(-) 3(*) 4(/) 
	 * <br/> {" - 选择 - ","加","减","乘","除"}; add, subtract, multiply and divide 
	 * @param index (Algorithm's index)
	 * @return
	 * @author dzd
	 * @date 2013/10/17
	 */
	public static String getAlgorithm(int index){
//		String operator = "";
		switch(index){
			case Algorithm.PLUS: 
				return "+";
			case Algorithm.MINUS: 
				return "-";
			case Algorithm.MULTIPLY: 
				return "*";
			case Algorithm.DIVIDE: 
				return "/";
			default : System.out.println("default");  return "+";
		}
//		return operator;
	}
	
	public static String getMySQLSelectSQLColumn(String column_name, String data_type ){
		data_type = data_type.toLowerCase();
//		System.out.println(column_name +":"+data_type);
		switch(data_type){
			case "timestamp": 
			case "datetime": 
				return "date_format("+column_name+", '%Y-%m-%d %H:%i:%S') "+column_name;
			case "int": 
			case "bigint":
				return column_name;
			default : 
//				System.out.println("default SelectSQLColumn!");  
				return " ifnull("+column_name+",'') "+column_name;
		}
	}
	// Prevent instantiation
    private void SQLManySwitch() {}
}