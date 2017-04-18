package person.daizhongde.virtue.configutils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

/**
 * support is not very good, need strict demands
 * @author dzd
 *
 */
public class ConfigReader_JS { 
	
	/**
	 * 读取配置项,有待改进,以后需要支持简单压缩过的javascript
	 * 
	 * @param key-属性名称
	 * @return 属性值
	 */
	public static ConfigDocument_JS read(String fname) {
		ConfigDocument_JS doc = new ConfigDocument_JS();
//		File dir = new File(fname);
//		try {
//			checkFile(dir);
//		} catch (Exception e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//			System.out.println("压缩JS文件时出错!");
//		}
		String var = "";
		Map map = new HashMap();
		
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		
		int lineNum = 1;
		String myreadline=""; // 定义一个String类型的变量,用来每次读取一行
		
		try {
			if( fname.startsWith("com/") )
			{
				String path =ConfigReader_JS.class.getResource("/").getPath();//得到工程名WEB-INF/classes/路径
				
				int index = path.indexOf("/test-classes/");
				
//				System.out.println("path:"+path);
//				path:/D:/daizd/Workspaces/MyEclipse%202015%20CI/migration/target/test-classes/
//				or: D:/tomcat/webapps/migration/WEB-INF/classes/
				
			    path=path.substring(0, 
			    		index==-1 ? path.indexOf("/classes/") : index
			    	);//从路径字符串中取出工程路径

			    if(fname.indexOf("authority") != -1){
			    	is = ConfigDocument_JS.class.getResourceAsStream( "/"+fname );
			    }else{
			    	is = new FileInputStream( java.net.URLDecoder.decode( path +"/classes/"+ fname,"UTF-8") );
			    }
			}
			else if( fname.indexOf("/")==-1 )
			{
//				System.out.println("2...");
				is = ConfigDocument_JS.class.getResourceAsStream( "/"+fname );
			}
			else
			{
//				System.out.println("3...");
				is = new FileInputStream( fname);
			}
//			fr = new FileReader( fname );// 创建FileReader对象，用来读取字符流
//			br = new BufferedReader(fr); // 缓冲指定文件的输入
//			br = new BufferedReader( new InputStreamReader( new FileInputStream(fname), "UTF-8" ) );
			
//			fis = new FileInputStream(fname);
			isr = new InputStreamReader(is, "UTF-8");
			br = new BufferedReader( isr );
			
			String key=null;
			String value="";
			boolean comment = false;
			
			while (br.ready()) {
				myreadline = br.readLine().trim();// 读取一行
				
				//去掉代码后面的行注释,only after ", like :  .. xxx",  //fdsfsfsf
				myreadline = myreadline.replaceFirst("\"\\s*,\\s*//.*$", "\",");
				/* clear section comment in the back of code which is in the same line, Havn't being test  */
				myreadline = myreadline.replaceFirst("\\s*/\\*.*\\*/\\s*$", "");
				
				if( myreadline.startsWith("/*") && myreadline.endsWith("*/")){
					
					lineNum++;
					continue;
				}
				if( myreadline.startsWith("/*") ){//!myreadline.endsWith("*/")
					comment = true;
					
					lineNum++;
					continue;
				}
				if( comment && myreadline.endsWith("*/")){
					comment = false;
					
					lineNum++;
					continue;
				}
				if( comment ){//!myreadline.endsWith("*/")
					
					lineNum++;
					continue;
				}
				if (myreadline == null
					|| myreadline.equalsIgnoreCase("")
					|| myreadline.startsWith("//")
//					|| myreadline.startsWith("/*")
//					|| myreadline.startsWith("*")
//					|| myreadline.startsWith("*/")
					|| myreadline.startsWith("--")
					|| myreadline.startsWith("#") ) {
					
					lineNum++;
					continue;
				} else if ( myreadline.startsWith("var ") ) {
					
					int beginIndex = myreadline.indexOf( "var " )+4;
					int endIndex =  myreadline.indexOf( "=", beginIndex );
					var = myreadline.substring(beginIndex, endIndex).trim();
//					map.put("var", var );
					doc.setName(var);
					
				} else if( var != null ) {
					/* If the line is defined a new variable */
					if(myreadline.startsWith( var+"." )){
						if( key != null ){
//							map.put(key, value.trim());
							doc.setValue(key, processJSPlus( value.trim() ) );
							key = null;
							value = "";
						}
						int varIndex = myreadline.indexOf( var+"." );
						int beginIndex = myreadline.indexOf( ".", varIndex )+1;
						int endIndex =  myreadline.indexOf( "=", beginIndex );
						key = myreadline.substring(beginIndex, endIndex).trim();
						
						value = myreadline.substring(endIndex+1).trim();
						if(value.endsWith("];")){
							doc.setValue(key, processJSPlus( value.trim() ) );
							key = null;
							value = "";
						}
					}else if( myreadline.endsWith("};") ){
						value += myreadline.substring(0, myreadline.length()-1);
//						map.put( key,  value );
						doc.setValue(key, processJSPlus( value.trim() ) );
						key = null;
						value = "";
					}else if( myreadline.endsWith("];") ){
						value += myreadline.substring(0, myreadline.length()-1);
//						map.put( key,  value );
						doc.setValue(key, processJSPlus( value.trim() ) );
						key = null;
						value = "";
					}else if( myreadline.endsWith("\";") ){//for string variable
						value += myreadline.substring(0, myreadline.length()-1);
//						map.put( key,  value );
						doc.setValue(key, processJSPlus( value.trim() ) );
						key = null;
						value = "";
					}else{
						value += myreadline;
					}
				}
				lineNum++;
			}
			if( key != null ){
//				map.put(key, value.trim() );
				doc.setValue(key, processJSPlus( value.trim() ) );
//				System.out.println("value:"+value);
				key = null;
				value = "";
			}
		} catch (Exception e) {
			System.out.println("lineNum:"+lineNum);
			System.out.println("lineContent:"+myreadline);
			e.printStackTrace();
		}finally{
			try {
				br.close();//先关外层流
				isr.close();
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return doc;
	}

	public static String processJSPlus(String value) {
		value = value.replaceAll("\" *\\+ *\"", "");
		value = value.replaceFirst(";+$", "");
//		System.out.println("value:"+value);
		return value;
	}
	
	// Prevent instantiation
    private void ConfigReader_JS() {}
    
	public static void main(String[] args) {
		System.out.println("map:\n"+ConfigReader_JS.read("AuthorityModule.js"));
		
	}

	public void testMain() throws Exception {
		String path = "C:/Users/dzd/Workspaces/MyEclipsePro2013/myapp/src/com/copote/myapp/config/PayerCommiInfo.js";
//		String path = "C:/Users/dzd/Workspaces/MyEclipsePro2013/myapp/src/com/copote/myapp/config/AuthorityModule.js";
//		File dir = new File(path);
		ConfigDocument_JS doc = read(path);
		System.out.println("doc:\n"+JSONObject.fromObject(doc).toString() );
		
	}

}
