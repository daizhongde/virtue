package person.daizhongde.virtue.util.date;

import java.util.Calendar;

public class CalendarUtil {
	public static void main(String args[]){
		int y,m,d,h,mi,s;    
		Calendar cal=Calendar.getInstance();    
		y=cal.get(Calendar.YEAR);    
		m=cal.get(Calendar.MONTH)+1;    
		d=cal.get(Calendar.DATE);    
		h=cal.get(Calendar.HOUR_OF_DAY);    
		mi=cal.get(Calendar.MINUTE);    
		s=cal.get(Calendar.SECOND);    
		System.out.println("现在时刻是"+y+"年"+m+"月"+d+"日"+h+"时"+mi+"分"+s+"秒"); 
		String date = "";
		date = String.valueOf(y)+((String.valueOf(m).length()==1)?("0"+m):String.valueOf(m));
	}
}
