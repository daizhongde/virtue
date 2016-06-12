package person.daizhongde.virtue.configutils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 
 * a file can include mutil-JSDocument
 * 
var AuthorityModule = {};
AuthorityModule.Field
AuthorityModule.export

 * @author dzd
 *
 */
public class ConfigDocument_JS {

	/**
	 * a file can include mutil-SQLDocument
	 * 
	 */
	private String name;
	
//	JSFieldNode _fieldMap = new JSFieldNode(JSONObject.fromObject(JSMAP.get("FIELD")));
	private JSFieldNode field;
	
//	JSExportNode _expMap = new JSExportNode(JSONObject.fromObject(JSMAP.get("EXP")));
	private Map<String,JSExportNode>  export;
//	private Map export;
	
	/** because import is java keyword, so UpperCase
	 * <p>JDK 1.5 Map<String,JSImportNode> */
//	private Map IMPORT;
	private Map<String,JSImportNode>  IMPORT;
	
	public ConfigDocument_JS(){
		
	}
	
	public ConfigDocument_JS(String name ){
		this.name = name;
	}
	/**
	 * "var"
	 * "field" 
	 *      "Field"
	 * "export" 
	 * 		"Export.export.ColumnMap"
	 *  	"Export.export.DefaultColumns" 
	 *  	
	 * @param key
	 * @param value
	 */
	public void setValue(String key, String value){
//		System.out.println( "key:" + key );
//		System.out.println( "value:" + value );
		if(key.equalsIgnoreCase("Field")){
			field = new JSFieldNode(JSONObject.fromObject(value));
			return;
		}
		String[] a = key.split("\\.");
//		System.out.println("a.length:"+a.length);
		String VIRTUEType = a[0];//Export
		String name1;//export export1 export2
		String name2;//ColumnMap DefaultColumns
		
		if( a.length==3 && VIRTUEType.trim().equalsIgnoreCase("Export") )
		{
			
//			System.out.println("Yes! I contains '.'");
			name1 = a[1];
			name2 = a[2];
			Map map = this.getExport();
			
			map = map==null? new HashMap() : map ;
			
			JSExportNode node;
			if( map.get( name1 ) == null )
			{
				node = new JSExportNode();
			}
			else
			{
				node = (JSExportNode)map.get( name1 );
			}
			
			//use java reflect
//			node.setColumnMap(JSONObject.fromObject(value));
//			node.setDefaultColumns(value);
			Object[] args = new Object[1];
			
		    try {
		    	Class[] parameterTypes = new Class[1];
		    	/** below jdk 1.6-  */
		    	if(name2.trim().equalsIgnoreCase("ColumnMap"))
		    	{
		    		parameterTypes[0] = Map.class;
		    		args[0] = JSONObject.fromObject(value);
		    	}
		    	else if(name2.trim().equalsIgnoreCase("DefaultColumns"))
		    	{
		    		parameterTypes[0] = List.class;
		    		args[0] = JSONArray.fromObject(value);
		    	}
		    	else
		    	{
		    		System.out.println("Error!"+this.getClass().getName());
		    		throw new Exception("严重错误！");
		    	}
		    	/** below jdk 1.7+  */
//		    	switch(name2){
//		    	case "ColumnMap": 
//		    		parameterTypes = Map.class;
//		    		args[0] = JSONObject.fromObject(value);
//		    		break;
//		    	case "DefaultColumns": 
//		    		parameterTypes = List.class;
//		    		args[0] = JSONArray.fromObject(value);
//		    		break;
//		    	default: System.out.println("No such property!<JSDocument>Export");
//		    	}
		    	Method method = JSExportNode.class.getMethod( "set"+name2, parameterTypes );
		    	method.invoke( node , args );
			}
		    catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			map.put( name1, node );
			this.setExport(map);
		}
		else if( a.length==3 && VIRTUEType.trim().equalsIgnoreCase("Import") )
		{
			name1 = a[1];
			name2 = a[2];
			Map map = this.getImport();
			
			map = map==null? new HashMap() : map ;
			
			JSImportNode node;
			if( map.get( name1 ) == null )
			{
				node = new JSImportNode();
			}
			else
			{
				node = (JSImportNode)map.get( name1 );
			}
			
			Object[] args = new Object[1];
		    try
		    {
		    	Class[] parameterTypes = new Class[1];
		    	/** below jdk 1.6-  */
		    	if(name2.trim().equalsIgnoreCase("ColumnMap"))
		    	{
		    		parameterTypes[0] = Map.class;
		    		args[0] = JSONObject.fromObject(value);
		    	}
		    	else if(name2.trim().equalsIgnoreCase("DefaultColumns"))
		    	{
		    		parameterTypes[0] = List.class;
		    		args[0] = JSONArray.fromObject(value);
		    	}
		    	else
		    	{
		    		System.out.println("Error!"+this.getClass().getName());
		    		throw new Exception("严重错误！");
		    	}
		    	/** below jdk 1.7+  */
//		    	switch(name2){
//		    	case "ColumnMap": 
//		    		parameterTypes = Map.class;
//		    		args[0] = JSONObject.fromObject(value);
//		    		break;
//		    	case "DefaultColumns": 
//		    		parameterTypes = List.class;
//		    		args[0] = JSONArray.fromObject(value);
//		    		break;
//		    	default: System.out.println("No such property!<JSDocument>Import");
//		    	}
		    	Method method = JSImportNode.class.getMethod( "set"+name2, parameterTypes );
		    	method.invoke( node , args );
			}
		    catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			map.put( name1, node );
			this.setImport(map);
		}
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public JSFieldNode getField() {
		return field;
	}

	public void setField(JSFieldNode field) {
		this.field = field;
	}
	
	/** JDK1.5 Map &lt String, JSExportNode &gt  */
	public Map<String, JSExportNode> getExport() {
		return export;
	}

	public void setExport(Map<String, JSExportNode> export) {
		this.export = export;
	}

	public Map<String, JSImportNode> getImport() {
		return IMPORT;
	}

	public void setImport(Map<String, JSImportNode> iMPORT) {
		IMPORT = iMPORT;
	}

}
