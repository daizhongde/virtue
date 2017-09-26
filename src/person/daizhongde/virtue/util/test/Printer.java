package person.daizhongde.virtue.util.test;

import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
/**
 * 打印map、list、array、vector,Enumeration, collection等集合数据
 * @author d144574
 * @date 2014-09-12
 * @modify by d144574
 * @modify date 2014-09-14
 */
public class Printer {
	
	public static void print(Map map){
		System.out.println("######    map   #########");
		Set set = map.keySet();
		Iterator it = set.iterator();
		Object key;
		while(it.hasNext()){
			key = it.next();
			System.out.println(key+":"+(map.get(key)!=null?map.get(key).toString():""));
		}
		System.out.println( "##### size:" + map.size()+"  ######" );
	}
	
	public static void print(List list){
		System.out.println("######    list   #########");
		for(int i=0, j=list.size(); i<j; i++){
			System.out.println(i+":"+(list.get(i)!=null?list.get(i).toString():""));
		}
		System.out.println( "##### size:" + list.size()+"  ######" );
	}
	public static void print(Collection coll){
		System.out.println("######    coll   #########");
		if (coll instanceof java.util.List ){
			print( (List) coll );
			return;
		}
		Iterator it = coll.iterator();
				
		for(int i=0, j=coll.size(); it.hasNext(); i++){
			Object o =  it.next();
			System.out.println(i+":"+( o!=null?o.toString():""));
		}
		System.out.println( "##### size:" + coll.size()+"  ######" );
	}
	
	public static void print(Object[] arr){
		System.out.println("######    arr   #########");
		for(int i=0, j=arr.length; i<j; i++){
			System.out.println(i+":"+(arr[i]!=null?arr[i].toString():""));
		}
		System.out.println( "##### length:" + arr.length+"  ######" );
	}
	
	public static void print(Enumeration enu){
		System.out.println("######    enu   #########");
		int i=0;
		
		while(enu.hasMoreElements()){
			i++;
			Object o = enu.nextElement();
			System.out.println(i+":"+(o!=null?o.toString():""));
		}
		System.out.println( "##### length:" + i+"  ######" );
	}
	
	public static void print(Vector v){
		int i=0;
		for( int j=v.size(); i<j; i++ ){
			Object o = v.get(i);
			System.out.println(i+":"+(o!=null?o.toString():""));
		}
		System.out.println( "length:" + i );
	}
//	public static void print(javax.servlet.http.HttpServletRequest request){
//		System.out.println("######    print request parameter   #########");
//		Enumeration enume = request.getParameterNames();
//		while( enume.hasMoreElements() ){
//			String parameterName = enume.nextElement().toString();
//			System.out.println( parameterName+":"+request.getParameter( parameterName ) );
//		}
//	}
	public static void printJSON(Object o ){
//		if ( o instanceof net.sf.json.JSONArray ){
//		if ( o.getClass().isArray() ){
		try
		{
			System.out.println( "JSONArray:\n"+net.sf.json.JSONArray.fromObject(o).toString() );
		}
		catch(Exception e)
		{
			try{
				System.out.println( "JSONObject:\n"+net.sf.json.JSONObject.fromObject(o).toString() );
			}
			catch(Exception e2)
			{
				System.out.println("Neither JSONArray nor JSONObject!" );
			}
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
