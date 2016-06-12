package person.daizhongde.virtue.configutils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

/**
 * support is not very good, need strict demands
 * @author dzd
 *
 */
public class VirtueJSReader { 
	
	private static Logger log = Logger.getLogger(VirtueJSReader.class);
	
	/**
	 * 读取配置项,有待改进,以后需要支持简单压缩过的javascript
	 * 
	 * @param key-属性名称
	 * @return 属性值
	 */
	public static VirtueJSDocument read(String fname) {
		
		log.debug("fname:"+fname);
		
		VirtueJSDocument doc = new VirtueJSDocument(fname);
		
//		doc.setName(fname);
//		File dir = new File(fname);
//		try {
//			checkFile(dir);
//		} catch (Exception e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//			System.out.println("压缩JS文件时出错!");
//		}
		
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		
		int lineNum = 1;
		String myreadline=""; // 定义一个String类型的变量,用来每次读取一行
		
		try {
			if( fname.startsWith("/com/") )
			{
//				System.out.println("1...");
				is = VirtueJSReader.class.getResourceAsStream( fname );
			}
//			else if( !fname.contains("/") )
			else if( fname.indexOf("/")==-1 )
			{
//				System.out.println("2...");
				is = VirtueJSReader.class.getResourceAsStream( "/"+fname );
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
				} 
				int indexCom = myreadline.indexOf("//");
				if(myreadline.indexOf("//")!=-1)
				{
					myreadline = myreadline.substring(0, indexCom);
				}
				if ( myreadline.startsWith("var ") ) 
				{
					if(key!=null){
						doc.setValue(key, value);
						key = null;
						value = "";
					}
					int beginIndex = myreadline.indexOf( "var " )+4;
					int endIndex =  myreadline.indexOf( "=", beginIndex );
					key = myreadline.substring(beginIndex, endIndex).trim();
					value += myreadline.substring(endIndex+1).trim();

				} 
				else if( myreadline.endsWith("};") ){
					value += myreadline.substring(0, myreadline.length()-1);
					doc.setValue(key, value);
					key = null;
					value = "";
				}else if( myreadline.endsWith("];") ){
					value += myreadline.substring(0, myreadline.length()-1);
					doc.setValue(key, processJSPlus( value.trim() ) );
					key = null;
					value = "";
				}else if( myreadline.endsWith("\";") ){
					value += myreadline.substring(0, myreadline.length()-1);
					doc.setValue(key, processJSPlus( value.trim() ) );
					key = null;
					value = "";
				}else{
					value += myreadline;
				}
				lineNum++;
			}//end of while
			if( key != null ){
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
//		System.out.println("value:"+value);
		return value;
	}
	
	// Prevent instantiation
    private void VirtueJSReader() {}
    
	public static void main(String[] args) {
		System.out.println("map:\n"+VirtueJSReader.read("AuthorityModule.js"));
		
	}

	public void testMain() throws Exception {
		String path = "C:/Users/dzd/Workspaces/MyEclipsePro2013/myapp/src/export_Options.js";
//		String path = "C:/Users/dzd/Workspaces/MyEclipsePro2013/myapp/src/com/copote/myapp/config/AuthorityModule.js";
//		File dir = new File(path);
		VirtueJSDocument doc = read(path);
//		System.out.println("doc:\n"+JSONObject.fromObject(doc).toString() );
		
		JSONObject o = JSONObject.fromObject(doc.getVariables().get("VIRTUEOptions"));
		System.out.println("doc:\n"+JSONObject.fromObject(o).toString() );
		
	}

}
