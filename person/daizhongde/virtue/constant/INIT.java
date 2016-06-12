package person.daizhongde.virtue.constant;

import person.daizhongde.virtue.configutils.ConfigReader_PROP;

/**
 * load on web server start. 
 * <br>change value need restart server, then the new value become effective
 * <p>globel config don't write getter,but special config like pertable need write getter to support develop mode 
 * @author dzd
 *
 */
public class INIT {
	private final static String CFName = "init.properties";
	
	private final static String _PRODUCTION_MODE = ConfigReader_PROP.findProperty(CFName, "PRODUCTION_MODE");
	public final static boolean PRODUCTION_MODE = _PRODUCTION_MODE==null ? 
			false : Boolean.valueOf( _PRODUCTION_MODE ).booleanValue();
	
	private final static String OS = System.getProperties().getProperty("os.name");
//	private final static String OS = _OS.toLowerCase().startsWith("win")?"windows":"linux";
	
	public final static String AUTH_schema = ConfigReader_PROP.findProperty(CFName, "AUTH.schema");
	public final static String AUTH_configFileDirectory = 
			ConfigReader_PROP.findProperty( CFName, 
				"AUTH.configFileDirectory"
			).replaceFirst("[\\\\\\/]+$", "");
	
	public final static String BUSI_schema = ConfigReader_PROP.findProperty(CFName, "BUSI.schema");
	
//	/**
//	 * Currently don't refer to multi-datasource in one project,
//	 * It refered, need extract some init's properties to respective configs 
//	 */
//	public final static String authorityConfigFileDirectory = 
//		ConfigReader_PROP.findProperty( CFName,
//			"authority.configFileDirectory"
//		).replaceFirst("[\\\\\\/]+$", "");
	/**
	 * replace end /
	 */
	public final static String BUSI_configFileDirectory = 
		ConfigReader_PROP.findProperty( CFName, 
			"BUSI.configFileDirectory"
		).replaceFirst("[\\\\\\/]+$", "");
	public final static String configFileDirectory = BUSI_configFileDirectory;
	
//	# ######################### test config begin ############################
	public final static String driver = ConfigReader_PROP.findProperty(CFName, "driver");
	public final static String url = ConfigReader_PROP.findProperty(CFName, "url");
	public final static String dbuser = ConfigReader_PROP.findProperty(CFName, "dbuser");
	public final static String dbpass = ConfigReader_PROP.findProperty(CFName, "dbpass");
	
	public final static String jndi = ConfigReader_PROP.findProperty(CFName, "jndi");
	public final static String factory = ConfigReader_PROP.findProperty(CFName, "factory");
	public final static String PROVIDER_URL = ConfigReader_PROP.findProperty(CFName, "PROVIDER_URL");
	public final static String principal = ConfigReader_PROP.findProperty(CFName, "principal");
	public final static String credentials = ConfigReader_PROP.findProperty(CFName, "credentials");
//	# ######################### test config end ############################
	
	private final static String _defaultOperatorIndex = ConfigReader_PROP.findProperty( CFName,"defaultOperatorIndex" );
//	static{
//		System.out.println("_defaultOperatorIndex:"+_defaultOperatorIndex);
//	}
	/** defalut operator's index is 1
	 *  <p>       0      1=   2!=    3>    4>=     5<     6<=   7like s% 8     9like %s  10  11like %s%  12    13
	 *  <br>{" - 选择 - ","等于","不等于","大于","大于或等于","小于","小于或等于","开头是","开头不是","结尾是","结尾不是","包含","不包含"};    in (1,2,3) 
	 * 
	 * **/
	public final static int defaultOperatorIndex = 
			_defaultOperatorIndex==null ? 
			Operator.EQUAL : Integer.parseInt( _defaultOperatorIndex );
	
	/** don't define to Boolean, because if bool when you haven't config this value will throw nullpoint exception  **/
	public final static String supportSetParameterList = ConfigReader_PROP.findProperty( CFName,"supportSetParameterList" );

	private final static String _WindowsLocalFileRootDirectory = ConfigReader_PROP.findProperty( CFName, "windows.localFileRootDirectory" ) ;
//	private final static String _WindowsTempFileDirectory = ConfigReader_PROP.findProperty( CFName, "windows.tempFileDirectory" ) ;
	
	private final static String _LinuxLocalFileRootDirectory = ConfigReader_PROP.findProperty( CFName, "linux.localFileRootDirectory" ) ;
//	private final static String _LinuxTempFileDirectory = ConfigReader_PROP.findProperty( CFName, "linux.tempFileDirectory" ) ;
	
	public final static String localFileRootDirectory = 
			(OS.toLowerCase().startsWith("win")?
				_WindowsLocalFileRootDirectory : _LinuxLocalFileRootDirectory
			).replaceFirst("[\\\\\\/]+$", "");
	
//	/export/excel/
	
	public final static String tempFileDirectory = localFileRootDirectory+"/temp";
//	public final static String tempFileDirectory = 
//			(OS.toLowerCase().startsWith("win")?
//				_WindowsTempFileDirectory : _LinuxTempFileDirectory
//			).replaceFirst("[\\\\\\/]+$", "");
	
	
	public final static int maxThreadNum = Integer.valueOf(ConfigReader_PROP.findProperty( CFName, "maxThreadNum")).intValue();
//	public final static int maxThreasingleJobMaxThreadNumdNum = Integer.valueOf(ConfigReader_PROP.findProperty( CFName, "singleJobMaxThreadNum")).intValue();
	
	/** below field can't be customized, relative to xls  */
	public final static int IETxtMultiple = Integer.valueOf(ConfigReader_PROP.findProperty( CFName, "IE.txtMultiple")).intValue();
	
	public final static int IELevel1SemaphoreCount = Integer.valueOf(ConfigReader_PROP.findProperty( CFName, "IE.level1SemaphoreCount")).intValue();
	public final static int IELevel2SemaphoreCount = Integer.valueOf(ConfigReader_PROP.findProperty( CFName, "IE.level2SemaphoreCount")).intValue();
	public final static int IELevel3SemaphoreCount = Integer.valueOf(ConfigReader_PROP.findProperty( CFName, "IE.level3SemaphoreCount")).intValue();
	
	public final static String connextion_ip  = ConfigReader_PROP.findProperty(CFName, "connextion.ip");
	public final static String connextion_port = ConfigReader_PROP.findProperty(CFName, "connextion.port");
	public final static String retail_ip = ConfigReader_PROP.findProperty(CFName, "retail.ip");
	public final static String retail_port = ConfigReader_PROP.findProperty(CFName, "retail.port");
    // Prevent instantiation
    private INIT() {}
    
	public static void main(String args[]) throws NoSuchFieldException, SecurityException{
		System.out.println("ConfigConstant.class.getField(\"TAuthorityModule_query\").getName():"+INIT.class.getField("TAuthorityModule_query").getName());
		System.out.println("ConfigConstant.class.getField(\"TAuthorityModule_query\"):"+INIT.class.getField("TAuthorityModule_query"));

		
	}
}