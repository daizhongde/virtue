package person.daizhongde.virtue.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateMemery {
	private static int date = new Integer( new SimpleDateFormat("yyyyMMdd").format( new Date() ) ).intValue();
	/**
	 * 取得当前(最后一次使用的)六位序号,类似于oracle中的sequence
	 * @return
	 */
	public static int currval(){
		return date;
	}
	public static void setDate( int dbdate ){
		date = dbdate;
	}
}
