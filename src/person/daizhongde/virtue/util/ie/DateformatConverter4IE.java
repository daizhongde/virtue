package person.daizhongde.virtue.util.ie;

import java.text.SimpleDateFormat;

public class DateformatConverter4IE {
	/**
	 * 导入导出时用来把配置中的日期格式转换成java中的日期格式 
	 * <p>
	 * default 
	 *   DateOrder				: "YMD",
	 *   DateDelimiter			: "/",
	 *   TimeDelimiter			: ":",
	 *   DateTimeOrder			: "DT",
	 *   
	 * to format is :  yyyy/MM/dd HH:mm:ss 
	 * 
	 * @return
	 */
	public static String convertCFG2format(String DateOrder, String DateDelimiter, String TimeDelimiter, 
			String DateTimeOrder, boolean ZeroPaddingDate ){
		String format = "";
//		boolean ZeroPaddingDate = true;
		
		String[] YMD = new String[3];
		String[] HMS = new String[3];
		
//		String DateOrder = options.get("DateOrder").toString();
		
		if( ZeroPaddingDate )
		{
			/* below jdk 1.6-  
			if( DateOrder.trim().equalsIgnoreCase("YMD") )
			{
				YMD[0] = "yyyy"; YMD[1] = "MM"; YMD[2] = "dd";
			}
			else if( DateOrder.trim().equalsIgnoreCase("YDM")  )
			{
				YMD[0] = "yyyy"; YMD[1] = "dd"; YMD[2] = "MM";
			}
			else if( DateOrder.trim().equalsIgnoreCase("MDY")  )
			{
				YMD[0] = "MM"; YMD[1] = "dd"; YMD[2] = "yyyy";
			}
			else if( DateOrder.trim().equalsIgnoreCase("DMY")  )
			{
				YMD[0] = "dd"; YMD[1] = "MM"; YMD[2] = "yyyy";
			}
			else if( DateOrder.trim().equalsIgnoreCase("DYM")  )
			{
				YMD[0] = "dd"; YMD[1] = "yyyy"; YMD[2] = "MM";
			}
			else if( DateOrder.trim().equalsIgnoreCase("MYD")  )
			{
				YMD[0] = "MM"; YMD[1] = "yyyy"; YMD[2] = "dd";
			}*/
			/*  below jdk 1.7+  */
			switch( DateOrder ){
			case "YMD": 
				YMD[0] = "yyyy"; YMD[1] = "MM"; YMD[2] = "dd";
				break;
			case "YDM": 
				YMD[0] = "yyyy"; YMD[1] = "dd"; YMD[2] = "MM";
				break;
			case "MDY": 
				YMD[0] = "MM"; YMD[1] = "dd"; YMD[2] = "yyyy";
				break;
			case "DMY": 
				YMD[0] = "dd"; YMD[1] = "MM"; YMD[2] = "yyyy";
				break;
			case "DYM": 
				YMD[0] = "dd"; YMD[1] = "yyyy"; YMD[2] = "MM";
				break;
			case "MYD": 
				YMD[0] = "MM"; YMD[1] = "yyyy"; YMD[2] = "dd";
				break;
			}
		}
		else
		{
			/* below jdk 1.6-  
			if( DateOrder.trim().equalsIgnoreCase("YMD") )
			{
				YMD[0] = "yy"; YMD[1] = "M"; YMD[2] = "d";
			}
			else if( DateOrder.trim().equalsIgnoreCase("YDM")  )
			{
				YMD[0] = "yy"; YMD[1] = "d"; YMD[2] = "M";
			}
			else if( DateOrder.trim().equalsIgnoreCase("MDY")  )
			{
				YMD[0] = "M"; YMD[1] = "d"; YMD[2] = "yy";
			}
			else if( DateOrder.trim().equalsIgnoreCase("DMY")  )
			{
				YMD[0] = "d"; YMD[1] = "M"; YMD[2] = "yy";
			}
			else if( DateOrder.trim().equalsIgnoreCase("DYM")  )
			{
				YMD[0] = "d"; YMD[1] = "yy"; YMD[2] = "M";
			}
			else if( DateOrder.trim().equalsIgnoreCase("MYD")  )
			{
				YMD[0] = "M"; YMD[1] = "yy"; YMD[2] = "d";
			} */
			/** below jdk 1.7+  */
			switch( DateOrder ){
			case "YMD": 
				YMD[0] = "yy"; YMD[1] = "M"; YMD[2] = "d";
				break;
			case "YDM": 
				YMD[0] = "yy"; YMD[1] = "d"; YMD[2] = "M";
				break;
			case "MDY": 
				YMD[0] = "M"; YMD[1] = "d"; YMD[2] = "yy";
				break;
			case "DMY": 
				YMD[0] = "d"; YMD[1] = "M"; YMD[2] = "yy";
				break;
			case "DYM": 
				YMD[0] = "d"; YMD[1] = "yy"; YMD[2] = "M";
				break;
			case "MYD": 
				YMD[0] = "M"; YMD[1] = "yy"; YMD[2] = "d";
				break;
			}
		}
		//Whenever time's minute and second is ZeroFilled
		HMS[0] = "HH"; HMS[1] = "mm"; HMS[2] = "ss";
		
		format = 
			YMD[0] +
			DateDelimiter +
			YMD[1]+
			DateDelimiter +
			YMD[2]+" "+
			HMS[0]+
			TimeDelimiter +
			HMS[1]+
			TimeDelimiter +
			HMS[2];

		return format;
	}
}
