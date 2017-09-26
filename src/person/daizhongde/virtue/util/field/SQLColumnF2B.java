package person.daizhongde.virtue.util.field;

/**
 * Description:\
 * F2B:front to back
 * <br/>网站: <a href="http://www.crazyjava.org">疯狂Java联盟</a> 
 * <br/>Copyright (C), 2003-2014, Dai zhongde 
 * <br/>This program is protected by copyright laws.
 * <p>for convert html field name to database table columnName
 * <br>most of time i equals -1, so judge ( i == -1 ) first
 * rule maked by dzd
 * @author dzd
 * @deprecated
 */
public class SQLColumnF2B {
	/**
	 * convert html field name to pure field (no alias)
	 * <br>when with alias,cut down (defaut alias's name's length is two, eg:t1, t2 ...)
	 * rule maked by dzd
	 * @param col (fieldName)
	 * @return
	 */
	public static String getFieldNameNoAlias(String field){
		int i = field.indexOf(".");
		if(i == -1){
			return field;
		}else{
			return field.substring(i+1);
		}
	}
	/**
	 * convert html field name to field with alias
	 * <br>when no alias add 't1.' before field, 't1.' is the default value, and is the only value , this program add
	 * rule maked by dzd
	 * @param col (fieldName)
	 * @return
	 */
	public static String getFieldNameWithTableAlias(String field){
		int i = field.indexOf(".");
		if(i == -1){
			return "t1."+field;
		}else{
			return field;
		}
	}
	
	/**
	 * convert html field name to database table columnName (no alias )
	 * <br>when with alias,cut down (defaut alias's name's length is two, eg:t1, t2 ...)
	 * rule maked by dzd
	 * @param field
	 * @return
	 */
	public static String getColumnNameNoAlias(String field){
		int i = field.indexOf(".");
		if(i == -1){
			return field.substring(0,1) + "_" + field.substring(1);
		}else{
			return field.substring(i+1,i+2) + "_" + field.substring(i+2);
		}
	}
	/**
	 * convert html field name to database table columnName with table's alias
	 * <br>when no alias add 't1.' before field, 't1.' is the default value, and is the only value , this program add
	 * rule maked by dzd
	 * @param col (fieldName)
	 * @return
	 */
	public static String getColumnNameWithTableAlias(String field){
		//return "t1."+getColumnNameNoAlias( field );//don't use this method because field may with different alias
		int i = field.indexOf(".");
		if(i == -1){
			return "t1."+field.substring(0,1) + "_" + field.substring(1);
		}else{
			return field.substring(0,i+2) + "_" + field.substring(i+2);
		}		
	}

	/**
	 * convert html field name to hibernate pojo name (no alias )
	 * <br>when with alias,cut down (defaut alias's name's length is two, eg:t1, t2 ...)
	 * rule maked by dzd
	 * @param field
	 * @return
	 */
	public static String getPOJOPropertyNoAlias(String field){
		int i = field.indexOf(".");
		if(i == -1){
			return field.substring(0, 2).toUpperCase() + field.substring(2).toLowerCase();
		}else{
			return field.substring(i+1, i+3).toUpperCase() + field.substring(i+3).toLowerCase();
		}
	}
	/**
	 * convert html field name to hibernate pojo name with alias
	 *  <br>when no alias add 't1.' before field, 't1.' is the default value, and is the only value , this program add
	 * rule maked by dzd
	 * @param field
	 * @return
	 */
	public static String getPOJOPropertyWithTableAlias(String field){
		int i = field.indexOf(".");
		if(i == -1){
			return "t1." + field.substring(0, 2).toUpperCase() + field.substring(2).toLowerCase();
		}else{
			//don't use <"t1."+> because field may with different alias
			return field.substring(0, 3) + field.substring(3, 5).toUpperCase() + field.substring(5).toLowerCase();
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	// Prevent instantiation
    private void SQLColumnF2B() {}
}
