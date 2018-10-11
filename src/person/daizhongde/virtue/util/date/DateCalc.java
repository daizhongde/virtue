package person.daizhongde.virtue.util.date;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
/**
 * 
 * java中对日期的加减操作
 * gc.add(1,-1)表示年份减一.
 * gc.add(2,-1)表示月份减一.
 * gc.add(3.-1)表示周减一.
 * gc.add(5,-1)表示天减一.
 * 以此类推应该可以精确的毫秒吧.没有再试.大家可以试试.
 * GregorianCalendar类的add(int field,int amount)方法表示年月日加减.
 * field参数表示年,月.日等.
 * amount参数表示要加减的数量
 * @author daizd
 *
 */
public class DateCalc {
	Date d = new Date();
	GregorianCalendar gc = new GregorianCalendar();
	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat sf2 = new SimpleDateFormat("yyyyMM");
	SimpleDateFormat sf3 = new SimpleDateFormat("dd");
	
	public String getYears() {
		gc.setTime(d);
		gc.add(1, +1);
		gc.set(gc.get(Calendar.YEAR), gc.get(Calendar.MONTH),
				gc.get(Calendar.DATE));
		return sf.format(gc.getTime());
	}

	public String getHalfYear() {
		gc.setTime(d);
		gc.add(2, +6);
		gc.set(gc.get(Calendar.YEAR), gc.get(Calendar.MONTH),
				gc.get(Calendar.DATE));
		return sf.format(gc.getTime());
	}

	public String getQuarters() {
		gc.setTime(d);
		gc.add(2, +3);
		gc.set(gc.get(Calendar.YEAR), gc.get(Calendar.MONTH),
				gc.get(Calendar.DATE));
		return sf.format(gc.getTime());
	}

	public String getLocalDate() {
		System.out.println(sf2.format(d.getTime()));
		System.out.println(sf3.format(d.getTime()));
		return sf.format(d);
	}

	public String getDays() {
		gc.setTime(d);
		gc.add(5, +4);
		gc.set(gc.get(Calendar.YEAR), gc.get(Calendar.MONTH),
				gc.get(Calendar.DATE));
		return sf.format(gc.getTime());
	}
	/**
	 * 方式一的话，只是通过日期来进行比较两个日期的相差天数的比较，没有精确到相差到一天的时间。如果是只是纯粹通过日期（年月日）来比较比较的话就是方式一。
	 * <p>
     * date2比date1多的天数
     * @param date1    
     * @param date2
     * @return    
     */
    public static int differentDays(Date date1,Date date2)
    {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
       int day1= cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);
        
        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if(year1 != year2)   //同一年
        {
            int timeDistance = 0 ;
            for(int i = year1 ; i < year2 ; i ++)
            {
                if(i%4==0 && i%100!=0 || i%400==0)    //闰年            
                {
                    timeDistance += 366;
                }
                else    //不是闰年
                {
                    timeDistance += 365;
                }
            }
            
            return timeDistance + (day2-day1) ;
        }
        else    //不同年
        {
            System.out.println("判断day2 - day1 : " + (day2-day1));
            return day2-day1;
        }
    }
    /**
     * 通过时间秒毫秒数判断两个时间的间隔
     * <p>
     * 对于方式二，是通过计算两个日期相差的毫秒数来计算两个日期的天数差的。一样会有一个小问题，就是当他们相差是23个小时的时候，它就不算一天了。如下面的两个日期
     * @param date1
     * @param date2
     * @return
     */
    public static int differentDaysByMillisecond(Date date1,Date date2)
    {
        int days = (int) ((date2.getTime() - date1.getTime()) / (1000*3600*24));
        return days;
    }
	public static void main(String[] args) {
		DateCalc ud = new DateCalc();
		System.out.println(ud.getLocalDate());
		System.out.println(ud.getYears());
		System.out.println(ud.getHalfYear());
		System.out.println(ud.getQuarters());
		System.out.println(ud.getDays());
	}
}
