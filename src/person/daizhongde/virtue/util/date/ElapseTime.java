package person.daizhongde.virtue.util.date;

import java.io.StringReader;
import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.Semaphore;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ElapseTime {

	public static void main(String args[]){
		Timestamp beginTime = new Timestamp( new Date().getTime() - 2*60*1000 );
		Timestamp endTime = new Timestamp(new Date().getTime());
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long diff = endTime.getTime() - beginTime.getTime();
		
		long days = diff / (1000 * 60 * 60 * 24);
 		 
		long hours = (diff-days*(1000 * 60 * 60 * 24))/(1000* 60 * 60);
		long minutes = (diff-days*(1000 * 60 * 60 * 24)-hours*(1000* 60 * 60))/(1000* 60);
		long seconds = (diff-days*(1000 * 60 * 60 * 24)-hours*(1000* 60 * 60) - minutes*(1000* 60))/1000;
//		System.out.println(""+days+"天"+hours+"小时"+minutes+"分");
//		System.out.println(" use time: "+days+" day "+hours+" hour "+minutes+" minute "+seconds+" seconds");
		  
//		System.out.println("millisecond:"+millisecond);
		
//		millisecond -= (2+24*365*1971)*60*60*1000;
//		long second = (endTime.getTime() - beginTime.getTime())/1000;
//		System.out.println("millisecond:"+millisecond);
		
		Calendar c1 = GregorianCalendar.getInstance();
		c1.setTimeInMillis(diff);
		//1970-01-01 03:02:00
//		c1.roll( Calendar.YEAR, 1970 );
		
//		d.setYear(0000);
//		d.setHours(00);
//		d.setDate(new Long(days).intValue());
//		d.setHours(new Long(hours).intValue());
//		d.setMinutes(new Long(minutes).intValue());
//		d.setSeconds(new Long(seconds).intValue());
		Timestamp t=  new Timestamp(diff);
		
		System.out.println( "format.format( d ):"+format.format( t.getTime() ) );
		
		
		System.out.println("The year now time is " + c1.get(c1.YEAR));
		System.out.println("The month now time is " + c1.get(c1.MONTH));
		System.out.println("The day_of_month now time is " + c1.get(c1.DAY_OF_MONTH));
	        
		
	}

}