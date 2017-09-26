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
public class FIELDUtil {
	
	public static String getAlias(String front){
		int i = front.indexOf(".");
		if(i == -1 || front.substring(0,i).length() >3 ){//TAuthorityUserByNHcreator.NUid ---HQL
			return "t1";
		}else{
			return front.substring(0,i);
		}
	}
	/**
	 * HQL use get pure coluen, like TAuthorityUserByNHtarget.NUid  --> return NUid
	 * @param front
	 * @return
	 */
	public static String getHQLColumnSuffix(String HQLColumn){
		int i = HQLColumn.lastIndexOf(".");
		if(i == -1  ){//TAuthorityUserByNHcreator.NUid ---HQL
			return HQLColumn;
		}else{
			return HQLColumn.substring(i+1);
		}
	}
	
	/**
	 * convert html field name to pure field (no alias)
	 * <br>when with alias,cut down (defaut alias's name's length is two, eg:t1, t2 ...)
	 * rule maked by dzd
	 * @param front 
	 * @return
	 */
	public static String getFrontNoAlias(String front){
		int i = front.indexOf(".");
		if(i == -1 || front.substring(0,i).length() >3 ){
			return front;
		}else{
			return front.substring(i+1);
		}
	}

	/**
	 * only used in class
	 * @see     person.daizhongde.virtue.configutils.JSQLParser
	 * @param front
	 * @return
	 */
	public static String getColumnLabel(String label){
		int i = label.indexOf(".");
		if(i == -1 || label.substring(0,i).length() >3 ){
			return label;
		}else{
			return label.substring(i+1);
		}
	}
	/** generate field getmethod's Name  */
	public static String getMethodName(String field){
		return "get" + Character.toString( field.charAt(0) ).toUpperCase() + field.substring(1);
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String col = "TAuthorityUserByNHcreator.NUid";
//		this.getHQLColumnSuffix(col);
		System.out.println("s = "+  getHQLColumnSuffix(col) );
	}
	// Prevent instantiation
    private void FIELDUtil() {}
}
