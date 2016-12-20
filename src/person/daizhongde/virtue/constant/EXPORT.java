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
public class EXPORT {
	private final static String propFile = "export.properties";
	private final static String jsFile = "export_Options.js";
	
	public final static String fileName = ConfigReader_PROP.findProperty(propFile,"export.fileName");
	public final static String sheetName = ConfigReader_PROP.findProperty(propFile, "export.sheetName");
	/** fixed column count range:0~columnCount, recomend 0~4,the column start at this first and obey the original order **/
	public final static int fixColumnCount = Integer.valueOf(ConfigReader_PROP.findProperty(propFile, "export.fixColumnCount")).intValue();
	/** whether office excel file's sheet autoSizeColumn ,if data hurge care and suggest set false **/
	public final static boolean sheetAutoSizeColumn = Boolean.valueOf(ConfigReader_PROP.findProperty( propFile, "export.sheetAutoSizeColumn")).booleanValue();
	/** if the  file compressed, value can be:  true,false **/
	public final static boolean compress = Boolean.valueOf(ConfigReader_PROP.findProperty( propFile, "export.compress")).booleanValue();
	
	/** the max record size of single excel sheet , range :1~65534, one row remain for column label **/
	public final static int singleSheetRecordSize = Integer.valueOf(ConfigReader_PROP.findProperty( propFile, "export.singleSheetRecordSize")).intValue();
	/** 
	 * the max record size of single excel file , the max size is depend on the one row data size, 150000 is recommend
	 * <br>singleFileRecordSize>=singleSheetRecordSize  **/
	public final static int singleFileRecordSize = Integer.valueOf(ConfigReader_PROP.findProperty( propFile, "export.singleFileRecordSize")).intValue();
	
	public final static int level1MinCount = Integer.valueOf(ConfigReader_PROP.findProperty( propFile, "export.level1MinCount")).intValue();
	public final static int level2MinCount = Integer.valueOf(ConfigReader_PROP.findProperty( propFile, "export.level2MinCount")).intValue();
	public final static int level3MinCount = Integer.valueOf(ConfigReader_PROP.findProperty( propFile, "export.level3MinCount")).intValue();

	/**  data mode. value scope: true- synchronous / false - asynchronous **/
	public final static boolean synchronous = Boolean.valueOf(ConfigReader_PROP.findProperty( propFile,  "export.synchronous")).booleanValue();
	/** The maximum number of threads ,use of  data file **/
	public final static int maxThreadNumber = Integer.valueOf(ConfigReader_PROP.findProperty( propFile,  "export.maxThreadNumber")).intValue();
	public final static int timeout = new StringCaculate().parse(ConfigReader_PROP.findProperty( propFile, "export.timeout").replaceAll(" ", "")).intValue();

	/** All XXXX.js's config  */
	private static VirtueJSDocument JSDOC = VirtueJSReader.read(jsFile);

	public final static Map xlsExportOptions =  JSONObject.fromObject(
			JSONObject.fromObject(JSDOC.getVariables().get("xlsExportOptions"))
		);
	
	public final static Map txtExportOptions =  JSONObject.fromObject(
			JSONObject.fromObject(JSDOC.getVariables().get("txtExportOptions"))
		);
	
	// Prevent instantiation
    private EXPORT() {}
    
	public static void main(String args[]) throws NoSuchFieldException, SecurityException{
		System.out.println("ConfigConstant.class.getField(\"TAuthorityModule_query\").getName():"+EXPORT.class.getField("TAuthorityModule_query").getName());
		System.out.println("ConfigConstant.class.getField(\"TAuthorityModule_query\"):"+EXPORT.class.getField("TAuthorityModule_query"));
	}
}
