package person.daizhongde.virtue.util.ie;

import java.sql.Types;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
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
 * -1 varchar
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
 * @author dzd
 *
 */
public class TXTCellUtil {

	private static Logger log = LogManager.getLogger(TXTCellUtil.class.getName() );
	
	private Map options;
	
    TXTCellUtil(Map options) {
    	
    	this.options = options;
    }
    
	/**
	 * getCellValue with column type
	 * if use this method, suggest setDefaultColumnStyle while create column header
	 * @param o
	 * @param columnType
	 * @param options
	 * @return
	 */
	public String getCellValueT(Object o, int columnType, int scale ){
		if( o == null ){
			return "";
		}else{
			switch(columnType){
			case Types.VARCHAR: //12
			case 1:
			case -9:
			case -15:
			case -1:
				return String.valueOf(o);
			case Types.NUMERIC: //2
			case 3:
			case 4:
			case 5:
			case 7:
			case 8:
			case -5:
			case -6:
			case 100:
			case 101:
				String format = "";//###.00
				
				switch(scale){
				case 0: 
					format = "###0";
					break;
				case 2: 
					format = "###0.00";
					break;
				case 1: 
					format = "###0.0";
					break;
				case 3:
					format = "###0.000";
					break;
				case 4: 
					format = "###0.0000";
					break;
				case 5:
					format = "###0.00000";
					break;
				case 6: 
					format = "###0.000000";
					break;
				default:
					format = "###0.00";
				}
				
				DecimalFormat decif = new DecimalFormat(format);
//		        System.out.println( df.format( o ) );
		        
				if(options.get("DecimalSymbol").toString().trim().equalsIgnoreCase(".")){
					return decif.format( o );
				}
				else
				{
					return decif.format( o ).replaceAll("\\.", options.get("DecimalSymbol").toString());
				}
			case Types.DATE://91
			case Types.TIMESTAMP: //93
				
//				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//				SimpleDateFormat format3 = new SimpleDateFormat("y-M-d H:m:s");
				
				String DateOrder = options.get("DateOrder").toString();
				String DateDelimiter = options.get("DateDelimiter").toString();
				String TimeDelimiter = options.get("TimeDelimiter").toString();
				String DateTimeOrder = options.get("DateTimeOrder").toString();
				
				boolean ZeroPaddingDate = Boolean.valueOf( options.get("ZeroPaddingDate").toString()).booleanValue();

				//Whenever time's minute and second is ZeroFilled
				String formatS = DateformatConverter4IE.convertCFG2format(DateOrder, DateDelimiter, TimeDelimiter, 
						DateTimeOrder, ZeroPaddingDate);
				
				SimpleDateFormat sdf = new SimpleDateFormat(
						formatS
				);
				
				return sdf.format( (java.util.Date)o );
			default :
				System.out.println("没有对应的列类型！");
				return String.valueOf(o);
			}
		}
	}

    
//	public static void main(String args[]) {
//
//	}
}
