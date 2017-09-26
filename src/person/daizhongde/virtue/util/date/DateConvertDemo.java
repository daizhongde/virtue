package person.daizhongde.virtue.util.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DateConvertDemo {

	private static Logger log = LogManager.getLogger(DateConvertDemo.class.getName());

	/**
	 * 日期转换成字符串
	 * 
	 * @param date
	 * @return str
	 */
	public static String DateToStr(Date date) {

		SimpleDateFormat format = new SimpleDateFormat("yy-M-d HH:mm:ss");
		String str = format.format(date);
		return str;
	}
	/**
	 * 日期转换成字符串
	 * 
	 * @param date
	 * @return str
	 */
	public static String DateToStr2(Date date) {

		SimpleDateFormat format = new SimpleDateFormat("yy-M-d HH:mm:ss:SSS");
		String str = format.format(date);
		return str;
	}
	/**
	 * 字符串转换成日期
	 * 
	 * @param str
	 * @return date
	 */
	public static Date StrToDate(String str) {

		SimpleDateFormat format = new SimpleDateFormat("yy-M-d HH:mm:ss");
		Date date = null;
		try {
			date = format.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * YYYYMMDD YYYYMMDDHHNNSS YYYY-MM-DD DD-MM-YYYY MM-DD-YYYY
	 * YYYY-MM-DD-HHNNSS YYYY-MM-DD-HHNNSSAM/PM YYYY-MM-DD-HH-NN-SS
	 * YYYY-MM-DD-HHNNSS
	 * 
	 * yyyyMMdd yyyyMMddHHmmss yyyy-MM-dd dd-MM-yyyy MM-dd-yyyy
	 * yyyy-MM-dd-HHmmss yyyy-MM-dd-HHmmssa yyyy-MM-dd-HH-mm-ss
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		
		Date date = new Date();
		   System.out.println("日期转字符串：" + DateConvertDemo.DateToStr(date));
		   System.out.println("字符串转日期：" + DateConvertDemo.StrToDate(DateConvertDemo.DateToStr(date)));
	}

}
