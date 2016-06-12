package person.daizhongde.virtue.util.ie;

import java.sql.Types;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
/**
 * oracle11gR2: <br>
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
 * <p>
 * SQLServer2008R2<br>
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
 * <p>
 * MySQL 5.1<br>
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
public class POICellUtil {
	
	/**
	 * setCellValue with column type
	 * if use this method, suggest setDefaultColumnStyle while create column header
	 * @param o value
	 * @param cell
	 * @param columnType SQL Type
	 */
	public static void setCellValueT(Object o, Cell cell, int columnType ){
		if( o == null ){
			cell.setCellValue( "" );
			return;
		}else{
			switch(columnType){
			case Types.VARCHAR: //12
			case 1:
			case -9:
			case -15:
			case -1:
//				System.out.println(null==cell);
//				System.out.println("cell:"+cell);
//				System.out.println("cell.toString():"+cell.toString());
				cell.setCellValue( String.valueOf(o));
				break;
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
				cell.setCellValue( Double.valueOf( String.valueOf(o) ).doubleValue() );
				break;
				/*switch(scale){
				case 0: 
					cell.setCellValue( Double.valueOf( String.valueOf(o) ) );
					break;
				case 2: 
					cell.setCellValue( Double.valueOf( String.valueOf(o) ) );
					break;
				case 1: 
					cell.setCellValue( Double.valueOf( String.valueOf(o) ) );
					break;
				case 3:
					cell.setCellValue( Double.valueOf( String.valueOf(o) ) );
					break;
				case 4: 
					cell.setCellValue( Double.valueOf( String.valueOf(o) ) );
					break;
				default:
					cell.setCellValue( Double.valueOf( String.valueOf(o) ) );
				}
				break;*/
			case Types.DATE://91
				cell.setCellValue( (java.sql.Date)o );
				break;
			case Types.TIMESTAMP: //93
				cell.setCellValue( (Date)o );
				break;
			default : System.out.println("没有对应的列类型！");
			}
		}
	}
	/**
	 * setCellValue with column type
	 * if use this method, suggest setDefaultColumnStyle while create column header
	 * @param o value
	 * @param cell
	 * @param columnType SQL Type
	 */
	public static Object getCellValueT(CellValue cellValue, int columnType ){
		
		switch(columnType){
		case Types.VARCHAR: //12
		case 1:
		case -9:
		case -15:
		case -1:
			return cellValue.getStringValue();
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
			return cellValue.getNumberValue();
		case Types.DATE://91
			return cellValue.getStringValue();
			
		case Types.TIMESTAMP: //93
			return cellValue.getStringValue();
		default : System.out.println("没有对应的列类型！");
			return "";
		}
	}
	/**
	 * setCellValue with cell style and column type
	 * @deprecated this method is not suggest, use setCellValueT and setDefaultColumnStyle instead
	 * @param o value
	 * @param cell
	 * @param cs cell style
	 * @param columnType SQL Type
	 * @param scale  number of digits to right of the decimal point
	 */
	public static void setCellValueST(Object o, Cell cell, POICellStyle cs, int columnType, int scale){
		
		switch(columnType){
		case 12:
		case 1:
		case -9:
		case -15:
		case -1:
			cell.setCellStyle(cs.text); 
			cell.setCellValue( o == null ? "" : String.valueOf(o));
			break;
		case 2: 
		case 3: 
		case 4: 
		case 5: 
		case 7:
		case 8:
		case -5:
		case -6:
		case 100:  	
		case 101:
			switch(scale){
			case 0: 
				cell.setCellStyle(cs.numberNCP0); 
				if(o == null){
					cell.setCellValue( "" );
				}else{
					cell.setCellValue( Double.valueOf( String.valueOf(o) ).doubleValue() ); 
				}
				break;
			case 2: 
				cell.setCellStyle(cs.numberNCP2); 
				if(o == null){
					cell.setCellValue( "" );
				}else{
					cell.setCellValue( Double.valueOf( String.valueOf(o) ).doubleValue() ); 
				}
				break;
			case 1: 
				cell.setCellStyle(cs.numberNCP1); 
				if(o == null){
					cell.setCellValue( "" );
				}else{
					cell.setCellValue( Double.valueOf( String.valueOf(o) ).doubleValue() ); 
				}
				break;
			case 3:
				cell.setCellStyle(cs.numberNCP3); 
				if(o == null){
					cell.setCellValue( "" );
				}else{
					cell.setCellValue( Double.valueOf( String.valueOf(o) ).doubleValue() ); 
				}
				break;
			case 4: 
				cell.setCellStyle(cs.numberNCP4); 
				if(o == null){
					cell.setCellValue( "" );
				}else{
					cell.setCellValue( Double.valueOf( String.valueOf(o) ).doubleValue() ); 
				}
				break;
			default:
				cell.setCellStyle(cs.numberNCP2); 
				if(o == null){
					cell.setCellValue( "" );
				}else{
					cell.setCellValue( Double.valueOf( String.valueOf(o) ).doubleValue() ); 
				}
			}
			break;
		case Types.DATE://91
		case 93: 
			cell.setCellStyle( cs.datetime ); //日期
			if(o == null){
				cell.setCellValue( "" );
			}else{
				cell.setCellValue( (Date)o );
			}
			break;
		default : System.out.println("没有对应的列类型！");
		}
	}
	/**
	 * sets the default column style for a given column.
	 * @param sheet
	 * @param column the column index, start from 0
	 * @param cs
	 * @param columnType
	 * @param scale number of digits to right of the decimal point
	 */
	public static void setDefaultColumnStyle(HSSFSheet sheet, int column, POICellStyle cs, int columnType, int scale){
		
		switch(columnType){
		case Types.VARCHAR: //12
		case 1:
		case -9:
		case -15:
		case -1:
			sheet.setDefaultColumnStyle( (short)column,  cs.text);
			break;
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
			switch(scale){
			case 0: 
				sheet.setDefaultColumnStyle( (short)column,  cs.numberNCP0);
				break;
			case 2: 
				sheet.setDefaultColumnStyle( (short)column,  cs.numberNCP2);
				break;
			case 1: 
				sheet.setDefaultColumnStyle( (short)column,  cs.numberNCP1);
				break;
			case 3:
				sheet.setDefaultColumnStyle( (short)column,  cs.numberNCP3);
				break;
			case 4:
				sheet.setDefaultColumnStyle( (short)column,  cs.numberNCP4);
				break;
			case 5:
				sheet.setDefaultColumnStyle( (short)column,  cs.numberNCP5);
				break;
			case 6: 
				sheet.setDefaultColumnStyle( (short)column,  cs.numberNCP6);
				break;
			case 9: 
				sheet.setDefaultColumnStyle( (short)column,  cs.rateP9);
				break;
			case 10:
				sheet.setDefaultColumnStyle( (short)column,  cs.numberNCP10);
				break;
			default:
				sheet.setDefaultColumnStyle( (short)column,  cs.numberNCP2);
			}
			break;
		case Types.DATE://91
		case Types.TIMESTAMP: //93
			sheet.setDefaultColumnStyle( (short)column,  cs.datetimeZF);
			break;
		default : System.out.println("没有对应的列类型！");
		}
	}
	
	// Prevent instantiation
    private void POICellUtil() {}
    
//	public static void main(String args[]) {
//
//	}
}
