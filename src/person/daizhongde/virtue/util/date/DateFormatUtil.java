package person.daizhongde.virtue.util.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DateFormatUtil {

	private static Logger log = LogManager.getLogger(DateFormatUtil.class.getName());

	/**
	 * java.sql.Timestamp->String 2011-07-02 22:19:06.015 -> 2011年07月02日 22:45
	 * 
	 * @param timpstamp
	 * @return
	 */
	public String TimpStampToString1(java.sql.Timestamp timpstamp) {
		SimpleDateFormat stm = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
		return stm.format(timpstamp);
	}

	/**
	 * java.sql.Timestamp->String 2011-07-02 22:19:06.015 -> 2011-07-02 23:20:53
	 * 
	 * @param timpstamp
	 * @return
	 */
	public String TimpStampToString2(java.sql.Timestamp timpstamp) {
		// new SimpleDateFormat("yyyy-MM-dd- HH:mm:ss").format(new Date());
		SimpleDateFormat stm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return stm.format(timpstamp);
	}

	public void testCalendar() {
		Calendar c1 = Calendar.getInstance();
		c1.setTime(new Date());

		// 当Calendar中设置的时间超过每项的最大值时,会以减去最大值后的值设置时间,例如月份设置13,最后会变成13-11=02
		Calendar c2 = Calendar.getInstance();
		c2.set(1920, 13, 24, 22, 32, 22);
		// 使用pattern
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd H:m:s");
		SimpleDateFormat format2 = new SimpleDateFormat("yy-MM-dd H:m:s");
		SimpleDateFormat format3 = new SimpleDateFormat("y-M-d H:m:s");

		// 使用约定格式
		DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.FULL,
				Locale.getDefault()); // 获取Calendar中各个属性字段的方法

		log.info("The year now time is " + c1.get(c1.YEAR));
		log.info("The month now time is " + c1.get(c1.MONTH));
		log.info("The day_of_month now time is " + c1.get(c1.DAY_OF_MONTH));
		log.info("The day_of_week now time is " + c1.get(c1.DAY_OF_WEEK));
		log.info("今天是在这个月的第几个星期: " + c1.get(c1.DAY_OF_WEEK_IN_MONTH));
		log.info("The day_of_year now time is " + c1.get(c1.DAY_OF_YEAR));
		// 不同模式对应的格式略有不同,有时间可以测试多一点模式
		log.info("yyyy-MM-dd H:m:s-->" + format.format(c1.getTime()));
		log.info("yy-MM-dd H:m:s-->" + format2.format(c1.getTime()));
		log.info("y-M-d H:m:s-->" + format3.format(c1.getTime()));
		log.info("DateFormat.FULL-->" + dateFormat.format(c1.getTime()));
		log.info(format.format(c2.getTime()));
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
		SimpleDateFormat stm = new SimpleDateFormat(
				"yyyy年MM月dd日 HH时mm分ss秒.S毫秒 E");
		SimpleDateFormat stm1 = new SimpleDateFormat("yy年MM月dd日 HH时mm分ss秒 E");
		// SimpleDateFormat stm11=new SimpleDateFormat("y年MM月dd日 HH时mm分ss秒 E");
		SimpleDateFormat stm2 = new SimpleDateFormat("yyyy年MM月dd日 hh时mm分ss秒 a");
		SimpleDateFormat stm3 = new SimpleDateFormat("yyyy年MM月dd日 hh时mm分ss秒 E",
				Locale.ENGLISH);
		SimpleDateFormat stm4 = new SimpleDateFormat(
				"yyyy年MM月dd日 hh时mm分ss秒 EEEE", Locale.ENGLISH);
		SimpleDateFormat stm5 = new SimpleDateFormat("yyyy年MM月dd日 hh时mm分ss秒 a",
				Locale.ENGLISH);

		SimpleDateFormat stm6 = new SimpleDateFormat("yyMMdd-HHmmss.S",
				Locale.ENGLISH);
		SimpleDateFormat stm7 = new SimpleDateFormat("yyMMdd-HHmmss.S",
				Locale.ENGLISH);

		// java.lang.Object
		// |-java.util.Date
		// |-java.sql.Timestamp
		// 2011年01月07日 20时52分09秒.353毫秒 星期五
		System.out.println(stm.format(new Date()));
		// 11年01月07日 20时48分34秒 星期五
		System.out.println(stm1.format(new Date()));
		// 1年01月07日 20时48分34秒 星期五
		// System.out.println(stm11.format(new Date()));
		// 2011年01月07日 08时53分56秒 下午
		System.out.println(stm2.format(new Date()));
		// 2011年01月18日 08时32分51秒 Tue
		System.out.println(stm3.format(new Date()));
		// 2011年01月18日 08时38分52秒 Tuesday
		System.out.println(stm4.format(new Date()));
		// 2011年01月07日 09时07分32秒 PM
		System.out.println(stm5.format(new Date()));
		System.out.println(stm6.format(new Date()));
		System.out.println(stm7.format(new Date()));

		// DateFormatUtil dfu = new DateFormatUtil();
		// dfu.testCalendar();
	}

}
