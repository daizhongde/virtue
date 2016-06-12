package person.daizhongde.virtue.configutils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import net.sf.json.JSONObject;

/**
 * a file can include muti-SQLDocument, but need update to extend....
 * @author dzd
 *
 */
public class ConfigReader_SQL {
	/**
	 * 读取配置项
	 * 
	 * @param key-属性名称
	 * @return 属性值
	 */
	public static ConfigDocument_SQL read(String fname) {
		ConfigDocument_SQL doc = new ConfigDocument_SQL();
//		Map map = new HashMap();
		
		String var = "";
		String prefix = "--@JavaScript ";
		
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		
		int lineNum = 1;
		String myreadline=""; // 定义一个String类型的变量,用来每次读取一行
		
		try {
			if( fname.startsWith("com/") )
			{
				String path =ConfigReader_SQL.class.getResource("/").getPath();//得到工程名WEB-INF/classes/路径
				
//				System.out.println("path:"+path);
//				path:/D:/daizd/Workspaces/MyEclipse%202015%20CI/migration/target/test-classes/
//				or: D:/tomcat/webapps/migration/WEB-INF/classes/
				
				int index = path.indexOf("/test-classes/");
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
				is = ConfigDocument_SQL.class.getResourceAsStream( "/"+fname );
			}
			else
			{
				is = new FileInputStream( fname );
			}
			
			isr = new InputStreamReader(is, "UTF-8");
			br = new BufferedReader( isr );
			
			String key=null;
			String value="";
			boolean comment = false;
			
			while (br.ready()) {
				
				myreadline = br.readLine().trim();// 读取一行
				
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
//					|| myreadline.startsWith("//")
//					|| myreadline.startsWith("/*")
//					|| myreadline.startsWith("*")
//					|| myreadline.startsWith("*/")
					|| ( myreadline.startsWith("--") && !myreadline.startsWith("--@JavaScript ") )
					|| myreadline.startsWith("#") ) {
					
					lineNum++;
					continue;
				} else if ( myreadline.startsWith("--@JavaScript var ") ) {
					
					int beginIndex = myreadline.indexOf( "--@JavaScript var " )+18;
					int endIndex =  myreadline.indexOf( "=", beginIndex );
					var = myreadline.substring(beginIndex, endIndex).trim();
//					map.put("var", var );
					doc.setName(var);
					
				} else if( var != null ) {
					/* If the line is defined a new variable */
					if(myreadline.startsWith( prefix + var + "." )){
						if( key != null ){
//							map.put(key, value.trim());							
							doc.setValue(key, value.trim() );
							key = null;
							value = "";
						}
						int varIndex = myreadline.indexOf( prefix + var + "." );
						int beginIndex = myreadline.indexOf( ".", varIndex )+1;
						key = myreadline.substring(beginIndex);
						value = "";
					}else if( myreadline.endsWith(";") ){
						value += myreadline.substring(0, myreadline.length()-1);
//						map.put( key,  value.trim() );
						doc.setValue(key, value.trim() );
						key = null;
						value = "";
					}else{
						int lineCom = myreadline.indexOf("--");
						int paraCom = myreadline.indexOf("/*");
												
						if(paraCom!=-1){
							myreadline = myreadline.substring(0,paraCom);
							if(!myreadline.endsWith("*/")){
								comment = true;
							}
						}
						
						if(lineCom!=-1 && !myreadline.startsWith("--@JavaScript ") )
							myreadline = myreadline.substring(0,lineCom);
						
						value += myreadline+" ";
					}
				}
				lineNum++;
			}
			if( key != null ){
//				map.put(key, value.trim() );
				doc.setValue(key, value.trim() );
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

	// Prevent instantiation
    private void ConfigReader_SQL() {}
    
	public static void main(String[] args) {
//		String path = "AuthorityModule.sql";
		String path ="C:/Users/dzd/Workspaces/MyEclipsePro2013/UniversalSearch/src/com/copote/nontaxteam/config/IntTxnLog.sql";
		ConfigDocument_SQL doc = read(path);
		System.out.println("doc:\n"+JSONObject.fromObject(doc).toString() );
		
	}

	public void testMain() throws Exception {
//		String path = "C:/Users/dzd/Workspaces/MyEclipsePro2013/myapp/src/com/copote/myapp/config/AuthorityModule.sql";
		String path ="C:/Users/dzd/Workspaces/MyEclipsePro2013/UniversalSearch/src/com/copote/nontaxteam/config/IntTxnLog.sql";
//		String path = "AuthorityModule.sql";
//		File dir = new File(path);
		ConfigDocument_SQL doc = read(path);
		System.out.println("doc:\n"+JSONObject.fromObject(doc).toString() );
		
	}
}
