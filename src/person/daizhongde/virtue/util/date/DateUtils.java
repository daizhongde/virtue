package person.daizhongde.virtue.util.date;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtils {
	private static SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
	
	/**
	 * string -> java.util.Date
	 * <p>
	 * For virtue
	 * @param args
	 */
	public static Date parseDate( String str ) {
		//1900-01-01 , 1900-01-01 00:00:01,
		//1900/01/01 , 1900/01/01 00:00:01,
		String[] s = str.toString().split("[-/ :]");
//		System.out.println("str:"+str);
		
		if( s.length == 3 ){//1900-01-01(parameter format)
//			System.out.println("s.length == 3 ");
			return new java.util.Date( Integer.valueOf( s[0] ).intValue() - 1900,
					Integer.valueOf( s[1] ).intValue() - 1,
					Integer.valueOf( s[2] ).intValue() );
			
		}else{//1900-01-01 00:00:01(parameter format, with time)
//			System.out.println("s.length != 3 ");
			return new java.util.Date( Integer.valueOf(s[0]).intValue()-1900,
					Integer.valueOf( s[1] ).intValue()-1,
					Integer.valueOf( s[2] ).intValue(),
					Integer.valueOf( s[3] ).intValue(),
					Integer.valueOf( s[4] ).intValue(),
					Integer.valueOf( s[5] ).intValue()
			);
			
		}
	}
	/**
	 * string -> java.sql.Date
	 * <p>
	 * For virtue
	 * @param args
	 */
	public static java.sql.Date parseSQLDate( String str ) {
		//1900-01-01 , 1900-01-01 00:00:01,
		//1900/01/01 , 1900/01/01 00:00:01,
		String[] s = str.toString().split("[-/ :]");
//		System.out.println("str:"+str);
		
		if( s.length == 3 ){//1900-01-01(parameter format)
//			System.out.println("s.length == 3 ");
			return new java.sql.Date( Integer.valueOf( s[0] ).intValue() - 1900,
					Integer.valueOf( s[1] ).intValue() - 1,
					Integer.valueOf( s[2] ).intValue() );
			
		}else{//1900-01-01 00:00:01(parameter format, with time)
//			System.out.println("s.length != 3 ");
			java.util.Date util_Date  = new java.util.Date( Integer.valueOf(s[0]).intValue()-1900,
					Integer.valueOf( s[1] ).intValue()-1,
					Integer.valueOf( s[2] ).intValue(),
					Integer.valueOf( s[3] ).intValue(),
					Integer.valueOf( s[4] ).intValue(),
					Integer.valueOf( s[5] ).intValue());
			java.sql.Date date = new java.sql.Date(util_Date.getTime());
			
			return date;
		}
	}
	/**
	 * string -> java.sql.Timestamp
	 * <p>
	 * For virtue
	 * @param args
	 */
	public static java.sql.Timestamp parseSQLTimestamp( String str ) {
		//1900-01-01 , 1900-01-01 00:00:01, 1900/01/01 00:00:01.000,
		//1900/01/01 , 1900/01/01 00:00:01, 1900/01/01 00:00:01.000,
		String[] s = str.toString().split("[-/ :.]");
//		System.out.println("str:"+str);
		str = str.replaceAll("/", "-");
		
		if( s.length == 3 ){//1900-01-01(parameter format)
			return java.sql.Timestamp.valueOf( str + " 00:00:00.000");
			
		}else if( s.length == 6 ){//1900-01-01 00:00:01(parameter format, with time)
//			System.out.println("s.length != 3 ");
			return java.sql.Timestamp.valueOf( str + ".000");
		}else{//1900-01-01 00:00:01(parameter format, with time)
//			System.out.println("s.length != 3 ");
			return java.sql.Timestamp.valueOf( str );
		}
	}
	/**
	 * 字符串转换成日期
	 * 
	 * @param str
	 * @return date
	 * @throws ParseException 
	 */
	public static Date Str2Date( String str, String fmt ) throws ParseException {

//		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		SimpleDateFormat format = new SimpleDateFormat( fmt );
		
		Date date = null;
		try {
			date = format.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
			throw e;
		}
		return date;
	}
	/**
	 * 字符串转换成日期
	 * <p>
	 * The precision of a Timestamp object is calculated to be either: 
		19 , which is the number of characters in yyyy-mm-dd hh:mm:ss 
		20 + s , which is the number of characters in the yyyy-mm-dd hh:mm:ss.[fff...] and s represents the scale of the given Timestamp, its fractional seconds precision. 
	 * @param str
	 * @return date
	 * @throws ParseException 
	 */
	public static Timestamp Str2Timestamp( String str, String fmt ) throws ParseException {
		return Timestamp.valueOf(str);
	}
	public static Timestamp Date2Timestamp( Date date ) throws ParseException {
		return new java.sql.Timestamp( date.getTime() );
	}
	public static Date Timestamp2Date( Timestamp ts ) {
		Date date = new Date();
		try {
			date = ts;
//			System.out.println(date);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return date;
	}
	/**
	 * get the day before
	 * @return
	 */
	public static Calendar getTheDayBefore(Date d){
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(d);
		gc.add(5, -1);
		gc.set(gc.get(Calendar.YEAR), gc.get(Calendar.MONTH),
				gc.get(Calendar.DATE));
		return gc;
	}
	
	public static Calendar getCaleCalendar(Date d, int field, int amount){
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(d);
		gc.add(field, amount);
		gc.set(gc.get(Calendar.YEAR), gc.get(Calendar.MONTH),
				gc.get(Calendar.DATE));
		return gc;
	}
	
	public static String getDateString(Date d, int field, int amount, String format){
		return new SimpleDateFormat( format ).format(getCaleCalendar(d,field, amount).getTime());
	}
	
	public static void main(String args[]){
//		Date date = parseDate( "1900/01/01 00:00:01" );
		Date date = parseDate( "1900/01/01" );
//		System.out.println("date:"+date.toString());
		String s = "1900/01/01 00:00:01";
		s = s.replaceAll("/", "-");
		System.out.println("s:"+s);
	}
}
