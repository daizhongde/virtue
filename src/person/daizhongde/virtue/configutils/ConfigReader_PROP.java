package person.daizhongde.virtue.configutils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader_PROP { 
	/**
	 * 读取配置项
	 * <p>
	 * 前缀                          例子                          					      说明 
<br>classpath: 	classpath:com/myapp/config.xml  从classpath中加载。
<br>file: 		file:/data/config.xml 			 作为 URL 从文件系统中加载。
<br>http: 		http://myserver/logo.png 		 作为 URL 加载。
<br>(none) 		/data/config.xml 				 根据 ApplicationContext 进行判断。

	 * @param key-属性名称
	 * @return 属性值
	 */
	public static String findProperty(String fname,String key) {
//		System.out.println("fname:"+fname+",key:"+key);
		String value = "";
//		System.out.println("user.dir="+System.getProperty("user.dir"));
		Properties prop = new Properties();
		InputStream is = null;
		try {
			if( fname.startsWith("com/") )
			{
//				System.out.println("1 starts with com/");
//				is = ConfigReader_PROP.class.getResourceAsStream( fname );
//				Properties  properties = System.getProperties();
////				properties.list(System.out);
//				if( properties.get("sun.java.command").toString().indexOf("junit") != -1 ){
//					
//				}
				String path =ConfigReader_PROP.class.getResource("/").getPath();//得到工程名WEB-INF/classes/路径

//				同目录文件夹：classes，m2e-jee，test-classes
				int index = path.indexOf("/test-classes/");
				
//				System.out.println("path:"+path);
//				path:/D:/daizd/Workspaces/MyEclipse%202015%20CI/migration/target/test-classes/
//				or: D:/tomcat/webapps/migration/WEB-INF/classes/
				
			    path=path.substring(0, 
			    		index==-1 ? path.indexOf("/classes/") : index
			    	);//从路径字符串中取出工程路径

			    if(fname.indexOf("authority") != -1){
			    	is = ConfigReader_PROP.class.getResourceAsStream( "/"+fname );
			    }else{
			    	is = new FileInputStream( java.net.URLDecoder.decode( path +"/classes/"+ fname,"UTF-8") );
			    }
			}
			else if( fname.startsWith("/WEB-INF/") )
			{
//				System.out.println("2...");
				String path =ConfigReader_PROP.class.getResource("/").getPath();//得到工程名WEB-INF/classes/路径
				
//				System.out.println("path:"+path);
			    path=path.substring(0, path.indexOf("/WEB-INF/"));//从路径字符串中取出工程路径
				is = new FileInputStream( java.net.URLDecoder.decode( path + fname,"UTF-8") );
			}
			else if( fname.indexOf("/")==-1 )
			{
//				System.out.println("3...");
				is = ConfigReader_PROP.class.getResourceAsStream( "/"+fname );
			}
			else
			{
//				System.out.println("4...");
				is = new FileInputStream( fname);
			}
			prop.load(is);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		value = prop.getProperty(key);
//		System.out.println(key+":" + value);
		return value;
	}
	/**
	 * 读取配置项
	 * 
	 * @param key-属性名称
	 * @return 属性值
	 */
	public static Properties findAll( String fname ) {
		String value = "";
		// System.out.println("user.dir="+System.getProperty("user.dir"));
		Properties prop = new Properties();
		InputStream in = null;
		try {
			if( fname.startsWith("/com/")
					|| fname.indexOf("/")==-1 ){
	//			InputStream in = getClass().getResourceAsStream("/"+fname);
				in = ConfigReader_PROP.class.getResourceAsStream( fname );
			}else{
				in = new FileInputStream( fname);
			}
			prop.load(in);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
//		value = prop.getProperty(key);
//		System.out.println(key+":" + value);
		return prop;
	}
	// Prevent instantiation
    private void ConfigReader_PROP() {}
    
	public static void main(String[] args) {
////		ConfigFileReader cf = new ConfigFileReader();
//		System.out.println("value:"+ConfigReader_PROP.findProperty("sql")+"\n"+ConfigReader_PROP.findProperty("tableColumnNames"));
//		String[] ns = ConfigReader_PROP.findProperty("tableColumnNames").split("\\|");
//		System.out.println("ns[0]:"+ns[0]);
//		Vector<String> leftTableColumnV=null;
//		for (int i = 0; i < ns.length; i++) {
//			leftTableColumnV.add(ns[i]);
//		}
	}
}
