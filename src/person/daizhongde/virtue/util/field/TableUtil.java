package person.daizhongde.virtue.util.field;

/**
 * Description:\
 * F2B:front to back
 * <br/>网站: <a href="http://www.crazyjava.org">疯狂Java联盟</a> 
 * <br/>Copyright (C), 2003-2014, Dai zhongde 
 * <br/>This program is protected by copyright laws.
 * <p>for convert html field name to database table columnName
 * <br/>for custom export add at 2013/12/25
 * <br/>most of time i equals -1, so judge ( i == -1 ) first
 * rule maked by dzd
 * 
 * 
 * @author dzd
 * write 2013/11/17
 * update 2013/12/25 add for custom export
 *
 */
public class TableUtil {
	
	public static String cvtTableName2frontPageName(String tableName){
		String ret = "";
		String[] a = tableName.split("\\_");
		ret = a[0].toLowerCase();
		for(int i = 1, j = a.length; i<j; i++ ){
			ret += ( a[i].substring(0, 1).toUpperCase() 
					+ a[i].substring(1).toLowerCase() );
		}
		return ret;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}
	// Prevent instantiation
    private void TableUtil() {}
}
