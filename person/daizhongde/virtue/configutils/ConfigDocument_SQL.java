package person.daizhongde.virtue.configutils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


/**
 * 
 * a file can include mutil-SQLDocument
 * 
--@JavaScript AuthorityModule.Query
--@JavaScript AuthorityModule.Add
--@JavaScript AuthorityModule.Update
--@JavaScript AuthorityModule.Read
--@JavaScript AuthorityModule.Del

--@JavaScript AuthorityModule.Combobox
--@JavaScript AuthorityModule.Nest
--@JavaScript AuthorityModule.Export

 * @author dzd
 *
 */
public class ConfigDocument_SQL {

	/**
	 * a file can include mutil-SQLDocument
	 */
	private String name;
	
	/** JDK 1.4 private Map<String,SQLNode> Query;  
	private Map Query;
	private Map Add;
	private Map Update;
	private Map Read;
	private Map Del;
	
	private Map Combobox;
	private Map Tree;
	private Map Nest;
	private Map Export;*/
	
	/** JDK 1.5 private Map<String,SQLNode> Query;  */
	private Map<String,SQLNode> Query;
	private Map<String,SQLNode> Add;
	private Map<String,SQLNode> Update;
	private Map<String,SQLNode> Read;
	private Map<String,SQLNode> Del;
	
	private Map<String,SQLNode> Combobox;
	private Map<String,SQLNode> Tree;
	private Map<String,SQLNode> Nest;
	private Map<String,SQLNode> Export;
	
	public ConfigDocument_SQL(){
		
	}
	
	public ConfigDocument_SQL(String name ){
		this.name = name;
	}
	/**
	 * "var"
	 * "add" 
	 * "update" 
	 * "read" 
	 * 		"Read.read.NativeSQL"
	 *  	"Read.read.HQL" 
	 *  	"Read.read.JPQL" 
	 * "del"
	 * 
	 * "query" 
	 * 		"Query.query.NativeSQL"
	 * 		"Query.query.HQL"  
	 * 		"Query.query.JPQL" 
	 * "export" 
	 * 		"Export.export.NativeSQL"
	 * 		"Export.export.HQL" 
	 * 		"Export.export.JPQL" 
	 * "combobox" 
	 * 		"Combobox.combobox.NativeSQL" 
	 * 		"Combobox.combobox.JPQL" 
	 *		"Combobox.combobox.HQL" 
	 * "nest" 
	 * 		"Nest.nest.NativeSQL"
	 * 		"Nest.nest.HQL" 
	 * 		"Nest.nest.JPQL" 
	 * 
	 * 
	 * @param key
	 * @param value
	 */
	public void setValue(String key, String value){
//		System.out.println("key:"+key);
		String[] a = key.split("\\.");
//		System.out.println("a.length:"+a.length);
		String VIRTUEType;//query 
		String SQLName;//query query1 query2
		String SQLType;//NativeSQL HQL JPQL--->SQL HQL JPQL  update by daizhongde 2014/3/5
		if( a.length==3 ){
//			System.out.println("Yes! I contains '.'");
			VIRTUEType = a[0];
			SQLName = a[1];
			SQLType = a[2];
//			Map map = this.getQuery();
			Map map=null;
		    try 
		    {
//				Class classType = Class.forName("com.virtue.sqlview.util.SQLDocument");
				//开始访问私有成员变量   
		        Field nameField = ConfigDocument_SQL.class.getDeclaredField( VIRTUEType );  
		        nameField.setAccessible(true);  
		        map = (Map)nameField.get(this);//修改私有变量name的值   
		        
//		    	Method method = this.getClass().getMethod( "get"+VIRTUEType );
//		    	map = (Map)method.invoke( this );
			} 
		    catch (Exception e) 
		    {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
			map = map==null? new HashMap() : map;
			SQLNode node;
			if( map.get( SQLName ) == null ){
				node = new SQLNode();
			}else{
				node = (SQLNode)map.get( SQLName );
			}
			//use java reflect
//			node.setHQL(value);
			Object[] args = new Object[1];
			args[0] = value;
			Class[] parameterTypes = new Class[1];
			parameterTypes[0] = String.class;
		    try {
		    	Method method = SQLNode.class.getMethod( "set"+SQLType, parameterTypes );
		    	method.invoke( node , args );
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			map.put( SQLName, node );
//			this.setQuery(map);
			Object[] args1 = new Object[1];
			args1[0] = map;
			Class[] parameterTypes2 = new Class[1];
			parameterTypes2[0] = Map.class;
			try {
		    	Method method = this.getClass().getMethod( "set"+VIRTUEType, parameterTypes2 );
		    	method.invoke( this, args1 );
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, SQLNode> getQuery() {
		return Query;
	}

	public void setQuery(Map<String, SQLNode> query) {
		Query = query;
	}

	public Map<String, SQLNode> getAdd() {
		return Add;
	}

	public void setAdd(Map<String, SQLNode> add) {
		Add = add;
	}

	public Map<String, SQLNode> getUpdate() {
		return Update;
	}

	public void setUpdate(Map<String, SQLNode> update) {
		Update = update;
	}

	public Map<String, SQLNode> getRead() {
		return Read;
	}

	public void setRead(Map<String, SQLNode> read) {
		Read = read;
	}

	public Map<String, SQLNode> getDel() {
		return Del;
	}

	public void setDel(Map<String, SQLNode> del) {
		Del = del;
	}

	public Map<String, SQLNode> getCombobox() {
		return Combobox;
	}

	public void setCombobox(Map<String, SQLNode> combobox) {
		Combobox = combobox;
	}

	public Map<String, SQLNode> getTree() {
		return Tree;
	}

	public void setTree(Map<String, SQLNode> tree) {
		Tree = tree;
	}

	public Map<String, SQLNode> getNest() {
		return Nest;
	}

	public void setNest(Map<String, SQLNode> nest) {
		Nest = nest;
	}

	public Map<String, SQLNode> getExport() {
		return Export;
	}

	public void setExport(Map<String, SQLNode> export) {
		Export = export;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

}
