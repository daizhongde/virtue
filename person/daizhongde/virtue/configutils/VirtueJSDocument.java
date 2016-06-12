package person.daizhongde.virtue.configutils;

import java.util.HashMap;
import java.util.Map;

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
public class VirtueJSDocument {

	/**
	 * a file can include mutil-SQLDocument
	 * 
	 */
	private String name;
	
//	JSExportNode _expMap = new JSExportNode(JSONObject.fromObject(JSMAP.get("EXP")));
	private Map variables = new HashMap();
	
	public VirtueJSDocument(){
		
	}
	
	public VirtueJSDocument(String name ){
		this.name = name;
	}
	/**
	 * @param key
	 * @param value
	 */
	public void setValue(String key, String value){
		variables.put(key, value);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map getVariables() {
		return variables;
	}

	public void setVariables(Map variables) {
		this.variables = variables;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

}
