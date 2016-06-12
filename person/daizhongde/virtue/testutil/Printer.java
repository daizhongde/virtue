package person.daizhongde.virtue.testutil;

import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 打印map、list、array、vector,Enumeration, collection等集合数据
 * @author d144574
 * @date 2014-09-12
 * @modify by d144574
 * @modify date 2014-09-14
 */
public class Printer {
	private static Logger log = LoggerFactory.getLogger( Printer.class);
	
	public static void print(Map map){
		log.debug("######    map   #########");
		Set set = map.keySet();
		Iterator it = set.iterator();
		Object key;
		while(it.hasNext()){
			key = it.next();
			log.debug(key+":"+(map.get(key)!=null?map.get(key).toString():""));
		}
		log.debug( "##### size:" + map.size()+"  ######" );
	}
	
	public static void print(List list){
		log.debug("######    list   #########");
		for(int i=0, j=list.size(); i<j; i++){
			log.debug(i+":"+(list.get(i)!=null?list.get(i).toString():""));
		}
		log.debug( "##### size:" + list.size()+"  ######" );
	}
	public static void print(Collection coll){
		log.debug("######    coll   #########");
		if (coll instanceof java.util.List ){
			print( (List) coll );
			return;
		}
		Iterator it = coll.iterator();
				
		for(int i=0, j=coll.size(); it.hasNext(); i++){
			Object o =  it.next();
			log.debug(i+":"+( o!=null?o.toString():""));
		}
		log.debug( "##### size:" + coll.size()+"  ######" );
	}
	
	public static void print(Object[] arr){
		log.debug("######    arr   #########");
		for(int i=0, j=arr.length; i<j; i++){
			log.debug(i+":"+(arr[i]!=null?arr[i].toString():""));
		}
		log.debug( "##### length:" + arr.length+"  ######" );
	}
	
	public static void print(Enumeration enu){
		log.debug("######    enu   #########");
		int i=0;
		
		while(enu.hasMoreElements()){
			i++;
			Object o = enu.nextElement();
			log.debug(i+":"+(o!=null?o.toString():""));
		}
		log.debug( "##### length:" + i+"  ######" );
	}
	
	public static void print(Vector v){
		int i=0;
		for( int j=v.size(); i<j; i++ ){
			Object o = v.get(i);
			log.debug(i+":"+(o!=null?o.toString():""));
		}
		log.debug( "length:" + i );
	}
//	public static void print(javax.servlet.http.HttpServletRequest request){
//		log.debug("######    print request parameter   #########");
//		Enumeration enume = request.getParameterNames();
//		while( enume.hasMoreElements() ){
//			String parameterName = enume.nextElement().toString();
//			log.debug( parameterName+":"+request.getParameter( parameterName ) );
//		}
//	}
	public static void printJSON(Object o ){
//		if ( o instanceof net.sf.json.JSONArray ){
//		if ( o.getClass().isArray() ){
		try
		{
			log.info( "JSONArray:\n"+net.sf.json.JSONArray.fromObject(o).toString() );
		}
		catch(Exception e)
		{
			try{
				log.info( "JSONObject:\n"+net.sf.json.JSONObject.fromObject(o).toString() );
			}
			catch(Exception e2)
			{
				log.info( "Neither JSONArray nor JSONObject!" );
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
