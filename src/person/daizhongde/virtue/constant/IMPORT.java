package person.daizhongde.virtue.constant;

import java.util.Map;

import net.sf.json.JSONObject;
import person.daizhongde.virtue.configutils.ConfigReader_PROP;
import person.daizhongde.virtue.configutils.StringCaculate;
import person.daizhongde.virtue.configutils.VirtueJSDocument;
import person.daizhongde.virtue.configutils.VirtueJSReader;
/**
 * load on web server start. 
 * <br>change value need restart server, then the new value become effective
 * <p>globel config don't write getter,but special config like pertable need write getter to support develop mode 
 * @author dzd
 *
 */
public final class IMPORT {
	private final static String propFile = "import.properties";
	private final static String jsFile = "import_Options.js";

	/** 
	 * the max import file size , 60MB is recommend
	 */
	public final static int singleFileMaxSize = 
		new StringCaculate().parse(ConfigReader_PROP.findProperty( propFile, "import.singleFileMaxSize").replaceAll(" ", "")).intValue();
//		Integer.valueOf(ConfigReader_PROP.findProperty( propFile, "import.singleFileMaxSize")).intValue();
	/** Unit is Byte **/
	public final static int level1MinSize = 
		new StringCaculate().parse(ConfigReader_PROP.findProperty( propFile, "import.level1MinSize").replaceAll(" ", "")).intValue();
//		Integer.valueOf(ConfigReader_PROP.findProperty( propFile, "import.level1MinSize")).intValue();
	/** Unit is Byte **/
	public final static int level2MinSize = 
		new StringCaculate().parse(ConfigReader_PROP.findProperty( propFile, "import.level2MinSize").replaceAll(" ", "")).intValue();
//		Integer.valueOf(ConfigReader_PROP.findProperty( propFile, "import.level2MinSize")).intValue();
	/** Unit is Byte **/
	public final static int level3MinSize = 
		new StringCaculate().parse(ConfigReader_PROP.findProperty( propFile, "import.level3MinSize").replaceAll(" ", "")).intValue();
//		Integer.valueOf(ConfigReader_PROP.findProperty( propFile, "import.level3MinSize")).intValue();

	/**  data mode. value scope: true- synchronous / false - asynchronous **/
	public final static boolean synchronous = Boolean.valueOf(ConfigReader_PROP.findProperty( propFile,  "import.synchronous")).booleanValue();
	/** The maximum number of threads ,use of  data file **/
	public final static int maxThreadNumber = Integer.valueOf(ConfigReader_PROP.findProperty( propFile,  "import.maxThreadNumber")).intValue();
	public final static int timeout = new StringCaculate().parse(ConfigReader_PROP.findProperty( propFile, "import.timeout").replaceAll(" ", "")).intValue();

	/** All XXXX.js's config  */
	private static VirtueJSDocument JSDOC = VirtueJSReader.read(jsFile);

	public final static Map xlsImportOptions =  JSONObject.fromObject(
			JSONObject.fromObject(JSDOC.getVariables().get("xlsImportOptions"))
		);
	
	public final static Map txtImportOptions =  JSONObject.fromObject(
			JSONObject.fromObject(JSDOC.getVariables().get("txtImportOptions"))
		);
	
	// Prevent instantiation
    private IMPORT() {}
    
	public static void main(String args[]) throws NoSuchFieldException, SecurityException{
		System.out.println("ConfigConstant.class.getField(\"TAuthorityModule_query\").getName():"+IMPORT.class.getField("TAuthorityModule_query").getName());
		System.out.println("ConfigConstant.class.getField(\"TAuthorityModule_query\"):"+IMPORT.class.getField("TAuthorityModule_query"));
	}
}
